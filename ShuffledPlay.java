import java.util.ArrayList;

public class ShuffledPlay implements PlayOrder,Observer {

    private int aCurrent;
    private int aSize;
    private ArrayList<Integer> aAlreadyPlayed= new ArrayList<>();
    public ShuffledPlay(int pCurrent, int pSize,ArrayList<Integer> pAlreadyPlayed ){
        assert pCurrent >=-1&& pSize>=0&&pAlreadyPlayed!=null;
        aCurrent=pCurrent;
        aSize=pSize;
        aAlreadyPlayed=pAlreadyPlayed;

    }

    public void setCurrent(int aCurrent) {
        this.aCurrent = aCurrent;
    }

    public void setSize(int aSize) {
        this.aSize = aSize;
    }

    /**
     * Q3.1
     * Chooses a random Song and checks if it has been already Played if not it select one
     * */
    public int getNext(){
       aCurrent=(int)(Math.random() * ((aSize) ));
        while (aAlreadyPlayed.contains((aCurrent))){
            aCurrent=(int)(Math.random() * ((aSize) ));
        }
        assert aCurrent<aSize;
        return aCurrent;
    }

    public boolean hasNext(){
        return (aAlreadyPlayed.size()<aSize);
    }
}

