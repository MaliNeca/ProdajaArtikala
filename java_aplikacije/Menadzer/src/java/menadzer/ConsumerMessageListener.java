/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menadzer;

import entiteti.Artikal;
import entiteti.NaStanju;
import entiteti.Prodavnica;
import entiteti.Rezervacija;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Nemanja
 */
public class ConsumerMessageListener implements MessageListener {
        private final String consumerName;
        private final EntityManager em;
        private final JMSProducer producer;
        private final JMSContext context;
        private final int id;
        private final  Topic topic;
	public ConsumerMessageListener(String consumerName, EntityManager em, JMSProducer producer, JMSContext context, int id, Topic topic) {
		this.consumerName = consumerName;
                this.em = em;
                this.producer = producer;
                this.context = context;
                this.id = id;
                this.topic = topic;
	}
        
        @Override
	public void onMessage(Message message) {
            ObjectMessage objectMessage = (ObjectMessage) message;
            int idProdavnice;
            try{  
               int izbor  = objectMessage.getIntProperty("Radnja");

                switch(izbor){
                    case 1:  //proveri stanje artikla
                            System.out.println("Provera stanja za artikal: " + objectMessage.getObject() + ", menadzer id: " + objectMessage.getStringProperty("idM") );
                            String stanje;
                            Random rand = new Random();
                            int result = rand.nextInt(100);
                            if(result<70) {
                                stanje = "Zapakovan";
                            }
                            else {
                                stanje = "Otpakovan";
                            }

                            TextMessage odgovor = this.context.createTextMessage();
                            idProdavnice = (int) objectMessage.getDoubleProperty("Prodavac");
                            odgovor.setText(stanje);
                            odgovor.setStringProperty("idP", "" + idProdavnice);
                            this.producer.send(this.topic, odgovor);
                            System.out.println("Artikal: " + objectMessage.getObject() + ", je u stanju: " + stanje);

                            break;

                    case 2: //izvrsi rezervaciju
                            Rezervacija rezervacija =  (Rezervacija) objectMessage.getObject();

                            Prodavnica prodavnica = em.find(Prodavnica.class, id);
                            odgovor = this.context.createTextMessage();
                            
                            if(prodavnica != null) {
                                em.getTransaction().begin();
                                rezervacija.setFkidProdavnica(prodavnica);
                                em.persist(rezervacija);
                                em.getTransaction().commit();
                                odgovor.setText("Uspesno rezervisano");
                            }else{
                                odgovor.setText("Doslo je do greske!!!");
                            }
                            
                            idProdavnice = (int) objectMessage.getDoubleProperty("Prodavac");
                            odgovor.setStringProperty("idP", "" + idProdavnice);
                            this.producer.send(topic, odgovor);
                            System.out.println("Rezervacija za: " + rezervacija.getIme() );
                            break; 
                            
                    case 3: //primi robu
                            Artikal noviArtikal = (Artikal) objectMessage.getObject();
                            Query query = em.createNativeQuery("SELECT * FROM Artikal WHERE naziv ='" + noviArtikal.getNaziv() +"'",Artikal.class);
                            List<Artikal> artikli = query.getResultList();
                            Artikal artikal ;
                            NaStanju na_stanju;


                            if(!artikli.isEmpty() ){
                                artikal = artikli.get(0);

                                query = em.createNativeQuery("SELECT * FROM na_stanju as n WHERE n.idProdavnica = '" + Menadzer.id +"' AND n.idArtikal = '" + artikal.getIdArtikal() + "'", NaStanju.class);
                                List<NaStanju> nastanju = query.getResultList(); 

                                if(!nastanju.isEmpty()){
                                    em.getTransaction().begin();
                                    na_stanju = nastanju.get(0);
                                    na_stanju.setKolicina(na_stanju.getKolicina() + objectMessage.getIntProperty("Kolicina"));
                                    
                                    
                                    em.persist(na_stanju);
                                    em.getTransaction().commit();
                                }else{
                                    em.getTransaction().begin();
                                    na_stanju = new NaStanju(Menadzer.id, artikal.getIdArtikal());
                                    na_stanju.setKolicina(objectMessage.getIntProperty("Kolicina"));
                                    em.persist(na_stanju);
                                    em.getTransaction().commit();
                                }

                                 System.out.println("Primljen je artikal: " + noviArtikal.getNaziv() + ", kolicina: " + objectMessage.getIntProperty("Kolicina") +", u prodavnici " + em.find(Prodavnica.class, Menadzer.id).getNaziv() + ", novo stanje je: " + na_stanju.getKolicina() );

                            }else{
                                System.out.println("Artikal sa datim nazivom ne postoji u bazi");
                            }

                            break; 
                    case 4: //promeni cenu
                            noviArtikal = (Artikal) objectMessage.getObject();
                            query = em.createNativeQuery("SELECT * FROM Artikal WHERE naziv ='" + noviArtikal.getNaziv() +"'",Artikal.class);
                            artikli = query.getResultList();
                            
                            if(!artikli.isEmpty() ){
                                artikal = artikli.get(0);
                                em.getTransaction().begin();
                                artikal.setCena(noviArtikal.getCena());
                                em.persist(artikal);
                                em.getTransaction().commit();


                            System.out.println("Promenjena  je cena za artikal: " + noviArtikal.getNaziv() + ", cena: " + noviArtikal.getCena() );

                            }else{
                                System.out.println("Artikal sa datim nazivom ne postoji u bazi");
                            }
                            break;
                                    
                                    
                                    
                                    } 
                } catch (JMSException ex) {
                Logger.getLogger(ConsumerMessageListener.class.getName()).log(Level.SEVERE, null, ex);
            }
                 
 	}

}


