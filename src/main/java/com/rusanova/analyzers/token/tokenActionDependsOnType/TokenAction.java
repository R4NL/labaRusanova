package com.rusanova.analyzers.token.tokenActionDependsOnType;

import com.rusanova.analyzers.token.Token;
import com.rusanova.analyzers.token.tokenTypeEnum.TokenType;

import java.util.List;

public class TokenAction {
    public static List<TokenType> AccessTokenTypeAfterCurrent(TokenType currentTokenType) {
        switch (currentTokenType) {
            case Variable:
                return List.of(TokenType.Assignment, TokenType.Operator, TokenType.Break);
            case Constant:
                return List.of(TokenType.Operator, TokenType.Break);
            case Bracket:
                return List.of(TokenType.Variable, TokenType.Constant, TokenType.Break, TokenType.Bracket);
            case Operator:
            case Assignment:
                return List.of(TokenType.Variable, TokenType.Constant);
            default:
                return List.of();
        }
    }

    public static boolean CheckForBrackets(List<Token> list) {
        return list.stream().mapToInt(TokenAction::check).sum() == 0;

    }

    private static int check(Token token) {
        if (token.getType() != TokenType.Bracket) {
            return 0;
        } else {
            if (token.getValue().equals("(")) {
                return 1;
            } else {
                if (token.getValue().equals(")")) {
                    return -1;
                } else {
                    throw new IllegalArgumentException("Incorrect brackets token in brackets int token №" + token.getId());
                }
            }
        }
    }

}
