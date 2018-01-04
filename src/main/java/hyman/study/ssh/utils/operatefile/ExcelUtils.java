package hyman.study.ssh.utils.operatefile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Array;
import java.util.Arrays;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

/**
 * excel操作工具类
 * @author Administrator
 *
 */
public class ExcelUtils {
	public static Workbook getWorkbook(String filePath){
		Workbook workbook=null;
		try {
			String realPath=FileUtils.getRealPath(filePath);
			InputStream in=new FileInputStream(realPath);
			workbook=Workbook.getWorkbook(in);
		} catch (Exception e) {
			System.out.println("获取workbook出错！");
			e.printStackTrace();
		}
		
		return workbook;
	}
	
	
	public static void printExcel(String filePath){
		Workbook workbook=getWorkbook(filePath);
		System.out.println("sheets count: " + workbook.getNumberOfSheets());
		System.out.println("sheets names: " + Arrays.toString(workbook.getSheetNames()));
		Sheet sheet=workbook.getSheet(0);
		
		
	}
}
