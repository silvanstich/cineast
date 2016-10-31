package org.vitrivr.cineast.explorative;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by silvanstich on 31.10.16.
 */
public class RequestHandler {

    private static final String PATH = "data/serialized/";
    private static Map<String, PlaneManager> planeManagers = new HashMap<>();

    public static PlaneManager getSpecificPlaneManager(String featureName) throws IOException, ClassNotFoundException {

        if(planeManagers.isEmpty()) readSerializedPlanManagers();
        if(!planeManagers.containsKey(featureName.toLowerCase())) throw new RuntimeException("Feature has not been processed");

        return planeManagers.get(featureName.toLowerCase());
    }

    private static void readSerializedPlanManagers() throws IOException, ClassNotFoundException {
        File path = new File(PATH);
        if(!path.exists()) throw new RuntimeException("Folder for serialized PlaneManager does not exist!");

        for(String fileName : path.list()){
            if(!fileName.matches("plane_manager_[A-z]*.ser")) continue;

            String featureName = fileName.replace("plane_manager_", "").replace(".ser", "").toLowerCase();
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(new File(path, fileName)));
            Object o = objectInputStream.readObject();
            PlaneManager p = (PlaneManager) o;
            planeManagers.put(featureName, p);
        }
    }
}