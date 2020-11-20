/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author JUJU
 */
@Entity
@Table(name = "BIO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bio.findAll", query = "SELECT b FROM Bio b")
    , @NamedQuery(name = "Bio.findById", query = "SELECT b FROM Bio b WHERE b.id = :id")
    , @NamedQuery(name = "Bio.findByName", query = "SELECT b FROM Bio b WHERE b.name = :name")
    , @NamedQuery(name = "Bio.findByAge", query = "SELECT b FROM Bio b WHERE b.age = :age")
    , @NamedQuery(name = "Bio.findBySchoolyear", query = "SELECT b FROM Bio b WHERE b.schoolyear = :schoolyear")
    , @NamedQuery(name = "Bio.findByMajor", query = "SELECT b FROM Bio b WHERE b.major = :major")
    , @NamedQuery(name = "Bio.findByNameAndAge", query = "SELECT b FROM Bio b WHERE b.name = :name AND b.age = :age")
    , @NamedQuery(name = "Bio.findBySchoolyearAdvanced", query = "SELECT b FROM Bio b WHERE b.schoolyear = :schoolyear")
    , @NamedQuery(name = "Bio.findByMinor", query = "SELECT b FROM Bio b WHERE b.minor = :minor")})


public class Bio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "NAME")
    private String name;
    @Basic(optional = false)
    @Column(name = "AGE")
    private int age;
    @Basic(optional = false)
    @Column(name = "SCHOOLYEAR")
    private String schoolyear;
    @Basic(optional = false)
    @Column(name = "MAJOR")
    private String major;
    @Column(name = "MINOR")
    private String minor;

    public Bio() {
    }

    public Bio(Integer id) {
        this.id = id;
    }

    public Bio(Integer id, String name, int age, String schoolyear, String major, String minor) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.schoolyear = schoolyear;
        this.major = major;
        this.minor = minor;
       
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSchoolyear() {
        return schoolyear;
    }

    public void setSchoolyear(String schoolyear) {
        this.schoolyear = schoolyear;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getMinor() {
        return minor;
    }

    public void setMinor(String minor) {
        this.minor = minor;
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
        if (!(object instanceof Bio)) {
            return false;
        }
        Bio other = (Bio) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Bio[ id=" + id + " ]";
    }
    
}
