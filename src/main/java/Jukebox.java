import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.sql.*;
import java.util.List;
import java.util.Scanner;

public class Jukebox extends JDBC
{
    void insertUser(int userId, String userName, long phoneNo, String email,String password, Connection con)
    {
        try {
            String sql = "insert into  userdetails values  (?,?,?,?,?)";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1,userId);

            statement.setString(2,userName);
            statement.setLong(3,phoneNo);
            statement.setString(4,email);
            statement.setString(5,password);
            statement.executeUpdate();
            System.out.println("Records have been inserted");
        } catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }
    public int credentialsValidator(int user_id,String username,String password, Connection con)throws CredentialsException
    {
        int verify = 0;
        try {
            boolean found=false;
            String sql = "select * from userdetails where user_Id =?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1,user_id);
            // System.out.println(statement);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.getString(2).equals(username) && resultSet.getString(5).equals(password))
                {
                    found = true;
                           verify = 1;
                }
                else
                {
                    throw new CredentialsException("please enter correct Username and password");
                }

            }
            if(found ==false)
                System.out.println("no data found for "+user_id+" Rating");
        }
        catch (SQLException e)
        {
            System.out.println(e);
        }
        return verify;
    }
    String showPathBasedOnId(int id,Connection con)
{
    String path = null;
    try {
        boolean found=false;
        String sql = "select * from songsList where song_id =?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1,id);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            found=true;
            path = resultSet.getString(6);
        }
        if(found ==false) {
            System.out.println("no data found for " + id + " Rating");
        }
        return path;
    }

    catch (SQLException e)
    {
        System.out.println(e.getMessage());
        return path;
    }
}
    public static void main(String[] args)
    {
        ChooseByOption option = new ChooseByOption();
        MethodsLibrary getMethod = new MethodsLibrary();
        JDBC obj = new JDBC();
        Jukebox obj1 = new Jukebox();
        System.out.println("press 1 to register and 2 to Login");
        Scanner sc = new Scanner(System.in);
        int i = sc.nextInt();
        if(i ==1)
        {System.out.println("Enter  user id");
            int id = sc.nextInt();
            System.out.println("Enter username");
            String userName = sc.next();
            System.out.println("Enter the phone number");
            System.out.print("+91 ");
            long no = sc.nextLong();
            System.out.println("Enter email id");
            String email = sc.next();
            System.out.println("Enter password:");
            String pass = sc.next();
            obj1.insertUser(id, userName, no, email,pass, obj.con);
            System.out.println("Registered Successfully");
            System.out.println("Press 2 to login ");
            i = sc.nextInt();
        }
        if(i==2) {
           int k = 1;
           int z=1;
           boolean CredentialsVerification = false;
            while (k == 1) {
                System.out.println("Enter your username");
                String name = sc.next();
                System.out.println("Enter your password");
                System.out.println("Password should be of min 8  characters including alphabets and digits");
                String pass = sc.next();
                System.out.println("Enter user id");
                int id = sc.nextInt();
                int exist = 0;
                try {
                    exist = obj1.credentialsValidator(id, name, pass, obj.con);
                } catch (CredentialsException e) {
                    System.out.println(e.getMessage());
                }
                while (exist == 1) {
                    System.out.println("________________________________WELCOME TO JUKEBOX____________________________________");
                    System.out.println("Press 4 to go to Settings and 5 to go to music bar");
                    CredentialsVerification = true;
                    int y = sc.nextInt();
                    if (y == 4) {
                        System.out.println("Press 1 to go to choice menu");
                        int j = sc.nextInt();
                        while (j == 1) {
                            option.getChoices();
                            System.out.println("");
                            System.out.println("press any key to go to the settings and 1 to back to the choice menu again ");
                            j = sc.nextInt();
                        }
                        System.out.println("press any key to go back to the main menu");
                        z = sc.nextInt();
                    }
                    System.out.println("press 5 to play music or any other key to log out");
                    y = sc.nextInt();
                    //   System.out.println("---------------------------------------------------------------------------------------");
                    if (y == 5) {
                        System.out.println("All songs details");
                        System.out.println("---------------------------------------------------------------------------------------");
                        System.out.println("Song Id    Song Name            Genre                Artist               Duration");
                        System.out.println("---------------------------------------------------------------------------------------");
                        List<Songs> songList = getMethod.getAllSongs(obj.con);
                        for (Songs e : songList) {
                            System.out.format("%-10s %-20s %-20s %-20s %-20s %n", e.getSong_id(), e.getAlbum_name(), e.getAlbum_genre(),
                                    e.getAlbum_artist(), e.getAlbum_duration());
                        }
                        System.out.println("---------------------------------------------------------------------------------------");
                        System.out.println("Press 2 to play music");
                        int p = sc.nextInt();
                        while (p == 2) {
                            System.out.println("Enter song id");
                            int id1 = sc.nextInt();
                            String path = obj1.showPathBasedOnId(id1, obj.con);
                            SimpleAudioPlayer.MusicControls(path);
                            System.out.println("press 2 to return back into the music bar or any key to return to the home page");
                            p = sc.nextInt();
                        }
                    }
                    System.out.println();
                    System.out.println("Press 1 to go back to the settings and any press any key  to logout");
                    k = sc.nextInt();
                    if(k!=1)
                    {
                        break;
                    }
                }
            }
                    if(CredentialsVerification==false) {
                      //  System.out.println("username already exists");
                        System.out.println("Press 1 to Enter username and Password again");
                        k = sc.nextInt();

                    }
                }
            }

        }


