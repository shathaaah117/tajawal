package util;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class ExcelWriter {
    static XSSFWorkbook workbook = new XSSFWorkbook();

    static XSSFSheet sheet = workbook.createSheet("Sheet#1");

    public static void writeExcel(String outputFile) {
        File file = new File(outputFile);

        //Add header
        sheet.createRow(0).createCell(0).setCellValue("input");
        sheet.createRow(0).createCell(1).setCellValue("response time");
        sheet.createRow(0).createCell(2).setCellValue("HTTP-code");
        sheet.createRow(0).createCell(3).setCellValue("response body");
        sheet.createRow(0).createCell(4).setCellValue("Assertion Result");

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            workbook.write(fileOutputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void appendToExcel(String outputFile,ArrayList<String>output)
    {
        int count=0;
        for(;count<output.size();count++) {
            sheet.createRow(sheet.getLastRowNum() + 1).createCell(count).setCellValue(output.get(count));
        }
    }
}

