package entiteti;

import entiteti.Prodavnica;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-01-26T12:00:21")
@StaticMetamodel(Menadzer.class)
public class Menadzer_ { 

    public static volatile SingularAttribute<Menadzer, String> ime;
    public static volatile SingularAttribute<Menadzer, String> prezime;
    public static volatile SingularAttribute<Menadzer, Prodavnica> idProdavnica;
    public static volatile SingularAttribute<Menadzer, Integer> idMenadzer;

}