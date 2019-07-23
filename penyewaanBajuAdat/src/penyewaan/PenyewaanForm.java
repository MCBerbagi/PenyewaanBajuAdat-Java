/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package penyewaan;

import customer.*;
import database.DAO;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import pengembalian.PengembalianDAO;
import penyewaanItem.PenyewaanItemDAOImp;

/**
 *
 * @author Th3-TW1N5
 */
public class PenyewaanForm extends javax.swing.JInternalFrame {

    PenyewaanDAO dao = new PenyewaanDAOImp();
    penyewaanItem.PenyewaanItemDAO daoitem = new PenyewaanItemDAOImp();

    int TAG = 0;
    int ID;

    public PenyewaanForm() {
        initComponents();
        view();
    }

    public void view() {
        tabelPenyewaan.setModel(dao.selectAll());
        DefaultTableModel dtm;
        String judul[] = {"ID", "Nama Barang", "Harga Beli", "Qty", "Sub Total"};
        dtm = new DefaultTableModel(null, judul);
        tabelItem.setModel(dtm);

//        tabelItem.setModel(dao.detail(ID));
    }

    public void viewItem() {
        if (tabelPenyewaan.getSelectedRowCount() != 0) {
            int selected = tabelPenyewaan.getSelectedRow();
            ID = Integer.valueOf(tabelPenyewaan.getValueAt(selected, 0).toString());
            tabelItem.setModel(dao.detail(ID));
        } else {
            ID = 0;
            tabelItem.setModel(dao.detail(ID));
        }
    }

    public void search() {
        if (tfcari.getText().isEmpty()) {
            view();
        } else {
            tabelPenyewaan.setModel(dao.search(tfcari.getText()));
        }
    }

//    public void delete() {
//        int jumlahbaris = tabelPenyewaan.getRowCount();
//        int select = tabelPenyewaan.getSelectedRowCount();
//        if (jumlahbaris > 0) {
//            if (select > 0) {
//                int row = tabelPenyewaan.getSelectedRow();
//                int ID = Integer.valueOf(tabelPenyewaan.getValueAt(row, 0).toString());
//                daoitem.deleteitem(ID);
//                dao.delete(ID);
//                view();
//            } else {
//                JOptionPane.showMessageDialog(null, "tidak ada data yang dipilih");
//            }
//
//        } else {
//
//            JOptionPane.showMessageDialog(null, "tidak ada data");
//        }
//
//    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tfcari = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        btnBaru = new javax.swing.JButton();
        btnRefresh = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelPenyewaan = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelItem = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        btnRefresh1 = new javax.swing.JButton();

        setTitle("Form Penyewaan");

        tfcari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfcariKeyTyped(evt);
            }
        });

        jLabel6.setText("Cari berdasarkan tanggal Sewa");

        btnBaru.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/baru.png"))); // NOI18N
        btnBaru.setText("Baru");
        btnBaru.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBaruActionPerformed(evt);
            }
        });

        btnRefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/round-trip-return-repeat-48.png"))); // NOI18N
        btnRefresh.setText("Refresh");
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        tabelPenyewaan.setModel(new javax.swing.table.DefaultTableModel(
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
        tabelPenyewaan.getTableHeader().setReorderingAllowed(false);
        tabelPenyewaan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelPenyewaanMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelPenyewaan);

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tabelItem.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tabelItem);

        jLabel1.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        jLabel1.setText("Items :");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1107, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE))
        );

        btnRefresh1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/export.png"))); // NOI18N
        btnRefresh1.setText("Struk");
        btnRefresh1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefresh1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnBaru, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRefresh)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRefresh1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfcari, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jScrollPane1)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tfcari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnBaru, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnRefresh)
                        .addComponent(btnRefresh1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tfcariKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfcariKeyTyped
        // TODO add your handling code here:
        search();
    }//GEN-LAST:event_tfcariKeyTyped

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        // TODO add your handling code here:
        view();
        viewItem();
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void tabelPenyewaanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelPenyewaanMouseClicked
        // TODO add your handling code here:
        viewItem();
    }//GEN-LAST:event_tabelPenyewaanMouseClicked

    private void btnBaruActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBaruActionPerformed
        // TODO add your handling code here:
        new penyewaanItem.PenyewaanItemForm().setVisible(true);
    }//GEN-LAST:event_btnBaruActionPerformed

    private void btnRefresh1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefresh1ActionPerformed
        // TODO add your handling code here:
        int index = tabelPenyewaan.getSelectedRowCount();
        int jumlahbaris = tabelPenyewaan.getRowCount();
        if (jumlahbaris > 0) {
            if (index > 0) {
                int baris = tabelPenyewaan.getSelectedRow();
                String id = tabelPenyewaan.getValueAt(baris, 0).toString();
                dao.cetak(Integer.valueOf(id));
            } else {
                JOptionPane.showMessageDialog(null, "data belum dipilih");
            }
        } else {
            JOptionPane.showMessageDialog(null, "tidak ada data");
        }
    }//GEN-LAST:event_btnRefresh1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBaru;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnRefresh1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tabelItem;
    public static javax.swing.JTable tabelPenyewaan;
    private javax.swing.JTextField tfcari;
    // End of variables declaration//GEN-END:variables
}
