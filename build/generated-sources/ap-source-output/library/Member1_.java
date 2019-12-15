package library;

import java.math.BigInteger;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import library.Books;
import library.Person1;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-12-01T16:48:32")
@StaticMetamodel(Member1.class)
public class Member1_ { 

    public static volatile SingularAttribute<Member1, Books> isBn3;
    public static volatile SingularAttribute<Member1, Books> isbn2;
    public static volatile SingularAttribute<Member1, Date> rdate2;
    public static volatile SingularAttribute<Member1, BigInteger> penalty;
    public static volatile SingularAttribute<Member1, String> memType;
    public static volatile SingularAttribute<Member1, Books> isbn;
    public static volatile SingularAttribute<Member1, BigInteger> numBooksBorrowed;
    public static volatile SingularAttribute<Member1, Date> memSd;
    public static volatile SingularAttribute<Member1, Date> bdate3;
    public static volatile SingularAttribute<Member1, Date> borrowDate;
    public static volatile SingularAttribute<Member1, Date> returnDate;
    public static volatile SingularAttribute<Member1, Person1> person1;
    public static volatile CollectionAttribute<Member1, Books> booksCollection;
    public static volatile SingularAttribute<Member1, Date> retdate3;
    public static volatile SingularAttribute<Member1, Date> memEd;
    public static volatile SingularAttribute<Member1, Date> borrowDate2;
    public static volatile SingularAttribute<Member1, String> memberid;

}