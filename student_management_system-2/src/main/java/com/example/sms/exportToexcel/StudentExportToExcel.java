package com.example.sms.exportToexcel;

import com.example.sms.entity.Student;
import org.apache.catalina.User;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.springframework.stereotype.Component;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Component
public class StudentExportToExcel {

    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<Student> studentList;

    public StudentExportToExcel(List<Student> studentList) {
        this.studentList = studentList;
        workbook = new XSSFWorkbook();
        sheet = workbook.createSheet("Students");


    }

    public void  writeHeaderRow(){
    Row row = sheet.createRow(0);

        Cell cell = row.createCell(0);
        cell.setCellValue("First Name ");

         cell = row.createCell(1);
        cell.setCellValue("Last Name");

         cell = row.createCell(2);
        cell.setCellValue("Email");




    }
    public void writeDataRows(){

        int rowCount = 1;
        for (Student studen: studentList)
        {
            Row row = sheet.createRow(rowCount++);

            Cell cell = row.createCell(0);
            cell.setCellValue(studen.getFirstName());

            cell = row.createCell(1);
            cell.setCellValue(studen.getLastName());

            cell = row.createCell(2);
            cell.setCellValue(studen.getEmail());

        }
    }

    public void export(HttpServletResponse response) throws IOException {

        writeHeaderRow();
        writeDataRows();
       ServletOutputStream outputStream=  response.getOutputStream();
       workbook.write(outputStream);
       workbook.close();
       outputStream.close();

    }
}
