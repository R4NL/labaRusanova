package com.rusanova.syntacticalAnalyzer;

import com.rusanova.token.Token;
import com.rusanova.token.tokenTypeEnum.TokenType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AnalyzerImp implements Analyzer {
    private List<Token> list;

    {
        list = new ArrayList<>();
    }

    public AnalyzerImp() {
    }

    public List<Token> getList() {
        return list;
    }

    private void setList(List<Token> list) {
        this.list = list;
    }

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

    private void check(List<String> lines) {
        for (int i = 0; i < lines.size(); i++) {
            i += checkForVariable(lines, i);
            i += checkForConstant(lines, i);
        }
        list.forEach(System.out::println);
    }

    private int checkForVariable(List<String> lines, int i) {
        if (Character.isLetter(lines.get(i).charAt(0))) {
            list.add(new Token(checkForNextVariable(lines.get(i), lines, i), TokenType.Variable));
            return list.get(list.size() - 1).getValue().length() - 1;
        } else {
            return 0;
        }
    }

    private String checkForNextVariable(String res, List<String> lines, int i) {
        if ((i + 1) < lines.size() && Character.isLetter(lines.get(i + 1).charAt(0))) {
            return checkForNextVariable(res + lines.get(i + 1), lines, i + 1);
        } else {
            return res;
        }
    }

    private int checkForConstant(List<String> lines, int i) {
        if (parseInt(lines.get(i))) {
            list.add(new Token(checkForNextConstant(lines.get(i), lines, i), TokenType.Constant));
            return list.get(list.size() - 1).getValue().length() - 1;
        } else {
            return 0;
        }
    }

    private String checkForNextConstant(String res, List<String> lines, int i) {
        if ((i + 1) < lines.size() && parseInt(lines.get(i + 1))) {
            return checkForNextConstant(res + lines.get(i + 1), lines, i + 1);
        } else {
            return res;
        }
    }

    private boolean parseInt(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }


}
