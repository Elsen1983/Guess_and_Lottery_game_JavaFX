package main.java.mainpack;


  /////////////////////
 //  I.M.P.O.R.T.S  //
/////////////////////
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

//The first tab is a guessing game. Guess a number 1 to 100.
// After each guess you are told whether you are too high or too low or if you have guessed the correct number.
// You are allowed 6 attempts.
// The application has a reset button, a guess button, a quit button and uses a random number generator.
// This game wins a 4 * prize.

public class Tab1_Guess_Game extends Tab implements EventHandler<ActionEvent> {


      //////////////////////////
     //  V.A.R.I.A.B.L.E.S.  //
    //////////////////////////
    private static VBox tab1VBox1 = new VBox();


    //Buttons
    private Button game1GuessBtn = new Button("Guess");
    private Button game1ResetBtn = new Button("Reset / New Game");
    private Button game1QuitBtn =  new Button("Quit");

    //Labels
    private Label introduction_game1_Lbl = new Label("Please enter a number between 1 and 100!");
    private Label resultLbl = new Label("");
    private Label counterLbl = new Label("");

    //TextFields
    private TextField enterNumber_game1_TxtF1 = new TextField();

    //counter for chance
    private int counter = 0;
    //check stars (because of reset)
    private int stars = 0;

    //generated random number
    private int randomNumber;
    //chosed number
    private int choosedNumber = 0;

      ///////////////////////////////////////
     //  G.E.T.T.E.R.S. + S.E.T.T.E.R.S.  //
    ///////////////////////////////////////

    public static VBox getTab1VBox1() { return tab1VBox1; }
    public static void setTab1VBox1(VBox tab1VBox1) {  Tab1_Guess_Game.tab1VBox1 = tab1VBox1; }

      ////////////////////////////////
     //  C.O.N.S.T.R.U.C.T.O.R.S.  //
    ////////////////////////////////

