import org.chocosolver.solver.Model;

public class Main {

    public static void main(String[] args){

        ISolve sol = new DefaultSolve();
        sol.defineModel();
        sol.solve();

    }
}
