/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package Admin;

import java.text.SimpleDateFormat;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ANANDA AYU KARTIKA S
 */
public class PeriodeGajii extends javax.swing.JDialog {

    /**
     * Creates new form PeriodeGajii
     */
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

    public PeriodeGajii(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        connect();
        loadComboPeriode();
//        loadTable();
//       

    }

    private void loadComboPeriode() {
    EntityManager em = emf.createEntityManager();
    try {
        jComboBox1.removeAllItems();

        List<Object[]> list = em.createNativeQuery(
            "SELECT id_periode_gaji, bulan, tahun FROM periode_gaji ORDER BY id_periode_gaji DESC"
        ).getResultList();

        for (Object[] row : list) {
            int id = (int) row[0];
            String bulan = row[1].toString();
            String tahun = row[2].toString();

            // Tambah ke combobox
            jComboBox1.addItem(id + " - " + bulan + " " + tahun);
        }

    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Gagal load periode: " + e.getMessage());
    } finally {
        em.close();
    }
}


    
//private void loadTable() {
//    EntityManager em = emf.createEntityManager();
//    try {
//        TypedQuery<PeriodeGaji> query = em.createQuery("SELECT p FROM PeriodeGaji p", PeriodeGaji.class);
//        List<PeriodeGaji> hasil = query.getResultList();
//
//        DefaultTableModel model = new DefaultTableModel();
//        model.addColumn("ID Periode");
//        model.addColumn("Bulan");
//        model.addColumn("Tahun");
//        model.addColumn("Tanggal Gajian");
//
//        for (PeriodeGaji p : hasil) {
//            model.addRow(new Object[]{
//                p.getIdPeriodeGaji(),
//                p.getBulan(),     // sesuaikan dengan nama field entity kamu
//                p.getTahun(),
//                p.getTanggalGajian()
//            });
//        }
//
//        tabelGaji.setModel(model);
//
//    } catch (Exception e) {
//        JOptionPane.showMessageDialog(this, "Gagal load tabel: " + e.getMessage());
//        e.printStackTrace();
//    } finally {
//        em.close();
//    }
//}
    
   private void loadTable(int idPeriode) {
    EntityManager em = emf.createEntityManager();

    try {
        String sql = "SELECT "
                + "g.id_gaji, "
                + "k.id_karyawan, "
                + "k.nama_karyawan, "
                + "p.bulan, "
                + "p.tahun, "
                + "g.gaji_pokok, "
                + "g.total_hadir, "
                + "g.total_cuti, "
                + "g.total_alpha, "
                + "g.tunjangan, "
                + "g.lembur, "
                + "g.bonus, "
                + "g.total_potongan, "
                + "g.total_gaji_bersih "
                + "FROM list_gaji g "
                + "JOIN karyawan k ON g.id_karyawan = k.id_karyawan "
                + "JOIN periode_gaji p ON g.id_periode_gaji = p.id_periode_gaji "
                + "WHERE g.id_periode_gaji = ?";   // <-- FIX DI SINI

        List<Object[]> list = em.createNativeQuery(sql)
                .setParameter(1, idPeriode)        // <-- FIX DI SINI
                .getResultList();

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID Gaji");
        model.addColumn("ID Karyawan");
        model.addColumn("Nama Karyawan");
        model.addColumn("Bulan");
        model.addColumn("Tahun");
        model.addColumn("Gaji Pokok");
        model.addColumn("Total Hadir");
        model.addColumn("Total Cuti");
        model.addColumn("Total Alpha");
        model.addColumn("Tunjangan");
        model.addColumn("Lembur");
        model.addColumn("Bonus");
        model.addColumn("Total Potongan");
        model.addColumn("Total Gaji Bersih");

        for (Object[] row : list) {
            model.addRow(row);
        }

        tabelGaji.setModel(model);

    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
    } finally {
        em.close();
    }
}



    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelGaji = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Periode Gaji");

        jComboBox1.setBackground(new java.awt.Color(255, 255, 255));
        jComboBox1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jComboBox1.setForeground(new java.awt.Color(0, 0, 0));
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        tabelGaji.setBackground(new java.awt.Color(255, 255, 255));
        tabelGaji.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        tabelGaji.setForeground(new java.awt.Color(0, 0, 0));
        tabelGaji.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tabelGaji);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jLabel1)
                        .addGap(38, 38, 38)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 549, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        if (jComboBox1.getSelectedItem() == null) return;

    String selected = jComboBox1.getSelectedItem().toString();

    int idPeriode = Integer.parseInt(selected.split(" - ")[0]);

    loadTable(idPeriode);
    }//GEN-LAST:event_jComboBox1ActionPerformed

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
//            java.util.logging.Logger.getLogger(PeriodeGajii.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(PeriodeGajii.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(PeriodeGajii.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(PeriodeGajii.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//        //</editor-fold>
//
//        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                PeriodeGajii dialog = new PeriodeGajii(new javax.swing.JFrame(), true);
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
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabelGaji;
    // End of variables declaration//GEN-END:variables
}
