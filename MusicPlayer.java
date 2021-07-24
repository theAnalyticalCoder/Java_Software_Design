

import java.util.*;

/**
 * A class representing a Music Player App.
 * It contains a pool of songs that allow the user to traverse and add to the queue to play.
 */
enum SP { SONG, PLAYLIST }
enum PLAY{DEFAULT, SHUFFLE}
public class MusicPlayer implements Iterable<Song>,Receiver{
    private Map<String, Song> aItems = new LinkedHashMap<>(); // Make sure a predictable iteration order.
    private Queue aQueue = new Queue();
    private Map<String, ArrayList<Song>> aPlaylists = new LinkedHashMap<>();
    private ArrayList<Integer> aAlreadyPlayed= new ArrayList<>();
    private DefaultPlay aDefault=new DefaultPlay(-1,aQueue.size(),aAlreadyPlayed);
    private ShuffledPlay aShuffled=new ShuffledPlay(-1,aQueue.size(),aAlreadyPlayed);
    private Model aModel= new Model(-1,0);

    MusicPlayer(){
        aModel.addObserver(aDefault);
        aModel.addObserver(aShuffled);
    }
    /**
     * Q2.1
     * iterator returns and iterator of only songs without the key that accompanies the Song
     * */

    public Iterator<Song> iterator() {
        ArrayList<Song> values= new ArrayList<Song>();
        for (Map.Entry<String, Song> entry : aItems.entrySet()) {
            values.add(entry.getValue());

        }
        return values.iterator();

    }

    /**
     *Q2.2
     * first it is determined if a Song or a playlist
     * Then if that song or Playlist exists a copy is made into a new playlist
     * @param pName the name of the either the song to be added or the playlist to be added
     * @param pSP whether its a song or a playlist
     * @param pPlaylistName the name of the playlist
     * @pre there is no playlist of the name pPlaylistName
     *
     */
    public void createPlaylist(String pPlaylistName, String pName, SP pSP){
        assert pName!=null&&pSP!=null&&pPlaylistName!=null;
        assert !(aPlaylists.containsKey(pPlaylistName));
        if(pSP==SP.SONG){
            if (aItems.containsKey(pName)){
                ArrayList<Song> arrayS= new ArrayList<>();
                //Song s= new Song(aItems.get(pName).getName(),aItems.get(pName).getArtist(),aItems.get(pName).getLength());
                arrayS.add(aItems.get(pName));
                aPlaylists.put(pPlaylistName,arrayS);
            }

        }
        else{
            if (aPlaylists.containsKey(pName)){
                ArrayList<Song> arrayS= new ArrayList<>();
                for(Song s :aPlaylists.get(pName)){
                    arrayS.add(s);

                }
                aPlaylists.put(pPlaylistName,arrayS);
            }

        }

    }
    /**
     * Q2.2
     * first it is determined if a Song or a playlist
     * Then if that song or Playlist exists a copy is made into a preexisting playlist
     * @param pName the name of the either the song to be added or the playlist to be added
     * @param pSP whether its a song or a playlist
     * @param pPlaylistName the name of the playlist
     * @pre there is a playlist of the name pPlaylistName
     * first it is determined if a Song or a playlist
     */
    public void addSongOrPlaylistToPlaylist(String pPlaylistName, String pName, SP pSP){
        assert pName!=null&&pSP!=null&&pPlaylistName!=null;
        assert (aPlaylists.containsKey(pPlaylistName));
        if(pSP==SP.SONG){
            if (aItems.containsKey(pName)){
                Song s=aItems.get(pName);
                aPlaylists.get(pPlaylistName).add(s);

            }

        }
        else{
            if (aPlaylists.containsKey(pName)){

                for(Song s :aPlaylists.get(pName)){
                    aPlaylists.get(pPlaylistName).add(s);

                }

            }

        }

    }
    /**
     * Q2.3 if the playlist has been created it copies all of its songs into the Queue
     * @param pPlaylistName name of playlist to add to Queue
     * @pre pPlaylistName is not null
     * */
    public void addPlaylistToQueue(String pPlaylistName){
        assert pPlaylistName!=null;
        assert (aPlaylists.containsKey(pPlaylistName));
        for(Song s:aPlaylists.get(pPlaylistName)){
            aQueue.add(s);
        }
        aModel.setSize(aQueue.size());
        aModel.notifyObservers();
    }
    /**
     * Q3.1 &Q3.2 &Q3.3
     * Takes as input the type of play wanted Shuffled or Default
     * then calls PlayNext to determine which Song to play
     * once the method returns it play the song chosen or displays an error message if none is return
     * @param pPlay !=null
     * */
    public void play(PLAY pPlay){
        assert pPlay!=null;
        PlayOrder pPlayOrder;
        if(pPlay==PLAY.DEFAULT){
            pPlayOrder=aDefault;
        }
        else{
            pPlayOrder=aShuffled;
        }
        Optional<Song>sToPlay=playNext(pPlayOrder);
        if(sToPlay.isPresent()){
         System.out.println("Playing "+sToPlay.get().getName()+" by Artist "+sToPlay.get().getArtist());
        }
        else{
            System.out.println("unfortunately we have finished playing all the songs in the queue");
        }
    }
/**
 * Q3.1 &Q3.2 &Q3.3&3.4
 * Returns the Optional<Song> specified by the class of pPlayorder if one exists
 * Updates the Songs that have been already played
 * Notifies Observers
 * Otherwise it return empty
 * @param pPlayOrder !=null
 * */
    private Optional<Song> playNext(PlayOrder pPlayOrder){
        assert pPlayOrder!=null;

        int aCurrentSong;


        if(pPlayOrder.hasNext()){
            aCurrentSong=pPlayOrder.getNext();
            aModel.setCurrent(aCurrentSong);
            aModel.notifyObservers();
            aAlreadyPlayed.add(aCurrentSong);
            return Optional.of(aQueue.get(aCurrentSong));
        }
        return Optional.empty();
    }

