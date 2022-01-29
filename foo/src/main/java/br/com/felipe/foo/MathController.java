package br.com.felipe.foo;


import br.com.felipe.foo.exception.UnsupportedMethodOperationException;
import org.springframework.web.bind.annotation.*;

@RestController
public class MathController {

    @GetMapping(value = "/sum/{numberOne}/{numberTwo}")
    public Double sum(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) throws Exception {
        if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
            throw new UnsupportedMethodOperationException("Please set a numeric value");
        }
        return convertToDouble(numberOne) + convertToDouble(numberTwo);
    }

    @GetMapping(value = "/minus/{numberOne}/{numberTwo}")
    public Double minus(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) throws Exception{
        if(!isNumeric(numberOne) || !isNumeric(numberTwo)){
            throw new UnsupportedMethodOperationException("Please set a numeric value");
        }

        return convertToDouble(numberOne) - convertToDouble(numberTwo);
    }

    @GetMapping(value = "/times/{numberOne}/{numberTwo}")
    public Double times(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) throws Exception{
        if(!isNumeric(numberOne) || !isNumeric(numberTwo)){
            throw new UnsupportedMethodOperationException("Please set a numeric value");
        }

        return convertToDouble(numberOne) * convertToDouble(numberTwo);
    }

    @GetMapping(value = "/divided/{numberOne}/{numberTwo}")
    public Double divided(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) throws Exception{
        if(!isNumeric(numberOne) || !isNumeric(numberTwo)){
            throw new UnsupportedMethodOperationException("Please set a numeric value");
        }
        if(numberTwo.equals("0") ){
            throw new UnsupportedMethodOperationException("Second number must be different than 0");

        }


        return convertToDouble(numberOne) / convertToDouble(numberTwo);
    }

    @GetMapping(value = "/sqrt/{numberOne}")
    public Double sqrt(@PathVariable("numberOne") String numberOne) throws Exception{
        if(!isNumeric(numberOne)){
            throw new UnsupportedMethodOperationException("Please set a numeric value");
        }
        Double convertedNumber = convertToDouble(numberOne);
        if(convertedNumber < 0){
            throw new UnsupportedMethodOperationException("The value must be a positive number");
        }

        return Math.sqrt(convertedNumber);
    }
    @GetMapping(value = "/mean/{numberOne}/{numberTwo}")
    public Double mean(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) throws Exception{
        if(!isNumeric(numberOne) || !isNumeric(numberTwo)){
            throw new UnsupportedMethodOperationException("Please set a numeric value");
        }

        return  (convertToDouble(numberOne) + convertToDouble(numberTwo)) / 2;
    }

    public Double convertToDouble(String strNumber) {
        if (strNumber == null) {
            return 0D;
        }
        String number = strNumber.replaceAll(",", ".");
        if(isNumeric(number)){
            return Double.parseDouble(number);
        }
        return 1D;

    }


    private boolean isNumeric(String strNumber) {
        if (strNumber == null) {
            return false;
        }
        String number = strNumber.replaceAll(",", ".");
        return number.matches("[-+]?[0-9]*\\.?[0-9]+");

    }


}
