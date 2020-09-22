/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prodavac;

import entiteti.Artikal;
import entiteti.NaStanju;
import entiteti.Prodavnica;
import entiteti.Rezervacija;
import static java.lang.System.exit;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.jms.ConnectionFactory;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;
import javax.jms.Topic;


/**
 *
 * @author Nemanja
 */
public class Prodavac  {
    
    static int prodavnica = 1;
    
    @Resource(lookup = "jms/__defaultConnectionFactory")
    private static ConnectionFactory connectionFactory;
    
    @Resource(lookup = "Topic")
    private static Topic topic;
    
    private static  Upiti upiti ;
   
   
   
   
    public static void main(String[] args) {
        upiti = new Upiti();
        Scanner  scan = new Scanner(System.in);
        System.out.println("id Prodavca: " + prodavnica);
            while(true){
                JMSContext context = connectionFactory.createContext();
                context.setClientID("Prodavnica" + prodavnica);
                JMSConsumer consumer = context.createDurableConsumer(topic, "Prodavnica" + prodavnica, "idP = '" + prodavnica + "'", false);
                JMSProducer producer = context.createProducer();
                
                
                
                
                
                System.out.println("\nUnesite izbor: ");
                System.out.println("1. Kupi odredjeni artikal ");
                System.out.println("2. Kupi rezervisani artikal ");
                System.out.println("3. Pretrazite artikal po tipu");
                System.out.println("4. Pretrazite artikal po nazivu");
                System.out.println("0. Kraj");
                String unos = scan.next();
                
                
                
                switch(unos){
                    case "1":   
                                System.out.println("Koji artikal zelite da kupite?");
                                String artikal = scan.next();
                                System.out.println("Kolicina?");
                                int kolicina = scan.nextInt();
                                if(upiti.proveri_stanje(artikal, kolicina, prodavnica)){
                                    System.out.println("Zelite li da proverite stanje artikla? \n 1. Da \n 2. Ne\n");
                                    int izbor = scan.nextInt();
                                    if (izbor == 1) System.out.println(proveri_stanje(artikal, prodavnica, 1, producer, consumer, context ));
                                    System.out.println("Zelite da izvrsite kupovinu? \n 1. Da\n 2. Ne\n");
                                    izbor = scan.nextInt();
                                    if (izbor == 1) upiti.prodaj(artikal,kolicina, prodavnica);
                                
                                }else{
                                    System.out.println("Trazeni artikal nemamo na stanju, mozemo da proverimo ostale prodavnice. \nU kom stanju zelite vas artikal? \n 1. Zapakovan\n 2. Otpakovan\n");
                                    int izbor = scan.nextInt();
                                    String stanje;
                                    if (izbor == 1) stanje = "Zapakovan";
                                    else stanje = "Otpakovan";
                                    List<NaStanju> nastanju = upiti.proveri_ostale_prodavnice(artikal, kolicina);
                                    NaStanju a ;
                                    String stanje_prod;
                                    
                                    if(!nastanju.isEmpty()){
                                        int i = 0;
                                        boolean pronadjen = false;
                                        for(NaStanju ns : nastanju){
                                            stanje_prod = proveri_stanje(artikal, ns.getProdavnica().getIdProdavnica(), 1, producer, consumer, context );
                                            if(stanje_prod.equals(stanje)){
                                                System.out.println("Trazeni artikal imamo na stanju u prodavnici: " + i + ". " + ns.getProdavnica().getNaziv() + ", kolicina: " + ns.getKolicina() + ", stanje: " + stanje_prod);
                                                pronadjen = true;
                                            }else{
                                                System.out.println("Trazeni artikal imamo na stanju u prodavnici: " + i + ". " + ns.getProdavnica().getNaziv() + ", kolicina: " + ns.getKolicina() + ", stanje: " + stanje_prod + ", a vi ste trazili: " + stanje);
                                            }
                                            i++;
                                        }
                                        if(pronadjen){
                                            System.out.println("Zelite li da izvrsimo rezervaciju? \n 1. Da\n 2. Ne\n");
                                            izbor = scan.nextInt();
                                            if (izbor == 1) {
                                                System.out.println("U kojoj prodavnici zelite da izvrsite rezervaciju? ");
                                                izbor = scan.nextInt();
                                                a = nastanju.get(izbor);
                                                System.out.println("Gde zelite da preuzmete artikal? \n0. Ovde \n1. " + a.getProdavnica().getNaziv());
                                                izbor = scan.nextInt();
                                                int rez_prod;
                                                String kontakt = "";
                                                if(izbor == 0){
                                                    rez_prod = prodavnica;
                                                    System.out.println("Unesite kontakt telefon " );
                                                    kontakt  = scan.next();
                                                }else{
                                                    rez_prod = a.getProdavnica().getIdProdavnica();
                                                }
                                                
                                                System.out.println("Unesite vase Ime" );
                                                String ime  = scan.next();
                                                System.out.println("Unesite vase Prezime" );
                                                String prezime  = scan.next();
                                                System.out.println("Unesite vas JMBG " );
                                                String jmbg  = scan.next();
                                                boolean rezervacija = rezervisi(ime, prezime, jmbg, kolicina, 2 , a.getArtikal(), a.getProdavnica().getIdProdavnica(), context, producer, consumer, rez_prod, kontakt);
                                                if(rezervacija){
                                                    System.out.println("Izvrsena je rezervacija za artikal: " + a.getArtikal().getNaziv() + ", kupcu sa JMBG " + jmbg);
                                                }else{
                                                    System.out.println("Doslo je do greske!!!");
                                                }
                                            }
                                        }
                                    }else {
                                        System.out.println("Trazeni artikal nemamo na stanju ni u jednoj prodavnici!");
                                    }
                        
                                }
                                break;
                    
                    case "2":  
                                System.out.println("Unesite vas JMBG");
                                String jmbg = scan.next();
                                Rezervacija rezervacija = upiti.proveri_rezervaciju(jmbg, prodavnica);
                                if(rezervacija != null){
                                    System.out.println("Vas artikal je: " + rezervacija.getFkidArtikal().getNaziv() + ", kolicina: " + rezervacija.getKolicina());
                                    System.out.println("Zelite da izvrsite kupovinu? \n 1. Da\n 2. Ne\n");
                                    int izbor = scan.nextInt();
                                    if (izbor == 1) upiti.prodaj(rezervacija.getFkidArtikal().getNaziv(),rezervacija.getKolicina(), prodavnica);
                                    upiti.skini_rezervaciju(rezervacija);
                                }else{
                                    System.out.println("Ne postoji rezervacija za unete podatke!!!");
                                }
                                break;
                    
                    case "3":   
                                System.out.println("Unesite tip");
                                String tip = scan.next();
                                upiti.pretrazi_tip(tip);
                                break;
                    
                    case "4":   
                                System.out.println("Unesite naziv");
                                String naziv = scan.next();
                                upiti.pretrazi_naziv(naziv);
                                break;
                    
                    case "0":   exit(0);
                    
                    default :   System.out.println("Pogresan unos!!! ");
                                break;
                    
                }
                upiti.istekle_rezervacije();
                consumer.close();
                context.close();
            }  
              
    }
          

