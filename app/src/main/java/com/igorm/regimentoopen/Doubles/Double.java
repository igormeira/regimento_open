package com.igorm.regimentoopen.Doubles;

/**
 * Created by igorm on 14/09/2016.
 */
public class Double {

    private long id;
    private String name_player1, name_player2;
    private Integer win;
    private Integer lose;
    private Integer gamesW;
    private Integer gamesL;
    private Integer setsW;
    private Integer setsL;

    public Double(String name_player1, String name_player2, Integer win, Integer lose, Integer gamesW, Integer gamesL, Integer setsW, Integer setsL){
        this.name_player1 = name_player1;
        this.name_player2 = name_player2;
        this.lose = lose;
        this.win = win;
        this.gamesW = gamesW;
        this.gamesL = gamesL;
        this.setsW = setsW;
        this.setsL = setsL;
    }

    public Double(long id, String name_player1, String name_player2, Integer win, Integer lose, Integer gamesW, Integer gamesL, Integer setsW, Integer setsL) {
        this.id = id;
        this.name_player1 = name_player1;
        this.name_player2 = name_player2;
        this.lose = lose;
        this.win = win;
        this.gamesW = gamesW;
        this.gamesL = gamesL;
        this.setsW = setsW;
        this.setsL = setsL;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNamePlayer1() {
        return name_player1;
    }

    public void setNamePlayer1(String name_player1) {
        this.name_player1 = name_player1;
    }

    public String getNamePlayer2() {
        return name_player2;
    }

    public void setNamePlayer2(String name_player2) {
        this.name_player2 = name_player2;
    }

    public Integer getWin() {
        return win;
    }

    public void setWin(Integer win) {
        this.win = win;
    }

    public Integer getLose() {
        return lose;
    }

    public void setLose(Integer lose) {
        this.lose = lose;
    }

    public Integer getGamesW() {
        return gamesW;
    }

    public void setGamesW(Integer gamesW) {
        this.gamesW = gamesW;
    }

    public Integer getGamesL() {
        return gamesL;
    }

    public void setGamesL(Integer gamesL) {
        this.gamesL = gamesL;
    }

    public Integer getSetsW() {
        return setsW;
    }

    public void setSetsW(Integer setsW) {
        this.setsW = setsW;
    }

    public Integer getSetsL() {
        return setsL;
    }

    public void setSetsL(Integer setsL) {
        this.setsL = setsL;
    }
}
