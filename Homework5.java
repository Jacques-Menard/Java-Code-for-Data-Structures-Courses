/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw5;

import static java.lang.Math.abs;

/**
 *
 * @author Jacques Menard
 */
public class Homework5 {
    public static int recMultiply(int m, int n){
    if(n== 0){
      return 0;
    }
    if(n > 0 ){
      return (m + recMultiply(m, n-1));
    }
    if(n < 0 ){
      return -recMultiply(m, -n);
    }
    return 0;
    }
    public static void printRepeats(int[] arr){     
        
        System.out.printf("Reapeating elements: ");
        for (int count = 0; count < arr.length; count++){
            if (arr[abs(arr[count])] >= 0){
                arr[abs(arr[count])] = -arr[abs(arr[count])];
            }else{
                System.out.printf("%d ", abs(arr[count]));
            }
        }
    }
}
