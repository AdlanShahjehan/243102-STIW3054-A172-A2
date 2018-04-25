package com.mycompany.assignment2;

import java.io.IOException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

public class Main {

    public static void main(String[] args) throws IOException, InvalidFormatException {
        ExcelToMD excel = new ExcelToMD();
        excel.readFile();
    }
}
