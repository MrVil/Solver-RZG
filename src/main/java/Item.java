import org.chocosolver.solver.variables.BoolVar;
import org.chocosolver.solver.variables.IntVar;

public class Item {

    private int weight;
    private IntVar utility;
    private IntVar isPicked;

    public Item(int weight, IntVar utility, IntVar isPicked) {
        this.weight = weight;
        this.utility = utility;
        this.isPicked = isPicked;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public IntVar getUtility() {
        return utility;
    }

    public void setUtility(IntVar utility) {
        this.utility = utility;
    }

    public IntVar getIsPicked() {
        return isPicked;
    }

    public void setIsPicked(IntVar isPicked) {
        this.isPicked = isPicked;
    }
}
