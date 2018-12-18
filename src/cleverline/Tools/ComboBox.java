package cleverline.Tools;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import cleverline.*;

import static cleverline.Tools.DrawingElement.*;

public class ComboBox extends JPanel{

  protected String[][]list;
  protected ListSelectionModel selectModel;

  private int columnWidth;         
  private String columnName;     
  private int cellAligment;         
  private RowSelectTable tableList;  
  private JPanel datRow;              

  private JDialog popUpWindow;  
  private Border bord=Border.border;

  public ComboBox(String[][]listItem, int cw, int ca, String cn, String title){
  list=listItem; columnWidth=cw; cellAligment=ca;
  columnName=cn; setLayout(new BoxLayout(this,1)); setAlignmentX(0);
  datRow=new JPanel(); datRow.setLayout(new BoxLayout(datRow,0));
    JLabel datCell=new JLabel(); datCell.setForeground(Color.black);
    datCell.setFont(font); datCell.setBackground(white);
    datCell.setOpaque(true); datCell.setText(list[0][0]);
    datCell.setBorder(BorderFactory.createEtchedBorder());
    Dimension dimension=new Dimension(Math.round((float)columnWidth*
    ppc),Math.round((float)2.8*ppc));
    datCell.setPreferredSize(dimension); 
    datCell.setMaximumSize(datCell.getPreferredSize());
    datCell.setHorizontalAlignment(cellAligment); datRow.add(datCell);
    datRow.setMaximumSize(dimension); add(datRow); setOpaque(false);
  if(title!=null)setBorder(BorderFactory.createTitledBorder(title));
    addMouseListener(new MouseAdapter(){
      public void mouseReleased(MouseEvent e){
        if(list.length<2){return;} updatePopUpWindow();
    }});
  }
  protected void updatePopUpWindow(){
  createPopUpWindow(); tableList.updateModel(list);
  Container ow=datRow; int cnX=ow.getX(), cnY=ow.getY();
    do {ow=ow.getParent(); cnX+=ow.getX(); cnY+=ow.getY();}
    while(!(ow instanceof JFrame) && !(ow instanceof JDialog));
    popUpWindow.setLocation(cnX, cnY); popUpWindow.setVisible(true);
  }
  private void createPopUpWindow(){if(popUpWindow!=null){return;}
  createTableList(); JPanel windowPanel=new JPanel();
  popUpWindow=new JDialog(bord, true);
  popUpWindow.setUndecorated(true); popUpWindow.setContentPane
  (windowPanel); windowPanel.setLayout(new BoxLayout(windowPanel, 1));
  windowPanel.setBorder(BorderFactory.createMatteBorder(1,1,8,5,Color.gray));
  windowPanel.add(tableList.getScrollPane());
  popUpWindow.pack();
  popUpWindow.addWindowListener(new WindowAdapter(){
  public void windowActivated(WindowEvent e){
    tableList.requestFocus(); tableList.setScrollToVisible(0);
    }
  });
  }
  private void createTableList(){int minRow=5;
  tableList=new RowSelectTable(new RowSelectTableModel(columnName,
  null, list), columnWidth, cellAligment, false, minRow, 8);
  tableList.addKeyListener(new KeyAdapter(){
    public void keyPressed(KeyEvent e){int code=e.getKeyCode();
      if(code==KeyEvent.VK_ESCAPE){popUpWindow.setVisible(false);
    }
  }
    });
  }
}
