/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customer;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Th3-TW1N5
 */
public interface DAO {

    public void insert(Object o);

    public void update(Object o);

    public void delete(long id);

    public DefaultTableModel selectAll();

    public DefaultTableModel search(String key);
}
