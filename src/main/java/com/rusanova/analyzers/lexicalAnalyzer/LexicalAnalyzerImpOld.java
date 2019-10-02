package com.rusanova.analyzers.lexicalAnalyzer;

import com.rusanova.analyzers.token.Token;
import com.rusanova.analyzers.token.tokenTypeEnum.TokenType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Deprecated
public class LexicalAnalyzerImpOld implements LexicalAnalyzer {
    private List<Token> list;

    {
        list = new ArrayList<>();
    }

    public LexicalAnalyzerImpOld() {
    }


    @Override
    public List<Token> smash(String line) {
        check(Arrays.asList(line.replaceAll(" ", "").split("")));
        return list;
    }

    private void check(List<String> lines) {
        for (int i = 0; i < lines.size(); i++) {
            i += checkForVariable(lines, i);
            i += checkForConstant(lines, i);
            checkForBracket(lines, i);
            checkForAssignment(lines, i);
            checkForOperator(lines, i);
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

    private void checkForBracket(List<String> lines, int i) {
        if (lines.get(i).equals("(") || lines.get(i).equals(")")) {
            list.add(new Token(lines.get(i), TokenType.Bracket));
        }
    }

    private void checkForAssignment(List<String> lines, int i) {
        if (lines.get(i).equals("=")) {
            list.add(new Token(lines.get(i), TokenType.Assignment));
        }
    }

    private void checkForOperator(List<String> lines, int i) {
        if ("+-*/".contains(lines.get(i))) {
            list.add(new Token(lines.get(i), TokenType.Operator));
        }
    }


}
