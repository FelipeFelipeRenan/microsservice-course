package br.com.felipe.foo.math;

import org.springframework.stereotype.Service;

@Service
public class SimpleMath {
    public  Double sum(Double numberOne, Double numberTwo){
        return numberOne + numberTwo;
    }
    public  Double minus(Double numberOne, Double numberTwo){
        return numberOne - numberTwo;
    }
    public  Double times(Double numberOne, Double numberTwo){
        return numberOne * numberTwo;
    }
    public  Double divided(Double numberOne, Double numberTwo){
        return numberOne / numberTwo;
    }
    public  Double sqrt(Double number){
        return  Math.sqrt(number);
    }
    public  Double mean(Double numberOne, Double numberTwo){
        return (numberOne + numberTwo)/2;
    }
}
