/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pengembalian;

import java.util.List;
import penyewaan.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jneferboy
 */
public interface PengembalianDAO {

//    public DefaultTableModel loadPenyewaan();
    public List<Penyewaan> loadPenyewaanbox();

    public DefaultTableModel selectAll();

    public DefaultTableModel detail(int id);

    public DefaultTableModel search(String key);

    public void insert(Object o);

    public void Updatepenye(Penyewaan p);
    public void cetak(String id);

    public void delete(int id);

}
