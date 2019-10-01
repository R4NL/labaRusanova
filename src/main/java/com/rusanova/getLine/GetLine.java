package com.rusanova.getLine;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;

public final class GetLine {
    public static String getLine(String url) {
        StringBuilder result = new StringBuilder();
        try {
            Files.readAllLines(Path.of(url)).forEach(n -> result.append(n));
        } catch (NoSuchFileException e) {
            createFile(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(result);
        return result.append(';').toString();
    }

    private static void createFile(String url){
        try {
            Files.createFile(Path.of(url));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
