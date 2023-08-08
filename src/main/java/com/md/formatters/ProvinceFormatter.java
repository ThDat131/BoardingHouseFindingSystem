package com.md.formatters;

import com.md.pojo.Provinces;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

public class ProvinceFormatter implements Formatter<Provinces> {


    @Override
    public Provinces parse(String provinceId, Locale locale) throws ParseException { //Template -> Java
        return new Provinces(provinceId);
    }

    @Override
    public String print(Provinces province, Locale locale) { // Java -> Template
        return String.valueOf(province.getCode());
    }
}
