/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customer;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;
import penyewaan.Penyewaan;

@DatabaseTable(tableName = "Customer")
public class Customer {

    @DatabaseField(generatedId = true, allowGeneratedIdInsert = true)
    long KTP;
    @DatabaseField
    String nama;
    @DatabaseField
    String alamat;
    @DatabaseField
    String nohp;
    @ForeignCollectionField
    ForeignCollection<Penyewaan> penyewaans;

    public long getKTP() {
        return KTP;
    }

    public void setKTP(long KTP) {
        this.KTP = KTP;
    }

   

   

    public ForeignCollection<Penyewaan> getPenyewaans() {
        return penyewaans;
    }

    public void setPenyewaans(ForeignCollection<Penyewaan> penyewaans) {
        this.penyewaans = penyewaans;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNohp() {
        return nohp;
    }

    public void setNohp(String nohp) {
        this.nohp = nohp;
    }

}
