package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FullName {
    private String firstName;
    private String fatherName;
    private String surname;
    private ArrayList<String> nameOfWinner;

    public FullName(String name){
        List nameOfWinner = new ArrayList();
        for (String winnersName : name.split(" "))
            nameOfWinner.add(winnersName);
    }
/*
    private void setNameOfWinner(String str){
        for(String s: str.split(" "))
          //  nameOfWinner.add(s);
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
*/
    public String getNameOfWinner(){
        return nameOfWinner.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FullName fullName = (FullName) o;
        return Objects.equals(nameOfWinner, fullName.nameOfWinner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameOfWinner);
    }

    @Override
    public String toString() {
        return "FullName{" +
                "nameOfWinner=" + nameOfWinner.get(0) +
                " " + nameOfWinner.get(1) + " " + nameOfWinner.get(2) + '}';
    }
}
