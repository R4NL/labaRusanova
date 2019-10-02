package com.rusanova.analyzers.syntacticAnalyzer;

import com.rusanova.analyzers.token.Token;
import com.rusanova.analyzers.token.tokenActionDependsOnType.TokenAction;
import com.rusanova.analyzers.token.tokenTypeEnum.TokenType;

import java.util.ArrayList;
import java.util.List;

public class SyntacticAnalyzerImp implements SyntacticAnalyzer {
    private List<Token> list;
    private boolean failedAnalyze = true;


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
        List<TokenType> typeList = new ArrayList<>();
        for (Token token : list) {
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
                }
            }

        }
    }


}
