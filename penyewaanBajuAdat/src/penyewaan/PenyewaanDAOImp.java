/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package penyewaan;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import database.Koneksi;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import bajuAdat.BajuAdatDAO;
import database.KoneksiReport;
import java.io.File;
import java.util.HashMap;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import penyewaan.Penyewaan;
import penyewaan.PenyewaanDAO;
import penyewaanItem.PenyewaanItem;
import penyewaanItem.PenyewaanItemDAOImp;

/**
 *
 * @author jneferboy
 */
public class PenyewaanDAOImp implements PenyewaanDAO {

    private Dao<Penyewaan, Integer> dao;
    private Dao<PenyewaanItem, Integer> daoItem;

    public PenyewaanDAOImp() {
        try {
            dao = DaoManager.createDao(Koneksi.cs(), Penyewaan.class);
            daoItem = DaoManager.createDao(Koneksi.cs(), PenyewaanItem.class);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    @Override
    public DefaultTableModel selectAll() {
        DefaultTableModel dtm;
        String[] title = {"ID", "Tanggal Sewa", "Batas Kembali", "User", "Customer", "Total Harga", "Dibayar", "Kembali", "Status"};
        dtm = new DefaultTableModel(null, title);
        try {
            List<Penyewaan> penyewaan = dao.queryForAll();

            for (Penyewaan p : penyewaan) {
                Object[] o = new Object[9];
                o[0] = p.getId_penyewaan();
                o[1] = p.getTanggal();
                o[2] = p.getTanggalBatasKembali();
                o[3] = p.getUser().getNama();
                o[4] = p.getCustomer().getNama();
                o[5] = p.getTotalHarga();
                o[6] = p.getDibayar();
                o[7] = p.getKembali();
                o[8] = p.getStatus();
                dtm.addRow(o);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PenyewaanDAOImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        //kembalikan hasil dtm
        return dtm;
    }

    @Override
    public DefaultTableModel search(String key) {
        DefaultTableModel dtm;
        String[] title = {"ID", "Tanggal Sewa", "Batas Kembali", "User", "Customer", "Total Harga", "Dibayar", "Kembali", "Status"};
        dtm = new DefaultTableModel(null, title);
        try {
            List<Penyewaan> penyewaan = dao.queryBuilder().where().
                    like("tgl_sewa", "%" + key + "%").query();
            for (Penyewaan p : penyewaan) {
                Object[] o = new Object[9];
                o[0] = p.getId_penyewaan();
                o[1] = p.getTanggal();
                o[2] = p.getTanggalBatasKembali();
                o[3] = p.getUser().getNama();
                o[4] = p.getCustomer().getNama();
                o[5] = p.getTotalHarga();
                o[6] = p.getDibayar();
                o[7] = p.getKembali();
                o[8] = p.getStatus();
                dtm.addRow(o);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PenyewaanDAOImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        //kembalikan hasil dtm
        return dtm;
    }

    @Override
    public DefaultTableModel detail(int id) {
        DefaultTableModel dtm;
        String judul[] = {"ID", "Nama Barang", "Harga Beli", "Qty", "Sub Total"};
        dtm = new DefaultTableModel(null, judul);
        try {
            List<PenyewaanItem> penyewaanItems = daoItem.queryForEq("penyewaan_id", id);
            for (PenyewaanItem p : penyewaanItems) {
                Object o[] = new Object[5];
                o[0] = p.getId();
                o[1] = p.getPakaiantradisional().getNama();
                o[2] = p.getPakaiantradisional().getHarga();
                o[3] = p.getJumlah_sewa();
                o[4] = p.getSubTotal();
                dtm.addRow(o);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PenyewaanDAOImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dtm;
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
            JasperViewer.viewReport(print , false);
        } catch (JRException ex) {
            Logger.getLogger(PenyewaanItemDAOImp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PenyewaanItemDAOImp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  

   

}
