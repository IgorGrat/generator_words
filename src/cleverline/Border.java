package cleverline;

import java.awt.event.*;
import javax.swing.*;

import static cleverline.Tools.DrawingElement.*;


public class Border extends JFrame{
  
  public static Border border;

  private String pattern;
  private JCheckBox[] ung;
  private FormPanel[] forms;
  private JTextField jtf;
  private JPanel main;
  
  public Border(){JPanel inner, owner=getPanel(1); int maxLenth=16;
  main=(JPanel)getContentPane(); main.setLayout(new BoxLayout(main, 1)); 
  pattern="NNNYNNNNN"; border=this; setResizable(false);

  /*приведение к единной длине всех названий*/
  String[] name=new String[]{
  "анаграмматор ул.",
  "анаграмматор общ",
  "шрифт Браеля",
  "морзянка",
  "англ.алфавит",
  "рус.алфавит",
  "укр.алфавит",
  "обратная раскл.",
  "семафорка"
  };
  forms=new FormPanel[name.length];
  forms[0]=new Anagrammator("st_kiev.xls", (byte)1);
  forms[1]=new Anagrammator("ozhegov.xls", (byte)2);
  forms[2]=new Brael();
  forms[3]=new Morze();
  forms[4]=new English();
  forms[5]=new Russian();
  forms[6]=new Ukraine();
  forms[7]=new Feedback();
  forms[8]=new Semofor();
  for(int y=0; y<name.length; y++){String mask="";
    for(int i=0; i<maxLenth; i++){mask+=" ";}
    name[y]=(name[y]+=mask).substring(0, maxLenth);
  }
  owner.add(switchPanel(5, name)); inner=getPanel(0);
  jtf=getTextFieldElem(0, 42, 3); inner.add(jtf);
  JButton but=getButtonElem("кнопка", 0, brown, 5, 10); inner.add(but);
  but.addActionListener(new ActionListener(){
    public void actionPerformed(ActionEvent e){String text;
    if((text=jtf.getText().trim()).isEmpty()){return;} JPanel input=getPanel(1);
    for(int i=0; i<ung.length; i++){
                                  if(ung[i].isSelected()){forms[i].run(text, input);}
    }
    if(input.getComponentCount()==0){return;} main.remove(1); main.add(input);
      pack(); setVisible(true); 
    }
  });
  inner.setBorder(BorderFactory.createTitledBorder
  (BorderFactory.createBevelBorder(0), "Чего изволите искать ?"));
  addWindowListener(new WindowAdapter(){
    public void windowClosing(WindowEvent e){dispose(); System.exit(0);
    }
  });
  owner.add(inner);  main.add(owner); main.add(getPanel(1));
  main.setBackground(white); main.setOpaque(true);
  setTitle("Найти код - легко, найти быстро - сложно");
  setResizable(false); pack(); setVisible(true);
  }
  private JPanel switchPanel(int maxVal, String[] name){int i=0;
    JPanel inner=getPanel(0), main=getPanel(1); main.add(inner);
    ung=new JCheckBox[name.length];
    for(int z=0; z<name.length; z++){i++;
      inner.add(Box.createHorizontalGlue());
      inner.add(ung[z]=getPrefSwich(name[z], black,
      pattern.getBytes()[z]=='Y', 5));  
      if(i==maxVal){i=0; main.add(inner=getPanel(0));}
    }
  return main;
  }
  public static void main(String[] args){new Border();
  }
}
