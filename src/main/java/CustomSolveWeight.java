import org.chocosolver.solver.Solution;
import org.chocosolver.solver.Solver;
import org.chocosolver.solver.search.strategy.Search;
import org.chocosolver.solver.search.strategy.selectors.values.IntValueSelector;
import org.chocosolver.solver.search.strategy.selectors.variables.VariableSelector;
import org.chocosolver.solver.variables.IntVar;

class CustomSolveWeight extends DefaultSolve {

    CustomSolveWeight(int[] weights, int[] energies, int capacity) {
        super(weights, energies, capacity);
    }

    public void solve() {

        Solver s = model.getSolver();
        s.setSearch(Search.intVarSearch(
                // variable selector
                (VariableSelector<IntVar>) variables -> {
                    IntVar ret = null;
                    int maxValue = -1;
                    for(int i = 0; i < variables.length; i++) {
                        if (!variables[i].isInstantiated()) {
                            if (weights[i] > maxValue) {
                                ret = variables[i];
                                maxValue = weights[i];
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
