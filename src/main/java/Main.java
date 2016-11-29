import org.chocosolver.solver.Model;

import java.io.*;

public class Main {

    public static void main(String[] args){

        String weights = "DATA/energies_40.txt";
        String energies = "DATA/weights_40.txt";
        int TAILLE = 40;
        int CAPACITY = 200;


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
            System.out.println("Successfully loaded files");
        }
        ISolve solDefault = new DefaultSolve(weightsArray, energiesArray, CAPACITY);
        solDefault.defineModel();
        solDefault.solve();


        ISolve solCustom = new CustomSolve(weightsArray, energiesArray, CAPACITY);
        solCustom.defineModel();
        solCustom.solve();


    }
}
