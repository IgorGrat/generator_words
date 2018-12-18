package cleverline.Tools;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

public class DrawingElement {

  public static Font font=new Font("Serif", 1, 15);
  public static Color mustard=new Color(220,210,170), grey=Color.GRAY,
  lightGray=Color.lightGray, white=Color.WHITE, black=Color.black,
  brown=new Color(160, 0, 0), darkGrey=Color.DARK_GRAY, 
  yellow=Color.YELLOW, red=Color.red, blue=Color.BLUE;
  public static int ppc=8;

  public static Dimension getDimension(double width, double height){return
  new Dimension(Math.round((float)width*ppc),Math.round((float)height*ppc));
  }
  public static JButton getButtonElem(String name, int alig, Color col,  int x,
    int y){JButton but=new JButton(); but.setFont(font); but.setText(name);
    but.setBackground(col); but.setAlignmentX(0); but.setFocusPainted(false);
    Dimension dim=getDimension(name.length()*1.1+4, 2.8);
    but.setHorizontalAlignment(alig); but.setPreferredSize(dim);
    but.setMaximumSize(dim); but.setMinimumSize(dim);
    but.setBorder(BorderFactory.createBevelBorder(0)); return but;
  }
  public static JTextField getTextFieldElem(int alig, int x, int y){
    JTextField txtFild = new JTextField(); txtFild.setAlignmentX(0);
    txtFild.setHorizontalAlignment(alig); txtFild.setFont(font);
    txtFild.setBorder(BorderFactory.createLineBorder(darkGrey));
    Dimension dim=getDimension(x, y); txtFild.setPreferredSize(dim);
    txtFild.setMaximumSize(dim); txtFild.setMinimumSize(dim); return txtFild;
  }
  public static JLabel getLabelElem(String text,int alig, Color color, int x,
    int y){JLabel lab=new JLabel(); lab.setText(text); lab.setOpaque(true);
    lab.setAlignmentX(0); lab.setBackground(color); lab.setFont(font);
    lab.setHorizontalAlignment(alig); Dimension dim=getDimension(x, y);
    lab.setBorder(BorderFactory.createLineBorder(grey));
    lab.setPreferredSize(dim); lab.setMaximumSize(dim);
    lab.setMinimumSize(dim); return lab;
  }
  public static JCheckBox getPrefSwich(String title, Color color,
    boolean selected, float higth){JCheckBox swi=new JCheckBox(title);
    swi.setFont(font); swi.setOpaque(false); swi.setFocusPainted(false);
    swi.setSelected(selected); Dimension dm=swi.getPreferredSize();
    swi.setFocusable(false); dm.height=Math.round(ppc*higth);
    swi.setPreferredSize(dm); if(color!=null) swi.setForeground(color);
    return swi;
  }
  public static JPanel getRadioButtonChooser(ButtonGroup group,
  ActionListener listener, String[] titles, Color[] color, int orient, float higth){
  JPanel panel; JRadioButton button; Dimension dim; int y=0;
  panel=new JPanel(); panel.setLayout(new BoxLayout(panel, orient));
    for(; y<titles.length; y++){button=new JRadioButton(titles[y]); 
    if(color!=null && color[y]!=null) {button.setForeground(color[y]);}
    button.setFont(font); button.setOpaque(false); panel.add(button);
    dim=button.getPreferredSize(); dim.height=Math.round(higth*ppc);
    button.setPreferredSize(dim); button.addActionListener(listener);
    group.add(button); button.setFocusPainted(false);} panel.setOpaque(false); 
    panel.setAlignmentX(0); return panel;
  }
  public static JScrollPane getScrollPane(JPanel inner, int with, int hight){
  JScrollPane scrollPane=new JScrollPane(inner);
  scrollPane.setAlignmentX(0); Dimension dim=getDimension(with, hight);
  scrollPane.setPreferredSize(dim); scrollPane.setMinimumSize(dim);
  scrollPane.setMaximumSize(dim); return scrollPane;
  }
  public static JPanel messageBD(String text){JPanel pan=getPanel(0);
  pan.add(getLabelElem(("Отсутствует база данных "+text), 0, white, 80, 3));
  return pan;}
  public static JPanel getPanel(int poz){JPanel inner=new JPanel();
  inner.setOpaque(false); inner.setLayout(new BoxLayout(inner, poz));
  inner.setAlignmentX(0); return  inner;
  }
}
