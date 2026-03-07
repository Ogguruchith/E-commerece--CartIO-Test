package com.cartiq.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelUtils {

	
	public static Object[][] getData() throws IOException
	{
		File excelFile =new File("src/test/resources/testdata/LoginData.xlsx");
		FileInputStream fis=new FileInputStream(excelFile);
		XSSFWorkbook workbook=new XSSFWorkbook(fis);
		XSSFSheet sheet=workbook.getSheet("Sheet1");
		int totalrows=sheet.getLastRowNum();
		int totalcol=sheet.getRow(0).getLastCellNum();
		
		Object[][] data=new Object[totalrows][totalcol];
		DataFormatter df=new DataFormatter();
		for(int i=0;i<totalrows;i++)
		{
			for(int j=0;j<totalcol;j++)
			{
				
				data[i][j]=df.formatCellValue(sheet.getRow(i+1).getCell(j));
			}
		}
		workbook.close();
        fis.close();
		return data;
		
		
	}
}
