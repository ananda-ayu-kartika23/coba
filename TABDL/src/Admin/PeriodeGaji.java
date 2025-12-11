/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Admin;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author ANANDA AYU KARTIKA S
 */
@Entity
@Table(name = "periode_gaji")
@NamedQueries({
    @NamedQuery(name = "PeriodeGaji.findAll", query = "SELECT p FROM PeriodeGaji p"),
    @NamedQuery(name = "PeriodeGaji.findByIdPeriodeGaji", query = "SELECT p FROM PeriodeGaji p WHERE p.idPeriodeGaji = :idPeriodeGaji"),
    @NamedQuery(name = "PeriodeGaji.findByBulan", query = "SELECT p FROM PeriodeGaji p WHERE p.bulan = :bulan"),
    @NamedQuery(name = "PeriodeGaji.findByTahun", query = "SELECT p FROM PeriodeGaji p WHERE p.tahun = :tahun"),
    @NamedQuery(name = "PeriodeGaji.findByTanggalGajian", query = "SELECT p FROM PeriodeGaji p WHERE p.tanggalGajian = :tanggalGajian")})
public class PeriodeGaji implements Serializable {

    @OneToMany(mappedBy = "idPeriodeGaji")
    private Collection<ListGaji_1> listGaji1Collection;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_periode_gaji")
    private Integer idPeriodeGaji;
    @Column(name = "bulan")
    private String bulan;
    @Column(name = "tahun")
    private Integer tahun;
    @Column(name = "tanggal_gajian")
    @Temporal(TemporalType.DATE)
    private Date tanggalGajian;

    public PeriodeGaji() {
    }

    public PeriodeGaji(Integer idPeriodeGaji) {
        this.idPeriodeGaji = idPeriodeGaji;
    }

    public Integer getIdPeriodeGaji() {
        return idPeriodeGaji;
    }

    public void setIdPeriodeGaji(Integer idPeriodeGaji) {
        this.idPeriodeGaji = idPeriodeGaji;
    }

    public String getBulan() {
        return bulan;
    }

    public void setBulan(String bulan) {
        this.bulan = bulan;
    }

    public Integer getTahun() {
        return tahun;
    }

    public void setTahun(Integer tahun) {
        this.tahun = tahun;
    }

    public Date getTanggalGajian() {
        return tanggalGajian;
    }

    public void setTanggalGajian(Date tanggalGajian) {
        this.tanggalGajian = tanggalGajian;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPeriodeGaji != null ? idPeriodeGaji.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PeriodeGaji)) {
            return false;
        }
        PeriodeGaji other = (PeriodeGaji) object;
        if ((this.idPeriodeGaji == null && other.idPeriodeGaji != null) || (this.idPeriodeGaji != null && !this.idPeriodeGaji.equals(other.idPeriodeGaji))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Admin.PeriodeGaji[ idPeriodeGaji=" + idPeriodeGaji + " ]";
    }

    public Collection<ListGaji_1> getListGaji1Collection() {
        return listGaji1Collection;
    }

    public void setListGaji1Collection(Collection<ListGaji_1> listGaji1Collection) {
        this.listGaji1Collection = listGaji1Collection;
    }

    String getNamaPeriode() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
