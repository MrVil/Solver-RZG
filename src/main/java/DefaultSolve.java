import org.chocosolver.solver.Model;
import org.chocosolver.solver.variables.BoolVar;

import java.util.ArrayList;

public class DefaultSolve implements ISolve {

    private int n = 100;
    private Model model = new Model("Backpack problem with "+n+" objects.");

    public void defineModel() {

        ArrayList<Item> items = new ArrayList<Item>();

        for(int i = 0; i < n; i++){
            items.add(new Item(
                model.intVar("W"+i, i),
                model.intVar("U"+i, i),
                model.boolVar("P")
            ));
        }

        //model.arithm().post;


    }

    public void solve() {
    }
}
