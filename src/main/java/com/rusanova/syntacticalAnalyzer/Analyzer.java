package com.rusanova.syntacticalAnalyzer;

import com.rusanova.token.Token;

import java.util.List;

public interface Analyzer {
    List<Token> smash(String line);
}

