package library;

import java.math.BigInteger;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import library.Employee;
import library.Feedback;
import library.Member1;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-12-01T16:48:32")
@StaticMetamodel(Person1.class)
public class Person1_ { 

    public static volatile SingularAttribute<Person1, Date> birthday;
    public static volatile SingularAttribute<Person1, String> pasword;
    public static volatile SingularAttribute<Person1, String> lName;
    public static volatile SingularAttribute<Person1, Character> gender;
    public static volatile SingularAttribute<Person1, String> city;
    public static volatile CollectionAttribute<Person1, Feedback> feedbackCollection;
    public static volatile SingularAttribute<Person1, BigInteger> phonenumber;
    public static volatile SingularAttribute<Person1, Employee> employee;
    public static volatile SingularAttribute<Person1, String> building;
    public static volatile SingularAttribute<Person1, String> fName;
    public static volatile SingularAttribute<Person1, String> street;
    public static volatile SingularAttribute<Person1, Member1> member1;
    public static volatile SingularAttribute<Person1, String> id;
    public static volatile SingularAttribute<Person1, String> email;
    public static volatile SingularAttribute<Person1, String> username;

}