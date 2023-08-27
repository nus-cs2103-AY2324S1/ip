import java.util.*;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;

public class Bob {
    private static ArrayList<Task> lst = new ArrayList<>();
    private static final String dataPath = "./data/bob.txt";

    /**
     * Formats string into nice display
     * @param strArray contains strings to be displayed on different lines
     * @return formatted string
     */
    private static String stringFormat(String[] strArray) {
        String content = "";
        for (String s : strArray) {
            content += "\t " + s + "\n";
        }
        return   "\t_______________________________________________\n"
                + content
                + "\t_______________________________________________";
    }

    private static String greet() {
        return stringFormat(new String[]{"Hi there! I'm Bob", "How can I help?"});
    }

    private static String exit() {
        return stringFormat(new String[]{"See you soon!"});
    }

    private static String echo(String input) {
        return stringFormat(new String[]{input});
    }


    /**
     * Generates the appropriate type of Task based on user input
     * Throws exceptions due to incorrect user input
     * @param description is of the form e.g. "event read /from 2pm /to 4pm"
     * @return the relevant Task
     * @throws WrongInputException for unrecognised input.
     * @throws MissingTaskException when task name is missing.
     * @throws MissingDeadlineException when duedate of Deadline is missing.
     * @throws MissingEventDatesException when start and end of Event is missing.
     */
    private static Task generateTask(String description)
            throws WrongInputException, MissingTaskException, MissingDeadlineException, MissingEventDatesException {
        // Split by the first " " into type, and task details
        String[] task = description.split(" ", 2);
        TaskType taskType;
        try {
            taskType = Enum.valueOf(TaskType.class, task[0]);
        } catch (Exception e) {
            throw new WrongInputException();
        }

        if (task.length == 1) {
            throw new MissingTaskException();
        }

        String taskDetails = task[1];

        if (taskType.equals(TaskType.deadline)) {
            return new Deadline(taskDetails);
        } else if (taskType.equals(TaskType.event)) {
            return new Event(taskDetails);
        } else {
            return new Todo(taskDetails);
        }
    }

    /**
     * Adds a Task to lst. Writes modified lst to bob.txt.
     * Handles exceptions that occur due to wrong input/ missing requirements
     * @param description is of the form e.g. "event read /from 2pm /to 4pm"
     * @return message for adding a Task
     */
    private static String addToList(String description) {
        try {
            Task taskObj = generateTask(description);
            lst.add(taskObj);
            writeFile();
            return stringFormat(new String[]{"New task added: ", "\t" + taskObj.toString(),
                    "You now have " + lst.size() + (lst.size() == 1 ? " task!" : " tasks!")});
        } catch (WrongInputException e) {
            return stringFormat(new String[]{e.message});
        } catch (MissingTaskException e) {
            return stringFormat(new String[]{e.message});
        } catch (MissingDeadlineException e) {
            return stringFormat(new String[]{e.message});
        } catch (MissingEventDatesException e) {
            return stringFormat(new String[]{e.message});
        } catch (IOException e) {
            return stringFormat(new String[]{"Error in data saving."});
        }
    }

    /**
     * Prints out the Tasks on lst.
     * @return display of lst
     */
    private static String displayList() {
        String[] tasks = new String[lst.size() + 1];
        if (lst.isEmpty()) {
            tasks[0] = "You currently have no tasks.";
        } else {
            tasks[0] = "Here are your tasks!";
        }

        for (int i = 0; i < lst.size(); i++) {
            tasks[i + 1] = (i + 1) + ". " + lst.get(i).toString();
        }
        return stringFormat(tasks);
    }

    /**
     * Marks Task as done or undone at a specified index. Writes modified lst to bob.txt.
     * @param index of Task to be marked
     * @param doneOrNot states whether the Task is done or not
     * @return message for marking a Task
     */
    private static String markDoneOrNot(int index, boolean doneOrNot) {
        lst.get(index - 1).SetDoneOrNot(doneOrNot);
        String statement = doneOrNot ? "Nice! You completed a task!" : "... This is now undone.";
        try {
            writeFile();
            return stringFormat(new String[]{statement, "\t" + lst.get(index - 1).toString()});
        } catch (IOException e) {
            return stringFormat(new String[]{"Error in data saving."});
        }
    }

