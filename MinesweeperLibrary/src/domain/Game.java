/* Game.java 
 * @autor prof. dr Sinisa Vlajic,  
 * Univerzitet u Beogradu'
 * Fakultet organizacionih nauka 
 * Katedra za softversko inzenjerstvo 
 * Laboratorija za softversko inzenjerstvo 
 * Datum:2020-07-26 Problem oko brojaca igre.
Domenska klasa za server na kome su pokrenuti web servisi.
 */
package domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class Game extends GeneralDObject implements Serializable {

    private int gameId;
    private int score;
    private Date date;
    private int levelId;
    private int userId;

    public Game() {
        gameId = 0;
        score = 0;
        SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
        date = new Date();
        date = java.sql.Date.valueOf(sm.format(date));
        levelId = 0;
        userId = 0;
    }

    public Game(int gameId, int score, Date date, int levelId, int userId) {
        this.gameId = gameId;
        this.score = score;
        this.date = date;
        this.levelId = levelId;
        this.userId = userId;
    }

    // primarni kljuc
    public Game(int gameId) {
        this.gameId = gameId;
    }

    public void setID(int id) {
        this.gameId = id;
    }

    public int getPrimaryKey() {
        return this.gameId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Date getDate(Date date) {
        SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
        this.date = java.sql.Date.valueOf(sm.format(date));
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getLevel() {
        return levelId;
    }

    public void setLevel(int level) {
        this.levelId = level;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public GeneralDObject getNewRecord(ResultSet rs) throws SQLException {
        return new Game(rs.getInt("gameId"), rs.getInt("score"), rs.getDate("date"), rs.getInt("levelId"), rs.getInt("userId"));
    }

    @Override
    public String getAtrValue() {
        return score + ", " + "'" + date + "'" + ", " + levelId + ", " + userId;
    }

    @Override
    public String setAtrValue() {
        return "gameId=" + gameId + ", " + "score=" + score + ", " + "date=" + "'" + getDate(date) + "'" + ", " + "levelId=" + levelId + ", " + "userId=" + userId;
    }

    @Override
    public String getClassName() {
        return "Game";
    }

    @Override
    public String getWhereCondition() {
        return "gameId = " + gameId;
    }

    @Override
    public String getNameByColumn(int column) {
        String names[] = {"gameId", "score", "date", "levelId", "userId"};
        return names[column];
    }

    @Override
    public String getColumnNames() {
        return " (score, date, levelId, userId) ";
    }

}
