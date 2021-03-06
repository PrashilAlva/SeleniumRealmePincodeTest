package com.realme.utils;

public class TestDataProvider {

	TestUtil obj;
	
	public TestDataProvider() {
		obj=new TestUtil();
	}

	public Object[][] dataProvider() {
		int row = obj.getRowCount();
		int cells = obj.getColCount();

		Object testData[][] = new Object[row - 1][cells];

		for (int i = 1; i < row; i++) {
			for (int j = 0; j < cells; j++) {
				if (j == 0)
					testData[i - 1][j] = obj.getNumericCellValue(i, j);
				else
					testData[i - 1][j] = obj.getStringCellValue(i, j);
			}
		}
		
		return testData;

	}

}
