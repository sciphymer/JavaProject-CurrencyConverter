package project.CurrencyConverter;

import org.apache.commons.cli.*;

import java.math.BigDecimal;

public class CurrencyConverter {

    public static void main(String[] args){
        Options options = new Options();
        options.addOption("a", "amount", true, "amount");
        options.addOption("f",true,"from currency");
        options.addOption("i",true,"input file path");
        options.addOption("t",true,"to currency");
        CommandLineParser parser = new DefaultParser();

        CommandLine cmd = null;

        try {
            cmd = parser.parse(options,args);
            if(!cmd.hasOption("a") || !cmd.hasOption("f") || !cmd.hasOption("i") || !cmd.hasOption("t")){
                System.out.println(
                        "Missing required options: i, f, t, a\n" +
                        "usage: currency-converter\n" +
                        "-a,--amount <arg>      amount\n" +
                        "-f,--from <arg>        from currency\n" +
                        "-i,--input <arg>       input file path\n" +
                        "-t,--to <arg>          too currency");
                System.exit(-1);
            }
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        String amount = cmd.getOptionValue("a");
        String fromCurrency = cmd.getOptionValue("f");
        String toCurrency = cmd.getOptionValue("t");
        String inputFilePath = cmd.getOptionValue("i");

    }
}
