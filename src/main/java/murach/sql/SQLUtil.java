
package murach.sql;
 import java.sql.*;

public class SQLUtil {
     public static synchronized String
     getHtmlRows(ResultSet results) throws SQLException{
         StringBuffer htmlRows = new StringBuffer();
         ResultSetMetaData metaData = results.getMetaData();
         int columnCount = metaData.getColumnCount();
        
         htmlRows.append("<table border='table'>");
         htmlRows.append("<tr class='success'>");
         for (int i = 1; i <= columnCount; i++)
             htmlRows.append("<td><b>"
                 + metaData.getColumnName(i) + "</td>");
         htmlRows.append("</tr>");
 
         while (results.next()){
             htmlRows.append("<tr>");
             for (int i = 1; i <= columnCount; i++)
                 htmlRows.append("<td>"
                     + results.getString(i) + "</td>");
         }
         htmlRows.append("</tr>");
         return htmlRows.toString();
     }
 }
    

