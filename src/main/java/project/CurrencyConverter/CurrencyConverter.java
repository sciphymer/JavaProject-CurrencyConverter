package project.CurrencyConverter;

import org.apache.commons.cli.*;

import java.math.BigDecimal;
import java.util.Map;

public class CurrencyConverter {

    static final String HELPMENU =
            "usage: currency-converter\n" +
            "-a,--amount <arg>      amount\n" +
            "-f,--from <arg>        from currency\n" +
            "-i,--input <arg>       input file path\n" +
            "-t,--to <arg>          too currency";

    public static void main(String[] args) {

        InputRequest request = null;

        try {
            request = inputRequestParser(args);
            ExchangeRateExtractor exRateExtractor = new ExchangeRateExtractor(request.getExChangeRateFilePath());
            Map<String,BigDecimal> exRatemap =  exRateExtractor.extractExchangeRate();

            ExchangeRateConverter exchangeRateConverter = new ExchangeRateConverter(exRatemap);
            BigDecimal currencyRate =  exchangeRateConverter.convertCurrency(request.getSrcCurrency(),request.getTargetCurrency());

            PrincipalCalculator calculator = new PrincipalCalculator(request,currencyRate);
            calculator.calculate();

        } catch (ParseException | MissingInputArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    static InputRequest inputRequestParser(String[] inputArgs) throws MissingInputArgumentException, ParseException{
        Options options = new Options();
        options.addOption("a", "amount", true, "amount");
        options.addOption("f",true,"from currency");
        options.addOption("i",true,"input file path");
        options.addOption("t",true,"to currency");
        CommandLineParser parser = new DefaultParser();

        parser.parse(options,inputArgs);

        CommandLine cmd;

        cmd = parser.parse(options,inputArgs);
        if(!cmd.hasOption("a") || !cmd.hasOption("f") || !cmd.hasOption("i") || !cmd.hasOption("t")){
            System.out.println("Missing required options: i, f, t, a\n" + HELPMENU);
            throw new MissingInputArgumentException("Missing required input argument");
        }

        String amount = cmd.getOptionValue("a");
        String srcCurrency = cmd.getOptionValue("f");
        String targetCurrency = cmd.getOptionValue("t");
        String inputFilePath = cmd.getOptionValue("i");
        return new InputRequest(srcCurrency, targetCurrency, amount,inputFilePath);
    }
}
