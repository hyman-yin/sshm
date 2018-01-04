package hyman.study.ssh.utils.operatefile;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

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
		Sheet psnSheet=workbook.getSheet(0);
		Sheet orgSheet=workbook.getSheet(1);
		int psnRows=psnSheet.getRows();
		int orgRows=orgSheet.getRows();
		int psnColumns=psnSheet.getColumns();
		int orgColumns=orgSheet.getColumns();
		
		for(int i=0;i<psnRows;i++){
			for(int j=0;j<psnColumns;j++){
				Cell cell=psnSheet.getCell(j, i);
				System.out.print(cell.getContents()+"\t\t");
			}
			System.out.println();
		}
		
		System.out.println();
		System.out.println();
		System.out.println();
		
		for(int i=0;i<orgRows;i++){
			for(int j=0;j<orgColumns;j++){
				Cell cell=orgSheet.getCell(j, i);
				System.out.print(cell.getContents()+"\t\t");
			}
			System.out.println();
		}
	}
	
	
	
	public static void createExcel(){
		String filePath="excel/test2.xls";
		String realPath=FileUtils.getRealPath(filePath);
		OutputStream out;
		try {
			out = new FileOutputStream(realPath);
			WritableWorkbook workbook=Workbook.createWorkbook(out);
			
			WritableSheet sheet=workbook.createSheet("第一张sheet", 0);
			
			//添加合并单元格，第一个参数是起始列，第二个参数是起始行，第三个参数是终止列，第四个参数是终止行
			sheet.mergeCells(0, 0, 2, 0);
			sheet.addCell(new Label(0,0,"人员信息"));
			
			//列，行，名称
			Label label1=new Label(0, 1, "姓名");
			Label label2=new Label(1, 1, "年龄");
			Label label3=new Label(2, 1, "性别");
			
			sheet.addCell(label1);
			sheet.addCell(label2);
			sheet.addCell(label3);
			
			
			sheet.addCell(new Label(0,2,"张三"));
			sheet.addCell(new Label(1,2,"18"));
			sheet.addCell(new Label(2,2,"M"));
			
			sheet.addCell(new Label(0,3,"李四"));
			sheet.addCell(new Label(1,3,"19"));
			sheet.addCell(new Label(2,3,"M"));
			
			sheet.addCell(new Label(0,4,"王五"));
			sheet.addCell(new Label(1,4,"20"));
			sheet.addCell(new Label(2,4,"M"));

			workbook.write();
			workbook.close();
			out.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	
}
