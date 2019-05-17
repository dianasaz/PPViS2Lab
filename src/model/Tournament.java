package model;

import java.util.Objects;

public class Tournament {
    private String nameOfTournament;
    private int date;
    private Enum<Sport> kindOfSport;
    private String winner;
    private int prize;
    private double prizeOfWinner;

    public Tournament(String nameOfTournament, Enum<Sport> kindOfSport, String winner, int prize, int date){
        this.kindOfSport = kindOfSport;
        this.nameOfTournament = nameOfTournament;
        this.prize = prize;
        setPrizeOfWinner();
        this.prizeOfWinner = getPrizeOfWinner();
        this.winner = winner;
        this.date = date;
        //setDate(date);
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

  //  public void setDate(Date date){
 //       this.date = date;
  //  }

    public int getDate(){
        return date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tournament tournament = (Tournament) o;
        return prize == tournament.prize &&
                prizeOfWinner == tournament.prizeOfWinner &&
                Objects.equals(nameOfTournament, tournament.nameOfTournament) &&
                Objects.equals(kindOfSport, tournament.kindOfSport) &&
                Objects.equals(winner, tournament.winner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameOfTournament, kindOfSport, winner, prize, prizeOfWinner);
    }

    @Override
    public String toString() {
        return "Tournament{" +
                "nameOfTournament='" + nameOfTournament + '\'' +
                ", kindOfSport='" + kindOfSport + '\'' +
                ", winner='" + winner + '\'' +
                ", prize=" + prize +
                ", prizeOfWinner=" + prizeOfWinner +
                '}';
    }
}