    public static String proveri_stanje(String artikal, int idMenadzera, int izbor, JMSProducer producer, JMSConsumer consumer, JMSContext context){
        String stanje = "";
        try {
            ObjectMessage objectMessage = context.createObjectMessage();
            
            objectMessage.setObject(artikal);
            objectMessage.setIntProperty("Radnja", izbor);
            objectMessage.setStringProperty("idM", "" + idMenadzera);
            objectMessage.setDoubleProperty("Prodavac", prodavnica);
            
            producer.send(topic, objectMessage);
            
            Message message = consumer.receive();
            
            if(message instanceof TextMessage){
                TextMessage textMessage = (TextMessage) message;
                stanje = textMessage.getText();
            }
            
            
        } catch (JMSException ex) {
            Logger.getLogger(Prodavac.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return stanje;
        
    }
   
    public static boolean rezervisi(String ime, String prezime, String jmbg, int kolicina, int izbor, Artikal artikal, int idMenadzera, JMSContext context, JMSProducer producer, JMSConsumer consumer, int rez_prod, String kontakt){
        try {
            //proveri da li postoji rezervacija za tu prodavnicu
            Rezervacija rezervacija_postoji = upiti.proveri_rezervaciju(jmbg, idMenadzera);
            if(rezervacija_postoji == null){
                Prodavnica prodavnica_rez = upiti.nadji_prodavnicu(rez_prod);
                Rezervacija rezervacija = new Rezervacija();
                rezervacija.setIme(ime);
                rezervacija.setPrezime(prezime);
                rezervacija.setKolicina(kolicina);
                rezervacija.setJmbg(jmbg);
                rezervacija.setFkidArtikal(artikal);
                rezervacija.setMestoPodizanja(prodavnica_rez.getNaziv()); //mesto podizanja
                rezervacija.setKontakt(kontakt); //kontakt podaci
                //istek
                Date today = new Date();
                today.setDate(today.getDate()+2);
                SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy' 'HH:mm");
                String datum = formatter.format(today);
                
                rezervacija.setIstek(datum);
                
                ObjectMessage objectMessage = context.createObjectMessage();
                objectMessage.setObject(rezervacija);
                objectMessage.setIntProperty("Radnja", izbor);
                objectMessage.setStringProperty("idM", "" + idMenadzera);
                objectMessage.setDoubleProperty("Prodavac", prodavnica);
                
                producer.send(topic, objectMessage);
                
                Message message = consumer.receive();

                if(message instanceof TextMessage){
                    TextMessage textMessage = (TextMessage) message;
                    System.out.println("\n" + textMessage.getText());
                }
            }else{
                System.out.println("Vec postoji rezervacija sa datim podacima");
                return false;
            }
        } catch (JMSException ex) {
            Logger.getLogger(Prodavac.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
}
   
