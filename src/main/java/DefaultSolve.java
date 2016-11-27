import org.chocosolver.solver.Model;
import org.chocosolver.solver.Solution;
import org.chocosolver.solver.variables.IntVar;

public class DefaultSolve implements ISolve {

    private int n = 5;
    private Model model = new Model("Backpack problem with "+n+" objects.");


    private IntVar[] occurences = new IntVar[n];
    private IntVar weightSum = null, energySum = null;
    private int[] weight = {2, 5, 1, 10, 5}, energy = {6, 4, 2, 8, 5};
    private int capacity = 10;

    public void defineModel() {



        for(int i = 0; i < n; i++){
            occurences[i] = model.intVar("O"+i, 0, 1);
        }
        weightSum = model.intVar("ws", 0, n*100);
        energySum = model.intVar("es", 0, n*100);

        model.scalar(occurences, weight, "=", weightSum).post();
        model.scalar(occurences, energy, "=", energySum).post();

        model.arithm(weightSum, "<=", capacity).post();

    }

    public void solve() {

        Solution solution = model.getSolver().findOptimalSolution(energySum, true);
        if(solution != null){
            System.out.println(solution.toString());
        }
    }
}
