import java.util.*; 

class Player{
   private String name = "unknown";
   private int matches = 0;
    
   public String toString(){
      if(matches == 1){
         return(name + " has 1 match."); //break this up for grammatical sense
      } else{return(name + " has " + matches + " matches.");
      }
   }
    
   Player(){
      name = "unknown";
      matches = 0;
   }
   
   Player(String n){
      name = n;
   }
   
   public String getName(){
      return(name);
   }
   
   public int getMatchesWon(){
      return(matches);
   }
   
   public void setName(String n){
      name = n;
   }
   
   public void setMatches(int m){
      matches = m;
   }
   
   public void addMatch(){
      matches++;
   }  

}