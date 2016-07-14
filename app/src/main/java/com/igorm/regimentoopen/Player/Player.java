package com.igorm.regimentoopen.Player;

/**
 * Created by Igor on 28/10/2015.
 */
public class Player {

    private long id;
    private String name;
    private Integer win;
    private Integer lose;
    private Integer gamesW;
    private Integer gamesL;
    private Integer setsW;
    private Integer setsL;

    public Player(String name, Integer win, Integer lose, Integer gamesW, Integer gamesL, Integer setsW, Integer setsL){
        this.name = name;
        this.lose = lose;
        this.win = win;
        this.gamesW = gamesW;
        this.gamesL = gamesL;
        this.setsW = setsW;
        this.setsL = setsL;
    }

    public Player() {

    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String nome) {
        this.name = nome;
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
    public Integer getSetsW() {
        return setsW;
    }
    public void setSetsW(Integer setsW) {
        this.setsW = setsW;
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

    public Integer getSetsL() {
        return setsL;
    }
    public void setSetsL(Integer setsL) {
        this.setsL = setsL;
    }
}
