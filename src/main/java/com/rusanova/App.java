package com.rusanova;

import com.rusanova.getLine.GetLine;
import com.rusanova.syntacticalAnalyzer.AnalyzerImp;
import com.rusanova.syntacticalAnalyzer.AnalyzerImpTwo;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        new AnalyzerImpTwo().smash(GetLine.getLine("String.txt"));
    }
}
