package bob;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import bob.exceptions.BobException;
import bob.tasks.Deadline;
import bob.tasks.Event;
import bob.tasks.Task;
import bob.tasks.Todo;

/**
 * Represents a storage that deals with loading tasks from the file and saving tasks in the file
 */
public class Storage {

    private String filePath;

    /**
     * Constructor for Storage class.
     *
     * @param filePath the path of the file to store.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads file content and re-initialises task if file exists, else creates new file.
     *
     * @return the arraylist of tasks that were recorded in the existing file, else an empty list.
     * @throws BobException
     */
    public ArrayList<Task> load() throws BobException {
        ArrayList<Task> list = new ArrayList<Task>();
        File file = new File(filePath);
        file.getParentFile().mkdirs(); //creates parent directories if not existing

        list = createFile(list, file);

        return list;
    }

    private ArrayList<Task> createFile(ArrayList<Task> list, File file) {
        //creates file if file doesn't exist, else reads file and re-initialises tasks
        try {
            if (file.createNewFile()) {
                //file is created
                System.out.println("Hello! I'm Bob");
                System.out.println("What can I do for you?");
                assert list.size() == 0;
            } else {
                //file exists
                //reads the file and re-initiates the list of tasks
                Scanner s = new Scanner(file);
                String currInput = "";
                int index = 0;

                while (s.hasNext()) {
                    currInput = s.nextLine();
                    readFile(list, currInput);
                    char[] charArray = currInput.toCharArray();

                    if (charArray[2] == '1') {
                        list.get(index).markAsDone();
                    }

                    index++;
                }
                return list;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return new ArrayList<Task>();
    }

    private void readFile(ArrayList<Task> list, String input) {
        char[] charArray = input.toCharArray();
        String taskName = "";
        String dateOne = ""; //would be either a due date (deadline) or a start date (event)
        String dateTwo = ""; //would be an end date (event)
        int firstDate = charArray.length;
        int secondDate = charArray.length;

        for (int i = 4; i < charArray.length; i++) {
            if (charArray[i] != ',' && i < firstDate) {
                taskName += charArray[i];
            }

            if (charArray[i] == ',' && firstDate == secondDate) {
                firstDate = i;
            } else if (charArray[i] == ',' && firstDate != secondDate) {
                secondDate = i;
            }

            if (i > firstDate && i < secondDate) {
                dateOne += charArray[i];
            } else if (i > secondDate) {
                dateTwo += charArray[i];
            }
        }

        if (charArray[0] == 'T') {
            list.add(new Todo(taskName));
        } else if (charArray[0] == 'D') {
            LocalDate d1 = LocalDate.parse(dateOne);
            list.add(new Deadline(taskName, d1));
        } else if (charArray[0] == 'E') {
            LocalDate d1 = LocalDate.parse(dateOne);
            LocalDate d2 = LocalDate.parse(dateTwo);
            list.add(new Event(taskName, d1, d2));
        }
    }

    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    private static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }

    /**
     * Saves the new list into the file.
     *
     * @param list the TaskList containing the tasks to be saved in the file.
     */
    public void saveNewList(TaskList list) {
        //clears file
        try {
            writeToFile(filePath, "");
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }

        //adds list of task into file
        for (int i = 0; i < list.size(); i++) {
            try {
                if (list.get(i) instanceof Todo) {
                    appendToFile(filePath, list.get(i).getTaskType() + "," + list.get(i).getStatusInt()
                            + "," + list.get(i).getDescription() + System.lineSeparator());
                } else if (list.get(i) instanceof Deadline) {
                    appendToFile(filePath, list.get(i).getTaskType() + "," + list.get(i).getStatusInt()
                            + "," + list.get(i).getDescription() + "," + list.get(i).getBy() + System.lineSeparator());
                } else if (list.get(i) instanceof Event) {
                    appendToFile(filePath, list.get(i).getTaskType() + "," + list.get(i).getStatusInt() + ","
                            + list.get(i).getDescription() + "," + list.get(i).getFrom() + "," + list.get(i).getTo()
                            + System.lineSeparator());
                }

            } catch (IOException e) {
                System.out.println("Something went wrong: " + e.getMessage());
            }
        }
    }
}
