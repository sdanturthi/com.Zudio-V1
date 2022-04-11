package com.Zudio.Utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLUtils {
	 static FileInputStream fis;
	 static FileOutputStream fos;
	 static XSSFWorkbook wb;
	 static XSSFSheet ws;
	 static XSSFRow row;
	 static XSSFCell col;
	 
	 public int getLastRow(String xlPath, String Sheet1) throws IOException {
		 fis = new FileInputStream(xlPath);
		 wb = new XSSFWorkbook(fis);
		 ws = wb.getSheet(Sheet1);
		 int lastRowNum =  ws.getLastRowNum();
		 wb.close();
		 fis.close();
		 return lastRowNum;	 
	 }
	 
	 public int getLastCol(String xlPath, String Sheet1, int rowNum) throws IOException{		 
		 fis = new FileInputStream(xlPath);
		 wb = new XSSFWorkbook(fis);
		 ws = wb.getSheet(Sheet1);
		 row = ws.getRow(rowNum);
		 int lastCellNum = row.getLastCellNum();		 
		 wb.close();
		 fis.close();
		 return lastCellNum;		 
	 }
	 
	 public String getCellData(String xlPath, String Sheet1, int rowNum, int colNum) throws IOException{
		 fis = new FileInputStream(xlPath);
		 wb = new XSSFWorkbook(fis);
		 ws = wb.getSheet(Sheet1);
		 row = ws.getRow(rowNum);
		 col = row.getCell(colNum);	
		 String cellValue;
		 
		 try {
			 DataFormatter formatter =  new DataFormatter();
			 cellValue = formatter.formatCellValue(col);
			 }
		 
		 catch(Exception e) {
			 cellValue = "";			 
		 }
		 
		 wb.close();
		 fis.close();
		 
		 return cellValue;		 
	 }
	 
	 public void setCellValue(String xlPath, String Sheet1, int rowNum, int colNum, String cellData) throws IOException {
		 fis = new FileInputStream(xlPath);
		 wb = new XSSFWorkbook(fis);
		 ws = wb.getSheet(Sheet1);		
		 row = ws.getRow(rowNum);
		 
		 if(row == null || row.getLastCellNum() <=0) {
			row = ws.createRow(rowNum);
		 }
		 
		 col = row.createCell(colNum);
		 col.setCellValue(cellData);
		 fos= new FileOutputStream(xlPath);
		 wb.write(fos);		 
		 wb.close();
		 fis.close();
		 fos.close(); 
		 
	 }

}
