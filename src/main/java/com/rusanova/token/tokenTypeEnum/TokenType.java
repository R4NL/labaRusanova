package com.rusanova.token.tokenTypeEnum;

public enum TokenType {
    Variable, Operator, Bracket, Assignment, Constant, Break;

    public static TokenType witch(String str) throws IllegalArgumentException{
        if (Character.isLetter(str.charAt(0))) {
            return TokenType.Variable;
        } else {
            if (parseInt(str)) {
                return TokenType.Constant;
            } else {
                if (str.equals("(") || str.equals(")")) {
                    return TokenType.Bracket;
                } else {
                    if (str.equals("=")) {
                        return TokenType.Assignment;
                    } else {
                        if ("+-*/".contains(str)) {
                            return TokenType.Operator;
                        } else {
                            if (str.equals(";")) {
                                return TokenType.Break;
                            } else {
                                throw new IllegalArgumentException("Token not recognized");
                            }
                        }
                    }
                }
            }
        }
    }

    private static boolean parseInt(String str) {
        if(str==null){
            throw new IllegalArgumentException("Get null in parse String to Int");
        }
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
