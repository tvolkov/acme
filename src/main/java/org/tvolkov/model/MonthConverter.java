package org.tvolkov.model;

import javax.persistence.AttributeConverter;
import java.time.YearMonth;

public class MonthConverter implements AttributeConverter<YearMonth, String> {
    @Override
    public String convertToDatabaseColumn(YearMonth attribute) {
        return attribute.toString();
    }

    @Override
    public YearMonth convertToEntityAttribute(String dbData) {
        return YearMonth.parse(dbData);
    }
}