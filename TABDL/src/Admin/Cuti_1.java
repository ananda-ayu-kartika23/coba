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
@Table(name = "cuti")
@NamedQueries({
    @NamedQuery(name = "Cuti_1.findAll", query = "SELECT c FROM Cuti_1 c"),
    @NamedQuery(name = "Cuti_1.findByIdCuti", query = "SELECT c FROM Cuti_1 c WHERE c.idCuti = :idCuti"),
    @NamedQuery(name = "Cuti_1.findByTanggalMulai", query = "SELECT c FROM Cuti_1 c WHERE c.tanggalMulai = :tanggalMulai"),
    @NamedQuery(name = "Cuti_1.findByTanggalSelesai", query = "SELECT c FROM Cuti_1 c WHERE c.tanggalSelesai = :tanggalSelesai"),
    @NamedQuery(name = "Cuti_1.findByAlasan", query = "SELECT c FROM Cuti_1 c WHERE c.alasan = :alasan"),
    @NamedQuery(name = "Cuti_1.findByStatusCuti", query = "SELECT c FROM Cuti_1 c WHERE c.statusCuti = :statusCuti")})
public class Cuti_1 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_cuti")
    private Integer idCuti;
    @Column(name = "tanggal_mulai")
    @Temporal(TemporalType.DATE)
    private Date tanggalMulai;
    @Column(name = "tanggal_selesai")
    @Temporal(TemporalType.DATE)
    private Date tanggalSelesai;
    @Column(name = "alasan")
    private String alasan;
    @Column(name = "status_cuti")
    private String statusCuti;
    @JoinColumn(name = "id_admin", referencedColumnName = "id_admin")
    @ManyToOne
    private Admin idAdmin;
    @JoinColumn(name = "supervisor", referencedColumnName = "id_karyawan")
    @ManyToOne
    private Karyawan_1 supervisor;
    @JoinColumn(name = "id_karyawan", referencedColumnName = "id_karyawan")
    @ManyToOne
    private Karyawan_1 idKaryawan;

    public Cuti_1() {
    }

    public Cuti_1(Integer idCuti) {
        this.idCuti = idCuti;
    }

    public Integer getIdCuti() {
        return idCuti;
    }

    public void setIdCuti(Integer idCuti) {
        this.idCuti = idCuti;
    }

    public Date getTanggalMulai() {
        return tanggalMulai;
    }

    public void setTanggalMulai(Date tanggalMulai) {
        this.tanggalMulai = tanggalMulai;
    }

    public Date getTanggalSelesai() {
        return tanggalSelesai;
    }

    public void setTanggalSelesai(Date tanggalSelesai) {
        this.tanggalSelesai = tanggalSelesai;
    }

    public String getAlasan() {
        return alasan;
    }

    public void setAlasan(String alasan) {
        this.alasan = alasan;
    }

    public String getStatusCuti() {
        return statusCuti;
    }

    public void setStatusCuti(String statusCuti) {
        this.statusCuti = statusCuti;
    }

    public Admin getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(Admin idAdmin) {
        this.idAdmin = idAdmin;
    }

    public Karyawan_1 getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(Karyawan_1 supervisor) {
        this.supervisor = supervisor;
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
        hash += (idCuti != null ? idCuti.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cuti_1)) {
            return false;
        }
        Cuti_1 other = (Cuti_1) object;
        if ((this.idCuti == null && other.idCuti != null) || (this.idCuti != null && !this.idCuti.equals(other.idCuti))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Admin.Cuti_1[ idCuti=" + idCuti + " ]";
    }

    

    
    
}
