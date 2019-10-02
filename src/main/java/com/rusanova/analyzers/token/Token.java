package com.rusanova.analyzers.token;

import com.rusanova.analyzers.token.tokenTypeEnum.TokenType;

import java.util.Objects;

public final class Token {
    private int id;
    private String value;
    private TokenType type;

    public Token(int id, String value, TokenType type) {
        this.id = id;
        this.value = value;
        this.type = type;
    }

    public Token(String value, TokenType type) {
        this.value = value;
        this.type = type;
    }

    public Token() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Token token = (Token) o;
        return id == token.id &&
                Objects.equals(value, token.value) &&
                type == token.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, value, type);
    }

    @Override
    public String toString() {
        return "Token{" +
                "id=" + id +
                ", value='" + value + '\'' +
                ", type=" + type +
                '}';
    }
}