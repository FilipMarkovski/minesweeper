/* Korisnik.java 
 * @autor Filip Markovski,  
 * Univerzitet u Beogradu'
 * Fakultet organizacionih nauka 
 * Katedra za softversko inzenjerstvo 
 * Laboratorija za softversko inzenjerstvo 
 * Datum:2020-07-26 Problem oko brojaca korisnika.
Domenska klasa za server na kome su pokrenuti web servisi.
 */
package domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class User extends GeneralDObject implements Serializable {

    public int userId;
    public String username;
    public String password;
    public String email;
    public int highscore;

    public User() {
        userId = 0;
        username = "";
        password = "";
        email = "";
        highscore = 0;
    }

    public User(int userId, String username, String password, String email, int highscore) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.highscore = highscore;
    }

    @Override
    public int getPrimaryKey() {
        return userId;
    }

    @Override
    public void setID(int id) {
        userId = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getHighscore() {
        return highscore;
    }

    public void setHighscore(int highscore) {
        this.highscore = highscore;
    }

    @Override
    public GeneralDObject getNewRecord(ResultSet rs) throws SQLException {
        return new User(rs.getInt("userId"), rs.getString("username"), rs.getString("password"), rs.getString("email"), rs.getInt("highscore"));
    }

    @Override
    public String getAtrValue() {
        return (username == null ? null : "'" + username + "'") + "," + (password == null ? null : "'" + password + "'") + "," + (email == null ? null : "'" + email + "'") + "," + "'" + highscore + "'";
    }

    @Override
    public String setAtrValue() {
        return "username=" + (username == null ? null : "'" + username + "'") + ", " + "password=" + (password == null ? null : "'" + password + "'") + ", " + "email=" + (email == null ? null : "'" + email + "'") + ", " + "highscore=" + highscore;
    }

    @Override
    public String getClassName() {
        return "User";
    }

    @Override
    public String getWhereCondition() {
        return "username='" + username + "' and password='" + password + "'";
    }

    @Override
    public String getNameByColumn(int column) {
        String names[] = {"userId", "username", "password", "email", "highscore"};
        return names[column];
    }

    @Override
    public String getColumnNames() {
        return " (username, password, email, highscore) ";
    }

}
