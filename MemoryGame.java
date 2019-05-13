import java.util.*;

class MemoryGame{

   String [][] playBoard;
   int [][] secretBoard;
   boolean match;
   int turncount;
   boolean winner;
   Player [] playerlist;
   int sizechoice;
   ArrayList<Integer> matched;
   
   public void setUp(){
      //instructions();    use with driver
      winner = false;
      turncount = 0;
      //playerlist = choosePlayerList();  use w driver
      //sizechoice = chooseBoardSize();   use w driver
      //showBoard();    use w driver
      //setSecretBoard(sizechoice); use with driver
      //setPlayBoard(sizechoice);   use with driver
      matched = new ArrayList<Integer>();
      matched.add(0); //so there's no null exception
   }
   
   public void takeTurn(int choice1, int choice2){
      
      int toggle = turncount%2;
      
      boolean play = validInput(choice1,choice2);
      
      if(play == true){
         
         //System.out.println("Let's see what your choices are!");
         //showChoice(choice1, choice2);
         match = checkMatch(choice1,choice2);
         
         if(match == false){
            //System.out.println("Not a match. Let's flip them back over.");
            //flipChoice(choice1, choice2);
            //printBoard();
         }else{
            //System.out.println("That's a match! Congrats!");
            matched.add(choice1);
            matched.add(choice2);
            playerlist[toggle].addMatch();
         }
         
         checkWinner();
         
         turncount++;
      }
      
   }
      
   
   public void instructions(){
      
      System.out.println("Let's play a Memory Game!");
      System.out.println("This is a two player game, where you try to match numbers.");
      System.out.println("You will get to choose the size of the board you play on, and you can flip two cards at a time.");
      System.out.println("The winner will be the player with the most matches at the end of the game. Good luck!!");
  
   }
   
   public Player [] choosePlayerList(){
      
      Scanner input = new Scanner(System.in);
      
      Player pone = new Player();
      Player ptwo = new Player();
      
      System.out.println("\nWhat is player one's first name?");
      String nameone = input.next();
      System.out.println("Great. What is player two's first name?");
      String nametwo = input.next();
      
      pone.setName(nameone);
      ptwo.setName(nametwo);
   
      Player [] tempplayers = {pone, ptwo};
   
      return(tempplayers);
      
   }
   
   public void setPlayerList(String n1,String n2){
      
      Player p1 = new Player(n1);
      Player p2 = new Player(n2);
      
      playerlist = new Player [2];
      playerlist[0] = p1;
      playerlist[1] = p2;
      
   }
   
   public Player [] getPlayerList(){
      return(playerlist);
   }     
   
   public int chooseBoardSize(){
      
      boolean valid = false;
      
      Scanner input = new Scanner(System.in);
      
      System.out.println("\nNow please choose your board size. You can play a 2x2 board, a 4x4 board, or a 6x6 board.");
      System.out.println("Which one would you like? (2/4/6)");
      
      String tempchoice = input.next(); //doing this with strings so that there isn't green vomit if someone puts in turtles or something like that
      
      String [] validchoice = {"2","4","6"};
      
      do{
         for(int i=0;i<3;i++){
            if(tempchoice.equals(validchoice[i])){
               valid = true;
            }
         }
         if(valid == false){
            System.out.println("That is not a valid size. Please enter one of these options:");
            System.out.println("    2, 4, or 6");
            tempchoice = input.next();
         }
      } while(valid == false);
      
      int choice = Integer.parseInt(tempchoice);
      
      return(choice);
   
   }
   
   public void setBoardSize(int sc){
      sizechoice = sc;
   }
   
   public int getBoardSize(){
      return(sizechoice);
   }
   
   public void showBoard(){
   
      System.out.println("To take a turn, you will choose which two spaces on the board you would like to flip over.");
      System.out.println("Here are the numbers that correspond with the spaces:");
      
      int visnum = 1;
      
      for(int r=0; r<sizechoice; r++){
         for(int c=0; c<sizechoice; c++){
            if(visnum<10){
               System.out.print(visnum + "  ");
            }else{
               System.out.print(visnum + " ");
            }
            visnum++;
         }
         System.out.print("\n");
      }
      
    }
    
