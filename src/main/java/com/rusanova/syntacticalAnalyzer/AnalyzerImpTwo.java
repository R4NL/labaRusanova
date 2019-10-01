package com.rusanova.syntacticalAnalyzer;

import com.rusanova.token.Token;
import com.rusanova.token.tokenTypeEnum.TokenType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AnalyzerImpTwo implements Analyzer {
    private List<Token> list;
    private final String operators = "+-*/";
    private String stringBuffer;
    private TokenType tokenTypeBuffer;

    {
        list = new ArrayList<>();
        stringBuffer = "";
        tokenTypeBuffer = null;
    }

    @Override
    public List<Token> smash(String line) {
        Arrays.asList(line.replaceAll(" ", "").split("")).stream().forEach(n -> add(n));
        list.forEach(System.out::println);
        return null;
    }

    private void add(String string) {
        if (tokenTypeBuffer == TokenType.witch(string)) {
            stringBuffer += string;
        } else {
            if (stringBuffer != null && !stringBuffer.equals("") && tokenTypeBuffer != null) {
                list.add(new Token(stringBuffer, tokenTypeBuffer));
            }
            tokenTypeBuffer = TokenType.witch(string);
            stringBuffer = string;
        }
    }


}
