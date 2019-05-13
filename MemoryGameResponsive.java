import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.geometry.Pos;
import javafx.scene.paint.Color; 
import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.geometry.Insets;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.text.TextAlignment;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.TextField;
import java.util.*;


public class MemoryGameResponsive extends Application
{
   MemoryGame mg;
   GridPane grid;
   ImageView [][] images;
   Button [][] board;
   Label resultLabel;
   Button flipcards;
   Label error;
   int sizechoice;
   private RadioButton small;
   private RadioButton medium;
   private RadioButton large;
   VBox vbox;
   VBox sizestuff;
   Label spacer;
   Label spacer2;
   Button [] ClckBtns;
   Button [][] WinBtns;
   int clicks;
   int clickdiv;
   Label p1name;
   Label p2name;
   TextField onename;
   TextField twoname;
   int [][] intboard;
   int [] sendints;
   int [] winInts;
   Label gameLabel;
   
   public static void main(String[] args)
   {
      // Launch the application.
      launch(args);
   }
   
   @Override
   public void start(Stage stage)
   {
     
      mg = new MemoryGame();
      mg.setUp();
      
      // label for choose board
      Label promptLabel = new Label("WELCOME! PLEASE SET UP YOUR GAME.");
      promptLabel.getStyleClass().add("prompt");
      
      p1name = new Label("PLAYER 1:  ");
      p1name.getStyleClass().add("radiobutt");
      p2name = new Label("PLAYER 2:  ");
      p2name.getStyleClass().add("radiobutt");
      onename = new TextField();
      twoname = new TextField();
      
      HBox p1info = new HBox(p1name, onename);
      HBox p2info = new HBox(p2name, twoname);
      p1info.getStyleClass().add("vbox");
      p2info.getStyleClass().add("vbox");
      p1info.setAlignment(Pos.CENTER);
      p2info.setAlignment(Pos.CENTER);
      
      VBox names = new VBox(p1info, p2info);
      names.getStyleClass().add("vbox");
      
      // different size radio buttons
      small = new RadioButton(" SMALL  (2x2) ");
      small.getStyleClass().add("radiobutt");
      
      medium = new RadioButton("MEDIUM (4x4)");
      medium.getStyleClass().add("radiobutt");
      
      large = new RadioButton(" LARGE  (6x6) ");
      large.getStyleClass().add("radiobutt");
      
      // Add the choices to a ToggleGroup.
      ToggleGroup radioGroup = new ToggleGroup();
      small.setToggleGroup(radioGroup);
      medium.setToggleGroup(radioGroup);
      large.setToggleGroup(radioGroup);
      
      // button for size choice
      Button playButton = new Button("PLAY!");
      playButton.getStyleClass().add("miscbutt");
      playButton.setOnAction(new PlayButtonHandler());
      
      // vbox with all of the size choice stuff
      sizestuff = new VBox(promptLabel,names,small,medium,large,playButton);
      sizestuff.setAlignment(Pos.CENTER);
      sizestuff.getStyleClass().add("vbox");
      sizestuff.setSpacing(40);
      
      //format and label stuff
      
      ImageView header = new ImageView("file:title.png");
      
      spacer = new Label(" \n ");
      spacer.getStyleClass().add("spacer");
      
      error = new Label("  ");
      error.getStyleClass().add("spacer");
      
      spacer2 = new Label("  ");
      spacer2.getStyleClass().add("spacer");
      
      vbox = new VBox(header,spacer,sizestuff,spacer2);
      vbox.setAlignment(Pos.CENTER);
      vbox.getStyleClass().add("vbox");
      
      // button to flip images - don't have a handler yet
      flipcards = new Button("FLIP CARDS");
      flipcards.getStyleClass().add("miscbutt");
      flipcards.setOnAction(new FlipButtonHandler());
      
      Scene scene = new Scene(vbox,800,1000); //create scene
      
      stage.setScene(scene); //set scene
     
      scene.getStylesheets().add("MemGame.css");
     
      stage.setTitle("Memory Game"); //set title
      
      stage.show(); //show window
   }

   
   GridPane makeBoard(){
   
      // board made with sizes from radio buttons
      GridPane tempgrid = new GridPane();
      
      tempgrid.setVgap(10);
      tempgrid.setHgap(10);
      tempgrid.setAlignment(Pos.CENTER);
      
      board = new Button [sizechoice][sizechoice];
      for(int r=0;r<sizechoice;r++){
         for(int c=0;c<sizechoice;c++){
            board[r][c] = new Button("");
            board[r][c].setOnAction(new GridHandler());
            board[r][c].setStyle("-fx-background-color: #D34C6E;-fx-background-radius: 0");
            board[r][c].setPrefWidth(80);
            board[r][c].setPrefHeight(80);
            tempgrid.add(board[r][c],c,r);
         }
      }
      
      return tempgrid;
   
   }
   
