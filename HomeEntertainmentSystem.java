import java.util.Iterator;
import java.util.Optional;

/**
 *
 * Class representing the client code of the MusicPlayer app.
 *
 */
public class HomeEntertainmentSystem {
    public static void main(String[] args) {

    MusicPlayer mp=new MusicPlayer();
    Song song0=new Song("s0","a", 2);
    Song song1=new Song("s1","b", 2);
    Song song2=new Song("s2","c", 2);
    Song song3=new Song("s3","c", 2);
    Song song4=new Song("s4","c", 2);
    Song song5=new Song("s5","c", 2);

    mp.addItem(song0);
    mp.addItem(song1);
    mp.addItem(song2);
    mp.addItem(song3);
    mp.addItem(song4);
    mp.addItem(song5);
    /**
     * Q2.1
     * Iterates through all songs and prints their titles
     * */
    Iterator<Song> I= mp.iterator();
    while(I.hasNext()){
        System.out.println(I.next().getName());
    }
    /**
     * Q2.2
     * You can create a playlist with either a song or another playlist
     * You can also add a Song or another playlist to an existing playlist
     * */
    mp.createPlaylist("p1","s0",SP.SONG);
    mp.createPlaylist("p2","p1",SP.PLAYLIST);
    mp.addSongOrPlaylistToPlaylist("p1","s1",SP.SONG);
/**
 * Q2.3
 * adds playlist to Queue
 * */
    mp.addPlaylistToQueue("p1");
    mp.addItemToQueue("s2");
    mp.addItemToQueue("s3");
    mp.addItemToQueue("s4");
    mp.addItemToQueue("s5");
    /**
     * Q3.1 &Q3.2 &Q3.3
     * */

    mp.play(PLAY.SHUFFLE);
    mp.play(PLAY.DEFAULT);
    /**
     * Q3.4
     * */
    mp.removeSongFromQueue("s0");
    /**
     * Q3.5
     * Unit tests
     * */
    ShuffledPlayTest spt=new ShuffledPlayTest();
    /**
     *Q4.1
     * VoiceController and RemoteController play the next Songs
     * it possible to Pass a RadioReciever as the Player into A controller
     * */
    RadioReceiver rR=new RadioReceiver();
    VoiceController vC= new VoiceController(mp);
    RemoteController rC= new RemoteController(mp);
    RemoteController rC2= new RemoteController(rR);
    vC.next();
    rC.next();
    rC2.next();

    }
}
