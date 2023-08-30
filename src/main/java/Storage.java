import java.io.*;

import java.util.ArrayList;

class Storage {
    private String textFile;
    private String dataFile;
    private ArrayList<Task> taskArr;
    public Storage(String textFile, String dataFile) {
        this.textFile = textFile;
        this.dataFile = dataFile;
    }
    public ArrayList<Task> load() throws DukeException {
        try {
            FileInputStream fileIn = new FileInputStream(this.dataFile);
            ObjectInputStream objIn = new ObjectInputStream(fileIn);
            // can safely cast because all the methods to modify the array
            // guarantee that the elements in the array are all sub-classes
            // of Task, the array is type-safe
            taskArr = (ArrayList<Task>) objIn.readObject();
            objIn.close();
        } catch (IOException e) {
            throw new DukeException("Data file is empty.");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return taskArr;
    }
    /**
     * Saves all the tasks' information in a text file.
     */
    public void writeToFile() {
        try {
            FileWriter fw = new FileWriter(textFile);
            BufferedWriter bw = new BufferedWriter(fw);
            for (int i = 0; i < taskArr.size(); i++) {
                bw.write(taskArr.get(i).toString());
                bw.newLine();
            }
            bw.close();
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    public void save() {
        try {
            FileOutputStream fos = new FileOutputStream(dataFile);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(taskArr);
            oos.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}
