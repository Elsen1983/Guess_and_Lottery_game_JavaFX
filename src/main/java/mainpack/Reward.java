package main.java.mainpack;

public class Reward {

      //////////////////////////
     //  V.A.R.I.A.B.L.E.S.  //
    //////////////////////////
    private String prize, stars, chooseReward;


      ///////////////////////////////////////
     //  G.E.T.T.E.R.S. + S.E.T.T.E.R.S.  //
    ///////////////////////////////////////
    public String getPrize() {
        return prize;
    }

    public void setPrize(String prize) {
        this.prize = prize;
    }

    public String getStars() {
        return stars;
    }

    public void setStars(String stars) {
        this.stars = stars;
    }

    public String getChooseReward() {
        return chooseReward;
    }

    public void setChooseReward(String chooseReward) {
        this.chooseReward = chooseReward;
    }

      ////////////////////////////////
     //  C.O.N.S.T.R.U.C.T.O.R.S.  //
    ////////////////////////////////

    public Reward(String pr, String st, String cR) {
        prize = pr;
        stars = st;
        chooseReward = cR;

    }

    public String toString()
    {
        String result = "\n" + prize + ",";
        result += stars + ",";
        result += chooseReward ;

        return result;
    }
}
