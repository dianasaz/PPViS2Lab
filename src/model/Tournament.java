package model;

import java.util.Date;
import java.util.Objects;

public class Tournament {
    private String nameOfTournament;
    private Date date;
    private Enum<Sports> kindOfSport;
    private FullName winner;
    private int prize;
    private double prizeOfWinner;

    public Tournament(String nameOfTournament, Date date, Enum<Sports> kindOfSport, FullName winner, int prize, int prizeOfWinner){
        setNameOfTournament(nameOfTournament);
        setKindOfSport(kindOfSport);
        setPrize(prize);
        setWinner(winner);
        setPrizeOfWinner();
        setDate(date);
    }

    public void setNameOfTournament(String nameOfTournament){
        this.nameOfTournament = nameOfTournament;
    }

    public String getNameOfTournament(){
        return nameOfTournament;
    }

    public void setKindOfSport(Enum<Sports> kindOfSport){
        this.kindOfSport = kindOfSport;
    }

    public Enum<Sports> getKindOfSport(){
        return kindOfSport;
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

    public void setWinner(FullName winner){
        this.winner = winner;
    }

    public FullName getWinner(){
        return winner;
    }

    public void setDate(Date date){
        this.date = date;
    }

    public Date getDate(){
        return date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tournament tournament = (Tournament) o;
        return date == tournament.date &&
                prize == tournament.prize &&
                prizeOfWinner == tournament.prizeOfWinner &&
                Objects.equals(nameOfTournament, tournament.nameOfTournament) &&
                Objects.equals(kindOfSport, tournament.kindOfSport) &&
                Objects.equals(winner, tournament.winner);
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
                ", kindOfSport='" + kindOfSport + '\'' +
                ", winner='" + winner + '\'' +
                ", prize=" + prize +
                ", prizeOfWinner=" + prizeOfWinner +
                '}';
    }
}
