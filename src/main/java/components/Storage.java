package components;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;

import tasks.Deadline;
import tasks.Event;
import tasks.TaskList;
import tasks.ToDo;

import java.io.IOException;

public class Storage {

    private final String PARENT_DIR;
    private final String FILEPATH;
    File store;

    /**
     * Constructor for Storage class
     * @param PARENT_DIR The parent directory of the file to be created
     * @param FILEPATH The path of the file to be created
     */
    public Storage(String PARENT_DIR, String FILEPATH) {
        this.PARENT_DIR = PARENT_DIR;
        this.FILEPATH = FILEPATH;
    }

    /**
     * Creates a file if it does not exist
     * @throws DukeException
     */
    public void loadOrCreateFile() throws DukeException {
        try {
            // Create a File object for the parent directory
            File parentDir = new File(this.PARENT_DIR);
            //System.out.println("Parent dir " + parentDir.getAbsolutePath());

            // Create the directory if it doesn't exist
            if (!parentDir.exists()) {
                boolean dirCreated = parentDir.mkdirs();
                if (!dirCreated) {
                    throw new DukeException("Failed to create directory at " + parentDir.getAbsolutePath());
                }
            }

            // Create a File object for the file to be created
            File file = new File(this.FILEPATH);

            // Create the file if it doesn't exist
            if (!file.exists()) {
                boolean fileCreated = file.createNewFile();
                if (!fileCreated) {
                    throw new DukeException("Failed to create store file at " + file.getAbsolutePath());
                }
            }
        
            //it is guaranteed that the file exists
            store = file;
        } catch (IOException e) {
               throw new DukeException(FILEPATH + " cannot be created.");
        }
    }

    /**
     * Reads data from the file and returns a TaskList object
     * @param storage
     * @return
     * @throws DukeException
     */
    public TaskList readData(Storage storage) throws DukeException {
        BufferedReader br;
        TaskList result = new TaskList();
        try {
            br = new BufferedReader(new FileReader(this.store));
            String line;
            while ((line = br.readLine()) != null) {
                //[T][X] read book 
                if (line.startsWith("[T]")) {
                    result.addTask(new ToDo(
                        line.substring(7)),
                        storage,
                        true
                        );
                    if (line.charAt(4) == 'X') {
                        result.get(result.size() - 1).markAsDone();
                    }
                //[D][ ] return book (by: Sunday)
                } else if (line.startsWith("[D]")) {
                    int byIndex = line.indexOf("(by: ");
                    result.addTask(new Deadline(
                        line.substring(7, byIndex - 1), 
                        line.substring(byIndex + 5, line.length() - 1)),
                        storage,
                        true
                        );
                    if (line.charAt(4) == 'X') {
                        result.get(result.size() - 1).markAsDone();
                    }
                //[E][ ] project meeting (from: Mon 2pm to: Fri 4pm)
                } else if (line.startsWith("[E]")) {
                    int fromIndex = line.indexOf(" (from: ");
                    int toIndex = line.indexOf(" to: ");
                    result.addTask(new Event(
                        line.substring(7, fromIndex), 
                        line.substring(fromIndex + 8, toIndex),
                        line.substring(toIndex + 5, line.length() - 1)),
                        storage,
                        true
                        );
                    if (line.charAt(4) == 'X') {
                        result.get(result.size() - 1).markAsDone();
                    }
                } 
            }

            br.close();
            return result;

        } catch (IOException e) {
            throw new DukeException("Something went wrong: " + e.getMessage());
        }
    }

    /**
     * Writes data to the file
     * @param newData
     * @throws DukeException
     */
    public void writeData(String newData) throws DukeException {
        BufferedWriter bw;
        try {
            bw = new BufferedWriter(new FileWriter(this.store, true));      
            bw.newLine();
            bw.write(newData);
            bw.close();
        } catch (IOException e) {
            throw new DukeException("Something went wrong: " + e.getMessage());
        }
    }

    /**
     * Deletes a line from the file
     * @param lineNumber
     * @throws DukeException
     */
    public void deleteLine(int lineNumber) throws DukeException {
        List<String> lines = new ArrayList<>();
        BufferedReader br = null;
        BufferedWriter bw = null;

        try {
            // Read file into list
            br = new BufferedReader(new FileReader(this.store));
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
            br.close();

            // Remove the line
            if (lineNumber < 1 || lineNumber > lines.size()) {
                throw new DukeException("Invalid line number");
            }
            lines.remove(lineNumber);

            // Write list back to file
            bw = new BufferedWriter(new FileWriter(store));
            for (int i = 0; i < lines.size(); i++) {
                bw.write(lines.get(i));
                if (i < lines.size() - 1) {
                    bw.newLine();
                }   
            }           
            bw.close();
        } catch (IOException e) {
            throw new DukeException("Something went wrong: " + e.getMessage());
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
                if (bw != null) {
                    bw.close();
                }
            } catch (IOException e) {
                throw new DukeException("Something went wrong while closing the file: " + e.getMessage());
            }
        }
    }

    /**
     * Replaces a line in the file
     * @param lineNumber
     * @param newData
     * @throws DukeException
     */
    public void replaceLine(int lineNumber, String newData) throws DukeException {
        List<String> lines = new ArrayList<>();
        BufferedReader br = null;
        BufferedWriter bw = null;

        try {
            // Read file into list
            br = new BufferedReader(new FileReader(this.store));
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
            br.close();

            // Replace the line
            if (lineNumber < 1 || lineNumber > lines.size()) {
                throw new DukeException("Invalid line number");
            }
            lines.set(lineNumber, newData);

            // Write list back to file
            bw = new BufferedWriter(new FileWriter(store));
            for (int i = 0; i < lines.size(); i++) {
                bw.write(lines.get(i));
                if (i < lines.size() - 1) {
                    bw.newLine();
                }   
            }           
            bw.close();
        } catch (IOException e) {
            throw new DukeException("Something went wrong: " + e.getMessage());
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
                if (bw != null) {
                    bw.close();
                }
            } catch (IOException e) {
                throw new DukeException("Something went wrong while closing the file: " + e.getMessage());
            }
        }
    }
}