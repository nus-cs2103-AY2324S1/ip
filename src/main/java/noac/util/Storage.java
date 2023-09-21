package noac.util;

import noac.task.Deadline;
import noac.task.Event;
import noac.task.Task;
import noac.task.Todo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.time.format.DateTimeParseException;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * For interactions with the save file such as loading and saving.
 */
public class Storage {

    private String filePath;

    /**
     * Initialise the Storage object with the file path.
     *
     * @param filePath The file path where the save file should be.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Load the tasks in the save file or created one if it does not exist.
     *
     * @return The loaded tasks.
     * @throws NoacException For any errors that needs to be displayed to user.
     */
    public ArrayList<Task> load() throws NoacException {

        ArrayList<Task> returnList = new ArrayList<>();

        try {
            File file = new File(this.filePath);
            file.getParentFile().mkdirs();

            if (file.createNewFile()) {
                return returnList;
            }

            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String[] fileLineInput = scanner.nextLine().split("\\|");
                String taskType = fileLineInput[0];

                returnList = handleDifferentTask(taskType, fileLineInput, returnList);
            }

        } catch (IOException e) {
            throw new NoacException("OOPS!!! Corrupted Save file");
        }

        return returnList;
    }


    /**
     * Write the task list to save file.
     *
     * @param taskList The task list to be written.
     */
    public void save(TaskList taskList) {
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for(int i = 0; i < taskList.size(); i++) {
                bufferedWriter.write(taskList.getTask(i).printToFile() + "\n");
            }

            bufferedWriter.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private ArrayList<Task> handleDifferentTask(String taskType, String[] fileLineInput, ArrayList<Task> returnList) throws NoacException {
        switch (taskType) {
            case "T":
                returnList = handleCaseTodo(fileLineInput, returnList);
                break;
            case "D":
                returnList = handleCaseDeadline(fileLineInput, returnList);
                break;
            case "E":
                returnList = handleCaseEvent(fileLineInput, returnList);
                break;
            default:
                throw new NoacException("OOPS!!! Corrupted Save file");
        }
        return returnList;
    }


    private ArrayList<Task> handleCaseTodo(String[] fileLineInput, ArrayList<Task> returnList) throws NoacException{
        if (fileLineInput.length != 4) {
            throw new NoacException("OOPS!!! Corrupted Save file");
        }

        Todo todo = new Todo(fileLineInput[2]);
        if (fileLineInput[1].equals("1")) {
            todo.markAsDone();
        }

        String[] tags = fileLineInput[3].split(" ");

        for (int i = 0; i < tags.length; i++) {
            todo.addTag(tags[i]);
        }

        returnList.add(todo);

        return returnList;
    }

    private ArrayList<Task> handleCaseDeadline(String[] fileLineInput, ArrayList<Task> returnList) throws NoacException{
        if (fileLineInput.length != 5) {
            throw new NoacException("OOPS!!! Corrupted Save file");
        }

        Deadline deadline;
        try {
            deadline = new Deadline(fileLineInput[2], Parser.parseDate(fileLineInput[3]));
        } catch (DateTimeParseException e) {
            throw new NoacException("OOPS!!! Corrupted Save file");
        }

        if (fileLineInput[1].equals("1")) {
            deadline.markAsDone();
        }

        String[] tags = fileLineInput[4].split(" ");

        for (int i = 0; i < tags.length; i++) {
            deadline.addTag(tags[i]);
        }

        returnList.add(deadline);

        return returnList;
    }

    private ArrayList<Task> handleCaseEvent(String[] fileLineInput, ArrayList<Task> returnList) throws NoacException{
        if (fileLineInput.length != 6) {
            throw new NoacException("OOPS!!! Corrupted Save file");
        }

        Event event;
        try{
            event = new Event(fileLineInput[2],Parser.parseDate(fileLineInput[3]) , Parser.parseDate(fileLineInput[4]));
        } catch (DateTimeParseException e) {
            throw new NoacException("OOPS!!! Corrupted Save file");
        }

        if (fileLineInput[1].equals("1")) {
            event.markAsDone();
        }

        String[] tags = fileLineInput[5].split(" ");

        for (int i = 0; i < tags.length; i++) {
            event.addTag(tags[i]);
        }

        returnList.add(event);

        return returnList;
    }

}
