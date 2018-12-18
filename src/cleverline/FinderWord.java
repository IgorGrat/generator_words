package cleverline;

import java.util.*;
import java.util.ArrayList;

public class FinderWord{


  private boolean stoped;
  private HashSet<String>result=new HashSet<String>();
  private String fmw="миниатюр";
  private byte limit=10;

  public FinderWord(){

  ArrayList<String[]>src=new streamingfile.Stream().getResult("ozhegov.xls", 
  null, false, null, true, null);
  char[] st=fmw.toCharArray(); Arrays.sort(st); fmw="";
  for(char ct: st){fmw+=ct;}


  for(String[] str : src){String mask=str[1], word=str[0]; 
  if(fmw.length()>mask.length() || mask.length()>limit){continue;} stoped=false;
  if(mask.indexOf(fmw)>-1){result.add(word); continue;}
  if(fmw.length()==mask.length()){continue;}
  new Cycle().run(mask, 0, (byte)(0));
  if(stoped){result.add(word);}
  }for(String row : result.toArray(new String[0])){System.out.println("row="+row);}
  }

  public class Cycle{
    public void run(String patern, int step, byte depth){--depth;
      for(int i=step; i<patern.length(); i++){
      String mask=patern.substring(0, i)+patern.substring(i+1);
      if(mask.indexOf(fmw)>-1){stoped=true; return;}
      else if(stoped){return;}
      else if(fmw.length()<mask.length()){new Cycle().run(mask, i, (byte)(depth));}
      }
    }
  }

public static void main(String[] args){new FinderWord();


  /*
  ArrayList<String[]>sort=new ArrayList<String[]>();
  for(String[] str : src){String val=str[0].toLowerCase();
  StringTokenizer stz=new StringTokenizer(val, "|");
  if(stz.countTokens()==0){continue;} val=stz.nextToken().trim();
  if(val.length()<3){continue;}
  if(!val.matches("[а-я]{"+String.valueOf(val.length())+"}")){continue;}
  String res=""; char[]srt=val.toCharArray(); Arrays.sort(srt);
  for(char c: srt){res+=c;} sort.add(new String[]{val, res});
  }new streamingfile.Stream().getResult("ozhegov.xls", true, sort);
  */




}

}

