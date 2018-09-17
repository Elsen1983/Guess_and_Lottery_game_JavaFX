package main.java.mainpack;


  /////////////////////
 //  I.M.P.O.R.T.S  //
/////////////////////
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import treePackage.Tree;
import java.io.*;
import java.util.Optional;
import java.util.Scanner;

//The third tab has a button which is used to set the prize selection using an observable list (or other) of items populated
//from a text file the user can select one of the options this value is used as a key to a hash map which
//stores a prize value...(It can be text or value)
//The strings can be anything apple banana pear, these are used to lookup the Map to get the appropriate prize.
//File contents
//
//E.g.        Prize                                  4/5/6          string name
//            1000                                   4              Apple
//            10000                                  5              Grape
//            Trip to Donegal                        4              Pear
//            Trip to Caribbean                      4              Banana
//
//List for a person who won a 4 prize. Users only see the strings to choose from. E.g. apple, pear Banana.
//The prize is retrieved from the Map. Use a pop-up to announce the prize.
//MAP      --------------------------
//            Apple           1000
//            Pear            trip to Donegal
//            Banana          trip to Caribbean
//
//The user is only allowed use the third tab if he/she has won a game in either tab 1 or two.


/*
Lab 2
Part A
- Create a class library.
- Add a few useful classes to a jar file.
 - This jar is to be used in another project.
- You can inherit from these or just instantiate and use them in the project (Import and export).
- Use your own classes to create the jar file or use one of the classes attached e.g. to replace the HashMap (from lab 1) with your own replacement structure.
- You could use a class called Prize with two attributes Key (String) and prize (String).
- Create a structure of these from the imported jar.
- The ordering in the structure will be given by the key.
 - (If you want you can think of a better use or a different use for the classes to create and use your own stacks queues …etc).  It’s the creation and use of the jar file that I want.

*/

public class Tab3_Prize extends Tab {

      //////////////////////////
     //  V.A.R.I.A.B.L.E.S.  //
    //////////////////////////

    private static GridPane gridPane = new GridPane();
    //file name
    private String fileName = "src\\main\\java\\rewards.txt";
    //Reward Object array --> all rewards stored in it
    private Reward[] rewardArray = new Reward[8];
    //Prize
    private Prize prize = new Prize();
    //Tree - mine
    private Tree tree = new Tree();
    //reward
    private String rewardWinner="";
    private Winners winners = new Winners();
    private HBox rewards_Hbox = new HBox();
    private FileInputStream pic1 = null;
    private FileInputStream pic2 = null;
    private Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

      ///////////////////////////////////////
     //  G.E.T.T.E.R.S. + S.E.T.T.E.R.S.  //
    ///////////////////////////////////////

    public static GridPane getGridPane() {
        return gridPane;
    }

    public static void setGridPane(GridPane gridPane) {
        Tab3_Prize.gridPane = gridPane;
    }

