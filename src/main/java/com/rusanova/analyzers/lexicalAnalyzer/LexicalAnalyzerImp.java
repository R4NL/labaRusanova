package com.rusanova.analyzers.lexicalAnalyzer;

import com.rusanova.analyzers.token.Token;
import com.rusanova.analyzers.token.tokenTypeEnum.TokenType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.rusanova.analyzers.token.tokenTypeEnum.TokenType.*;

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
    public List<Token> smash(String line) throws IllegalArgumentException{
        Arrays.stream(line.replaceAll(" ", "").split("")).forEach(this::add);
        list.forEach(System.out::println);
        return list;
    }

    private void add(String string) throws IllegalArgumentException {
        try {
            if (tokenTypeBuffer == witch(string) && atomicOperator(witch(string))) {
                stringBuffer += string;
            } else {
                if (stringBuffer != null && !stringBuffer.equals("") && tokenTypeBuffer != null) {
                    list.add(new Token(list.size(), stringBuffer, tokenTypeBuffer));
                }
                if (witch(string) == Break) {
                    list.add(new Token(list.size(), string, witch(string)));
                } else {
                    tokenTypeBuffer = witch(string);
                    stringBuffer = string;
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage() + " token №" + list.size());
            throw new IllegalArgumentException(e.getMessage() + " token №" + list.size());
        }
    }

    private boolean atomicOperator(TokenType type) {
        if (type == Bracket || type == Operator || type == Assignment || type == Break) {
            return false;
        } else {
            return true;
        }
    }


}
