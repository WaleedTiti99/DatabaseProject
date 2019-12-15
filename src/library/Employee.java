/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Waleed
 */
@Entity
@Table(name = "EMPLOYEE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Employee.findAll", query = "SELECT e FROM Employee e"),
    @NamedQuery(name = "Employee.findByEmpId", query = "SELECT e FROM Employee e WHERE e.empId = :empId"),
    @NamedQuery(name = "Employee.findByEmpType", query = "SELECT e FROM Employee e WHERE e.empType = :empType"),
    @NamedQuery(name = "Employee.findByStartHour", query = "SELECT e FROM Employee e WHERE e.startHour = :startHour"),
    @NamedQuery(name = "Employee.findByEndHour", query = "SELECT e FROM Employee e WHERE e.endHour = :endHour"),
    @NamedQuery(name = "Employee.findByNoHours", query = "SELECT e FROM Employee e WHERE e.noHours = :noHours"),
    @NamedQuery(name = "Employee.findBySlryEmp", query = "SELECT e FROM Employee e WHERE e.slryEmp = :slryEmp")})
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "EMP_ID")
    private String empId;
    @Column(name = "EMP_TYPE")
    private String empType;
    @Column(name = "START_HOUR")
    private BigInteger startHour;
    @Column(name = "END_HOUR")
    private BigInteger endHour;
    @Column(name = "NO_HOURS")
    private BigInteger noHours;
    @Column(name = "SLRY_EMP")
    private BigInteger slryEmp;
    @JoinColumn(name = "EMP_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Person1 person1;

    public Employee() {
    }

    public Employee(String empId) {
        this.empId = empId;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getEmpType() {
        return empType;
    }

    public void setEmpType(String empType) {
        this.empType = empType;
    }

    public BigInteger getStartHour() {
        return startHour;
    }

    public void setStartHour(BigInteger startHour) {
        this.startHour = startHour;
    }

    public BigInteger getEndHour() {
        return endHour;
    }

    public void setEndHour(BigInteger endHour) {
        this.endHour = endHour;
    }

    public BigInteger getNoHours() {
        return noHours;
    }

    public void setNoHours(BigInteger noHours) {
        this.noHours = noHours;
    }

    public BigInteger getSlryEmp() {
        return slryEmp;
    }

    public void setSlryEmp(BigInteger slryEmp) {
        this.slryEmp = slryEmp;
    }

    public Person1 getPerson1() {
        return person1;
    }

    public void setPerson1(Person1 person1) {
        this.person1 = person1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (empId != null ? empId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Employee)) {
            return false;
        }
        Employee other = (Employee) object;
        if ((this.empId == null && other.empId != null) || (this.empId != null && !this.empId.equals(other.empId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "library.Employee[ empId=" + empId + " ]";
    }
    
}
