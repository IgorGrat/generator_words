package cleverline;

import javax.swing.*;
import java.util.ArrayList;

public abstract class FormPanel extends JPanel{

  protected ArrayList<String[]>source;
  protected  String[][] list;

  public FormPanel(String fileName){if(source==null){
  source=new streamingfile.Stream().
  getResult(fileName, "", false, null, true, null);}
  }
  public abstract void run(String pattern, JPanel conteiner);
}
