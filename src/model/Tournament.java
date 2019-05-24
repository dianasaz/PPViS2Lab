package model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Tournament {
    private String nameOfTournament;
    private Date date;
    private Enum<Sport> kindOfSport;
    private String winner;
    private int prize;
    private double prizeOfWinner;
    public final static SimpleDateFormat dateFormat = new SimpleDateFormat("d.MM.yyyy");


    public Tournament() {
    }

    public Tournament(String nameOfTournament, Enum<Sport> kindOfSport, String winner, int prize, Date date){
        this.kindOfSport = kindOfSport;
        this.nameOfTournament = nameOfTournament;
        this.prize = prize;
        setPrizeOfWinner();
        this.prizeOfWinner = getPrizeOfWinner();
        this.winner = winner;
        this.date = date;
    }

    public void setNameOfTournament(String nameOfTournament){
        this.nameOfTournament = nameOfTournament;
    }

    public String getNameOfTournament(){
        return nameOfTournament;
    }

    public void setKindOfSport(Enum<Sport> kindOfSport){
        this.kindOfSport = kindOfSport;
    }

    public Sport getKindOfSport(){
        return (Sport) kindOfSport;
    }

    public void setPrize(int prize){
        this.prize = prize;
    }

    public int getPrize(){
        return prize;
    }

    public void setPrizeOfWinner(){
        this.prizeOfWinner = this.getPrize() * 0.6;
    }

    public double getPrizeOfWinner(){
        return prizeOfWinner;
    }

    public void setWinner(String winner){
        this.winner = winner;
    }

    public String getWinner(){
        return winner;
    }

    public void setDate(Date date){
        this.date = date;
    }

    public String getDateOfTournament(){
        return dateFormat.format(date);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tournament that = (Tournament) o;
        return prize == that.prize &&
                Double.compare(that.prizeOfWinner, prizeOfWinner) == 0 &&
                Objects.equals(nameOfTournament, that.nameOfTournament) &&
                Objects.equals(date, that.date) &&
                Objects.equals(kindOfSport, that.kindOfSport) &&
                Objects.equals(winner, that.winner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameOfTournament, date, kindOfSport, winner, prize, prizeOfWinner);
    }

    @Override
    public String toString() {
        return "Tournament{" +
                "nameOfTournament='" + nameOfTournament + '\'' +
                ", date=" + date +
                ", kindOfSport=" + kindOfSport +
                ", winner='" + winner + '\'' +
                ", prize=" + prize +
                ", prizeOfWinner=" + prizeOfWinner +
                '}';
    }
}
