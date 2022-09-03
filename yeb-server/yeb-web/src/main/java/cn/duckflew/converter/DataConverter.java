package cn.duckflew.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class DataConverter implements Converter<String, LocalDate>
{

    @Override
    public LocalDate convert(String source)
    {
        try
        {
            return LocalDate.parse(source, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
