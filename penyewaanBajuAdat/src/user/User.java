/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;
import penyewaan.Penyewaan;

/**
 *
 * @author Th3-TW1N5
 */
@DatabaseTable(tableName = "user")
public class User {

    @DatabaseField(generatedId = true)
    int id;
    @DatabaseField
    String nama;
    @DatabaseField
    String Alamat;
    @DatabaseField
    String nohp;
    @DatabaseField
    String username;
    @DatabaseField
    String password;
    @DatabaseField
    String HakAkses;

    public String getHakAkses() {
        return HakAkses;
    }

    public void setHakAkses(String HakAkses) {
        this.HakAkses = HakAkses;
    }
    @ForeignCollectionField
    ForeignCollection<penyewaan.Penyewaan> penyewaans;

    public ForeignCollection<Penyewaan> getPenyewaans() {
        return penyewaans;
    }

    public void setPenyewaans(ForeignCollection<Penyewaan> penyewaans) {
        this.penyewaans = penyewaans;
    }

    public String getAlamat() {
        return Alamat;
    }

    public void setAlamat(String Alamat) {
        this.Alamat = Alamat;
    }

    public String getNohp() {
        return nohp;
    }

    public void setNohp(String nohp) {
        this.nohp = nohp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
