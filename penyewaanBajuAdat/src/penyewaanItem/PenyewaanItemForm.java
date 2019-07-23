/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package penyewaanItem;

import bajuAdat.BajuAdat;
import auth.Auth;
import static bajuAdat.BajuAdatlForm.tabelBaju;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import customer.Customer;
import database.Koneksi;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import penyewaan.Penyewaan;
import penyewaan.PenyewaanDAO;
import penyewaan.PenyewaanDAOImp;

import user.User;

/**
 *
 * @author jneferboy
 */
public class PenyewaanItemForm extends javax.swing.JFrame {

//    Dao<BajuAdat, Integer> daobaju;
    private PenyewaanItemDAO dao = new PenyewaanItemDAOImp();
    PenyewaanDAO daosewa = new PenyewaanDAOImp();
    database.DAO daobajuu = new bajuAdat.BajuAdatDAO();

    List<Integer> bajuadatId = new ArrayList<>();
    List<Long> customerId = new ArrayList<>();

    BajuAdat bajuadat;
    Customer customer;
    PenyewaanItem penyewaanItem;

    //get user logged in
    /**
     * Creates new form PenyewaanItemForm
     */
    public PenyewaanItemForm() {
        initComponents();
        setLocationRelativeTo(null);
        loadPakaianTradisional();
        viewItem();
        loadCustomer();

        tfdibayar.setText("0");
        tfkembali.setText("0");
        tampungstok.setVisible(false);
        btnSimpan.setEnabled(false);
        cbcus.setSelectedIndex(0);

        tfUser.setText(Auth.NAMA);
    }

    public void viewItem() {
        tabelItem.setModel(dao.viewItem());
    }

    public void loadPakaianTradisional() {
        cbPakaianTradisional.removeAllItems();
        cbPakaianTradisional.addItem("-- Pilih --");
        bajuadatId.add(0);

        for (BajuAdat b : dao.loadPakaianTradisional()) {
            cbPakaianTradisional.addItem(b.getNama());
            bajuadatId.add(b.getIdbajuAdat());

        }
    }

    public void loadCustomer() {
        cbcus.removeAllItems();
        cbcus.addItem("-- Pilih --");
        customerId.add(Long.valueOf(0));
        for (Customer c : dao.loadCustomer()) {
            cbcus.addItem(String.valueOf(c.getKTP() + "  ||  " + c.getNama()));
            customerId.add(Long.valueOf(c.getKTP()));
        }
    }

    public void getSubtotal() {
        bajuadat = dao.loadPakaianTradisional().get(cbPakaianTradisional.getSelectedIndex() - 1);

        if (!tfQty.getText().isEmpty()) {

            penyewaanItem = new PenyewaanItem();
            penyewaanItem.setJumlah_sewa(Integer.valueOf(tfQty.getText()));

            penyewaanItem.setSubTotal(penyewaanItem.getJumlah_sewa() * bajuadat.getHarga());
            tfSubTotal.setText(String.valueOf(penyewaanItem.getSubTotal()));

        }

    }

