package com.rusanova.token;

import com.rusanova.token.tokenTypeEnum.TokenType;

import java.util.Objects;

public final class Token {
    private String value;
    private TokenType type;
    private int id;

    public Token(String value, TokenType type, int id) {
        this.value = value;
        this.type = type;
        this.id = id;
    }

    public Token() {
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public TokenType getType() {
        return type;
    }

    public void setType(TokenType type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Token token = (Token) o;
        return id == token.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Token{" +
                "value='" + value + '\'' +
                ", type=" + type +
                ", id=" + id +
                '}';
    }
}
