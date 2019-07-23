/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pengembalian;

import auth.Auth;
import bajuAdat.BajuAdat;
import penyewaan.*;
import customer.*;
import database.DAO;
import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import pengembalian.PengembalianDAO;
import penyewaanItem.PenyewaanItem;
import penyewaanItem.PenyewaanItemDAO;
import penyewaanItem.PenyewaanItemDAOImp;
import penyewaanItem.PenyewaanItemForm;
import user.User;

/**
 *
 * @author Th3-TW1N5
 */
public class PengembalianForm extends javax.swing.JInternalFrame {

    PengembalianDAO dao = new PengembalianDAOImp();
    PenyewaanItemDAO daopenye = new PenyewaanItemDAOImp();
//    PenyewaanDAO daopenyewaan=new PenyewaanDAOImp();
//    penyewaanItem.PenyewaanItemDAO daopenyeitem = new PenyewaanItemDAOImp();

    List<Integer> penyewaanid = new ArrayList<>();

    Penyewaan penyewaan;
    PenyewaanItem penyewaanItem;
    bajuAdat.BajuAdat pakaiantradisional;

    int TAG = 0;
    int ID;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public PengembalianForm() {
        initComponents();
        view();
        viewItem();
        loadpenyewaanbox();
        jButton1.setEnabled(false);
    }

    public void loadpenyewaanbox() {
        try {
            jDatapenyewaan.removeAllItems();
            jDatapenyewaan.addItem("-- Pilih --");
            penyewaanid.add(0);
            for (Penyewaan b : dao.loadPenyewaanbox()) {
                jDatapenyewaan.addItem(b.getId_penyewaan() + " : " + b.getCustomer().getNama() + " : " + b.getTanggalBatasKembali());
                penyewaanid.add(b.getId_penyewaan());
            }
        } catch (Exception e) {

        }
    }

    public void view() {
        tabelPengembalian.setModel(dao.selectAll());
        DefaultTableModel dtm;
        String judul[] = {"ID", "Nama Barang", "Harga Beli", "Qty", "Sub Total"};
        dtm = new DefaultTableModel(null, judul);
        tabelItem.setModel(dtm);
        tabelPengembalian.setModel(dao.selectAll());
        jtglKembali.setEnabled(false);
        tfbayar.setEnabled(false);
        tfbayar.setText("0");
        tfdenda.setText("0");
        tfkembali.setText("0");
        selisihHari.setText("0");
        jtglKembali.setDate(null);
//        jhapus.setVisible(true);
        jPanel2.setVisible(true);
        jDatapenyewaan.setSelectedIndex(0);
//        tabelItem.setModel(dao.detail(ID));
    }

