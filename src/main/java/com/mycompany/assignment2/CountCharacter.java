package com.mycompany.assignment2;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class CountCharacter {

    public static void countCharacter() throws IOException {
        double startTime = System.nanoTime();
        System.out.println();
        System.out.println("Counting character...");
        File file = new File("C:/Users/ROG_PC/243102-STIW3054-A172-A2.wiki/Markdown.md");
        Scanner in = new Scanner(file);
        int words = 0;
        int chars = 0;

        while (in.hasNextLine()) {
            String line = in.nextLine();
            chars += line.length();
            words += new StringTokenizer(line, " ,").countTokens();
        }
        System.out.println("Number of words: " + words);
        System.out.println("Number of characters: " + chars);

        double stopTime1 = System.nanoTime();
        double elapsedTime1 = stopTime1 - startTime;
        double seconds = (double) elapsedTime1 / 1000000000.0;
        System.out.printf("\nDone character count at %.9f seconds", seconds);
    }
}
