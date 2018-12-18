package cleverline.Tools;

import javax.swing.table.*;

public class RowSelectTableModel extends AbstractTableModel{

  private String columnName;
  private String[][] list;
  private boolean[] editAble;

  public RowSelectTableModel(String cn, boolean[] ea, String[][] ls){
  columnName=cn; list=ls; editAble=ea;
  }
  public void updateModel(String[][] ls){list=ls;
  }
  public int getColumnCount(){return 1;
  }
  public int getRowCount(){return list.length;
  }
  public String getColumnName(int col){return columnName;
  }
  public Object getValueAt(int row, int col){return list[row][col];
  }
  public Class getColumnClass(int c){return getValueAt(0, c).getClass();
  }
  public boolean isCellEditable(int row, int col){
  return editAble==null? false : editAble[col];
  }
  public void setValueAt(Object value, int row, int col ){
  list[row][col]=(String)value; fireTableCellUpdated(row, col);
  }
}
