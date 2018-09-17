package main.java.mainpack;

/*
- You could use a class called Prize with two attributes Key (String) and prize (String).
 */
public class Prize implements java.io.Serializable, Cloneable{


      //////////////////////////
     //  V.A.R.I.A.B.L.E.S.  //
    //////////////////////////
    private String key;
    private String prize;


      ///////////////////////////////////////
     //  G.E.T.T.E.R.S. + S.E.T.T.E.R.S.  //
    ///////////////////////////////////////
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getPrize() {
        return prize;
    }

    public void setPrize(String prize) {
        this.prize = prize;
    }


      ////////////////////////////////
     //  C.O.N.S.T.R.U.C.T.O.R.S.  //
    ////////////////////////////////
    public Prize() {

    }

    public Prize(String value) {
        setPrize(value);
    }

    public Prize(String key, String prize) {
        this.key=key;
        this.prize=prize;

    }

    @Override
    public String toString() {
        return "key="+ key + " reward=" + prize ;
    }

    //for cloneable
    public Object clone()throws CloneNotSupportedException{
        return super.clone();
    }
}
