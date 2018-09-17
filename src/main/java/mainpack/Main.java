package main.java.mainpack;

  /////////////////////
 //  I.M.P.O.R.T.S  //
/////////////////////
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Side;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class Main extends Application{
      //////////////////////////
     //  V.A.R.I.A.B.L.E.S.  //
    //////////////////////////



    //Stage is the main window
    private Stage window;
    //static variables for easy access to third tab details
    private static int starNum = 0;

    //TabPane
    private static TabPane tabPane = new TabPane();
    //tabs as static --> reset them if need
    static Tab winnersTab = new  Tab0_Winners();
    static Tab guessGameTab = new Tab1_Guess_Game();
    static Tab lottoGameTab = new Tab2_Lottery_Game();
    static Tab prizeTab = new Tab3_Prize();

      ///////////////////////////////////////
     //  G.E.T.T.E.R.S. + S.E.T.T.E.R.S.  //
    ///////////////////////////////////////

    public static int getStarNum() {
        return starNum;
    }
    public static void setStarNum(int starNum) {
        Main.starNum = starNum;
    }

      ////////////////////////////////
     //  C.O.N.S.T.R.U.C.T.O.R.S.  //
    ////////////////////////////////

      //////////////////////
     //  M.E.T.H.O.D.S.  //
    //////////////////////


    //MAIN METHOD
    public static void main(String[] args) {
        launch(args);
    }

    //method for reset games if user chose it on tab3
    public static void resetTabs(){

        starNum = 0;

        //revome tabs
        tabPane.getTabs().remove(winnersTab);
        tabPane.getTabs().remove(guessGameTab);
        tabPane.getTabs().remove(lottoGameTab);
        tabPane.getTabs().remove(prizeTab);



        //clear original panes children elements and create new tabs
        Tab0_Winners.getbPane().getChildren().clear();
        winnersTab = new Tab0_Winners();

        Tab1_Guess_Game.getTab1VBox1().getChildren().clear();
        guessGameTab = new Tab1_Guess_Game();

        Tab2_Lottery_Game.getMainPane().getChildren().clear();
        lottoGameTab = new Tab2_Lottery_Game();

        Tab3_Prize.getGridPane().getChildren().clear();
        prizeTab = new Tab3_Prize();


        //add new tabs to the tabPane
        tabPane.getTabs().addAll(winnersTab, guessGameTab, lottoGameTab, prizeTab);

        //change the focus back to the first tab
        SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();
        selectionModel.select(0);
    }

    //START METHOD
    //Stage primaryStage is the main window
    @Override
    public void start(Stage primaryStage)  throws Exception{{

        window = primaryStage;
        //add title to window
        window.setTitle("Lab1 games");
        //can't resize main window
        window.setResizable(false);
        Group root = new Group();
        Parent parent = FXMLLoader.load(getClass().getResource("sample.fxml"));
        BorderPane borderPane = new BorderPane();

        Scene scene = new Scene(root, 500, 400, Color.DARKGREY);

        StartAlertBox.display("Login");

        //create TabPane & style it (padding...etc)
        tabPane.setPadding(new Insets(5, 5, 5, 5));
        //position for tabs menu
        tabPane.setSide(Side.TOP);


        tabPane.getTabs().addAll(winnersTab, guessGameTab, lottoGameTab, prizeTab);

        // bind to take available space
        borderPane.prefHeightProperty().bind(scene.heightProperty());
        borderPane.prefWidthProperty().bind(scene.widthProperty());
        borderPane.setCenter(tabPane);

        root.getChildren().add(borderPane);

        window.setScene(new Scene(parent, 300, 275));
        //A scene object can be added to a stage(window)
        window.setScene(scene);
        window.show();
    }




}
}

//TabbedPane
//http://www.java2s.com/Code/Java/JavaFX/SetTabPanedirectionSideLEFTSideTOPSideRIGHTSideBOTTOM.htm