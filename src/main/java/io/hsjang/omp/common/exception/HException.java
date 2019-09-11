package io.hsjang.omp.common.exception;

public class HException extends RuntimeException{

    private static final long serialVersionUID = 1L;
    
    public HException(){
        super();
    }

    public HException(String message){
        super(message);
    }
}