    public void viewItem() {
        try {

            int selected = tabelPengembalian.getSelectedRow();
            ID = Integer.valueOf(tabelPengembalian.getValueAt(selected, 1).toString());
            tabelItem.setModel(dao.detail(ID));

        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, e);

        }
    }

    public void search() {
        if (tfcari.getText().isEmpty()) {
            view();
        } else {
            tabelPengembalian.setModel(dao.search(tfcari.getText()));
        }
    }

    public void hitung() {
        try {
            int row = tabelPengembalian.getSelectedRow();
            int index = jDatapenyewaan.getSelectedIndex() - 1;
            Penyewaan penyen = dao.loadPenyewaanbox().get(index);
            Date date1 = sdf.parse(penyen.getTanggalBatasKembali());
            Date date2 = sdf.parse(String.valueOf(sdf.format(jtglKembali.getDate())));
            long selisih = date2.getTime() - date1.getTime();
            if (date2.getTime() < date1.getTime()) {
                selisihHari.setText("0");
                tfdenda.setText("0");
            } else {
                int telat = (int) TimeUnit.DAYS.convert(selisih, TimeUnit.MILLISECONDS);
                selisihHari.setText(String.valueOf(telat));
                double denda = telat * 20000;
                tfdenda.setText(String.valueOf(denda));

                if (telat > 0) {
                    tfbayar.setEnabled(true);

                } else {
                    tfbayar.setEnabled(false);
                }

            }

        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, e);
            JOptionPane.showMessageDialog(null, "tanggal kembali belum dipilih");

        }

    }

    public void save() {
        if (tfbayar.getText().isEmpty() || tfdenda.getText().isEmpty() || tfkembali.getText().isEmpty()
                || jtglKembali.getDate() == null) {
            JOptionPane.showMessageDialog(null, "inputan Belum Lengkap");
        } else {
            if (jDatapenyewaan.getSelectedIndex() > 0) {
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date date = new Date();
                System.out.println(dateFormat.format(date)); //2016/11/16 12:08:43
                Pengembalian p = new Pengembalian();

                p.setTanggalKembali(dateFormat.format(jtglKembali.getDate()));
                p.setDenda(Double.valueOf(tfdenda.getText()));
                p.setDibayar(Double.valueOf(tfbayar.getText()));
                p.setKembali(Double.valueOf(tfkembali.getText()));

                Penyewaan penye = new Penyewaan();

//        Penyewaan penye1 = new Penyewaan();
                int jumlahbaris = tabelPengembalian.getSelectedRowCount();
                jtglKembali.setEnabled(true);
                int index = jDatapenyewaan.getSelectedIndex() - 1;
                Penyewaan penyen = dao.loadPenyewaanbox().get(index);
                //            int IDpenye = Integer.valueOf(tabelPengembalian.getValueAt(row, 0).toString());
//            Penyewaan penyen = (Penyewaan) dao.loadPenyewaan().getValueAt(row, 0);
                p.setPenyewaan(penye);
                penye.setId_penyewaan(penyen.getId_penyewaan());
                penye.setCustomer(penyen.getCustomer());
                penye.setDibayar(penyen.getDibayar());
                penye.setPenyewaanItems(penyen.getPenyewaanItems());

                penye.setTanggal(penyen.getTanggal());
                penye.setTanggalBatasKembali(penyen.getTanggalBatasKembali());
                penye.setTotalHarga(penyen.getTotalHarga());
                penye.setUser(penyen.getUser());
                penye.setStatus("Sudah Kembali");
                dao.Updatepenye(penye);
                dao.insert(p);
                dao.cetak(String.valueOf(p.getId()));
                tfbayar.setText("0");
                tfbayar.setEnabled(false);
                tfdenda.setText("0");
                tfkembali.setText("0");
                selisihHari.setText("0");
                jtglKembali.setDate(null);
                jButton1.setEnabled(false);
                jDatapenyewaan.setSelectedIndex(0);
                view();
                view();
                loadpenyewaanbox();

            }
        }

    }

    public void delete() {
        int jumlahbaris = tabelPengembalian.getRowCount();
        int select = tabelPengembalian.getSelectedRowCount();
        if (jumlahbaris > 0) {
            if (select > 0) {
                int row = tabelPengembalian.getSelectedRow();
                ID = Integer.valueOf(tabelPengembalian.getValueAt(row, 0).toString());
                dao.delete(ID);
                view();
            } else {
                JOptionPane.showMessageDialog(null, "tidak ada data yang dipilih");
            }

        } else {

            JOptionPane.showMessageDialog(null, "tidak ada data");
        }

    }

    void filterangka(KeyEvent a) {
        if (Character.isAlphabetic(a.getKeyChar())) {
            a.consume();
            JOptionPane.showMessageDialog(null, "inputan hanya angka saja");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnRefresh = new javax.swing.JButton();
        jtglKembali = new com.toedter.calendar.JDateChooser();
        jlabeltangal = new javax.swing.JLabel();
        jhari = new javax.swing.JLabel();
        selisihHari = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelPengembalian = new javax.swing.JTable();
        tfcari = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelItem = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        btnharii = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        tfbayar = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        tfdenda = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        tfkembali = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jhapus = new javax.swing.JButton();
        jDatapenyewaan = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        btnRefresh1 = new javax.swing.JButton();

        setTitle("Form Pengembalian");

        btnRefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/round-trip-return-repeat-48.png"))); // NOI18N
        btnRefresh.setText("Refresh");
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        jtglKembali.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                jtglKembaliAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        jlabeltangal.setText("Tgl Kembali");

        jhari.setText("Hari");

        selisihHari.setEditable(false);
        selisihHari.setBackground(new java.awt.Color(204, 204, 204));

        jButton1.setText("Simpan data pengembalian");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        tabelPengembalian.setModel(new javax.swing.table.DefaultTableModel(
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
        tabelPengembalian.getTableHeader().setReorderingAllowed(false);
        tabelPengembalian.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelPengembalianMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelPengembalian);

        tfcari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfcariKeyTyped(evt);
            }
        });

        jLabel6.setText("Cari berdasarkan tanggal Kembali");

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
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 642, Short.MAX_VALUE)
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
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 337, Short.MAX_VALUE))
        );

        btnharii.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/bill-32.png"))); // NOI18N
        btnharii.setText("hitung");
        btnharii.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnhariiActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Transaksi", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        tfbayar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tfbayarKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfbayarKeyTyped(evt);
            }
        });

        jLabel7.setText("Di Bayar");

        tfdenda.setEditable(false);
        tfdenda.setBackground(new java.awt.Color(204, 204, 204));

        jLabel5.setText("Denda");

        jButton3.setText("Bayar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton3)
                    .addComponent(tfdenda, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
                    .addComponent(tfbayar))
                .addGap(0, 7, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {tfbayar, tfdenda});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(tfdenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(tfbayar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addComponent(jButton3)
                .addContainerGap())
        );

        tfkembali.setEditable(false);
        tfkembali.setBackground(new java.awt.Color(204, 204, 204));

        jLabel8.setText("Kembali");

        jhapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Windows-Close-Program-icon.png"))); // NOI18N
        jhapus.setText("Hapus");
        jhapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jhapusActionPerformed(evt);
            }
        });

        jDatapenyewaan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jDatapenyewaan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jDatapenyewaanActionPerformed(evt);
            }
        });

        jLabel2.setText("Data Penyewaan");

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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addGap(18, 18, 18)
                                    .addComponent(jLabel8)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(tfkembali, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jlabeltangal)
                                        .addComponent(jhari))
                                    .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(selisihHari)
                                            .addGap(18, 18, 18)
                                            .addComponent(btnharii))
                                        .addComponent(jtglKembali, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jDatapenyewaan, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnRefresh)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jhapus)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnRefresh1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 644, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfcari, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRefresh)
                    .addComponent(jhapus)
                    .addComponent(btnRefresh1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jDatapenyewaan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jtglKembali, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlabeltangal))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jhari)
                    .addComponent(selisihHari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnharii))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(tfkembali, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addContainerGap(319, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfcari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(26, 26, 26)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        // TODO add your handling code here:
        loadpenyewaanbox();
        view();
//        jPanel2.setVisible(true);
        tfcari.setVisible(true);
        jLabel6.setVisible(true);
        jButton1.setEnabled(true);
        jtglKembali.setEnabled(false);
        jtglKembali.setVisible(true);
        jlabeltangal.setVisible(true);
        jtglKembali.setVisible(true);
        selisihHari.setVisible(true);
        btnharii.setVisible(true);
        jhari.setVisible(true);
        jButton1.setEnabled(false);
//        viewItem();

    }//GEN-LAST:event_btnRefreshActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        save();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tabelPengembalianMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelPengembalianMouseClicked
        // TODO add your handling code here:
        viewItem();
    }//GEN-LAST:event_tabelPengembalianMouseClicked

    private void tfcariKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfcariKeyTyped
        // TODO add your handling code here:
        search();
    }//GEN-LAST:event_tfcariKeyTyped

    private void jtglKembaliAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jtglKembaliAncestorAdded
        // TODO add your handling code here:
