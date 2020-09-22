/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menadzer;


import static java.lang.System.exit;
import java.util.Scanner;
import javax.annotation.Resource;
import javax.jms.ConnectionFactory;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.jms.Topic;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Nemanja
 */

//int property  1. stanje artikla 
//2. izrvsi rezervaciju
//3. primi novu robu
//4. promeni cenu
public class Menadzer {
    
  
    @Resource(lookup = "Topic")
    static Topic topic;
    @Resource(lookup = "MyTopicPrijem")
    static Topic topic_prijem;
    @Resource(lookup = "jms/__defaultConnectionFactory")
    static ConnectionFactory connectionFactory;
    
    
    static int id = 4;
    
    
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MenadzerPU");
        EntityManager em = emf.createEntityManager();
        JMSContext context = connectionFactory.createContext();
        context.setClientID("Menadzer" + id);
        System.out.println("Id Menadzera: " + id);
        JMSConsumer consumer = context.createDurableConsumer(topic, "Menadzer" + id, "idM = '" + id +"'", false);
        JMSConsumer consumer1 = context.createConsumer(topic_prijem);
        JMSProducer producer = context.createProducer();
        
        consumer.setMessageListener(new ConsumerMessageListener("Consumer " + id, em, producer, context, id,topic));
        consumer1.setMessageListener(new ConsumerMessageListener("Consumer " + id, em, producer, context, id, topic_prijem));
        Scanner  scan = new Scanner(System.in);
        while(true){
          System.out.println("0. KRAJ");
          int unos = scan.nextInt();
          if(unos == 0) exit(0);
               
        }
    }
}