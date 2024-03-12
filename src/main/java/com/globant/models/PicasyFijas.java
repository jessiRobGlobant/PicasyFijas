package com.globant.models;

import lombok.Builder;
import lombok.Data;

import java.util.*;

@Data
@Builder
public class PicasyFijas {

    // Attributes
    private final static byte numOfDigits = 4;
    private final static byte[] guessNumber = new byte[numOfDigits];
    private final static Set<Byte> guessNumbers = new HashSet<>();
    private static int numTries = 0;

    // Methods
    private void createGuessNumber(){
        byte randomDigit;
        byte i = 0;

        while (i < numOfDigits){
            randomDigit = (byte) (Math.random() * (10));
            if (!guessNumbers.contains(randomDigit)){
                guessNumber[i] = randomDigit;
                guessNumbers.add(randomDigit);
                i++;
            }
        }
    }

    public void beginGame(){
        numTries = 0;
        createGuessNumber();
    }
    public int[] guessNumber(byte[] userGuess){
        Set<Byte> picas = new HashSet<>(); // numbers present
        Set<Byte> fijas = new HashSet<>(); //positions were it matches

        // find fijas
        for (byte i = 0; i < numOfDigits; i++){
            if (userGuess[i] == guessNumber[i]){
                fijas.add(i);
            }
        }

        // find picas
        for (byte i = 0; i < numOfDigits; i++){
            if ((!fijas.contains(i)) &&
                    (guessNumbers.contains(userGuess[i]))){
                picas.add(i);
            }
        }
        return new int[]{picas.size(), fijas.size()};
    }
}
