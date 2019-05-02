import java.util.*;

class MemoryGame1{
   public static void main(String argv[]){
      Scanner input = new Scanner(System.in);
      //int [][] board
      public static void boardChoice(){
         
      System.out.println("Board 1: 2*2 | Board 2: 4*4 | Board 3: 6*6");
      System.out.println("Enter the number of the board you would like to play: ");
      int getBoardChoice() = input.nextInt();
      //go to 2*2 board
      if getBoardChoice() == 1{
         int sizechoice = 2;
         //system.println(sizechoice);
         makeBoard(sizechoice);
      }   
       //go to 4*4 board
       if getBoardChoice() == 2{
         int sizechoice = 4;
         //system.println(sizechoice);
        makeBoard(sizechoice);
       }     
       //go to 6*6 board
       if getBoardChoice() == 3{ 
         int sizechoice = 6;
         //system.println(sizechoice);
         makeBoard(sizechoice);       
       }
       
       else{
         system.println("You have to choose a valid board size!");
       } }                        
         //int sizechoice2 = 4;
         //int sizechoice3 = 6;
       public static void makeBoard(sizechoice){
         
         int[][] board = new int [sizechoice][sizechoice];
      
         int sc = sizechoice*sizechoice; //total amt of numbers needed

         List<Integer> boardnumbers = new ArrayList<>(sc); //makes list w pairs of number for board size
         for(int x = 0; x<2; x++){
            for (int i = 0; i < (sc/2); i++){
               boardnumbers.add(i);
            }
         } 
      Collections.shuffle(boardnumbers); //shuffles pairs to randomize
      
      int order = 0;
      for(int r=0;r<sizechoice;r++){ //makes grid w images from random numbers
         for(int c=0;c<sizechoice;c++){
            board[r][c] = boardnumbers.get(order);;
            order++;
         }
       }
       
       for(int r=0;r<sizechoice;r++){ //makes grid w images from random numbers
         for(int c=0;c<sizechoice;c++){
            System.out.print(board[r][c]);
         }
         System.out.print("\n");
       }


   }
   public static void createBoard(){
  
   int [][] createIntBoard()
     
      int total = (sizechoice * sizechoice) + 1;
      int [][] temp = new int [sizechoice][sizechoice];
     
      int order = 1;
     
      for(int r=0;r<sizechoice;r++){
         for(int c=0;c<sizechoice;c++){
            temp[r][c] = order;
            order++;
         }
       }
      
       return(temp);
   }
  }
 }    
}