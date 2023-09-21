package dukepackage;

import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import java.util.ArrayList;


/**
 * The Storage class is responsible for managing the storage of tasks in a task list.
 */
public class Storage {

    /**
     * The list of tasks.
     */
    protected ArrayList<Task> taskList;
    protected final String MATCHING_STRING = "     Here are the matching tasks in your list:\n";
    protected final String filePathRelativeFromJar = "../data/duke.txt";

    /**
     * Constructs a Storage object with an empty task list.
     */
    public Storage() {
        this.taskList = new ArrayList<Task>();
    }

    /**
     * Retrieves the task list.
     *
     * @return The task list.
     */
    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    /**
     * Adds a task to the task list.
     *
     * @param t The task to be added.
     */
    public void addList(Task t) {
        this.taskList.add(t);
    }

    /**
     * Deletes a task from the task list.
     *
     * @param id The index of the task to be deleted.
     */
    public String deleteTask(int id) {
        String result = "";
        result += ("     Noted. I've removed this task:");
        Task t = this.taskList.get(id);
        result += t.printMarking(false);
        this.taskList.remove(id);
        int size = this.taskList.size();
        result += String.format("\n     Now you have %d tasks in the list.\n", size);
        return result;
    }

    /**
     * Writes the task list to a file.
     */
    public void writeTasksToFile() {
        try {
            FileWriter fileWriter = new FileWriter(filePathRelativeFromJar);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (Task tasking : this.taskList) {
                // format the string
                String formattedString = "";
                Integer priority = tasking.isDone ? 1 : 0;
                switch (tasking.type) {
                    case TODO:
                        formattedString = String.format("%c|%d|%s", 'T', priority, tasking.description);
                        break;
                    case DEADLINE:
                        String start = tasking.startTime.toString().replace("T", " ");
                        formattedString = String.format("%c|%d|%s|%s", 'D', priority, tasking.description, start);
                        break;
                    case EVENT:
                        start = tasking.startTime.toString().replace("T", " ");
                        String end = tasking.endTime.toString().replace("T", " ");
                        formattedString = String.format("%c|%d|%s|%s|%s",
                                'E', priority, tasking.description, start, end);
                        break;
                }
                bufferedWriter.write(formattedString);
                bufferedWriter.newLine(); // Move to the next line
            }
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Initializes a Task object from an array of values.
     *
     * @param values An array of values representing task information.
     * @return A Task object initialized from the provided values.
     */
    public Task initTaskFromLine(String[] values) {
        TaskType type = Objects.equals(values[0], "T")
                ? TaskType.TODO
                : Objects.equals(values[0], "D")
                ? TaskType.DEADLINE
                : TaskType.EVENT;
        int valuesLength = values.length;
        String start = valuesLength >= 4 ? values[3] : "";
        String end = valuesLength >= 5 ? values[4] : "";
        return new Task(values[2], type, start, end);
    }

    /**
     * Loads the task list from a file.
     */
    public void loadListFromFile() {
        try {
            File file = new File(filePathRelativeFromJar);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    System.out.printf("Error creating file: ", e);
                }
            }
            FileReader fileReader = new FileReader(file); // append mode
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                // Assuming your line contains comma-separated values
                String[] values = line.split("\\|");
                Task obj = initTaskFromLine(values); // Instantiate with appropriate arguments
                obj.setStatus(!(values[1].equals("0")));
                // Store the object in your storage instance
                addList(obj);
//                bufferedReader.close();
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Appends a task's information to a StringBuilder.
     *
     * @param result The StringBuilder to which the task information is appended.
     * @param index  The index of the task.
     * @param t      The Task object containing task information.
     * @return The updated StringBuilder containing the appended task information.
     */
    public StringBuilder appendTaskToString(StringBuilder result, int index, Task t) {
        result.append(String.format("     %d.[%s][%s] %s",
                index, t.getTypeIcon(), t.getStatusIcon(), t.description));
        if (!Objects.isNull(t.startTime) && !Objects.isNull(t.endTime)) {
            String start = t.startTime.toString().replace("T", " ");
            String end = t.endTime.toString().replace("T", " ");
            result.append(String.format(" (from: %s to: %s)%n", start, end));
        } else if (!Objects.isNull(t.startTime)) {
            String start = t.startTime.toString().replace("T", " ");
            result.append(String.format(" (by: %s)%n", start));
        } else {
            result.append("\n");
        }
        return result;
    }

    /**
     * Prints the task list.
     *
     * @return A string representation of the entire task list.
     */
    public String printTaskList() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < this.taskList.size(); i++) {
            // taskList is 0 indexed while list in GUI is 1-indexed
            int index = i + 1;
            Task t = this.taskList.get(i);
            result = appendTaskToString(result, index, t);
        }
        return result.toString();
    }

    /**
     * Prints a list of matching tasks based on a search string.
     *
     * @param toFindString The search string used to filter matching tasks.
     * @return A string representation of the matching tasks.
     */
    public String printMatchingList(String toFindString) {
        StringBuilder result = new StringBuilder();
        result.append(MATCHING_STRING);
        for (int i = 0; i < this.taskList.size(); i++) {
            int index = i + 1;
            Task t = this.taskList.get(i);
            if (t.getDescription().contains(toFindString)) {
                result = appendTaskToString(result, index, t);
            }
        }
        return result.toString();
    }


    /**
     * Prints the marking for a task.
     *
     * @param i The index of the task.
     * @return The marking information.
     */
    public String printTaskMarking(int i) {
        String result = "";
        try {
            Task t = this.taskList.get(i);
            result += t.printMarking(true);
            System.out.println();
        } catch (Exception e) {
            result += ("     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        return result;
    }

    /**
     * Changes the marking of a task.
     *
     * @param i      The index of the task.
     * @param isDone The new marking status.
     * @throws DukeException If the task index is invalid.
     */
    public void changeTaskMarking(int i, boolean isDone) throws DukeException {
        try {
            Task t = this.taskList.get(i);
            t.setStatus(isDone);
        } catch (Exception e) {
            throw new DukeException("     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Prints the entry of a task.
     *
     * @param t The task.
     * @return The task entry information.
     */
    public String printTaskEntry(Task t) {
        String result = "";
        result += t.printDescription();
        int size = this.taskList.size();
        result += String.format("\n     Now you have %d tasks in the list.\n", size);
        return result;
    }
}