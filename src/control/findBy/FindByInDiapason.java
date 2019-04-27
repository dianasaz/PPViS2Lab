package control.findBy;

import model.Tournament;

public interface FindByInDiapason {
    public boolean compareBy(Tournament tournament, double high, double low );
}
