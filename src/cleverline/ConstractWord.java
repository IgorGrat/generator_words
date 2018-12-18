package cleverline;

import java.util.*;

public class ConstractWord {

private HashSet<String>scope=new HashSet();


  public ConstractWord(){

    ArrayList<String[]>src=new streamingfile.Stream().getResult("ozhegov.xls",
    null, false, null, true, null); byte qu=5; String patern="";
    char[] st="тщпбеоруаикт".toCharArray(); Arrays.sort(st);
    for(char ct: st){patern+=ct;}
    new Cycle().run(patern, 0, (byte)(patern.length()-qu));
    for(String[] str : src){if(str[1].length()!=qu){continue;}
    if(scope.contains(str[1])){System.out.println("Правильное слово="+str[0]);}
    }
  }
  public class Cycle{
    public void run(String patern, int step, byte depth){--depth;
      for(int i=step; i<patern.length(); i++){
      String mask=patern.substring(0, i)+patern.substring(i+1);
      if(depth==0){scope.add(mask);}
      else{new Cycle().run(mask, i, (byte)(depth));}
      }
    }
  }
  public static void main(String[] args){new ConstractWord();
  }
}
