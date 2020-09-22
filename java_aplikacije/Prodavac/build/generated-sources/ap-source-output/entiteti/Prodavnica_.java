package entiteti;

import entiteti.Menadzer;
import entiteti.NaStanju;
import entiteti.Promet;
import entiteti.Rezervacija;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-01-26T12:00:19")
@StaticMetamodel(Prodavnica.class)
public class Prodavnica_ { 

    public static volatile ListAttribute<Prodavnica, Menadzer> menadzerList;
    public static volatile ListAttribute<Prodavnica, Rezervacija> rezervacijaList;
    public static volatile SingularAttribute<Prodavnica, String> naziv;
    public static volatile SingularAttribute<Prodavnica, Integer> idProdavnica;
    public static volatile SingularAttribute<Prodavnica, String> mesto;
    public static volatile ListAttribute<Prodavnica, Promet> prometList;
    public static volatile ListAttribute<Prodavnica, NaStanju> naStanjuList;

}