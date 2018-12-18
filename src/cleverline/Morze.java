package cleverline;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;

import static cleverline.Tools.DrawingElement.*;

public class Morze extends FormPanel{

private JTextField dashCell;
private String dash="2";
private JTextField pointCell;
private String point="1";
private JTextField separCell;
private HashMap<String, String[]>list=new HashMap();
private ArrayList<String[]>result=new ArrayList();
private JTextField input;
private JPanel resultPanel;

  public Morze(){super("morze.xls");
  setLayout(new BoxLayout(this, 0)); setAlignmentX(0);
  setBorder(BorderFactory.createTitledBorder(
  BorderFactory.createBevelBorder(0), "Панель азбуки морзе"));
  JPanel lev0=getPanel(1), lev1=getPanel(0), lev2; add(lev0); 
  lev0.setAlignmentY(0); lev0.add(lev1); lev1.add(lev2=getPanel(0));
  lev2.add(dashCell=getTextFieldElem(0, 3, 3)); dashCell.setText("-");
  lev2.setBorder(BorderFactory.createTitledBorder("тире"));
  lev1.add(lev2=getPanel(0));
  lev2.add(pointCell=getTextFieldElem(0, 3, 3)); pointCell.setText(".");
  lev2.setBorder(BorderFactory.createTitledBorder("тчк"));
  lev1.add(lev2=getPanel(0));
  lev2.add(separCell=getTextFieldElem(0, 3, 3));
  lev2.setBorder(BorderFactory.createTitledBorder("рзд"));
  lev0.add(lev1=getPanel(1)); lev1.add(getLabelElem("English", 0, black, 13, 3));
  lev1.add(getLabelElem("Russian", 0, red, 13, 3));
  lev1.add(getLabelElem("Morze  ", 0, yellow, 13, 3));
  ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  add(lev0=getPanel(1)); lev0.add(lev1=getPanel(0)); lev0.setAlignmentY(0);
  lev1.add(input=getTextFieldElem(0, 60, 3)); JButton but;
  lev1.setBorder(BorderFactory.createTitledBorder("строка ввода значения"));
  lev1.add(but=getButtonElem("искать", 0, grey, 6, 3));
  but.addActionListener(new ActionListener(){
    public void actionPerformed(ActionEvent e){makeProcedure();
    }});
  int width=Math.round(lev1.getPreferredSize().width/8); lev0.add(lev1=getPanel(0));
  JScrollPane scrollPane=getScrollPane(resultPanel=getPanel(0), width, 11);
  int polit=JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS;
  scrollPane.setHorizontalScrollBarPolicy(polit); lev1.add(scrollPane);
  resultPanel.setAlignmentY(0);
  }
  public void run(String pattern, JPanel cont){
  if(source.isEmpty()){cont.add(messageBD("морзе")); return;}
  setText(pattern); updateListVal(); cont.add(this);
  }
  public void setText(String pattern){input.setText(pattern.trim());
  }
  private void makeProcedure(){boolean letter=true; String row, sep,
  vl=""; if((row=input.getText().trim().toUpperCase()).isEmpty()){return;};
  StringTokenizer stz=new StringTokenizer(row,
  (sep=separCell.getText().trim()).isEmpty()? " " : sep); updateListVal();
  result.clear(); String[] st;
  if(row.indexOf(dash)==0 || row.indexOf(point)==0){letter=false;}
  while(stz.hasMoreTokens()){vl=stz.nextToken(); 
  for(int i=0; i<vl.length(); i++){
   if((st=list.get(vl.substring(i, !letter? vl.length() : (i+1))))==null){
   st=new String[]{"?","?","?"};} result.add(st); if(!letter){st[2]=vl; break;}}}
  fillResultPanel();
  }
  private void updateListVal(){
  String upDash=dashCell.getText().trim(), upPoint=pointCell.getText().trim();
  if(upDash.equals(dash) && upPoint.equals(point) ){return;} list.clear();
  for(String[] st : source){String result="";
  for(int i=2; i<st.length; i++){if(st[i].isEmpty()){break;} result+=st[i];}
  //  Так как я работаю с соурсом, менять надо новое значение со значением в соурсе
  // а это 1 и 2
  result=result.replaceAll("2", upDash).replaceAll("1", upPoint);
  String[] val=new String[]{st[0], st[1], result}; list.put(result, val);
  for(int i=0; i<2; i++){if(st[i].isEmpty()){continue;} list.put(st[i], val);}
  }dash=upDash; point=upPoint;
  }
  private void fillResultPanel(){resultPanel.removeAll();
    for(String[] st : result){JPanel pan=getPanel(1);
    pan.add(getLabelElem(st[0], 0, white, 6, 3));
    pan.add(getLabelElem(st[1], 0, white, 6, 3));
    pan.add(getLabelElem(st[2], 0, white, 6, 3));
    resultPanel.add(pan);} resultPanel.revalidate(); resultPanel.repaint();
  }
}