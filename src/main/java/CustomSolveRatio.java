import org.chocosolver.solver.Model;
import org.chocosolver.solver.Solution;
import org.chocosolver.solver.Solver;
import org.chocosolver.solver.search.strategy.Search;
import org.chocosolver.solver.search.strategy.selectors.values.IntValueSelector;
import org.chocosolver.solver.search.strategy.selectors.variables.VariableSelector;
import org.chocosolver.solver.search.strategy.strategy.IntStrategy;
import org.chocosolver.solver.variables.IntVar;

import java.util.Random;

class CustomSolveRatio extends DefaultSolve {

    private double[] ratio;

    CustomSolveRatio(int[] weight, int[] energies, int capacity) {
        super(weight, energies, capacity);

        this.ratio = new double[weight.length];
        for(int i = 0; i<weight.length; i++) {
            this.ratio[i] = (double) energies[i]/weight[i];
        }
    }

    public void solve() {

        Solver s = model.getSolver();
        s.setSearch(Search.intVarSearch(
                // variable selector
                (VariableSelector<IntVar>) variables -> {
                    IntVar ret = null;
                    double maxValue = -1;
                    for(int i = 0; i < variables.length; i++) {
                        if (!variables[i].isInstantiated()) {
                            if (ratio[i] > maxValue) {
                                ret = variables[i];
                                maxValue = ratio[i];
                            }
                        }
                    }
                    return ret;
                },
                // value selector
                (IntValueSelector) var -> var.getUB(),
                // variables to branch on
                occurences
        ));

        Solution solution = s.findSolution();
        if(solution != null) {
            System.out.println(solution.toString());
            model.getSolver().printStatistics();
        }
    }
}
