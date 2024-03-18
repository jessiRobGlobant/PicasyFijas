package com.globant;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.globant.gameInterface.GameInterface;

import lombok.extern.java.Log;

/**
 * Hello world!
 *
 */
@Log
public class App 
{
    static List<String> names;

    static{
        names = Arrays.asList("John", "Jane", "Adam", "Eve", "William", "1", "5");
        logInfo("Original list values");
        names.forEach(name -> logInfo(name));
    }

    public static void lambdaEspresions (){

        logInfo("Sorted ascendant");
        names = names.stream().sorted().collect(Collectors.toList());
        logInfo(names.toString());

        logInfo("Sorted descendant");
        names = names.stream().sorted(Collections.reverseOrder()).collect(Collectors.toList());
        logInfo(names.toString());

        logInfo("Sorted normally");
        names.sort(null);
        logInfo(names.toString());
    }
    public static void main( String[] args )
    {
        // final GameInterface gameInterface = new GameInterface();
        lambdaEspresions();
    }

    public static void logInfo(String msg){
        log.info(msg);
    }
}
