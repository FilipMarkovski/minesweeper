package domain;



import java.io.Serializable;
import java.sql.*;


public abstract class GeneralDObject implements Serializable
{ abstract public String getAtrValue();
  abstract public String setAtrValue();
  abstract public String getClassName();
  abstract public String getWhereCondition();
  abstract public String getNameByColumn(int column);
  abstract public String getColumnNames();
  abstract public GeneralDObject getNewRecord(ResultSet rs) throws SQLException;
  abstract public int getPrimaryKey();
  abstract public void setID(int id);
}
