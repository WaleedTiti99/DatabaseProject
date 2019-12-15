/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Waleed
 */
@Entity
@Table(name = "BOOKS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Books.findAll", query = "SELECT b FROM Books b"),
    @NamedQuery(name = "Books.findByTitle", query = "SELECT b FROM Books b WHERE b.title = :title"),
    @NamedQuery(name = "Books.findByGenre", query = "SELECT b FROM Books b WHERE b.genre = :genre"),
    @NamedQuery(name = "Books.findBySection", query = "SELECT b FROM Books b WHERE b.section = :section"),
    @NamedQuery(name = "Books.findByDescreption", query = "SELECT b FROM Books b WHERE b.descreption = :descreption"),
    @NamedQuery(name = "Books.findByBorrowPeriod", query = "SELECT b FROM Books b WHERE b.borrowPeriod = :borrowPeriod"),
    @NamedQuery(name = "Books.findByPosition", query = "SELECT b FROM Books b WHERE b.position = :position"),
    @NamedQuery(name = "Books.findByPublishedYear", query = "SELECT b FROM Books b WHERE b.publishedYear = :publishedYear"),
    @NamedQuery(name = "Books.findByIsbn", query = "SELECT b FROM Books b WHERE b.isbn = :isbn")})
public class Books implements Serializable {

    @Column(name = "NUM_TIME_B")
    private BigInteger numTimeB;
    @Column(name = "BOOK_PIC")
    private String bookPic;
    @OneToMany(mappedBy = "isBn3")
    private Collection<Member1> member1Collection;
    @OneToMany(mappedBy = "isbn")
    private Collection<Member1> member1Collection1;
    @OneToMany(mappedBy = "isbn2")
    private Collection<Member1> member1Collection2;

    private static final long serialVersionUID = 1L;
    @Column(name = "TITLE")
    private String title;
    @Column(name = "GENRE")
    private String genre;
    @Column(name = "SECTION")
    private String section;
    @Column(name = "DESCREPTION")
    private String descreption;
    @Column(name = "BORROW_PERIOD")
    private BigInteger borrowPeriod;
    @Column(name = "POSITION")
    private String position;
    @Column(name = "PUBLISHED_YEAR")
    private BigInteger publishedYear;
    @Id
    @Basic(optional = false)
    @Column(name = "ISBN")
    private Long isbn;
    @JoinColumn(name = "MEMBERIDB", referencedColumnName = "MEMBERID")
    @ManyToOne(optional = false)
    private Member1 memberidb;

    public Books() {
    }

    public Books(Long isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getDescreption() {
        return descreption;
    }

    public void setDescreption(String descreption) {
        this.descreption = descreption;
    }

    public BigInteger getBorrowPeriod() {
        return borrowPeriod;
    }

    public void setBorrowPeriod(BigInteger borrowPeriod) {
        this.borrowPeriod = borrowPeriod;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public BigInteger getPublishedYear() {
        return publishedYear;
    }

    public void setPublishedYear(BigInteger publishedYear) {
        this.publishedYear = publishedYear;
    }

    public Long getIsbn() {
        return isbn;
    }

    public void setIsbn(Long isbn) {
        this.isbn = isbn;
    }

    public Member1 getMemberidb() {
        return memberidb;
    }

    public void setMemberidb(Member1 memberidb) {
        this.memberidb = memberidb;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (isbn != null ? isbn.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Books)) {
            return false;
        }
        Books other = (Books) object;
        if ((this.isbn == null && other.isbn != null) || (this.isbn != null && !this.isbn.equals(other.isbn))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "library.Books[ isbn=" + isbn + " ]";
    }

    public BigInteger getNumTimeB() {
        return numTimeB;
    }

    public void setNumTimeB(BigInteger numTimeB) {
        this.numTimeB = numTimeB;
    }

    public String getBookPic() {
        return bookPic;
    }

    public void setBookPic(String bookPic) {
        this.bookPic = bookPic;
    }

    @XmlTransient
    public Collection<Member1> getMember1Collection() {
        return member1Collection;
    }

    public void setMember1Collection(Collection<Member1> member1Collection) {
        this.member1Collection = member1Collection;
    }

    @XmlTransient
    public Collection<Member1> getMember1Collection1() {
        return member1Collection1;
    }

    public void setMember1Collection1(Collection<Member1> member1Collection1) {
        this.member1Collection1 = member1Collection1;
    }

    @XmlTransient
    public Collection<Member1> getMember1Collection2() {
        return member1Collection2;
    }

    public void setMember1Collection2(Collection<Member1> member1Collection2) {
        this.member1Collection2 = member1Collection2;
    }
    
}
