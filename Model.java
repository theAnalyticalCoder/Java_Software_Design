import java.util.ArrayList;
/**
 * we do not need to store an ArrayList of all the already played Songs
 * */
public class Model {
    private int aCurrent = -1;
    private int aSize=0;
    private ArrayList<Observer> aObservers = new ArrayList<>();

    public Model(int pCurrent, int pSize){
        aSize=pSize;
        aCurrent=pCurrent;

    }
    public void addObserver(Observer pObserver)
    { aObservers.add(pObserver); }
    public void removeObserver(Observer pObserver)
    { aObservers.remove(pObserver); }

    public void setCurrent(int aCurrent) {
        this.aCurrent = aCurrent;
    }

    public int getCurrent() {
        return aCurrent;
    }

    public void setSize(int aSize) {
        this.aSize = aSize;
    }

    public void notifyObservers()
    {
        for(Observer observer : aObservers) {
            observer.setCurrent(aCurrent);
            observer.setSize(aSize);
        }
    }
}

