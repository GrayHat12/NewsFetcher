/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newsfetcher;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author GrayHat
 */
public class GrayTokenizer {
    
    public List<String> Tokenize(String inp)
    {
        List<String> out = new LinkedList<>();
        String temp="";
        for(int i=0;i<inp.length();i++)
        {
            if(inp.charAt(i)==' '||inp.charAt(i)=='<'||inp.charAt(i)=='>'||inp.charAt(i)=='('||inp.charAt(i)==')'||inp.charAt(i)==','||inp.charAt(i)==']'||inp.charAt(i)=='['||inp.charAt(i)=='{'||inp.charAt(i)=='}'||inp.charAt(i)=='"'||inp.charAt(i)=='\'')
            {
                out.add(temp);
                temp="";
                continue;
            }
            temp+=inp.charAt(i);
        }
        out.add(temp);
        return out;
    }
}