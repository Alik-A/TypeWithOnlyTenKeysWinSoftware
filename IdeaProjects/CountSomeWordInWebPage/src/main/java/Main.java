import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {
        Document ynet = Jsoup.connect("http://www.ynet.co.il/home/0,7340,L-2,00.html").get();
        Elements elements = ynet.getElementsByTag("p");
        String wordS = "נתניהו";
        int ynetCnt = 0;

        ArrayList <String> ynetStrL = new ArrayList<String>();
        for(Element e1 : elements)
        {
            ynetStrL.add(e1.text());

        }
        for (String s1 : ynetStrL){
            for (String word:s1.split(" " )) {
                if (wordS.contains(word)){
                    ynetCnt++;

                }

            }

        }
        System.out.println(ynetCnt);

//        int pos = ynetStr.indexOf(word);
//        while(pos >= 0)
//        {
//            ynetCnt++;
//            pos++;
//            pos = ynetStr.indexOf(word, pos);
//
//        }
    }
}
