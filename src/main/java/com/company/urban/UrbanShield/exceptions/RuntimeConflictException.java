package com.company.urban.UrbanShield.exceptions;



public class RuntimeConflictException extends RuntimeException{

    public RuntimeConflictException(){

    }

    public RuntimeConflictException(String message){
        super(message);
    }
}