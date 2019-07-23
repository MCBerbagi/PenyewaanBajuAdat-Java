/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package penyewaan;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;
import customer.Customer;
import penyewaanItem.PenyewaanItem;

import user.User;

/**
 *
 * @author muhammad
 */
@DatabaseTable(tableName = "penyewaan")
public class Penyewaan {

    @DatabaseField(generatedId = true, columnName = "id")
    private int id_penyewaan;
    @DatabaseField(columnName = "tgl_sewa")
    private String tanggal;
    @DatabaseField(columnName = "Batas_kembali")
    private String tanggalBatasKembali;
    @DatabaseField(columnName = "total_harga")
    private double totalHarga;

    public String getTanggalBatasKembali() {
        return tanggalBatasKembali;
    }

    public void setTanggalBatasKembali(String tanggalBatasKembali) {
        this.tanggalBatasKembali = tanggalBatasKembali;
    }
    @DatabaseField
    private double dibayar;
    @DatabaseField
    private double kembali;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    @DatabaseField
    private String status;

    //class ini (pembelian) dimiliki oleh user
    @DatabaseField(foreign = true, foreignAutoRefresh = true, foreignColumnName = "KTP")
    private customer.Customer customer;
    @ForeignCollectionField
    ForeignCollection<PenyewaanItem> penyewaanItems;

    public ForeignCollection<PenyewaanItem> getPenyewaanItems() {
        return penyewaanItems;
    }

    public void setPenyewaanItems(ForeignCollection<PenyewaanItem> penyewaanItems) {
        this.penyewaanItems = penyewaanItems;
    }
    @DatabaseField(foreign = true, foreignAutoRefresh = true, foreignColumnName = "id")
    private User user;

    //class ini (pembelian) dimiliki oleh suplier
    public int getId_penyewaan() {
        return id_penyewaan;
    }

    public void setId_penyewaan(int id_penyewaan) {
        this.id_penyewaan = id_penyewaan;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public double getTotalHarga() {
        return totalHarga;
    }

    public void setTotalHarga(double totalHarga) {
        this.totalHarga = totalHarga;
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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
