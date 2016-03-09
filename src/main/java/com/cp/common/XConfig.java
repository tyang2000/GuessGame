package com.cp.common;

import java.util.Properties;


/**
 * @author tyang
 *
 */
public class XConfig {

    private static XConfig singleton;

    private int upperBound;

    private int lowerBound;

    public int getLowerBound() {
        return lowerBound;
    }

    public int getUpperBound() {
        return upperBound;
    }

    // 	A private constructor(): To make sure no outside caller can create an instance
    private XConfig() { }

    // must synchronized
    public static synchronized XConfig getInstance( ) {
        if (singleton == null) {
            singleton = new XConfig();	// new from inside the class
            singleton.initialize();
        }
        return singleton;
    }

    //
    private void initialize() {

        Properties properties = System.getProperties();
        upperBound = getInt("upperbound", EConstants.UPPER_BOUND.getValue());
        lowerBound = getInt("lowerbound", EConstants.LOWER_BOUND.getValue());
    }

    static String getString(String key, String def) {

        String val = def;

        try {
            val = System.getProperties().getProperty(key);
        }
        catch(Exception e) {
            System.err.println(e.getMessage());
        }

        return val.trim();
    }

    static int getInt(String key, int def) {

        int val = def;

        try {
            String str = System.getProperties().getProperty(key);
            if (str!=null) val = Integer.parseInt(str);
        }
        catch(Exception e) {
            System.err.println(e.getMessage());
        }

        return val;
    }
}
