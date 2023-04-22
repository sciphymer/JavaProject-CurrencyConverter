package project.CurrencyConverter;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

public class ExchangeRateExtractor {

    private final Document dom;

    private final static String TAGNAME = "Cube";
    public ExchangeRateExtractor(String filePath) {

        try (InputStream in = new FileInputStream(filePath)) {
            DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            dom = docBuilder.parse(in);
        } catch (IOException | SAXException | ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
    }

    public Map<String, BigDecimal> extractExchangeRate(){
            Map<String, BigDecimal> exRateMap = new HashMap<>();
            NodeList nList = dom.getElementsByTagName(TAGNAME);
            for (int i = 0; i < nList.getLength(); i++) {
                Node nCurrency = nList.item(i).getAttributes().getNamedItem("currency");
                Node nRate = nList.item(i).getAttributes().getNamedItem("rate");
                if (nCurrency != null && nRate != null) {
                    String currency = nCurrency.getNodeValue();
                    BigDecimal rate = new BigDecimal(nRate.getNodeValue()).setScale(6, RoundingMode.HALF_EVEN);
                    exRateMap.put(currency, rate);
                }
            }
        return exRateMap;
    }
}
