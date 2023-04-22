package project.CurrencyConverter;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PrincipalCalculator {

    BigDecimal amount;
    BigDecimal currencyRate;

    InputRequest request;
    public PrincipalCalculator(InputRequest request, BigDecimal currencyRate) {
        this.request = request;
        this.amount = new BigDecimal(request.getAmount()).setScale(6);
        this.currencyRate = currencyRate;
    }

    public BigDecimal calculate(){
        BigDecimal result =  amount.multiply(currencyRate).setScale(2, RoundingMode.HALF_EVEN);
        String msg = String.format("From: %s | To: %s | Amount: %s | Result: %s %s", request.getSrcCurrency(), request.getTargetCurrency(), request.getAmount(), request.getTargetCurrency(), result);
        System.out.println(msg);
        return result;
    }

}
