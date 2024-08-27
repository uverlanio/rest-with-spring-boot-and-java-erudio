package br.com.erudio.converters;

public class NumberConverter {

    public static Double convertToDouble(String strNumber) {

        if(strNumber == null){
            return 0D;
        }

        String number = strNumber.replace(",",".");

        if(isNumeric(number)){
            return Double.parseDouble(number);
        }

        return 0D;
    }

    public static boolean isNumeric(String numbers){

        if(numbers == null){
            return false;
        }

        String number = numbers.replace(",",".");

        return number.matches("[-+]?[0-9]*\\.?[0-9+]");
    }
}
