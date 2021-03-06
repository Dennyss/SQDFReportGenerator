package com.configuration;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Denys Kovalenko on 2/2/2015.
 */
public class Configuration {
    // Configuration common for all profiles
    public static final String COUNT_QUERY = "";
    public static final String DATA_QUERY = "";
    public static final String MY_EMAIL_ADDRESS = "";
    public static final String MY_EMAIL_PASSWORD = "";
    public static final String TO_RECIPIENTS_LIST = "";
    public static final String CC_RECIPIENTS_LIST = "";
    public static final String FILE_NAME = getFileName();

    public static String getMailBody(int recordsNumber) {
        return "";
    }

    private static String getFileName() {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        String today = dateFormatter.format(new Date(System.currentTimeMillis()));
        return "FIT_SQDF_VSB_TxReport_" + today + ".xlsx";
    }


    // Configuration different for all profiles
    // Local configuration
    public static final String FILE_PATH = "e:\\";

    public static final String DB_DRIVER = "";
    public static final String DB_URL = "";
    public static final String DB_USERNAME = "";
    public static final String DB_PASSWORD = "";

    // Server configuration
    //public static final String FILE_PATH = "";

    //public static final String DB_DRIVER = "";
    //public static final String DB_URL = "";
    //public static final String DB_USERNAME = "";
    //public static final String DB_PASSWORD = "";
}
