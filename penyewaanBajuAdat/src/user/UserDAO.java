/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import database.Koneksi;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Th3-TW1N5
 */
public class UserDAO implements database.DAO{
 //deklarasi class dao
    Dao<User, Integer> dao;
     public UserDAO() {
        try {
            //hubungkan dao class dengan koneksi
            dao = DaoManager.createDao(Koneksi.cs(), User.class);
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public void insert(Object o) {
        try {
            dao.create((User) o);
            JOptionPane.showMessageDialog(null, "Tambah data berhasil !");
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Object o) {
         try {
            dao.update((User) o);
            JOptionPane.showMessageDialog(null, "Ubah data berhasil !");
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(int id) {
        try {
            dao.deleteById(id);
            JOptionPane.showMessageDialog(null, "Hapus Data Berhasil");
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
        }
    }


    @Override
    public DefaultTableModel selectAll() {
        DefaultTableModel dtm;
        String[] title = {"ID", "Nama", "Alamat","No HP","Username", "Password","Hak Akses"};
        dtm = new DefaultTableModel(null, title);
        try {
            List<User> user = dao.queryForAll();
            for (User u : user) {
                Object[] o = new Object[7];
                o[0] = u.getId();
                o[1] = u.getNama();
                o[2] = u.getAlamat();
                o[3] = u.getNohp();
                o[4] = u.getUsername();
                o[5] = u.getPassword();
                o[6] = u.getHakAkses();
               
                dtm.addRow(o);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        //kembalikan hasil dtm
        return dtm;
    }


    @Override
    public DefaultTableModel search(String key) {
       DefaultTableModel dtm;
        String[] title = {"ID", "Nama", "Alamat","No HP","Username", "Password","Hak Akses"};
        dtm = new DefaultTableModel(null, title);
        try {
            List<User> user = dao.queryBuilder().where().
                    like("nama", "%"+key+"%").query();
            for (User u : user) {
                Object[] o = new Object[7];
                o[0] = u.getId();
                o[1] = u.getNama();
                o[2] = u.getAlamat();
                o[3] = u.getNohp();
                o[4] = u.getUsername();
                o[5] = u.getPassword();
                o[6] = u.getHakAkses();
               
                dtm.addRow(o);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        //kembalikan hasil dtm
        return dtm;
    }

}
    

