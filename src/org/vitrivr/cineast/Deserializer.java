package org.vitrivr.cineast;

import org.vitrivr.cineast.explorative.PlaneManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Deserializer {

    public static void main(String[] args){


        Object o = null;
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(new File(args[0])));
            o = objectInputStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        PlaneManager pm = (PlaneManager) o;
        System.out.println(pm.getCenter());

    }
}
