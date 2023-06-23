import java.sql.*;


public class JDBC
{
    Connection con;
    JDBC()
    {
      //  Connection con = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
          con = DriverManager.getConnection("jdbc:mysql://localhost:3306/songs", "root", "root");
        }catch(ClassNotFoundException e)
        {
            System.out.println(e);
        }catch(SQLException e)
        {
            System.out.println(e);
        }

    }

    public static void main(String[] args) {
        JDBC obj = new JDBC();

        System.out.println(obj.con);
    }
}
