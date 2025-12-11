/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package Admin;

import java.awt.Component;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.math.BigDecimal;

/**
 *
 * @author ANANDA AYU KARTIKA S
 */
public class EditKaryawan extends javax.swing.JDialog {

    private EntityManagerFactory emf;
    private EntityManager em;

    public EditKaryawan(JFrame parent, boolean modal) {

        super(parent, modal);
        initComponents();
        connect();
        setLocationRelativeTo(null);
        isiComboBoxDivisi();
        isiComboBoxJabatan();
        isiComboBoxDivisi();
        isiComboBoxJabatan();  // opsional: boleh dipanggil setelah divisi dipilih
        setAdminOtomatis();

    }

    public void connect() {
        try {
            emf = Persistence.createEntityManagerFactory("TABDLPU");
            em = emf.createEntityManager();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Koneksi gagal: " + e.getMessage());
        }
    }

    private void selectDivisiByName(String namaDivisi) {
        for (int i = 0; i < cmbDivisi_1.getItemCount(); i++) {
            Divisi_1 d = cmbDivisi_1.getItemAt(i);
            if (d.getNamaDivisi().equalsIgnoreCase(namaDivisi)) {
                cmbDivisi_1.setSelectedIndex(i);
                break;
            }
        }
    }

    private void selectJabatanByName(String namaJabatan) {
        for (int i = 0; i < cmbJabatan_1.getItemCount(); i++) {
            Jabatan_1 j = cmbJabatan_1.getItemAt(i);
            if (j.getNamaJabatan().equalsIgnoreCase(namaJabatan)) {
                cmbJabatan_1.setSelectedIndex(i);
                break;
            }
        }
    }

    private void setGajiOtomatis() {
        try {
            Jabatan_1 jabatan = (Jabatan_1) cmbJabatan_1.getSelectedItem();
            if (jabatan == null) {
                txtGajiPokok.setText("");
                return;
            }

            int idJabatan = jabatan.getIdJabatan();
            int gaji = 0;

            if (idJabatan == 1) {
                gaji = 8000000;
            } else if (idJabatan >= 2 && idJabatan <= 5) {
                gaji = 6000000;
            } else if (idJabatan >= 6 && idJabatan <= 13) {
                gaji = 4000000;
            }

            txtGajiPokok.setText(String.valueOf(gaji));

        } catch (Exception e) {
            System.out.println("Error set gaji: " + e.getMessage());
        }
    }

    public EditKaryawan(java.awt.Frame parent, boolean modal,
            String idKaryawan,
            String nama,
            String jenisKelamin,
            String tanggalLahir,
            String alamat,
            String noHp,
            String tanggalMasuk,
            String statusKerja,
            String divisi,
            String jabatan,
            String supervisor,
            String gajiPokok) {

        super(parent, modal);
        initComponents();

        connect();
        isiComboBoxDivisi();
        isiComboBoxJabatan();
//    isiComboBoxSupervisor();
//    isiComboBoxGajiPokok();

        txtIDKaryawan.setText(idKaryawan);
        txtNama.setText(nama);
        cmbKelamin.setSelectedItem(jenisKelamin);
        txtTanggalLahir.setText(tanggalLahir);
        txtAlamat.setText(alamat);
        txtNoHp.setText(noHp);
        txtTanggalMasuk.setText(tanggalMasuk);
        txtStatusKerja.setText(statusKerja);

        // ComboBox
        selectDivisiByName(divisi);
        selectJabatanByName(jabatan);
        txtGajiPokok.setText(gajiPokok);

    }

