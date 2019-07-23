/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bajuAdat;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import database.Koneksi;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import user.User;
import user.UserDAO;

/**
 *
 * @author Th3-TW1N5
 */
public class BajuAdatDAO implements database.DAO {

    //deklarasi class dao
    Dao<BajuAdat, Integer> dao;

    public BajuAdatDAO() {
        try {
            //hubungkan dao class dengan koneksi
            dao = DaoManager.createDao(Koneksi.cs(), BajuAdat.class);
        } catch (SQLException ex) {
            Logger.getLogger(BajuAdatDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void insert(Object o) {
        try {
            dao.create((BajuAdat) o);
            JOptionPane.showMessageDialog(null, "Tambah data berhasil !");
        } catch (SQLException ex) {
            Logger.getLogger(BajuAdatDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    @Override
    public void update(Object o) {
        try {
            dao.update((BajuAdat) o);
            JOptionPane.showMessageDialog(null, "Ubah data berhasil !");
        } catch (SQLException ex) {
            Logger.getLogger(BajuAdatDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(int id) {
        try {
            dao.deleteById(id);
            JOptionPane.showMessageDialog(null, "Hapus data berhasil !");
        } catch (SQLException ex) {
            Logger.getLogger(BajuAdatDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public DefaultTableModel selectAll() {
        DefaultTableModel dtm;
        String[] title = {"ID", "Nama Baju", "Harga", "Stok"};
        dtm = new DefaultTableModel(null, title);
        try {
            List<BajuAdat> baju = dao.queryForAll();
            for (BajuAdat b : baju) {
                Object[] o = new Object[4];
                o[0] = b.getIdbajuAdat();
                o[1] = b.getNama();
                o[2] = b.getHarga();
                o[3] = b.getStok();

                dtm.addRow(o);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BajuAdatDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        //kembalikan hasil dtm
        return dtm;
    }

    @Override
    public DefaultTableModel search(String key) {
        DefaultTableModel dtm;
        String[] title = {"ID", "Nama Baju", "Harga", "Stok"};
        dtm = new DefaultTableModel(null, title);
        try {
            List<BajuAdat> baju = dao.queryBuilder().where().
                    like("nama_paket", "%" + key + "%").query();
            for (BajuAdat b : baju) {
                Object[] o = new Object[6];
                o[0] = b.getIdbajuAdat();
                o[1] = b.getNama();
                o[2] = b.getHarga();
                o[3] = b.getStok();
                dtm.addRow(o);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BajuAdatDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        //kembalikan hasil dtm
        return dtm;
    }
}
