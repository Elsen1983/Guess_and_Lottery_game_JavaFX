package main.java.mainpack;

  /////////////////////
 //  I.M.P.O.R.T.S  //
/////////////////////
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.*;
import java.util.*;


public class Tab0_Winners extends Tab {

      //////////////////////////
     //  V.A.R.I.A.B.L.E.S.  //
    //////////////////////////

    private static BorderPane bPane = new BorderPane();
    private TextArea txtArea = new TextArea();
    private String leaderBoardFile = "src\\main\\java\\listOfWinners.txt";
    private String str = "Name ---------   Prize -------\n";
    private ArrayList loadedArrayList = new ArrayList();
    private String orderTypeSelected = "time";

    ///////////////////////////////////////
     //  G.E.T.T.E.R.S. + S.E.T.T.E.R.S.  //
    ///////////////////////////////////////

    public static BorderPane getbPane() {
        return bPane;
    }

    public static void setbPane(BorderPane bPane) {
        Tab0_Winners.bPane = bPane;
    }

    private String getStr() {
        return str;
    }

    private void setStr(String str) {
        this.str = getStr() + str + "\n";
    }

    public ArrayList getLoadedArrayList() {
        return loadedArrayList;
    }

    public void setLoadedArrayList(ArrayList loadedArrayList) {
        this.loadedArrayList = loadedArrayList;
    }

    public String getOrderTypeSelected() {
        return orderTypeSelected;
    }

    public void setOrderTypeSelected(String orderTypeSelected) {
        this.orderTypeSelected = orderTypeSelected;
    }

      ////////////////////////////////
     //  C.O.N.S.T.R.U.C.T.O.R.S.  //
    ////////////////////////////////

    public Tab0_Winners() {

        Tab0_Winners.this.setDisable(false);
        Tab0_Winners.this.setText("Winners");

        bPane.setStyle("-fx-background-color: #aaddeb;");
        VBox vBox = new VBox();
        HBox hBoxOne = new HBox();
        HBox hBoxTwo = new HBox();
        HBox hBoxThree = new HBox();
        HBox hBoxFour = new HBox();
        HBox hBoxFive = new HBox();

        Label lblOne = new Label();
        lblOne.setText("LIST OF WINNERS");
        Label lblTwo = new Label();
        lblTwo.setText("Originally ordered by record's time. Change it by: ");
        Label removeLbl = new Label("Enter winner name and remove him/her from the list!");

        Button orderRecordTime = new Button ("Time");
        Button orderName = new Button("Name");
        Button orderPrize = new Button("Prize");
        Button removeBtn = new Button ("Remove");


        txtArea.setEditable(false);

        TextField removeWinner = new TextField();

        readFile(leaderBoardFile);

        txtArea.setText(getStr());

        txtArea.setStyle("-fx-text-alignment: center;");


        orderRecordTime.setOnAction(e -> {
            orderByTime(txtArea);
        });

        orderName.setOnAction(e -> {
            orderByName(txtArea);
        });

        orderPrize.setOnAction(e -> {
            orderByPrize(txtArea);
        });

        removeBtn.setOnAction(e -> {
            removeFromList(removeWinner);
        });



        hBoxOne.getChildren().addAll(lblOne);
        hBoxOne.setAlignment(Pos.CENTER);

        hBoxTwo.getChildren().addAll(lblTwo);

        hBoxThree.setSpacing(15);
        hBoxThree.getChildren().addAll(orderRecordTime, orderName, orderPrize);
        hBoxThree.setAlignment(Pos.CENTER_LEFT);
        hBoxThree.setSpacing(15);

        hBoxFour.getChildren().add(removeLbl);

        hBoxFive.getChildren().addAll(removeWinner, removeBtn);
        hBoxFive.setSpacing(10);


        vBox.getChildren().addAll(hBoxOne,hBoxTwo,hBoxThree,txtArea, hBoxFour, hBoxFive);
        vBox.setSpacing(10);

        bPane.setCenter(vBox);
        bPane.setPadding(new Insets(20,50,10,50));

        Tab0_Winners.this.setContent(bPane);


    }




    //////////////////////
     //  M.E.T.H.O.D.S.  //
    //////////////////////

    private void orderByTime(TextArea txtArea) {
        ArrayList copyArr = loadedArrayList;
        readFile(leaderBoardFile);
        str = "Name ---------   Prize -------\n";
        txtArea.setText(getStr());

        for (Object aCopyArr : copyArr) {
            setStr(aCopyArr.toString());
            txtArea.setText(getStr());

        }
        setOrderTypeSelected("time");
    }

