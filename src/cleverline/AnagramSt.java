package cleverline;

import java.util.*;
import javax.swing.*;

public class AnagramSt extends FormPanel{

  private HashMap<String, ArrayList<String>> result;
  private ArrayList<String> level;
  private String[][] depository;

  public AnagramSt(){super("text.txt");
  }
  public void run(String pattern, JPanel conteiner){};
  private void run(String pattern, int sp, String[] line, byte depth){String val;
  /* rest - патерн */
  /* step - сдвиг вырезки текста по мере вызова последующих методов  */
  /* line - исходное слово */
  /* depth - количество вырезаемых букв */
    if(--depth<0){return;}//Вырезка дальше не нужна//////////////////////////////////
    for(; sp<line[1].length(); sp++){
      val=line[1].substring(0, sp)+line[1].substring(sp+1);
      if(val.length()==pattern.length() && val.equals(pattern)){
        ArrayList<String>rs; String item=String.valueOf(depth);
        if((rs=result.get(depth))==null){result.put(item, rs=new ArrayList());
          level.add(item);
        }rs.add(line[0]);
      }
      else{run(pattern, sp, line, depth);}
    }
  }
  private void subStrPatern(){
  
  }
}
