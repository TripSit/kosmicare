package eddiecurtis.github.com.kosmicare;

import android.app.Activity;
import android.os.Environment;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by eddie on 19/08/15.
 */
class FileManager {


    private static final FileManager INSTANCE = new FileManager();

    private Map<Integer, File> fileMap;

    FileManager() {
        refreshFileMap();
    }

    static FileManager instance() {
        return INSTANCE;
    }

    private void refreshFileMap() {
        fileMap = new TreeMap<Integer, File>();
        File[] files = getSaveDirectory().listFiles();
        if ( files != null ) {
            for (File file : files) {
                try {
                    Integer id = new Integer(getId(file));
                    fileMap.put(id, file);
                } catch (Exception e) {
                    // ignore
                }
            }
        }
    }

    void saveFormData(FormData data) throws IOException {
        String csvData = data.toCsvString();
        File directory = getSaveDirectory();
        directory.mkdirs();
        String fileName = String.format("%d - %s - %s-%s-%s.csv", data.getVisitorId(), data.getVisitor(), data.getYear(), data.getMonth(), data.getDay());
        File file = new File(directory, fileName);
        file.createNewFile();
        FileWriter fw = new FileWriter(file.getAbsoluteFile());
        fw.write(csvData);
        fw.close();

        // If save was successful, delete any other files with this ID which don't have the same name
        String newFilePrefix = fileName.split("-")[0];
        for (File existingFile : directory.listFiles()) {
            if (existingFile.getName().split("-")[0].equals(newFilePrefix) && !existingFile.getName().equals(fileName)) {
                existingFile.delete();
            }
        }
        fileMap.put(new Integer(data.getVisitorId()), file);
    }

    File getSaveDirectory() {
        return new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS) + "/kosmicare");
    }

    void deleteRecord(Activity activity, int currentId, String visitorName) {
        File file = fileMap.remove(new Integer(currentId));
        if (file != null){
            file.delete();
            Toast.makeText(activity, "Deleted record for " + visitorName, Toast.LENGTH_SHORT).show();
            return;
        }
        Toast.makeText(activity, "Record must be saved before it can be deleted", Toast.LENGTH_SHORT).show();
    }

    private int getId(File file) {
        return Integer.parseInt(file.getName().split("-")[0].trim());
    }

    int getNextVisitorId() {
        int highest = 0;
        for (File file : fileMap.values()) {
            highest = Math.max(highest, getId(file));
        }
        return highest + 1;
    }

    String[] getVisitorHeadings() {
        List<String> headings = new ArrayList<String>();
        for (File f : fileMap.values()) {
            int index = f.getName().indexOf("-", f.getName().indexOf("-") + 1);
            headings.add(f.getName().substring(0, index));
        }
        return headings.toArray(new String[headings.size()]);
    }

    FormData getVisitorData(int id) {
        File file = fileMap.get(new Integer(id));
        if (file != null) {
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
                String csv = br.readLine();
                br.close();
                return FormData.fromCsvString(csv);
            } catch (Exception e) {

            }
        }
        return null;
    }
}
