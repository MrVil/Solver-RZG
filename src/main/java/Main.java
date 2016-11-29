import org.chocosolver.solver.Model;

import java.io.*;

public class Main {

    public static void main(String[] args){

        String weights = "DATA/p08_w.txt";
        String energies = "DATA/p08_p.txt";
        int TAILLE = 24;
        int CAPACITY = 6404180; //6404180


        String line = null;
        String line2 = null;

        int[] weightsArray = new int[TAILLE];
        int[] energiesArray = new int[TAILLE];

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
            System.out.println("Successfully loaded files\n");
        }
        ISolve solDefault = new DefaultSolve(weightsArray, energiesArray, CAPACITY);
        solDefault.defineModel();
        solDefault.solve();


        ISolve solCustom = new CustomSolve(weightsArray, energiesArray, CAPACITY);
        solCustom.defineModel();
        solCustom.solve();


        ISolve solOptimal = new OptimalSolve(weightsArray, energiesArray, CAPACITY);
        solOptimal.defineModel();
        solOptimal.solve();

    }
}
