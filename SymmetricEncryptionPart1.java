/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symmetricencryptionpart1;

/**
 *
 * Purpose Encrypts a text file using either a Vigener or Permutation Cypher
 * @author Jacques Menard
 * @version 1.0 2/24/2016
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SymmetricEncryptionPart1{

    /**
     * The main method calls prints either a Vigoner cypher and a Permutation cypher.
     * I used "Old Man and The Sea Ernest Hemingway.txt" as my file for encryption.
     * My vigener key is bravery
     * My permutation key is (2,6,0,1,5,3,1)
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
        System.out.println(Vigoner());
        
        System.out.println(Permutation());
    }
    
    /**
     * Method that asks user for a file location. Then using a vigener cypher encrypts the plaintext.
     * Method will only return a correct value if the plaintext is preproccesed by removing all the special characters and converting upper case letters to lower case.
     * 
     * @return An encrypted string of the plaintext.
     */
    public static String Vigoner(){
        
        //Variable Initialization
        
        String plaintext ="";
        int count = 0;
        int keycount = 0;
        String cyphertext = "";
        List<Character> alphabet = Arrays.asList('a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z');
        String key = "bravery";
        //Promps user with messsage and saves string entered into plaintextLocation
        Scanner in = new Scanner(System.in);
        System.out.println("Enter file location of plaintext to encrypt: ");
        String plaintextLocation = in.nextLine();
        
        //Looks up and copies plaintext txt file into the string plaintext
        //If file isnt found throws a FileNotFound error
        try {
            Scanner sc = new Scanner(new File(plaintextLocation));
            while (sc.hasNextLine()) {
            plaintext += sc.nextLine();
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SymmetricEncryptionPart1.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        

       
        
        //Loop goes through the plaintext string one character at a time and shifts it using the correct shift value based on its location and value in the string.
        
        while(count<plaintext.length()){
            
            //Checks for spaces and jumpts back if detected increments the count by 1
            if (plaintext.charAt(count)== ' '){
                count++;
                cyphertext+=" ";
                continue; 
            }
            
            // Looks up values of the current plaintext and key character adds them and divides them by 26.
            //The remainder is then converted into a letter (example 0 to a and 25 to z) and concatenates it to the cyphertext string.
            if (keycount==key.length()){
                keycount=0;
            }
            if(keycount==0){
                cyphertext += alphabet.get((alphabet.indexOf(plaintext.charAt(count))+ alphabet.indexOf(key.charAt(0)))%26);
            }
            if(keycount==1){
                cyphertext += alphabet.get((alphabet.indexOf(plaintext.charAt(count))+ alphabet.indexOf(key.charAt(1)))%26);
            }
            if(keycount==2){
                cyphertext += alphabet.get((alphabet.indexOf(plaintext.charAt(count))+ alphabet.indexOf(key.charAt(2)))%26);
            }
            if(keycount==3){
                cyphertext += alphabet.get((alphabet.indexOf(plaintext.charAt(count))+ alphabet.indexOf(key.charAt(3)))%26);
            }
            if(keycount==4){
                cyphertext += alphabet.get((alphabet.indexOf(plaintext.charAt(count))+ alphabet.indexOf(key.charAt(4)))%26);
            }
            if(keycount==5){
                cyphertext += alphabet.get((alphabet.indexOf(plaintext.charAt(count))+ alphabet.indexOf(key.charAt(5)))%26);
            }
            if(keycount==6){
                cyphertext += alphabet.get((alphabet.indexOf(plaintext.charAt(count))+ alphabet.indexOf(key.charAt(6)))%26);
            }

            count++;
            keycount++;
        }
        
        
        return cyphertext;
}

    public static String Permutation(){
               
        int count = 0;
        int n = 0;
        int spacecount = 0;
        
        String cyphertext = "";
        String plaintext= "";
        String tempstring = "";
        String nospace = "";
        List<Integer> key = Arrays.asList(2,6,0,1,5,3,4);
        
        
        //Prompts the user for a file location.
        //C:\Users\Bob Ross\Desktop\School\Comp Sci 2\SymmetricEncryptionPart1\Old Man and The Sea Ernest Hemingway.txt
        Scanner in = new Scanner(System.in);
        System.out.println("Enter file location of plaintext to encrypt: ");
        String plaintextLocation = in.nextLine();
        
        //Looks up and copies plaintext txt file into the string plaintext
        //If file isnt found throws a FileNotFound error
        try {
            Scanner sc = new Scanner(new File(plaintextLocation));
            while (sc.hasNextLine()) {
            plaintext += sc.nextLine();
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SymmetricEncryptionPart1.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
        while(count<plaintext.length()){
          
            if (plaintext.charAt(count)== ' '){
                nospace += "";
            }else{
                nospace += plaintext.charAt(count);
            }
            count++;    
        }
        
        
        count=0;
        int i = 0;

        while(count<nospace.length()){
            if (i==7){
                i=0;
                n++;
            }
                
            tempstring+=nospace.charAt(key.get(i)+6*n);
            
            count++;           
            i++;
            
        }
        System.out.println(tempstring);
        count=0;
        while(count<plaintext.length()){
          
            if (plaintext.charAt(count)== ' '){
                cyphertext+=" ";
                spacecount++;
            }else{
                cyphertext+=tempstring.charAt(count-spacecount);
            }
            count++;    
        }
        return cyphertext;
    }
}

