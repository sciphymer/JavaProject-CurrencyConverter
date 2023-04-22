package project.CurrencyConverter;

public class InputRequest {
    private String srcCurrency;
    private String targetCurrency;
    private String amount;
    private String exChangeRateFilePath;

    public InputRequest(String srcCurrency, String targetCurrency, String amount, String exChangeRateFilePath) {
        this.srcCurrency = srcCurrency;
        this.targetCurrency = targetCurrency;
        this.amount = amount;
        this.exChangeRateFilePath = exChangeRateFilePath;
    }

    public String getSrcCurrency() {
        return srcCurrency;
    }

    public void setSrcCurrency(String srcCurrency) {
        this.srcCurrency = srcCurrency;
    }

    public String getTargetCurrency() {
        return targetCurrency;
    }

    public void setTargetCurrency(String targetCurrency) {
        this.targetCurrency = targetCurrency;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getExChangeRateFilePath() {
        return exChangeRateFilePath;
    }

    public void setExChangeRateFilePath(String exChangeRateFilePath) {
        this.exChangeRateFilePath = exChangeRateFilePath;
    }
}
