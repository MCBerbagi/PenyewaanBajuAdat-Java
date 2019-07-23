/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customer;

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

public class CustomerDAO implements DAO {

    //deklarasi class dao
    Dao<Customer, Long> dao;

    public CustomerDAO() {
        try {
            //hubungkan dao class dengan koneksi
            dao = DaoManager.createDao(Koneksi.cs(), Customer.class);
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void insert(Object o) {
        try {
            dao.create((Customer) o);
            JOptionPane.showMessageDialog(null, "Tambah data berhasil !");
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    @Override
    public void update(Object o) {
        try {
            dao.update((Customer) o);
            JOptionPane.showMessageDialog(null, "Ubah data berhasil !");
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    @Override
    public void delete(long id) {
        try {
            dao.deleteById(Long.valueOf(id));
            JOptionPane.showMessageDialog(null, "Hapus data berhasil !");
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    @Override
    public DefaultTableModel selectAll() {

        DefaultTableModel dtm;
        String[] title = {"KTP", "Nama ", "Alamat", "NO HP"};
        dtm = new DefaultTableModel(null, title);
        try {
            List<Customer> customer = dao.queryForAll();
            for (Customer b : customer) {
                Object[] o = new Object[4];
                o[0] = b.getKTP();
                o[1] = b.getNama();
                o[2] = b.getAlamat();
                o[3] = b.getNohp();

                dtm.addRow(o);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        //kembalikan hasil dtm
        return dtm;
    }

    @Override
    public DefaultTableModel search(String key) {
        DefaultTableModel dtm;
        String[] title = {"KTP", "Nama ", "Alamat", "NO HP"};
        dtm = new DefaultTableModel(null, title);
        try {
            List<Customer> customer = dao.queryBuilder().where().
                    like("nama", "%" + key + "%").query();

            for (Customer b : customer) {
                Object[] o = new Object[4];
                o[0] = b.getKTP();
                o[1] = b.getNama();
                o[2] = b.getAlamat();
                o[3] = b.getNohp();

                dtm.addRow(o);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        //kembalikan hasil dtm
        return dtm;
    }
}
