package com.xlsfile;

import com.configuration.Configuration;
import com.dto.ReportRow;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.util.List;

/**
 * Created by Denys Kovalenko on 12/30/2014.
 */
public class FileGenerator {

    public void generateFile(List<ReportRow> reportRows) {
        try (FileOutputStream fileOut = new FileOutputStream(Configuration.FILE_PATH + Configuration.FILE_NAME)) {
            XSSFWorkbook workbook = new XSSFWorkbook();
            generateSheet(workbook, reportRows);

            workbook.write(fileOut);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    private void generateSheet(XSSFWorkbook workbook, List<ReportRow> reportRows) {
        XSSFSheet sheet = workbook.createSheet("SQDF VSB TxReport");
        XSSFRow header = sheet.createRow(0);

        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setAlignment(CellStyle.ALIGN_CENTER);

        // Header cells
        Cell cell1 = header.createCell(0);
        cell1.setCellStyle(cellStyle);
        cell1.setCellValue("VIN");

        Cell cell2 = header.createCell(1);
        cell2.setCellStyle(cellStyle);
        cell2.setCellValue("EVENT ID");

        Cell cell3 = header.createCell(2);
        cell3.setCellStyle(cellStyle);
        cell3.setCellValue("RECEIVED ON");

        Cell cell4 = header.createCell(3);
        cell4.setCellStyle(cellStyle);
        cell4.setCellValue("SERVICE TYPE");

        // Value cells
        int i = 1;
        for (ReportRow reportRow : reportRows) {
            XSSFRow row = sheet.createRow(i++);
            row.createCell(0).setCellValue(reportRow.getVin());
            row.createCell(1).setCellValue(reportRow.getCorrelationId());
            row.createCell(2).setCellValue(reportRow.getCreateTimestamp());
            row.createCell(3).setCellValue(reportRow.getServiceType());
        }

        sheet.autoSizeColumn(0);
        sheet.autoSizeColumn(1);
        sheet.autoSizeColumn(2);
        sheet.autoSizeColumn(3);
    }

}
