package com.cigo.software;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.cigo.software.util.ExcelWriter;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:com/cigo/software/junit-application-context.xml" })
@WebAppConfiguration
public class TestExcel {

	private static final Logger log = LoggerFactory.getLogger(TestExcel.class);

	@Autowired
	private ExcelWriter excelWriter;

	@Test
	public void loadExcelAndWrite() throws Exception {
		log.debug("##### Start test load exist excel!");
		String directoryName = "report";

		String path = System.getProperty("java.io.tmpdir");
		createDirectory(directoryName, path);

		Date created = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");

		String fileName = "test-" + sdf.format(created) + ".xlsx";

		String fileAndPathName = path + File.separator + directoryName + File.separator + fileName;

		List<Map<String, Object>> listPrint = new ArrayList<Map<String, Object>>();
		Map<String, Object> myMap = new HashMap<String, Object>();

		myMap.put("test", "1234");
		listPrint.add(myMap);

		Workbook wb = new XSSFWorkbook();
		String sheetName = "sheetNameTest";
		createReportExcel(fileAndPathName, wb, sheetName, listPrint);

		listPrint = new ArrayList<Map<String, Object>>();
		myMap = new HashMap<String, Object>();

		myMap.put("test", "5678");
		listPrint.add(myMap);

		wb = new XSSFWorkbook(new FileInputStream(fileAndPathName));
		sheetName = "sheetNameTest2";
		createReportExcel(fileAndPathName, wb, sheetName, listPrint);
	}

	private void createDirectory(String directoryName, String path) {
		File theDir = new File(path + File.separator + directoryName);

		if (!theDir.exists()) {
			theDir.mkdir();
		}

	}

	private void createReportExcel(String fileName, Workbook wb, String sheetName, List<Map<String, Object>> listPrint) throws FileNotFoundException,
			IOException {
		log.debug("nome sheet " + sheetName);

		Sheet sheet = wb.createSheet(sheetName);

		String listField = "test";

		int rowIndex = 1;

		for (Map<String, Object> result : listPrint) {
			Row row = sheet.createRow(rowIndex);
			excelWriter.writeRow(result, row, listField.split(","));
			rowIndex++;
		}

		FileOutputStream fileOut;

		fileOut = new FileOutputStream(fileName);
		wb.write(fileOut);
		fileOut.close();
	}

}
