package com.realme.utils;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class TestUtil {

	private XSSFWorkbook workbook;
	private Sheet sheet;

	public TestUtil() {
		try {
			String excelPath = System.getProperty("user.dir");
			workbook = new XSSFWorkbook(excelPath + "/data/TestData.xlsx");
			sheet = workbook.getSheet("Sheet1");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int getRowCount() {
		return sheet.getPhysicalNumberOfRows();
	}

	public int getColCount() {
		return sheet.getRow(0).getPhysicalNumberOfCells();
	}

	public String getStringCellValue(int rowNo, int colNo) {
		return sheet.getRow(rowNo).getCell(colNo).getStringCellValue();
	}

	public int getNumericCellValue(int rowNo, int colNo) {
		return (int) sheet.getRow(rowNo).getCell(colNo).getNumericCellValue();
	}

}
