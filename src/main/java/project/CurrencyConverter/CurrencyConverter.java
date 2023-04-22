package project.CurrencyConverter;

import org.apache.commons.cli.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
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

        InputRequest request = inputRequestParser(args);

        ExchangeRateExtractor exRateExtractor = new ExchangeRateExtractor(request.getExChangeRateFilePath());
        Map<String,BigDecimal> exRatmap =  exRateExtractor.extractExchangeRate();

        ExchangeRateConverter exchangeRateConverter = new ExchangeRateConverter(exRatmap);
        BigDecimal currencyRate =  exchangeRateConverter.convertCurrency(request.getSrcCurrency(),request.getTargetCurrency());

        PrincipalCalculator calculator = new PrincipalCalculator(request,currencyRate);
        calculator.calculate();
    }

    static InputRequest inputRequestParser(String[] inputArgs){
        Options options = new Options();
        options.addOption("a", "amount", true, "amount");
        options.addOption("f",true,"from currency");
        options.addOption("i",true,"input file path");
        options.addOption("t",true,"to currency");
        CommandLineParser parser = new DefaultParser();
        try {
            parser.parse(options,inputArgs);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        CommandLine cmd;
        try {
            cmd = parser.parse(options,inputArgs);
            if(!cmd.hasOption("a") || !cmd.hasOption("f") || !cmd.hasOption("i") || !cmd.hasOption("t")){
                System.out.println(
                        "Missing required options: i, f, t, a\n" + HELPMENU);
                System.exit(-1);
            }
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        String amount = cmd.getOptionValue("a");
        String srcCurrency = cmd.getOptionValue("f");
        String targetCurrency = cmd.getOptionValue("t");
        String inputFilePath = cmd.getOptionValue("i");
        return new InputRequest(srcCurrency, targetCurrency, amount,inputFilePath);
    }
}