    /**
     * Deletes Task at specified index from list. Writes modified lst to bob.txt.
     * @param index of Task to be deleted
     * @return message for deleting a Task
     */
    private static String deleteTask(int index) {
        String taskStr = lst.get(index - 1).toString();
        lst.remove(index - 1);
        try {
            writeFile();
            return stringFormat(new String[]{"I've removed this task from list: ", "\t" + taskStr,
                    "You now have " + lst.size() + (lst.size() == 1 ? " task!" : " tasks!")});
        } catch (IOException e) {
            return stringFormat(new String[]{"Error in data saving."});
        }
    }

    /**
     * If bob.txt exists, read data from it. If data is not in correct form, delete file and create empty file.
     * If bob.txt does not exist, create empty file.
     * @throws IOException
     */
    private static void readFile() throws IOException {
        File file = new File(dataPath);

        if (file.createNewFile()) { // if file do not exist
            // do nothing, as a new file is created. list remains empty
        } else {
            // read from file
            Scanner sc = new Scanner(file);
            try {
                while (sc.hasNext()) {
                    lst.add(Task.parse(sc.nextLine()));
                }
            } catch (IndexOutOfBoundsException e) { // file corrupted
                file.delete();
                file.createNewFile();
            }
        }
    }

    /**
     * Writes a new line to bob.txt for each Task in lst.
     * Called whenever an operation that alters lst is implemented.
     * @throws IOException
     */
    private static void writeFile() throws IOException {
        FileWriter fw = new FileWriter(dataPath, false);
        String lstString = "";
        for (Task tsk : lst) {
            lstString += tsk.toTxt() + "\n";
        }
        fw.write(lstString);
        fw.close();
    }

    public static void main(String[] args) {
        try {
            readFile();
        } catch (IOException e) {
            System.out.println(stringFormat(new String[]{"Error in data saving."}));
        }

        System.out.println(greet());

        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                String nextLine = sc.nextLine();

                if (nextLine.equals("list")) {
                    System.out.println(displayList());

                } else if (nextLine.contains("mark")) { // if command is to mark or unmark
                    String[] markIndex = nextLine.split(" ");
                    if (markIndex.length == 1) {
                        throw new MissingIndexException();
                    }
                    int index = 0;
                    try {
                        index = Integer.parseInt(markIndex[1]);
                    } catch (NumberFormatException e) {
                        throw new MissingIndexException();
                    }
                    boolean doneOrNot = true;
                    if (nextLine.contains("unmark")) {
                        doneOrNot = false;
                    }
                    System.out.println(markDoneOrNot(index, doneOrNot));

                } else if (nextLine.contains("delete")) {
                    String[] deleteIndex = nextLine.split(" ");
                    if (deleteIndex.length == 1) {
                        throw new MissingIndexException();
                    }
                    int index = 0;
                    try {
                        index = Integer.parseInt(deleteIndex[1]);
                    } catch (NumberFormatException e) {
                        throw new MissingIndexException();
                    }
                    System.out.println(deleteTask(index));
                } else if (nextLine.equals("bye")) {
                    System.out.println(exit());
                    sc.close();
                    break;

                } else { // if command is to add tasks
                    if (nextLine.isEmpty()) {
                        throw new NoSuchElementException();
                    }
                    System.out.println(addToList(nextLine));
                }
            }
            catch (NoSuchElementException e) {
                System.out.println(stringFormat(new String[]{"Write something!"}));
            } catch (MissingIndexException e) {
                System.out.println(stringFormat(new String[]{e.message}));
            } catch (IndexOutOfBoundsException e) {
                System.out.println(stringFormat(new String[]{"Index provided is wrong!"}));
            }
        }
    }
}
