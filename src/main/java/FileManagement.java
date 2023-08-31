import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileManagement {

    /**
     * Adds file into the directory specified.
     *
     * @param directory is the directory that will be created in the C drive.
     * @param file is the file to be added to the directory.
     * @return whether file has been successfully added into the hard disk.
     */
    public boolean addFile(File directory, File file) {
        try {
            if (directory.mkdir()) {
                file.createNewFile();

                return true;
            }
        } catch (IOException e) {
            System.out.println("An error occurred while trying to create the file.");
        }

        return false;
    }

    public boolean checkFileExists(File file) {
        return file.exists();
    }

    public ArrayList<Task> retrieveData(File file) {
        //TODO read the contents of the file and retrieve them
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {

                String task = sc.nextLine();
                //TODO process content from line into a Task
                
            }

        } catch (FileNotFoundException e) {
            System.out.println("Unable to find file.");
        }

    }
}
