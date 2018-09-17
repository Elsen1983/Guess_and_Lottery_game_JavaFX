package main.java.mainpack;

/*

1.	Create an arraylist of Winners for your Gui game. You are allowed to add remove and list the Winners.
    Use a javaFx gui for this.


2.	 This class is to be persisted to an object file using serialization. You must load the contents at startup.

3.	Two reports are to be produced about all the winners. The first report is just ordered by the natural ordering i.e.
    Last and First Name. The second report is to be ordered on the prize that they won.


*/

  /////////////////////
 //  I.M.P.O.R.T.S  //
/////////////////////
import javafx.scene.control.Tab;

import java.io.*;
import java.util.ArrayList;

public class Winners extends Tab implements java.io.Serializable  {

      //////////////////////////
     //  V.A.R.I.A.B.L.E.S.  //
    //////////////////////////
    private String fName;
    private String lName;
    private transient Prize prize = new Prize();
    private transient String reward = prize.getPrize();
    private transient ArrayList<String> winnerList = new ArrayList<>();

      ///////////////////////////////////////
     //  G.E.T.T.E.R.S. + S.E.T.T.E.R.S.  //
    ///////////////////////////////////////
    public Prize getPrize() {
        return prize;
    }

    public void setPrize(Prize prize) {
        this.prize = prize;
    }

    public String getReward() {
        return reward;
    }

    public void setReward(String reward) {
        this.reward = reward;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public ArrayList<String> getWinnerList() {
        return winnerList;
    }

    public void setWinnerList(ArrayList<String> winnerList) {
        this.winnerList = winnerList;
        winnerList.add(getfName() + "_" + getlName() + " - " + getReward());
    }




      ////////////////////////////////
     //  C.O.N.S.T.R.U.C.T.O.R.S.  //
    ////////////////////////////////

    public Winners() {

    }
    public Winners (String fName, String lName, String reward){
        this.fName = fName;
        this.lName = lName;
        this.reward = reward;

    }

      //////////////////////
     //  M.E.T.H.O.D.S.  //
    //////////////////////

    public void addToWinnersList(String fName, String lName, String reward){

        if(!fName.isEmpty() && !lName.isEmpty() && !reward.isEmpty()) {


            String winnersStr = fName + "_" + lName + " - " + reward;

            //System.out.println(winnersStr);

            setWinnerList(winnerList);

            System.out.println(getWinnerList());

            String path = "src\\main\\java\\listOfWinners.txt";

            writeListToFile(path, createListToWrite());
        }
        else{
            System.out.println("User is unknown!!");
        }

    }

    public String createListToWrite(){

        StringBuilder report = new StringBuilder();

        for (int i = 0; i < winnerList.size(); i++) {
            String aRewardArray = winnerList.get(i).toString();
            report.append(aRewardArray);
            report.append("\n");
            System.out.println("Hmm:" + report);
        }

        return report.toString();
    }

    public void writeListToFile(String path, String report) {

        //How to serialize ArrayList in java <--- an other way is to serialize the arraylist
        //https://beginnersbook.com/2013/12/how-to-serialize-arraylist-in-java/

        //Different ways to shorting an arraylist:
        //https://dzone.com/articles/sorting-java-arraylist

            FileWriter fw;
            try {
                // true is necessary for "not overwrite" the original file
                //Creates a file output stream to write to the file represented by the specified File object.
                // If the second argument is true, then bytes will be written to the end of the file rather than the beginning.
                // A new FileDescriptor object is created to represent this file connection.
                fw = new FileWriter(path, true);
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


    public void printWinners(){
        System.out.print( getfName() + " " + getlName() + " --- " + getReward());
        //System.out.println("Array: " + getWinnerList());
    }

}
