package entiteti;

import entiteti.Artikal;
import entiteti.NaStanjuPK;
import entiteti.Prodavnica;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-01-26T12:00:19")
@StaticMetamodel(NaStanju.class)
public class NaStanju_ { 

    public static volatile SingularAttribute<NaStanju, NaStanjuPK> naStanjuPK;
    public static volatile SingularAttribute<NaStanju, Prodavnica> prodavnica;
    public static volatile SingularAttribute<NaStanju, Artikal> artikal;
    public static volatile SingularAttribute<NaStanju, Integer> kolicina;

}