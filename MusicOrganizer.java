import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * A class to hold details of audio tracks.
 * Individual tracks may be played.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 7.4
 */
public class MusicOrganizer
{
    // An ArrayList for storing music tracks.
    private ArrayList<Track> trackList;
    // A player for the music tracks.
    private MusicPlayer player;
    // A reader that can read music files and load them as tracks.
    private TrackReader reader;
    //minimum of the number list
    private int min;
    //maximum of the number list
    private int max;
    //index of which number is currently being pointed to.
    private int index;
    //Random number generator
    private Random randomGenerator;

    /**
     * Create a MusicOrganizer
     */
    public MusicOrganizer()
    {
        randomGenerator = new Random();
        player = new MusicPlayer();
        reader = new TrackReader();
        trackList = reader.readTracks("../audio", ".mp3");
        if(! trackList.isEmpty()) {
            System.out.println("Music library loaded. " + getNumberOfTracks() + " tracks.");
        }
        else {
            System.out.println("No tracks loaded.");
        }
        System.out.println();
    }
    
    public void playRandomTrack()
    {
        int randomIndex;
        if(trackList.size() > 0){
            randomIndex = randomGenerator.nextInt(trackList.size());
            //stop currently playing track, if one is being played.
            stopPlaying();
            Track track = trackList.get(randomIndex);
            player.startPlaying(track.getFilename());
            //increment current song's counter for amt played.
            track.incrementCount();
            System.out.println("Now playing: " + track.getArtist() + " - " + track.getTitle() + " - Times played : " + track.getCount());
        }
    }
    
    public void ShufflePlayAllTracks()
    {
        int randomIndex;
        //copy of original playlist.
        ArrayList<Track> copyOfTrackList = new ArrayList<Track>(trackList);
        //New randomized tracklist.
        ArrayList<Track> randomizedTrackList = new ArrayList<Track>();
        while(copyOfTrackList.size() > 0){
            randomIndex = randomGenerator.nextInt(copyOfTrackList.size());
            Track randomTrack = copyOfTrackList.get(randomIndex);
            randomizedTrackList.add(randomTrack);
            copyOfTrackList.remove(randomIndex);
        }
        for(Track track : randomizedTrackList){
            //Stop playing current track to play next one.
            stopPlaying();
            player.startPlaying(track.getFilename());
            //increment current song's counter for amt played.
            track.incrementCount();
            System.out.println("Now playing: " + track.getArtist() + " - " + track.getTitle() + " - Times played : " + track.getCount());
        }
    }
    
    /**
     * Add a track file to the collection.
     * @param filename The file name of the track to be added.
     */
    public void addFile(String filename)
    {
        trackList.add(new Track(filename));
    }
    
    public void favorite(int index){
        Track track = trackList.get(index);
        track.favorite();
    }
    
    /**
     * Add a track to the collection.
     * @param aTrack The track to be added.
     */
    public void addTrack(Track aTrack)
    {
        trackList.add(aTrack);
    }
    
    /**
     * Return the number of tracks in the collection.
     * @return The number of tracks in the collection.
     */
    public int getNumberOfTracks()
    {
        return trackList.size();
    }
    
    /**
     * List a track from the collection.
     * @param index The index of the track to be listed.
     */
    public void listTrack(int index)
    {
        System.out.print("Track " + index + ": ");
        Track aTrack = trackList.get(index);
        System.out.println(aTrack.getDetails());
    }
    
    /**
     * Show a list of all the tracks in the collection.
     */
    public void listAllTracks()
    {
        System.out.println("Track listing: ");

        for(Track aTrack : trackList) {
            System.out.println(aTrack.getDetails());
        }
        System.out.println();
    }
    
    public void removeTracksByTitle(String titleToRemove){
        Iterator<Track> it = trackList.iterator();
        while(it.hasNext()){
            String title = it.next().getTitle();
            if(title.contains(titleToRemove)){//contains, not equals.
                it.remove();
            }
        }
    }
    
    /**
     * List all tracks by the given artist.
     * @param artist The artist's name.
     */
    public void listByArtist(String artist)
    {
        for(Track aTrack : trackList) {
            if(aTrack.getArtist().contains(artist)) {
                System.out.println(aTrack.getDetails());
            }
        }
    }
    
    /**
     * Remove a track from the collection.
     * @param index The index of the track to be removed.
     */
    public void removeTrack(int index)
    {
        if(validIndex(index)) {
            trackList.remove(index);
        }
    }
    
    /**
     * Play a track in the collection.
     * @param index The index of the track to be played.
     */
    public void playTrack(int index)
    {
        if(validIndex(index)) {
            //stop currently playing track, if one is being played.
            stopPlaying();
            Track track = trackList.get(index);
            player.startPlaying(track.getFilename());
            //increment current song's counter for amt played.
            track.incrementCount();
            System.out.println("Now playing: " + track.getArtist() + " - " + track.getTitle() + " - Times played : " + track.getCount());
        }   
    }

    /**
     * For Q34.
     */
    public void multiplesOfFive(int min, int max){
        index = min;
        while(index <= max){
            System.out.println(index);
            index = index + 5;
        }
    }
    
    /**
     * For Q35 & 36.
     */
    public void printTotalSum(int min, int max){
        index = min; //start counting from min.
        int sum = 0; //sum is nothing. acts like a piggy bank.
        while(index <= max){
            sum = sum + index; //index is added to sum to increment.
            index = index + 1; //index is min + 1(so first loop,1+1)
        }
        System.out.println(sum);
    }
    
    /**
     * Stop the player.
     */
    public void stopPlaying()
    {
        player.stop();
    }
    
    /**
     * For Q37
     */
    public boolean isPrime(int n){
        boolean prime;
        prime = true;
        index = 0;
        int divisor = 2;
        while(divisor < (n-1)){
            if(n % divisor == 0){
                prime = false;
                return prime;
            }else{
                divisor = divisor + 1;//the divisor.
                index = index + 1;//number being counted, the dividend.
                //NOT used anywhere else.
            }
        }
        return prime;
    }
    
    /**
     * Determine whether the given index is valid for the collection.
     * Print an error message if it is not.
     * @param index The index to be checked.
     * @return true if the index is valid, false otherwise.
     */
    private boolean validIndex(int index)
    {
        // The return value.
        // Set according to whether the index is valid or not.
        boolean valid;
        
        if(index < 0) {
            System.out.println("Index cannot be negative: " + index);
            valid = false;
        }
        else if(index >= trackList.size()) {
            System.out.println("Index is too large: " + index);
            valid = false;
        }
        else {
            valid = true;
        }
        return valid;
    }
}
