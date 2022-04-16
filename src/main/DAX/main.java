package main.DAX;

import org.apache.log4j.BasicConfigurator;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import java.io.IOException;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
//https://github.com/sstrickx/yahoofinance-api


public class main {
    public static void main(String[] args) throws IOException {

        BasicConfigurator.configure();

        boolean aktiv = true;
        Stock stock = YahooFinance.get("^GDAXI");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
        ArrayList<BigDecimal> DaxValues = new ArrayList<>();


        System.out.println("------------------------------------");
        //calling the Daxprice every minute
        while (aktiv){
            System.out.println("Time: " + dtf.format(LocalDateTime.now()));
            System.out.println("Current price: " + stock.getQuote().getPrice() + " €");
            DaxValues.add(stock.getQuote().getPrice());

            BigDecimal avg = DaxGetAvg(DaxValues);
            System.out.println("Average price: " + avg + " €");

            BigDecimal maxPrice = DaxMaxPrice(DaxValues);
            System.out.println("Max price: " + maxPrice + " €");

            BigDecimal minPrice = DaxMinPrice(DaxValues);
            System.out.println("Min price: " + minPrice + " €");
            System.out.println("------------------------------------");

            try {
                Thread.sleep(60*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    private static BigDecimal DaxMinPrice(ArrayList<BigDecimal> daxValues) {
        return Collections.min(daxValues);
    }

    private static BigDecimal DaxMaxPrice(ArrayList<BigDecimal> daxValues) {
        return Collections.max(daxValues);
    }

    private static BigDecimal DaxGetAvg(ArrayList<BigDecimal> daxValues) {

        BigDecimal sum = daxValues.stream()
                .map(Objects::requireNonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return sum.divide(new BigDecimal(daxValues.size()),2, RoundingMode.CEILING);

    }
}

