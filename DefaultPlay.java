import java.util.ArrayList;

public class DefaultPlay implements PlayOrder, Observer {

    private int aCurrent;
    private int aSize;
    private ArrayList<Integer> aAlreadyPlayed= new ArrayList<>();
    public DefaultPlay(int pCurrent, int pSize,ArrayList<Integer> pAlreadyPlayed ){
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
     * takes the first possible song
     * Does not Loop around even if some songs have not been played
     * */
    public int getNext(){
        aCurrent++;
        while (aAlreadyPlayed.contains((aCurrent))&&aCurrent<aSize){
            aCurrent++;
        }
        assert aCurrent<aSize;
        return aCurrent;
    }
    /**
     *  Only hasNext() if the end of the queue has been reached
     * */
    public boolean hasNext(){
        int tempCurrent=aCurrent;
        while (aAlreadyPlayed.contains((tempCurrent+1))&&tempCurrent+1<aSize){
            tempCurrent++;
        }

        return tempCurrent+1<aSize ;
    };
}
