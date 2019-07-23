/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bajuAdat;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "bajuAdat")
public class BajuAdat {

    @DatabaseField(generatedId = true, columnName = "id")
    int idbajuAdat;
    @DatabaseField(columnName = "nama_paket")
    String nama;
    @DatabaseField
    double harga;
    @DatabaseField
    int stok;
    @ForeignCollectionField
    ForeignCollection<penyewaanItem.PenyewaanItem> penyewaanItems;

    public int getIdbajuAdat() {
        return idbajuAdat;
    }

    public void setIdbajuAdat(int idbajuAdat) {
        this.idbajuAdat = idbajuAdat;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public double getHarga() {
        return harga;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }

    public int getStok() {
        return stok;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }

}
