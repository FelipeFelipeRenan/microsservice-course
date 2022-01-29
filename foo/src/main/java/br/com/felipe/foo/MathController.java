package br.com.felipe.foo;


import br.com.felipe.foo.exception.UnsupportedMethodOperationException;
import org.springframework.web.bind.annotation.*;

@RestController
public class MathController {

    @RequestMapping(value = "/sum/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double sum(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) throws Exception {
        if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
            throw new UnsupportedMethodOperationException("Please set a numeric value");
        }
        Double sum = convertToDouble(numberOne) + convertToDouble(numberTwo);
        return sum;
    }

    @RequestMapping(value = "/minus/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double minus(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) throws Exception{
        if(!isNumeric(numberOne) || !isNumeric(numberTwo)){
            throw new UnsupportedMethodOperationException("Please set a numeric value");
        }
        Double minus = convertToDouble(numberOne) - convertToDouble(numberTwo);
        return minus;
    }

    @RequestMapping(value = "/times/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double times(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) throws Exception{
        if(!isNumeric(numberOne) || !isNumeric(numberTwo)){
            throw new UnsupportedMethodOperationException("Please set a numeric value");
        }
        Double times = convertToDouble(numberOne) * convertToDouble(numberTwo);
        return times;
    }

    @RequestMapping(value = "/divided/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double divided(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) throws Exception{
        if(!isNumeric(numberOne) || !isNumeric(numberTwo)){
            throw new UnsupportedMethodOperationException("Please set a numeric value");
        }
        if(numberTwo.equals("0") ){
            throw new UnsupportedMethodOperationException("Second number must be different than 0");

        }

        Double divided = convertToDouble(numberOne) / convertToDouble(numberTwo);
        return divided;
    }

    @RequestMapping(value = "/sqrt/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Integer sqrt(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) throws Exception{
        if(!isNumeric(numberOne) || !isNumeric(numberTwo)){
            throw new UnsupportedMethodOperationException("Please set a numeric value");
        }
        if(numberTwo.equals("0") ){
            throw new UnsupportedMethodOperationException("Second number must be different than 0");

        }

        Integer sqrt = Math.sqrt(convertToDouble(numberOne), convertToDouble(numberTwo));
        return sqrt;
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
