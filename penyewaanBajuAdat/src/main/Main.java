/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import auth.AuthDAOImp;
import auth.AuthForm;
import bajuAdat.BajuAdatlForm;
import java.awt.CardLayout;
import customer.CustomerForm;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import pengembalian.PengembalianForm;
import penyewaan.LaporanForm;
import penyewaan.PenyewaanForm;
import user.UserForm;
import database.Koneksi;
import java.io.File;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.stmt.PreparedQuery;
import database.DAO;
import database.Koneksi;
import database.KoneksiReport;
import static groovy.inspect.Inspector.print;
import java.io.File;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JRViewer;
import net.sf.jasperreports.view.JasperViewer;
import static org.slf4j.helpers.Util.report;

public class Main extends javax.swing.JFrame {

    JasperReport report;
    JasperPrint print;
    JasperDesign desaign;
    private BajuAdatlForm baju;
    private user.UserForm user;
    private CustomerForm customer;
    private penyewaan.PenyewaanForm penyewaan;
    private pengembalian.PengembalianForm pengembalian;
    private walpaper walpaper;
    private LaporanForm laporan;
    auth.AuthDAO a = new AuthDAOImp();

    public Main() {
        initComponents();
        jDateChooser1.setVisible(false);
        jDateChooser2.setVisible(false);
        lsampai.setVisible(false);
        btprint1.setVisible(false);
        btprint.setVisible(false);
        cblaporan.setVisible(false);
        desktop.setVisible(true);
//        btprint.setVisible(false);
        showpanel("f");

        setTitle("Aplikasi Penyewaan Baju Adat by: Mahar Catur Ferniza");
        user1.setText(auth.Auth.NAMA);
        user2.setText(auth.Auth.NAMA);
        user3.setText(auth.Auth.NAMA);
        user4.setText(auth.Auth.NAMA);
        jhak.setText(auth.Auth.HakAkses);
        jhak1.setText(auth.Auth.HakAkses);
        jhak2.setText(auth.Auth.HakAkses);
        jhak3.setText(auth.Auth.HakAkses);
//        desktop.setEnabled(false);
//        desktop.setSize(600, 300);
        setLocationRelativeTo(null);
        setExtendedState(MAXIMIZED_BOTH);

//        desktop.setVisible(false);
        baju = new BajuAdatlForm();
        customer = new CustomerForm();
        penyewaan = new PenyewaanForm();
        pengembalian = new PengembalianForm();
        walpaper = new walpaper();
        user = new UserForm();
        laporan = new LaporanForm();

        desktop.add("f", walpaper);
        desktop.add("a", baju);
        desktop.add("b", customer);
        desktop.add("c", user);
        desktop.add("d", penyewaan);
        desktop.add("e", pengembalian);
        desktop.add("l", laporan);

        desktop.setVisible(true);

//        desktop.add("b", pem);
    }

    private void showpanel(String panelIdentifier) {
        CardLayout cardLayout = (CardLayout) desktop.getLayout();
        cardLayout.show(desktop, panelIdentifier);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jdataMaster1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        user1 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jhak = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jdatamaster = new javax.swing.JPanel();
        jUser = new javax.swing.JButton();
        jJCustomer = new javax.swing.JButton();
        jBajuAdat = new javax.swing.JButton();
        user3 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jhak1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        user2 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jpenyewaan = new javax.swing.JButton();
        jPengembalian = new javax.swing.JButton();
        jhak2 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        cblaporan = new javax.swing.JComboBox();
        btprint = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        user4 = new javax.swing.JLabel();
        jhak3 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        lsampai = new javax.swing.JLabel();
        btprint1 = new javax.swing.JButton();
        jpesan = new javax.swing.JLabel();
        desktop = new javax.swing.JDesktopPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jdataMaster1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jdataMaster1MouseClicked(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(204, 255, 255));

        user1.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        user1.setText("User");

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/1493299630_profle.png"))); // NOI18N

        jButton4.setBackground(new java.awt.Color(204, 255, 255));
        jButton4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/1493298904_go-home.png"))); // NOI18N
        jButton4.setText("Home");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(204, 255, 255));
        jButton5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/SignOut-48.png"))); // NOI18N
        jButton5.setText("Logout");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jhak.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jhak.setText("Hak");

