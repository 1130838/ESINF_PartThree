package model;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import treebase.BST;
import static utils.FileImport.importDataFromFile;
/**
 * Created by bruno.devesa on 03/12/2015.
 */
public class TreeFinishedActivity extends BST {

    /**
     * This constructor populates the newly created tree with FinishedActivities
     * imported from the text file which name is fileName
     * @param fileName Name of the file to import FinishedActivities
     * @throws java.io.FileNotFoundException
     */
    public TreeFinishedActivity(String fileName) throws FileNotFoundException {
        ArrayList<ArrayList<String>> activities = importDataFromFile(fileName);

        for(ArrayList<String> activ : activities){
            insert(new FinishedActivity(activ.get(0), activ.get(1), activ.get(2), Integer.parseInt(activ.get(3)), Integer.parseInt(activ.get(4))));
        }
    }
}
