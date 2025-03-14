package at.fhj.msd;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class App {

    public static void main(String[] args) {

        String filename = "src/main/resources/data.txt";
        ArrayList<String> ValidList = read(filename);
        System.out.println(ValidList);

    }

    public static ArrayList<String> read(String filename) {

        ArrayList<String> ValidList = new ArrayList<>();
        File file = new File(filename);

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {

            String line;
            while ((line = br.readLine()) != null) {
                ValidList.add(line);
            }

        } catch (FileNotFoundException e) {
            System.out.printf("File '%s' was not found", file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ValidList;

    }

}
