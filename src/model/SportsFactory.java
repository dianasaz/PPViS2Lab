package model;

public class SportsFactory {
    public static Sport setSport(String string){
        Sport sport = Sport.NOTHING;
        switch (string){
            case "FOOTBALL":{
                sport = Sport.FOOTBALL;
                break;
            }
            case "TENNIS":{
                sport = Sport.TENNIS;
                break;
            }
            case "SWIMMING":{
                sport = Sport.SWIMMING;
                break;
            }
            case "PINGPONG":{
                sport = Sport.PINGPONG;
                break;
            }
            case "GANDBALL":{
                sport = Sport.GANDBALL;
                break;
            }
            case "HOCKEY":{
                sport = Sport.HOCKEY;
                break;
            }
        }
        return sport;
    }
}
