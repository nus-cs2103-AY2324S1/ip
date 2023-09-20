package alyssa;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import alyssa.exceptions.AlyssaArgumentException;
import alyssa.exceptions.AlyssaException;
/**
 * This class deals with loading tasks from ./data/alyssa.txt and saving tasks in one file.
 */
public class Storage {
    /** The relative path to storage file. Usually ./data/alyssa.txt. */
    private String filePath;
    /** The relative path of the folder which the storage file is in. Usually ./data. */
    private String dirPath;
    /** The storage file. */
    private File saveFile;
    private Parser parser;
    /**
     * Constructor method for the alyssa.Storage class.
     * @param filePath The relative path of alyssa.txt.
     */
    public Storage(String filePath, String dirPath) {
        this.filePath = filePath;
        this.dirPath = dirPath;
        this.parser = new Parser();
    }
    /**
     * Checks for the presence of ./data and ./data/alyssa.txt, and adds them if absent.
     */
    private void checkSaveFile() {
        File dataDir = new File(dirPath);
        if (!dataDir.exists()) {
            //System.out.println("data directory doesn't exist");
            dataDir.mkdir();
        }
        saveFile = new File(filePath);
        if (!saveFile.exists()) {
            //System.out.println("savefile doesn't exist");
            try {
                saveFile.createNewFile();
                saveFile.setReadable(true);
                saveFile.setWritable(true);
            } catch (IOException e) {
                System.out.println("Something went wrong when creating a new alyssa.txt");
            }
        }
    }

    /**
     * Imports tasks from ./data/alyssa.txt if there are any.
     * @return a list of Tasks, used to instantiate the TaskList object.
     */
    protected List<Task> loadTasks() {
        List<Task> taskList = new ArrayList<>();
        checkSaveFile();
        try {
            Scanner fileScanner = new Scanner(saveFile);
            //each line read by fileScanner is a task!
            while (fileScanner.hasNextLine()) {
                String nextTask = fileScanner.nextLine();
                String[] parsedTask = parser.parseStoredTask(nextTask);
                String typeOfTask = parsedTask[0];
                String taskSymbol = parsedTask[1];
                String desc = parsedTask[2];
                switch (typeOfTask) {
                case "T":
                    Todo newTodo = new Todo(desc);
                    if (taskSymbol.equals("X")) {
                        newTodo.markAsDone();
                    }
                    taskList.add(newTodo);
                    break;
                case "D":
                    LocalDate by;
                    try {
                        by = LocalDate.parse(parsedTask[3]);
                    } catch (DateTimeParseException e) {
                        throw new AlyssaArgumentException("Invalid by. Syntax: yyyy-mm-dd");
                    }
                    Deadline newDeadline = new Deadline(desc, by);
                    if (taskSymbol.equals("X")) {
                        newDeadline.markAsDone();
                    }
                    taskList.add(newDeadline);
                    break;
                case "E":
                    Event newEvent = new Event(desc, parsedTask[3], parsedTask[4]);
                    if (taskSymbol.equals("X")) {
                        newEvent.markAsDone();
                    }
                    taskList.add(newEvent);
                    break;
                default:
                    throw new AlyssaException("Corrupted alyssa.txt");
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Weird... you didn't have a saved alyssa.txt file even after we added it for you");
        } catch (AlyssaException e) {
            System.out.println(e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Corrupted alyssa.txt");
        }
        return taskList;
    }
    /**
     * Saves existing tasks to ./data/alyssa.txt.
     * @throws IOException when file or path cannot be found, or file is a directory, or file cannot be opened.
     */
    protected void saveTasks(TaskList lst) throws IOException {
        List<Task> taskList = lst.getTaskList();
        FileWriter fw = new FileWriter(filePath);
        for (Task task : taskList) {
            String entry = "";
            if (task instanceof Todo) {
                Todo todo = (Todo) task;
                entry = "T ~ ";
                entry += todo.getStatusIcon() + " ~ ";
                entry += todo.getDescription();
            } else if (task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                entry = "D ~ ";
                entry += deadline.getStatusIcon() + " ~ ";
                entry += deadline.getDescription() + " ~ ";
                entry += deadline.getByForStorage();
            } else if (task instanceof Event) {
                Event event = (Event) task;
                entry = "E ~ ";
                entry += event.getStatusIcon() + " ~ ";
                entry += event.getDescription() + " ~ ";
                entry += event.getFrom() + " ~ ";
                entry += event.getTo();
            } else {
                throw new AlyssaException("Oops! Something went wrong.");
            }
            entry += System.lineSeparator();
            fw.write(entry);
        }
        fw.close();
    }
}
