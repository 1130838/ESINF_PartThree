package model;

import java.io.FileNotFoundException;

/**
 * Created by bruno.devesa on 03/12/2015.
 */
public class ProjectAnalysis {

    private TreeFinishedProject projectTree;
    private TreeFinishedActivity activityTree;

    /**
     * Given a specific file for finished activities and for finished projects
     * this method will extract the data and create a BST for each.
     *
     * @param projectFileName
     * @param activityFileName
     * @throws FileNotFoundException
     */
    public void importData(String projectFileName, String activityFileName) throws FileNotFoundException{
        projectTree=new TreeFinishedProject(projectFileName);
        activityTree=new TreeFinishedActivity(activityFileName);
    }

}
