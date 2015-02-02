package com.db;

import com.configuration.Configuration;
import com.dto.ReportRow;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by Denys Kovalenko on 12/30/2014.
 */
public class SQDFReportDAO {

    private Connection connection;
    private SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss aaa");

    public SQDFReportDAO() {
        formatter.setTimeZone(TimeZone.getTimeZone("CST"));

        try {
            Class.forName(Configuration.DB_DRIVER);
            connection = DriverManager.getConnection(Configuration.DB_URL, Configuration.DB_USERNAME, Configuration.DB_PASSWORD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getCount() {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(Configuration.COUNT_QUERY);
            statement.setTimestamp(1, getStartTimestamp());
            statement.setTimestamp(2, getEndTimestamp());
            resultSet = statement.executeQuery();
            resultSet.next();
            return resultSet.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(statement);
            close(resultSet);
        }

        return 0;
    }


    public List<ReportRow> retrieveReportData() {
        List<ReportRow> reportRows = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(Configuration.DATA_QUERY);
            statement.setTimestamp(1, getStartTimestamp());
            statement.setTimestamp(2, getEndTimestamp());
            resultSet = statement.executeQuery();
            int i = 0;
            while (resultSet.next()) {
                ReportRow reportRow = new ReportRow();
                reportRow.setVin(resultSet.getString(1));
                reportRow.setCorrelationId(resultSet.getString(2));
                reportRow.setCreateTimestamp(resultSet.getString(3));
                reportRow.setServiceType(resultSet.getString(4));
                reportRows.add(reportRow);
                i++;
                if(i % 1000 == 0){
                    System.out.println("Read " + i + " records");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(statement);
            close(resultSet);
        }

        return reportRows;
    }

    private Timestamp getStartTimestamp() throws ParseException {
        java.util.Date date = formatter.parse(getToday() + " 05:00:00 AM");
        return new Timestamp(date.getTime());
    }

    private Timestamp getEndTimestamp() throws ParseException {
        java.util.Date date = formatter.parse(getTomorrow() + " 05:00:00 AM");
        return new Timestamp(date.getTime());
    }

    private String getToday() {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MMM-yyyy");
        return dateFormatter.format(new Date(System.currentTimeMillis()));
    }

    private String getTomorrow() {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MMM-yyyy");
        return dateFormatter.format(new Date(System.currentTimeMillis() + 86400000));
    }

    public void closeConnection() {
        if (connection == null) {
            return;
        }
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void close(Statement statement) {
        if (statement == null) {
            return;
        }
        try {
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void close(ResultSet resultSet) {
        if (resultSet == null) {
            return;
        }
        try {
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
