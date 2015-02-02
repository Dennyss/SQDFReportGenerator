package com;

import com.configuration.Configuration;
import com.db.SQDFReportDAO;
import com.dto.ReportRow;
import com.email.EmailSender;
import com.xlsfile.FileGenerator;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Denys Kovalenko on 12/30/2014.
 */
public class SQDFReportGenerator {

    public static void main(String args[]){
        SQDFReportDAO sqdfReportDAO = new SQDFReportDAO();
        int recordsNumber = sqdfReportDAO.getCount();
        System.out.println("Total rows count: " + recordsNumber);

        List<ReportRow> reportRows = sqdfReportDAO.retrieveReportData();
        sqdfReportDAO.closeConnection();

        FileGenerator fileGenerator = new FileGenerator();
        fileGenerator.generateFile(reportRows, Configuration.getFileName(getToday()));

        //int recordsNumber = 72790;
        EmailSender emailSender = new EmailSender();
        emailSender.sendEmail(recordsNumber, Configuration.getFileName(getToday()));
    }

    private static String getToday() {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormatter.format(new Date(System.currentTimeMillis()));
    }
}
