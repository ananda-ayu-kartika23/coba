/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Admin;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author ANANDA AYU KARTIKA S
 */
@Entity
@Table(name = "list_gaji")
@NamedQueries({
    @NamedQuery(name = "ListGaji_1.findAll", query = "SELECT l FROM ListGaji_1 l"),
    @NamedQuery(name = "ListGaji_1.findByIdGaji", query = "SELECT l FROM ListGaji_1 l WHERE l.idGaji = :idGaji"),
    @NamedQuery(name = "ListGaji_1.findByTunjangan", query = "SELECT l FROM ListGaji_1 l WHERE l.tunjangan = :tunjangan"),
    @NamedQuery(name = "ListGaji_1.findByTotalGajiBersih", query = "SELECT l FROM ListGaji_1 l WHERE l.totalGajiBersih = :totalGajiBersih"),
    @NamedQuery(name = "ListGaji_1.findByGajiPokok", query = "SELECT l FROM ListGaji_1 l WHERE l.gajiPokok = :gajiPokok"),
    @NamedQuery(name = "ListGaji_1.findByTotalHadir", query = "SELECT l FROM ListGaji_1 l WHERE l.totalHadir = :totalHadir"),
    @NamedQuery(name = "ListGaji_1.findByTotalCuti", query = "SELECT l FROM ListGaji_1 l WHERE l.totalCuti = :totalCuti"),
    @NamedQuery(name = "ListGaji_1.findByTotalAlpha", query = "SELECT l FROM ListGaji_1 l WHERE l.totalAlpha = :totalAlpha"),
    @NamedQuery(name = "ListGaji_1.findByTotalPotongan", query = "SELECT l FROM ListGaji_1 l WHERE l.totalPotongan = :totalPotongan"),
    @NamedQuery(name = "ListGaji_1.findByLembur", query = "SELECT l FROM ListGaji_1 l WHERE l.lembur = :lembur"),
    @NamedQuery(name = "ListGaji_1.findByBonus", query = "SELECT l FROM ListGaji_1 l WHERE l.bonus = :bonus")})
public class ListGaji_1 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_gaji")
    private Integer idGaji;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "tunjangan")
    private BigDecimal tunjangan;
    @Column(name = "total_gaji_bersih")
    private BigDecimal totalGajiBersih;
    @Column(name = "gaji_pokok")
    private BigDecimal gajiPokok;
    @Column(name = "total_hadir")
    private Integer totalHadir;
    @Column(name = "total_cuti")
    private Integer totalCuti;
    @Column(name = "total_alpha")
    private Integer totalAlpha;
    @Column(name = "total_potongan")
    private BigDecimal totalPotongan;
    @Column(name = "lembur")
    private BigDecimal lembur;
    @Column(name = "bonus")
    private BigDecimal bonus;
    @JoinColumn(name = "id_karyawan", referencedColumnName = "id_karyawan")
    @ManyToOne
    private Karyawan_1 idKaryawan;
    @JoinColumn(name = "id_periode_gaji", referencedColumnName = "id_periode_gaji")
    @ManyToOne
    private PeriodeGaji idPeriodeGaji;

    public ListGaji_1() {
    }

    public ListGaji_1(Integer idGaji) {
        this.idGaji = idGaji;
    }

    public Integer getIdGaji() {
        return idGaji;
    }

    public void setIdGaji(Integer idGaji) {
        this.idGaji = idGaji;
    }

    public BigDecimal getTunjangan() {
        return tunjangan;
    }

    public void setTunjangan(BigDecimal tunjangan) {
        this.tunjangan = tunjangan;
    }

    public BigDecimal getTotalGajiBersih() {
        return totalGajiBersih;
    }

    public void setTotalGajiBersih(BigDecimal totalGajiBersih) {
        this.totalGajiBersih = totalGajiBersih;
    }

    public BigDecimal getGajiPokok() {
        return gajiPokok;
    }

    public void setGajiPokok(BigDecimal gajiPokok) {
        this.gajiPokok = gajiPokok;
    }

    public Integer getTotalHadir() {
        return totalHadir;
    }

    public void setTotalHadir(Integer totalHadir) {
        this.totalHadir = totalHadir;
    }

    public Integer getTotalCuti() {
        return totalCuti;
    }

    public void setTotalCuti(Integer totalCuti) {
        this.totalCuti = totalCuti;
    }

    public Integer getTotalAlpha() {
        return totalAlpha;
    }

    public void setTotalAlpha(Integer totalAlpha) {
        this.totalAlpha = totalAlpha;
    }

    public BigDecimal getTotalPotongan() {
        return totalPotongan;
    }

    public void setTotalPotongan(BigDecimal totalPotongan) {
        this.totalPotongan = totalPotongan;
    }

    public BigDecimal getLembur() {
        return lembur;
    }

    public void setLembur(BigDecimal lembur) {
        this.lembur = lembur;
    }

    public BigDecimal getBonus() {
        return bonus;
    }

    public void setBonus(BigDecimal bonus) {
        this.bonus = bonus;
    }

    public Karyawan_1 getIdKaryawan() {
        return idKaryawan;
    }

    public void setIdKaryawan(Karyawan_1 idKaryawan) {
        this.idKaryawan = idKaryawan;
    }

    public PeriodeGaji getIdPeriodeGaji() {
        return idPeriodeGaji;
    }

    public void setIdPeriodeGaji(PeriodeGaji idPeriodeGaji) {
        this.idPeriodeGaji = idPeriodeGaji;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idGaji != null ? idGaji.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ListGaji_1)) {
            return false;
        }
        ListGaji_1 other = (ListGaji_1) object;
        if ((this.idGaji == null && other.idGaji != null) || (this.idGaji != null && !this.idGaji.equals(other.idGaji))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Admin.ListGaji_1[ idGaji=" + idGaji + " ]";
    }
    
}
