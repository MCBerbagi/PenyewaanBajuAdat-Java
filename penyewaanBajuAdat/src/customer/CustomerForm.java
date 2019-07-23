/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customer;

import java.awt.Color;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Th3-TW1N5
 */
public class CustomerForm extends javax.swing.JInternalFrame {

    DAO dao = new CustomerDAO();
    int TAG = 0;
    int ID;

    public CustomerForm() {
        initComponents();
        view();

    }

    public void view() {
        tabelCustomer.setModel(dao.selectAll());
    }

    public void search() {
        if (tfcari.getText().isEmpty()) {
            view();
        } else {
            tabelCustomer.setModel(dao.search(tfcari.getText()));
        }
    }

    public void save() {
        try {
            
        if (tfKtp.getText().isEmpty() || tfAlamat.getText().isEmpty() || tfNoHp.getText().isEmpty() || tNama.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Inputan Data Belum Lengkap");
        } else {
            Customer b = new Customer();
            b.setKTP(Long.valueOf(tfKtp.getText()));
            b.setNama(tNama.getText());
            b.setAlamat(tfAlamat.getText());
            b.setNohp(tfNoHp.getText());

            if (TAG == 0) {

                //insert
                dao.insert(b);

            } else {
                //update
                int row = tabelCustomer.getSelectedRow();
                long ID = Long.valueOf(tabelCustomer.getValueAt(row, 0).toString());
                b.setKTP((ID));
                dao.update(b);
            }
            view();
            reset();
        }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void delete() {
        int jumlahbaris = tabelCustomer.getRowCount();
        int select = tabelCustomer.getSelectedRowCount();
        if (jumlahbaris > 0) {
            if (select > 0) {
                int row = tabelCustomer.getSelectedRow();
                long ID = Long.valueOf(tabelCustomer.getValueAt(row, 0).toString());
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
        int jumlahbaris = tabelCustomer.getRowCount();
        int select = tabelCustomer.getSelectedRowCount();
        if (jumlahbaris > 0) {
            if (select > 0) {
                TAG = 1;
                int row = tabelCustomer.getSelectedRow();
                tfKtp.setText(tabelCustomer.getValueAt(row, 0).toString());
                tNama.setText(tabelCustomer.getValueAt(row, 1).toString());
                tfAlamat.setText(tabelCustomer.getValueAt(row, 2).toString());
                tfNoHp.setText(tabelCustomer.getValueAt(row, 3).toString());
                tfKtp.setEnabled(false);
            } else {
                JOptionPane.showMessageDialog(null, "tidak ada data yang dipilih");
            }

        } else {

            JOptionPane.showMessageDialog(null, "tidak ada data");
        }

    }

    public void reset() {
        TAG = 0;
        tfKtp.setText("");
        tfKtp.setBackground(Color.white);
        tNama.setText("");
        tfAlamat.setText("");
        tfNoHp.setText("");
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
        tabelCustomer = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        tfcari = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        tfKtp = new javax.swing.JTextField();
        tNama = new javax.swing.JTextField();
        btnSimpan = new javax.swing.JButton();
        btnUbah = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        tfNoHp = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tfAlamat = new javax.swing.JTextArea();

        setTitle("Form Customer");

        btnReset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Reset-icon.png"))); // NOI18N
        btnReset.setText("Reset");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        tabelCustomer.setModel(new javax.swing.table.DefaultTableModel(
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
        tabelCustomer.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tabelCustomer);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("KTP :");

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Nama:");

        tfcari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfcariKeyTyped(evt);
            }
        });

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Alamat");

        tfKtp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tfKtpKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfKtpKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfKtpKeyTyped(evt);
            }
        });

        tNama.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tNamaKeyTyped(evt);
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
        jLabel4.setText("Pengisian Data Customer");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(214, 214, 214)
                .addComponent(jLabel4)
                .addContainerGap(284, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel4)
                .addContainerGap(34, Short.MAX_VALUE))
        );

        tfNoHp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfNoHpKeyTyped(evt);
            }
        });

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("No Hp");

        jLabel6.setText("Cari berdasarkan nama");

        tfAlamat.setColumns(20);
        tfAlamat.setRows(5);
        jScrollPane2.setViewportView(tfAlamat);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabel5))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(tfNoHp, javax.swing.GroupLayout.PREFERRED_SIZE, 447, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel1)
                                .addComponent(jLabel2)
                                .addComponent(jLabel3))
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(tNama, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 451, Short.MAX_VALUE)
                                .addComponent(tfKtp, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(btnSimpan)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(btnUbah)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnHapus)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnReset))))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfcari, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1)))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnHapus, btnReset, btnSimpan, btnUbah});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(tfKtp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tfcari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(tNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfNoHp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnUbah)
                                .addComponent(btnSimpan))
                            .addComponent(btnHapus)
                            .addComponent(btnReset))
                        .addGap(129, 129, 129))))
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

    private void tfKtpKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfKtpKeyTyped
        // TODO add your handling code here:
        filterangka(evt);

    }//GEN-LAST:event_tfKtpKeyTyped

    private void tNamaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tNamaKeyTyped
        // TODO add your handling code here:
        filterhuruf(evt);
    }//GEN-LAST:event_tNamaKeyTyped

    private void tfNoHpKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfNoHpKeyTyped
        // TODO add your handling code here:
        filterangka(evt);
    }//GEN-LAST:event_tfNoHpKeyTyped

    private void tfKtpKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfKtpKeyPressed
        // TODO add your handling code here:

    }//GEN-LAST:event_tfKtpKeyPressed

    private void tfKtpKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfKtpKeyReleased
        // TODO add your handling code here:
        if (tfKtp.getText().length() == 16) {
            tfKtp.setBackground(Color.yellow.brighter());
//            JOptionPane.showMessageDialog(null, "KTP Sudah 16 digit");

        } else {
//            JOptionPane.showMessageDialog(null, "KTP kurang 16 digit");
            tfKtp.setBackground(Color.WHITE);

        }
    }//GEN-LAST:event_tfKtpKeyReleased


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
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField tNama;
    private javax.swing.JTable tabelCustomer;
    private javax.swing.JTextArea tfAlamat;
    private javax.swing.JTextField tfKtp;
    private javax.swing.JTextField tfNoHp;
    private javax.swing.JTextField tfcari;
    // End of variables declaration//GEN-END:variables
}
