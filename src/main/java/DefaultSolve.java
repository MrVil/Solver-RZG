import org.chocosolver.solver.Model;
import org.chocosolver.solver.Solution;
import org.chocosolver.solver.variables.IntVar;

import java.util.Random;

class DefaultSolve implements ISolve {

    static  Random rand = new Random();
    private static int n = 24;
    private Model model = new Model("Backpack problem with "+n+" objects.");
    private IntVar[] occurrences = new IntVar[n];
    private IntVar energySum = null;
    private int[] weight = new int[n], energy = new int[n];

    /*public static void randomize() {
        for(int i = 0; i < n; i++){
            weight[i] = rand.nextInt(n*10);
            energy[i] = rand.nextInt(n*10);
        }
    }*/

    DefaultSolve(int[] weight, int[] energy) {
        this.weight = weight;
        this.energy = energy;
    }

    public void defineModel() {

        int capacity = 6404180;
        for(int i = 0; i < n; i++){
            occurrences[i] = model.intVar("O"+i, 0, 1);
        }
        IntVar weightSum = model.intVar("ws", 0, n*capacity);
        energySum = model.intVar("es", 0, n*capacity);

        model.scalar(occurrences, weight, "=", weightSum).post();
        model.scalar(occurrences, energy, "=", energySum).post();


        model.arithm(weightSum, "<=", capacity).post();

    }

    public void solve() {

        Solution solution = model.getSolver().findSolution();//findOptimalSolution(energySum, true);
        if(solution != null){
            System.out.println(solution.toString());
        }
    }
}
