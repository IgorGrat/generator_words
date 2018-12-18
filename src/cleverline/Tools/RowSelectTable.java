package cleverline.Tools;

import javax.swing.*;
import javax.swing.table.*;
import javax.swing.event.*;
import java.awt.*;

import cleverline.*;
import static cleverline.Tools.DrawingElement.*;

public class RowSelectTable extends JTable implements TableModelListener{

  private JScrollPane scrollPane;
  private JViewport viewport;
  private RowSelectTableModel model;
  private int aligment;
  private Border bord=Border.border;

// ���������� ����� ������� ��� ����������� ����� � ����������� ��������� ������
  private class TableCellRenderer extends DefaultTableCellRenderer{

    public TableCellRenderer(int col){
    setHorizontalAlignment(aligment);
    }
    public Component getTableCellRendererComponent(JTable table,
    Object value, boolean isSelected, boolean hasFocus, int row, int col){
    Color fgrd, bgrd;
    if((fgrd=isLightValue(row, col))==null){fgrd=table.getForeground();}
    if((bgrd=isLightFone(row, col))==null){bgrd=table.getBackground();}
    setForeground(fgrd); setBackground(bgrd);
    super.getTableCellRendererComponent(table, changeValue(col, value),
    isSelected(col, isSelected), hasFocus, row, col); return this;
    }}
/*****************   ������� ��������������� ������������   *******************/
// ����� ����� �������������� � ������� ��������� ������ �������. ��������� ���-
// ��� ���������. �� ��������� ������ �� ������, ���������� null.
  protected Color isLightValue(int row, int col){return null;
  } // ����, ��� � ���� ������ ��������� ������ ���� ������
  protected Color isLightFone(int row, int col){return null;
  }// ����� ��������� �������� �������� � ���� ������� ������������� ��������
  protected Object changeValue(int col, Object value){return value;
  }// ����� ��������� �������� �������� �������� ��������� �������
  protected boolean isSelected(int col, boolean selected){
  return selected;
  }// ����� ��� ���������������. ������� ���������� �� ������������, ��.�����.
// ������������ ����� ������ ��������� ������������ � �������� ��������� ����-
//  ������� ����� "TextFieldCellEditor". ������ ���������� ��������� ����������-
// ���� ��������� �������� � ��� ��������. ������� ��. � ���������� "Purchasing",
  protected TableCellEditor getEditor(int column){return null;
  }//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  protected javax.swing.table.TableCellRenderer getRenderer(int col){
  return new TableCellRenderer(col);
  }//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

  public RowSelectTable(RowSelectTableModel model, int columnWidth,
  int alig, boolean isl, int min, int max){super(model); aligment=alig;
  this.model=model; getTableHeader().setFont(font); setFont(font);
  setAutoResizeMode(4); // AUTO_RESIZE_ALL_COLUMNS
  setSelectionMode(0); TableColumnModel cm=getColumnModel();
   
  TableColumn col=cm.getColumn(0); col.setCellRenderer(getRenderer(0));
  if(model.isCellEditable(0, 0)){col.setCellEditor(getEditor(0));}
  col.setPreferredWidth(Math.round(ppc*(float)columnWidth));
  col.setMaxWidth(col.getPreferredWidth());
  col.setMinWidth(col.getPreferredWidth());
    
  int tabWidth=cm.getTotalColumnWidth(); max=Math.min(getRowCount(), max);
  max=Math.max(max, min); setRowHeight(Math.round(ppc*2.3f));
  setPreferredScrollableViewportSize(new Dimension(tabWidth, getRowHeight()*max));
  scrollPane=new JScrollPane(this, isl? 22 : 20, 30); scrollPane.setAlignmentX(0);
  // ���������� ���� �������� ������ ����� ������� �� "ENTER"
  viewport=scrollPane.getViewport(); viewport.setBackground(Color.white);
  }
  public void updateModel(String[][] ls){
  model.updateModel(ls); model.fireTableDataChanged();
  }
  public JScrollPane getScrollPane(){return scrollPane;
  }
  public void setScrollToVisible(int selRow){// selectRow � ������� ���������
  Rectangle cellRect=getCellRect(selRow, 0, true);
  Point pt=viewport.getViewPosition();
  cellRect.setLocation(cellRect.x-pt.x, cellRect.y-pt.y);
  viewport.scrollRectToVisible(cellRect);
  }
}




