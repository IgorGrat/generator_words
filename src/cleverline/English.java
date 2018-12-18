package cleverline;

import cleverline.Tools.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.im.InputContext;
import java.util.*;
import javax.swing.*;
import static cleverline.Tools.DrawingElement.*;

public class English extends FormPanel{

  private UnitTable[] table=new UnitTable[12];

  private class UnitTable extends CellTable{

    public UnitTable(int width, int high, int qu){super(width, high, qu);
    input.addKeyListener(new KeyAdapter(){
      public void keyReleased(KeyEvent e){
      String st=((JTextField)e.getSource()).getText().trim().toUpperCase();
      if(st.length()>2 && e.getKeyCode()!=KeyEvent.VK_ENTER){
      Toolkit.getDefaultToolkit().beep(); return;} fillValue(getValue(st));
      //sg=InputContext.getInstance().getLocale().getLanguage();
      //if(sg.charAt(0)=='u' && !st.matches("[1-9]+")){st="*"+st;}
      }});;
      input.addFocusListener(new FocusAdapter() {
      public void focusGained(FocusEvent e){JTextField field;
      field=(JTextField)e.getSource(); field.selectAll();
      }
      public void focusLost(FocusEvent e){
      fillValue(getValue(((JTextField)e.getSource()).getText().trim().toUpperCase()));
      }});
    }
  }
  public English(){super("lengvich.xls");
    setLayout(new BoxLayout(this, 0)); setAlignmentX(0);
    setBorder(BorderFactory.createTitledBorder(
    BorderFactory.createBevelBorder(1), "ѕанель языков"));
    JPanel inner=getPanel(0), name=getPanel(1);
    name.add(Box.createVerticalGlue());
    name.add(getLabelElem("Rusian", 0, red, 8, 3));
    name.add(getLabelElem("Ukrain", 0, blue, 8, 3));
    name.add(getLabelElem("English", 0,  black, 8, 3));
    name.add(getLabelElem("Num", 0,  grey, 8, 3)); inner.add(name);
    for(int i=0; i<table.length; i++){
    inner.add(table[i]=new UnitTable(5, 3, 4));
    }add(inner);
  }
  public void run(String pattern, JPanel conteiner){
    if(source.isEmpty()){conteiner.add(messageBD("по €зыкам")); return;
    }pattern=((pattern+="            ").substring(0, table.length)).toUpperCase();
    for(int i=0; i<table.length; i++){String st=pattern.substring(i, i+1);
    table[i].setText(st); table[i].fillValue(getValue(st) );
    }conteiner.add(this);
  }
  private String[] getValue(String val){int num, row=0;
  if(val.matches("[0-9]+") && (num=Integer.parseInt(val))<34 && num>0){row=num;}
  else if(val.length()>1 || val.isEmpty()){row=0;}
  else{short dd=(short)val.charAt(0);
   if(dd>1039 && dd<1073){row=dd-'ј'+1; if(dd>1045){row++;}}
   if(dd>64 && dd<91){row=dd-'A'+1;}
  }return source.get(row);
  }
}

