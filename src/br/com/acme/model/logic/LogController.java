/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.acme.model.logic;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class LogController {
    
    private static boolean isLogOn = true;
    
    public static void setLogStatus(boolean status){
        isLogOn = status;
    }
    
    public static boolean getLogStatus(){
        return isLogOn;
    }
    
    public static void writeLog(String message){
        File log = new File("library.log");
        StringBuilder finalMessage = new StringBuilder();
        finalMessage.append("[").append(new Date().toString()).append("] ").append(message).append("\n");
        try{
            FileWriter fileWriter = new FileWriter(log, true);
            fileWriter.write(finalMessage.toString());
            fileWriter.flush();
            fileWriter.close();
        }catch(IOException ex){
            System.out.println("ajuda aqui");
        }
    }
    
}
