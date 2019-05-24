package view;

import control.DateLabelFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import java.text.ParseException;
import java.util.Date;
import java.util.Properties;


public class DatePicker {
    private JDatePickerImpl datePicker;

    DatePicker(){
        UtilDateModel model = new UtilDateModel();
        model.setSelected(true);
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
        datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
    }

    public JDatePickerImpl getDatePicker(){
        return datePicker;
    }

    public Date getDate() throws ParseException {
        return (Date) datePicker.getModel().getValue();
    }

}
