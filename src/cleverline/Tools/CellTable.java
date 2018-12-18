package cleverline.Tools;

import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.*;

import static cleverline.Tools.DrawingElement.*;

public class CellTable extends JPanel{

  public static CellTable owner;
  protected JTextField input;
  private JLabel[] labelElements;

  public CellTable(int width, int high, int qu){
  setLayout(new BoxLayout(this, 1)); setAlignmentX(0);
  add(input=getTextFieldElem(0, width, high)); labelElements=new JLabel[qu];
  input.addFocusListener(new FocusAdapter(){
    public void focusGained(FocusEvent e){
    if(owner!=null){owner.setColorField(white);}    
    owner=CellTable.this; setColorField(yellow);
  }});
  for(int i=0; i<labelElements.length; i++){
  add(labelElements[i]=getLabelElem("", 0, yellow, 5, 3));}
  }
  public void setColorField(Color color){input.setBackground(color);
  }
  public void fillValue(String[] str){
  for(int i=0; i<labelElements.length; i++){labelElements[i].setText(str[i]);}
  }
  public void setText(String st){input.setText(st);
  }
  public void setFocusField(){input.requestFocus();}
}