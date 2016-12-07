package org.vitrivr.cineast.explorative;

import org.vitrivr.cineast.ExplorativeConfig;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EvaluationFile {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyyy-mm-dd_hh:mm:ss");
    private static final String name = sdf.format(new Date(System.currentTimeMillis())) + ".csv";
    private static final String path = "./evaluation/" + ExplorativeConfig.getFeatureName() + "/" + ExplorativeConfig.getCompactness();
    private static File file;
    private static FileWriter fw;
    private static long startTime;

    public static void open() throws IOException {
        file = new File(path, name);
        if (!file.exists()){
            new File(path).mkdirs();
            file.createNewFile();
        }
        fw = new FileWriter(file, true);
    }

    public static void start() {
        EvaluationFile.startTime = System.currentTimeMillis();
        try {
            fw.write("0, 0,0,0\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
        writes a csv string, first column is time since starttime is set
    */
    public static void append(int csvArgument){
        StringBuilder sb = new StringBuilder();
        sb.append(System.currentTimeMillis() - startTime).append(",").append(csvArgument);
        try {
            fw.write(sb.toString());
            fw.write("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /*
        writes a csv string, first column is time since starttime is set
    */
    public static void append(long... csvArguments){
        StringBuilder sb = new StringBuilder();
        sb.append(System.currentTimeMillis() - startTime);
        for(long csvArgument : csvArguments){
            sb.append(",").append(csvArgument);
        }
        try {
            fw.write(sb.toString());
            fw.write("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void close(){
        try {
            fw.flush();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