    public Tab1_Guess_Game() {

        Tab1_Guess_Game.this.setDisable(false);
        Tab1_Guess_Game.this.setText("Game1");

        //generate the number for game
        randomNumber = randomNumber();

        //FIRST TAB
        //.setSpacing(10) --> space between elements
        tab1VBox1.setSpacing(10);

        //'INTRODUCTION' LABEL font and font style
        introduction_game1_Lbl.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 15));
        tab1VBox1.getChildren().add(introduction_game1_Lbl);

        //'ENTER NUMBER' TEXTFIELD & style
        enterNumber_game1_TxtF1.setAlignment(Pos.CENTER);
        enterNumber_game1_TxtF1.setPromptText("E.g. 1, 45, 57, 2, 100");
        //next 3 line are necessary together
        enterNumber_game1_TxtF1.setMinWidth(40);
        enterNumber_game1_TxtF1.setPrefWidth(85);
        enterNumber_game1_TxtF1.setMaxWidth(120);
        tab1VBox1.getChildren().add(enterNumber_game1_TxtF1);

        //-- BUTTONS
        //'GUESS' BUTTON & style
        game1GuessBtn.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 15));
        game1GuessBtn.setOnAction(this);
        //'RESET' BUTTON & style
        game1ResetBtn.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 15));
        game1ResetBtn.setOnAction(this);
        tab1VBox1.getChildren().addAll(game1GuessBtn, game1ResetBtn);

        //'RESULT' LABEL
        resultLbl.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 15));
        tab1VBox1.getChildren().add(resultLbl);

        //'COUNTER' LABEL
        counterLbl.setText("You tried " + counter + " times from the maximum 6!");
        tab1VBox1.getChildren().add(counterLbl);

        //'QUIT' BUTTON
        game1QuitBtn.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 15));
        game1QuitBtn.setOnAction(this);
        tab1VBox1.getChildren().add(game1QuitBtn);

        //VBOX 'style'
        tab1VBox1.setAlignment(Pos.CENTER);
        //javafx css style
        tab1VBox1.setStyle("-fx-background-color: #f0ff61");

        //add the content
        Tab1_Guess_Game.this.setContent(tab1VBox1);
    }


      //////////////////////
     //  M.E.T.H.O.D.S.  //
    //////////////////////

    //simple random number generation (1-100)
    private int randomNumber() {
        int randomN;
        randomN = (int) (Math.random() * 100 + 1);
        System.out.println("Random Number: " + randomN);
        return randomN;
    }

    //in this tab I used the handle method for build up buttons actions
    @Override
    public void handle(ActionEvent event) {

        //check which button and addd action to them 1 by 1


        //GUESS BUTTON ACTON
        if (event.getSource() == game1GuessBtn) {
            //System.out.println("Counter is : " + counter);
            //maximum 6 guess
            if (counter < 6) {

                //check textfield input for validation
                if (!checkInput(enterNumber_game1_TxtF1.getText())) {
                    //do nothing because the input was wrong and the error messages in the method what is validating

                } else {
                    //input was fine,so .... first change counterLabel
                    counterLbl.setText("You tried " + counter + " times from the maximum 6!");

                    //check number range (smaller or bigger)
                    //bigger
                    if (choosedNumber > randomNumber) {
                        resultLbl.setText("Your number (" + choosedNumber + ") is too HIGH.");
                    } else if (choosedNumber < randomNumber) {
                        resultLbl.setText("Your number (" + choosedNumber + ") is too LOW.");
                    }
                    // --------- !!!!!!!!!!   WIN  !!!!!!!!!!!! ----------
                    else {
                        resultLbl.setText("You WIN!! The number was " + choosedNumber + ".");
                        game1GuessBtn.setDisable(true);
                        introduction_game1_Lbl.setText("YOU WON! Congratulation!!!");

                        //if won in game_1 earlier
                        if(stars==4){
                            //do nothing...
                        }
                        //if didn't won earlier
                        else{
                            stars = 4;
                            Main.setStarNum(Main.getStarNum() + 4);

                            Main.prizeTab.setDisable(false);
                        }


                    }

                    introduction_game1_Lbl.setText("Your choice was: " + enterNumber_game1_TxtF1.getText());

                    //System.out.println(randomNumber());
                    //System.out.println("Guess button pressed " + counter + " times!");
                }
            } else if (counter == 5) {
                resultLbl.setText("You LOSE, no more chance to guess. Please press RESET for a new game!");
                game1GuessBtn.setDisable(true);
                counterLbl.setText("You tried 6 times, so unfortunately you can not do it again!");
            } else {
                //counter less then 5 or 6
            }
        }

        //RESET BUTTON ACTION
        if (event.getSource() == game1ResetBtn) {
            counter = 0;

            if(stars==4){
                Main.setStarNum(Main.getStarNum()-stars);
                stars = 0;

                //win minimum 4 star with game 2
                if(Main.getStarNum()>3){
                    //do nothing
                }else{
                    //disable prize tab
                    Main.prizeTab.setDisable(true);
                }

            }else{ }

            introduction_game1_Lbl.setText("Please enter a number between 1 and 100!");
            enterNumber_game1_TxtF1.setText("");
            enterNumber_game1_TxtF1.setPromptText("E.g. 1,45,57,2,100");
            counterLbl.setText("You tried " + counter + " times from the maximum 6!");
            randomNumber = randomNumber();
            game1GuessBtn.setDisable(false);
            resultLbl.setText("");
            //System.out.println("Reset & Random = " + randomNumber);
        }

        //QUIT BUTTON ACTION
        if (event.getSource() == game1QuitBtn) {
            Platform.exit();

        }
    }

    private boolean checkInput(String str) {
        //not a valid length
        if (str.length() > 3 || str.length() < 1) {
            resultLbl.setText("Sorry, but enter numbers between 1 and 100! Try again!");
            enterNumber_game1_TxtF1.setText("");
            enterNumber_game1_TxtF1.setPromptText("E.g. 1,45,57,2,100");
            return false;
        } else {
            try {
                //parse the input text
                String textFieldText = enterNumber_game1_TxtF1.getText();
                int parsedInt = Integer.parseInt(textFieldText);

                //validate the parsed int for range (1-100) --> validateInputInteger(int)
                //true validation
                if (validateInputInteger(parsedInt)) {

                    //all validation is fine, its a number and it is in the range, so choosedNumber will equal with this
                    choosedNumber = parsedInt;

                    //incrase the counter
                    counter = counter + 1;
                    System.out.println("counter in validation" + counter);

                    return true;
                }
                //false validation
                else {
                    return false;
                }
                //some error on parsing give exception --> error message and etc..
            } catch (Exception e) {
                resultLbl.setText("Sorry, but enter numbers only between 1 and 100! Try again!");
                enterNumber_game1_TxtF1.setText("");
                enterNumber_game1_TxtF1.setPromptText("E.g. 1, 45, 57, 2, 100");
                return false;
            }
        }
    }

    //validation for range of number (1-100)
    private boolean validateInputInteger(int inputInt) {

        //too big or small number
        if (inputInt > 100 || inputInt < 1) {
            resultLbl.setText("Sorry, but enter numbers between 1 and 100! Try again!");
            enterNumber_game1_TxtF1.setText("");
            enterNumber_game1_TxtF1.setPromptText("E.g. 1,45,57,2,100");
            return false;
        }
        //number is between 1 and 100
        else {
            return true;
        }
    }


}