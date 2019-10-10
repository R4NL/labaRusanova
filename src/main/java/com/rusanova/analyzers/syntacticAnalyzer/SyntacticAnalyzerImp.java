package com.rusanova.analyzers.syntacticAnalyzer;

import com.rusanova.analyzers.token.Token;
import com.rusanova.analyzers.token.tokenActionDependsOnType.TokenAction;
import com.rusanova.analyzers.token.tokenTypeEnum.TokenType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static com.rusanova.analyzers.token.tokenTypeEnum.TokenType.Assignment;

public class SyntacticAnalyzerImp implements SyntacticAnalyzer {
    private List<Token> list;
    private boolean failedAnalyze;
    private List<TokenType> typeList;

    {
        failedAnalyze = true;
        typeList = new ArrayList<>();
    }

    public SyntacticAnalyzerImp(List<Token> list) {
        this.list = list;
    }


    @Override
    public void analyze() {
        try {
            if (!TokenAction.CheckForBrackets(list)) {
                System.out.println("Incorrect brackets count in token");
                failedAnalyze = false;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            failedAnalyze = false;
        }
        check();
        list.forEach(System.out::println);
        checkForBrackets();
        if (failedAnalyze) {
            System.out.println("No mistake");
        }
    }

    private void check() {
        list.forEach(this::checkToken);
        if (list.stream().mapToInt(this::countAssignment).sum() != 1) {
            failedAnalyze = false;
            throw new IllegalArgumentException("Incorrect count of Assignment");
        }
        checkForMultiBrackets();

    }

    private void checkToken(Token token) {
        if (typeList.contains(token.getType())) {
            typeList = TokenAction.AccessTokenTypeAfterCurrent(token.getType());
        } else {
            if (typeList.equals(new ArrayList<>()) && token.getType() == TokenType.Variable) {
                typeList = TokenAction.AccessTokenTypeAfterCurrent(token.getType());
            } else {
                failedAnalyze = false;
                System.out.println("Illegal type of token №" + token.getId());

            }
        }
        if (token.getType() == TokenType.Break) {
            if (list.size() > (token.getId() + 1)) {
                System.out.println("Illegal symbol №" + (token.getId() + 1));
                failedAnalyze = false;
            }
        }
    }

    private int countAssignment(Token token) {
        if (token.getType() == Assignment) {
            return 1;
        } else return 0;
    }

    private void checkForMultiBrackets() {
        Map<String, Long> map = list.stream().filter(s -> s.getValue().equals("(") || s.getValue().equals(")")).collect(Collectors
                .groupingBy(Token::getValue, Collectors.counting()));
        if (map.get("(") != map.get(")")) {
            System.out.println("Illegal count of brackets");
        }
    }

    private void checkForBrackets() {
        AtomicInteger counter = new AtomicInteger();
        List<Token> brackets = list.stream().filter(s -> s.getValue().equals("(") || s.getValue().equals(")"))
                .collect(Collectors.toList());
        System.out.println(brackets);
        brackets.forEach(s -> {
            if (s.getValue().equals("(")) {
                counter.getAndIncrement();
            }
            if (s.getValue().equals(")")) {
                counter.getAndDecrement();
            }
            if (counter.get() < 0) {
                failedAnalyze = false;
                System.out.println("Illegal bracket in token №" + s.getId());
            }
        });
    }

}
