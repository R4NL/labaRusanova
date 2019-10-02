package com.rusanova.analyzers.lexicalAnalyzer;

import com.rusanova.analyzers.token.Token;
import com.rusanova.analyzers.token.tokenTypeEnum.TokenType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LexicalAnalyzerImp implements LexicalAnalyzer {
    private List<Token> list;
    private String stringBuffer;
    private TokenType tokenTypeBuffer;

    {
        list = new ArrayList<>();
        stringBuffer = "";
        tokenTypeBuffer = null;
    }

    public LexicalAnalyzerImp() {
    }

    @Override
    public List<Token> smash(String line) {
        Arrays.stream(line.replaceAll(" ", "").split("")).forEach(this::add);
        list.forEach(System.out::println);
        return list;
    }

    private void add(String string) {
        if (tokenTypeBuffer == TokenType.witch(string)) {
            stringBuffer += string;
        } else {
            if (stringBuffer != null && !stringBuffer.equals("") && tokenTypeBuffer != null) {
                list.add(new Token(list.size(), stringBuffer, tokenTypeBuffer));
            }
            if (TokenType.witch(string) == TokenType.Break) {
                list.add(new Token(list.size(), string, TokenType.witch(string)));
            } else {
                tokenTypeBuffer = TokenType.witch(string);
                stringBuffer = string;
            }
        }
    }


}
