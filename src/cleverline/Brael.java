package cleverline;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import cleverline.Tools.*;
import static cleverline.Tools.DrawingElement.*;

public class Brael extends FormPanel{


  private HashMap<String, Integer> stockValue;
  private JRadioButton[] rbut=new JRadioButton[6];
  private UnitTable[] table=new UnitTable[12];
  private FormaBrael formaBrael;
  private int row;

  private class UnitTable extends CellTable{

    public UnitTable(int width, int high, int qu){
    super(width, high, qu); setAlignmentY(1);
    input.addMouseListener(new MouseAdapter(){
      public void mouseReleased(MouseEvent e){JTextField field;
      field=(JTextField)e.getSource(); field.selectAll(); setColorField(yellow);
      updateRow(field.getText().trim()); formaBrael.setMaskPicture();
      }});
    input.addKeyListener(new KeyAdapter(){
      public void keyReleased(KeyEvent e){String st;
      if((st=((JTextField)e.getSource()).getText().trim()).isEmpty()){return;}
      if(st.length()>2 && e.getKeyCode()!=KeyEvent.VK_ENTER){
      Toolkit.getDefaultToolkit().beep(); return;} updateRow(st);
      formaBrael.setMaskPicture(); fillValue(source.get(row));
      }});
      input.addFocusListener(new FocusAdapter() {

      public void focusLost(FocusEvent e){
      String st=((JTextField)e.getSource()).getText().trim();
      if(st.isEmpty()){updateRow(st); formaBrael.setMaskPicture();
      fillValue(source.get(row));}
      }});
    }
  }
  private class FormaBrael extends JPanel{
  
    public FormaBrael(){
    setLayout(new BoxLayout(this, 1)); setAlignmentX(0);
    JPanel in; JRadioButton button; setAlignmentY(1);
    setBorder(BorderFactory.createBevelBorder(0));
    for(int i=0; i<3; i++){add(in=getPanel(0)); in.setAlignmentX(0);
    for(int y=0; y<2; y++){in.add(rbut[2*i+y]=button=new JRadioButton());
    button.setOpaque(false);
    button.addActionListener(new ActionListener(){
    public void actionPerformed(ActionEvent e){row=0;
    for(int i=0; i<rbut.length; i++){
     if(rbut[i].isSelected()){row+=Math.pow(2, 5-i);}
    }UnitTable.owner.fillValue(source.get(row)); UnitTable.owner.setText("");
    }});}}
    }
    private void setMaskPicture(){
     for(int i=0; i<rbut.length; i++){rbut[i].setSelected((row>>5-i & 1)>0);}
    }
  }
  public Brael(){super("brael.xls");
  setLayout(new BoxLayout(this, 0)); setAlignmentX(0); setAlignmentY(1);
  JPanel panel=getPanel(1); panel.setAlignmentY(1);
  panel.add(getLabelElem("Rusian", 0, red, 8, 3));
  panel.add(getLabelElem("English", 0,  black, 8, 3));
  panel.add(getLabelElem("Num", 0,  grey, 8, 3)); add(panel);
  setBorder(BorderFactory.createTitledBorder(
  BorderFactory.createBevelBorder(1), "Панель Шрифта Брайля"));
  for(int i=0; i<table.length; i++){
  add(table[i]=new UnitTable(5, 3, 3));}add(Box.createHorizontalStrut(10));
  add(formaBrael=new FormaBrael());
  }
  public void run(String patt, JPanel cont){
  if(source.isEmpty()){cont.add(messageBD("по Брайлю")); return;}
  stockValue=new HashMap(); int y=-1; for(String[] src : source){++y;
   for(String str : src){if(str.equals("/")){continue;} stockValue.put(str, y);
   }
  }patt=(patt+="            ").substring(0, table.length);                               
  for(int i=0; i<table.length; i++){String mask=patt.substring(i, i+1); 
  table[i].setText(mask); updateRow(mask); table[i].fillValue(source.get(row));
  table[i].setColorField(white);} formaBrael.setMaskPicture();
  CellTable actual=UnitTable.owner=table[table.length-1]; actual.setFocusField();
  actual.setColorField(yellow); cont.add(this);
  }
  private void updateRow(String text){text=text.toUpperCase();
   Integer num=stockValue.get(text); row=num==null? 0 : num.intValue();}
}
