/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Admin;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author ANANDA AYU KARTIKA S
 */
@Entity
@Table(name = "divisi")
@NamedQueries({
    @NamedQuery(name = "Divisi_1.findAll", query = "SELECT d FROM Divisi_1 d"),
    @NamedQuery(name = "Divisi_1.findByIdDivisi", query = "SELECT d FROM Divisi_1 d WHERE d.idDivisi = :idDivisi"),
    @NamedQuery(name = "Divisi_1.findByNamaDivisi", query = "SELECT d FROM Divisi_1 d WHERE d.namaDivisi = :namaDivisi"),
    @NamedQuery(name = "Divisi_1.findByLokasiDivisi", query = "SELECT d FROM Divisi_1 d WHERE d.lokasiDivisi = :lokasiDivisi")})
public class Divisi_1 implements Serializable {

    static void addItem(String string) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @OneToMany(mappedBy = "idDivisi")
    private Collection<Karyawan_1> karyawan1Collection;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_divisi")
    private Integer idDivisi;
    @Column(name = "nama_divisi")
    private String namaDivisi;
    @Column(name = "lokasi_divisi")
    private String lokasiDivisi;

    public Divisi_1() {
    }

    public Divisi_1(Integer idDivisi) {
        this.idDivisi = idDivisi;
    }

    public Integer getIdDivisi() {
        return idDivisi;
    }

    public void setIdDivisi(Integer idDivisi) {
        this.idDivisi = idDivisi;
    }

    public String getNamaDivisi() {
        return namaDivisi;
    }

    public void setNamaDivisi(String namaDivisi) {
        this.namaDivisi = namaDivisi;
    }

    public String getLokasiDivisi() {
        return lokasiDivisi;
    }

    public void setLokasiDivisi(String lokasiDivisi) {
        this.lokasiDivisi = lokasiDivisi;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDivisi != null ? idDivisi.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Divisi_1)) {
            return false;
        }
        Divisi_1 other = (Divisi_1) object;
        if ((this.idDivisi == null && other.idDivisi != null) || (this.idDivisi != null && !this.idDivisi.equals(other.idDivisi))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return namaDivisi ;
    }

    public Collection<Karyawan_1> getKaryawan1Collection() {
        return karyawan1Collection;
    }

    public void setKaryawan1Collection(Collection<Karyawan_1> karyawan1Collection) {
        this.karyawan1Collection = karyawan1Collection;
    }

    Object getDivisi() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
