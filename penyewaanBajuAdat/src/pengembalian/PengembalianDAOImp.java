/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pengembalian;

import penyewaan.*;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import database.Koneksi;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import bajuAdat.BajuAdat;
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
import pengembalian.Pengembalian;
import pengembalian.PengembalianDAO;
import penyewaan.PenyewaanDAOImp;
import penyewaan.Penyewaan;
import penyewaanItem.PenyewaanItem;
import penyewaanItem.PenyewaanItemDAOImp;

/**
 *
 * @author jneferboy
 */
public class PengembalianDAOImp implements PengembalianDAO {

    private Dao<Pengembalian, Integer> dao;
    private Dao<PenyewaanItem, Integer> daoItem;
    private Dao<Penyewaan, Integer> daoPenyewaan;
    private Dao<BajuAdat, Integer> daobaju;
    Penyewaan penyewaan;

    public PengembalianDAOImp() {
        try {
            dao = DaoManager.createDao(Koneksi.cs(), Pengembalian.class);
            daoItem = DaoManager.createDao(Koneksi.cs(), PenyewaanItem.class);
            daoPenyewaan = DaoManager.createDao(Koneksi.cs(), Penyewaan.class);
            daobaju = DaoManager.createDao(Koneksi.cs(), BajuAdat.class);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    @Override
    public DefaultTableModel search(String key) {
        DefaultTableModel dtm;
        String[] title = {"ID", "Id penyewaan", "Batas Kembali", "Tanggal Kembali", "User", "Customer", "Denda", "Dibayar", "Kembali", "Status"};
        dtm = new DefaultTableModel(null, title);
        try {
            List<Pengembalian> pengembalian = dao.queryBuilder().where().
                    like("tgl_kembali", "%" + key + "%").query();

            for (Pengembalian p : pengembalian) {
                Object[] o = new Object[10];
                o[0] = p.getId();
                o[1] = p.getPenyewaan().getId_penyewaan();
                o[2] = p.getPenyewaan().getTanggalBatasKembali();
                o[3] = p.getTanggalKembali();
                o[4] = p.getPenyewaan().getUser().getNama();
                o[5] = p.getPenyewaan().getCustomer().getNama();
                o[6] = p.getDenda();
                o[7] = p.getDibayar();
                o[8] = p.getKembali();
                o[9] = p.getPenyewaan().getStatus();

                dtm.addRow(o);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PengembalianDAOImp.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
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
            Logger.getLogger(PengembalianDAOImp.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
        }
        return dtm;
    }

    @Override
    public void insert(Object o) {
        try {
            dao.create((Pengembalian) o);

            JOptionPane.showMessageDialog(null, "Pengembalian Pakaian Tradisional berhasil !");
        } catch (SQLException ex) {
            Logger.getLogger(PengembalianDAOImp.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);

        }
    }

    @Override
    public DefaultTableModel selectAll() {
        DefaultTableModel dtm;
        String[] title = {"ID", "Id penyewaan", "Batas Kembali", "Tanggal Kembali", "User", "Customer", "Denda", "Dibayar", "Kembali", "Status"};
        dtm = new DefaultTableModel(null, title);
        try {
            List<Pengembalian> pengembalian = dao.queryForAll();

            for (Pengembalian p : pengembalian) {
                Object[] o = new Object[10];
                o[0] = p.getId();
                o[1] = p.getPenyewaan().getId_penyewaan();
                o[2] = p.getPenyewaan().getTanggalBatasKembali();
                o[3] = p.getTanggalKembali();
                o[4] = p.getPenyewaan().getUser().getNama();
                o[5] = p.getPenyewaan().getCustomer().getNama();
                o[6] = p.getDenda();
                o[7] = p.getDibayar();
                o[8] = p.getKembali();
                o[9] = p.getPenyewaan().getStatus();

                dtm.addRow(o);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PengembalianDAOImp.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
        }
        //kembalikan hasil dtm
        return dtm;
    }

    @Override
    public void Updatepenye(Penyewaan p) {
        try {
//            daoPenyewaan.update((Penyewaan) p);
            daoPenyewaan.update(p);

        } catch (SQLException ex) {
            Logger.getLogger(PengembalianDAOImp.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    @Override
    public List<Penyewaan> loadPenyewaanbox() {
        List<Penyewaan> penyewaans = null;
        try {
            penyewaans = daoPenyewaan.queryBuilder().where().
                    like("status", "%" + "Belum Kembali" + "%").query();

        } catch (SQLException ex) {
            Logger.getLogger(PenyewaanItemDAOImp.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
        }
        return penyewaans;
    }

    @Override
    public void delete(int id) {
        try {
            dao.deleteById(id);
            JOptionPane.showMessageDialog(null, "data berhasil di hapus");
        } catch (SQLException ex) {
            Logger.getLogger(PengembalianDAOImp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void cetak(String id) {
         try {
            JasperReport report;
            JasperPrint print;
            JasperDesign desaign;
            HashMap parameters = new HashMap();
//            parameters.put("Ktp",id );
            Koneksi kon = new Koneksi();
            File sumber = new File("src/report/PengembalianStruk.jrxml");
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
