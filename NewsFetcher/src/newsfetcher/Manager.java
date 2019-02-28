/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newsfetcher;

import java.net.URLEncoder;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 *
 * @author GrayHat
 */
public class Manager {
    Set<String> pagesVisited=new HashSet<>();
    List<String> pagesToVisit=new LinkedList<>();
    int numVisited=0;
    public void search(String text,int MaxPages) throws Exception
    {
        String url="https://news.google.com/search?q="+URLEncoder.encode(text,"UTF-8");
        while(pagesVisited.size()<MaxPages)
        {
            String currUrl;
            if(pagesToVisit.isEmpty())
            {
                currUrl=url;
            }
            else
                currUrl=nextUrl();
            pagesVisited.add(currUrl);
            numVisited+=1;
            Grawler gh=new Grawler(text,currUrl);
            gh.crawl();
            pagesToVisit.addAll(gh.getLinks());
            gh.showPage();
            System.out.println("\n\nPage Displayed Above belonged to URL - \""+currUrl+"\"");
        }
        System.out.println("DONE ** VISITED "+pagesVisited.size()+" pages....");
    }

    private String nextUrl() {
        if(pagesToVisit.get(numVisited).startsWith("https://news.google")||pagesToVisit.get(numVisited).startsWith("http://news.google")||pagesToVisit.get(numVisited).startsWith("https://about.google/")||pagesToVisit.get(numVisited).startsWith("https://myaccount.google")||pagesToVisit.get(numVisited).startsWith("https://google.")||pagesToVisit.get(numVisited).startsWith("https://www.youtube.")||pagesToVisit.get(numVisited).startsWith("https://drive.google")||pagesToVisit.get(numVisited).startsWith("https://photos.google")||pagesToVisit.get(numVisited).startsWith("https://hangouts")||pagesToVisit.get(numVisited).startsWith("https://www.google.co.in/webhp")||pagesToVisit.get(numVisited).startsWith("https://maps.google")||pagesToVisit.get(numVisited).startsWith("https://contacts.google")||pagesToVisit.get(numVisited).startsWith("https://www.google.com/calendar")||pagesToVisit.get(numVisited).startsWith("https://plus")||pagesToVisit.get(numVisited).startsWith("https://translate")||pagesToVisit.get(numVisited).startsWith("https://docs.goog")||pagesToVisit.get(numVisited).startsWith("https://books.google")||pagesToVisit.get(numVisited).startsWith("https://www.blogger.")||pagesToVisit.get(numVisited).startsWith("https://jamboard.google")||pagesToVisit.get(numVisited).startsWith("https://www.google.")||pagesToVisit.get(numVisited).startsWith("https://accounts.google")||pagesToVisit.get(numVisited).startsWith("https://play.google")||pagesToVisit.get(numVisited).startsWith("https://mail.google")||pagesToVisit.get(numVisited).startsWith("https://keep.google")||pagesToVisit.get(numVisited).startsWith("https://itunes.apple")||pagesToVisit.get(numVisited).startsWith("https://support.google")||pagesToVisit.get(numVisited).startsWith("http://itunes.apple"))
        {
            numVisited+=1;
            return nextUrl();
        }
        if(!pagesVisited.contains(pagesToVisit.get(numVisited)))
            return pagesToVisit.get(numVisited);
        else
        {
            numVisited+=1;
            return nextUrl();
        }
    }
}