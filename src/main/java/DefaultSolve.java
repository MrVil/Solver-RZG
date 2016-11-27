import org.chocosolver.solver.Model;
import org.chocosolver.solver.variables.IntVar;

public class DefaultSolve implements ISolve {

    private int n = 100;
    private Model model = new Model("Backpack problem with "+n+" objects.");
    private IntVar[] vars = new IntVar[n];
    public void defineModel() {

    }

    public void solve() {

    }
}
