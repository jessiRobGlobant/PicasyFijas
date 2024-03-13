package com.globant.models;

import lombok.*;

import java.util.*;

@Data
@Builder
@Getter
@NoArgsConstructor
public class PicasyFijas {

    // Attributes
    private final static byte numOfDigits = 4;
    private final static byte[] guessNumber = new byte[numOfDigits];
    private final static Set<Byte> guessNumbers = new HashSet<>();
    private static int numTries = 0;
    private static int bestScore = 0;
    private static int picas;
    private static int fijas;

    // Getter
    public byte getNumOfDigits(){
        return numOfDigits;
    }

    public int getNumOfTries(){
        return numTries;
    }

    public int getFijas() {
        return fijas;
    }

    public int getPicas() {
        return picas;
    }

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
        System.out.println(Arrays.toString(guessNumber));
    }

    public void beginGame(){
        numTries = 0;
        createGuessNumber();
    }
    public boolean guessNumber(String userGuessString){

        String[] userGuess = userGuessString.split("");
        numTries++;

        Set<Byte> picas = new HashSet<>(); // numbers present
        Set<Byte> fijas = new HashSet<>(); //positions were it matches
        Set<String> fijasNum = new HashSet<>(); //positions were it matches

        // find fijas
        for (byte i = 0; i < numOfDigits; i++){
            if (Byte.parseByte(userGuess[i]) == guessNumber[i]){
                fijas.add(i);
                fijasNum.add(userGuess[i]);
            }
        }

        // find picas
        for (byte i = 0; i < numOfDigits; i++){
            if ((!fijasNum.contains(userGuess[i])) &&
                    (guessNumbers.contains(Byte.parseByte(userGuess[i])))){
                picas.add(i);
            }
        }

        // Set response
        PicasyFijas.picas = picas.size();
        PicasyFijas.fijas = fijas.size();

        return isWinner();
    }

    private boolean isWinner() {
        return (fijas == numOfDigits);
    }

}
