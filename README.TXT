Project: music-organizer-v4. A project to collect audio files.
Authors: David J. Barnes and Michael Kölling

This project is part of the material for the book

   Objects First with Java - A Practical Introduction using BlueJ
   Seventh edition
   David J. Barnes and Michael Kölling

It is discussed in chapter 4.

To start:

Create a MusicOrganizer object. This will scan the default audio 
folder for any mp3 files. The default folder is the one named
"audio", in the same directory as the project folder.

You can place your own mp3 files into this folder to include and 
play them. Just make sure the filename is of the expected format: 

  artist name-track name.mp3

Lab report (part a = question, part b = answer)

29. Add a playCount field to the Track class. Provide methods to reset the 
count to zero and to increment it by one.

  public void resetCount(){
        playCount = 0;
    }
    
    public void incrementCount(){
        //OR playCount ++;
        playCount = playCount + 1;
    }

30. Have the MusicOrganizer increment the play count of a track whenever it 
is played via the playTrack method.

public class Track{
...

    public int getCount(){
        return playCount;
    }
}


public class MusicOrganizer{
   ...

    /**
     * Play a track in the collection.
     * @param index The index of the track to be played.
     */
    public void playTrack(int index)
    {
        if(validIndex(index)) {
            Track track = trackList.get(index);
            player.startPlaying(track.getFilename());
            track.incrementCount();
            System.out.println("Now playing: " + track.getArtist() + " - " + track.getTitle() + " - Times played : " + track.getCount());
        }
    }
}
31. Add a further field, of your choosing, to the Track class, and provide 
accessor and mutator methods to access and change it in some way. Find a way 
to use this information in your version of the project; for instance, include 
it in a track’s details string, or allow it to be set via a method in the 
MusicOrganizer class.



32. If you play two tracks without stopping the first one, both will play 
simultaneously. This is not very useful. Change your program so that a playing 
track is automatically stopped when another track is started.



33. Suppose we express the first version of the key search in pseudo-code as follows:
boolean missing = true;
while(missing) {
if(the keys are in the next place) {
missing = false;
}
}
Try to express the second version by completing the following outline:
boolean found = false;
while(. . .) {
if(the keys are in the next place) {
...
}
}



34. Write a while loop (for example, in a method called multiplesOfFive) that prints out 
all multiples of 5 between 10 and 95.



35. Write a while loop to sum the values 1 to 10 and print the sum once the loop has 
finished.



36. Write a method called sum with a while loop that adds up all numbers between two 
numbers a and b. The values for a and b can be passed to the sum method as parameters.



37. Challenge exercise Write a method isPrime(int n) that returns true if the parameter 
n is a prime number, and false if it is not. To implement the method, you can write a while 
loop that divides n by all numbers between 2 and (n–1) and tests whether the division yields 
a whole number. You can write this test by using the modulo operator (%) to check whether 
the integer division leaves a remainder of 0 (see the discussion of the modulo operator in 
Section 3.12.3).



38. In the findFirst method of MusicOrganizer, the loop’s condition repeatedly asks the 
trackList collection how many tracks it is storing. Does the value returned by size vary 
from one check to the next? If you think the answer is no, then rewrite the method so that 
the number of tracks is determined only once and stored in a local variable prior to 
execution of the loop. Then use the local variable in the loop’s condition rather than 
the call to size. Check that this version gives the same results. If you have problems
completing this exercise, try using the debugger to see where things are going wrong.



39. Implement a method in your music organizer that lets you specify a string as a parameter, 
and then removes all tracks whose titles contain that string.


