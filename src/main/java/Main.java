import java.io.*;

public class Main {

    public static void main(String[] args){

        String weights = "DATA/energies_40.txt"; //p08_w //energies_40
        String energies = "DATA/weights_40.txt"; //p08_p //weights_40
        int TAILLE = 40; //24 //40
        int CAPACITY = 100; //6404180 //100


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


        ISolve solCustomWeight = new CustomSolveWeight(weightsArray, energiesArray, CAPACITY);
        solCustomWeight.defineModel();
        solCustomWeight.solve();


        ISolve solCustomRatio = new CustomSolveRatio(weightsArray, energiesArray, CAPACITY);
        solCustomRatio.defineModel();
        solCustomRatio.solve();


        ISolve solOptimal = new OptimalSolve(weightsArray, energiesArray, CAPACITY);
        solOptimal.defineModel();
        solOptimal.solve();

    }
}
