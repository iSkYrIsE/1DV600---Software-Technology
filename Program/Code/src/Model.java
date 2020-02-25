/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangman;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author iSkYrIsE
 */
public class Model {
       
   private String capital;
   private String auxCadena;
   private final int nFallos = 7;
   private int errors;
   private int estado;
   private int numAciertos;
   private ArrayList<String> letrasAcertadas;
   private ArrayList<String> capitales;
    
    public Model(){
        letrasAcertadas = new ArrayList<>();
        estado = 0;
        numAciertos = 0;
        errors = 0;
        capitales = new ArrayList<>();
    }
    
    public void generateCapital(){  
        int capitalN = (int) (Math.random()*(capitales.size()));
        capital = capitales.get(capitalN);
    }
    
    
    
    public String evaluateWord(){
        String palabraCompleta = "";
        String letra = "";
        int flag = 0;
        /*
            Each time we check the correct letters of the word, reiniciate the number
            of right answers to not let them accumulate each time we call evaluateWord()
                 
            ALWAYS check the word with the letters that we have
        */
        //this.numAciertos = 1;
        
        //Run over the letters of the capital
        for (int i = 0; i < capital.length(); i++) {
            
            //Run over the letters that already are right answered
            for(int j=0; j<letrasAcertadas.size(); j++){
                letra = String.valueOf(capital.charAt(i));
                
                //If the letter matches with one of the capital's letter, put the flag at 1
                if(letra.equals(letrasAcertadas.get(j))){
                    flag = 1;
                }    
            }
            
            //As the flag is at 1, draw the letter that corresponds
            /*
                If in the array of right letters we had the P of Paris
                it would draw that letter in the if(flag == 1).
                If the letter doesn't exist, draw the _
            */
            if(flag == 1){
                palabraCompleta = palabraCompleta+" "+letra;
                
                //Increment the number of right answers because the letter exists
                //numAciertos++;
                auxCadena = "";
                auxCadena = palabraCompleta.replaceAll(" ", "");
                
                if(capital.equals(auxCadena)){
                    numAciertos = capital.length();
                }
                
                
            }else{
                palabraCompleta = palabraCompleta+" _";
            }  
            
            //Always reiniciate the flag to not let the app think that the letter always exists
            flag = 0;
        }

        return palabraCompleta;
    }

    public int evaluateError(String letra) {
        
        //If flag = 0 everything is correct
        //If the flag = 1, there is an error
        int flag = 0;
        
        //Check that the letter's lenght is 1
        if (getCapital().contains(letra)) {
            
            //Run over the letter's list that we got right
            for (int i = 0; i < getletrasAcertadas().size(); i++) {
                
                //If the letter already exists, put flag = 1, ERROR
                if (getletrasAcertadas().get(i).equals(letra)) {
                    flag = 1;
                }
            }
        } else {
            flag = 1;
        }
        
        //If there is an error, increment the error number
        if (flag == 1) {
            this.errors++;
        } else {
            //If there isn't any error, add the letter to the correct letter's list
            addLetraAcertada(letra);
        }
        return flag;
    }
    
    //In this method check the victory or the defeat of the game
    public void comprobarEstado(){
        
        //If the number of errors is equal to 7 (nFallos), DEFEAT
        if(errors == nFallos){
            estado = 2;
        
        }
        //If the number of right answers is equal to the lenght of the capital, VICTORY
        if(numAciertos == capital.length()){
            estado = 1;
        } 
    }
    
    public int getEstado(){
        return estado;
    }
    public void addError(){
        errors++;
    }
    public int gerErrors(){
        return errors;
    } 
    public ArrayList<String> getletrasAcertadas(){
        return this.letrasAcertadas;
    }
    public String getCapital(){
        return capital;
    }
    
    public void addLetraAcertada(String letra){
        this.letrasAcertadas.add(letra);
    }
    
    public void resetValues(){
        letrasAcertadas.clear();
        estado = 0;
        errors = 0;
        capital = "";
    }
    
    public void loadFromFile() throws FileNotFoundException, IOException{
        File file = new File("capitales.txt");
        
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        
        String capital;
        
        while((capital = br.readLine()) != null){
            capitales.add(capital);
        }
    }
    
    public ArrayList<String> loadErrorFromFile() throws FileNotFoundException, IOException{
        File file = new File("Errores/error"+errors+".txt");
        ArrayList<String> errores = new ArrayList<>();
        
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        
        String lineaError;
        
        while((lineaError = br.readLine()) != null){
            errores.add(lineaError);
        }
        
        return errores;
    }
}
