package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

	//DataProvider Method 1:
	@DataProvider(name="LoginData")
	public String[][] getData() throws IOException {
		String path = ".\\testData\\opencart_LoginData.xlsx";	//taking xl file from testData folder
		
		ExcelUtility xlutil = new ExcelUtility(path);	//creating an object of ExcelUtility class
		
		int totalRowCount = xlutil.getRowCount("Sheet1");
		//System.out.println("Total number of rows: "+totalRowCount);
		int totalColCount = xlutil.getCellCount("Sheet1",1);
		//System.out.println("Total number of columns: "+totalColCount);
		
		//created two dimensional array which can store data from excel file
		String loginData[][] = new String[totalRowCount][totalColCount];
		
		//read data from excel and storing into 2D array
		for(int i=1; i<=totalRowCount;i++) {		//1 - as we do not need header part (1st row)
			for(int j=0;j<totalColCount;j++) {		//0
				loginData[i-1][j] = xlutil.getCellData("Sheet1", i, j);	//1,0
			}
		}
		return loginData;
	}
	
	//DataProvider Method 2:
	
	//DataProvider Method 3:
	
	//DataProvider Method 4:
}
