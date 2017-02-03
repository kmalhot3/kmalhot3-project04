//Jukebox.java
//package queues;

import java.io.*;
import java.util.*;
import cs1c.*;


public class Jukebox  {
   private Queue<SongEntry> favoritesPL, loungePL, roadTripPL ;

   //Constructor for Jukebox initializes the 3 queues
   public Jukebox(){
      /*System.out.println("Constructing Jukebox");
     Queue<SongEntry> favoritesPL = new Queue<SongEntry>("favorites");
     Queue<SongEntry> loungePL = new Queue<SongEntry>("loungePL");
     Queue<SongEntry> roadTripPL = new Queue<SongEntry>("roadTripPL");
     */ 
      //System.out.println(favoritesPL.toString());
      
		this.favoritesPL = new Queue<SongEntry>("favorites");
		this.loungePL = new Queue<SongEntry>("loungePL");
		this.roadTripPL = new Queue<SongEntry>("roadTripPL");
		//System.out.println(favoritesPL.toString());
   }

   //Reads the given file and searches allSongs and if the song is found, it is added
   // to the correct playlist
   public void fillPlaylists(String requestFile, SongEntry[] allSongs){
      ArrayList<String> tempList = new ArrayList<String>();
      try{
         Scanner scanner = new Scanner(new File(requestFile));

         //seperates the info by commas and newLines
         scanner.useDelimiter(",|\\n");
         while(scanner.hasNext()){
              tempList.add(scanner.next());
         }
         scanner.close();
      }
      catch(Exception e){
         System.out.println("File Not Found");
      }

      /* Tests the output of the tempList
      for(int i = 0; i < tempList.size(); i++){
         System.out.println(tempList.get(i));
      }
      */

      for(int i = 1; i < tempList.size(); i++){
       //  for(int j = 0; j < 5000; j++){
            //System.out.println("i = " + i  );
            SongEntry tempSongEntry = search(tempList.get(i),allSongs);
            //System.out.println(tempSongEntry);
           
            //if the song is not found, don't add null to the playlist
            if(tempSongEntry != null){
               //System.out.println("Song Entry not null");
               switch(tempList.get(i-1)){

                  // This is where I keep getting my error. 
                  case "favorites": favoritesPL.enqueue(tempSongEntry);
                                    break;
                  case "lounge": 
                                    loungePL.enqueue(tempSongEntry);
                                    //System.out.println(loungePL.toString());
                                    break;
                  case "road trip": roadTripPL.enqueue(tempSongEntry);
                                    break;
                  default: break;                  
           //    }
            }
         }
      }

   }


   //helper method to search the json file and return the Song Entry if found
   public SongEntry search(String songName, SongEntry[] allSongs) {
      
      for(int i = 0; i < allSongs.length; i++){
         if(allSongs[i].getTitle().equals(songName)){
         //   System.out.println("Found + "+ i);
          //  loungePL.enqueue(allSongs[i]);

            return allSongs[i];
         }
      }

      return null;
   }

   //Accessors
   public Queue<SongEntry> getFavoritePL(){
      return this.favoritesPL;
   }

   public Queue<SongEntry> getLoungePL(){
      return this.loungePL;
   }

   public Queue<SongEntry> getRoadTripPL(){
      return this.roadTripPL;
   }

}
