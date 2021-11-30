
package callanalytics;


import java.sql.*;
import java.util.Properties;

/*
   Database name:Analytics
   Table name:callCenter
   Attribute
   id,From_Number,Start_Time,End_Time,Duration
*/
/*
steps to run
1.change datbase name accordingly;
2.update the String url according to your username and password
3.Update the variable names according to your table
*/

public class CallAnalytics {

    static String URL = "jdbc:mysql://localhost/Analytics";
    static Properties info;
    static Connection con=null;
    static Statement stmt=null;
    static ResultSet rs=null;
    public static void main(String[] args) throws SQLException {
        try{  
            Class.forName("com.mysql.cj.jdbc.Driver");  
            info = new Properties( );
            info.put( "user", "root" );
            info.put( "password", "" );
            con=DriverManager.getConnection(URL, info);
            stmt=con.createStatement();  
            String Query1="select  HOUR(Start_Time) from callCenter group by HOUR(Start_Time)"
            + " order by count(*) desc limit 1";
            
            
            String Query2="select  HOUR(Start_Time) from callCenter group by HOUR(Start_Time)"
            + " order by AVG(Duration) desc limit 1";
            
            
            String Query3="select  DAYNAME(Start_Time) from callCenter group by DAYNAME(Start_Time)"
            + " order by count(*) desc limit 1";
            
            
            String Query4="select  DAYNAME(Start_Time) from callCenter group by DAYNAME(Start_Time)"
            + " order by AVG(Duration) desc limit 1";
            
            
            System.out.println("Hour of the day when the call volume is highest");
            rs=stmt.executeQuery(Query1);
            while(rs.next())
            {
                System.out.println(rs.getInt(1)+"-"+(rs.getInt(1)+1));
            }
            
            
            System.out.println("Hour of the day when the calls are longest");
            rs=stmt.executeQuery(Query2);
             while(rs.next())
            {
                System.out.println(rs.getInt(1)+"-"+(rs.getInt(1)+1));
            }
             
             
            System.out.println("Day of the week when the call volume is highest.");
            rs=stmt.executeQuery(Query3);
             while(rs.next())
            {
                System.out.println(rs.getString(1)+" ");
            }
             
             
            System.out.println("Day of the week when the calls are longest.");
            rs=stmt.executeQuery(Query4);
             while(rs.next())
            {
                System.out.println(rs.getString(1)+" ");
            }
            
            }
            catch(Exception e){ 
                System.out.println(e);
            }
            finally{
                 con.close();
                 stmt.close();
                 rs.close();
            }


     
    }
    
}
