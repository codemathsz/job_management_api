package br.com.codemathsz.job_management.exceptions;

public class CompanyNotFoundException extends RuntimeException{

    public CompanyNotFoundException(){
        super("Empresa não encontrada");
    }
}