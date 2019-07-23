/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Koneksi {
//konfigurasi koneksi ke mysql server

    public static ConnectionSource cs() {

        String url = "jdbc:mysql://localhost:3306/penyewaanbajuadat";
        String username = "root";
        String passwd = "catur123";

        //inisiasi sumber koneksi
        ConnectionSource konek = null;
        try {
            konek = new JdbcConnectionSource(url, username, passwd);
        } catch (SQLException ex) {
            Logger.getLogger(Koneksi.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,"koneksi gagal");
        }
        //mengembalikan hasil koneksi
        return konek;

    }
    public static void main(String[] args) {
        Koneksi.cs();
    }

}