    private void orderByName(TextArea txtArea) {
        ArrayList copyArr = getLoadedArrayList();

        Collections.sort(copyArr);

        //System.out.println(loadedArrayList);

        str = "Name ---------   Prize -------\n";
        txtArea.setText(getStr());

        for (Object aCopyArr : copyArr) {
            setStr(aCopyArr.toString());
            txtArea.setText(getStr());
        }
        setOrderTypeSelected("name");
    }

    private void orderByPrize(TextArea txtArea) {
        ArrayList copyArr = new ArrayList();
        String lineRead;
        for(int i=0; i<loadedArrayList.size(); i++){
            lineRead = loadedArrayList.get(i).toString();

            try{
                String parts [] = lineRead.split(" - ");
                String playerName = parts[0];
                String playerPrize = parts[1];

                String changedStr = playerPrize + " - " + playerName;
                copyArr.add(changedStr);


            }catch (ArrayIndexOutOfBoundsException air) {
                String error = "Not enough items in  : '" + loadedArrayList
                        + "' index position : " + air.getMessage();
                System.out.println(error);
            }

        }
        Collections.sort(copyArr);

        //System.out.println(copyArray);

        str = "Prize ---------   Name -------\n";
        txtArea.setText(getStr());

        for (Object aCopyArr : copyArr) {
            setStr(aCopyArr.toString());
            txtArea.setText(getStr());
        }
        setOrderTypeSelected("prize");
    }



    private void removeFromList(TextField removeWinner) {
        String lineRead;
        String path = "src\\main\\java\\listOfWinners.txt";

        if(!removeWinner.getText().isEmpty()) {

            for( int i=loadedArrayList.size()-1; i >= 0 ; i--){

                lineRead = String.valueOf(loadedArrayList.get(i));

                //System.out.println("Read: " + lineRead);

                String parts [] = lineRead.split(" - ");
                String namePart = parts[0];

                String namePartArray [] = namePart.split("_");
                String playerFName = namePartArray[0];
                String playerLName = namePartArray[1];


                String comparedStrOne = playerFName + "_" + playerLName;
                String comparedStrTwo = playerFName + " " + playerLName;

                if(removeWinner.getText().equals(comparedStrOne) || removeWinner.getText().equals(comparedStrTwo) ){

                    System.out.println("Find: " + lineRead);

                    loadedArrayList.remove(i);



                }
                else{

                }


            }

        }
        else{

        }
        writeListToFile(path, createListToWrite(loadedArrayList) );

        if(getOrderTypeSelected().equals("time")){
            orderByTime(txtArea);
        }
        if (getOrderTypeSelected().equals("name")){
            orderByName(txtArea);
        }
        if (getOrderTypeSelected().equals("prize")){
            orderByPrize(txtArea);
        }

    }

    private String createListToWrite(ArrayList copyArr){

        StringBuilder report = new StringBuilder();

        for (int i = 0; i < copyArr.size(); i++) {
            String aRewardArray = copyArr.get(i).toString();
            report.append(aRewardArray);
            report.append("\n");
        }

        return report.toString();
    }

    private void writeListToFile(String path, String report) {


        FileWriter fw;
        try {

            fw = new FileWriter(path, false);
            Writer output = new BufferedWriter(fw);
            output.write(report);
            output.flush();
            output.close();
        }
        //message and stop if file not found
        catch (FileNotFoundException fnf) {
            System.out.println(path + " not found ");
            System.exit(0);
        }
        //stack trace here because we don't expect to come here
        catch (IOException ioe) {
            ioe.printStackTrace();
            System.exit(1);
        }
    }



    private void readFile(String filename) {
    //https://beginnersbook.com/2014/10/how-to-remove-repeated-elements-from-arraylist/

        loadedArrayList.clear();
        try {
            File f = new File(filename);
            Scanner scanner = new Scanner(f);
            while (scanner.hasNextLine()) {
                //read first line and process it
                String inputLine = scanner.nextLine();
                if (inputLine.length() != 0) {
                    //ignored if blank line

                    //create a new arraylist from text

                    loadedArrayList.add(inputLine);

                    setStr(inputLine);
                }
            }

        }
        //if the file is not found, stop with system exit
        catch (FileNotFoundException fnf){
            System.out.println( filename + " not found ");
            System.exit(0);
        }
    }

    private void removeDuplicated (){
        // Creating LinkedHashSet

        /* Adding ArrayList elements to the LinkedHashSet
         * in order to remove the duplicate elements and
         * to preserve the insertion order.
         */
        LinkedHashSet<String> lhs = new LinkedHashSet<String>(loadedArrayList);

        // Removing ArrayList elements
        loadedArrayList.clear();

        // Adding LinkedHashSet elements to the ArrayList
        loadedArrayList.addAll(lhs);
    }

}