    public void addItem() {

        try {
            if (cbPakaianTradisional.getSelectedIndex() == 0 && tfQty.getText().isEmpty() && tfSubTotal.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "inputan belum lengkap");
            }else if(tfQty.getText().equals("0")){
                JOptionPane.showMessageDialog(null,"jumlah beli tidak boleh nol");
            } else {
                bajuadat = dao.loadPakaianTradisional().get(cbPakaianTradisional.getSelectedIndex() - 1);
                DecimalFormat df = new DecimalFormat("#,###,##0.00");
                if (bajuadat.getStok() < Integer.valueOf(tfQty.getText())) {
                    JOptionPane.showMessageDialog(null, "Maaf Stok kurang !!" + "\nSisa Stok :" + bajuadat.getStok());
                    cbPakaianTradisional.setSelectedIndex(0);
                    tfQty.setText(null);
                    tfSubTotal.setText(null);

                } else {
//               
                    penyewaanItem.setPakaiantradisional(bajuadat);
                    dao.addItem(penyewaanItem);
                    lTotal.setText("Rp. " + String.valueOf(df.format(dao.refreshTotal())));
                    viewItem();
                    cbPakaianTradisional.setSelectedIndex(0);

                    tfQty.setText("");
                    tfSubTotal.setText("");
                    tfdibayar.setText("0");
                    tfkembali.setText("0");
                    btnSimpan.setEnabled(false);
//                bajuAdat.BajuAdatlForm.tabelBaju.setModel(daobajuu.selectAll());

                }
            }
        } catch (Exception e) {
//JOptionPane.showMessageDialog(null, e);
        }

    }

    public void editItem() {

        try {
            bajuadat = dao.loadPakaianTradisional().get(cbPakaianTradisional.getSelectedIndex() - 1);
            DecimalFormat df = new DecimalFormat("#,###,##0.00");
            if (cbPakaianTradisional.getSelectedIndex() == 0 && tfQty.getText().isEmpty() && tfSubTotal.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "inputan belum lengkap");
            } else {
                if (bajuadat.getStok() < Integer.valueOf(tfQty.getText())) {
                    JOptionPane.showMessageDialog(null, "Maaf Stok kurang !!" + "\nSisa Stok :" + bajuadat.getStok());
                    cbPakaianTradisional.setSelectedIndex(0);
                    tfQty.setText(null);
                    tfSubTotal.setText(null);

                } else {
//               
                    int index = tabelItem.getSelectedRow();
                    penyewaanItem.setPakaiantradisional(bajuadat);
                    dao.editItem(index, penyewaanItem);
                    lTotal.setText("Rp. " + String.valueOf(df.format(dao.refreshTotal())));
                    viewItem();
                    cbPakaianTradisional.setSelectedIndex(0);

                    tfQty.setText("");
                    tfSubTotal.setText("");
                    tfdibayar.setText("0");
                    tfkembali.setText("0");
                    btnSimpan.setEnabled(false);
                    JOptionPane.showMessageDialog(null, "Item Berhasil di Update");
//                bajuAdat.BajuAdatlForm.tabelBaju.setModel(daobajuu.selectAll());

                }
            }
        } catch (Exception e) {
//JOptionPane.showMessageDialog(null, e);
        }

    }

    //    }
    public void deleteItem() {
        try {
            DecimalFormat df = new DecimalFormat("#,###,##0.00");
            int jumlahbaris = tabelItem.getRowCount();
            int select = tabelItem.getSelectedRowCount();
            if (jumlahbaris > 0) {
                if (select > 0) {
                    int row = tabelItem.getSelectedRow();

                    cbPakaianTradisional.setSelectedIndex(0);
                    tfQty.setText("");
                    tfSubTotal.setText("");
                    tfdibayar.setText("0");
                    tfkembali.setText("0");
                    btnSimpan.setEnabled(false);
                    dao.deleteItem(row);
                    viewItem();
                    lTotal.setText("Rp. " + String.valueOf(df.format(dao.refreshTotal())));
                    bajuAdat.BajuAdatlForm.tabelBaju.setModel(daobajuu.selectAll());
                } else {
                    JOptionPane.showMessageDialog(null, "tidak ada data yang dipilih");
                }

            } else {

                JOptionPane.showMessageDialog(null, "tidak ada data");
            }

        } catch (Exception e) {

        }
    }

    public void save() {
        if (cbcus.getSelectedIndex() == 0 || tfdibayar.getText().equals("") || tfkembali.getText().equals("")
                || jBatasKembali.getDate() == null) {
            JOptionPane.showMessageDialog(rootPane, "inputan data belum lengkap");
        } else {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            System.out.println(dateFormat.format(date)); //2016/11/16 12:08:43
            Penyewaan p = new Penyewaan();

            p.setTanggal(dateFormat.format(date));
            p.setTanggalBatasKembali(dateFormat.format(jBatasKembali.getDate()));
            p.setTotalHarga(dao.refreshTotal());
            p.setDibayar(Double.valueOf(tfdibayar.getText()));
            p.setKembali(Double.valueOf(tfkembali.getText()));
            p.setStatus("Belum Kembali");
            Customer c = new Customer();
            if (cbcus.getSelectedIndex() > 0) {
                int index = cbcus.getSelectedIndex() - 1;
                Customer cus = dao.loadCustomer().get(index);
                c.setKTP(cus.getKTP());
                p.setCustomer(c);
                User user = new User();
                user.setId(Auth.ID);
                //setuser
                p.setUser(user);
                //simpan penyewaan
                double total = dao.refreshTotal();
                double bayar = Double.valueOf(tfdibayar.getText());

                dao.insertPenyewaan(p);
                dao.cetak(p.getId_penyewaan());
                cbcus.setSelectedIndex(0);
//        resetItem();
                tfkembali.setText("0");
                lTotal.setText("Rp. " + "0");
//        tabelItem.setModel(dao.reset());
                tfdibayar.setText("0");
                dispose();
                bajuAdat.BajuAdatlForm.tabelBaju.setModel(daobajuu.selectAll());
                penyewaan.PenyewaanForm.tabelPenyewaan.setModel(daosewa.selectAll());

            } else {

                JOptionPane.showMessageDialog(null, "nama customer belum dipilih");
            }
        }

        //user
    }

    public void hitung() {
        double total = dao.refreshTotal();
        double bayar = Double.valueOf(tfdibayar.getText());
        if (bayar < total) {
            JOptionPane.showMessageDialog(null, "maaf uang anda kurang");
            tfkembali.setText("0");

        } else {
            double sisa = bayar - total;
            tfkembali.setText(String.valueOf(sisa));
            btnSimpan.setEnabled(true);
        }

    }

    public void reset() {
        //reset item
        dao.reset();

        cbPakaianTradisional.setSelectedIndex(0);
        lTotal.setText("Rp. " + String.valueOf(dao.refreshTotal()));
        viewItem();
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        tfUser = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        lTotal = new javax.swing.JLabel();
        cbcus = new javax.swing.JComboBox<String>();
        jLabel7 = new javax.swing.JLabel();
        jBatasKembali = new com.toedter.calendar.JDateChooser();
        jLabel8 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        cbPakaianTradisional = new javax.swing.JComboBox<String>();
        tfQty = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        tfSubTotal = new javax.swing.JTextField();
        btnTambahItem = new javax.swing.JButton();
        btnHapusItem = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelItem = new javax.swing.JTable();
        btnSimpan = new javax.swing.JButton();
        tfdibayar = new javax.swing.JTextField();
        tfkembali = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        tampungstok = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Form Penyewaan Item");
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("User :");

        tfUser.setEditable(false);

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.setToolTipText("");

        lTotal.setFont(new java.awt.Font("DejaVu Sans", 1, 18)); // NOI18N
        lTotal.setText("Rp. 0");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lTotal, javax.swing.GroupLayout.DEFAULT_SIZE, 381, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(29, Short.MAX_VALUE)
                .addComponent(lTotal)
                .addGap(27, 27, 27))
        );

        cbcus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel7.setText("Customer");

        jLabel8.setText("Batas Kembali");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jBatasKembali, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfUser, javax.swing.GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)
                    .addComponent(cbcus, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(106, 106, 106)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(tfUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(cbcus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jBatasKembali, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel4.setText("Pakaian");

        cbPakaianTradisional.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbPakaianTradisional.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbPakaianTradisionalActionPerformed(evt);
            }
        });

        tfQty.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfQtyKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfQtyKeyTyped(evt);
            }
        });

        jLabel5.setText("Qty :");

        jLabel6.setText("Subtotal :");

        tfSubTotal.setEditable(false);
        tfSubTotal.setBackground(new java.awt.Color(204, 204, 204));

        btnTambahItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/add-item.png"))); // NOI18N
        btnTambahItem.setText("Tambah ");
        btnTambahItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahItemActionPerformed(evt);
            }
        });

        btnHapusItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/recycle-full.png"))); // NOI18N
        btnHapusItem.setText("Hapus");
        btnHapusItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusItemActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/transaction_A-20.png"))); // NOI18N
        jButton2.setText("edit");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbPakaianTradisional, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfQty, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnTambahItem)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnHapusItem)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnTambahItem, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(btnHapusItem, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(jButton2))
                    .addComponent(tfSubTotal)
                    .addComponent(tfQty)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(cbPakaianTradisional, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5)
                        .addComponent(jLabel6)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {cbPakaianTradisional, jLabel4, jLabel5, jLabel6});

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
        tabelItem.getTableHeader().setReorderingAllowed(false);
        tabelItem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelItemMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelItem);

        btnSimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/shopping-cart-insert-icon.png"))); // NOI18N
        btnSimpan.setText("Simpan");
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });

        tfdibayar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfdibayarKeyTyped(evt);
            }
        });

        tfkembali.setEditable(false);
        tfkembali.setBackground(new java.awt.Color(204, 204, 204));

        jLabel2.setText("Di bayar");

        jLabel3.setText("Kembali");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/bill-32.png"))); // NOI18N
        jButton1.setText("Hitung");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        tampungstok.setText("stok");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tfdibayar)
                    .addComponent(tfkembali, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addGap(64, 64, 64)
                .addComponent(btnSimpan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(tampungstok))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfdibayar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(jButton1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(tfkembali, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btnSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tampungstok))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tfQtyKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfQtyKeyTyped
        // TODO add your handling code here:
      
    }//GEN-LAST:event_tfQtyKeyTyped

    private void btnTambahItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahItemActionPerformed
        // TODO add your handling code here:
        addItem();
    }//GEN-LAST:event_btnTambahItemActionPerformed

    private void btnHapusItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusItemActionPerformed
        // TODO add your handling code here:
        deleteItem();

    }//GEN-LAST:event_btnHapusItemActionPerformed

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        // TODO add your handling code here:
        save();

    }//GEN-LAST:event_btnSimpanActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        hitung();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void cbPakaianTradisionalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbPakaianTradisionalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbPakaianTradisionalActionPerformed

    private void tabelItemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelItemMouseClicked
        // TODO add your handling code here:
        int jumlahbaris = tabelItem.getRowCount();
        if (jumlahbaris > 0) {
            int pilih = tabelItem.getSelectedRowCount();
            if (pilih > 0) {
                int index = tabelItem.getSelectedRow();

                tfQty.setText(tabelItem.getValueAt(index, 3).toString());
                tfSubTotal.setText(tabelItem.getValueAt(index, 4).toString());
                cbPakaianTradisional.setSelectedItem(tabelItem.getValueAt(index, 1));
            } else {
                JOptionPane.showMessageDialog(null, "tidak ada data yang dipilih");
            }
        } else {
            JOptionPane.showMessageDialog(null, "tidak ada data");
        }
    }//GEN-LAST:event_tabelItemMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        editItem();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void tfdibayarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfdibayarKeyTyped
        // TODO add your handling code here:

    }//GEN-LAST:event_tfdibayarKeyTyped

    private void tfQtyKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfQtyKeyReleased
        // TODO add your handling code here:
        if(tfQty.getText().isEmpty()){
            tfSubTotal.setText("");
            
        }else{
            
            getSubtotal();
            filterangka(evt);
        }
    }//GEN-LAST:event_tfQtyKeyReleased

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
            java.util.logging.Logger.getLogger(PenyewaanItemForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PenyewaanItemForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PenyewaanItemForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PenyewaanItemForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new PenyewaanItemForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHapusItem;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JButton btnTambahItem;
    private javax.swing.JComboBox<String> cbPakaianTradisional;
    private javax.swing.JComboBox<String> cbcus;
    private com.toedter.calendar.JDateChooser jBatasKembali;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lTotal;
    private javax.swing.JTable tabelItem;
    private javax.swing.JLabel tampungstok;
    private javax.swing.JTextField tfQty;
    private javax.swing.JTextField tfSubTotal;
    private javax.swing.JTextField tfUser;
    private javax.swing.JTextField tfdibayar;
    private javax.swing.JTextField tfkembali;
    // End of variables declaration//GEN-END:variables
}
