/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package Admin;

import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ANANDA AYU KARTIKA S
 */
public class ListGaji extends javax.swing.JDialog {

    private EntityManagerFactory emf;
    private EntityManager em;

    public void connect() {
        try {
            emf = Persistence.createEntityManagerFactory("TABDLPU");
            em = emf.createEntityManager();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Koneksi gagal: " + e.getMessage());
        }
    }

    public ListGaji(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        connect();
        loadTable();

    }

    private void loadTable() {
        EntityManager em = emf.createEntityManager();

        try {
            String sql
                    = "SELECT "
                    + "g.id_gaji, "
                    + "k.id_karyawan, "
                    + "k.nama_karyawan, "
                    + "g.id_periode_gaji, "
                    + "k.gaji_pokokk AS gaji_pokok, "
                    + "COALESCE(a.total_hadir, 0) AS total_hadir, "
                    + "COALESCE(a.total_jam_kerja, 0) AS total_jam_kerja, "
                    + "COALESCE(c.total_cuti, 0) AS total_cuti, "
                    + "(28 - COALESCE(a.total_hadir, 0) - COALESCE(c.total_cuti, 0)) AS total_alpha, "
                    // TOTAL POTONGAN (fix)
                    + "("
                    + "(GREATEST(28 - COALESCE(a.total_hadir,0) - COALESCE(c.total_cuti,0),0) * 100000) "
                    + "+ (COALESCE(c.total_cuti,0) * 100000)"
                    + ") AS total_potongan, "
                    // GAJI BERSIH (sebelum bonus, tunjangan, lembur)
                    + "(k.gaji_pokokk "
                    + " - ((GREATEST(28 - COALESCE(a.total_hadir,0) - COALESCE(c.total_cuti,0),0) * 100000) "
                    + " + (COALESCE(c.total_cuti,0) * 100000))"
                    + ") AS gaji_bersih_, "
                    + "COALESCE(g.bonus, 0) AS bonus, "
                    + "COALESCE(g.tunjangan, 0) AS tunjangan, "
                    + "COALESCE(g.lembur, 0) AS lembur, "
                    // TOTAL GAJI (FIX – sudah kurangi total potongan)
                    + "("
                    + "  (k.gaji_pokokk "
                    + "   - ((GREATEST(28 - COALESCE(a.total_hadir,0) - COALESCE(c.total_cuti,0),0) * 100000) "
                    + "      + (COALESCE(c.total_cuti,0) * 100000))"
                    + "  ) "
                    + "  + COALESCE(g.bonus,0) "
                    + "  + COALESCE(g.tunjangan,0) "
                    + "  + COALESCE(g.lembur,0)"
                    + ") AS total_gaji "
                    + "FROM karyawan k "
                    + "LEFT JOIN ( "
                    + "    SELECT id_karyawan, COUNT(*) AS total_hadir, "
                    + "           SUM(EXTRACT(EPOCH FROM (waktu_keluar - waktu_masuk)) / 3600) AS total_jam_kerja "
                    + "    FROM absensi "
                    + "    WHERE tanggal BETWEEN '2025-01-06' AND '2026-01-06' "
                    + "      AND status_kehadiran = 'Hadir' "
                    + "    GROUP BY id_karyawan "
                    + ") a ON a.id_karyawan = k.id_karyawan "
                    + "LEFT JOIN ( "
                    + "    SELECT id_karyawan, "
                    + "           SUM((tanggal_selesai - tanggal_mulai) + 1) AS total_cuti "
                    + "    FROM cuti "
                    + "    WHERE status_cuti = 'Disetujui' "
                    + "      AND ( tanggal_mulai BETWEEN '2025-01-06' AND '2026-01-06' "
                    + "         OR tanggal_selesai BETWEEN '2025-01-06' AND '2026-01-06' ) "
                    + "    GROUP BY id_karyawan "
                    + ") c ON c.id_karyawan = k.id_karyawan "
                    + "LEFT JOIN list_gaji g ON g.id_karyawan = k.id_karyawan";

            List<Object[]> result = em.createNativeQuery(sql).getResultList();

            DefaultTableModel model = new DefaultTableModel();

            model.addColumn("ID Gaji");
            model.addColumn("ID Karyawan");
            model.addColumn("Nama");
            model.addColumn("ID Periode");
            model.addColumn("Gaji Pokok");
            model.addColumn("Total Hadir");
            model.addColumn("Total Jam");
            model.addColumn("Total Cuti");
            model.addColumn("Total Alpha");
            model.addColumn("Total Potongan");
            model.addColumn("Gaji Bersih Mingguan");
            model.addColumn("Bonus");
            model.addColumn("Tunjangan");
            model.addColumn("Lembur");
            model.addColumn("Total Gaji");

            for (Object[] row : result) {
                model.addRow(new Object[]{
                    row[0], // ID Gaji
                    row[1], // ID Karyawan
                    row[2], // Nama
                    row[3], // ID Periode
                    row[4], // Gaji Pokok
                    row[5], // Total Hadir
                    row[6], // Total Jam
                    row[7], // Total Cuti
                    row[8], // Total Alpha
                    row[9], // Total Potongan
                    row[10], // Gaji Bersih Mingguan
                    row[11], // Bonus
                    row[12], // Tunjangan
                    row[13], // Lembur
                    row[14] // Total Gaji
                });
            }

            jTableListGaji.setModel(model);

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    private void saveGaji(int idKaryawan, BigDecimal tunjangan, BigDecimal bonus, BigDecimal lembur, BigDecimal totalGaji) {

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        try {
            // Cek apakah data gaji untuk karyawan tersebut sudah ada
            String checkSql = "SELECT COUNT(*) FROM list_gaji WHERE id_karyawan = ?";

            Object countObj = em.createNativeQuery(checkSql)
                    .setParameter(1, idKaryawan)
                    .getSingleResult();

            // Convert COUNT(*) → integer aman
            int count = Integer.parseInt(countObj.toString());

            if (count > 0) {
                // -----------------------------
                // UPDATE (jika data sudah ada)
                // -----------------------------
                String updateSql = "UPDATE list_gaji "
                        + "SET bonus = ?, tunjangan = ?, lembur = ?, total_gaji_bersih = ? "
                        + "WHERE id_karyawan = ?";

                em.createNativeQuery(updateSql)
                        .setParameter(1, bonus)
                        .setParameter(2, tunjangan)
                        .setParameter(3, lembur)
                        .setParameter(4, totalGaji)
                        .setParameter(5, idKaryawan)
                        .executeUpdate();

            } else {
                // -----------------------------
                // INSERT (jika belum ada)
                // -----------------------------
                String insertSql = "INSERT INTO list_gaji "
                        + "(id_karyawan, id_periode_gaji, bonus, tunjangan, lembur, total_gaji_bersih) "
                        + "VALUES (?, ?, ?, ?, ?, ?)";

                em.createNativeQuery(insertSql)
                        .setParameter(1, idKaryawan)
                        .setParameter(2, 1) // sementara hardcode periode 1
                        .setParameter(3, bonus)
                        .setParameter(4, tunjangan)
                        .setParameter(5, lembur)
                        .setParameter(6, totalGaji)
                        .executeUpdate();
            }

            em.getTransaction().commit();
            JOptionPane.showMessageDialog(this, "Data gaji berhasil disimpan.");

        } catch (Exception e) {
            em.getTransaction().rollback();
            JOptionPane.showMessageDialog(this, "Gagal menyimpan gaji: " + e.getMessage());

        } finally {
            em.close();
        }
    }

    private void insertToDatabase(Object[] row) {
        try {
            em.getTransaction().begin();

            String sqlInsert = "INSERT INTO list_gaji ("
                    + "id_karyawan, "
                    + "id_periode_gaji, "
                    + "tunjangan, "
                    + "total_gaji_bersih, "
                    + "gaji_pokok, "
                    + "total_hadir, "
                    + "total_cuti, "
                    + "total_alpha, "
                    + "total_potongan, "
                    + "lembur, "
                    + "bonus"
                    + ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            em.createNativeQuery(sqlInsert)
                    .setParameter(1, row[1]) // id_karyawan
                    .setParameter(2, row[2]) // id_periode_gaji (sudah tampil di tabel)
                    .setParameter(3, row[12]) // tunjangan
                    .setParameter(4, row[14]) // total_gaji_bersih / total_gaji
                    .setParameter(5, row[4]) // gaji pokok
                    .setParameter(6, row[5]) // total hadir
                    .setParameter(7, row[7]) // total cuti
                    .setParameter(8, row[8]) // total alpha
                    .setParameter(9, row[9]) // total potongan
                    .setParameter(10, row[13]) // lembur
                    .setParameter(11, row[11]) // bonus
                    .executeUpdate();

            em.getTransaction().commit();

        } catch (Exception e) {
            em.getTransaction().rollback();
            JOptionPane.showMessageDialog(this,
                    "Gagal menyimpan data gaji: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableListGaji = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        btnSimpan = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(204, 204, 255));

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));

        jTableListGaji.setBackground(new java.awt.Color(255, 255, 255));
        jTableListGaji.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jTableListGaji.setForeground(new java.awt.Color(0, 0, 0));
        jTableListGaji.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTableListGaji);

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Pilih Periode");

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Tunjangan");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Bonus");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Lembur");

        jTextField1.setBackground(new java.awt.Color(255, 255, 255));
        jTextField1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jTextField1.setForeground(new java.awt.Color(0, 0, 0));
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jTextField2.setBackground(new java.awt.Color(255, 255, 255));
        jTextField2.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jTextField2.setForeground(new java.awt.Color(0, 0, 0));

        jTextField3.setBackground(new java.awt.Color(255, 255, 255));
        jTextField3.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jTextField3.setForeground(new java.awt.Color(0, 0, 0));

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 0, 0));
        jButton1.setText("Data Gaji Divisi");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btnSimpan.setBackground(new java.awt.Color(255, 255, 255));
        btnSimpan.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnSimpan.setForeground(new java.awt.Color(0, 0, 0));
        btnSimpan.setText("Simpan");
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(jLabel1)
                .addGap(142, 142, 142)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(82, 82, 82))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(110, 110, 110)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 521, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(208, 208, 208)
                        .addComponent(jLabel4)))
                .addContainerGap(21, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(85, 85, 85)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(87, 87, 87))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(btnSimpan))
                .addGap(47, 47, 47))
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

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed

        int row = jTableListGaji.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Pilih data di tabel terlebih dahulu!");
            return;
        }

        try {
            int idKaryawan = Integer.parseInt(jTableListGaji.getValueAt(row, 1).toString());

            BigDecimal tunjangan = new BigDecimal(jTextField1.getText());
            BigDecimal bonus = new BigDecimal(jTextField2.getText());
            BigDecimal lembur = new BigDecimal(jTextField3.getText());
            BigDecimal gajiBersih = new BigDecimal(jTableListGaji.getValueAt(row, 9).toString());

            BigDecimal totalGajiBaru = gajiBersih
                    .add(tunjangan)
                    .add(bonus)
                    .add(lembur);

            saveGaji(idKaryawan, tunjangan, bonus, lembur, totalGajiBaru);

            JOptionPane.showMessageDialog(this, "Data berhasil disimpan!");

            loadTable();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Input tidak valid: " + ex.getMessage());
        }


    }//GEN-LAST:event_btnSimpanActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        GajiDivisi fk = new GajiDivisi(new javax.swing.JFrame(), true);
        fk.setLocationRelativeTo(this);
        fk.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

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
//            java.util.logging.Logger.getLogger(ListGaji.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(ListGaji.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(ListGaji.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(ListGaji.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//
//        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                ListGaji dialog = new ListGaji(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnSimpan;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableListGaji;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables

}
