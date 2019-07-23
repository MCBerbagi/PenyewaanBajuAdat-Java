/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bajuAdat;

import database.DAO;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;

/**
 *
 * @author Th3-TW1N5
 */
public class BajuAdatlForm extends javax.swing.JInternalFrame {

    DAO dao = new BajuAdatDAO();
    int TAG = 0;
    int ID;

    public BajuAdatlForm() {
        initComponents();
        view();
    }

    /**
     * Creates new form BajuAdatForm
     */
    public void view() {
        tabelBaju.setModel(dao.selectAll());
    }

    public void search() {
        if (tfcari.getText().isEmpty()) {
            view();
        } else {
            tabelBaju.setModel(dao.search(tfcari.getText()));
        }
    }

    public void save() {
        if (tfNama.getText().isEmpty() || tfharga.getText().isEmpty() || tfstok.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Inputan Belum Lengkap");
        } else if (Integer.valueOf(tfstok.getText()) <= 0) {
            JOptionPane.showMessageDialog(null, "stok tidak boleh nol");

        } else if (Integer.valueOf(tfharga.getText()) <= 0) {
            JOptionPane.showMessageDialog(null, "Harga tidak boleh nol");

        } else {
            BajuAdat b = new BajuAdat();
            b.setNama(tfNama.getText());
            b.setHarga(Double.valueOf(tfharga.getText()));
            b.setStok(Integer.valueOf(tfstok.getText()));

            if (TAG == 0) {
                //insert
                dao.insert(b);
                System.out.println("Generated Id : " + b.getIdbajuAdat());
            } else {
                //update
                b.setIdbajuAdat(ID);

                dao.update(b);
            }
            view();
            reset();

        }
    }

    public void delete() {
        int jumlahbaris = tabelBaju.getRowCount();
        int select = tabelBaju.getSelectedRowCount();
        if (jumlahbaris > 0) {
            if (select > 0) {
                int row = tabelBaju.getSelectedRow();
                ID = Integer.valueOf(tabelBaju.getValueAt(row, 0).toString());
                dao.delete(ID);
                view();
            } else {
                JOptionPane.showMessageDialog(null, "tidak ada data yang dipilih");
            }

        } else {

            JOptionPane.showMessageDialog(null, "tidak ada data");
        }

    }

    public void getRecord() {
        int jumlahbaris = tabelBaju.getRowCount();
        int select = tabelBaju.getSelectedRowCount();
        if (jumlahbaris > 0) {
            if (select > 0) {
                TAG = 1;
                int row = tabelBaju.getSelectedRow();
                ID = Integer.valueOf(tabelBaju.getValueAt(row, 0).toString());
                tfNama.setText(tabelBaju.getValueAt(row, 1).toString());
                tfharga.setText(tabelBaju.getValueAt(row, 2).toString());
                tfstok.setText(tabelBaju.getValueAt(row, 3).toString());
            } else {
                JOptionPane.showMessageDialog(null, "tidak ada data yang dipilih");
            }

        } else {

            JOptionPane.showMessageDialog(null, "tidak ada data");
        }

    }

    public void reset() {
        TAG = 0;
        tfNama.setText("");
        tfharga.setText("");
        tfstok.setText("");
        view();

    }

    void filterangka(KeyEvent a) {

        if (Character.isAlphabetic(a.getKeyChar())) {

            a.consume();
            JOptionPane.showMessageDialog(null, "Hanya Boleh Input Angka");
        }

    }

    void filterhuruf(KeyEvent b) {
        if (Character.isDigit(b.getKeyChar())) {
            b.consume();
            JOptionPane.showMessageDialog(null, "Hanya Boleh Input Huruf");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnReset = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelBaju = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        tfcari = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        tfNama = new javax.swing.JTextField();
        tfharga = new javax.swing.JTextField();
        tfstok = new javax.swing.JTextField();
        btnSimpan = new javax.swing.JButton();
        btnUbah = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setTitle("Form Baju Adat");
        setName("Form Baju Adat"); // NOI18N

        btnReset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Reset-icon.png"))); // NOI18N
        btnReset.setText("Reset");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        tabelBaju.setModel(new javax.swing.table.DefaultTableModel(
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
        tabelBaju.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tabelBaju);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Nama :");

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Harga");

        tfcari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfcariKeyTyped(evt);
            }
        });

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Stok");

        tfNama.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfNamaKeyTyped(evt);
            }
        });

        tfharga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfhargaActionPerformed(evt);
            }
        });
        tfharga.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfhargaKeyTyped(evt);
            }
        });

        tfstok.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfstokKeyTyped(evt);
            }
        });

        btnSimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/shopping-cart-insert-icon.png"))); // NOI18N
        btnSimpan.setText("Simpan");
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });

        btnUbah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Text-Edit-icon.png"))); // NOI18N
        btnUbah.setText("Ubah");
        btnUbah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUbahActionPerformed(evt);
            }
        });

        btnHapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Windows-Close-Program-icon.png"))); // NOI18N
        btnHapus.setText("Hapus");
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(153, 255, 255));
        jPanel1.setToolTipText("");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jLabel4.setText("Pengisian Data Baju Adat");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(214, 214, 214)
                .addComponent(jLabel4)
                .addContainerGap(282, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel4)
                .addContainerGap(34, Short.MAX_VALUE))
        );

        jLabel5.setText("Cari berdasarkan nama");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnSimpan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnUbah)
                        .addGap(18, 18, 18)
                        .addComponent(btnHapus)
                        .addGap(18, 18, 18)
                        .addComponent(btnReset))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tfNama, javax.swing.GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
                            .addComponent(tfharga)
                            .addComponent(tfstok))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tfcari, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1)))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(tfNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfcari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(tfharga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(tfstok, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnSimpan)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnHapus)
                                .addComponent(btnReset)
                                .addComponent(btnUbah)))
                        .addContainerGap(209, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        // TODO add your handling code here:
        reset();
    }//GEN-LAST:event_btnResetActionPerformed

    private void tfcariKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfcariKeyTyped
        // TODO add your handling code here:
        search();
        filterhuruf(evt);
    }//GEN-LAST:event_tfcariKeyTyped

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        // TODO add your handling code here:
        save();
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void btnUbahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUbahActionPerformed
        // TODO add your handling code here:
        getRecord();
    }//GEN-LAST:event_btnUbahActionPerformed

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        // TODO add your handling code here:
        delete();
    }//GEN-LAST:event_btnHapusActionPerformed

    private void tfNamaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfNamaKeyTyped
        // TODO add your handling code here:
        filterhuruf(evt);
    }//GEN-LAST:event_tfNamaKeyTyped

    private void tfhargaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfhargaActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_tfhargaActionPerformed

    private void tfhargaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfhargaKeyTyped
        // TODO add your handling code here:
        filterangka(evt);
    }//GEN-LAST:event_tfhargaKeyTyped

    private void tfstokKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfstokKeyTyped
        // TODO add your handling code here:
        filterangka(evt);

    }//GEN-LAST:event_tfstokKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JButton btnUbah;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTable tabelBaju;
    private javax.swing.JTextField tfNama;
    private javax.swing.JTextField tfcari;
    private javax.swing.JTextField tfharga;
    private javax.swing.JTextField tfstok;
    // End of variables declaration//GEN-END:variables
}
