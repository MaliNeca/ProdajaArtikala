package entiteti;

import entiteti.Artikal;
import entiteti.Prodavnica;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-01-26T12:00:21")
@StaticMetamodel(Rezervacija.class)
public class Rezervacija_ { 

    public static volatile SingularAttribute<Rezervacija, String> ime;
    public static volatile SingularAttribute<Rezervacija, String> prezime;
    public static volatile SingularAttribute<Rezervacija, String> kontakt;
    public static volatile SingularAttribute<Rezervacija, String> mestoPodizanja;
    public static volatile SingularAttribute<Rezervacija, Integer> idRezervacija;
    public static volatile SingularAttribute<Rezervacija, String> jmbg;
    public static volatile SingularAttribute<Rezervacija, Integer> kolicina;
    public static volatile SingularAttribute<Rezervacija, Artikal> fkidArtikal;
    public static volatile SingularAttribute<Rezervacija, String> istek;
    public static volatile SingularAttribute<Rezervacija, Prodavnica> fkidProdavnica;

}