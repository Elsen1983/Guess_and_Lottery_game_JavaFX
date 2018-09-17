package main.java.mainpack;

  /////////////////////
 //  I.M.P.O.R.T.S  //
/////////////////////
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;


public class StartAlertBox {
      //////////////////////////
     //  V.A.R.I.A.B.L.E.S.  //
    //////////////////////////
    private static String userFName="";
    private static String userLName="";
    private static Winners winners = new Winners();


      ///////////////////////////////////////
     //  G.E.T.T.E.R.S. + S.E.T.T.E.R.S.  //
    ///////////////////////////////////////
    public static String getUserFName() {
        return userFName;
    }

    public static void setUserFName(String userFName) {
        StartAlertBox.userFName = userFName;
    }

    public static String getUserLName() {
        return userLName;
    }

    public static void setUserLName(String userLName) {
        StartAlertBox.userLName = userLName;
    }

    public static Winners getWinners() {
        return winners;
    }

    public static void setWinners(Winners winners) {
        StartAlertBox.winners = winners;
    }


      ////////////////////////////////
     //  C.O.N.S.T.R.U.C.T.O.R.S.  //
    ////////////////////////////////

    public StartAlertBox(){
    }

      //////////////////////
     //  M.E.T.H.O.D.S.  //
    //////////////////////

    public static void display(String title) {
        Stage window = new Stage();

        //Block events to other windows
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(350);
        window.setMinHeight(300);

        Label labelOne = new Label();
        labelOne.setText("Please enter your First name:");

        TextField txtFieldOne = new TextField();

        Label labelTwo = new Label();
        labelTwo.setText("Please enter your Last name:");

        TextField txtFieldTwo = new TextField();

        //// Deserialization
        try {
            FileInputStream fileIn = new FileInputStream("src\\main\\java\\lastWinner.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            winners = (Winners) in.readObject();

            System.out.println("Last winner name: " + winners.getfName() + " " + winners.getlName());

            //load the last winner name into the alert-box fields
            txtFieldOne.setText(winners.getfName());
            txtFieldTwo.setText(winners.getlName());

            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
            return;
        } catch (ClassNotFoundException c) {
            System.out.println("WINNERS class not found");
            c.printStackTrace();
            return;
        }

        //button for clear the alert-box fields
        Button resetNames = new Button("Reset name");
        resetNames.setOnAction(e -> {
            txtFieldOne.setText("");
            txtFieldTwo.setText("");
        });

        //button for start the game and use the filled name
        Button startGamesButton = new Button("Start game!");
        startGamesButton.setOnAction(e -> {
                if(!txtFieldOne.getText().equals("")){
                    txtFieldOne.setStyle("-fx-border-color: none;");
                    if(!txtFieldTwo.getText().equals("")){

                        userFName = txtFieldOne.getText();
                        userLName = txtFieldTwo.getText();

                        //save the filled fields into winners as fName and lName
                        winners.setfName(txtFieldOne.getText());
                        winners.setlName(txtFieldTwo.getText());

                        window.close();
                    }
                    else{
                        txtFieldTwo.setStyle("-fx-border-color: red;");
                    }

                }
                else{
                    txtFieldOne.setStyle("-fx-border-color: red;");
                }
    });
        //button for Visual VM
        Button vmButton = new Button("VM_Test");
        vmButton.setOnAction(e -> {
            ArrayList testList = new ArrayList();
            Prize pr = new Prize();
            try{
                while(true){
                    testList.add(pr.clone());

                }
            }catch (OutOfMemoryError om){
                om.printStackTrace();
            } catch (CloneNotSupportedException e1) {
                e1.printStackTrace();
            }
        });


        VBox layout = new VBox(10);

        layout.getChildren().addAll(labelOne, txtFieldOne, labelTwo, txtFieldTwo, resetNames, startGamesButton, vmButton );
        layout.setAlignment(Pos.CENTER);
        layout.setMinHeight(200);
        layout.setMinWidth(200);

        //Display window and wait for it to be closed before returning
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }
}