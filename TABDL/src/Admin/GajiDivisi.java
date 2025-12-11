/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package Admin;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ANANDA AYU KARTIKA S
 */
public class GajiDivisi extends javax.swing.JDialog {

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

    public GajiDivisi(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        connect();
//        loadDataGaji();
        loadComboDivisi();
loadComboPeriode();
        

    }
    
    
private void loadComboDivisi() {
        EntityManager emLocal = emf.createEntityManager();
        try {
            List<Divisi_1> list = emLocal.createQuery("SELECT d FROM Divisi_1 d", Divisi_1.class)
                    .getResultList();

            cmbDivisi.removeAllItems();

            for (Divisi_1 d : list) {
                cmbDivisi.addItem(d);
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Gagal load divisi: " + ex.getMessage());
        } finally {
            emLocal.close();
        }
    }

    // ===================== LOAD PERIODE (STRING) =====================
    private void loadComboPeriode() {
        EntityManager emLocal = emf.createEntityManager();
        try {
            cmbPeriode.removeAllItems();

            List<Object[]> list = emLocal.createNativeQuery(
                    "SELECT id_periode_gaji, bulan, tahun FROM periode_gaji ORDER BY id_periode_gaji DESC"
            ).getResultList();

            for (Object[] row : list) {
                int id = (int) row[0];
                String bulan = row[1].toString();
                String tahun = row[2].toString();

                // contoh: 1 - Januari 2025
                cmbPeriode.addItem(id + " - " + bulan + " " + tahun);
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Gagal load periode: " + ex.getMessage());
        } finally {
            emLocal.close();
        }
    }



    private void filterGaji() {

    if (cmbDivisi.getSelectedItem() == null) {
        tabelGaji.setModel(new DefaultTableModel());
        return;
    }

    if (cmbPeriode.getSelectedItem() == null) {
        tabelGaji.setModel(new DefaultTableModel());
        return;
    }

    Divisi_1 div = (Divisi_1) cmbDivisi.getSelectedItem();

    String selectedPer = cmbPeriode.getSelectedItem().toString();
    int idPeriode;

    try {
        idPeriode = Integer.parseInt(selectedPer.split(" - ")[0].trim());
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(this, "Format periode tidak valid: " + ex.getMessage());
        return;
    }

    EntityManager emLocal = emf.createEntityManager();
    try {
        String jpql = "SELECT lg.idGaji, k.namaKaryawan, k.idDivisi.namaDivisi, "
                + "lg.totalGajiBersih, lg.gajiPokok, lg.totalHadir, lg.totalCuti, lg.totalAlpha "
                + "FROM ListGaji_1 lg "
                + "JOIN lg.idKaryawan k "
                + "WHERE k.idDivisi.idDivisi = :div "
                + "AND lg.idPeriodeGaji.idPeriodeGaji = :per";

        List<Object[]> hasil = emLocal.createQuery(jpql, Object[].class)
                .setParameter("div", div.getIdDivisi())
                .setParameter("per", idPeriode)
                .getResultList();

        DefaultTableModel model = new DefaultTableModel(
                new String[]{"ID Gaji", "Nama", "Divisi", "Total Bersih", "Gaji Pokok", "Hadir", "Cuti", "Alpha"},
                0
        );

        BigDecimal totalDivisi = BigDecimal.ZERO;

        for (Object[] o : hasil) {

            BigDecimal gaji = (BigDecimal) o[3]; // totalGajiBersih
            totalDivisi = totalDivisi.add(gaji);

            model.addRow(o);
        }

        // Tambahkan baris total paling bawah
        model.addRow(new Object[]{
            "", "", "TOTAL GAJI DIVISI",
            totalDivisi, "", "", "", ""
        });

        tabelGaji.setModel(model);

    } catch (Exception ex) {
        JOptionPane.showMessageDialog(this, "Gagal filter gaji: " + ex.getMessage());
    } finally {
        emLocal.close();
    }
}


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelGaji = new javax.swing.JTable();
        cmbDivisi = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        cmbPeriode = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));

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

        cmbDivisi.setBackground(new java.awt.Color(255, 255, 255));
        cmbDivisi.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        cmbDivisi.setForeground(new java.awt.Color(0, 0, 0));
        cmbDivisi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbDivisiActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Divisi");

        cmbPeriode.setBackground(new java.awt.Color(255, 255, 255));
        cmbPeriode.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        cmbPeriode.setForeground(new java.awt.Color(0, 0, 0));
        cmbPeriode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbPeriodeActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Periode");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbPeriode, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbDivisi, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 791, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cmbDivisi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbPeriode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 78, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
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

    private void cmbDivisiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbDivisiActionPerformed


    
    filterGaji();

    }//GEN-LAST:event_cmbDivisiActionPerformed

    private void cmbPeriodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbPeriodeActionPerformed

    filterGaji();

    }//GEN-LAST:event_cmbPeriodeActionPerformed

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
//            java.util.logging.Logger.getLogger(GajiDivisi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(GajiDivisi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(GajiDivisi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(GajiDivisi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                GajiDivisi dialog = new GajiDivisi(new javax.swing.JFrame(), true);
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
    private javax.swing.JComboBox<Divisi_1> cmbDivisi;
    private javax.swing.JComboBox<String> cmbPeriode;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabelGaji;
    // End of variables declaration//GEN-END:variables
}