    /**
     * Add a single song to the music pool if a song with the same name is not already in the pool.
     * @param pItem the song to be added in the pool
     * @pre pItem !=null
     */
    public void addItem(Song pItem) {
        assert pItem != null;
        aItems.putIfAbsent(pItem.getName(), pItem);
    }


    /**
     * Q3.4
     * Add a single song to the queue if that song can be found in the music pool.
     * @param pItemName the name of the song
     * @pre pItemName !=null
     */
    public void addItemToQueue(String pItemName) {
        assert pItemName!= null;
        if (aItems.containsKey(pItemName)) {
            aQueue.add(aItems.get(pItemName));
            aModel.setSize(aQueue.size());
            aModel.notifyObservers();
        }
    }

    /**
     * Q3.4
     * Add a single song to the queue if that song can be found in the music pool.
     * @param pSongName the name of the song
     * @pre pItemName !=null
     */
    public void removeSongFromQueue(String pSongName) {
        assert pSongName!= null;
        int indexOfSong=aQueue.getSongIndex(pSongName);
        if(indexOfSong>-1){
            if(aModel.getCurrent()==indexOfSong){
                aModel.setCurrent(indexOfSong-1);
            }
            resetAlreadyPlayed(indexOfSong);
            aQueue.remove(aItems.get(pSongName));
            aModel.setSize(aQueue.size());
            aModel.notifyObservers();
        }


    }/**
     Q3.4
     * when we remove a song from the queue we need to remove its index from our Already Played list
     * and we need to update the indices in the List
     * i.e any song index j after index i becomes j-1
     */
    private void resetAlreadyPlayed(int pSongRemovedIndex){
        int counter=0;
        for(int i:aAlreadyPlayed){
            if(pSongRemovedIndex<=i){
                if(pSongRemovedIndex==i){
                    aAlreadyPlayed.remove(counter);
                }
                else{
                    aAlreadyPlayed.set(counter,i-1);
                }
            }
            counter++;
        }

    }
    /**
     * Obtain the number of songs in the queue
     * @return the number of songs in the queue
     */
    public int getQueueSize() {
        return aQueue.size();
    }


}
