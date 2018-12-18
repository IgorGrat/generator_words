package cleverline;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

import static cleverline.Tools.DrawingElement.*;

public class Secret {
  private JDialog frames;
  private JPanel main;
  private JLabel find, finded;
  private JTextField inter, intered;
  private JButton clean;
  private String[] cif;
  private String[] word;
  private HashMap<String,String> finds;
  private  Clipboard clip;
  
  public Secret(){byte value=5; byte [] str=new byte[4] ;
    for(int y=0; y<4; y++){str[y]=(byte)(value>>3-y & 1);}
    find = getLabelElem("Введите цифры",0, lightGray, 200, 20);
    inter=getTextFieldElem(2, 200, 24);
    finded = getLabelElem("Результат", 0, lightGray, 200, 20);
    intered = getTextFieldElem(2, 200, 24);
    clean =getButtonElem("Очистить", 0, lightGray, 200,30);
    clean.addMouseListener(new MouseAdapter(){
      public void mousePressed(MouseEvent e)
      {clean.setBorder(BorderFactory.createBevelBorder(1));inter.setText("");
      intered.setText("");};
      public void mouseReleased(MouseEvent e) {
      main.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
      clean.setBorder(BorderFactory.createBevelBorder(0));}});
    main =new JPanel(); main.setLayout(new BoxLayout(main, 1));
    main.add(find); main.add(inter); main.add(finded);  main.add(intered);
    main.add(clean); frames = new JDialog(); frames.setContentPane(main);
    frames.setResizable(false); frames.setTitle("Секретный код");
    frames.pack(); frames.setVisible(true);
    frames.addWindowListener(new WindowAdapter(){
      public void windowClosing(WindowEvent e){System.exit(0);}});
    cif=new String[]{"1","2","3","4","5","6","7","8","9","10","11","12","13","14",
    "15","16","17","18","19","20","21","22","23","24","25","26"};
    word=new String[]{"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q",
    "r","s","t","u","v","w","x","y","z"}; finds = new HashMap();
    for (int i=0; i<cif.length; i++) {finds.put(cif[i], word[i]);}
      inter.addKeyListener(new KeyAdapter(){
      public void keyReleased(KeyEvent e){ ;
    main.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
    inter.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
    intered.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
    intered.setText(""); findWorld();}});
  }
  private void findWorld(){String s, finish ; StringTokenizer st; String[] fini;
  s =inter.getText(); st=new StringTokenizer(s, "\u0020");finish ="";
  fini= new String[st.countTokens()];  for (int i=0; i<fini.length; i++){
  fini[i]=st.nextToken(); fini[i]= finds.get(fini[i]); finish=finish+fini[i];}
  intered.setText(finish); clip = Toolkit.getDefaultToolkit().getSystemClipboard();
  clip.setContents(new StringSelection(intered.getText()), null);
  }
  }




