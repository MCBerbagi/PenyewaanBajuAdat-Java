/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package penyewaanItem;

import bajuAdat.BajuAdat;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import penyewaan.*;

/**
 *
 * @author jneferboy
 */
public interface PenyewaanItemDAO {

    public List<BajuAdat> loadPakaianTradisional();

    public List<customer.Customer> loadCustomer();

    public void addItem(PenyewaanItem item);

    public void editItem(int index, PenyewaanItem item);

    public void deleteItem(int index);

    public double refreshTotal();

    public DefaultTableModel viewItem();

    public void insertPenyewaan(Penyewaan p);

    public void cetak(int id);

    public void reset();

  

}
