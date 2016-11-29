import org.chocosolver.solver.Model;

import java.io.*;

public class Main {

    public static void main(String[] args){

        String weights = "DATA/p08_w.txt";
        String energies = "DATA/p08_p.txt";

        String line = null;
        String line2 = null;

        int[] weightsArray = new int[24];
        int[] energiesArray = new int[24];

        try {
            FileReader fr1 = new FileReader(weights);
            FileReader fr2 = new FileReader(energies);

            BufferedReader br1 = new BufferedReader(fr1);
            BufferedReader br2 = new BufferedReader(fr2);

            int count = 0;

            while((line = br1.readLine()) != null) {
                weightsArray[count] = Integer.parseInt(line);
                energiesArray[count] = Integer.parseInt(br2.readLine());
                count++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            System.out.println("Successfully loaded files");
        }
        ISolve sol = new DefaultSolve(weightsArray, energiesArray);
        sol.defineModel();
        sol.solve();
//        ISolve sol = new DefaultSolve();
//        sol.defineModel();
//        sol.solve();

//        ISolve sol = new ParetoSolve();
//        sol.defineModel();
//        sol.solve();


    }
}