      ////////////////////////////////
     //  C.O.N.S.T.R.U.C.T.O.R.S.  //
    ////////////////////////////////
    public Tab3_Prize() {

        this.setDisable(true);
        this.setText("Prize");

        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10, 25, 25, 25));
        gridPane.setStyle("-fx-background-color: rgb(103,241,89)");


        //Reward objects for write file
        //easy way to add new rewards -> e.g. possible to create them in a method by scanner
        Reward r1 = new Reward("1000",              "4",    "Apple");
        Reward r2 = new Reward("10000",             "5",    "Grape");
        Reward r3 = new Reward("Trip to Donegal",   "4",    "Pear");
        Reward r4 = new Reward("Trip to Dublin", "4",    "Banana");
        //it is overwrite the other apple in the tree
        Reward r5 = new Reward("2000",              "4",    "Apple");
        Reward r6 = new Reward("15000",             "4",    "Orange");
        Reward r7 = new Reward("Trip to Cork",   "4",    "Carrot");
        Reward r8 = new Reward("Trip to Caribbean", "5",    "Banana");
        //store reward objects in an array
        rewardArray[0] = r1;
        rewardArray[1] = r2;
        rewardArray[2] = r3;
        rewardArray[3] = r4;
        rewardArray[4] = r5;
        rewardArray[5] = r6;
        rewardArray[6] = r7;
        rewardArray[7] = r8;

        //write the prizes into a file
            writeToFile(fileName, getAllReward());

        //LABELS
        Label topMessage_Lbl = new Label("Congratulation, you WIN minimum one game, now play once more!\n");
        topMessage_Lbl.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 15));
        Label secMessage_Lbl = new Label("Select one from the buttons below, and see your real reward!\n");
        secMessage_Lbl.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 15));
        //hide it first
        secMessage_Lbl.setVisible(false);

        //BUTTONS
        Button callTree_Btn = new Button("Check possible rewards!");
        callTree_Btn.setOnAction(e -> {

            readFile(fileName);
            //System.out.println("Stars: " + Main.getStarNum());

            //add the choose-reward buttons (eg apple, pear) to the pane
            createButtonsFromTree();

            //disable this button, so cant call these methods again --> no duplications on pane
            callTree_Btn.setDisable(true);
            secMessage_Lbl.setVisible(true);
            Main.guessGameTab.setDisable(true);
            Main.lottoGameTab.setDisable(true);
        });

        gridPane.add(topMessage_Lbl, 0, 0);
        gridPane.add(callTree_Btn, 0, 1);
        gridPane.add(secMessage_Lbl, 0,2);
        gridPane.add(rewards_Hbox, 0,3);

        //add gridpane to this tab main content
        this.setContent(gridPane);
    }


      //////////////////////
     //  M.E.T.H.O.D.S.  //
    //////////////////////


    //store all object from the array as one string
    private String getAllReward(){

        //http://www.java2s.com/Tutorials/Java/Java_Data_Type/0280__Java_StringBuilder_StringBuffer.htm
        StringBuilder report= new StringBuilder();

        for (Reward aRewardArray : rewardArray) {
            report.append(aRewardArray.toString());
            report.append("\n");
        }

        return report.toString();
    }


    //write the file with prizes
    private  void writeToFile(String filename, String report) {

        FileWriter fw;
        try {
            fw = new FileWriter(filename);

            fw.write(report);
            fw.flush();
            fw.close();
        }
        //message and stop if file not found
        catch (FileNotFoundException fnf){
            System.out.println(filename + " not found ");
            System.exit(0);
        }
        //stack trace here because we don't expect to come here
        catch (IOException ioe){
            ioe.printStackTrace();
            System.exit(1);
        }
    }




    //https://coderanch.com/t/568374/java/reading-text-file-hashmap
    //https://stackoverflow.com/questions/29061782/java-read-txt-file-to-hashmap-split-by
    private void readFile(String filename) {

        //if the stars number was bigger than 5 then change it to 5, because we have rewardsfor maximum 5 star (best reward)
        if (Main.getStarNum() > 5) {
            Main.setStarNum(5);
        }

        try {
            File f = new File(filename);
            Scanner scanner = new Scanner(f);
            while (scanner.hasNextLine()) {
                //read first line and process it
                String inputLine = scanner.nextLine();
                if (inputLine.length() != 0) {
                    //ignored if blank line
                    checkLinesAndCreateTree(inputLine);
                }
            }

        }
        //if the file is not found, stop with system exit
        catch (FileNotFoundException fnf){
            System.out.println( filename + " not found ");
            System.exit(0);
        }
    }

    private void checkLinesAndCreateTree(String inputLine) {

        try {
            String parts [] = inputLine.split(",");
            String reward = parts[0];
            String star = parts[1];
            String key = parts[2];

            try{
                int stars = Integer.parseInt(star);

                if(stars == Main.getStarNum()){

                    prize.setKey(key);
                    prize.setPrize(reward);

                   // System.out.println("Prize toString: " + prize.toString());

                    tree.put(prize.getKey(), prize.getPrize());

                }else {
                    //System.out.println("Something went wrong in checkLineAndCreateMap method in Tab3");
                }

            }catch (NumberFormatException nfe){
                System.out.println("Problem with parsing!");
            }
        }
        //got this part from Persistance.pdf example
        catch (ArrayIndexOutOfBoundsException air) {
            String error = "Not enough items in  : '" + inputLine
                    + "' index position : " + air.getMessage();
            System.out.println(error);
        }

    }

    private void createButtonsFromTree(){

        // !!!! ordering first !!!!
        tree.inorderTraversal();

        String str;
        str = tree.getOrderedStr();

        String traversedArray [] = str.split(" ");
        //i starts from 1, because 0 is null
        for(int i=1; i< traversedArray.length; i++){

            String btnKey = traversedArray[i];

            Button button = new Button(btnKey);

            button.setOnAction(e -> {
                String btnValue;
                try{

                    btnValue= String.valueOf(tree.get(btnKey));
                    rewardWinner = btnValue;
                    try {
                        String path1 = "src\\main\\java\\eurobag.png";
                        String path2 = "src\\main\\java\\trip.png";

                        pic1 = new FileInputStream(path1);
                        pic2 = new FileInputStream(path2);

                    } catch (FileNotFoundException fnf) {
                        fnf.printStackTrace();
                        System.out.println("Icon-image is not exist!");
                    }

                    // Set the icon (must be included in the project).
                    Image money_Img = new Image(pic1);
                    Image trip_Img = new Image(pic2);
                    ImageView imageView1 = new ImageView(money_Img);
                    ImageView imageView2 = new ImageView(trip_Img);
                    imageView1.setFitWidth(100);
                    imageView1.setFitHeight(100);
                    imageView2.setFitWidth(100);
                    imageView2.setFitHeight(100);

                    try {
                        //if parsing is possible then image will money-bag icon
                        int parsed = Integer.parseInt(btnValue);
                        alert.setContentText("You won " + parsed + " euro!!");
                        alert.setGraphic(imageView1);
                        serializeCurrentWinner();

                    }catch (Exception parse){
                        //if parsing impossible then image will be a trip icon
                        alert.setContentText("You won a " + btnValue + "!!!");
                        alert.setGraphic(imageView2);
                        serializeCurrentWinner();
                    }

                }catch (Exception npe){
                    System.out.println(tree.get(btnKey));
                }




                createPopUpBox();

            });

            button.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 15));
            //add buttons to HBox
            rewards_Hbox.getChildren().addAll(button);
            rewards_Hbox.setSpacing(10);
        }
    }

    private void serializeCurrentWinner() {

        /// Serialization
        winners.setfName(StartAlertBox.getUserFName());
        winners.setlName(StartAlertBox.getUserLName());
        winners.setReward(rewardWinner);
        //add these details into a winners arraylist
        winners.addToWinnersList(winners.getfName(), winners.getlName(), winners.getReward());

        try {
            FileOutputStream fileOut = new FileOutputStream("src\\main\\java\\lastWinner.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(winners);
            out.close();
            fileOut.close();

            System.out.println("Serialized data is saved in src/main/java/lastWinner.ser");

            } catch (IOException i) {
                i.printStackTrace();

                System.out.println("Something went wrong in serializeCurrentWinner method in Tab3_Prize class!");
            }
        //////

        //add these details into a winners arraylist
        //winners.addToWinnersList(winners.getfName(), winners.getlName(), winners.getReward());

        //System.out.println("ArrayList size: " + winners.getWinnerList().size());

       // String str = Tab0_Winners.getStr();

        //for(int i = 0; i< Winners.getWinnerList().size(); i++){

           // str = str + "\n" + winners.getWinnerList();

        //}

       // Tab0_Winners.setStr(str);

        System.out.println(winners.getWinnerList());
    }



    private void createPopUpBox(){

    //create the pop-up box
    //http://code.makery.ch/blog/javafx-dialogs-official/
    //https://examples.javacodegeeks.com/desktop-java/javafx/dialog-javafx/javafx-dialog-example/
    alert.setTitle("Congratulation!!");
    alert.setHeaderText(null);
    alert.getDialogPane().setStyle("-fx-font-family: Times New Roman");
    alert.getDialogPane().setStyle("-fx-font-size: 18px");

    //
    ButtonType reset_Btn = new ButtonType("New Game");
    ButtonType close_Btn = new ButtonType("Close", ButtonBar.ButtonData.CANCEL_CLOSE);
    alert.getButtonTypes().setAll(reset_Btn, close_Btn);

    //for confirmation box button's actions
    Optional<ButtonType> result = alert.showAndWait();
    if (result.get() == reset_Btn){
        // ... user chose reset games
        System.out.println("new game applied");
        Main.resetTabs();

    } else if(result.get() == close_Btn){
        // ... user chose close
        Platform.exit();
    }

}
}
