import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MethodsLibraryTest extends JDBC {


    void showBasedOnArtist() {
        // List<Songs>songs = obj.
        MethodsLibrary obj = new MethodsLibrary();
        JDBC jdbc = new JDBC();
        List<Songs> songs = obj.getAllSongs(jdbc.con);
        try {
            List<Songs> list = obj.showBasedOnArtist("Ed-sheeran", songs);
            System.out.println(list.size());
            //  assertEquals(1,list.size());
            // assertEquals();
        } catch (CredentialsException e) {
            System.out.println(e.getMessage());
        }

    }
    @ Test
    void showBasedONAlbum() {
        MethodsLibrary obj = new MethodsLibrary();
        JDBC jdbc = new JDBC();
        List<Songs> songs = obj.getAllSongs(jdbc.con);
        try {
            List<Songs> list = obj.showBasedOnAlbum("Not-Afraid", songs);
            assertEquals(1,list.size());
        } catch (CredentialsException e) {
            System.out.println(e.getMessage());
        }
    }
    @ Test
    void showBasedONGenre()
    {
        MethodsLibrary obj = new MethodsLibrary();
        JDBC jdbc = new JDBC();
        List<Songs> songs = obj.getAllSongs(jdbc.con);
        try {
            List<Songs> list = obj.showBasedOnGenre("Pop", songs);
            assertEquals(3,list.size());
        } catch (CredentialsException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void SongsList() {
        MethodsLibrary obj = new MethodsLibrary();
        JDBC jdbc = new JDBC();
        List<Songs> songs = obj.getAllSongs(jdbc.con);
        assertEquals(10, songs.size());
    }

    @Test
    void CredentialsValidator() {

        Jukebox jukebox = new Jukebox();


        try {
          //  int verify1 = jukebox.credentialsValidator(1, "John", "John56@$", jukebox.con);
            int verify = jukebox.credentialsValidator(1, "joh", "s", jukebox.con);
            assertEquals(1, verify);
        } catch (CredentialsException e) {
            System.out.println(e.getMessage());
        }
    }
    @Test
    void createPlayList() {
        JDBC jdbc = new JDBC();
        MethodsLibrary obj = new MethodsLibrary();

        int verify = obj.CreatePlayList(1, "Playlist", 1, 20, jdbc.con);
        assertEquals(1, verify);
    }

    @ Test
   void showPlaylistDetails()
    {
        JDBC jdbc = new JDBC();
        MethodsLibrary obj = new MethodsLibrary();
        assertEquals(0,obj.showPlayListDetails(6 ,jdbc.con));
    }
    @ Test
    void addPlaylistData()
    {
        JDBC jdbc = new JDBC();
        MethodsLibrary obj = new MethodsLibrary();
        assertEquals(1,obj.addPlayerListData(10,4, jdbc.con));
    }
    @ Test
    void showPlayListData()
    {
        JDBC jdbc = new JDBC();
        MethodsLibrary obj = new MethodsLibrary();
        assertEquals(1,obj.showPlayerListData(2, jdbc.con));

    }
    @Test
    void choices()
    {
        ChooseByOption obj = new ChooseByOption();
        assertEquals(1,obj.getChoices());
    }
}