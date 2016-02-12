package com.cigo.software.util;

import java.text.SimpleDateFormat;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ExcelWriter {

	private static final Logger log = LoggerFactory.getLogger(ExcelWriter.class);

	public void writeHeaderOfSheet(Sheet sheet, String[] headers, CellStyle myStyle) {
		Row row = sheet.createRow(0);
		int cellIndex = 0;
		for (String header : headers) {
			Cell cell = row.createCell(cellIndex++);

			cell.setCellValue(header);
			if (myStyle != null)
				cell.setCellStyle(myStyle);
		}
	}

	public void writeRow(Map<String, Object> result, Row row, String[] headers) {
		int cellIndex = 0;

		for (String header : headers) {
			final Object value = result.get(header);
			writeCell(value, row, cellIndex);
			cellIndex++;
		}
	}

	public void writeCell(Object value, Row row, int cellIndex) {
		Cell cell = row.createCell(cellIndex);
		if (value != null) {
			cell.setCellValue(convertStringValue(value));
		} else {
			cell.setCellValue("");
		}
	}

	private String convertStringValue(Object value) {
		if (value instanceof java.lang.String)
			return value.toString();
		else if (value instanceof java.lang.Integer)
			return String.valueOf(value);
		else if (value instanceof java.math.BigDecimal)
			return String.valueOf(value);
		else if (value instanceof java.sql.Timestamp)
			return new SimpleDateFormat("MM/dd/yyyy").format(value);
		else {
			log.error("Type error - value : " + value + " type: " + value.getClass());
			return "";

		}

	}
}
