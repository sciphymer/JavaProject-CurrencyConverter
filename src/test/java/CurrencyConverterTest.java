import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import project.CurrencyConverter;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class CurrencyConverterTest {

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void missingArgument() {
        String[] inputArgument = new String[]{"-a","10000","-f","USD","-t","HKD"};
        CurrencyConverter.main(inputArgument);
        Assertions.assertTrue(outputStreamCaptor.toString().trim().contains(
                "Missing required options: i, f, t, a\n" +
                        "usage: currency-converter\n" +
                        "-a,--amount <arg>      amount\n" +
                        "-f,--from <arg>        from currency\n" +
                        "-i,--input <arg>       input file path\n" +
                        "-t,--to <arg>          too currency"
        ));
    }

    @Test
    public void correctArgument() {
        String fxRefFilePath = "src/main/resources/eurofxref-daily.xml";
        String[] inputArgument = new String[]{"-a","10000","-f","USD","-t","HKD","-i",fxRefFilePath};
        CurrencyConverter.main(inputArgument);
        Assertions.assertTrue(outputStreamCaptor.toString().trim().contains(
                "From: USD | To: HKD | Amount: 10000 | Result: HKD 78499.23"
        ));
    }
}
