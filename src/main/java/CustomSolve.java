import org.chocosolver.solver.Model;
import org.chocosolver.solver.Solution;
import org.chocosolver.solver.Solver;
import org.chocosolver.solver.search.strategy.Search;
import org.chocosolver.solver.search.strategy.selectors.values.IntValueSelector;
import org.chocosolver.solver.search.strategy.selectors.variables.VariableSelector;
import org.chocosolver.solver.search.strategy.strategy.IntStrategy;
import org.chocosolver.solver.variables.IntVar;

import java.util.Random;

class CustomSolve implements ISolve {

    static  Random rand = new Random();
    private static int n = 24;
    private Model model = new Model("Backpack problem with "+n+" objects.");
    private IntVar[] occurences = new IntVar[n];
    private IntVar energySum = null;
    private int[] weight = new int[n], energies = new int[n];
    private String stringReturn = "";

    int capacity;

    CustomSolve(int[] weight, int[] energies, int capacity) {
        this.weight = weight;
        this.energies = energies;

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
        model.scalar(occurences, energies, "=", energySum).post();

        model.arithm(weightSum, "<=", capacity).post();

    }

    public void solve() {

        Solver s = model.getSolver();
        /*
        s.setSearch(
                Search.intVarSearch(
                // selects the variable of smallest domain size
                new FirstFail(model),
                // selects the smallest domain value (lower bound)
                new IntDomainMax(),
                // apply equality (var = val)
                DecisionOperator.int_eq,
                // variables to branch on
                occurences
        ));
        */

        /*
        s.setSearch(intVarSearch(
                // variable selector
                (VariableSelector<IntVar>) variables -> {
                    for(IntVar v:variables){
                        if(!v.isInstantiated()){
                            return v;
                        }
                    }
                    return null;
                },
                // value selector
                (IntValueSelector) var -> var.getLB(),
                // variables to branch on
                occurences
        ));
        */

        s.setSearch(Search.intVarSearch(
                // variable selector
                (VariableSelector<IntVar>) variables -> {
                    IntVar ret = null;
                    int maxValue = 0;
                    for(int i = 0; i < variables.length; i++) {
                        if (!variables[i].isInstantiated()) {
                            if (energies[i] > maxValue) {
                                ret = variables[i];
                                maxValue = energies[i];
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
            //model.getSolver().printStatistics();
            System.out.println("ça : " + stringReturn);
        }
    }
/*
    public static IntStrategy intVarSearch(VariableSelector<IntVar> varSelector,
                                           IntValueSelector valSelector,
                                           IntVar... vars) {
        return null;
    }
    */
}
