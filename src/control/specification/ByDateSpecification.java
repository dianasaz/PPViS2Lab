package control.specification;

import model.Tournament;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ByDateSpecification  implements Specification<Tournament> {
    private String searchDate;
    public final static SimpleDateFormat dateFormat = new SimpleDateFormat("d.MM.yyyy");

    public ByDateSpecification(Date searchDate){
        this.searchDate = dateFormat.format(searchDate);
    }

    @Override
    public boolean match(Tournament bean) {
        return searchDate.equals(bean.getDateOfTournament());
    }
}
