package com.rusanova.analyzers.lexicalAnalyzer;

import com.rusanova.analyzers.token.Token;

import java.util.List;

public interface LexicalAnalyzer  {
    List<Token> smash(String line) throws IllegalArgumentException;
}

