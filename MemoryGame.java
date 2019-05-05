import java.util.*;

class MemoryGame{
   //public static void main(String argv[]){
   //use scanner to take in the client input
   Scanner input = new Scanner(System.in);
   //MemoryGame game = new MemoryGame();
   int getBoardChoice = 0;
   int  getRow = 0;
   int  getCol = 0;
   int [][] playBoard;
   int [][] secretBoard;
   int sizeChoice = 0;
   int turn = 0;
   boolean match = false;
   int matchCount = 0;
   int turnCount = 1;
   boolean winner = false;
   
   public void boardChoice(){
         
      System.out.println("Board 1: 2*2 | Board 2: 4*4 | Board 3: 6*6");
      System.out.println("Enter the number of the board you would like to play: ");
      getBoardChoice = input.nextInt();
      //go to 2*2 board
     // System.out.println(getBoardChoice);
      if (getBoardChoice == 1){
         sizeChoice = 2;
         //system.println(sizeChoice);
         playBoard = playBoard(sizeChoice);
         secretBoard = makeBoard(sizeChoice);
      }   
       //go to 4*4 board
      else if (getBoardChoice == 2){
         sizeChoice = 4;
         //system.println(sizeChoice);
         playBoard = playBoard(sizeChoice);
         secretBoard = makeBoard(sizeChoice);
      }     
       //go to 6*6 board
      else if (getBoardChoice == 3){ 
         sizeChoice = 6;
         //system.println(sizeChoice);
         playBoard = playBoard(sizeChoice);
         secretBoard = makeBoard(sizeChoice);      
      } 
      else{
         System.out.println("You have to choose a valid board size!");
      } 
      int sc = sizeChoice*sizeChoice;
      while(winner == false || turn <= sc){
         System.out.println("Enter a row choice: ");
         getRow = input.nextInt();
         System.out.println("Enter a column choice: ");
         getCol = input.nextInt();
         takeTurn(getRow, getCol, sizeChoice, playBoard, secretBoard);
         turn++;  
      }
   }                        
   public int [][] makeBoard(int sizeChoice){
         
      int[][] secretBoard = new int [sizeChoice][sizeChoice];
      int sc = sizeChoice*sizeChoice; //total amt of numbers needed
      List<Integer> boardNums = new ArrayList<>(sc); //makes list w pairs of number for board size
      for(int x = 0; x<2; x++){
         for (int i = 1; i <=(sc/2); i++){
            boardNums.add(i);
         }
      } 
      Collections.shuffle(boardNums); //shuffles pairs to randomize
      
      int order = 0;
      for(int r=0;r<sizeChoice;r++){ //makes grid w images from random numbers
         for(int c=0;c<sizeChoice;c++){
            secretBoard[r][c] = boardNums.get(order);
            order++;
         }
      }     
//     for(int r=0;r<sizeChoice;r++){ //for testing: checks if grid is randomized, take out before turning in
//          for(int c=0;c<sizeChoice;c++){
//             System.out.print(secretBoard[r][c] + " ");
//          }
//          System.out.print("\n");
//       }
       return secretBoard;
   }
   public void createBoard(int sizeChoice){//set to 1 because we have 0 as place marker. 

      int total = (sizeChoice * sizeChoice) + 1;
      int [][] temp = new int [sizeChoice][sizeChoice];
      int order = 1;
      for(int r=0;r<sizeChoice;r++){
         
         for(int c=0;c<sizeChoice;c++){
            temp[r][c] = order;
            order++;
         }
      }
   }
   public int [][] playBoard(int sizeChoice){//Game play starts at 1 and increases turn after 2 choices
      
      int[][] playBoard = new int [sizeChoice][sizeChoice];
      for(int r=0;r<sizeChoice;r++){ //makes playing grid          
         
         for(int c=0;c<sizeChoice;c++){
            playBoard[r][c] = 0;
            System.out.print(playBoard[r][c] + " ");
         }
         System.out.print("\n");
      }
      return playBoard;
   }
   public void takeTurn(int rowChoice, int colChoice, int sizeChoice, int [][] playBoard, int [][] secretBoard){
   
      playBoard[rowChoice][colChoice] = secretBoard[rowChoice][colChoice];
      upDatedBoard(sizeChoice, playBoard);
      if(isMatch(rowChoice, colChoice, sizeChoice, playBoard, secretBoard) == true){
         System.out.println("You found a match!"); 
      }
      else{
         if (turnCount%2 == 0 && turnCount >= 2){
         
            for(int r=0;r<sizeChoice;r++){ //makes grid flipped back over
            
               for(int c=0;c<sizeChoice;c++){
                  if (playBoard[r][c] != 0 ){
                     playBoard[r][c] = 0;
                  }
               }
            }
         }
         upDatedBoard(sizeChoice, playBoard);
         isWinner(rowChoice, colChoice, sizeChoice, playBoard, secretBoard);
      }
      turnCount++;
   }
   public void upDatedBoard(int sizeChoice, int [][] playBoard){
      
      for(int r=0;r<sizeChoice;r++){ //makes grid w/ updated game play
         
         for(int c=0;c<sizeChoice;c++){
           
            System.out.print(playBoard[r][c] + " ");
         }
         System.out.print("\n");
      }
   }
   public boolean isMatch (int rowChoice, int colChoice, int sizeChoice, int [][] playBoard, int [][] secretBoard){
      
      for(int r=0;r<sizeChoice;r++){ //checks grid for matches
      
         for(int c=0;c<sizeChoice;c++){
            if (playBoard[r][c] != 0 ){
               if (playBoard[r][c] == playBoard[rowChoice][colChoice] ){
                  matchCount++;   
               }
            }      
         }
      }
      System.out.println(matchCount);
      if(matchCount == 3){
         match = true;
         matchCount = 0;
      }
      else{      
         match = false;
      }
      return match;
   }
   public boolean isWinner (int rowChoice, int colChoice, int sizeChoice, int [][] playBoard, int [][] secretBoard){
      
      for(int r=0;r<sizeChoice;r++){ //checks winner
      
         for(int c=0;c<sizeChoice;c++){
               if (playBoard[r][c] != secretBoard[rowChoice][colChoice] ){
                  winner = false;
                  return winner;  
               }    
         }
      }
   winner = true;
   return winner;
   }
}