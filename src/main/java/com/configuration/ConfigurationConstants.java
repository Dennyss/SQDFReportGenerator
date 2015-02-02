package com.configuration;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Denys Kovalenko on 2/2/2015.
 */
public class ConfigurationConstants {
    // Configuration common for all profiles
    public static final String COUNT_QUERY = "";
    public static final String DATA_QUERY = "";
    public static final String MY_EMAIL_ADDRESS = "";
    public static final String MY_EMAIL_PASSWORD = "";
    public static final String TO_RECIPIENTS_LIST = "";
    public static final String CC_RECIPIENTS_LIST = "";
    public static final String FILE_NAME = getFileName();
    public static final int ONE_DAY = 86400000;

    public static String getMailBody(int recordsNumber) {
        return "";
    }

    public static String getFileName() {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        String yesterday = dateFormatter.format(new Date(System.currentTimeMillis() - ONE_DAY));
        return "FIT_SQDF_VSB_TxReport_" + yesterday + ".xlsx";
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

