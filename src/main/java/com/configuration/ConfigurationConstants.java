package com.configuration;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Denys Kovalenko on 2/2/2015.
 */
public class ConfigurationConstants {
    // Configuration common for all profiles
    public static final String COUNT_QUERY = "select count(*) from vhcl_rpt_hist where cretd_tmst >= ? AND cretd_tmst < ? and data_indcr_cd='1' and rpt_type_nme='SQDF' ORDER BY CRETD_TMST ASC";
    public static final String DATA_QUERY = "select VIN_NBR, CORLTN_ID, CRETD_TMST, SRVC_TYPE_NME from vhcl_rpt_hist where cretd_tmst >= ? AND cretd_tmst < ? and data_indcr_cd='1' and rpt_type_nme='SQDF' ORDER BY CRETD_TMST ASC";
    public static final String MY_EMAIL_ADDRESS = "denys.kovalenko@globallogic.com";
    public static final String MY_EMAIL_PASSWORD = "Mynameisden21#";
    public static final String TO_RECIPIENTS_LIST = "denys.kovalenko@globallogic.com";
    public static final String CC_RECIPIENTS_LIST = "";
    public static final String FILE_NAME = getFileName();
    public static final int ONE_DAY = 86400000;

    public static String getMailBody(int recordsNumber) {
        return "Hello All,\n\n" +
                "Please find in attachment the auto generated report of SQDF transactions performed on FIT.\n" +
                "Total number of records is: " + recordsNumber + ".\n\n" +
                "Thanks,\n" +
                "Denys Kovalenko | Software Engineer\n" +
                "GlobalLogic\n" +
                "M 1 (913) 701-0774  S denis.kovalenko\n" +
                "www.globallogic.com\n" +
                "\n" +
                "http://www.globallogic.com/email_disclaimer.txt";
    }

    public static String getFileName() {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        String yesterday = dateFormatter.format(new Date(System.currentTimeMillis() - ONE_DAY));
        return "FIT_SQDF_VSB_TxReport_" + yesterday + ".xlsx";
    }


    // Configuration different for all profiles
    // Local configuration
//    public static final String FILE_PATH = "e:\\";
//
//    public static final String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";
//    public static final String DB_URL = "jdbc:oracle:thin:@(DESCRIPTION =(ADDRESS = (PROTOCOL = TCP)(HOST=localhost)(PORT = 8985))(CONNECT_DATA =(SERVICE_NAME = VSBPAPPS)(FAILOVER_MODE = (TYPE = SELECT) (METHOD = BASIC) (RETRIES = 180) (DELAY =5))))";
//    public static final String DB_USERNAME = "vsb_app_user";
//    public static final String DB_PASSWORD = "App!Pr0d";

    // Server configuration
    public static final String FILE_PATH = "/share/vsb/SQDFReportGenerator/Reports/";

    public static final String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";
    public static final String DB_URL = "jdbc:oracle:thin:@(DESCRIPTION =(ADDRESS = (PROTOCOL = TCP)(HOST=prod-cvp-vsb-scan.c9.com)(PORT = 1521))(CONNECT_DATA =(SERVICE_NAME = VSBPAPPS)(FAILOVER_MODE = (TYPE = SELECT) (METHOD = BASIC) (RETRIES = 180) (DELAY =5))))";
    public static final String DB_USERNAME = "vsb_app_user";
    public static final String DB_PASSWORD = "App!Pr0d";
}
