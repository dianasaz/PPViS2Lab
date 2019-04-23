package model;

import java.util.ArrayList;
import java.util.Objects;

public class FIO {
    private ArrayList<String> nameOfWinner;

    public FIO(String name){
        nameOfWinner = new ArrayList<>();
        setNameOfWinner(name);
    }

    private void setNameOfWinner(String str){
        for(String s: str.split(" "))
            nameOfWinner.add(s);
    }

    private String getFirstName(){
        return nameOfWinner.get(0);
    }

    private String getFatherName(){
        return nameOfWinner.get(1);
    }

    private String getSurname(){
        return nameOfWinner.get(2);
    }

    public String getNameOfWinner(){
        return nameOfWinner.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FIO fio = (FIO) o;
        return Objects.equals(nameOfWinner, fio.nameOfWinner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameOfWinner);
    }

    @Override
    public String toString() {
        return "FIO{" +
                "nameOfWinner=" + nameOfWinner.get(0) +
                " " + nameOfWinner.get(1) + " " + nameOfWinner.get(2) + '}';
    }
}
