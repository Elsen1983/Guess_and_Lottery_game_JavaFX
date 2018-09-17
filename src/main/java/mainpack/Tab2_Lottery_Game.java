package main.java.mainpack;


  /////////////////////
 //  I.M.P.O.R.T.S  //
/////////////////////
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// The second tab simulates the national lottery user can guess numbers
//  and they are compared with a set of pre-generated random numbers.
// The user gets a prize for getting 4 or more numbers correct.
// A 4 * prize for 4 numbers, 5* for 5 numbers, etc..
// The user is allowed any number of attempts at guessing the lottery numbers .
// This tab is called lotto cure.

public class Tab2_Lottery_Game extends Tab {



    //////////////////////////
     //  V.A.R.I.A.B.L.E.S.  //
    //////////////////////////
    private static GridPane mainPane = new GridPane();

    private Label errorMessage_Lbl = new Label("");

    // arrays
    private int [] generatedNumbersArray = randNumArray();
    private int [] lottoNumbersArray = new int[5];
    private int [] checkNumbersArray = new int [5];
    private List<Integer> comparedList = new ArrayList<>();
    private int numOfHits = 0;

    //stars
    private int stars = 0;

      ///////////////////////////////////////
     //  G.E.T.T.E.R.S. + S.E.T.T.E.R.S.  //
    ///////////////////////////////////////
      public static GridPane getMainPane() {
          return mainPane;
      }

    public static void setMainPane(GridPane mainPane) {
        Tab2_Lottery_Game.mainPane = mainPane;
    }



      ////////////////////////////////
     //  C.O.N.S.T.R.U.C.T.O.R.S.  //
    ////////////////////////////////