        jLabel2.setText("MAHAR CATUR FERNIZA");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton4)
                .addGap(18, 18, 18)
                .addComponent(jButton5)
                .addGap(316, 316, 316)
                .addComponent(jLabel2)
                .addGap(201, 201, 201)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jhak))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(user1)))
                .addGap(18, 18, 18)
                .addComponent(jLabel9))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton4)
                        .addComponent(jButton5))
                    .addComponent(jLabel9))
                .addGap(0, 2, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(user1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jhak)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jdataMaster1.addTab("Akses", jPanel1);

        jdatamaster.setBackground(new java.awt.Color(153, 255, 153));

        jUser.setBackground(new java.awt.Color(102, 255, 102));
        jUser.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/1493299630_profle.png"))); // NOI18N
        jUser.setText("User");
        jUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jUserActionPerformed(evt);
            }
        });

        jJCustomer.setBackground(new java.awt.Color(102, 255, 102));
        jJCustomer.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jJCustomer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/user3-48.png"))); // NOI18N
        jJCustomer.setText("Customer");
        jJCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jJCustomerActionPerformed(evt);
            }
        });

        jBajuAdat.setBackground(new java.awt.Color(102, 255, 102));
        jBajuAdat.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jBajuAdat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Clothing-Store-48.png"))); // NOI18N
        jBajuAdat.setText("Baju Adat");
        jBajuAdat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBajuAdatActionPerformed(evt);
            }
        });

        user3.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        user3.setText("User");

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/1493299630_profle.png"))); // NOI18N

        jhak1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jhak1.setText("Hak");

        javax.swing.GroupLayout jdatamasterLayout = new javax.swing.GroupLayout(jdatamaster);
        jdatamaster.setLayout(jdatamasterLayout);
        jdatamasterLayout.setHorizontalGroup(
            jdatamasterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jdatamasterLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jBajuAdat)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jJCustomer)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jUser)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jdatamasterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(user3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jhak1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addComponent(jLabel11))
        );
        jdatamasterLayout.setVerticalGroup(
            jdatamasterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jdatamasterLayout.createSequentialGroup()
                .addGroup(jdatamasterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jdatamasterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jBajuAdat)
                        .addComponent(jJCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jUser))
                    .addComponent(jLabel11)
                    .addGroup(jdatamasterLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(user3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jhak1)))
                .addGap(22, 22, 22))
        );

        jdataMaster1.addTab("Data Master", jdatamaster);

        jPanel3.setBackground(new java.awt.Color(153, 153, 255));

        user2.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        user2.setText("User");

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/1493299630_profle.png"))); // NOI18N

        jpenyewaan.setBackground(new java.awt.Color(102, 102, 255));
        jpenyewaan.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jpenyewaan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/contract-48.png"))); // NOI18N
        jpenyewaan.setText("Penyewaan");
        jpenyewaan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jpenyewaanActionPerformed(evt);
            }
        });

        jPengembalian.setBackground(new java.awt.Color(102, 102, 255));
        jPengembalian.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPengembalian.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/round-trip-return-repeat-48.png"))); // NOI18N
        jPengembalian.setText("Pengembalian");
        jPengembalian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPengembalianActionPerformed(evt);
            }
        });

        jhak2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jhak2.setText("Hak");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpenyewaan)
                .addGap(18, 18, 18)
                .addComponent(jPengembalian)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 825, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(user2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jhak2, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addComponent(jLabel10))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jpenyewaan)
                        .addComponent(jPengembalian))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(user2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jhak2))
                    .addComponent(jLabel10))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jdataMaster1.addTab("Transaksi", jPanel3);

        jPanel4.setBackground(new java.awt.Color(255, 102, 102));

        cblaporan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-- PILIH --", "Baju Adat", "Customer", "User", "Penyewaan", "Pengembalian" }));
        cblaporan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cblaporanActionPerformed(evt);
            }
        });

        btprint.setBackground(new java.awt.Color(255, 102, 102));
        btprint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/printer-orange-icon.png"))); // NOI18N
        btprint.setText("Print");
        btprint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btprintActionPerformed(evt);
            }
        });

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/1493299630_profle.png"))); // NOI18N

        user4.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        user4.setText("User");

        jhak3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jhak3.setText("Hak");

        jDateChooser1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "tanggal awal", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N
        jDateChooser1.setDateFormatString("yyyy-MM-dd");

        jDateChooser2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "tanggal Akhir", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N
        jDateChooser2.setDateFormatString("yyyy-MM-dd");

        lsampai.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lsampai.setText("-");

        btprint1.setBackground(new java.awt.Color(255, 102, 102));
        btprint1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/printer-orange-icon.png"))); // NOI18N
        btprint1.setText("Print");
        btprint1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btprint1ActionPerformed(evt);
            }
        });

        jpesan.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jpesan.setText("Maaf Anda tidak dapat melihat laporan");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(cblaporan, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lsampai)
                .addGap(18, 18, 18)
                .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btprint, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btprint1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jpesan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 140, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jhak3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(user4, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addComponent(jLabel12))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel12)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(user4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jhak3)
                .addGap(18, 18, 18))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cblaporan, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lsampai))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btprint1)
                            .addComponent(btprint, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jpesan))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jdataMaster1.addTab("Laporan", jPanel4);

        desktop.setBackground(new java.awt.Color(204, 255, 255));
        desktop.setLayout(new java.awt.CardLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/pakaian_adat_indonesia_by_canary7891.jpg"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1366, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 838, Short.MAX_VALUE)
        );

        desktop.add(jPanel2, "card2");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jdataMaster1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addComponent(desktop)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jdataMaster1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(desktop, javax.swing.GroupLayout.DEFAULT_SIZE, 817, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jPengembalianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPengembalianActionPerformed
        // TODO add your handling code here:
        desktop.setVisible(true);
        showpanel("e");
    }//GEN-LAST:event_jPengembalianActionPerformed

    private void jpenyewaanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jpenyewaanActionPerformed
        // TODO add your handling code here:
        desktop.setVisible(true);
        showpanel("d");
    }//GEN-LAST:event_jpenyewaanActionPerformed

    private void jBajuAdatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBajuAdatActionPerformed
        // TODO add your handling code here:
        desktop.setVisible(true);
        showpanel("a");
    }//GEN-LAST:event_jBajuAdatActionPerformed

    private void jJCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jJCustomerActionPerformed
        // TODO add your handling code here:
        desktop.setVisible(true);
        showpanel("b");
    }//GEN-LAST:event_jJCustomerActionPerformed

    private void jUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jUserActionPerformed
        // TODO add your handling code here:
        desktop.setVisible(true);
        showpanel("c");
    }//GEN-LAST:event_jUserActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:

        if ((JOptionPane.showConfirmDialog(null, " Anda Yakin Ingin Logout .." + "?", "Perhatian",
                JOptionPane.YES_NO_OPTION)) == 0) {

            new auth.AuthForm().setVisible(true);
            a.logout();

            dispose();
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        desktop.setVisible(true);
        showpanel("f");
    }//GEN-LAST:event_jButton4ActionPerformed

    private void btprintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btprintActionPerformed
        // TODO add your handling code here:
        desktop.setVisible(true);
        showpanel("l");
        try {
            String file = null;
            HashMap parameters = new HashMap();

            parameters = null;
            DateFormat date = new SimpleDateFormat("yyyy-MM-dd");
            if (cblaporan.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(null, "data yang akan di PRINT belum dipilih !");
            } else {
                if (cblaporan.getSelectedIndex() == 1) {
                    file = "BajuAdatReport";
                } else if (cblaporan.getSelectedIndex() == 2) {
                    file = "CustomerReport";
                } else if (cblaporan.getSelectedIndex() == 3) {
                    file = "UserReport";
                } else if (cblaporan.getSelectedIndex() == 4) {

                    file = "PenyewaanReport";
                    parameters.put("tglmulai", jDateChooser1.getDate()).toString();
                    parameters.put("tglakhir", jDateChooser2.getDate()).toString();
                } else if (cblaporan.getSelectedIndex() == 5) {
                    file = "PengembalianReport";
                }

                Koneksi kon = new Koneksi();
                File sumber = new File("src/report/" + file + ".jrxml");
                desaign = JRXmlLoader.load(sumber);
                report = JasperCompileManager.compileReport(desaign);
                print = JasperFillManager.fillReport(report, parameters, new KoneksiReport().getkon());

                JRViewer viewer = new JRViewer(print);
                viewer.setOpaque(true);
                viewer.setVisible(true);
                LaporanForm.scroolpanelaporan.add(viewer);
                LaporanForm.scroolpanelaporan.setViewportView(viewer);
                cblaporan.setSelectedIndex(0);
                jDateChooser1.setVisible(false);
                jDateChooser2.setVisible(false);
                lsampai.setVisible(false);
                btprint1.setVisible(false);
                btprint.setVisible(true);
            }
        } catch (JRException ex) {
            JOptionPane.showMessageDialog(null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LaporanForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btprintActionPerformed

    private void jdataMaster1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jdataMaster1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jdataMaster1MouseClicked

    private void cblaporanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cblaporanActionPerformed
        // TODO add your handling code here:
        if (cblaporan.getSelectedIndex() > 3) {
            jDateChooser1.setVisible(true);
            jDateChooser2.setVisible(true);
            lsampai.setVisible(true);
            btprint1.setVisible(true);
            btprint.setVisible(false);

        } else {
            jDateChooser1.setVisible(false);
            jDateChooser2.setVisible(false);
            lsampai.setVisible(false);
            btprint1.setVisible(false);
            btprint.setVisible(true);
        }

    }//GEN-LAST:event_cblaporanActionPerformed

    private void btprint1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btprint1ActionPerformed
        // TODO add your handling code here:
        desktop.setVisible(true);
        showpanel("l");
        try {
            if (jDateChooser2.getDate() == null || jDateChooser1.getDate() == null) {
                JOptionPane.showMessageDialog(null, "Data tidak isi dengan lengkap" + "\nTlong cek kembali");
            } else {
                JasperReport report;
                JasperPrint print;
                JasperDesign desaign;
                HashMap parameters = new HashMap(2);
                String file = "";
                DateFormat date = new SimpleDateFormat("yyyy-MM-dd");
                parameters.put("tglawal", String.valueOf(date.format(jDateChooser1.getDate())));
                parameters.put("tglakhir", String.valueOf(date.format(jDateChooser2.getDate())));

                if (cblaporan.getSelectedIndex() <= 3) {
                    JOptionPane.showMessageDialog(null, "data yang akan di PRINT belum dipilih !");
                } else {
                    if (cblaporan.getSelectedIndex() == 4) {
                        file = "PenyewaanReport";
                    } else {
                        file = "PengembalianReport";
                    }

                    Koneksi kon = new Koneksi();
                    File sumber = new File("src/report/" + file + ".jrxml");
                    desaign = JRXmlLoader.load(sumber.getPath());
                    report = JasperCompileManager.compileReport(desaign);
                    print = JasperFillManager.fillReport(report, parameters, new KoneksiReport().getkon());

                    JRViewer viewer = new JRViewer(print);
                    viewer.setOpaque(true);
                    viewer.setVisible(true);
                    LaporanForm.scroolpanelaporan.add(viewer);
                    LaporanForm.scroolpanelaporan.setViewportView(viewer);
                    cblaporan.setSelectedIndex(0);
                    jDateChooser1.setVisible(false);
                    jDateChooser2.setDate(null);
                    jDateChooser1.setDate(null);
                    jDateChooser2.setVisible(false);
                    lsampai.setVisible(false);
                    btprint1.setVisible(false);
                    btprint.setVisible(true);
                }
            }
        } catch (JRException ex) {
            JOptionPane.showMessageDialog(null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LaporanForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btprint1ActionPerformed

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
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        try {
            com.jtattoo.plaf.mcwin.McWinLookAndFeel.setTheme("Large-Font", "Java Swing", "");
            UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
            new Main().setVisible(true);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JButton btprint;
    public static javax.swing.JButton btprint1;
    public static javax.swing.JComboBox cblaporan;
    private javax.swing.JDesktopPane desktop;
    public static javax.swing.JButton jBajuAdat;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    public static com.toedter.calendar.JDateChooser jDateChooser1;
    public static com.toedter.calendar.JDateChooser jDateChooser2;
    public static javax.swing.JButton jJCustomer;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    public static javax.swing.JButton jPengembalian;
    public static javax.swing.JButton jUser;
    public static javax.swing.JTabbedPane jdataMaster1;
    public static javax.swing.JPanel jdatamaster;
    public static javax.swing.JLabel jhak;
    public static javax.swing.JLabel jhak1;
    public static javax.swing.JLabel jhak2;
    public static javax.swing.JLabel jhak3;
    public static javax.swing.JButton jpenyewaan;
    public static javax.swing.JLabel jpesan;
    private javax.swing.JLabel lsampai;
    public static javax.swing.JLabel user1;
    public static javax.swing.JLabel user2;
    public static javax.swing.JLabel user3;
    public static javax.swing.JLabel user4;
    // End of variables declaration//GEN-END:variables
}