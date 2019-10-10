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
    private final String Variable = "[a-zA-z]+\\w*";
    private final String Operator = "[+-/*&&[^.]]";
    private final String Bracket = "[()]";
    private final String Assignment = "[=]";
    private final String Constant = "\\d+[\\.]?\\d*+";
    private final String Break = ";$";
    private String line;
    private Token tokenBuffer;

    {
        result = new ArrayList<>();
    }

    @Override
    public List<Token> smash(String line) throws IllegalArgumentException {
        this.line = line;
        Arrays.asList(this.getClass().getDeclaredFields()).forEach(this::collect);
        result.sort((Comparator.comparingInt(Token::getId)));
        result.forEach(System.out::println);
        checkForUnknownTokens();
        System.out.println();

        int i = 0;
        while (i < (result.size() - 1)) {
            tokenOperatorConverter(result.get(i));
            i++;
        }

        return result;
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

    private void checkForUnknownTokens() {
        StringBuilder builder = new StringBuilder(line);
        System.out.println(builder);
        result.forEach(n -> builder.delete(builder.indexOf(n.getValue()), builder.indexOf(n.getValue()) + n.getValue().length()));
        if (builder.length() > 0) {
            System.out.println("Unknown token " + builder);
        }
    }

    private void tokenOperatorConverter(Token token) {
        Token tokenAfter = result.get(result.indexOf(token) + 1);

        switch (tokenAfter.getType()) {
            case Constant, Variable:
                switch (token.getType()) {
                    case Operator: {
                        result.set(result.indexOf(tokenAfter),
                                token.setType(tokenAfter.getType())
                                        .setValue(token.getValue() + tokenAfter.getValue()));
                        result.remove(token);
                        break;
                    }

                }
        }

    }
}
