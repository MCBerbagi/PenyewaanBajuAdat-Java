/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import database.DAO;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;

/**
 *
 * @author Th3-TW1N5
 */
public class UserForm extends javax.swing.JInternalFrame {

    DAO dao = new UserDAO();
    int TAG = 0;
    int ID;

    public UserForm() {
        initComponents();
        view();
    }

    /**
     * Creates new form BajuAdatForm
     */
    public void view() {
        tabelUser.setModel(dao.selectAll());
    }

    public void search() {
        if (tfcari.getText().isEmpty()) {
            view();
        } else {
            tabelUser.setModel(dao.search(tfcari.getText()));
        }
    }

    public void save() {
        if (tfNama.getText().isEmpty() || tfAlamat.getText().isEmpty() || tfPasswd.getText().isEmpty()
                || tfUsername.getText().isEmpty() || tfnohp.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null,"Inputan data belum lengkap");
        } else {
            User b = new User();
            b.setNama(tfNama.getText());
            b.setAlamat(tfAlamat.getText());
            b.setNohp(tfnohp.getText());
            b.setUsername(tfUsername.getText());
            b.setPassword(tfPasswd.getText());
            b.setHakAkses(jAkses.getSelectedItem().toString());

            if (TAG == 0) {
                //insert
                dao.insert(b);
                System.out.println("Generated Id : " + b.getId());
            } else {
                //update
                b.setId(ID);
                dao.update(b);
            }
            view();
            reset();
        }
    }

    public void delete() {
        int jumlahbaris = tabelUser.getRowCount();
        int select = tabelUser.getSelectedRowCount();
        if (jumlahbaris > 0) {
            if (select > 0) {
                int row = tabelUser.getSelectedRow();
                ID = Integer.valueOf(tabelUser.getValueAt(row, 0).toString());
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
        int jumlahbaris = tabelUser.getRowCount();
        int select = tabelUser.getSelectedRowCount();
        if (jumlahbaris > 0) {
            if (select > 0) {
                TAG = 1;
                int row = tabelUser.getSelectedRow();
                ID = Integer.valueOf(tabelUser.getValueAt(row, 0).toString());
                tfNama.setText(tabelUser.getValueAt(row, 1).toString());
                tfAlamat.setText(tabelUser.getValueAt(row, 2).toString());
                tfnohp.setText(tabelUser.getValueAt(row, 3).toString());
                tfUsername.setText(tabelUser.getValueAt(row, 4).toString());
                tfPasswd.setText(tabelUser.getValueAt(row, 5).toString());
                jAkses.setSelectedItem(tabelUser.getValueAt(row, 6).toString());
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
        tfAlamat.setText("");
        tfnohp.setText("");
        tfUsername.setText("");
        tfPasswd.setText("");
        jAkses.setSelectedIndex(0);
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
        tabelUser = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        tfcari = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        tfNama = new javax.swing.JTextField();
        tfAlamat = new javax.swing.JTextField();
        btnSimpan = new javax.swing.JButton();
        btnUbah = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        tfnohp = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        tfUsername = new javax.swing.JTextField();
        tfPasswd = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jAkses = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();

        setTitle("Form User");

        btnReset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Reset-icon.png"))); // NOI18N
        btnReset.setText("Reset");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        tabelUser.setModel(new javax.swing.table.DefaultTableModel(
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
        tabelUser.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tabelUser);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Nama ");

        tfcari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfcariKeyTyped(evt);
            }
        });

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Alamat");

        tfNama.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfNamaKeyTyped(evt);
            }
        });

        tfAlamat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfAlamatKeyTyped(evt);
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
        jLabel4.setText("Pengisian Data User");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(298, 298, 298)
                .addComponent(jLabel4)
                .addContainerGap(324, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel4)
                .addContainerGap(32, Short.MAX_VALUE))
        );

        tfnohp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfnohpKeyTyped(evt);
            }
        });

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("No Hp");

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Username");

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("Password");

        jLabel8.setText("Cari berdasarkan Nama");

        jAkses.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-- Pilih --", "Admin", "Kasir" }));

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("Hak Akses");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel3)
                                .addComponent(jLabel5)
                                .addComponent(jLabel6)
                                .addComponent(jLabel7)
                                .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tfnohp)
                            .addComponent(tfUsername)
                            .addComponent(tfPasswd)
                            .addComponent(tfAlamat, javax.swing.GroupLayout.PREFERRED_SIZE, 427, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jAkses, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfNama, javax.swing.GroupLayout.PREFERRED_SIZE, 427, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnSimpan)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnUbah)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnHapus)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnReset)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tfcari, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnHapus, btnReset, btnSimpan, btnUbah});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfcari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(tfAlamat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(tfnohp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(tfUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(tfPasswd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jAkses, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnSimpan)
                            .addComponent(btnUbah)
                            .addComponent(btnHapus)
                            .addComponent(btnReset))
                        .addGap(44, 44, 44))))
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

    private void tfAlamatKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfAlamatKeyTyped
        // TODO add your handling code here:
        filterhuruf(evt);
    }//GEN-LAST:event_tfAlamatKeyTyped

    private void tfnohpKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfnohpKeyTyped
        // TODO add your handling code here:
        filterangka(evt);
    }//GEN-LAST:event_tfnohpKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JButton btnUbah;
    private javax.swing.JComboBox jAkses;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabelUser;
    private javax.swing.JTextField tfAlamat;
    private javax.swing.JTextField tfNama;
    private javax.swing.JTextField tfPasswd;
    private javax.swing.JTextField tfUsername;
    private javax.swing.JTextField tfcari;
    private javax.swing.JTextField tfnohp;
    // End of variables declaration//GEN-END:variables
}
