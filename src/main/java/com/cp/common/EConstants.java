package com.cp.common;

/**
 * Store constant values of the application
 */
public enum EConstants {

    UPPER_BOUND(100),       // the upper bound value
    LOWER_BOUND(1)          // the lower bound value
    ;

    private int nValue;

    EConstants(final int val) {
        nValue = val;
    }

    public int getValue() { return nValue; }
}
