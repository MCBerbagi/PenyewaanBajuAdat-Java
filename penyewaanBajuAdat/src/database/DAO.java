
package database;
import javax.swing.table.DefaultTableModel;

public interface DAO {
  public void insert(Object o);
  public void update (Object o);
  public void delete(int id);
  public DefaultTableModel selectAll();
  public DefaultTableModel search(String key);
  
}
