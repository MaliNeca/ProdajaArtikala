package entiteti;

import entiteti.Prodavnica;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-01-26T12:00:21")
@StaticMetamodel(Promet.class)
public class Promet_ { 

    public static volatile SingularAttribute<Promet, Integer> brojProdatih;
    public static volatile SingularAttribute<Promet, String> datum;
    public static volatile SingularAttribute<Promet, Double> iznos;
    public static volatile SingularAttribute<Promet, Integer> idPromet;
    public static volatile SingularAttribute<Promet, Prodavnica> fkProdavnica;

}