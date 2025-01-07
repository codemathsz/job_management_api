package br.com.codemathsz.job_management.exceptions;

public class UserFoundException extends RuntimeException{

    public UserFoundException(){
        super("Usuario já existe");
    }
}
