package model;

import javax.swing.JFormattedTextField;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateFormat extends JFormattedTextField.AbstractFormatter{
    private String pattern = "d-MM-yyyy";
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

    @Override
    public Object stringToValue(String text) throws ParseException {
        return simpleDateFormat.parseObject(text);
    }

    @Override
    public String valueToString(Object value) throws ParseException {
        if (value != null){
        Calendar calendar = (Calendar) value;
        return simpleDateFormat.format(calendar.getTime());}
        else return "";
    }
}
