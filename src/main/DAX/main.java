package main.DAX;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.math.BigDecimal;
import java.util.ArrayList;
//https://github.com/sstrickx/yahoofinance-api

public class main {
    public static void main(String[] args) throws IOException {

        boolean aktiv = true;
        Stock stock = YahooFinance.get("^GDAXI");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
        ArrayList<BigDecimal> DaxValues = new ArrayList<>();

        //calling the Daxprice every minute
        while (aktiv){
            System.out.println(dtf.format(LocalDateTime.now()) + ": " + stock.getQuote().getPrice() + " €");
            DaxValues.add(stock.getQuote().getPrice());
            BigDecimal avg = DaxGetAvg(DaxValues);
            System.out.println(avg);


            try {
                Thread.sleep(1*60*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static BigDecimal DaxGetAvg(ArrayList<BigDecimal> daxValues) {
        BigDecimal avg = null;
        //todo: avg ermitteln
                return avg;

    }

}

