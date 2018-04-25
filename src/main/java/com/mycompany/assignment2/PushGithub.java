package com.mycompany.assignment2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import static java.lang.Thread.sleep;

public class PushGithub {

    public void runGit() throws IOException {
        try {

            ProcessBuilder builder = new ProcessBuilder(
                    "cmd.exe", "/c", "cd && cd \"C:\\Users\\ROG_PC\\243102-STIW3054-A172-A2.wiki\" && git add * && git commit -m \"Test\" && git pull && git push");
            builder.redirectErrorStream(true);
            Process p = builder.start();
            BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            System.out.println("\nGIT result : \n");
            while (true) {
                line = r.readLine();
                if (line == null) {
                    break;
                }
                System.out.println(line);
            }
            sleep(3000);
        } catch (Exception e) {
            System.out.println("Terminal cant open !");
        }
        double startTime = System.nanoTime();
        double stopTime = System.nanoTime();
        double elapsedTime = stopTime - startTime;
        double seconds = (double) elapsedTime / 1000000000.0;
        System.out.printf("\nPush to Git ended at %.9f seconds", seconds);
        CountCharacter CC = new CountCharacter();
        CC.countCharacter();
    }
}
