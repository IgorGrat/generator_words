package cleverline;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;
import static cleverline.Tools.DrawingElement.*;

public class Anagrammator extends FormPanel{
// Для работы со списком по улицам, должна соблюдаться такая последовательность:
// 1.Русское название улицы.
// 2. Русское название улицы все буквы идут в алфавитном порядке
// 3.Украинское название улицы.
// 4. Украинское название улицы все буквы идут в алфавитном порядке
// 5-.....Номера домов.
// Для работы со списком слов, смотри выше два первых пункта:
  private byte type;
  private AnagramWindow firstLevel;
  private AnagramWindow secondLevel;
  private PattWindow pattWindow;
  private NumPattWindow numPattWindow;
  private ButtonGroup group=new ButtonGroup();
  private int leng=0;
  private static boolean stoped=false;

  private class Window extends JPanel{

    public String parent="";
    protected String text="";
    protected ArrayList<String[]>sourceList=new ArrayList();
    protected ArrayList<String[]>resultList=new ArrayList();
    protected ArrayList<Window>liseners=new ArrayList();
    protected byte limit;
    private JTextField input, maxLenth;
    private JPanel resultPanel;

    public void setSourceList(ArrayList<String[]>src){sourceList=src;
    }
    public ArrayList<String[]>getResultList(){return resultList;
    }
    public void addListener(Window windows){liseners.add(windows);
    }
    public void clearResultPanel(){resultList.clear(); fillResultPanel();
    }
    public void setInputValue(String st){input.setText(st);
    }
    protected String getInputText(boolean abc){limit=40; String text, limitLen;
    text=input.getText().trim().toLowerCase(); text+=parent;
    limitLen=maxLenth.getText().trim();
    if(limitLen.matches("[1-9]{1}[0-9]?")){limit=Byte.parseByte(limitLen);}
    if(!abc){char[] st=text.toCharArray(); Arrays.sort(st); text=""; 
    for(char ct: st){text+=ct;}}
    return text;
    }
    private void makeProcedure(){getResult(); fillResultPanel();
    eventProcedure();}
    protected void eventProcedure(){
    }
    protected void algoritm(String[] st){
    }
    private void getResult(){text=getInputText(false);
    if(text.isEmpty()){return;} resultList.clear(); long start=System.nanoTime();
    for(String[] str : sourceList){algoritm(str);} System.out.println((System.nanoTime()-start)/1e6f);
    }
    private void fillResultPanel(){resultPanel.removeAll();
    for(String[] tt : resultList){JPanel pan=getPanel(0);
    pan.setBorder(BorderFactory.createLineBorder(grey));
    pan.add(getLabelElem(" "+tt[0], 2, white, 24, 3)); resultPanel.add(pan);}
    resultPanel.revalidate(); resultPanel.repaint();
    }
    public Window(){setLayout(new BoxLayout(this, 1)); setAlignmentX(0);
    JPanel panel=getPanel(0), inner=getPanel(0); add(panel); JButton but;
    inner.add(input=getTextFieldElem(0, 10, 3));
    inner.setBorder(BorderFactory.createBevelBorder(1)); panel.add(inner);
    panel.add(Box.createHorizontalStrut(4));
    panel.add(maxLenth=getTextFieldElem(0, 3, 3)); maxLenth.setText("15");
    panel.add(Box.createHorizontalStrut(4));
    panel.add(but=getButtonElem("искать", 0, grey, 6, 3)); resultPanel=getPanel(1);
    but.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e){makeProcedure();
    }});
    add(getScrollPane(resultPanel, 25, 40));
    }
  }
  private class AnagramWindow extends Window{
    
    /*private boolean stoped=false;
    private class Cycle{
    public void run(String patern, String key, int step, byte depth){--depth;
    for(int i=step; i<patern.length(); i++){
    String mask=patern.substring(0, i)+patern.substring(i+1);
    if(mask.indexOf(key)>-1){stoped=true; return;}
    else if(stoped){return;}
    else if(key.length()<mask.length()){new Cycle().run(mask, key, i,
    (byte)(depth));}
    }}}*/
    protected void algoritm(String[] str){
    String word=str[0+leng*2], mask=str[1+leng*2];
    if(text.length()>mask.length() || mask.length()>limit){return;} stoped=false;
    if(mask.indexOf(text)>-1){resultList.add(new String[]{word, mask}); return;}
    if(text.length()==mask.length()){return;} run(mask, text, 0, (byte)(0));
    // new Cycle().run(mask, text, 0, (byte)(0));
    if(stoped){resultList.add(new String[]{word, mask});}
    }
    protected void eventProcedure(){
    for(Window win : liseners){win.setSourceList(getResultList());
    win.parent=text; win.clearResultPanel();
    }}
  }
  private class PattWindow extends Window{
    protected void algoritm(String[] str){String word=str[0], mask=str[1];
    if(mask.length()>word.length() || mask.length()>limit){return;}
    if(word.indexOf(text)>-1){resultList.add(str);}
    }
    protected String getInputText(boolean abc){return super.getInputText(true);}
  }
  private class NumPattWindow extends Window{
    protected void algoritm(String[] str){
    for(int i=4; i<str.length; i++){if(text.equals(str[i])){resultList.add(str);}}
    }
    protected String getInputText(boolean abc){return super.getInputText(true);}
  }
  public Anagrammator(String file, byte tp){super(file); type=tp;
  setLayout(new BoxLayout(this, 1)); setAlignmentX(0); JPanel panel, inner;
  setBorder(BorderFactory.createTitledBorder(
  BorderFactory.createBevelBorder(1), ("Панель поиска "+(type==1?
  "улиц" : "слов")+" по метке") )); add(panel=getPanel(0)); 
  panel.add(getRadioButtonChooser(group, new ActionListener() {
    public void actionPerformed(ActionEvent e){
    Enumeration<AbstractButton>enumer=group.getElements(); leng=-1;
    while(enumer.hasMoreElements()){leng++;
      if(enumer.nextElement().isSelected()){break;}}
    }}, new String[]{"Russian", "Ukrainian"}, new Color[]{red, blue}, 2, 0));
  Enumeration<AbstractButton>enumer=group.getElements(); int i=-1;
  while(enumer.hasMoreElements()){AbstractButton ab; i++;
    ab=enumer.nextElement(); if(type==2){ab.setEnabled(false);}
    if(i==leng){ab.setSelected(true); /*break;*/}
  }panel.add(inner=getPanel(0)); JTextField field;
  inner.add(field=getTextFieldElem(0, 10, 3)); field.setText("онмурт");
  field.setEditable(false); String text="маска для поиска слов";
  inner.add(getLabelElem(text, 0, lightGray, 21, 3));
  inner.add(field=getTextFieldElem(0, 3, 3)); field.setText("15");
  field.setEditable(false); text=" максимальная длина слова";
  inner.add(getLabelElem(text, 0, lightGray, 24, 3)); add(panel=getPanel(0));
  panel.add(inner=getPanel(0));
  inner.add(firstLevel=new AnagramWindow()); firstLevel.setSourceList(source);
  inner.add(Box.createHorizontalStrut(5));
  inner.add(secondLevel=new AnagramWindow()); firstLevel.addListener(secondLevel);
  inner.setBorder(BorderFactory.createTitledBorder(
  BorderFactory.createBevelBorder(0),
  "Основное и остаточное анаграммирование слова"));
  panel.add(Box.createHorizontalStrut(5)); panel.add(pattWindow=new PattWindow());
  pattWindow.setBorder(BorderFactory.createTitledBorder(
  BorderFactory.createBevelBorder(0), "Совпадение части в слове"));
  pattWindow.setSourceList(source);
   if(type==1){panel.add(Box.createHorizontalStrut(5));
   panel.add(numPattWindow=new NumPattWindow());
   numPattWindow.setSourceList(source);
   numPattWindow.setBorder(BorderFactory.createTitledBorder(
   BorderFactory.createBevelBorder(0), "Улицы с номером дома"));
   }
  }
  public void run(String pattern, JPanel cont){
  if(source.isEmpty()){cont.add(messageBD("по "+(type==1?"словам" : "улицам")));
  return;} firstLevel.setInputValue(pattern); pattWindow.setInputValue(pattern);
  cont.add(this);
  }
  public static void run(String patern, String key, int step, byte depth){--depth;
    for(int i=step; i<patern.length(); i++){
    String mask=patern.substring(0, i)+patern.substring(i+1);
    if(mask.indexOf(key)>-1){stoped=true; return;}
    else if(stoped){return;}
    else if(key.length()<mask.length()){run(mask, key, i, (byte)(depth));}
  }}
}
