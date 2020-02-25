/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangman;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author iSkYrIsE
 */
public class Controller {

    private View view;
    private Model model;
    private Scanner scanner;
    private int option;

    public Controller() {

        this.view = new View();
        this.model = new Model();
        scanner = new Scanner(System.in);

        try {
            model.loadFromFile();
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

        optionsMenu();
    }

    private void optionsMenu() {

        do {
            view.drawMenu();
            option = Integer.parseInt(scanner.nextLine());
            switch (option) {
                case 0:
                    this.Confirmation();
                    break;
                case 1:
                    this.playGame();
                    break;
                default:
                    view.error();
            }
        } while (option != 0);
    }

    private void playGame() {
        
        //Generate the capital we will play with
        model.generateCapital();
        String letra;
        
        //Check how many letters we got and draw the word in consecuence
        view.drawWord(model.evaluateWord());

        do {
            //Check how many letters we got and draw the word in consecuence
            //view.drawWord(model.evaluateWord());
            //Ask for word
            view.requestLetter();
            letra = scanner.nextLine();

            //Check the lenght of the letter
            if (letra.length() == 1) {
                
                //Check if the letter is already insertedComprobamos si la letra ya se habia dicho anteriormente
                //If there is an error, draw hangman
                if (model.evaluateError(letra) == 1) {
                    try {
                        view.drawHangedManFromFile(model.loadErrorFromFile());
                    } catch (IOException ex) {
                        Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } else {
                
                //If the letter is bigger than 1 position, increment the errors in 1 and draw another part of the hangman
                model.addError();
                view.lengthError();
                try {
                    view.drawHangedManFromFile(model.loadErrorFromFile());
                } catch (IOException ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            //Check if we win or lose
            // 0 == CONTINUE WITH THE GAME
            // 1 == WON GAME
            // 2 == LOST GAME
            //This is checked in every turn of the game
            view.drawWord(model.evaluateWord());
            model.comprobarEstado();
            //System.out.println("Estado actual: "+model.getEstado());
            
        } while (model.getEstado() == 0);
        
        
        //Obtain the state of the game and draw it
        view.endGame(model.getEstado());
        //Reniciate the values to play a new game
        model.resetValues();
    }
    private void Confirmation(){
        
        System.out.println("\nAre you sure you want to quit?\n");
        
        do{
            view.drawQuitMenu();
            option = Integer.parseInt(scanner.nextLine());
            switch(option){
                case 0:
                    System.out.println("\nSee you soon!\n");
                    break;
                case 1:
                    System.out.println("\nSo lets play!!!\n");
                    this.optionsMenu();
                default:
                    view.error();
            }

        } while (option != 0);
    
    }
    
    
    /*
    public class User_Authentication{
        {
            String username, password;
            Scanner s = new Scanner(System.in);
            System.out.print("Enter username:");//username:user
            username = s.nextLine();
            System.out.print("Enter password:");//password:user
            password = s.nextLine();
            if(username.equals("user") && password.equals("user"))
            {
                System.out.println("Authentication Successful");
            }
            else
            {
                System.out.println("Authentication Failed");
            }
        }
    }
    */
}
