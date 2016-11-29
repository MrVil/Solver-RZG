import org.chocosolver.solver.Model;

public class Main {

    public static void main(String[] args){

        ISolve sol1 = new DefaultSolve(), sol2 = new DefaultSolve(), sol3 = new DefaultSolve();
        DefaultSolve.randomize();
        sol1.defineModel();
        sol1.solve();
        sol2.defineModel();
        sol2.solve();
        sol3.defineModel();
        sol3.solve();

    }
}
