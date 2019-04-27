package control.findBy;

import model.Tournament;

public interface FindBy {
    public boolean compareBy(Tournament tournament, Object ob);
}
