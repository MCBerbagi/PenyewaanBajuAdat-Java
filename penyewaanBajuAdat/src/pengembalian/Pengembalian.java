/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pengembalian;

import penyewaan.*;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import user.User;

/**
 *
 * @author jneferboy
 */
@DatabaseTable(tableName = "pengembalian")
public class Pengembalian {

    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(columnName = "tgl_kembali")
    private String tanggalKembali;
    @DatabaseField
    private double denda;
    @DatabaseField
    private double dibayar;
    @DatabaseField
    private double kembali;

    //class ini (pembelian) dimiliki oleh upenyewaan
    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private Penyewaan penyewaan;

    //class ini (pembelian) dimiliki oleh suplier
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTanggalKembali() {
        return tanggalKembali;
    }

    public void setTanggalKembali(String tanggalKembali) {
        this.tanggalKembali = tanggalKembali;
    }

    public double getDenda() {
        return denda;
    }

    public void setDenda(double denda) {
        this.denda = denda;
    }

    public double getDibayar() {
        return dibayar;
    }

    public void setDibayar(double dibayar) {
        this.dibayar = dibayar;
    }

    public double getKembali() {
        return kembali;
    }

    public void setKembali(double kembali) {
        this.kembali = kembali;
    }

    public Penyewaan getPenyewaan() {
        return penyewaan;
    }

    public void setPenyewaan(Penyewaan penyewaan) {
        this.penyewaan = penyewaan;
    }

}
