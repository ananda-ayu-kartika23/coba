/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Admin;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author ANANDA AYU KARTIKA S
 */
@Entity
@Table(name = "karyawan")
@NamedQueries({
    @NamedQuery(name = "Karyawan_1.findAll", query = "SELECT k FROM Karyawan_1 k"),
    @NamedQuery(name = "Karyawan_1.findByIdKaryawan", query = "SELECT k FROM Karyawan_1 k WHERE k.idKaryawan = :idKaryawan"),
    @NamedQuery(name = "Karyawan_1.findByNamaKaryawan", query = "SELECT k FROM Karyawan_1 k WHERE k.namaKaryawan = :namaKaryawan"),
    @NamedQuery(name = "Karyawan_1.findByJenisKelamin", query = "SELECT k FROM Karyawan_1 k WHERE k.jenisKelamin = :jenisKelamin"),
    @NamedQuery(name = "Karyawan_1.findByTanggalLahir", query = "SELECT k FROM Karyawan_1 k WHERE k.tanggalLahir = :tanggalLahir"),
    @NamedQuery(name = "Karyawan_1.findByAlamat", query = "SELECT k FROM Karyawan_1 k WHERE k.alamat = :alamat"),
    @NamedQuery(name = "Karyawan_1.findByNoHp", query = "SELECT k FROM Karyawan_1 k WHERE k.noHp = :noHp"),
    @NamedQuery(name = "Karyawan_1.findByTanggalMasuk", query = "SELECT k FROM Karyawan_1 k WHERE k.tanggalMasuk = :tanggalMasuk"),
    @NamedQuery(name = "Karyawan_1.findByStatusKerja", query = "SELECT k FROM Karyawan_1 k WHERE k.statusKerja = :statusKerja"),
    @NamedQuery(name = "Karyawan_1.findByGajiPokokk", query = "SELECT k FROM Karyawan_1 k WHERE k.gajiPokokk = :gajiPokokk"),
    @NamedQuery(name = "Karyawan_1.findByUsername", query = "SELECT k FROM Karyawan_1 k WHERE k.username = :username"),
    @NamedQuery(name = "Karyawan_1.findByPassword", query = "SELECT k FROM Karyawan_1 k WHERE k.password = :password")})
public class Karyawan_1 implements Serializable {

    @OneToMany(mappedBy = "idKaryawan")
    private Collection<ListGaji_1> listGaji1Collection;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_karyawan")
    private Integer idKaryawan;
    @Column(name = "nama_karyawan")
    private String namaKaryawan;
    @Column(name = "jenis_kelamin")
    private Character jenisKelamin;
    @Column(name = "tanggal_lahir")
    @Temporal(TemporalType.DATE)
    private Date tanggalLahir;
    @Column(name = "alamat")
    private String alamat;
    @Column(name = "no_hp")
    private String noHp;
    @Column(name = "tanggal_masuk")
    @Temporal(TemporalType.DATE)
    private Date tanggalMasuk;
    @Column(name = "status_kerja")
    private String statusKerja;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "gaji_pokokk")
    private BigDecimal gajiPokokk;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @OneToMany(mappedBy = "supervisor")
    private Collection<Cuti_1> cuti1Collection;
    @OneToMany(mappedBy = "idKaryawan")
    private Collection<Cuti_1> cuti1Collection1;
    @JoinColumn(name = "id_admin", referencedColumnName = "id_admin")
    @ManyToOne
    private Admin idAdmin;
    @JoinColumn(name = "id_divisi", referencedColumnName = "id_divisi")
    @ManyToOne
    private Divisi_1 idDivisi;
    @JoinColumn(name = "id_jabatan", referencedColumnName = "id_jabatan")
    @ManyToOne
    private Jabatan_1 idJabatan;
    @OneToMany(mappedBy = "supervisor")
    private Collection<Karyawan_1> karyawanCollection;
    @JoinColumn(name = "supervisor", referencedColumnName = "id_karyawan")
    @ManyToOne
    private Karyawan_1 supervisor;
    @OneToMany(mappedBy = "idKaryawan")
    private Collection<Absensi> absensiCollection;

    public Karyawan_1() {
    }

    public Karyawan_1(Integer idKaryawan) {
        this.idKaryawan = idKaryawan;
    }

    public Integer getIdKaryawan() {
        return idKaryawan;
    }

    public void setIdKaryawan(Integer idKaryawan) {
        this.idKaryawan = idKaryawan;
    }

    public String getNamaKaryawan() {
        return namaKaryawan;
    }

    public void setNamaKaryawan(String namaKaryawan) {
        this.namaKaryawan = namaKaryawan;
    }

    public Character getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(Character jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public Date getTanggalLahir() {
        return tanggalLahir;
    }

    public void setTanggalLahir(Date tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNoHp() {
        return noHp;
    }

    public void setNoHp(String noHp) {
        this.noHp = noHp;
    }

    public Date getTanggalMasuk() {
        return tanggalMasuk;
    }

    public void setTanggalMasuk(Date tanggalMasuk) {
        this.tanggalMasuk = tanggalMasuk;
    }

    public String getStatusKerja() {
        return statusKerja;
    }

    public void setStatusKerja(String statusKerja) {
        this.statusKerja = statusKerja;
    }

    public BigDecimal getGajiPokokk() {
        return gajiPokokk;
    }

    public void setGajiPokokk(BigDecimal gajiPokokk) {
        this.gajiPokokk = gajiPokokk;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Collection<Cuti_1> getCuti1Collection() {
        return cuti1Collection;
    }

    public void setCuti1Collection(Collection<Cuti_1> cuti1Collection) {
        this.cuti1Collection = cuti1Collection;
    }

    public Collection<Cuti_1> getCuti1Collection1() {
        return cuti1Collection1;
    }

    public void setCuti1Collection1(Collection<Cuti_1> cuti1Collection1) {
        this.cuti1Collection1 = cuti1Collection1;
    }

    public Admin getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(Admin idAdmin) {
        this.idAdmin = idAdmin;
    }

    public Divisi_1 getIdDivisi() {
        return idDivisi;
    }

    public void setIdDivisi(Divisi_1 idDivisi) {
        this.idDivisi = idDivisi;
    }

    public Jabatan_1 getIdJabatan() {
        return idJabatan;
    }

    public void setIdJabatan(Jabatan_1 idJabatan) {
        this.idJabatan = idJabatan;
    }

    public Collection<Karyawan_1> getKaryawanCollection() {
        return karyawanCollection;
    }

    public void setKaryawanCollection(Collection<Karyawan_1> karyawanCollection) {
        this.karyawanCollection = karyawanCollection;
    }

    public Karyawan_1 getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(Karyawan_1 supervisor) {
        this.supervisor = supervisor;
    }

    public Collection<Absensi> getAbsensiCollection() {
        return absensiCollection;
    }

    public void setAbsensiCollection(Collection<Absensi> absensiCollection) {
        this.absensiCollection = absensiCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idKaryawan != null ? idKaryawan.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Karyawan_1)) {
            return false;
        }
        Karyawan_1 other = (Karyawan_1) object;
        if ((this.idKaryawan == null && other.idKaryawan != null) || (this.idKaryawan != null && !this.idKaryawan.equals(other.idKaryawan))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Admin.Karyawan_1[ idKaryawan=" + idKaryawan + " ]";
    }

    public Collection<ListGaji_1> getListGaji1Collection() {
        return listGaji1Collection;
    }

    public void setListGaji1Collection(Collection<ListGaji_1> listGaji1Collection) {
        this.listGaji1Collection = listGaji1Collection;
    }
    
}
