package Duke;

import DukeException.*;
import DukeException.FileNotFoundException;
import Task.*;

import java.io.*;

public class Storage {
    final private File file;
    final private FileWriter fileWriter;

    public Storage(String filePath) throws FileNotFoundException {

        file = new File(filePath);
        try {
            fileWriter = new FileWriter(file);
        } catch (IOException e) {
            //Update
            System.out.println("????????");
            throw new FileNotFoundException("!");
        }
    }

    public void AddTask(Task task) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(task.toSaveFormat());
            bufferedWriter.newLine();
            bufferedWriter.flush();
            bufferedWriter.flush();
        } catch (IOException e){
            //Throw exception
        }
    }

    public void RemoveTask(Task task) {
        File tempFile;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            tempFile = new File("C:\\Users\\ortt2\\Documents\\ip\\src\\data\\temp.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(tempFile));
            String hashCode = String.valueOf(task.hashCode());
            String nextLine;
            while((nextLine = bufferedReader.readLine()) != null) {
                String hash = nextLine.split("|")[0];
                if(!nextLine.split("\\|")[0].equals(hashCode)){
                    bufferedWriter.write(nextLine);
                }
            }
            bufferedReader.close();
            bufferedWriter.flush();
        }
        catch (java.io.FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (!tempFile.renameTo(file)) {
                System.err.println("Could not rename temp file.");
            }
            System.out.println("Line deleted successfully.");
    }
        public TaskList LoadTaskList() throws FileNotFoundException {
        return new TaskList(this);
    }
}
