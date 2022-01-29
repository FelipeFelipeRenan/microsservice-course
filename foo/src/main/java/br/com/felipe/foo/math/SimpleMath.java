package br.com.felipe.foo.math;

public class SimpleMath {
    public static Double sum(Double numberOne, Double numberTwo){
        return numberOne + numberTwo;
    }
    public static Double minus(Double numberOne, Double numberTwo){
        return numberOne - numberTwo;
    }
    public static Double times(Double numberOne, Double numberTwo){
        return numberOne * numberTwo;
    }
    public static Double divided(Double numberOne, Double numberTwo){
        return numberOne / numberTwo;
    }
    public static Double sqrt(Double number){
        return  Math.sqrt(number);
    }
    public static Double mean(Double numberOne, Double numberTwo){
        return (numberOne + numberTwo)/2;
    }
}
