package com.bookin.bookmanagement.util;

import com.bookin.bookmanagement.constant.Country;
import org.junit.jupiter.api.Test;

import java.text.NumberFormat;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

class CurrencyUtilTest {

    @Test
    void convertCurrency() {
        NumberFormat france = NumberFormat.getCurrencyInstance(Locale.FRANCE);
        double usdToEuroRate = 0.88;
        double euro = 12 * usdToEuroRate;

        String fValue = CurrencyUtil.convertCurrency("12", Country.FRANCE);
        assertEquals(fValue,france.format(euro));


        NumberFormat us = NumberFormat.getCurrencyInstance(Locale.US);
        String uValue = CurrencyUtil.convertCurrency("12", Country.US);
        assertEquals(uValue,us.format(12));


        NumberFormat india = NumberFormat.getCurrencyInstance(new Locale("en", "IN"));
        double inr = 12 * 74.42;
        String iValue = CurrencyUtil.convertCurrency("12",Country.INDIA);
        assertEquals(iValue, india.format(inr));

    }
}