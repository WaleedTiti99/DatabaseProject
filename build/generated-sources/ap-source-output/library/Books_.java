package library;

import java.math.BigInteger;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import library.Member1;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-12-01T16:48:32")
@StaticMetamodel(Books.class)
public class Books_ { 

    public static volatile CollectionAttribute<Books, Member1> member1Collection2;
    public static volatile SingularAttribute<Books, String> descreption;
    public static volatile CollectionAttribute<Books, Member1> member1Collection1;
    public static volatile SingularAttribute<Books, BigInteger> numTimeB;
    public static volatile SingularAttribute<Books, Long> isbn;
    public static volatile SingularAttribute<Books, String> section;
    public static volatile SingularAttribute<Books, String> title;
    public static volatile SingularAttribute<Books, Member1> memberidb;
    public static volatile SingularAttribute<Books, String> genre;
    public static volatile SingularAttribute<Books, String> bookPic;
    public static volatile CollectionAttribute<Books, Member1> member1Collection;
    public static volatile SingularAttribute<Books, String> position;
    public static volatile SingularAttribute<Books, BigInteger> publishedYear;
    public static volatile SingularAttribute<Books, BigInteger> borrowPeriod;

}