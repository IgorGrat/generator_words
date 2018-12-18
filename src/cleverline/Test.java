package cleverline;

import java.util.*;
import java.util.ArrayList;
import java.util.StringTokenizer;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class Test {

  public static void main(String[] args){
    for(int[] i : createSpiral(5, 5)){
      for(int j : i){System.out.print(j+"    ");}
        System.out.println("");
    }

  }
  private static int[][] createSpiral(int rows, int cols){
    if(rows<0 || cols<0){
      throw new IllegalArgumentException("value must be positive");
    }int[][] matrix=new int[rows][cols];
    int step=0;
    int num=0;
    do{
      int value=num;
      int maxDown=num+2*rows+cols-2+1;
      int left=num+2*rows+cols*2-4+1;
      int right=num+rows;
      for(int i=step; i<cols; i++){
        matrix[step][i]=++value;
        matrix[cols-1-step][i]=--maxDown;
      }for(int i=step+1; i<rows-1-step; i++){
        matrix[i][step]=--left;
        matrix[i][cols-1-step]=++right;
      }num+=(2*rows+2*cols-4); rows-=1; cols-=1; step++;
    }while(rows>0 || cols>0);
    return matrix;
  }
}
