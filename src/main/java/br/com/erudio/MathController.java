package br.com.erudio;

import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicLong;

import static org.apache.tomcat.util.http.parser.HttpParser.isNumeric;

@RestController
public class MathController {

    private static final String template = "Hello %s";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping(value = "/sum/{numberOne}/{numberTwo}"
            , method = RequestMethod.GET)
    public Double sum(
            @PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo
            ) throws Exception {

            if(!isNumeric(numberOne) || !isNumeric(numberTwo)){
                throw new Exception();
            }

        return convertToDouble(numberOne) + convertToDouble(numberTwo);
    }

    private Double convertToDouble(String strNumber) {

        if(strNumber == null){
            return 0D;
        }

        String number = strNumber.replace(",",".");

        if(isNumeric(number)){
            return Double.parseDouble(number);
        }

        return 0D;
    }

    private boolean isNumeric(String numbers){

        if(numbers == null){
            return false;
        }

        String number = numbers.replace(",",".");

        return number.matches("[-+]?[0-9]*\\.?[0-9+]");
    }
}
