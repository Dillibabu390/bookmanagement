package com.bookin.bookmanagement.util;
import com.bookin.bookmanagement.constant.Country;
import com.bookin.bookmanagement.usermanagement.service.UserHelperService;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.NumberFormat;
import java.util.Locale;

public class CurrencyUtil {



    private CurrencyUtil() {

    }

    public static String convertCurrency(String stringValue, Country country) {

         int value = Integer.parseInt(stringValue);


        switch (country) {
            case FRANCE:
                NumberFormat france = NumberFormat.getCurrencyInstance(Locale.FRANCE);
                double usdToEuroRate = 0.88;
                double euro = value * usdToEuroRate;
                return france.format(euro);
            case US:
                NumberFormat us = NumberFormat.getCurrencyInstance(Locale.US);
                return us.format(value);
            case INDIA:
                NumberFormat india = NumberFormat.getCurrencyInstance(new Locale("en", "IN"));
                double inr = value * 74.42;
                return india.format(inr);
        }
        return null;
    }


}