    //constructor of class
    public Tab2_Lottery_Game() {

        Tab2_Lottery_Game.this.setDisable(false);
        Tab2_Lottery_Game.this.setText("Game2");


        mainPane.setAlignment(Pos.CENTER);
        mainPane.setHgap(10);
        mainPane.setVgap(10);
        mainPane.setPadding(new Insets(10, 25, 25, 25));
        mainPane.setStyle("-fx-background-color: rgb(241,241, 241)");

        //Line0
        HBox line0_Hbox = new HBox();
        line0_Hbox.setSpacing(10);
        //Line1
        HBox line1_Hbox = new HBox();
        line1_Hbox.setSpacing(10);
        //Line2
        HBox line2_Hbox = new HBox();
        line2_Hbox.setSpacing(10);
        //Line3
        HBox line3_Hbox = new HBox();
        line3_Hbox.setSpacing(10);
        //Line4
        HBox line4_Hbox = new HBox();
        line4_Hbox.setSpacing(10);
        //Line5
        HBox line5_Hbox = new HBox();
        line5_Hbox.setSpacing(10);
        //Line6
        HBox line6_Hbox = new HBox();
        line6_Hbox.setSpacing(10);

        Label lottoNumbers_Lbl = new Label("");
        lottoNumbers_Lbl.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 15));
        Label yourHits_Lbl = new Label("");
        yourHits_Lbl.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 15));

        //Line2 - Line4 in a VBox
        VBox buttonsAndLinesElements_Vbox = new VBox();


        //shadow effect
        DropShadow shadow = new DropShadow();
        shadow.setOffsetY(1.0);


        //lotto cure top label
        Label lottoCure_Lbl = new Label("--- LOTTO CURE ---");
        lottoCure_Lbl.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 18));
        lottoCure_Lbl.setMinHeight(30);

        //add lottocure to LINE0
        line0_Hbox.setAlignment(Pos.BASELINE_LEFT);
        line0_Hbox.getChildren().addAll(lottoCure_Lbl);

        //rule label
        Label rule_Lbl = new Label("  GAME RULE:   Choose five different number between 1 and 50! Good luck!");
        rule_Lbl.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 12));

        //add rule label to LINE1
        line1_Hbox.setAlignment(Pos.BASELINE_LEFT);
        line1_Hbox.getChildren().addAll(rule_Lbl);

        //TEXTFIELDS
        TextField num1_TxtField = new TextField();
        num1_TxtField.setMaxWidth(50);
        num1_TxtField.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 15));
        num1_TxtField.setEffect(shadow);

        TextField num2_TxtField = new TextField();
        num2_TxtField.setMaxWidth(50);
        num2_TxtField.setEffect(shadow);
        num2_TxtField.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 15));

        TextField num3_TxtField = new TextField();
        num3_TxtField.setMaxWidth(50);
        num3_TxtField.setEffect(shadow);
        num3_TxtField.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 15));

        TextField num4_TxtField = new TextField();
        num4_TxtField.setMaxWidth(50);
        num4_TxtField.setEffect(shadow);
        num4_TxtField.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 15));

        TextField num5_TxtField = new TextField();
        num5_TxtField.setMaxWidth(50);
        num5_TxtField.setEffect(shadow);
        num5_TxtField.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 15));
        //------------------------------------------------

        //numbers label
        Label addNumbers_Lbl = new Label("   NUMBERS  :");
        addNumbers_Lbl.setMaxWidth(80);
        addNumbers_Lbl.setMinWidth(80);
        addNumbers_Lbl.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 12));

        //add 'numbers' label + textfields to second line (LINE2)
        line2_Hbox.setAlignment(Pos.BASELINE_LEFT);
        line2_Hbox.getChildren().addAll(addNumbers_Lbl, num1_TxtField, num2_TxtField, num3_TxtField, num4_TxtField, num5_TxtField);


        //create images for validation
        //FileInputStreams
        FileInputStream pic1 = null;

        FileInputStream pic2 = null;
        FileInputStream pic3 = null;
        try {

            String path1 = "src\\main\\java\\nothing.png";
            String path2 = "src\\main\\java\\good.png";
            String path3 = "src\\main\\java\\wrong.png";

            pic1 = new FileInputStream(path1);
            pic2 = new FileInputStream(path2);
            pic3 = new FileInputStream(path3);
        } catch (FileNotFoundException e) {
            System.out.println("WTF");
            e.printStackTrace();
        }

        //Images
        Image nothing_Img = new Image(pic1);
        Image good_Img = new Image(pic2);
        Image wrong_Img = new Image(pic3);

        //ImageViews
        ImageView imageView1 = new ImageView(nothing_Img);
        imageView1.setFitWidth(50);
        imageView1.setFitHeight(35);
        ImageView imageView2 = new ImageView(nothing_Img);
        imageView2.setFitWidth(50);
        imageView2.setFitHeight(35);
        ImageView imageView3 = new ImageView(nothing_Img);
        imageView3.setFitWidth(50);
        imageView3.setFitHeight(35);
        ImageView imageView4 = new ImageView(nothing_Img);
        imageView4.setFitWidth(50);
        imageView4.setFitHeight(35);
        ImageView imageView5 = new ImageView(nothing_Img);
        imageView5.setFitWidth(50);
        imageView5.setFitHeight(35);



        //---- BUTTONS ----------------------------------
        Button check_Btn = new Button("CHECK");
        Button clear_Btn = new Button("CLEAR");
        Button start_Btn = new Button("START");

        // CHECK BUTTON
        check_Btn.setMinWidth(80);
        check_Btn.setMaxHeight(35);
        check_Btn.setPadding(new Insets(5,10,5,10));
        check_Btn.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 12));
        check_Btn.setTooltip(new Tooltip("It will check the validity of your numbers!"));
        // Button ACTION
        check_Btn.setOnAction(e -> {

            //if all input validation is true
            if(checkInputValidity(num1_TxtField,imageView1, good_Img,wrong_Img) &&
              checkInputValidity(num2_TxtField,imageView2, good_Img,wrong_Img) &&
              checkInputValidity(num3_TxtField,imageView3, good_Img,wrong_Img) &&
              checkInputValidity(num4_TxtField,imageView4, good_Img,wrong_Img) &&
              checkInputValidity(num5_TxtField,imageView5, good_Img,wrong_Img))
            {
                //create a new empty array to hold numbers from input
                checkNumbersArray = new int[5];

                //no need try and catch for pharsing because the inputs were validate earlier
                int num1 = Integer.parseInt(num1_TxtField.getText());
                int num2 = Integer.parseInt(num2_TxtField.getText());
                int num3 = Integer.parseInt(num3_TxtField.getText());
                int num4 = Integer.parseInt(num4_TxtField.getText());
                int num5 = Integer.parseInt(num5_TxtField.getText());

                //check for dublication
                    //NUM1
                    //System.out.println("Num1 is unique. - " + num1);
                    checkNumbersArray[0] =num1;
                    imageView1.setImage(good_Img);

                        //NUM2
                        if(checkDuplication(num2, checkNumbersArray)){
                            //System.out.println("Num2 is unique. - " + num2);
                            checkNumbersArray[1] =num2;
                            imageView2.setImage(good_Img);

                            //NUM3
                            if(checkDuplication(num3, checkNumbersArray)){
                                //System.out.println("Num3 is unique. - " + num3);
                                checkNumbersArray[2] =num3;
                                imageView3.setImage(good_Img);

                                //NUM4
                                if(checkDuplication(num4, checkNumbersArray)){
                                    //System.out.println("Num4 is unique. - " + num4);
                                    checkNumbersArray[3] =num4;
                                    imageView4.setImage(good_Img);

                                    //NUM5
                                    if(checkDuplication(num5, checkNumbersArray)){
                                        //System.out.println("Num5 is unique. - " + num5);
                                        checkNumbersArray[4] =num5;
                                        imageView5.setImage(good_Img);

                                        //all is unique, so change start button to avilable

                                        if(numOfHits==0) {
                                            start_Btn.setDisable(false);
                                            start_Btn.setStyle("-fx-border-color: green");
                                            start_Btn.setFocusTraversable(true);
                                        }
                                        else{
                                            start_Btn.setDisable(true);
                                            errorMessage_Lbl.setText("Please change your choices before check again!");
                                        }


                                    }else{
                                        //num5 is repeated
                                        imageView5.setImage(wrong_Img);
                                        start_Btn.setStyle("-fx-border-color: none");
                                        errorMessage_Lbl.setText("Fifth number ("+ num5 +") was used before!");
                                    }

                                }else{
                                    //num4 is repeated
                                    imageView4.setImage(wrong_Img);
                                    start_Btn.setStyle("-fx-border-color: none");
                                    errorMessage_Lbl.setText("Fourth number ("+ num4 +") was used before!");
                                }

                            }else{
                                //num3 is repeated
                                imageView3.setImage(wrong_Img);
                                start_Btn.setStyle("-fx-border-color: none");
                                errorMessage_Lbl.setText("Third number ("+ num3 +") was used before!");
                            }

                        }else{
                            //num2 is repeated
                            imageView2.setImage(wrong_Img);
                            start_Btn.setStyle("-fx-border-color: none");
                            errorMessage_Lbl.setText("Second number ("+ num2 +") was used before!");
                        }


                System.out.println("Input numbers array : " + Arrays.toString(checkNumbersArray));
            }
            else{
                start_Btn.setStyle("-fx-border-color: none");
                start_Btn.setDisable(true);
            }
        }
        );

        // CLEAR BUTTON
        clear_Btn.setMinWidth(80);
        clear_Btn.setMinHeight(35);
        clear_Btn.setPadding(new Insets(5,10,5,10));
        clear_Btn.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 12));
        // Button ACTION
        clear_Btn.setOnAction(e -> {

                    //clear the last win result
                    if(stars>0){
                        //reduce the main stars counter by the last game stars
                        Main.setStarNum(Main.getStarNum() - stars);
                        stars=0;
                    }

                    //clear everything to default
                    start_Btn.setDisable(true);
                    start_Btn.setStyle("-fx-border-color: none");

                    num1_TxtField.setText("");
                    num1_TxtField.setStyle("-fx-border-color: none");
                    num1_TxtField.setDisable(false);
                    num2_TxtField.setText("");
                    num2_TxtField.setStyle("-fx-border-color: none");
                    num2_TxtField.setDisable(false);
                    num3_TxtField.setText("");
                    num3_TxtField.setStyle("-fx-border-color: none");
                    num3_TxtField.setDisable(false);
                    num4_TxtField.setText("");
                    num4_TxtField.setStyle("-fx-border-color: none");
                    num4_TxtField.setDisable(false);
                    num5_TxtField.setText("");
                    num5_TxtField.setStyle("-fx-border-color: none");
                    num5_TxtField.setDisable(false);

                    check_Btn.setDisable(false);

                    errorMessage_Lbl.setText("");

                    imageView1.setImage(nothing_Img);
                    imageView2.setImage(nothing_Img);
                    imageView3.setImage(nothing_Img);
                    imageView4.setImage(nothing_Img);
                    imageView5.setImage(nothing_Img);

                    lottoNumbers_Lbl.setText("");
                    yourHits_Lbl.setText("");
                    lottoNumbers_Lbl.setVisible(false);
                    yourHits_Lbl.setVisible(false);

                    comparedList.clear();
                    numOfHits = 0;
        }
        );

        // START BUTTON
        start_Btn.setMinWidth(80);
        start_Btn.setMinHeight(35);
        start_Btn.setPadding(new Insets(5,10,5,10));
        start_Btn.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 12));
        start_Btn.setDisable(true);
        // Start Button ACTION
        start_Btn.setOnAction(e -> {

            numOfHits =0;
            lottoNumbersArray = Arrays.copyOf(generatedNumbersArray, 5);
            System.out.println("Lotto numbers: " + Arrays.toString(lottoNumbersArray));
            Arrays.sort(checkNumbersArray);
            System.out.println("Your numbers: " + Arrays.toString(checkNumbersArray));

            compareArrays(checkNumbersArray, lottoNumbersArray);

            lottoNumbers_Lbl.setText("Lotto numbers: " + Arrays.toString(lottoNumbersArray));
            yourHits_Lbl.setText("Your hits: " + comparedList);
            lottoNumbers_Lbl.setVisible(true);
            yourHits_Lbl.setVisible(true);

            //call the prize method
            givePrize(numOfHits);
            System.out.println(numOfHits);

            //disable button
            start_Btn.setDisable(true);
            num1_TxtField.setDisable(true);
            num2_TxtField.setDisable(true);
            num3_TxtField.setDisable(true);
            num4_TxtField.setDisable(true);
            num5_TxtField.setDisable(true);

            check_Btn.setDisable(true);

        });
        //------------------------------------------------


        //add button and imageviews to LINE3
        line3_Hbox.getChildren().addAll(check_Btn, imageView1, imageView2,imageView3,imageView4,imageView5);

        //clear label
        Label clearMessage_Lbl = new Label("- If you press 'CLEAR' then all number will be deleted. -");
        clearMessage_Lbl.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 12));

        //add button and label to LINE4
        line4_Hbox.setAlignment(Pos.BASELINE_LEFT);
        line4_Hbox.getChildren().addAll(clear_Btn,clearMessage_Lbl);

        //error message label
        errorMessage_Lbl.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 17));
        errorMessage_Lbl.setTextFill(Color.RED);

        //add start button and error label to LINE5
        line5_Hbox.getChildren().addAll(start_Btn, errorMessage_Lbl);
        line5_Hbox.setAlignment(Pos.BASELINE_LEFT);


        lottoNumbers_Lbl.setVisible(false);
        yourHits_Lbl.setVisible(false);
        yourHits_Lbl.setAlignment(Pos.BASELINE_RIGHT);
        line6_Hbox.getChildren().addAll(lottoNumbers_Lbl, yourHits_Lbl);
        line6_Hbox.setAlignment(Pos.BASELINE_LEFT);


        //add hboxes into a vbox
        buttonsAndLinesElements_Vbox.setSpacing(10);
        buttonsAndLinesElements_Vbox.getChildren().addAll(line3_Hbox,line4_Hbox,line5_Hbox,line6_Hbox);

        //add elements to main pane
        mainPane.add(line0_Hbox, 0, 0);
        mainPane.add(line1_Hbox, 0, 1);
        mainPane.add(line2_Hbox,0, 2);
        mainPane.add(buttonsAndLinesElements_Vbox, 0,3);

        //add content to main
        Tab2_Lottery_Game.this.setContent(mainPane);

    }

      //////////////////////
     //  M.E.T.H.O.D.S.  //
    //////////////////////

    //randomArray generator
    private int[] randNumArray() {
    generatedNumbersArray = new int [5];

        //goint throughout the array  'createNum.length' times
        for(int i=0; i < generatedNumbersArray.length; i++){

            //generate a new random number always when reach this point
            int randNumber = (int) (Math.random() * 50 + 1);

            //check the duplication in the array what is generating in the for loop
            //if checkDuplication gives back a true
            if(checkDuplication(randNumber, generatedNumbersArray)) {

               generatedNumbersArray[i] = randNumber;

           }
           ////if checkDuplication gives back a false
           else{
                //System.out.println("Number " + randNumber + " is already in the array!");
                //recursive call on same method
                generatedNumbersArray = randNumArray();
                break;
            }
        }
        //sort the array before store it
        Arrays.sort(generatedNumbersArray);

        System.out.println(Arrays.toString(generatedNumbersArray));

        //return the array with unique numbers
        return generatedNumbersArray;
    }

    //check the duplicated numbers in the 'half-created' array until itt will populate with unique numbers
    private boolean checkDuplication(int num, int [] arr){

        boolean b = false;
            //go throughout the whole array and check all element equality with the randomly generated number
            for(int i =0; i < arr.length-1; i++){
                //if element from position = with random number then return false
                if(arr[i] == num){
                    //System.out.println(arr[i] + " is in the array, so don't add " + num + " into the array!");
                    b=false;
                    //jumps out from the for-loop and change return boolean to false
                    break;
                }else{
                    b=true;
                    //break;
                }
            }
        return b;
    }

    //check the inputs are valid numbers
    private boolean checkInputValidity (TextField textField, ImageView imageView, Image good, Image wrong){

        boolean b;
        try{
            String str = textField.getText();
            int parsedInt =  Integer.parseInt(str);
                if(parsedInt > 0 && parsedInt < 51){
                    imageView.setImage(good);
                    textField.setStyle("-fx-border-color: none");
                    errorMessage_Lbl.setText("");
                    b=true;
                }else{
                    imageView.setImage(wrong);
                    //System.out.println("It is not a number in the range 1 to 50!");
                    errorMessage_Lbl.setText("It is not a number in the range 1 to 50!");
                    textField.setStyle("-fx-border-color: red");
                    b=false;
                }

        }catch (NumberFormatException nfee){
            imageView.setImage(wrong);
            //System.out.println("It is not a number!!");
            errorMessage_Lbl.setText("It is not a number!!");
            textField.setStyle("-fx-border-color: red");
            b=false;
        }

        return b;

    }

    //compare two arrays and gives back the same elements
    private void compareArrays(int[] arr1, int[] arr2){

        for (int anArr1 : arr1) {

            for (int anArr2 : arr2) {

                if (anArr1 == anArr2) {
                    //check the list contains the common element or not
                    //if yes, then...
                    if (!comparedList.contains(anArr1)) {
                        System.out.println("Found number: " + anArr1);
                        //add the common element into the list
                        comparedList.add(anArr1);
                        //incrase the counter
                        numOfHits = numOfHits + 1;

                        //System.out.println(comparedList);
                    }
                }
            }
        }


    }

    //check prize for hits
    private void  givePrize(int num){

        switch(num){

            case 4 :
                System.out.println("You got four hit, so give you 4 ✵ !");
                stars = 4;
                Main.setStarNum(Main.getStarNum()+4);
                Main.prizeTab.setDisable(false);
                //Tab3_Prize.gridPane.setDisable(false);
                break;

            case 5 :
                System.out.println("You got five hit, so give you 5 ✵ !");
                stars = 5;
                Main.setStarNum(Main.getStarNum()+5);
                Main.prizeTab.setDisable(false);
                //Tab3_Prize.gridPane.setDisable(false);
                break;

                default:
                    stars =0;
                    System.out.println("Sorry, you didn't got enough hit, so can't give you any ✵ now");
        }

    }

}

