/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newsfetcher;

/**
 *
 * @author root
 */
public class SlowPrint {
    private void delay(long PERIOD) {
        long lastTime = System.currentTimeMillis();
        boolean sleep = true;
        while (sleep) {
            long thisTime = System.currentTimeMillis();
            if (thisTime >= lastTime + PERIOD) {
                sleep = false;
            }
        }
    }
    
    public static void printslowly(String inp)
    {
        SlowPrint ob=new SlowPrint();
        boolean isbrac=false;
        for(int i=0;i<inp.length();i++)
        {
            if(inp.charAt(i)=='<')
                isbrac=true;
            if(inp.charAt(i)=='>')
            {
                isbrac=false;
                continue;
            }
            if(isbrac)
                continue;
            System.out.print(inp.charAt(i));
            ob.delay(2l);
        }
        System.out.println();
    }
}
