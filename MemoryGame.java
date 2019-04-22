import java.util.*; 
   
class MemoryGame extends Game{
   //extending game
   //need to have a setBoard function that chooses the board size
   boolean isWinner=false;
   char [][] grid = new char[2][2];
   char [][] grid = new char[3][3];
   char [][] grid = new char[4][4];
   int turnCount=0; 
   String msg = "";
   char symbol;
   
   MemoryGame(){
      boardChoice();
      setUp();
  }
  public void boardChoice(){
   System.out.println("Board A: 2*2 | Board B: 3*3 | Board C: 4*4");
   System.out.println("Enter the letter of the board you would like to play: ");
   char getBoardChoice() = input.nextInt('A'|'B'|'C');
   if getBoardChoice('A') = //go to 2*2 board
   if getBoardChoice('B') = //go to 3*3 board //will need a free space - randomized?
   if getBoardChoice('C') = //go to 4*4 board                      
  }
   
  public void setUp(){ 
      for (int row = 0; row < grid.length; row++) {
         for (int col = 0; col < grid[row].length; col++) {
            grid[row][col]='*';
            System.out.print(grid[row][col] + " ");
         }
         System.out.println();
      }
   
      isWinner = false;
      turnCount=0; 
   }
   
   //public int getTurnCount(){
      //return(turnCount);}

   //public char getSymbol(){
      //if (turnCount%2 == 0){
         //symbol = 'x';
      }
      //else{
         //symbol= 'o';
      }
      //return (symbol);
   }
   public boolean isWinner(){
      return(isWinner);
   }
   //match for randomized img
   //tempMatch1=inputChoice + inputChoice, tempMatch2
   //tempRandMatch = randImg + randImg
   //not getSymbol, getInputChoice?
   public void checkWinner(){
      //checking column
      for(int c=0; c<3;c++){
         if(grid[c][0]==getSymbol() && 
            grid[c][1]==getSymbol() &&
            grid[c][0]==getSymbol()){
            isWinner = true;}
          }    
      //checking rows
      for(int r=0; r<3;r++){
         if(grid[0][r]==getSymbol() && 
            grid[1][r]==getSymbol() &&
            grid[2][r]==getSymbol()){
            isWinner = true;}
          }
      //check diagonal     
      if(grid[0][0]==getSymbol() && 
         grid[1][1]==getSymbol() &&
         grid[2][2]==getSymbol()){
         isWinner = true;}
      
      if(grid[2][0]==getSymbol() && 
            grid[1][1]==getSymbol() &&
            grid[0][2]==getSymbol()){
            isWinner = true;}
   }
   //will have 3 different isValidInput because of 3 boards <=1,<=2,<=3
   public boolean isValidInput(int row, int col){
      if(row<=2 && col<=2){
         //if flipped already
         if (grid[row][col]==//'x'||grid[row][col]=='o'){System.out.println ("!"); return false;}
         return(true);
      }
      else
         return(false); 
   } 
   
   public void takeTurn(int rowChoice, int colChoice){
      //if = first button turn, else = if second button turn
      if (turnCount%2 == 0){
         grid[rowChoice][colChoice] = //leave card up;
      }
      {
      //doubt this would be an else
      //want it to be the second choice in game turn
      else
         grid[rowChoice][colChoice] = //'o';
      }
      turnCount++;  
   } 
   
   // public String gameOverStatus(){
//       if(isWinner())
//          return("winner");
//       else if (turnCnt ==3)
//          return("loser");
//       else
//          return("inProgress");
//    }
   //dont remember if this prints just inital grid 
   //or if calls printGrid to incorporated in turns
   public void printGrid(){
      for (int row = 0; row < grid.length; row++) {
         for (int col = 0; col < grid[row].length; col++) {
            System.out.print(grid[row][col] + " ");
         }
         System.out.println();
      }
   }
      
   public String toString(){
      String s = msg + "you have taken " + turnCount + " turns";
      return(s);
   }

}
