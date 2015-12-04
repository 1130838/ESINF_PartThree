package model;

import treebase.BST;
import utils.FileImport;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Created by bruno.devesa on 03/12/2015.
 */
public class TreeFinishedProject extends BST<FinishedProject> {

    /**
     * Constructor of the tree
     *
     * @param filename - name of the file to import data
     * @throws FileNotFoundException
     */
    public TreeFinishedProject(String filename) throws FileNotFoundException {
        if (filename != null) {
            importData(filename);
        }
    }

    /**
     * Import data from the file (parameter), create the FinishedProject object
     * and insert them in the tree
     *
     * @param filename - name of the file to import data
     * @throws FileNotFoundException
     */
    private void importData(String filename) throws FileNotFoundException {
        ArrayList<ArrayList<String>> fProjList = FileImport.importDataFromFile(filename);

        for (ArrayList<String> fProj : fProjList) {
            super.insert(new FinishedProject(fProj));
        }
    }

}
