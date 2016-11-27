import org.chocosolver.solver.variables.BoolVar;
import org.chocosolver.solver.variables.IntVar;

public class Item {

    private IntVar weight, utility;
    private BoolVar isPicked;

    public Item(IntVar weight, IntVar utility, BoolVar isPicked) {
        this.weight = weight;
        this.utility = utility;
        this.isPicked = isPicked;
    }

    public IntVar getWeight() {
        return weight;
    }

    public void setWeight(IntVar weight) {
        this.weight = weight;
    }

    public IntVar getUtility() {
        return utility;
    }

    public void setUtility(IntVar utility) {
        this.utility = utility;
    }

    public BoolVar getIsPicked() {
        return isPicked;
    }

    public void setIsPicked(BoolVar isPicked) {
        this.isPicked = isPicked;
    }
}
