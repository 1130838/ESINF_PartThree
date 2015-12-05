package model;

import treebase.BST;
import utils.FileImport;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by bruno.devesa on 03/12/2015.
 */
public class TreeFinishedProject extends BST<FinishedProject> {



   // List<ArrayList<String>> finishedProjectList;

    public TreeFinishedProject() {
    }

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

  /*  public List<ArrayList<String>> getFinishedProjectList() {
        return finishedProjectList;
    }*/


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

            //finishedProjectList.add(fProj);
        }
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TreeFinishedProject other = (TreeFinishedProject) obj;

        Iterator otherIt = other.inOrder().iterator();
        Iterator instanceIt = this.inOrder().iterator();
        while (otherIt.hasNext()) {
            if (!otherIt.next().equals(instanceIt.next())) {
                return false;
            }
        }

        return true;
    }


}
