package library;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import library.Books;
import library.Person1;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-12-01T16:48:32")
@StaticMetamodel(Feedback.class)
public class Feedback_ { 

    public static volatile SingularAttribute<Feedback, String> column1;
    public static volatile SingularAttribute<Feedback, Person1> fId;
    public static volatile SingularAttribute<Feedback, BigDecimal> fbn;
    public static volatile SingularAttribute<Feedback, String> type;
    public static volatile SingularAttribute<Feedback, Books> fIsbn;

}