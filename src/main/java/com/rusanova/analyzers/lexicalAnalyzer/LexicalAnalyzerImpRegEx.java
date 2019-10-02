package com.rusanova.analyzers.lexicalAnalyzer;

import com.rusanova.analyzers.token.Token;
import com.rusanova.analyzers.token.tokenTypeEnum.TokenType;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class LexicalAnalyzerImpRegEx implements LexicalAnalyzer {
    private List<Token> result;
    private final String Variable = "[a-zA-z]+";
    private final String Operator = "[+-/*]";
    private final String Bracket = "[()]";
    private final String Assignment = "[=]";
    private final String Constant = "[1-9]+";
    private final String Break = ";$";
    private String line;

    {
        result = new ArrayList<>();
    }

    @Override
    public List<Token> smash(String line) throws IllegalArgumentException {
        this.line = line;
        Arrays.asList(this.getClass().getDeclaredFields()).forEach(this::collect);
        result.sort((Comparator.comparingInt(Token::getId)));
        result.forEach(System.out::println);
        return null;
    }

    private void collect(Field field) {
        if (field.toString().contains("final")) {

            try {
                Matcher matcher = Pattern.compile(String.valueOf(field.get(this))).matcher(line);
                result.addAll(matcher.results().map(n -> new Token(matcher.start(), matcher.group(), TokenType.valueOf(field.getName()))).collect(Collectors.toList()));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }
    }
}
