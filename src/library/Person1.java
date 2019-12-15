/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Waleed
 */
@Entity
@Table(name = "PERSON1")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Person1.findAll", query = "SELECT p FROM Person1 p"),
    @NamedQuery(name = "Person1.findByFName", query = "SELECT p FROM Person1 p WHERE p.fName = :fName"),
    @NamedQuery(name = "Person1.findByLName", query = "SELECT p FROM Person1 p WHERE p.lName = :lName"),
    @NamedQuery(name = "Person1.findByGender", query = "SELECT p FROM Person1 p WHERE p.gender = :gender"),
    @NamedQuery(name = "Person1.findByBirthday", query = "SELECT p FROM Person1 p WHERE p.birthday = :birthday"),
    @NamedQuery(name = "Person1.findByCity", query = "SELECT p FROM Person1 p WHERE p.city = :city"),
    @NamedQuery(name = "Person1.findByStreet", query = "SELECT p FROM Person1 p WHERE p.street = :street"),
    @NamedQuery(name = "Person1.findByBuilding", query = "SELECT p FROM Person1 p WHERE p.building = :building"),
    @NamedQuery(name = "Person1.findByEmail", query = "SELECT p FROM Person1 p WHERE p.email = :email"),
    @NamedQuery(name = "Person1.findByPasword", query = "SELECT p FROM Person1 p WHERE p.pasword = :pasword"),
    @NamedQuery(name = "Person1.findByUsername", query = "SELECT p FROM Person1 p WHERE p.username = :username"),
    @NamedQuery(name = "Person1.findById", query = "SELECT p FROM Person1 p WHERE p.id = :id"),
    @NamedQuery(name = "Person1.findByPhonenumber", query = "SELECT p FROM Person1 p WHERE p.phonenumber = :phonenumber")})
public class Person1 implements Serializable {

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "person1")
    private Employee employee;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "person1")
    private Member1 member1;

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "F_NAME")
    private String fName;
    @Basic(optional = false)
    @Column(name = "L_NAME")
    private String lName;
    @Column(name = "GENDER")
    private Character gender;
    @Column(name = "BIRTHDAY")
    @Temporal(TemporalType.TIMESTAMP)
    private Date birthday;
    @Column(name = "CITY")
    private String city;
    @Column(name = "STREET")
    private String street;
    @Column(name = "BUILDING")
    private String building;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "PASWORD")
    private String pasword;
    @Column(name = "USERNAME")
    private String username;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private String id;
    @Column(name = "PHONENUMBER")
    private BigInteger phonenumber;
    @OneToMany(mappedBy = "fId")
    private Collection<Feedback> feedbackCollection;

    public Person1() {
    }

    public Person1(String id) {
        this.id = id;
    }

    public Person1(String id, String fName, String lName) {
        this.id = id;
        this.fName = fName;
        this.lName = lName;
    }

    public String getFName() {
        return fName;
    }

    public void setFName(String fName) {
        this.fName = fName;
    }

    public String getLName() {
        return lName;
    }

    public void setLName(String lName) {
        this.lName = lName;
    }

    public Character getGender() {
        return gender;
    }

    public void setGender(Character gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasword() {
        return pasword;
    }

    public void setPasword(String pasword) {
        this.pasword = pasword;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigInteger getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(BigInteger phonenumber) {
        this.phonenumber = phonenumber;
    }

    @XmlTransient
    public Collection<Feedback> getFeedbackCollection() {
        return feedbackCollection;
    }

    public void setFeedbackCollection(Collection<Feedback> feedbackCollection) {
        this.feedbackCollection = feedbackCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Person1)) {
            return false;
        }
        Person1 other = (Person1) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "library.Person1[ id=" + id + " ]";
    }

    public Member1 getMember1() {
        return member1;
    }

    public void setMember1(Member1 member1) {
        this.member1 = member1;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
    
}
