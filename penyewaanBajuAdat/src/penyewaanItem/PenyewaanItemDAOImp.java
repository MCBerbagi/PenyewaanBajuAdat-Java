/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package penyewaanItem;

import bajuAdat.BajuAdat;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import customer.Customer;
import database.Koneksi;
import database.KoneksiReport;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
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
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import penyewaan.*;

/**
 *
 * @author jneferboy
 */
public class PenyewaanItemDAOImp implements PenyewaanItemDAO {

    private Dao<BajuAdat, Integer> daoPakaianTradisional;
    private Dao<Customer, Integer> daoCustomer;
    private Dao<Penyewaan, Integer> daoPenyewaan;
    private Dao<PenyewaanItem, Integer> daoPenyewaanItem;
//  LIST ITEM SEMENTARA
    private List<PenyewaanItem> items = new ArrayList<>();

    public PenyewaanItemDAOImp() {
        try {

            daoPakaianTradisional = DaoManager.createDao(Koneksi.cs(), BajuAdat.class);
            daoCustomer = DaoManager.createDao(Koneksi.cs(), Customer.class);
            daoPenyewaan = DaoManager.createDao(Koneksi.cs(), Penyewaan.class);
            daoPenyewaanItem = DaoManager.createDao(Koneksi.cs(), PenyewaanItem.class);
        } catch (SQLException ex) {
            Logger.getLogger(PenyewaanItemDAOImp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<BajuAdat> loadPakaianTradisional() {
        //initial data
        List<BajuAdat> bajuadats = null;
        try {
            bajuadats = daoPakaianTradisional.queryForAll();
        } catch (SQLException ex) {
            Logger.getLogger(PenyewaanItemDAOImp.class.getName()).log(Level.SEVERE, null, ex);
//            JOptionPane.showMessageDialog(null, ex);
        }
        return bajuadats;
    }

    @Override
    public void addItem(PenyewaanItem item) {
        items.add(item);
    }

    @Override
    public void deleteItem(int index) {
        items.remove(index);
       
    }

    @Override
    public double refreshTotal() {
        double total = 0;
        for (PenyewaanItem item : items) {
            total += item.getSubTotal();
        }
        return total;
    }

    @Override
    public DefaultTableModel viewItem() {
        DefaultTableModel dtm;
        String[] title = {"ID", "Baju Adat", "Harga Sewa", "Qty", "Sub Total"};
        dtm = new DefaultTableModel(null, title);

        for (PenyewaanItem item : items) {
            Object[] o = new Object[5];
            o[0] = item.getPakaiantradisional().getIdbajuAdat();
            o[1] = item.getPakaiantradisional().getNama();
            o[2] = item.getPakaiantradisional().getHarga();
            o[3] = item.getJumlah_sewa();
            o[4] = item.getSubTotal();
            dtm.addRow(o);
        }

        //kembalikan hasil dtm
        return dtm;
    }

    @Override
    public void reset() {
        items.removeAll(items);
    }

    @Override
    public void insertPenyewaan(Penyewaan p) {
        try {
            daoPenyewaan.create(p);

            for (PenyewaanItem i : items) {
                PenyewaanItem penyItem = new PenyewaanItem();
                //set penyewaan
                penyItem.setPenyewaan(p);
                penyItem.setJumlah_sewa(i.getJumlah_sewa());
                penyItem.setSubTotal(i.getSubTotal());
                //setbajuadat
                penyItem.setPakaiantradisional(i.getPakaiantradisional());

                daoPenyewaanItem.create(penyItem);
             
            }
            JOptionPane.showMessageDialog(null, "Transaksi penyewaan telah tersimpan");
        } catch (SQLException ex) {
            Logger.getLogger(PenyewaanItemDAOImp.class.getName()).log(Level.SEVERE, null, ex);
//            JOptionPane.showMessageDialog(null, ex);
            JOptionPane.showMessageDialog(null, "data belum lengkap");
        }
    }

    @Override
    public List<Customer> loadCustomer() {
        //initial data
        List<Customer> customer = null;
        try {
            customer = daoCustomer.queryForAll();
        } catch (SQLException ex) {
            Logger.getLogger(PenyewaanItemDAOImp.class.getName()).log(Level.SEVERE, null, ex);
//            JOptionPane.showMessageDialog(null, ex);
        }
        return customer;
    }

    @Override
    public void cetak(int id) {
        try {
            JasperReport report;
            JasperPrint print;
            JasperDesign desaign;
            HashMap parameters = new HashMap();
//            parameters.put("Ktp",id );
            Koneksi kon = new Koneksi();
            File sumber = new File("src/report/PenyewaanStruk.jrxml");
            desaign = JRXmlLoader.load(sumber);
            parameters.put("id", id);
            report = JasperCompileManager.compileReport(desaign);
            print = JasperFillManager.fillReport(report, parameters, new KoneksiReport().getkon());
            JasperViewer.viewReport(print, false);
        } catch (JRException ex) {
            Logger.getLogger(PenyewaanItemDAOImp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PenyewaanItemDAOImp.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void editItem(int index, PenyewaanItem item) {
        items.set(index, item);
    }

   
}
