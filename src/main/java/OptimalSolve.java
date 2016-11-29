import org.chocosolver.solver.Model;
import org.chocosolver.solver.Solution;
import org.chocosolver.solver.variables.IntVar;

import java.util.Random;

class OptimalSolve extends DefaultSolve {

    OptimalSolve(int[] weight, int[] energy, int capacity) {
        super(weight, energy, capacity);
    }

    public void solve() {
        Solution solution = model.getSolver().findOptimalSolution(energySum, true);
        if(solution != null){
            System.out.println(solution.toString());
            model.getSolver().printShortStatistics();
        }
    }
}