   int [][] createIntBoard(){
      
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
   
   ImageView[][] makeImageGrid(){
      
      ImageView[][] imagegrid = new ImageView [sizechoice][sizechoice];

      int [][] orderboard = mg.getSecretBoard();
      
      String order = "empty";
      for(int r=0;r<sizechoice;r++){ //makes grid w images from random numbers
         for(int c=0;c<sizechoice;c++){
            order = Integer.toString(orderboard[r][c]);
            imagegrid[r][c] = new ImageView(new Image("file:"+order+".png"));
            imagegrid[r][c].setFitWidth(60);
            imagegrid[r][c].setFitHeight(60);
         }
      }
      return(imagegrid);
       
   }
   
   Label makeLabel(String s){
   
      Label templabel = new Label(s);
      templabel.setTextFill(Color.web("#258EA6"));
      templabel.setFont(Font.font("Didact Gothic",40));
      return templabel;
   }
   
   class GridHandler implements EventHandler<ActionEvent>
   //trying to make it so that if the images flipped match, that number is saved into a winning list
   //then when the flip button is pressed, those winning buttons stay flipped u
   {
      @Override
      public void handle(ActionEvent event)
      {
         int row = 7;
         int column = 7;
         for(int r=0;r<sizechoice;r++){
            for(int c=0;c<sizechoice;c++){
               if(event.getSource().equals(board[r][c])){
                  row = r;
                  column = c;}
             }
          }
          board[row][column] = new Button("", images[row][column]); 
          board[row][column].setPrefWidth(80);
          board[row][column].setPrefHeight(80);
          board[row][column].getStyleClass().add("imgbutt");   
          grid.add(board[row][column],column,row); 
         
          int tempchoice = intboard[row][column];
          sendints[clicks] = tempchoice;
          
          if(clicks == 0){
            gameLabel.setText("Great! Pick your next card.");
          }
          
          clicks = clicks+1;
          
          boolean matched = false;
         
          if(clicks == 2){
            clicks = 0;
            for(int r=0;r<sizechoice;r++){
               for(int c=0;c<sizechoice;c++){
                  board[r][c].setOnAction(null);
                }
             }
            mg.takeTurn(sendints[0],sendints[1]);
            matched = mg.checkMatch(sendints[0],sendints[1]);//take turn also calls this function but we need to know if it's a match here
            ArrayList<Integer> mlist = mg.getMatches();
            int size = mlist.size();
            
            if(matched){
               gameLabel.setText("Congrats - it's a match!");
               for(int r=0;r<sizechoice;r++){
                  for(int c=0;c<sizechoice;c++){
                     for(int i=0;i<size;i++){
                           if(mlist.get(i) == intboard[r][c]){
                              board[r][c].setOnAction(null);
                           } else {
                              board[r][c].setOnAction(new GridHandler());
                           }
                        }
                   }
                }
                if(mg.isWinner()){
                  Player [] playerlist = mg.getPlayerList();
                  if(playerlist[0].getMatchesWon() > playerlist[1].getMatchesWon()){
                     gameLabel.setText(playerlist[0].getName() + " won!");
                  } else if (playerlist[0].getMatchesWon() == playerlist[1].getMatchesWon()) {
                     gameLabel.setText("You tied!");
                  } else {
                     gameLabel.setText(playerlist[1].getName() + " won!");
                  }
                  flipcards.setOnAction(null);
                }

            } else {
               gameLabel.setText("Not a match - press the button to flip them back over and end your turn!");
               gameLabel.setTextAlignment(TextAlignment.CENTER);
            }
   
      }
   }
  }
   
   
   class FlipButtonHandler implements EventHandler<ActionEvent>{
      @Override
      public void handle(ActionEvent event){
      
         int tc = mg.getTurnCount();
         Player [] plyrs = mg.getPlayerList();
         int toggle = tc%2;
         String displayname = plyrs[toggle].getName();
         
         String display = displayname + ", flip your first card.";
         gameLabel.setText(display);
         
         clicks = 0;
         
         ArrayList<Integer> mlist = mg.getMatches();
         int size = mlist.size();
         boolean nextbutt = true;

         for(int r=0;r<sizechoice;r++){
            for(int c=0;c<sizechoice;c++){
              nextbutt = true;
              for(int i=0;i<size;i++){
               if(mlist.get(i) == intboard[r][c]){
                  board[r][c].setOnAction(null);
                  nextbutt = false;
              }}
               if (nextbutt == true){
                  board[r][c] = new Button("");
                  board[r][c].setOnAction(new GridHandler());
                  board[r][c].getStyleClass().add("boardbutt");
                  board[r][c].setPrefWidth(80);
                  board[r][c].setPrefHeight(80);
                  grid.add(board[r][c],c,r);
                  }
               }
            }
         }
         
     }
   
   class PlayButtonHandler implements EventHandler<ActionEvent>
   {
      @Override
      public void handle(ActionEvent event)
      {
      
         if (small.isSelected())
            sizechoice = 2; //2x2 board
         
         if (medium.isSelected())
            sizechoice = 4; //3x3 board
         
         if (large.isSelected())
            sizechoice = 6; //4x4 board
            
         mg.setBoardSize(sizechoice);
         mg.setSecretBoard(sizechoice);
         mg.setPlayBoard(sizechoice);
            
         grid = makeBoard(); //makes them for the correct size board
         images = makeImageGrid();
         intboard = createIntBoard();
         
         String p1 = onename.getText();
         String p2 = twoname.getText();
         
         mg.setPlayerList(p1,p2);
         
         vbox.getChildren().remove(sizestuff); //clearing scene of size choice
         vbox.getChildren().remove(spacer2);
      
         spacer.setFont(Font.font("Helvetica",50));
         
         String firstturn = p1 + ", flip your first card.";
         gameLabel = new Label(firstturn);
         gameLabel.getStyleClass().add("prompt");
         
         vbox.getChildren().addAll(gameLabel, grid); //adds everything for game to scene
         vbox.getChildren().add(error);
         vbox.getChildren().add(flipcards);
         
         clicks = 0; //to set up for event handler
         sendints = new int [2]; //also for event handler
       
      }
       
   }


}