package com.rusanova.analyzers.syntacticAnalyzer;

import com.rusanova.analyzers.token.Token;
import com.rusanova.analyzers.token.tokenActionDependsOnType.TokenAction;
import com.rusanova.analyzers.token.tokenTypeEnum.TokenType;

import java.util.ArrayList;
import java.util.List;

import static com.rusanova.analyzers.token.tokenTypeEnum.TokenType.Assignment;

public class SyntacticAnalyzerImp implements SyntacticAnalyzer {
    private List<Token> list;
    private boolean failedAnalyze = true;
    private List<TokenType> typeList = new ArrayList<>();


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
        System.out.println(failedAnalyze);
    }

    private void check() {
        list.forEach(this::checkToken);
        if (list.stream().mapToInt(this::countAssignment).sum() != 1) {
            failedAnalyze = false;
            throw new IllegalArgumentException("Incorrect count of Assignment");
        }
        list.forEach(this::checkForMultiBrackets);

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

    private void checkForMultiBrackets(Token token) {
        if (token.getType() == TokenType.Bracket) {
            if (token.getValue().equals("(")) {
                if (list.get(list.indexOf(token)+1).getValue().equals(")")) {
                    System.out.println("Illegal token №" + token.getId());
                    failedAnalyze = false;
                }
            } else {
                if (list.get(list.indexOf(token)+1).getValue().equals("(")) {
                    System.out.println("Illegal token №" + token.getId());
                    failedAnalyze = false;
                }
            }
        }
    }

}
