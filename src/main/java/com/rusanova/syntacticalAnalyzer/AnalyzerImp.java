package com.rusanova.syntacticalAnalyzer;

import com.rusanova.token.Token;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AnalyzerImp implements Analyzer {

    @Override
    public List<Token> smash(String line) {
        line = line.replaceAll(" ", "");
        List<String> list = toList(line);
        check(list);
        System.out.println(list);
        return null;
    }

    private List<String> toList(String str) {
        return Arrays.asList(str.split(""));
    }

    private List<Token> check(List<String> lines) {
        List<Token> list=new ArrayList<>();
        for (int i = 0; i < lines.size(); i++) {
            if (Character.isLetter(lines.get(i).charAt(0))) {
                String a=checkForNextVariable(lines.get(i),lines,i);
                if(a.length()>1){
                    i+=a.length()-1;
                }
                System.out.println(i+" "+a);
            }
        }
        return null;
    }

    private String checkForNextVariable(String res, List<String> lines, int i) {
        if ((i + 1) < lines.size() && Character.isLetter(lines.get(i + 1).charAt(0))) {
            return checkForNextVariable(res + lines.get(i + 1), lines, i + 1);
        } else {
            return res;
        }
    }

}
