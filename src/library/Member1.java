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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "MEMBER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Member1.findAll", query = "SELECT m FROM Member1 m"),
    @NamedQuery(name = "Member1.findByMemberid", query = "SELECT m FROM Member1 m WHERE m.memberid = :memberid"),
    @NamedQuery(name = "Member1.findByMemSd", query = "SELECT m FROM Member1 m WHERE m.memSd = :memSd"),
    @NamedQuery(name = "Member1.findByMemEd", query = "SELECT m FROM Member1 m WHERE m.memEd = :memEd"),
    @NamedQuery(name = "Member1.findByMemType", query = "SELECT m FROM Member1 m WHERE m.memType = :memType"),
    @NamedQuery(name = "Member1.findByNumBooksBorrowed", query = "SELECT m FROM Member1 m WHERE m.numBooksBorrowed = :numBooksBorrowed"),
    @NamedQuery(name = "Member1.findByBorrowDate", query = "SELECT m FROM Member1 m WHERE m.borrowDate = :borrowDate"),
    @NamedQuery(name = "Member1.findByReturnDate", query = "SELECT m FROM Member1 m WHERE m.returnDate = :returnDate"),
    @NamedQuery(name = "Member1.findByBorrowDate2", query = "SELECT m FROM Member1 m WHERE m.borrowDate2 = :borrowDate2"),
    @NamedQuery(name = "Member1.findByPenalty", query = "SELECT m FROM Member1 m WHERE m.penalty = :penalty"),
    @NamedQuery(name = "Member1.findByRdate2", query = "SELECT m FROM Member1 m WHERE m.rdate2 = :rdate2"),
    @NamedQuery(name = "Member1.findByBdate3", query = "SELECT m FROM Member1 m WHERE m.bdate3 = :bdate3"),
    @NamedQuery(name = "Member1.findByRetdate3", query = "SELECT m FROM Member1 m WHERE m.retdate3 = :retdate3")})
public class Member1 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "MEMBERID")
    private String memberid;
    @Column(name = "MEM_SD")
    @Temporal(TemporalType.TIMESTAMP)
    private Date memSd;
    @Column(name = "MEM_ED")
    @Temporal(TemporalType.TIMESTAMP)
    private Date memEd;
    @Column(name = "MEM_TYPE")
    private String memType;
    @Column(name = "NUM_BOOKS_BORROWED")
    private BigInteger numBooksBorrowed;
    @Column(name = "BORROW_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date borrowDate;
    @Column(name = "RETURN_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date returnDate;
    @Column(name = "BORROW_DATE2")
    @Temporal(TemporalType.TIMESTAMP)
    private Date borrowDate2;
    @Column(name = "PENALTY")
    private BigInteger penalty;
    @Column(name = "RDATE2")
    @Temporal(TemporalType.TIMESTAMP)
    private Date rdate2;
    @Column(name = "BDATE3")
    @Temporal(TemporalType.TIMESTAMP)
    private Date bdate3;
    @Column(name = "RETDATE3")
    @Temporal(TemporalType.TIMESTAMP)
    private Date retdate3;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "memberidb")
    private Collection<Books> booksCollection;
    @JoinColumn(name = "IS_BN3", referencedColumnName = "ISBN")
    @ManyToOne
    private Books isBn3;
    @JoinColumn(name = "I_S_B_N", referencedColumnName = "ISBN")
    @ManyToOne
    private Books isbn;
    @JoinColumn(name = "ISBN2", referencedColumnName = "ISBN")
    @ManyToOne
    private Books isbn2;
    @JoinColumn(name = "MEMBERID", referencedColumnName = "ID", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Person1 person1;

    public Member1() {
    }

    public Member1(String memberid) {
        this.memberid = memberid;
    }

    public String getMemberid() {
        return memberid;
    }

    public void setMemberid(String memberid) {
        this.memberid = memberid;
    }

    public Date getMemSd() {
        return memSd;
    }

    public void setMemSd(Date memSd) {
        this.memSd = memSd;
    }

    public Date getMemEd() {
        return memEd;
    }

    public void setMemEd(Date memEd) {
        this.memEd = memEd;
    }

    public String getMemType() {
        return memType;
    }

    public void setMemType(String memType) {
        this.memType = memType;
    }

    public BigInteger getNumBooksBorrowed() {
        return numBooksBorrowed;
    }

    public void setNumBooksBorrowed(BigInteger numBooksBorrowed) {
        this.numBooksBorrowed = numBooksBorrowed;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public Date getBorrowDate2() {
        return borrowDate2;
    }

    public void setBorrowDate2(Date borrowDate2) {
        this.borrowDate2 = borrowDate2;
    }

    public BigInteger getPenalty() {
        return penalty;
    }

    public void setPenalty(BigInteger penalty) {
        this.penalty = penalty;
    }

    public Date getRdate2() {
        return rdate2;
    }

    public void setRdate2(Date rdate2) {
        this.rdate2 = rdate2;
    }

    public Date getBdate3() {
        return bdate3;
    }

    public void setBdate3(Date bdate3) {
        this.bdate3 = bdate3;
    }

    public Date getRetdate3() {
        return retdate3;
    }

    public void setRetdate3(Date retdate3) {
        this.retdate3 = retdate3;
    }

    @XmlTransient
    public Collection<Books> getBooksCollection() {
        return booksCollection;
    }

    public void setBooksCollection(Collection<Books> booksCollection) {
        this.booksCollection = booksCollection;
    }

    public Books getIsBn3() {
        return isBn3;
    }

    public void setIsBn3(Books isBn3) {
        this.isBn3 = isBn3;
    }

    public Books getIsbn() {
        return isbn;
    }

    public void setIsbn(Books isbn) {
        this.isbn = isbn;
    }

    public Books getIsbn2() {
        return isbn2;
    }

    public void setIsbn2(Books isbn2) {
        this.isbn2 = isbn2;
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
        hash += (memberid != null ? memberid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Member1)) {
            return false;
        }
        Member1 other = (Member1) object;
        if ((this.memberid == null && other.memberid != null) || (this.memberid != null && !this.memberid.equals(other.memberid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "library.Member1[ memberid=" + memberid + " ]";
    }
    
}