//        hitung();
    }//GEN-LAST:event_jtglKembaliAncestorAdded

    private void btnhariiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhariiActionPerformed
        // TODO add your handling code here:
        hitung();
    }//GEN-LAST:event_btnhariiActionPerformed

    private void tfbayarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfbayarKeyPressed
        // TODO add your handling code here:


    }//GEN-LAST:event_tfbayarKeyPressed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        double bayar = Double.valueOf(tfbayar.getText());
        double denda = Double.valueOf(tfdenda.getText());
        if (bayar < denda) {
            JOptionPane.showMessageDialog(null, "maaf uang anda kurang");
            jButton1.setEnabled(false);
        } else {
            double kembali = bayar - denda;
            tfkembali.setText(String.valueOf(kembali));
            jButton1.setEnabled(true);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jhapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jhapusActionPerformed
        // TODO add your handling code here:
        delete();

    }//GEN-LAST:event_jhapusActionPerformed

    private void jDatapenyewaanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jDatapenyewaanActionPerformed
        // TODO add your handling code here:
        try {
            if (jDatapenyewaan.getSelectedIndex() > 0) {
                int index = jDatapenyewaan.getSelectedIndex() - 1;
                Penyewaan p = dao.loadPenyewaanbox().get(index);

                tabelItem.setModel(dao.detail(p.getId_penyewaan()));
                jtglKembali.setEnabled(true);
            }
        } catch (Exception e) {

        }
    }//GEN-LAST:event_jDatapenyewaanActionPerformed

    private void tfbayarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfbayarKeyTyped
        // TODO add your handling code here:
        filterangka(evt);

    }//GEN-LAST:event_tfbayarKeyTyped

    private void btnRefresh1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefresh1ActionPerformed
        // TODO add your handling code here:
        int index = tabelPengembalian.getSelectedRowCount();
        int jumlahbaris = tabelPengembalian.getRowCount();
        if (jumlahbaris > 0) {
            if (index > 0) {
                int baris = tabelPengembalian.getSelectedRow();
                String id = tabelPengembalian.getValueAt(baris, 0).toString();
                dao.cetak((id));
            } else {
                JOptionPane.showMessageDialog(null, "data belum dipilih");
            }
        } else {
            JOptionPane.showMessageDialog(null, "tidak ada data");
        }
    }//GEN-LAST:event_btnRefresh1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnRefresh1;
    private javax.swing.JButton btnharii;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox jDatapenyewaan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton jhapus;
    private javax.swing.JLabel jhari;
    private javax.swing.JLabel jlabeltangal;
    private com.toedter.calendar.JDateChooser jtglKembali;
    private javax.swing.JTextField selisihHari;
    private javax.swing.JTable tabelItem;
    private javax.swing.JTable tabelPengembalian;
    private javax.swing.JTextField tfbayar;
    private javax.swing.JTextField tfcari;
    private javax.swing.JTextField tfdenda;
    private javax.swing.JTextField tfkembali;
    // End of variables declaration//GEN-END:variables
}
