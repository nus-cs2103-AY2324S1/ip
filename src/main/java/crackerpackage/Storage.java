package crackerpackage;

import java.io.*;
import java.nio.file.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import crackerpackage.tasks.Deadline;
import crackerpackage.tasks.Event;
import crackerpackage.tasks.Task;
import uicomponents.Parser;

/**
 * A Storage that stores tasks for the bot.
 *
 * @author Anton Tan Hong Zhi
 */
public class Storage {

    final private File ARCHIVE = new File("./data/archive.txt");
    final private File DATA_DIRECTORY = new File("./data");
    private File file;

    /**
     * Creates a Storage object.
     *
     * @param fileString The path to the file
     */

    public Storage(String fileString) {
        this.file = new File(fileString);
    }

    /**
     * Saves the content in the list to a file.
     *
     * @param list List of tasks to store
     * @throws IOException
     */
    public void save(TodoList list) throws IOException {

        FileWriter writer = null;

        if (!this.file.exists() && !DATA_DIRECTORY.exists()) {
            Path path = FileSystems.getDefault().getPath("data");
            Files.createDirectory(path);
            this.file.createNewFile();
        } else if (!this.file.exists()) {
            this.file.createNewFile();
        }
        writer = new FileWriter(file);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        for (int i = 0; i < list.size(); i++) {
            StringBuilder taskString = new StringBuilder();
            Task task = list.getTask(i);
            assert task != null;
            taskString.append(list.getTaskString(i).charAt(1));
            taskString.append('|');
            taskString.append(task.getStatus());
            taskString.append('|');
            taskString.append(task.getDesc());
            taskString.append('|');
            if (task instanceof Event) {
                taskString.append(LocalDate.parse(((Event) task).getStart(), formatter));
                taskString.append('|');
                taskString.append(LocalDate.parse(((Event) task).getEnd(), formatter));
                taskString.append('|');
            } else if (task instanceof Deadline) {
                taskString.append(LocalDate.parse(((Deadline) task).getDeadline(), formatter));
                taskString.append('|');
            }
            writer.write(taskString + System.lineSeparator());
        }
        writer.close();
    }


    /**
     * Returns a TodoList based on the contents of the file stored.
     *
     * @return a TodoList based on the contents of the file stored
     */
    public TodoList load() {

        TodoList list = new TodoList();
        Scanner sc = null;
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            return list;
        }
        while (sc.hasNext()) {
            String taskString = sc.nextLine();
            String[] arr = taskString.split("\\|");

            try {
                Task task = Parser.parseTask(arr);
                if (Boolean.parseBoolean(arr[1])) {
                    task.markDone();
                }
                list.store(task);
            } catch (Exception e) {
                System.out.println(e);
                System.out.println("something bad when loading");
            }
        }
        return list;
    }

    /**
     * Puts the task in an archive
     *
     * @param task the task to be put in archive
     */
    public void archive(String task) {
        if (!this.ARCHIVE.exists() && !DATA_DIRECTORY.exists()) {
            Path path = FileSystems.getDefault().getPath("data");
            try {
                Files.createDirectory(path);
                this.ARCHIVE.createNewFile();
            } catch(IOException e){
                //should not happen
                System.out.println(e);
                System.out.println("Error creating archive");
            }
        } else if (!this.ARCHIVE.exists()) {
            try {
                this.ARCHIVE.createNewFile();
            } catch(IOException e) {
                //should not happen
                System.out.println(e);
                System.out.println("Error creating archive");
            }
        }
        try {
            FileWriter fr = new FileWriter(ARCHIVE, true);
            BufferedWriter br = new BufferedWriter(fr);
            br.write(task + "\n");
            br.close();
            fr.close();
        } catch (IOException e){
            //should not happen
            System.out.println("Error creating archive");
        }
    }

}
