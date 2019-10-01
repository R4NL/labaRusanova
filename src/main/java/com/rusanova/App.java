package com.rusanova;

import com.rusanova.getLine.GetLine;
import com.rusanova.syntacticalAnalyzer.AnalyzerImp;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        new AnalyzerImp().smash(GetLine.getLine("String.txt"));
    }
}