   public void setSecretBoard(int sc){
    
      secretBoard = new int [sc][sc];
      int total = sc*sc; //total amt of numbers needed
      List<Integer> boardNums = new ArrayList<>(total); //makes list w pairs of number for board size
      
      for(int x = 0; x<2; x++){
        for (int i = 1; i <=(total/2); i++){
           boardNums.add(i);
        }
      } 
      
      Collections.shuffle(boardNums); //shuffles pairs to randomize
            
      int order = 0;
      for(int r=0;r<sc;r++){ //makes grid w images from random numbers
        for(int c=0;c<sc;c++){
           secretBoard[r][c] = boardNums.get(order);
           order++;
         }
      }
      
    }
     
   public int[][] getSecretBoard(){
      return(secretBoard);
    }   
    
   public void setPlayBoard(int sc){
    
      playBoard = new String[sc][sc];
      
      for(int r=0; r<sc; r++){
         for(int c=0; c<sc; c++){
            playBoard[r][c] = "#";
         }
      }
   } 
   
   public boolean validInput(int c1,int c2){
      
      boolean valid = true;

      int size = matched.size();
         
      for(int i=0;i<size;i++){
         if(matched.get(i) == c1 || matched.get(i) == c2){
            System.out.println("You already found the match for one of those spots!");
            i = 100;
            valid = false;
         }
      }
       
      if(c1 == c2){
         System.out.println("You can't pick the same spot!");
         valid = false;
      }
      
      if(c1<1 || c2<1 || c1>(sizechoice*sizechoice) || c2>(sizechoice*sizechoice)){
         System.out.println("One of your choices is out of bounds!");
         valid = false;
      }
      
      return(valid);
   }
   
   public void showChoice(int choice1,int choice2){
   
      int rc1 = (choice1 - 1)/sizechoice; //row and column of choice 1
      int cc1 = (choice1 - 1)%sizechoice;
         
      int rc2 = (choice2 - 1)/sizechoice; //row and column of choice 2
      int cc2 = (choice2 - 1)%sizechoice;
      
      playBoard[rc1][cc1] = Integer.toString(secretBoard[rc1][cc1]);
      playBoard[rc2][cc2] = Integer.toString(secretBoard[rc2][cc2]);
      
      for(int r=0; r<sizechoice; r++){
         for(int c=0; c<sizechoice; c++){
            System.out.print(playBoard[r][c] + " "); 
         }
         System.out.print("\n");
      }
      
   }
   
   public void flipChoice(int choice1, int choice2){
   
      int rc1 = (choice1 - 1)/sizechoice; //row and column of choice 1
      int cc1 = (choice1 - 1)%sizechoice;
         
      int rc2 = (choice2 - 1)/sizechoice; //row and column of choice 2
      int cc2 = (choice2 - 1)%sizechoice;
      
      playBoard[rc1][cc1] = "#";
      playBoard[rc2][cc2] = "#";
      
   }
   
   public void printBoard(){
      
      for(int r=0; r<sizechoice; r++){
         for(int c=0; c<sizechoice; c++){
            System.out.print(playBoard[r][c] + " "); 
         }
         System.out.print("\n");
      }
      
   }
   
   public int getTurnCount(){
      return(turncount);
   }
   
   public boolean isWinner(){
      return(winner);
   }
   
   public void checkWinner(){
      if((matched.size()-1) == sizechoice*sizechoice){
         winner = true;
      }
   }
   
   public boolean checkMatch(int choice1, int choice2){
      
      int rc1 = (choice1 - 1)/sizechoice; //row and column of choice 1
      int cc1 = (choice1 - 1)%sizechoice;
         
      int rc2 = (choice2 - 1)/sizechoice; //row and column of choice 2
      int cc2 = (choice2 - 1)%sizechoice;
      
      boolean match = false;
      
      if(secretBoard[rc1][cc1] == secretBoard[rc2][cc2]){
         match = true;
      }
      
      return(match);
   }
   
   public ArrayList<Integer> getMatches(){
      return(matched);
   }
   
}

         
      
      
    
       
   
   
