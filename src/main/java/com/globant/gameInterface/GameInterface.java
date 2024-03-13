package com.globant.gameInterface;


import com.globant.models.PicasyFijas;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class GameInterface {

    private static final Logger logger = LogManager.getLogger(GameInterface.class);
    private static final Scanner scanner = new Scanner(System.in);
    private static final PicasyFijas picasyFijas = new PicasyFijas();

    // Constructor
    public GameInterface(){
        logInfo("Bienvenido al juego Picas y Fijas");
        mainMenu();
    }

    // Methods
    private static void mainMenu(){
        String option = "";
        while (!option.equals("0")){
            logInfo("\n¿Deseas comenzar un nuevo juego?");
            logInfo("1. Si");
            logInfo("0. Salir");

            option = scanner.nextLine();
            if (isAnOption(option, "0","1")){
                if (option.equals("1")){
                    picasyFijas.beginGame();
                    gameMenu();
                }
            }
        }
    }

    private static void gameMenu(){
        boolean winner = false;
        boolean exit = false;
        String userGuess = "";
        while ((!winner) && (!exit)){
            logInfo("\nNumero de intentos: "+picasyFijas.getNumOfTries());
            logInfo("\nIngresa el numero que crees que es o 0 para salir:");

            userGuess = scanner.nextLine();
            try {
                if(userGuess.equals("0")){
                    exit = true;
                }
                else if (isValidGuess(userGuess)){
                    winner = picasyFijas.guessNumber(userGuess);
                    logInfo("\nPicas: "+picasyFijas.getPicas());
                    logInfo("\nFijas: "+ picasyFijas.getFijas());
                }
            } catch (Exception e) {
                logInfo("Ha sucedido un error con el juego");
                // logInfo(e);
            }
            
        }
        if (winner){
            logInfo("\nFelicidades, ganaste!");
            logInfo("Numero de intentos: "+picasyFijas.getNumOfTries());
        }
    }

    // Helper functions
    private static void logInfo(Object msg){
        System.out.println(msg);
        logger.info(msg);
    }

    // Input verification
    public static boolean isAnOption(final String option, final String minOption, final String maxOption){

        final String regexString = String.format("([%s-%s])", minOption, maxOption);

        final boolean inOptions = option.matches(regexString);
        if (inOptions){
            return true;
        }
        else{
            System.out.println("\nNo se ingresó una opcion valida");
            return false;
        }

    }

    public static boolean isValidGuess(final String integer){
        final boolean isInteger = integer.matches("(\\d)+");

        // Check if it is valid lengh and integer values
        if ((isInteger) && (integer.length() == picasyFijas.getNumOfDigits())){
            Set<Character> digits = new HashSet<>();
            int i=0;
            boolean notRepeated = true;
            while ((i<integer.length()) && (notRepeated)) {
                if(digits.contains(integer.charAt(i))) {
                    notRepeated = false;
                 }
                digits.add(integer.charAt(i));
                i++;
            }
            if (!notRepeated){
                logInfo("\nNo se pueden ingresar digitos repetidos");
            }
            return notRepeated;
        }

        logInfo("\nNo se ingresó un numero valido");
        return false;
    }
}
