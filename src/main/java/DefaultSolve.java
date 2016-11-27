import org.chocosolver.solver.Model;
import org.chocosolver.solver.Solution;
import org.chocosolver.solver.variables.BoolVar;
import org.chocosolver.solver.variables.IntVar;

import java.util.ArrayList;

public class DefaultSolve implements ISolve {

    private int n = 5;
    private Model model = new Model("Backpack problem with "+n+" objects.");

    private IntVar[] isPickedArray = new IntVar[n];
    private int[] weight = new int[n];

    public void defineModel() {

        ArrayList<Item> items = new ArrayList<Item>();

        weight[0] = 1;
        weight[1] = 10;
        weight[2] = 4;
        weight[3] = 3;
        weight[4] = 2;

        //Initialisation des objets dans le problème
        for(int i = 0; i < n; i++){
            isPickedArray[i] = model.intVar("P"+i, 0, 1);
        }

        //Initialisation de la capacité du sac à dos
        int capacity = 2;

        model.scalar(isPickedArray, weight, "<=", capacity).post();


    }

    public void solve() {

        Solution solution = model.getSolver().findSolution();//.findOptimalSolution(utility, true);
        if(solution != null){
            System.out.println(solution.toString());
        }
    }
}
