import java.io.*;
import java.util.Random;

public class Main {

    public static void main(String[] args){

        String weightsFile; //p08_w //energies_40
        String energiesFile; //p08_p //weights_40
        int TAILLE = 5; //24 //40
        int CAPACITY = 15; //6404180 //100
        int BOUND = 10;

        for (int i = 0; i < 5; i ++) {
            System.out.println("START ----------------------------------- ITER : " + i);
            weightsFile = "WEIGHTS_iter_" + i + "_" + TAILLE + "_" + BOUND + ".txt";
            energiesFile = "ENERGIES_iter_" + i + "_" + TAILLE + "_" + BOUND + ".txt";
            fileGeneratorMarcEdition(TAILLE, weightsFile, BOUND);
            fileGeneratorMarcEdition(TAILLE, energiesFile, BOUND);

            String line = null;

            int[] weightsArray = new int[TAILLE];
            int[] energiesArray = new int[TAILLE];

            try {
                FileReader fr1 = new FileReader("DATA/" + weightsFile);
                FileReader fr2 = new FileReader("DATA/" + energiesFile);

                BufferedReader br1 = new BufferedReader(fr1);
                BufferedReader br2 = new BufferedReader(fr2);

                int count = 0;

                while((line = br1.readLine()) != null) {
                    weightsArray[count] = Integer.parseInt(line);
                    energiesArray[count] = Integer.parseInt(br2.readLine());
                    count++;
                }

                fr1.close();
                fr2.close();

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

            System.out.println("START ----------------------------------- ITER : " + i);
        }


    }

    public static void fileGenerator(int nbElements, int nbFile) {
        Random rand = new Random();
        for(int i = 0; i<nbFile; i++) {

            int[] randWeights = new int[nbElements];
            int[] randEnergies = new int[nbElements];
            for(int j=0; j<nbElements;j++) {
                randWeights[j] = rand.nextInt(10) + 1;
                randEnergies[j] = rand.nextInt(10) + 1;
            }

            String weightsContent = "";
            String energiesContent ="";

            for(int j = 0; j<nbElements;j++) {
                weightsContent = weightsContent + randWeights[j]+ "\r\n";
                energiesContent = energiesContent + randEnergies[j] +"\r\n";
            }

            File fileWeight = new File("DATA/AutogeneratedFiles/weights" + nbElements + "_" + (i+1) +".txt");
            File fileEnergy = new File("DATA/AutogeneratedFiles/energies" + nbElements + "_" + (i+1) +".txt");

            try {
                if (!fileEnergy.exists()) {
                    fileEnergy.createNewFile();
                }

                if (!fileWeight.exists()) {
                    fileWeight.createNewFile();
                }

                FileWriter fwW = new FileWriter(fileWeight.getAbsoluteFile());
                FileWriter fwE = new FileWriter(fileEnergy.getAbsoluteFile());

                BufferedWriter bwW = new BufferedWriter(fwW);
                BufferedWriter bwE = new BufferedWriter(fwE);

                bwW.write(weightsContent);
                bwE.write(energiesContent);

                bwW.close();
                bwE.close();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                System.out.println("------------------------------------------------------");
            }
        }
    }

    public static void fileGeneratorMarcEdition(int lines, String name, int range) {
        try {

            File file = new File("DATA/" + name);
            Random rand = new Random();
            String content = "";
            if (!file.exists()) {
                file.createNewFile();
            }

            for (int i = 0 ; i<lines; i++) {
                content = content + (rand.nextInt(range) + 1) + "\r\n";
            }
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(content);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
