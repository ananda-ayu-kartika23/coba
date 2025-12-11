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
@Table(name = "jabatan")
@NamedQueries({
    @NamedQuery(name = "Jabatan_1.findAll", query = "SELECT j FROM Jabatan_1 j"),
    @NamedQuery(name = "Jabatan_1.findByIdJabatan", query = "SELECT j FROM Jabatan_1 j WHERE j.idJabatan = :idJabatan"),
    @NamedQuery(name = "Jabatan_1.findByNamaJabatan", query = "SELECT j FROM Jabatan_1 j WHERE j.namaJabatan = :namaJabatan"),
    @NamedQuery(name = "Jabatan_1.findByLevelJabatan", query = "SELECT j FROM Jabatan_1 j WHERE j.levelJabatan = :levelJabatan")})
public class Jabatan_1 implements Serializable {

    static void addItem(String string) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @OneToMany(mappedBy = "idJabatan")
    private Collection<Karyawan_1> karyawan1Collection;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_jabatan")
    private Integer idJabatan;
    @Column(name = "nama_jabatan")
    private String namaJabatan;
    @Column(name = "level_jabatan")
    private String levelJabatan;

    public Jabatan_1() {
    }

    public Jabatan_1(Integer idJabatan) {
        this.idJabatan = idJabatan;
    }

    public Integer getIdJabatan() {
        return idJabatan;
    }

    public void setIdJabatan(Integer idJabatan) {
        this.idJabatan = idJabatan;
    }

    public String getNamaJabatan() {
        return namaJabatan;
    }

    public void setNamaJabatan(String namaJabatan) {
        this.namaJabatan = namaJabatan;
    }

    public String getLevelJabatan() {
        return levelJabatan;
    }

    public void setLevelJabatan(String levelJabatan) {
        this.levelJabatan = levelJabatan;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idJabatan != null ? idJabatan.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Jabatan_1)) {
            return false;
        }
        Jabatan_1 other = (Jabatan_1) object;
        if ((this.idJabatan == null && other.idJabatan != null) || (this.idJabatan != null && !this.idJabatan.equals(other.idJabatan))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return namaJabatan;
    }

    public Collection<Karyawan_1> getKaryawan1Collection() {
        return karyawan1Collection;
    }

    public void setKaryawan1Collection(Collection<Karyawan_1> karyawan1Collection) {
        this.karyawan1Collection = karyawan1Collection;
    }
    
}
