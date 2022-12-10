/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.acme.model;

/**
 *
 * @author Gustavo
 */
public class IllegalArgumentException extends Exception{

    @Override
    public String getMessage() {
        return "Impact factor must be greater than zero.";
    }
    
    
    
}
