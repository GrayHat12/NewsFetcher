/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newsfetcher;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Entities;
import org.jsoup.select.Elements;

/**
 *
 * @author GrayHat
 */
class Grawler {
    
    Document htmldoc=null;
    List<String> linksFound=new LinkedList<>();
    List<String> images=new LinkedList<>();
    List<String> videos=new LinkedList<>();
    String heading="";
    String bodyText="";
    String baseUrl="";
    String currUrl="";
    String text="";
    private static final String USER_AGENT = "Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:65.0) Gecko/20100101 Firefox/65.0";

    Grawler(String t, String c) {
        text=t;
        currUrl=c;
    }
    
    void crawl() {
        try {
            Connection.Response connection = Jsoup.connect(currUrl).userAgent(USER_AGENT).referrer("https://news.google.com/?hl=en-IN&gl=IN&ceid=IN:en").timeout(2200000).followRedirects(true).execute();
            Document htmlDocument = connection.parse();
            htmldoc = htmlDocument;
            baseUrl=htmlDocument.baseUri();
            if (connection.statusCode() == 200) {
                System.out.println("\n**Visiting OK** Recieved web page at " + baseUrl);
            }
            if (connection.statusCode() == 201) {
                System.out.println("\n**Visiting CREATED** Recieved web page at " + baseUrl);
            }
            if (connection.statusCode() == 202) {
                System.out.println("\n**Visiting ACCEPTED** Recieved web page at " + baseUrl);
            }
            if (connection.statusCode() == 203) {
                System.out.println("\n**Visiting INFORMATIVE** Recieved web page at " + baseUrl);
            }
            if (!connection.contentType().contains("text/html")) {
                System.out.println("**FAILURE** Retrieved Something other than HTML");
                return;
            }
            Elements linksOnPage = htmlDocument.select("a[href]");
            System.out.println("Found (" + linksOnPage.size() + ") links");
            for (Element link : linksOnPage) {
                linksFound.add(link.attr("abs:href"));
                //System.out.println(link.attr("abs:href"));
            }
            return;
        } catch (IOException e) {
            System.out.println("Error IOE - at link : " + currUrl);
            e.printStackTrace();
        }
    }

    List<String> getLinks() {
        return linksFound;
    }

    void showPage() {
        if (htmldoc == null) {
            System.out.println("ERROR! Call crawl() before analysis");
            return;
        }
        heading=htmldoc.head().text();
        System.out.println("HEADING : "+heading);
        System.out.println("\n\nIMAGE LINKS : ");
        getAssets();
        Elements img=htmldoc.select("img");
        for(Element im:img)
        {
            if(im.absUrl("src").startsWith("https://ssl")||im.absUrl("src").startsWith("https://www.google"));
            else
                images.add(im.absUrl("src"));
        }
        for(String imag:images)
            SlowPrint.printslowly(imag);
        System.out.println("\n\n\n\nVIDEO LINKS : ");
        for(String vid:videos)
            SlowPrint.printslowly(vid);
        System.out.println("\n\n\n\nBODY CONTENT : ");
        htmldoc.outputSettings().escapeMode(Entities.EscapeMode.extended);
        htmldoc.outputSettings().escapeMode(Entities.EscapeMode.base);
        htmldoc.outputSettings().escapeMode(Entities.EscapeMode.xhtml);
        bodyText = htmldoc.text();
        SlowPrint.printslowly(bodyText);
    }

    private void getAssets() {
        GrayTokenizer gt=new GrayTokenizer();
        List<String> tokens=gt.Tokenize(htmldoc.body().text());
        for(String tok:tokens)
        {
            if(tok.startsWith("http")&&(tok.endsWith(".png")||tok.endsWith(".img")||tok.endsWith(".jpg")||tok.endsWith(".jpeg")))
            {
                if(tok.startsWith("https://ssl")||tok.startsWith("https://www.google"));
                else
                    images.add(tok);
            }
            if(tok.startsWith("http")&&(tok.endsWith(".mp4")||tok.endsWith(".avi")||tok.endsWith(".gif")||tok.endsWith(".mpeg")||tok.endsWith(".flv")))
            {
                videos.add(tok);
            }
            if(tok.startsWith("/watch"))
            {
                videos.add("https://www.youtube.com"+tok);
            }
        }
    }
    
}
