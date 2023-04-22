package project.calculator;

import project.InputRequest;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

public class PrincipalCalculator {

    BigDecimal amount;
    BigDecimal currencyRate;
    InputRequest request;
    ExchangeRateConverter exchangeRateConverter;

    public PrincipalCalculator(InputRequest request, Map<String,BigDecimal> exchangeRateMap) {
        this.exchangeRateConverter = new ExchangeRateConverter(exchangeRateMap);
        this.currencyRate =  exchangeRateConverter.convertCurrency(request.getSrcCurrency(),request.getTargetCurrency());
        this.request = request;
        this.amount = new BigDecimal(request.getAmount()).setScale(6);
    }

    public BigDecimal calculate(){
        BigDecimal result =  amount.multiply(currencyRate).setScale(2, RoundingMode.HALF_EVEN);
        String msg = String.format("From: %s | To: %s | Amount: %s | Result: %s %s", request.getSrcCurrency(), request.getTargetCurrency(), request.getAmount(), request.getTargetCurrency(), result);
        System.out.println(msg);
        return result;
    }

}
