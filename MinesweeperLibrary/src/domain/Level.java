/* Level.java 
 * @autor Filip Markovski,  
 * Univerzitet u Beogradu'
 * Fakultet organizacionih nauka 
 * Katedra za softversko inzenjerstvo 
 * Laboratorija za softversko inzenjerstvo 
 * Datum:2020-07-26 poruka1
Domenska klasa za server na kome su pokrenuti web servisi.
 */
package domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class Level extends GeneralDObject implements Serializable {

    public int levelId;
    public String difficulty;
    public double bombPercentage;

    public Level() {
        levelId = 0;
        difficulty = "";
        bombPercentage = 0;
    }

    public Level(int levelId, String difficulty, double bombPercentage) {
        this.levelId = levelId;
        this.difficulty = difficulty;
        this.bombPercentage = bombPercentage;
    }

    // primarni kljuc
    public Level(int levelId) {
        this.levelId = levelId;
    }

    public void setID(int id) {
        this.levelId = id;
    }

    public int getPrimaryKey() {
        return this.levelId;
    }

    @Override
    public GeneralDObject getNewRecord(ResultSet rs) throws SQLException {
        return new Level(rs.getInt("levelId"), rs.getString("difficulty"), rs.getDouble("bombPercentage"));
    }

    @Override
    public String getAtrValue() {
        return levelId + ", " + (difficulty == null ? null : "'" + difficulty + "'") + ", " + bombPercentage;
    }

    @Override
    public String setAtrValue() {
        return "levelId=" + levelId + ", " + "difficulty=" + (difficulty == null ? null : "'" + difficulty + "'") + ", " + "bombPercentage=" + bombPercentage;
    }

    @Override
    public String getClassName() {
        return "Level";
    }

    @Override
    public String getWhereCondition() {
        return "difficulty = '" + difficulty + "'";
    }

    @Override
    public String getNameByColumn(int column) {
        String names[] = {"levelId", "difficulty", "bombPercentage"};
        return names[column];
    }

    @Override
    public String getColumnNames() {
        return " (difficulty, bombPercentage) ";
    }

}
