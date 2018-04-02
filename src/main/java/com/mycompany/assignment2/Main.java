package com.mycompany.assignment2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import static java.lang.Thread.sleep;
import java.util.Iterator;
import java.util.Scanner;
import java.util.StringTokenizer;



public class Main {

    public static void main(String[] args) throws IOException, InvalidFormatException {
        ExcelToMD excel = new ExcelToMD();
        excel.readFile();
    }

    public static class ExcelToMD {

        Writer writer = null;

        boolean test = true;

        public void readFile() throws IOException, InvalidFormatException {
            createFile();

            Workbook workbook = WorkbookFactory.create(new File("C:\\Users\\ROG_PC\\practicumstudent.xlsx"));
            Sheet sheet = workbook.getSheetAt(0);

            // Create a DataFormatter to format and get each cell's value as String
            DataFormatter dataFormatter = new DataFormatter();

            writer.write("# Asignment 2 STIW3054 A172 - 243102\n\n");
            writer.write("## STIX 3912 Practicum\n\n");
            writer.write("### `U5a (BSc IT)`\n\n");

            Iterator<Row> rowIterator = sheet.rowIterator();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();

                // Iterate over the columns of the current row
                Iterator<Cell> cellIterator = row.cellIterator();

                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    String cellValue = dataFormatter.formatCellValue(cell);
                    System.out.print(cellValue + "\t | \t");
                    writer.write(cellValue + " | ");
                }
                System.out.println();
                writer.write("\n");
                createTableLine();
            }
            closeFile();

            PushGithub github = new PushGithub();
            github.runGit();
        }

        public void createFile() throws IOException {
            File file = new File("C:/Users/ROG_PC/243102-STIW3054-A172-A2.wiki/Markdown.md");
            writer = new BufferedWriter(new FileWriter(file));
        }

        public void createTableLine() throws IOException {

            if (test == true) {
                writer.write(":--:|:--:|--|-- \n");
                test = false;
            }
        }

        public void closeFile() {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static class PushGithub {

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
        countCharacter();
        }
    }
    
    public static void countCharacter() throws IOException{
           long startTime = System.nanoTime();
           System.out.println();
           System.out.println ("Counting character...");            
        File file = new File("C:/Users/ROG_PC/243102-STIW3054-A172-A2.wiki/Markdown.md");
            Scanner in = new Scanner(file);
            int words = 0;
            int chars = 0;

          while(in.hasNextLine())  {
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

