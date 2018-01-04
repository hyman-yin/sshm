package sshm;

import org.junit.Test;

import hyman.study.ssh.utils.operatefile.ExcelUtils;

public class ExcelUtilsTest {
	String filePath="excel/test.xls";

	@Test
	public void testPrintExcel(){
		ExcelUtils.printExcel(filePath);
	}
}
