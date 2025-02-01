package br.com.codemathsz.job_management.exceptions;

public class JobNotFoundException extends RuntimeException{

    public JobNotFoundException(){
        super("Job not found");
    }
}
