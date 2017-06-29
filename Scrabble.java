/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw8;

/**
 *
 * @authors Ben @ Jacques Menard
 */

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.io.*;
import java.util.Collections;
import java.util.Comparator;


public class Scrabble{

    public static final String fileLoc = "C:\\Users\\Bob Ross\\Desktop\\enable1.txt";
    public static final String letterFileLoc = "C:\\Users\\Bob Ross\\Desktop\\School\\Comp Sci 2\\letters.csv";
    public static List<String> allWords;
    public static Map<String,Boolean> myDictionary;
    public static Map<Integer,Integer> sizeMap;
    public static Map<Character, Integer> letterValueMap;
    public static Map<Character, Integer> lettercountMap;
    public static Map<Integer, List<String>> wordListMap;

    //You are free to put whatever tests you like in the main method
    public static void main(String[] args) {
        try {
            importWords();
        } catch (FileNotFoundException ex) {
            System.err.println("Words File not found : " + ex);
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + fileLoc + "'");
        }
        constructDictionary();
        constructSizeMap();
              
        //Simple Tests
        System.out.println(allWords.get(4));
        System.out.println(isValidWord("hey"));
        System.out.println(numWordsBySize(3));
        try{
            constructLetterMaps();
        }catch(FileNotFoundException ex){
            System.out.println("File was not found");
        }
        constructWordListMap();
        System.out.println(letterValueMap.entrySet());
        System.out.println(lettercountMap.entrySet());
        System.out.println(calculateStringValue("Friends*"));
        List<String> testList = new ArrayList<>();
        testList.add("words");
        testList.add("with");        
        testList.add("friends");
        testList.add("is");
        testList.add("fun");
        printStringsWithValues(testList);
        System.out.println(getKey("aa"));
        System.out.println(getKey("ab"));
        System.out.println(getKey("abt"));
        System.out.println(wordListMap.get(getKey(allWords.get(1))).add(allWords.get(1)));
        System.out.println(wordListMap.get(getKey("battle")));
    }
    
    /*
        Imports all the words from the file and puts them into our word list
    */
    public static void importWords() throws FileNotFoundException, IOException{
        allWords = new ArrayList<>();
        int lineCount = 0;
        String line = null;
        FileReader fileReader = new FileReader(fileLoc);

            // Always wrap FileReader in BufferedReader.
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        while((line = bufferedReader.readLine()) != null) {
            allWords.add(line);
        }    

            // Always close files.
            bufferedReader.close();
        
    }
    /*
        Puts all the words into Hash Map where the keys are the words and the 
        value is the boolean true for each word.  That is, all map entries will have 
        the same value.  Those who know some other Java data
        structures such as HashSet will realize we could've easily used one of those 
        here since we don't actually have different values.
    */
    public static void constructDictionary(){
        myDictionary = new HashMap<>();
        try {
            importWords();
        } catch (FileNotFoundException ex) {
            System.err.println("Words File not found : " + ex);
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + fileLoc + "'");
        }
        for(int i=0; i<allWords.size();i++){
            myDictionary.put(allWords.get(i), Boolean.TRUE);
        }
    
    }
    
    /*
        Once you have your map constructed, this should tell you whether a given string is a valid word.      
    */
    public static boolean isValidWord(String w){
        if (myDictionary.containsKey(w)){
            return true;
        }       
        return false;
    }
    
    /*
        Another way of doing the same thing.  Which is better and why?
        //Your answer here: The first method runs in O(1) time so it is faster then the second.
    
    
    
    */
    public static boolean isValidWordTwo(String w){
        for (String s:allWords){
            if (w.equals(s))
                return true;                       
        }
        return false;
    }
    
    /*
        This method constructs a map where the keys are integers that represent the length of words and the values
        are the numbers of words of that have that length.  
    */
    public static void constructSizeMap(){
       sizeMap = new HashMap<>(); 
        try {
            importWords();
        } catch (FileNotFoundException ex) {
            System.err.println("Words File not found : " + ex);
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + fileLoc + "'");
        }
        for(int i=0; i<allWords.size();i++){
            if(sizeMap.get(allWords.get(i).length())!=null){
                sizeMap.put(allWords.get(i).length(), sizeMap.get(allWords.get(i).length())+1);
            }else{   
            sizeMap.put(allWords.get(i).length(), 0);
            }
        }     
    }
    
    /*
        This method will return the number of words in the dictionary that have size n
        where n is the parameter of the method.  If you didn't have to handle the case where there 
        are no words of that size, this could just be one line of code.  You should use your size map in this method.
    */
    public static Integer numWordsBySize(int n){
        constructSizeMap();
        if (sizeMap.get(n)==null){
            return 0;
        }else{
            return sizeMap.get(n);
        }
        
    }
    
    public static void constructLetterMaps() throws FileNotFoundException{
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        try{
            letterValueMap  = new HashMap<>();
            lettercountMap  = new HashMap<>();
            br = new BufferedReader(new FileReader(letterFileLoc));
            while ((line = br.readLine()) != null) {
                String[] split = line.split(cvsSplitBy);
                letterValueMap.put(split[0].charAt(0),Integer.parseInt(split[2]));
                lettercountMap.put(split[0].charAt(0),Integer.parseInt(split[1]));
            }
            
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        } finally {
            if(br !=null){
                try{
                    br.close();
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }
    public static int calculateStringValue(String word){
            try{
                constructLetterMaps();
            }catch(FileNotFoundException ex){
                System.out.println("File was not found");
            }
            String trueword=word.toLowerCase();
            if(isAlpha(trueword)){
                int value = 0;
                for(int i=0; i<word.length();i++){
                    value+= letterValueMap.get(trueword.charAt(i));
                }
                return value;
            }
            return -1;
    }
    public static boolean printWords(String s){
        if(s.contains("*")){
            System.out.println(s);
     
            
        }else{
            System.out.println(s);
            return true;
        }
        return false;
    }
    

    public static boolean isAlpha(String name) {
        char[] chars = name.toCharArray();
        for (char c : chars) {
            if(c=='*'){
                return true;
            }
            if(!Character.isLetter(c)) {
                return false;
            }
        }

        return true;
    }
    public static PriorityQueue<String> pQSortStrings(List<String> strings){
           
        PriorityQueue<String>  myQue = new PriorityQueue(strings.size(), new WWFComparator());
        for(int i =0; i<strings.size();i++){
            myQue.add(strings.get(i));
        }
        return myQue;
    }
    
    public static boolean printStringsWithValues(List<String> strings){
        try{
            constructLetterMaps();
        }catch(FileNotFoundException ex){
            System.out.println("File was not found");
        }
        
        int count = 0;
        PriorityQueue<String> descendingValues = pQSortStrings(strings);
        for(int i =0; i<strings.size();i++){
            System.out.println(""+descendingValues.peek()+",   " +calculateStringValue(descendingValues.peek()));
            descendingValues.poll();
            count++;
        }
        System.out.println("Size of List = " + count);
        return true;
    }
    public static Integer getKey(String s){
        //My key will be (The sum of character values^2+ word size)
        int key = 0;
        for(int i=0; i<s.length();i++){
            int c = (int)s.charAt(i);
           key += c^2;
        }
        
        key+= s.length();
        return key;  
        }
        
    public static void constructWordListMap(){
        wordListMap= new HashMap<>();
        try {
            importWords();
        } catch (FileNotFoundException ex) {
            System.err.println("Words File not found : " + ex);
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + fileLoc + "'");
        }
        
        for(int i=0; i<allWords.size();i++){
            if(wordListMap.containsKey(getKey(allWords.get(i)))){
                wordListMap.get(getKey(allWords.get(i))).add(allWords.get(i));
            }else{
                List<String> list = new ArrayList<>();
                list.add(allWords.get(i));
                wordListMap.put(getKey(allWords.get(i)),list);
            }
        }
        
        
            
        }
        
    public static void constructAllWordsMap(int n){
            
        }
        
    public static List<String> allWordsFromString(String s){
            List<String> myList = new ArrayList<>();
            return myList;
        }
        
private static class WWFComparator implements Comparator<String>{

        @Override
        public int compare(String x, String y) {
           if (calculateStringValue(x)>calculateStringValue(y)){
                return -1;
            }else if(calculateStringValue(x)<calculateStringValue(y)){
                return 1;
            }else{
                return 0;
            }
        }

    
}
}

    
