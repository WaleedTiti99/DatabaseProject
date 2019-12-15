/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Waleed
 */
@Entity
@Table(name = "FEEDBACK")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Feedback.findAll", query = "SELECT f FROM Feedback f"),
    @NamedQuery(name = "Feedback.findByFbn", query = "SELECT f FROM Feedback f WHERE f.fbn = :fbn"),
    @NamedQuery(name = "Feedback.findByType", query = "SELECT f FROM Feedback f WHERE f.type = :type"),
    @NamedQuery(name = "Feedback.findByColumn1", query = "SELECT f FROM Feedback f WHERE f.column1 = :column1")})
public class Feedback implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "FBN")
    private BigDecimal fbn;
    @Column(name = "TYPE")
    private String type;
    @Column(name = "COLUMN1")
    private String column1;
    @JoinColumn(name = "F_ISBN", referencedColumnName = "ISBN")
    @ManyToOne
    private Books fIsbn;
    @JoinColumn(name = "F_ID", referencedColumnName = "ID")
    @ManyToOne
    private Person1 fId;

    public Feedback() {
    }

    public Feedback(BigDecimal fbn) {
        this.fbn = fbn;
    }

    public BigDecimal getFbn() {
        return fbn;
    }

    public void setFbn(BigDecimal fbn) {
        this.fbn = fbn;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getColumn1() {
        return column1;
    }

    public void setColumn1(String column1) {
        this.column1 = column1;
    }

    public Books getFIsbn() {
        return fIsbn;
    }

    public void setFIsbn(Books fIsbn) {
        this.fIsbn = fIsbn;
    }

    public Person1 getFId() {
        return fId;
    }

    public void setFId(Person1 fId) {
        this.fId = fId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (fbn != null ? fbn.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Feedback)) {
            return false;
        }
        Feedback other = (Feedback) object;
        if ((this.fbn == null && other.fbn != null) || (this.fbn != null && !this.fbn.equals(other.fbn))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "library.Feedback[ fbn=" + fbn + " ]";
    }
    
}