    private void isiComboBoxDivisi() {

        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Divisi_1> query = em.createQuery("SELECT d FROM Divisi_1 d", Divisi_1.class);

            List<Divisi_1> hasil = query.getResultList();

            if (!hasil.isEmpty()) {
                for (Divisi_1 d : hasil) {
                    cmbDivisi_1.addItem(d);
                }
            }

            // Tidak memilih data apapun saat awal
            cmbDivisi_1.setSelectedIndex(-1);

        } finally {
            em.close();
        }
        isiComboBoxJabatan();
        setAdminOtomatis();
    }

    private void isiComboBoxJabatan() {
        EntityManager em = emf.createEntityManager();
        try {
            cmbJabatan_1.removeAllItems();
            TypedQuery<Jabatan_1> query;

            // Ambil divisi yang sedang dipilih
            Divisi_1 divisi = (Divisi_1) cmbDivisi_1.getSelectedItem();

            if (divisi == null) {
                // jika belum memilih divisi → kosongkan jabatan
                return;
            }

            int idDivisi = divisi.getIdDivisi();

            // FILTER BERDASARKAN ID DIVISI
            if (idDivisi == 1) {
                query = em.createQuery(
                        "SELECT j FROM Jabatan_1 j WHERE j.idJabatan = 1",
                        Jabatan_1.class
                );

            } else if (idDivisi == 2) {
                query = em.createQuery(
                        "SELECT j FROM Jabatan_1 j WHERE j.idJabatan IN (2, 3)",
                        Jabatan_1.class
                );

            } else if (idDivisi == 3) {
                query = em.createQuery(
                        "SELECT j FROM Jabatan_1 j WHERE j.idJabatan IN (4,5,6,7)",
                        Jabatan_1.class
                );

            } else if (idDivisi == 4) {
                query = em.createQuery(
                        "SELECT j FROM Jabatan_1 j WHERE j.idJabatan IN (8,9)",
                        Jabatan_1.class
                );

            } else if (idDivisi == 5) {
                query = em.createQuery(
                        "SELECT j FROM Jabatan_1 j WHERE j.idJabatan IN (12,13)",
                        Jabatan_1.class
                );

            } else if (idDivisi == 6) {
                query = em.createQuery(
                        "SELECT j FROM Jabatan_1 j WHERE j.idJabatan IN (10)",
                        Jabatan_1.class
                );

            } else if (idDivisi == 7) {
                query = em.createQuery(
                        "SELECT j FROM Jabatan_1 j WHERE j.idJabatan IN (11)",
                        Jabatan_1.class
                );

            } else {
                query = em.createQuery(
                        "SELECT j FROM Jabatan_1 j",
                        Jabatan_1.class
                );
            }

            // Masukkan hasil ke combobox jabatan
            List<Jabatan_1> list = query.getResultList();
            for (Jabatan_1 j : list) {
                cmbJabatan_1.addItem(j);
            }

        } finally {
            em.close();
        }
        setSupervisorOtomatis();
        setAdminOtomatis();

    }

    private void setSupervisorOtomatis() {
        try {
            Jabatan_1 jabatan = (Jabatan_1) cmbJabatan_1.getSelectedItem();
            if (jabatan == null) {
                txtSupervisor.setText("");
                return;
            }

            int idJabatan = jabatan.getIdJabatan();
            int supervisorId = 0;

            // Aturan supervisor berdasarkan ID jabatan
            if (idJabatan >= 6 && idJabatan <= 13) {
                supervisorId = 5;  // Supervisor Produksi (ID karyawan = 5)
            } else if (idJabatan >= 2 && idJabatan <= 5) {
                supervisorId = 1;  // Manager (ID karyawan = 1)
            } else if (idJabatan == 1) {
                txtSupervisor.setText("-");  // Manager tidak punya supervisor
                return;
            }

            // Ambil supervisor dari tabel Karyawan_1
            Karyawan_1 supervisor = em.find(Karyawan_1.class, supervisorId);

            if (supervisor != null) {
                txtSupervisor.setText(supervisor.getNamaKaryawan());
            } else {
                txtSupervisor.setText("Tidak ditemukan");
            }

        } catch (Exception e) {
            System.out.println("Error atur supervisor: " + e.getMessage());
        }
    }

    private void setAdminOtomatis() {
        try {
            Divisi_1 divisi = (Divisi_1) cmbDivisi_1.getSelectedItem();
            if (divisi == null) {
                txtAdmin.setText("");
                return;
            }

            int idDivisi = divisi.getIdDivisi();
            int idAdmin;

            // Aturan admin
            if (idDivisi == 1 || idDivisi == 2 || idDivisi == 4) {
                idAdmin = 1;
            } else if (idDivisi == 3) {
                idAdmin = 2;
            } else if (idDivisi == 5 || idDivisi == 6 || idDivisi == 7) {
                idAdmin = 2;
            } else {
                idAdmin = 0;
            }

            // Jika idAdmin = 0 (tidak ada admin)
            if (idAdmin == 0) {
                txtAdmin.setText("-");
                return;
            }

            // Ambil nama admin dari tabel Admin
            Admin admin = em.find(Admin.class, idAdmin);

            if (admin != null) {
                txtAdmin.setText(admin.getNamaAdmin()); // tampilkan nama
            } else {
                txtAdmin.setText("Tidak ditemukan");
            }

        } catch (Exception e) {
            System.out.println("Error set admin: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtIDKaryawan = new javax.swing.JTextField();
        txtNama = new javax.swing.JTextField();
        cmbKelamin = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtTanggalLahir = new javax.swing.JTextField();
        txtAlamat = new javax.swing.JTextField();
        txtNoHp = new javax.swing.JTextField();
        txtTanggalMasuk = new javax.swing.JTextField();
        txtStatusKerja = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        cmbDivisi_1 = new javax.swing.JComboBox<>();
        cmbJabatan_1 = new javax.swing.JComboBox<>();
        txtSupervisor = new javax.swing.JTextField();
        txtGajiPokok = new javax.swing.JTextField();
        txtAdmin = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));
        jPanel1.setForeground(new java.awt.Color(0, 0, 0));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("DATA KARYAWAN");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("ID Karyawan");

        txtIDKaryawan.setBackground(new java.awt.Color(255, 255, 255));
        txtIDKaryawan.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        txtIDKaryawan.setForeground(new java.awt.Color(0, 0, 0));
        txtIDKaryawan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIDKaryawanActionPerformed(evt);
            }
        });

        txtNama.setBackground(new java.awt.Color(255, 255, 255));
        txtNama.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        txtNama.setForeground(new java.awt.Color(0, 0, 0));
        txtNama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNamaActionPerformed(evt);
            }
        });

        cmbKelamin.setBackground(new java.awt.Color(255, 255, 255));
        cmbKelamin.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        cmbKelamin.setForeground(new java.awt.Color(0, 0, 0));
        cmbKelamin.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "P", "L" }));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Nama");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Jenis Kelamin");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Tempat Tanggal Lahir");

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Alamat");

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("No Hp");

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Tanggal Masuk");

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Status Kerja");

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Divisi");

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Jabatan");

        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Supervisor");

        txtTanggalLahir.setBackground(new java.awt.Color(255, 255, 255));
        txtTanggalLahir.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        txtTanggalLahir.setForeground(new java.awt.Color(0, 0, 0));

        txtAlamat.setBackground(new java.awt.Color(255, 255, 255));
        txtAlamat.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        txtAlamat.setForeground(new java.awt.Color(0, 0, 0));

        txtNoHp.setBackground(new java.awt.Color(255, 255, 255));
        txtNoHp.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        txtNoHp.setForeground(new java.awt.Color(0, 0, 0));
        txtNoHp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNoHpActionPerformed(evt);
            }
        });

        txtTanggalMasuk.setBackground(new java.awt.Color(255, 255, 255));
        txtTanggalMasuk.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        txtTanggalMasuk.setForeground(new java.awt.Color(0, 0, 0));

        txtStatusKerja.setBackground(new java.awt.Color(255, 255, 255));
        txtStatusKerja.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        txtStatusKerja.setForeground(new java.awt.Color(0, 0, 0));
        txtStatusKerja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtStatusKerjaActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Gaji Pokok");

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 0, 0));
        jButton1.setText("Edit");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        cmbDivisi_1.setBackground(new java.awt.Color(255, 255, 255));
        cmbDivisi_1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        cmbDivisi_1.setForeground(new java.awt.Color(0, 0, 0));
        cmbDivisi_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbDivisi_1ActionPerformed(evt);
            }
        });

        cmbJabatan_1.setBackground(new java.awt.Color(255, 255, 255));
        cmbJabatan_1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        cmbJabatan_1.setForeground(new java.awt.Color(0, 0, 0));
        cmbJabatan_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbJabatan_1ActionPerformed(evt);
            }
        });

        txtSupervisor.setBackground(new java.awt.Color(255, 255, 255));
        txtSupervisor.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        txtSupervisor.setForeground(new java.awt.Color(0, 0, 0));
        txtSupervisor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSupervisorActionPerformed(evt);
            }
        });

        txtGajiPokok.setBackground(new java.awt.Color(255, 255, 255));
        txtGajiPokok.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        txtGajiPokok.setForeground(new java.awt.Color(0, 0, 0));
        txtGajiPokok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGajiPokokActionPerformed(evt);
            }
        });

        txtAdmin.setBackground(new java.awt.Color(255, 255, 255));
        txtAdmin.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        txtAdmin.setForeground(new java.awt.Color(0, 0, 0));

        jLabel14.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Admin");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel5)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel7)
                                        .addComponent(jLabel6)))
                                .addGap(34, 34, 34)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                            .addGap(1, 1, 1)
                                            .addComponent(txtTanggalLahir, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(txtIDKaryawan, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtNama, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(cmbKelamin, javax.swing.GroupLayout.Alignment.LEADING, 0, 189, Short.MAX_VALUE)))
                                    .addComponent(txtAlamat, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtNoHp, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jButton1)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtGajiPokok, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cmbJabatan_1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtStatusKerja, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtTanggalMasuk, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addGap(122, 122, 122)
                                .addComponent(cmbDivisi_1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtSupervisor, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(0, 32, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(130, 130, 130))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtIDKaryawan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cmbKelamin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(txtTanggalLahir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(txtAlamat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel11)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNoHp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTanggalMasuk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addGap(18, 18, 18)
                        .addComponent(txtStatusKerja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbDivisi_1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbJabatan_1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtSupervisor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtGajiPokok, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13))
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)
                        .addGap(146, 146, 146))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 486, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNamaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNamaActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        // Validasi input
        if (txtIDKaryawan.getText().equals("")
                || txtNama.getText().equals("")
                || cmbKelamin.getSelectedItem() == null
                || txtTanggalLahir.getText().equals("")
                || txtAlamat.getText().equals("")
                || txtNoHp.getText().equals("")
                || txtTanggalMasuk.getText().equals("")
                || txtStatusKerja.getText().equals("")
                || cmbDivisi_1.getSelectedItem() == null
                || cmbJabatan_1.getSelectedItem() == null
                || txtSupervisor.getText().equals("")
                || txtAdmin.getText().equals("")
                || txtGajiPokok.getText().equals("")) {

            JOptionPane.showMessageDialog(this, "Isi Semua Data!");
            return;
        }

        try {

            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();

            // Cari data lama berdasarkan ID karyawan
            int idK = Integer.parseInt(txtIDKaryawan.getText());
            Karyawan_1 k = em.find(Karyawan_1.class, idK);

            if (k == null) {
                JOptionPane.showMessageDialog(this, "Data karyawan tidak ditemukan!");
                return;
            }

            // SET DATA BARU
            k.setNamaKaryawan(txtNama.getText());
            k.setJenisKelamin(cmbKelamin.getSelectedItem().toString().charAt(0));
            k.setTanggalLahir(java.sql.Date.valueOf(txtTanggalLahir.getText()));
            k.setAlamat(txtAlamat.getText());
            k.setNoHp(txtNoHp.getText());
            k.setTanggalMasuk(java.sql.Date.valueOf(txtTanggalMasuk.getText()));
            k.setStatusKerja(txtStatusKerja.getText());

// Gaji pokok (AMAN)
            String rawGaji = txtGajiPokok.getText().trim().replaceAll("[^0-9.]", "");
            if (rawGaji.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Gaji Pokok tidak boleh kosong!");
                return;
            }
            BigDecimal gaji = new BigDecimal(rawGaji);
            k.setGajiPokokk(gaji);

            // RELASI OBJEK
            Divisi_1 div = (Divisi_1) cmbDivisi_1.getSelectedItem();
            Jabatan_1 jab = (Jabatan_1) cmbJabatan_1.getSelectedItem();

            k.setIdDivisi(div);
            k.setIdJabatan(jab);

            // Supervisor
            int supId = Integer.parseInt(txtSupervisor.getText());
            Karyawan_1 supervisor = em.find(Karyawan_1.class, supId);
            k.setSupervisor(supervisor);

            // Admin
            int adminId = Integer.parseInt(txtAdmin.getText());
            Admin admin = em.find(Admin.class, adminId);
            k.setIdAdmin(admin);

            em.persist(k);
            em.getTransaction().commit();

            JOptionPane.showMessageDialog(this, "Data berhasil diperbarui!");
            dispose();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            e.printStackTrace();
        }


    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtSupervisorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSupervisorActionPerformed

    }//GEN-LAST:event_txtSupervisorActionPerformed

    private void cmbJabatan_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbJabatan_1ActionPerformed

        setSupervisorOtomatis();


    }//GEN-LAST:event_cmbJabatan_1ActionPerformed

    private void txtGajiPokokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGajiPokokActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGajiPokokActionPerformed

    private void txtIDKaryawanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIDKaryawanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIDKaryawanActionPerformed

    private void txtNoHpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNoHpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNoHpActionPerformed


    private void cmbDivisi_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbDivisi_1ActionPerformed
        Divisi_1 d = (Divisi_1) cmbDivisi_1.getSelectedItem();
        if (d != null) {
            isiComboBoxJabatan();
            setAdminOtomatis();

        }

    }//GEN-LAST:event_cmbDivisi_1ActionPerformed

    private void txtStatusKerjaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtStatusKerjaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtStatusKerjaActionPerformed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(EditKaryawan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(EditKaryawan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(EditKaryawan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(EditKaryawan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                EditKaryawan dialog = new EditKaryawan(new javax.swing.JFrame(), true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<Divisi_1> cmbDivisi_1;
    private javax.swing.JComboBox<Jabatan_1> cmbJabatan_1;
    private javax.swing.JComboBox<String> cmbKelamin;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtAdmin;
    private javax.swing.JTextField txtAlamat;
    private javax.swing.JTextField txtGajiPokok;
    private javax.swing.JTextField txtIDKaryawan;
    private javax.swing.JTextField txtNama;
    private javax.swing.JTextField txtNoHp;
    private javax.swing.JTextField txtStatusKerja;
    private javax.swing.JTextField txtSupervisor;
    private javax.swing.JTextField txtTanggalLahir;
    private javax.swing.JTextField txtTanggalMasuk;
    // End of variables declaration//GEN-END:variables

}
