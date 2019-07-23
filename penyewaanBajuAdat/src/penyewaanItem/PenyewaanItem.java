/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package penyewaanItem;

import bajuAdat.BajuAdat;
import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;
import penyewaan.Penyewaan;

/**
 *
 * @author jneferboy
 */
@DatabaseTable(tableName = "penyewaanitem")
public class PenyewaanItem {

    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField
    private int jumlah_sewa;
    @DatabaseField(columnName = "subTotal")
    private double subTotal;
    @DatabaseField(foreign = true, foreignAutoRefresh = true,foreignColumnName = "id")
    private BajuAdat pakaiantradisional;
    @DatabaseField(foreign = true, foreignAutoRefresh =  true,foreignColumnName = "id")
    private Penyewaan penyewaan;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getJumlah_sewa() {
        return jumlah_sewa;
    }

    public void setJumlah_sewa(int jumlah_sewa) {
        this.jumlah_sewa = jumlah_sewa;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public BajuAdat getPakaiantradisional() {
        return pakaiantradisional;
    }

    public void setPakaiantradisional(BajuAdat pakaiantradisional) {
        this.pakaiantradisional = pakaiantradisional;
    }

    

    public Penyewaan getPenyewaan() {
        return penyewaan;
    }

    public void setPenyewaan(Penyewaan penyewaan) {
        this.penyewaan = penyewaan;
    }

}
