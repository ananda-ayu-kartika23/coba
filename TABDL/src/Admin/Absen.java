/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package Admin;

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
public class Absen extends javax.swing.JDialog {

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

    public Absen(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        connect();
        loadTable();
        loadComboNama();

    }
    
    private void loadComboNama() {
    EntityManager em = emf.createEntityManager();
    try {
        List<Karyawan_1> list = em.createQuery("SELECT k FROM Karyawan_1 k", Karyawan_1.class).getResultList();

        cmbNama.removeAllItems();
        cmbNama.addItem("Semua Karyawan");

        for (Karyawan_1 k : list) {
            cmbNama.addItem(k.getNamaKaryawan());
        }

    } catch (Exception ex) {
        JOptionPane.showMessageDialog(this, "Gagal load nama: " + ex.getMessage());
    } finally {
        em.close();
    }
}

    
    private void filterByNama() {
    String selectedNama = (String) cmbNama.getSelectedItem();

    EntityManager em = emf.createEntityManager();

    try {
        String jpql;

        if (selectedNama == null || selectedNama.equals("Semua Karyawan")) {
            jpql = "SELECT a FROM Absensi a";
        } else {
            jpql = "SELECT a FROM Absensi a WHERE a.idKaryawan.namaKaryawan = :nama";
        }

        TypedQuery<Absensi> query = em.createQuery(jpql, Absensi.class);

        if (selectedNama != null && !selectedNama.equals("Semua Karyawan")) {
            query.setParameter("nama", selectedNama);
        }

        List<Absensi> hasil = query.getResultList();

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID Absensi");
        model.addColumn("Nama Karyawan");
        model.addColumn("Tanggal");
        model.addColumn("Waktu Masuk");
        model.addColumn("Waktu Keluar");
        model.addColumn("Status");
        model.addColumn("Total Waktu Kerja");

        for (Absensi a : hasil) {

            String namaKaryawan = "-";
            if (a.getIdKaryawan() != null) {
                namaKaryawan = a.getIdKaryawan().getNamaKaryawan();
            }

            String totalWaktuKerja = "-";
            if (a.getWaktuMasuk() != null && a.getWaktuKeluar() != null) {
                long selisih = a.getWaktuKeluar().getTime() - a.getWaktuMasuk().getTime();
                long menit = selisih / 60000;
                long jam = menit / 60;
                long sisaMenit = menit % 60;
                totalWaktuKerja = jam + " jam " + sisaMenit + " menit";
            }

            model.addRow(new Object[]{
                a.getIdAbsensi(),
                namaKaryawan,
                a.getTanggal(),
                a.getWaktuMasuk(),
                a.getWaktuKeluar(),
                a.getStatusKehadiran(),
                totalWaktuKerja
            });
        }

        tabelKaryawan.setModel(model);

    } catch (Exception ex) {
        JOptionPane.showMessageDialog(this, "Error filter: " + ex.getMessage());
    } finally {
        em.close();
    }
}



private void loadTable() {

    if (emf == null) {
        JOptionPane.showMessageDialog(this, 
                "Gagal load tabel: Koneksi database belum terbentuk");
        return;
    }

    EntityManager em = emf.createEntityManager();

    try {
        TypedQuery<Absensi> query = em.createQuery("SELECT a FROM Absensi a", Absensi.class);
        List<Absensi> hasil = query.getResultList();

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID Absensi");
        model.addColumn("Nama Karyawan");
        model.addColumn("Tanggal");
        model.addColumn("Waktu Masuk");
        model.addColumn("Waktu Keluar");
        model.addColumn("Status");
        model.addColumn("Total Waktu Kerja");

        for (Absensi a : hasil) {

            String namaKaryawan = "-";
            if (a.getIdKaryawan() != null && a.getIdKaryawan().getNamaKaryawan() != null) {
                namaKaryawan = a.getIdKaryawan().getNamaKaryawan();
            }

            String totalWaktuKerja = "-";
            if (a.getWaktuMasuk() != null && a.getWaktuKeluar() != null) {

                long selisih = a.getWaktuKeluar().getTime() - a.getWaktuMasuk().getTime();
                long menit = selisih / 60000;
                long jam = menit / 60;
                long sisaMenit = menit % 60;

                totalWaktuKerja = jam + " jam " + sisaMenit + " menit";
            }

            model.addRow(new Object[]{
                a.getIdAbsensi(),
                namaKaryawan,
                a.getTanggal(),
                a.getWaktuMasuk(),
                a.getWaktuKeluar(),
                a.getStatusKehadiran(),
                totalWaktuKerja
            });
        }

        tabelKaryawan.setModel(model);

    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, 
            "Gagal load tabel: " + e.getClass().getSimpleName() + " - " + e.getMessage());
    } finally {
        em.close();
    }
}




    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelKaryawan = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        cmbNama = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(204, 204, 255));

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));

        tabelKaryawan.setBackground(new java.awt.Color(255, 255, 255));
        tabelKaryawan.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        tabelKaryawan.setForeground(new java.awt.Color(0, 0, 0));
        tabelKaryawan.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tabelKaryawan);

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Nama Karyawan");

        cmbNama.setBackground(new java.awt.Color(255, 255, 255));
        cmbNama.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        cmbNama.setForeground(new java.awt.Color(0, 0, 0));
        cmbNama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbNamaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(cmbNama, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 614, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cmbNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 366, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmbNamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbNamaActionPerformed
        filterByNama();

//        loadTable(idPeriode);
    }//GEN-LAST:event_cmbNamaActionPerformed

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
//            java.util.logging.Logger.getLogger(Absen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(Absen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(Absen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(Absen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                Absen dialog = new Absen(new javax.swing.JFrame(), true);
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
    private javax.swing.JComboBox<String> cmbNama;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabelKaryawan;
    // End of variables declaration//GEN-END:variables
}
