import org.chocosolver.solver.Model;
import org.chocosolver.solver.Solution;
import org.chocosolver.solver.variables.IntVar;

import java.util.Random;

public class DefaultSolve implements ISolve {

    static  Random rand = new Random();
    static int n = 100;
    Model model = new Model("Backpack problem with "+n+" objects.");
    IntVar[] occurences = new IntVar[n];
    IntVar weightSum = null, energySum = null;
    int capacity = n*10;
    static int[] weight = new int[n], energy = new int[n];

    public static void randomize() {
        for(int i = 0; i < n; i++){
            weight[i] = rand.nextInt(n*10);
            energy[i] = rand.nextInt(n*10);
        }
    }

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
