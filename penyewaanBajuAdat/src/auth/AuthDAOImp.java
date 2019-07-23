/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auth;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import database.Koneksi;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import user.User;

/**
 *
 * @author jneferboy
 */
public class AuthDAOImp implements AuthDAO {

    private Dao<User, Integer> dao;

    public AuthDAOImp() {
        try {
            //hubungkan cs dengan dao
            dao = DaoManager.createDao(Koneksi.cs(), User.class);
        } catch (SQLException ex) {
            Logger.getLogger(AuthDAOImp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void login(String username, String password, String HakAkses) {
        try {
            User user = dao.queryBuilder().where()
                    .eq("username", username)
                    .and()
                    .eq("password", password)
                    .and()
                    .eq("HakAkses", HakAkses).queryForFirst();

            if (user != null) {
                Auth.ID = user.getId();
                Auth.NAMA = user.getNama();
                Auth.HakAkses = user.getHakAkses();
                Auth.AUTH = true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }

    @Override
    public void logout() {
        try {
            Auth.ID = 0;
            Auth.NAMA = null;
            Auth.HakAkses = null;
            Auth.AUTH = false;
        } catch (Exception e) {

        }
    }

}
