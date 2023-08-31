import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

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

    public ArrayList<Task> retrieveTasks(File file) throws FileNotFoundException {
        //TODO read the contents of the file and retrieve them
        ArrayList<Task> tasks = new ArrayList<>();

        Scanner sc = new Scanner(file);

        while (sc.hasNextLine()) {
            final String task = sc.nextLine();
            //TODO process content from line into a Task

            /*
            Format:
            T | 1 | read book
            D | 0 | return book | June 6th
            E | 0 | project meeting | Aug 6th 2-4pm
            T | 1 | join sports club
                */
            char type = task.charAt(0);
            String[] descriptions = task.split("::");

            switch (type) {
                case 'T':
                    tasks.add(new ToDo(descriptions[2], descriptions[1].matches("1")));
                    break;

                case 'D':
                    //Deadline
                    tasks.add(new Deadline(descriptions[2], descriptions[3], descriptions[1].matches("1")));
                    break;

                case 'E':
                    //Event
                    tasks.add(new Event(descriptions[2], descriptions[3], descriptions[1].matches("1")));
                    break;

                default:
                    System.out.println("Wrong file format");
            }
        }

        return tasks;
    }
}
