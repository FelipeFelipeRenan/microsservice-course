package br.com.felipe.foo;


import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MathController {
   
    @RequestMapping(value="/sum/numberOne/numberTwo", method = RequestMethod.GET)
    public Double sum(@PathVariable("numberOne" ) String numberOne , @PathVariable("numberTwo" ) String numberTwo)  throws Exception{
        if(!isNumeric(numberOne)){
            throw new Exception();
        }
        Double sum = convertToDouble(numberOne) + convertToDouble(numberTwo);
        return sum;
    }

    private boolean isNumeric(String strNumber){
        
        return false;
    }

    public Double convertToDouble(String strNumber){
        return 1D;

    }

}
