/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package Admin;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;

/**
 *
 * @author ANANDA AYU KARTIKA S
 */
public class IzinCuti extends javax.swing.JDialog {

    private EntityManagerFactory emf;
    private EntityManager em;
    private Integer idKaryawanLogin;

    public void connect() {
        try {
            emf = Persistence.createEntityManagerFactory("TABDLPU");
            em = emf.createEntityManager();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Koneksi gagal: " + e.getMessage());
        }
    }

    public IzinCuti(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        connect();

    }

    public void setIdKaryawanLogin(int id) {
        this.idKaryawanLogin = id;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtAlasan = new javax.swing.JTextArea();
        txtTanggalSelesai = new javax.swing.JTextField();
        txtTanggalMulai = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        Alasan = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Tanggal Mulai");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Tanggal Selesai");

        txtAlasan.setBackground(new java.awt.Color(255, 255, 255));
        txtAlasan.setColumns(20);
        txtAlasan.setRows(5);
        jScrollPane1.setViewportView(txtAlasan);

        txtTanggalSelesai.setBackground(new java.awt.Color(255, 255, 255));

        txtTanggalMulai.setBackground(new java.awt.Color(255, 255, 255));

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 0, 0));
        jButton1.setText("Ajukan");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        Alasan.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        Alasan.setForeground(new java.awt.Color(0, 0, 0));
        Alasan.setText("Alasan");

        jButton2.setBackground(new java.awt.Color(255, 255, 255));
        jButton2.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(0, 0, 0));
        jButton2.setText("Pemberitahuan");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2)
                        .addGap(44, 44, 44)
                        .addComponent(jButton1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(Alasan, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtTanggalSelesai, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
                                .addComponent(txtTanggalMulai, javax.swing.GroupLayout.Alignment.LEADING)))))
                .addGap(28, 28, 28))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtTanggalMulai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtTanggalSelesai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Alasan))
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap(45, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {

            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }

            em.getTransaction().begin();

            // Ambil input form
            String tglMulaiStr = txtTanggalMulai.getText().trim();
            String tglSelesaiStr = txtTanggalSelesai.getText().trim();
            String alasan = txtAlasan.getText().trim();

            if (tglMulaiStr.isEmpty() || tglSelesaiStr.isEmpty() || alasan.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Semua field harus diisi!");
                em.getTransaction().rollback();
                return;
            }

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date tglMulai = sdf.parse(tglMulaiStr);
            Date tglSelesai = sdf.parse(tglSelesaiStr);

            // Generate ID_CUTI otomatis
            int newId = ((Number) em.createQuery(
                    "SELECT COALESCE(MAX(c.idCuti), 0) + 1 FROM Cuti_1 c"
            ).getSingleResult()).intValue();

            // Ambil data karyawan login
            Karyawan_1 kry = em.find(Karyawan_1.class, idKaryawanLogin);
            if (kry == null) {
                JOptionPane.showMessageDialog(this, "Data karyawan tidak ditemukan!");
                em.getTransaction().rollback();
                return;
            }

            int idDivisi = kry.getIdDivisi().getIdDivisi();  // pastikan ada getter
            int idKaryawan = kry.getIdKaryawan();

            // ===============================
            //      HITUNG ADMIN OTOMATIS
            // ===============================
            int idAdmin;
            if (idDivisi == 1 || idDivisi == 2 || idDivisi == 4) {
                idAdmin = 1;
            } else if (idDivisi == 3) {
                idAdmin = 2;
            } else if (idDivisi == 5 || idDivisi == 6 || idDivisi == 7) {
                idAdmin = 2;
            } else {
                idAdmin = 0;  // tidak ada admin
            }

            Admin adminObj = null;
            if (idAdmin > 0) {
                adminObj = em.find(Admin.class, idAdmin);
            }

            // ===============================
            //      HITUNG SUPERVISOR OTOMATIS
            // ===============================
            int idSupervisor;
            if (idKaryawan >= 2 && idKaryawan <= 5) {
                idSupervisor = 1;
            } else {
                idSupervisor = 5;
            }

            Karyawan_1 supervisorObj = em.find(Karyawan_1.class, idSupervisor);

            if (supervisorObj == null) {
                JOptionPane.showMessageDialog(this,
                        "Supervisor ID " + idSupervisor + " tidak ditemukan di tabel Karyawan!"
                );
                em.getTransaction().rollback();
                return;
            }

            // ===============================
            //   MEMBUAT OBJEK CUTI BARU
            // ===============================
            Cuti_1 c = new Cuti_1();
            c.setIdCuti(newId);
            c.setIdKaryawan(kry);
            c.setTanggalMulai(tglMulai);
            c.setTanggalSelesai(tglSelesai);
            c.setAlasan(alasan);
            c.setStatusCuti("Menunggu");

            // Set admin & supervisor ke entity
            c.setIdAdmin(adminObj);
            c.setSupervisor(supervisorObj);

            // SIMPAN
            em.persist(c);
            em.getTransaction().commit();

            JOptionPane.showMessageDialog(this, "Pengajuan cuti berhasil!");

        } catch (Exception e) {

            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }

            JOptionPane.showMessageDialog(this,
                    "Gagal mengajukan cuti: " + e.getMessage()
            );
            e.printStackTrace();
            dispose();
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        PemberitahuanCuti fk = new PemberitahuanCuti(new javax.swing.JFrame(), true);
        fk.setLocationRelativeTo(this);
        fk.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

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
//            java.util.logging.Logger.getLogger(IzinCuti.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(IzinCuti.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(IzinCuti.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(IzinCuti.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//
//        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                IzinCuti dialog = new IzinCuti(new javax.swing.JFrame(), true);
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
    private javax.swing.JLabel Alasan;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea txtAlasan;
    private javax.swing.JTextField txtTanggalMulai;
    private javax.swing.JTextField txtTanggalSelesai;
    // End of variables declaration//GEN-END:variables

}
