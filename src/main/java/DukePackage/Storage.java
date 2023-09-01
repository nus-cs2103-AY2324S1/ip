package DukePackage;

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
    protected final String MATCHING_STRING = "     Here are the matching tasks in your list:";

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
    public void deleteTask(int id) {
        System.out.println("     Noted. I've removed this task:");
        Task t = this.taskList.get(id);
        t.printMarking(false);
        this.taskList.remove(id);
        int size = this.taskList.size();
        System.out.printf("\n     Now you have %d tasks in the list.\n", size);
    }

    /**
     * Writes the task list to a file.
     */
    public void writeTasksToFile() {
        try {
            FileWriter fileWriter = new FileWriter("data/duke.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (Task tasking : this.taskList) {
                // format the string
                String formattedString = "";
                Integer priority = tasking.isDone
                        ? 1
                        : 0;
                switch (tasking.type) {
                    case TODO:
                        formattedString = String.format("%c|%d|%s",
                                'T', priority, tasking.description);
                        break;
                    case DEADLINE:
                        formattedString = String.format("%c|%d|%s|%s",
                                'D', priority, tasking.description,
                                tasking.startTime.toString().replace("T", " "));
                        break;
                    case EVENT:
                        formattedString = String.format("%c|%d|%s|%s|%s",
                                'E', priority, tasking.description,
                                tasking.startTime.toString().replace("T", " "),
                                tasking.endTime.toString().replace("T", " "));
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
     * Loads the task list from a file.
     */
    public void loadListFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("data/duke.txt"))) {
            File file = new File("data/duke.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            FileReader fileReader = new FileReader(file); // append mode
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = reader.readLine()) != null) {
                // Assuming your line contains comma-separated values
                String[] values = line.split("\\|");
                // Create your Java object based on the parsed values
                TaskType type = Objects.equals(values[0], "T")
                        ? TaskType.TODO
                        : Objects.equals(values[0], "D")
                        ? TaskType.DEADLINE
                        : TaskType.EVENT;
                String start = "", end = "";
                try {
                    start = values[3];
                } catch (Exception e) {
                    start = "";
                }
                try {
                    end = values[4];
                } catch (Exception e) {
                    end = "";
                }
                Task obj = new Task(values[2], type, start, end); // Instantiate with appropriate arguments
                obj.setStatus(!Objects.equals(values[1], "0"));
                // Store the object in your storage instance
                addList(obj);
                bufferedReader.close();
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Prints the task list.
     */
    public void printTaskList() {
        for (int i = 0; i < this.taskList.size(); i++) {
            int index = i + 1;
            Task t = this.taskList.get(i);
            System.out.printf("     %d.[%s][%s] %s", index, t.getTypeIcon(), t.getStatusIcon(), t.description);
            if (!Objects.isNull(t.startTime) && !Objects.isNull(t.endTime)) {
                System.out.printf(" (from: %s to: %s)%n", t.startTime.toString().replace("T", " "), t.endTime.toString().replace("T", " "));
            } else if (!Objects.isNull(t.startTime)) {
                System.out.printf(" (by: %s)%n", t.startTime.toString().replace("T", " "));
            } else {
                System.out.print("\n");
            }
        }
    }

    /**
     * Prints the marking for a task.
     *
     * @param i The index of the task.
     * @throws DukeException If the task index is invalid.
     */
    public void printTaskMarking(int i) throws DukeException {
        try {
            Task t = this.taskList.get(i);
            t.printMarking(true);
            System.out.println();
        } catch (Exception e) {
            throw new DukeException("     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
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
     */
    public void printTaskEntry(Task t) {
        t.printDescription();
        int size = this.taskList.size();
        System.out.printf("\n     Now you have %d tasks in the list.\n", size);
    }

    public void printMatchingList(String toFindString) {
        System.out.println(MATCHING_STRING);
        for (int i = 0; i < this.taskList.size(); i++) {
            int index = i + 1;
            Task t = this.taskList.get(i);
            if (t.getDescription().contains(toFindString)) {
                System.out.printf("     %d.[%s][%s] %s", index, t.getTypeIcon(), t.getStatusIcon(), t.description);
                if (!Objects.isNull(t.startTime) && !Objects.isNull(t.endTime)) {
                    System.out.printf(" (from: %s to: %s)%n", t.startTime.toString().replace("T", " "), t.endTime.toString().replace("T", " "));
                } else if (!Objects.isNull(t.startTime)) {
                    System.out.printf(" (by: %s)%n", t.startTime.toString().replace("T", " "));
                } else {
                    System.out.print("\n");
                }
            }
        }
    }
}