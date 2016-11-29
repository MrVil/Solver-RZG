import org.chocosolver.solver.Model;
import org.chocosolver.solver.Solution;
import org.chocosolver.solver.variables.IntVar;

import java.util.Random;

class OptimalSolve implements ISolve {

    static  Random rand = new Random();
    private static int n = 24;
    private Model model = new Model("Backpack problem with "+n+" objects.");
    private IntVar[] occurences = new IntVar[n];
    private IntVar energySum = null;
    private int[] weight = new int[n], energy = new int[n];

    int capacity;

    /*public static void randomize() {
        for(int i = 0; i < n; i++){
            weight[i] = rand.nextInt(n*10);
            energy[i] = rand.nextInt(n*10);
        }
    }*/

    OptimalSolve(int[] weight, int[] energy, int capacity) {
        this.weight = weight;
        this.energy = energy;

        this.capacity = capacity;

        this.n = weight.length;
        this.occurences = new IntVar[n];
        this.model = new Model("Backpack problem with "+n+" objects.");
    }

    public void defineModel() {

        for(int i = 0; i < n; i++){
            occurences[i] = model.intVar("O"+i, 0, 1);
        }
        IntVar weightSum = model.intVar("ws", 0, n * capacity);
        energySum = model.intVar("es", 0, n*capacity);

        model.scalar(occurences, weight, "=", weightSum).post();
        model.scalar(occurences, energy, "=", energySum).post();

        model.arithm(weightSum, "<=", capacity).post();

    }

    public void solve() {

        Solution solution = model.getSolver().findOptimalSolution(energySum, true);
//        Solution solution = model.getSolver().findSolution();
        if(solution != null){
            System.out.println(solution.toString());
            //model.getSolver().printStatistics();
        }
    }
}
