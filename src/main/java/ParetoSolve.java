import org.chocosolver.solver.Model;
import org.chocosolver.solver.Solution;
import org.chocosolver.solver.variables.IntVar;

import java.util.List;

/**
 * Created by station2 on 27/11/2016.
 */
public class ParetoSolve implements ISolve {

    private int n;
    private int[] nbItems;
    private int[] weights;
    private int[] utilities;
    private Model model;
    private IntVar[] occurrences;
    private IntVar weight;
    private IntVar utility;

    public void defineModel() {
        this.n = 4;
        this.nbItems = new int[]{5, 2, 6, 7}; // Number of items for each category
        this.weights = new int[]{5, 8, 7, 8}; // weight of an item for each item
        this.utilities  = new int[]{5, 9, 4, 1}; // Evaluated use of the object => need to be maximised

        this.model = new Model("Pareto solving of knapsack problem");

        this.occurrences = new IntVar[nbItems.length];
        for (int i = 0; i < nbItems.length; i++) {
            occurrences[i] = model.intVar(0, nbItems[i]);
        }

        this.weight = model.intVar(0,100);
        this.utility = model.intVar(0,200);

        model.knapsack(occurrences, weight, utility, weights, utilities).post();

    }

    public void solve() {

        List<Solution> solutions = model.getSolver().findParetoFront(new IntVar[]{this.utility},true);
        for (Solution solution : solutions) {
            System.out.println("-----------------------------------");
            System.out.println("Weight: " + solution.getIntVal(weight) + "\t Utility:" + solution.getIntVal(utility));
            System.out.println("Headlight: " + solution.getIntVal(occurrences[0]));
            System.out.println("Chocolate bar: " + solution.getIntVal(occurrences[1]));
            System.out.println("Warm cloth: " + solution.getIntVal(occurrences[2]));
            System.out.println("Shoes: " + solution.getIntVal(occurrences[3]));
        }
        System.out.println("There are "+solutions.size()+" Pareto-optimal solutions");

    }
}
