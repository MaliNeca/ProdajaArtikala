/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package radnik;

import entiteti.Artikal;
import static java.lang.System.exit;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.ObjectMessage;
import javax.jms.Topic;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Nemanja
 */
public class Radnik  {

    
    @Resource(lookup = "jms/__defaultConnectionFactory")
    private static ConnectionFactory connectionFactory;
    
    @Resource(lookup = "Topic")
    private static Topic topic;
    @Resource(lookup = "MyTopicPrijem")
    static Topic topic_prijem;
    
    static int id = 2;
    
    @SuppressWarnings("SleepWhileInLoop")
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("RadnikPU");
        EntityManager em = emf.createEntityManager();
        Scanner  scan = new Scanner(System.in);
        
        JMSContext context = connectionFactory.createContext();
        context.setClientID("Radnik" + id);
        System.out.println("id Radik: " + id);
        JMSProducer producer = context.createProducer();     
        while(true){
            System.out.println("1. Proizvedi artikal");
            System.out.println("2. Promeni cenu");
            System.out.println("0. Kraj");
            String unos = scan.next();
                
            
            switch(unos){
                case "1":   try{
                                System.out.println("Koji artikal zelite da proizvedemo");
                                String naziv = scan.next();
                                System.out.println("Kolicina?");
                                int kolicina = scan.nextInt();

                                Query query = em.createNativeQuery("SELECT * FROM Artikal WHERE naziv ='" + naziv +"'",Artikal.class);
                                List<Artikal> artikli = query.getResultList();
                                Artikal artikal;
                                if(!artikli.isEmpty()){
                                    artikal = artikli.get(0);                        
                                    //proizvodi
                                    System.out.println("Proizvodi se...");
                                    Thread.sleep(artikal.getVreme() * 1000); 
                                    
                                    //salje
                                    
                                    int idMenadzera = (int) (Math.random() * 5 + 1);
                                    
                                    ObjectMessage objectMessage = context.createObjectMessage();
                                    objectMessage.setObject(artikal);
                                    objectMessage.setIntProperty("Radnja", 3);
                                    objectMessage.setStringProperty("idM", "" + idMenadzera);
                                    objectMessage.setIntProperty("Kolicina", kolicina);
            
                                    producer.send(topic, objectMessage);
                                    System.out.println("Poslat je artikal: " + artikal.getNaziv() + ", kolicina: " + kolicina + ", prodavnici id: " + idMenadzera);
                                      
                                }else{
                                    System.out.println("Takav artikal ne postoji u bazi");
                                }
                                

                                
                            } catch (InterruptedException | JMSException ex) {
                                Logger.getLogger(Radnik.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                break;
                                
                case "2":   System.out.println("Za koji artikal zelite da promenite cenu");
                            String naziv = scan.next();
                            System.out.println("Nova cena?");
                            int cena = scan.nextInt();
                           
                            Query query = em.createNativeQuery("SELECT * FROM Artikal WHERE naziv ='" + naziv +"'",Artikal.class);
                            List<Artikal> artikli = query.getResultList();
                            
                            Artikal artikal ;
                            
                            if(!artikli.isEmpty()){
                                    artikal = artikli.get(0);                        
                                    em.getTransaction().begin();
                                    artikal.setCena((double)cena);
                                    em.persist(artikal);
                                    em.getTransaction().commit();
                                    
                                    
                                    
                                   
                                 try{   
                                    ObjectMessage objectMessage = context.createObjectMessage();
                                    objectMessage.setObject(artikal);
                                    objectMessage.setIntProperty("Radnja", 4);
                                    
                                   
                                    producer.send(topic_prijem, objectMessage);
                                    
                                    
                                    
                                    System.out.println("Poslat je artikal: " + artikal.getNaziv() + ", nova cena: " + artikal.getCena() );
                                 } catch (JMSException ex) { 
                                    Logger.getLogger(Radnik.class.getName()).log(Level.SEVERE, null, ex);
                                } 
                            }else{
                                System.out.println("Takav artikal ne postoji u bazi");
                            }
                           break;
                case "0": exit(0);
                default :   System.out.println("Pogresan unos!!! ");
                            break;          
            
            }
        
        
        }
    
    }
}
