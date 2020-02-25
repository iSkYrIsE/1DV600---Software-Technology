/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangman;

import java.util.ArrayList;

/**
 *
 * @author iSkYrIsE
 */
public class View {
    
    public void drawMenu(){
        System.out.println("================================");
        System.out.println("\t1) Play game");
        System.out.println("\t0) Exit");
        System.out.println("================================");
        System.out.print("Option: ");
    }
    
    public void endGame(int estado){
        
        System.out.println("================================");
        if(estado == 1){
            System.out.println("\tYou win!!!");
        }else{
            System.out.println("\tYou lose!!!");
        }
        System.out.println("================================");
    }
    
    public void requestLetter(){
        System.out.println("");
        System.out.print("Introduce a letter: ");
    }
    public void error(){
        System.out.println("\nERROR! The option does not exist\n");
    }
    public void drawWord(String palabra){
        System.out.println(palabra);   
    }
    public void lengthError(){
        System.out.println("\nERROR! Letter length is bigger than 1");
    }
    
    public void drawHangedManFromFile(ArrayList<String> lines){    
        for (String line:lines) {
            System.out.println(line);
        }
    }
    
    public void drawQuitMenu(){
        
        System.out.println("================================");
        System.out.println("\t1) Nope");
        System.out.println("\t0) Yeah");
        System.out.println("================================");
        System.out.print("Option: ");
        
}
 
}
