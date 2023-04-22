package project.CurrencyConverter;

import java.math.BigDecimal;
import java.util.Map;

public class ExchangeRateConverter {

    private final Map<String, BigDecimal> exChangeRates;
    public ExchangeRateConverter(Map<String, BigDecimal> exChangeRates) {
        this.exChangeRates = exChangeRates;
    }

    public BigDecimal convertCurrency(String srcCurrency, String targetCurrency){
        try {
            if (!exChangeRates.containsKey(targetCurrency)) {
                throw new CurrencyRateNotFoundException("No Currency rate on " + targetCurrency);
            }
            if (!exChangeRates.containsKey(srcCurrency)) {
                throw new CurrencyRateNotFoundException("No Currency rate on " + srcCurrency);
            }
        } catch (CurrencyRateNotFoundException e){
            return BigDecimal.ZERO;
        }
        return exChangeRates.get(targetCurrency).divide(exChangeRates.get(srcCurrency),6);
    }
}
