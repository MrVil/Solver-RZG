import org.chocosolver.solver.Model;
import org.chocosolver.solver.Solution;
import org.chocosolver.solver.variables.IntVar;

import java.util.Random;

class DefaultSolve implements ISolve {

    static  Random rand = new Random();
    protected static int n = 24;
    protected Model model = new Model("Backpack problem with "+n+" objects.");
    protected IntVar[] occurences = new IntVar[n];
    protected int[] weights = new int[n], energies = new int[n];
    protected int capacity;
    protected IntVar weightSum;
    protected IntVar energySum;

    DefaultSolve(int[] weights, int[] energies, int capacity) {
        this.weights = weights;
        this.energies = energies;

        this.capacity = capacity;

        this.n = weights.length;
        this.occurences = new IntVar[n];
        this.model = new Model("Backpack problem with "+n+" objects.");
    }

    public void defineModel() {

        for(int i = 0; i < n; i++){
            occurences[i] = model.intVar("O"+i, 0, 1);
        }
        weightSum = model.intVar("ws", 0, n * capacity);
        energySum = model.intVar("es", 0, n * capacity);

        model.scalar(occurences, weights, "=", weightSum).post();
        model.scalar(occurences, energies, "=", energySum).post();

        model.arithm(weightSum, "<=", capacity).post();

    }

    public void solve() {
        Solution solution = model.getSolver().findSolution();
        if(solution != null){
            System.out.println(solution.toString());
            model.getSolver().printStatistics();
        }
    }
}
