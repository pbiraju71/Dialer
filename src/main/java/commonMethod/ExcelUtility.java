package commonMethod;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {

	public static FileInputStream fi;
	public static FileOutputStream fo;
	public static XSSFWorkbook wb;
	public static XSSFSheet ws;
	public static XSSFRow row;
	public static XSSFCell cell;
	public static CellStyle style;

	public static int getRowCount(String xlfilePath, String xlsheet) throws IOException {
		fi = new FileInputStream(xlfilePath);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(xlsheet);
		int rowcount = ws.getLastRowNum();
		wb.close();
		fi.close();
		return rowcount;
	}

	public static int getCellCount(String xlfilePath, String xlsheet, int rownum) throws IOException {
		fi = new FileInputStream(xlfilePath);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(xlsheet);
		row = ws.getRow(rownum);
		short cellcount = row.getLastCellNum();
		wb.close();
		fi.close();
		return cellcount;
	}

	public static String getCellData(String xlfilePath, String xlsheet, int rownum, int colnum) throws IOException {
		fi = new FileInputStream(xlfilePath);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(xlsheet);
		row = ws.getRow(rownum);
		cell = row.getCell(colnum);
		String data;
		try {
			// data=cell.toString();
			DataFormatter formattar = new DataFormatter();
			data = formattar.formatCellValue(cell);
		} catch (Exception e) {
			data = "";
		}
		wb.close();
		fi.close();
		return data;
	}

	public static void setCellData(String xlfilePath, String xlsheet, int rownum, int colnum, String data)
			throws IOException {
		fi = new FileInputStream(xlfilePath);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(xlsheet);
		row = ws.getRow(rownum);
		cell = row.createCell(colnum);
		cell.setCellValue(data);
		fo = new FileOutputStream(xlfilePath);
		wb.write(fo);
		wb.close();
		fi.close();
		fo.close();
	}

	public static void fillGreenColor(String xlfilePath, String xlsheet, int rownum, int colnum) throws IOException {
		fi = new FileInputStream(xlfilePath);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(xlsheet);
		row = ws.getRow(rownum);
		cell = row.getCell(colnum);
		style = wb.createCellStyle();
		style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		cell.setCellStyle(style);
		fo = new FileOutputStream(xlfilePath);
		wb.write(fo);
		wb.close();
		fi.close();
		fo.close();
	}
	
	public static void fillRedColor(String xlfilePath, String xlsheet, int rownum, int colnum) throws IOException {
		fi = new FileInputStream(xlfilePath);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(xlsheet);
		row = ws.getRow(rownum);
		cell = row.getCell(colnum);
		style = wb.createCellStyle();
		style.setFillForegroundColor(IndexedColors.RED.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		cell.setCellStyle(style);
		fo = new FileOutputStream(xlfilePath);
		wb.write(fo);
		wb.close();
		fi.close();
		fo.close();
	}
	
	public static ArrayList<String> getDataFromExcel(String testcaseName,String xlfilePath) throws IOException {
		ArrayList<String> a = new ArrayList<String>();
		FileInputStream fis = new FileInputStream(xlfilePath);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		int sheets = workbook.getNumberOfSheets();
		for (int i = 0; i < sheets; i++) {
			if (workbook.getSheetName(i).equalsIgnoreCase("Sheet1")) {
				XSSFSheet sheet = workbook.getSheetAt(i);
				// Identify testcases column by scanning the entire 1st row
				Iterator<Row> rows = sheet.iterator();
				Row firstrow = rows.next();
				Iterator<Cell> ce = firstrow.cellIterator();
				int k = 0;
				int column = 0;
				while (ce.hasNext()) {
					Cell value = ce.next();
					if (value.getStringCellValue().equalsIgnoreCase("TestCaseName")) {
						column = k;
					}
					k++;
				}
				System.out.println(column);
				while (rows.hasNext()) {
					Row r = rows.next();
					if (r.getCell(column).getStringCellValue().equalsIgnoreCase(testcaseName)) {
						Iterator<Cell> cv = r.cellIterator();
						while (cv.hasNext()) {
							Cell c = cv.next();
							if (c.getCellType() == CellType.STRING) {
								a.add(cv.next().getStringCellValue());
							} else {
								a.add(NumberToTextConverter.toText(c.getNumericCellValue()));

							}

						}
					}
				}
			}

		}
		return a;

	}

}
