package br.com.felipe.foo.controllers;

import br.com.felipe.foo.exception.UnsupportedMethodOperationException;
import br.com.felipe.foo.math.SimpleMath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import static br.com.felipe.foo.request.converters.NumberConverter.convertToDouble;
import static br.com.felipe.foo.request.converters.NumberConverter.isNumeric;

@RestController
public class MathController {
    private static final String VALUERROR =  "Please set a numeric value";

    @Autowired
    private SimpleMath math;

    @GetMapping(value = "/sum/{numberOne}/{numberTwo}")
    public Double sum(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo){
        if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
            throw new UnsupportedMethodOperationException(VALUERROR);
        }
        return math.sum(convertToDouble(numberOne), convertToDouble(numberTwo));
    }

    @GetMapping(value = "/minus/{numberOne}/{numberTwo}")
    public Double minus(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo){
        if(!isNumeric(numberOne) || !isNumeric(numberTwo)){
            throw new UnsupportedMethodOperationException(VALUERROR);
        }

        return math.minus(convertToDouble(numberOne) , convertToDouble(numberTwo));
    }

    @GetMapping(value = "/times/{numberOne}/{numberTwo}")
    public Double times(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo){
        if(!isNumeric(numberOne) || !isNumeric(numberTwo)){
            throw new UnsupportedMethodOperationException(VALUERROR);
        }

        return math.times(convertToDouble(numberOne) , convertToDouble(numberTwo));
    }

    @GetMapping(value = "/divided/{numberOne}/{numberTwo}")
    public Double divided(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo){
        if(!isNumeric(numberOne) || !isNumeric(numberTwo)){
            throw new UnsupportedMethodOperationException(VALUERROR);
        }
        if(numberTwo.equals("0") ){
            throw new UnsupportedMethodOperationException("Second number must be different than 0");

        }


        return math.divided(convertToDouble(numberOne) , convertToDouble(numberTwo));
    }

    @GetMapping(value = "/sqrt/{numberOne}")
    public Double sqrt(@PathVariable("numberOne") String numberOne){
        if(!isNumeric(numberOne)){
            throw new UnsupportedMethodOperationException(VALUERROR);
        }
        Double convertedNumber = convertToDouble(numberOne);
        if(convertedNumber < 0){
            throw new UnsupportedMethodOperationException("The value must be a positive number");
        }

        return math.sqrt(convertedNumber);
    }
    @GetMapping(value = "/mean/{numberOne}/{numberTwo}")
    public Double mean(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo){
        if(!isNumeric(numberOne) || !isNumeric(numberTwo)){
            throw new UnsupportedMethodOperationException(VALUERROR);
        }

        return math.mean(convertToDouble(numberOne) , convertToDouble(numberTwo));
    }

    @GetMapping(value = "/hallo")
    public ResponseEntity<String> hallo(){
        return new ResponseEntity<>("Hello world", HttpStatus.OK);
    }

}
