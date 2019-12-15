package library;

import java.math.BigInteger;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import library.Person1;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-12-01T16:48:32")
@StaticMetamodel(Employee.class)
public class Employee_ { 

    public static volatile SingularAttribute<Employee, String> empId;
    public static volatile SingularAttribute<Employee, BigInteger> endHour;
    public static volatile SingularAttribute<Employee, Person1> person1;
    public static volatile SingularAttribute<Employee, String> empType;
    public static volatile SingularAttribute<Employee, BigInteger> startHour;
    public static volatile SingularAttribute<Employee, BigInteger> noHours;
    public static volatile SingularAttribute<Employee, BigInteger> slryEmp;

}