/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Admin;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author ANANDA AYU KARTIKA S
 */
@Entity
@Table(name = "absensi")
@NamedQueries({
    @NamedQuery(name = "Absensi.findAll", query = "SELECT a FROM Absensi a"),
    @NamedQuery(name = "Absensi.findByIdAbsensi", query = "SELECT a FROM Absensi a WHERE a.idAbsensi = :idAbsensi"),
    @NamedQuery(name = "Absensi.findByTanggal", query = "SELECT a FROM Absensi a WHERE a.tanggal = :tanggal"),
    @NamedQuery(name = "Absensi.findByWaktuMasuk", query = "SELECT a FROM Absensi a WHERE a.waktuMasuk = :waktuMasuk"),
    @NamedQuery(name = "Absensi.findByWaktuKeluar", query = "SELECT a FROM Absensi a WHERE a.waktuKeluar = :waktuKeluar"),
    @NamedQuery(name = "Absensi.findByStatusKehadiran", query = "SELECT a FROM Absensi a WHERE a.statusKehadiran = :statusKehadiran"),
    @NamedQuery(name = "Absensi.findByTotalWaktuKerja", query = "SELECT a FROM Absensi a WHERE a.totalWaktuKerja = :totalWaktuKerja")})
public class Absensi implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_absensi")
    private Integer idAbsensi;
    @Column(name = "tanggal")
    @Temporal(TemporalType.DATE)
    private Date tanggal;
    @Column(name = "waktu_masuk")
    @Temporal(TemporalType.TIME)
    private Date waktuMasuk;
    @Column(name = "waktu_keluar")
    @Temporal(TemporalType.TIME)
    private Date waktuKeluar;
    @Column(name = "status_kehadiran")
    private String statusKehadiran;
    @Column(name = "total_waktu_kerja")
    @Temporal(TemporalType.TIME)
    private Date totalWaktuKerja;
    @JoinColumn(name = "id_karyawan", referencedColumnName = "id_karyawan")
    @ManyToOne
    private Karyawan_1 idKaryawan;

    public Absensi() {
    }

    public Absensi(Integer idAbsensi) {
        this.idAbsensi = idAbsensi;
    }

    public Integer getIdAbsensi() {
        return idAbsensi;
    }

    public void setIdAbsensi(Integer idAbsensi) {
        this.idAbsensi = idAbsensi;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public Date getWaktuMasuk() {
        return waktuMasuk;
    }

    public void setWaktuMasuk(Date waktuMasuk) {
        this.waktuMasuk = waktuMasuk;
    }

    public Date getWaktuKeluar() {
        return waktuKeluar;
    }

    public void setWaktuKeluar(Date waktuKeluar) {
        this.waktuKeluar = waktuKeluar;
    }

    public String getStatusKehadiran() {
        return statusKehadiran;
    }

    public void setStatusKehadiran(String statusKehadiran) {
        this.statusKehadiran = statusKehadiran;
    }

    public Date getTotalWaktuKerja() {
        return totalWaktuKerja;
    }

    public void setTotalWaktuKerja(Date totalWaktuKerja) {
        this.totalWaktuKerja = totalWaktuKerja;
    }

    public Karyawan_1 getIdKaryawan() {
        return idKaryawan;
    }

    public void setIdKaryawan(Karyawan_1 idKaryawan) {
        this.idKaryawan = idKaryawan;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAbsensi != null ? idAbsensi.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Absensi)) {
            return false;
        }
        Absensi other = (Absensi) object;
        if ((this.idAbsensi == null && other.idAbsensi != null) || (this.idAbsensi != null && !this.idAbsensi.equals(other.idAbsensi))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Admin.Absensi[ idAbsensi=" + idAbsensi + " ]";
    }
    
}
