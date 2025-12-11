/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package Admin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author ANANDA AYU KARTIKA S
 */
public class Gaji extends javax.swing.JDialog {

    /**
     * Creates new form PeriodeGajii
     */
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

    public Gaji(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        connect();

//        loadTable();
    }

    public Gaji(java.awt.Frame parent, boolean modal, int idLogin) {
        
    super(parent, modal);
    initComponents();
    connect();
    
    this.idKaryawanLogin = idLogin;

    loadComboPeriode();  // WAJIB di sini


        
    }

    public void setIdKaryawanLogin(int id) {
        this.idKaryawanLogin = id;
    }

    private void loadComboPeriode() {
    jComboBox1.removeAllItems();
    jComboBox1.addItem("0 - Semua Periode");

    EntityManager em = emf.createEntityManager();
    try {
        List<PeriodeGaji> list = em.createQuery(
            "SELECT p FROM PeriodeGaji p ORDER BY p.tahun DESC, p.bulan DESC",
            PeriodeGaji.class
        ).getResultList();

        for (PeriodeGaji p : list) {
            String teks = p.getIdPeriodeGaji() + " - " + p.getTahun() + "-" + p.getBulan();
            jComboBox1.addItem(teks);
        }
    } finally {
        em.close();
    }
}

    private void hideColumn(int index) {
    tabelGaji.getColumnModel().getColumn(index).setMinWidth(0);
    tabelGaji.getColumnModel().getColumn(index).setMaxWidth(0);
    tabelGaji.getColumnModel().getColumn(index).setWidth(0);
}


    private void loadTable(int idPeriode) {
    if (idKaryawanLogin == null) {
        JOptionPane.showMessageDialog(this, 
                "ID Login Karyawan masih NULL!");
        return;
    }

    EntityManager em = emf.createEntityManager();
    try {

        String sql = "SELECT "
                + "g.id_gaji, "
                + "k.id_karyawan, "
                + "g.id_periode_gaji, "
                + "k.nama_karyawan, "
                + "p.bulan, p.tahun, "
                + "g.gaji_pokok, g.total_hadir, g.total_cuti, g.total_alpha, "
                + "g.tunjangan, g.lembur, g.bonus, g.total_potongan, g.total_gaji_bersih "
                + "FROM list_gaji g "
                + "JOIN karyawan k ON g.id_karyawan = k.id_karyawan "
                + "JOIN periode_gaji p ON g.id_periode_gaji = p.id_periode_gaji "
                + "WHERE g.id_periode_gaji = ? AND g.id_karyawan = ?";

        List<Object[]> list = em.createNativeQuery(sql)
                .setParameter(1, idPeriode)
                .setParameter(2, idKaryawanLogin)
                .getResultList();

        DefaultTableModel model = new DefaultTableModel();

        model.addColumn("ID Gaji");
        model.addColumn("ID Karyawan");      // hidden
        model.addColumn("ID Periode");       // hidden
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

        // SEMBUNYIKAN KOLUMN ID
        hideColumn(1); // id_karyawan
        hideColumn(2); // id_periode_gaji

    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error load table: " + e.getMessage());
    } finally {
        em.close();
    }
}

    
    private void loadTableAll() {
    if (idKaryawanLogin == null) {
        JOptionPane.showMessageDialog(this, 
            "ID Login Karyawan masih NULL! Pastikan Gaji dipanggil dengan idLogin.");
        return;
    }

    EntityManager em = emf.createEntityManager();
    try {
        String sql = "SELECT "
                + "g.id_gaji, k.id_karyawan, k.nama_karyawan, "
                + "p.bulan, p.tahun, "
                + "g.gaji_pokok, g.total_hadir, g.total_cuti, g.total_alpha, "
                + "g.tunjangan, g.lembur, g.bonus, g.total_potongan, g.total_gaji_bersih "
                + "FROM list_gaji g "
                + "JOIN karyawan k ON g.id_karyawan = k.id_karyawan "
                + "JOIN periode_gaji p ON g.id_periode_gaji = p.id_periode_gaji "
                + "WHERE g.id_karyawan = ? "
                + "ORDER BY g.id_periode_gaji DESC";

        List<Object[]> list = em.createNativeQuery(sql)
                .setParameter(1, idKaryawanLogin)
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
        JOptionPane.showMessageDialog(this, "Error load table: " + e.getMessage());
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
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Periode Gaji");

        jComboBox1.setBackground(new java.awt.Color(255, 255, 255));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        tabelGaji.setBackground(new java.awt.Color(255, 255, 255));
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

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 0, 0));
        jButton1.setText("Cetak");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel1)
                .addGap(66, 66, 66)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(31, 31, 31))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 936, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(24, 24, 24))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
                                                
    if (jComboBox1.getSelectedItem() == null) return;

    String teks = jComboBox1.getSelectedItem().toString();
    int idPeriode = Integer.parseInt(teks.split(" - ")[0]);

    if (idPeriode == 0) {
        loadTableAll();
    } else {
        loadTable(idPeriode);
    }


    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
     

    try {
        Class.forName("org.postgresql.Driver");
        Connection conn = DriverManager.getConnection(
            "jdbc:postgresql://localhost:5432/TugasBDL",
            "postgres",
            "Renjun23032000."
        );

        InputStream reportStream = getClass().getResourceAsStream("/Admin/a.jasper");
        if (reportStream == null) {
            JOptionPane.showMessageDialog(this, "File report tidak ditemukan!");
            return;
        }

        String periodeDipilih = jComboBox1.getSelectedItem().toString();

Map<String, Object> parameters = new HashMap<>();
parameters.put("id_karyawan_param", idKaryawanLogin);

// Jika “0 - Semua Periode”
if (periodeDipilih.startsWith("0")) {
    parameters.put("id_periode_param", null);
    System.out.println("Cetak: SEMUA PERIODE");
} else {
    // Format item combobox = "2 - Februari 2025"
    int idPeriode = Integer.parseInt(periodeDipilih.split(" - ")[0]);
    parameters.put("id_periode_param", idPeriode);
    System.out.println("Cetak: PERIODE = " + idPeriode);
}

        // CETAK LAPORAN
        JasperPrint jprint = JasperFillManager.fillReport(reportStream, parameters, conn);
        JasperViewer viewer = new JasperViewer(jprint, false);
        viewer.setVisible(true);

    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
    }



    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Gaji.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Gaji.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Gaji.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Gaji.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Gaji dialog = new Gaji(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabelGaji;
    // End of variables declaration//GEN-END:variables

}
