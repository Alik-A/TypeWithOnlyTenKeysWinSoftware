import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        int ind = 0;
        String url = "http://www.boi.org.il/heb/Pages/HomePage.aspx";
        Document doc = null;
        try {
            
        doc = Jsoup.connect(url).get();
    }
        catch (IOException e){
            System.out.println(e);
        }
        Elements elms = doc.getElementsByTag("td");

        for (Element e1: elms) {
            if (e1.text().equals("USD דולר") && ind == 0){
                ind = 1;
            }
            else
                if (ind == 1){
                    System.out.println(e1.text());
                    break;
            }

        }
    }
}
