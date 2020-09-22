/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prodavac;

import entiteti.Artikal;
import entiteti.Menadzer;
import entiteti.NaStanju;
import entiteti.Prodavnica;
import entiteti.Promet;
import entiteti.Rezervacija;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.LockModeType;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 *
 * @author Nemanja
 */
public class Upiti {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProdavacPU");
    EntityManager em = emf.createEntityManager();
    
    
        public  void pretrazi_tip(String s){
            System.out.println("\nArtikli po tipu " + s);
            Query query = em.createNativeQuery("SELECT * FROM Artikal WHERE tip ='" + s +"'",Artikal.class);
            List<Artikal> artikli = query.getResultList();
             System.out.println("Naziv | Tip | Cena");
             artikli.forEach((artikal) -> {
                 System.out.println(artikal.getNaziv()+ " " + artikal.getTip() + " " + artikal.getCena());
        });

        }
        public  void pretrazi_naziv(String s){
            System.out.println("\nArtikli po nazivu " + s);
            Query query = em.createNativeQuery("SELECT * FROM Artikal WHERE naziv ='" + s +"'",Artikal.class);
            List<Artikal> artikli = query.getResultList();
            System.out.println("Naziv | Tip | Cena");
            artikli.forEach((artikal) -> {
                System.out.println(artikal.getNaziv()+ " " + artikal.getTip() + " " + artikal.getCena());
        });
        }

        public  boolean  proveri_stanje(String naziv, int kolicina, int idProdavnice){
            Prodavnica prodavnica = em.find(Prodavnica.class, idProdavnice);
            Query query = em.createNativeQuery("SELECT * FROM na_stanju as n WHERE n.idProdavnica = '" + prodavnica.getIdProdavnica() +"' AND n.idArtikal = (SELECT a.idArtikal FROM artikal a WHERE a.Naziv = '" + naziv + "' )",NaStanju.class);
            List<NaStanju> nastanju = query.getResultList();
            
            if(!nastanju.isEmpty()){
                NaStanju artikal = nastanju.get(0);
                query = em.createNativeQuery("SELECT SUM(r.kolicina) FROM Rezervacija as r WHERE r.fk_idArtikal = '" + artikal.getArtikal().getIdArtikal() + "' AND r.fk_idProdavnica = '" + idProdavnice +"'" );
                
                Number rezervisano =  (Number) query.getSingleResult();
                if(rezervisano == null) rezervisano = 0;
                if(artikal.getKolicina() >= (kolicina + rezervisano.intValue())){
                    System.out.println("Na stanju imamo: " + artikal.getArtikal().getNaziv() + ", kolicina: " + artikal.getKolicina() +", rezervisano " + rezervisano.intValue() + ", u prodavnici: " + artikal.getProdavnica().getNaziv());
                    return true;
                
                }else{
                    System.out.println("Nemamo dovoljnu kolicinu na stanju!");
                    return false;
                }
                
            }else {
                System.out.println("Trenutno nemamo na stanju: " + naziv + ", u prodavnici: " + prodavnica.getNaziv());
                return false;
            }
            
           
        }
        
        
        public void prodaj(String naziv, int kolicina, int idProdavnice){
            
            em.getTransaction().begin();
           
            Query query = em.createNativeQuery("SELECT * FROM na_stanju as n WHERE n.idProdavnica = '" + idProdavnice +"' AND n.idArtikal = (SELECT a.idArtikal FROM artikal a WHERE a.Naziv = '" + naziv + "' )",NaStanju.class);
            NaStanju artikal = (NaStanju) query.getSingleResult();
            em.lock(artikal, LockModeType.PESSIMISTIC_WRITE);
            artikal.setKolicina(artikal.getKolicina() - kolicina);
            em.persist(artikal);
            System.out.println(" \n Prodat je : " + artikal.getArtikal().getNaziv() + ", u prodavnici: " + artikal.getProdavnica().getNaziv() + ", novo stanje je: " + artikal.getKolicina() );
            em.lock(artikal, LockModeType.NONE);
            Date today = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            String datum = formatter.format(today);
           
            
            
              Query query1 = em.createNativeQuery("SELECT * FROM promet as p WHERE p.datum = '" + datum +"' AND p.fk_prodavnica = '" + idProdavnice +"'",Promet.class);
              List<Promet> prometi = query1.getResultList();
              if(!prometi.isEmpty()){
                    Promet promet = prometi.get(0);
                    promet.setBrojProdatih(promet.getBrojProdatih() + kolicina);
                    promet.setIznos(promet.getIznos() + (kolicina * artikal.getArtikal().getCena()));
                    System.out.println("\nNovi dnevni promet za : " + promet.getFkProdavnica().getNaziv() + ",je: " + promet.getBrojProdatih() + " artikla, ukupan iznos: " + promet.getIznos() + " dinara , dana: " + promet.getDatum() );
              }else{
                   Promet promet = new Promet();
                   promet.setBrojProdatih(kolicina);
                   promet.setDatum(datum);
                   promet.setFkProdavnica(em.find(Prodavnica.class, idProdavnice));
                   promet.setIznos(kolicina * artikal.getArtikal().getCena());
                   em.persist(promet);
                   System.out.println("\nUpisan je promet prodavnici: " + promet.getFkProdavnica().getNaziv() + ", prodato je: " + promet.getBrojProdatih() + " artikla, u iznosu od: " + promet.getIznos() + " dinara , dana: " + promet.getDatum() );
             
              }
            
              
            
            em.getTransaction().commit();
            
        }
       
        
        public List<NaStanju> proveri_ostale_prodavnice(String artikal, int kolicina){
            Query query = em.createNativeQuery("SELECT * FROM na_stanju as n WHERE n.kolicina >= '" + kolicina + "' AND n.idArtikal = (SELECT a.idArtikal FROM artikal a WHERE a.Naziv = '" + artikal + "' )", NaStanju.class);
            List<NaStanju> nastanju = query.getResultList();
            return nastanju;
            
          
        }
        
        
        public  Rezervacija proveri_rezervaciju(String jmbg, int idProdavnice){
            
            Query query = em.createNativeQuery("SELECT * FROM Rezervacija as r WHERE r.jmbg = '" + jmbg +"' AND r.fk_idProdavnica = '" + idProdavnice +"'", Rezervacija.class);
            List<Rezervacija> rezervacija = query.getResultList();
            return rezervacija.isEmpty()?null:rezervacija.get(0);
            
        }

        
        
        public void skini_rezervaciju(Rezervacija rezervacija){
            em.getTransaction().begin();
            em.remove(rezervacija);
            em.getTransaction().commit();
        }
        
        public void istekle_rezervacije(){
            
            Date today = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy' 'HH:mm");
            String datum = formatter.format(today);
            
            
            Query query = em.createNativeQuery("SELECT * FROM Rezervacija as r WHERE r.Istek <= '" + datum + "'", Rezervacija.class);
            List<Rezervacija> rezervacije = query.getResultList();
            
            if(!rezervacije.isEmpty()){
                for(Rezervacija r : rezervacije){
                    em.getTransaction().begin();
                    em.remove(r);
                    em.getTransaction().commit();
                    System.out.println("Obrisana je rezervacija: " + r.getIme() + " " + r.getPrezime() + " " + r.getIstek());
                }
            }else{
                System.out.println("\n\nNe postoje istekle rezervacije");
            }
        }
        
        public Prodavnica nadji_prodavnicu(int idProdavnice){
            return em.find(Prodavnica.class, idProdavnice);
        }
        
        
        
        
        
        
        
        
}
    