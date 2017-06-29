/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw7;

import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Jacques Menard
 */
public class Homework7 {

    /**
     * @param args the command line arguments
     */
    public static int a = 3;
    public static int b = 5;
    public static int N = 5;//size of the Hash Table

    
    public static void main(String[] args) {
        List<Integer> myList= new ArrayList();
        myList.add(15);
        myList.add(5);
        myList.add(25);

        printHashTable(myList);
    }
    
    //should return (a*key + b) mod N
    public static int hash(int key){
        int answer = (a*key+b)%N;
        return answer;
    }
    
          
    /* Prints out the index from 0 to N-1, followed by the keys stored at that location in the hash table*/
    public static void printHashTable(List<Integer> keys){
        String[] hashTable = new String[N];
        for(int i=0;i<N;i++){
            hashTable[i]="";
        }
        
        for(int i=0;i<keys.size();i++){
            if(hashTable[hash(keys.get(i))]==""){
                hashTable[hash(keys.get(i))]=""+keys.get(i);
            }else{
                hashTable[hash(keys.get(i))]=hashTable[hash(keys.get(i))]+", "+keys.get(i);
            }
        }
        
        for(int n=0;n<N;n++){
            System.out.println(""+n+"   "+hashTable[n]);            
        }
         
    }
    

    
}
