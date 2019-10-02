package com.rusanova;

import com.rusanova.analyzers.lexicalAnalyzer.LexicalAnalyzer;
import com.rusanova.analyzers.lexicalAnalyzer.LexicalAnalyzerImpRegEx;
import com.rusanova.analyzers.syntacticAnalyzer.SyntacticAnalyzer;
import com.rusanova.analyzers.syntacticAnalyzer.SyntacticAnalyzerImp;
import com.rusanova.analyzers.token.Token;
import com.rusanova.inPutStream.GetLine;

import java.util.List;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        LexicalAnalyzer lexicalAnalyzer = new LexicalAnalyzerImpRegEx();
        List<Token> tokenList = lexicalAnalyzer.smash(GetLine.getLine("String.txt"));
        SyntacticAnalyzer syntacticAnalyzer=new SyntacticAnalyzerImp(tokenList);
        syntacticAnalyzer.analyze();

    }
}
