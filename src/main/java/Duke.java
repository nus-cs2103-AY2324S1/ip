import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.BufferedWriter;

//emoticons taken from: https://kaomojikuma.com/ and https://www.emoticonstext.com/

public class Duke {
    private static String line = "--------------------------------------------------------------------";
    private static ArrayList<Task> list = new ArrayList<>();

    public static void newTaskAdded(Task task) {
        System.out.println("(｀･ω･´)ﾉ New task added:\n" + task);
    }

    public static void getNumberOfTasks(ArrayList<Task> list) {
        System.out.println(list.size() == 1
                ? "Now you have " + list.size() + " task in the list!\n" + line
                : "Now you have " + list.size() + " tasks in the list!\n" + line);
    }

    //credit: https://www.freecodecamp.org/news/java-string-to-int-how-to-convert-a-string-to-an-integer/
    private static boolean isNumber(String s) {
        return s != null && s.matches("[0-9.]+");
    }

    public static File createDataFile() throws IOException {
        File dataFile = new File("./data/duke.txt");
        try {
            Path dirPath = Paths.get("./data/");
            if (!Files.exists(dirPath)) {
                Files.createDirectory(dirPath);
            }
            if (!dataFile.exists()) {
                dataFile.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Unable to create file!!");
        }
        return dataFile;
    }

    //called at the beginning of the program
    //creates a new File if file not found
    public static void loadTasks() throws IOException {
        File dataFile = new File("./data/duke.txt");
        if (!dataFile.exists()) {
            dataFile = createDataFile();
        }
        Scanner sc = new Scanner(dataFile);
        ArrayList<Task> data = new ArrayList<>();
        while (sc.hasNextLine()) {
            String task = sc.nextLine();
            if (!task.isBlank()) {
                // | is a special symbol
                String[] taskDetails = task.split(" " + "\\|" + " ");
                String type = taskDetails[0];
                int status = Integer.parseInt(taskDetails[1]);
                String desc = taskDetails[2];
                switch (type) {
                case "T":
                    ToDo toDo = new ToDo(status, desc);
                    data.add(toDo);
                    break;
                case "D":
                    String date = taskDetails[3];
                    Deadline deadline = new Deadline(status, desc, date);
                    data.add(deadline);
                    break;
                case "E":
                    String start = taskDetails[3];
                    String end = taskDetails[4];
                    Event event = new Event(status, desc, start, end);
                    data.add(event);
                    break;
                }
            }
        }
        list = data;
        sc.close();
    }

    //update the changes in list to the text file
    public static void updateFile() throws IOException {
        try {
            //check if file exists, else create
            File dataFile = new File("./data/duke.txt");
            if (!dataFile.exists()) {
                dataFile = createDataFile();
            }

            //create a FileWriter object to write to file. Note that this overwrites the existing data!
            FileWriter file = new FileWriter("./data/duke.txt");
            BufferedWriter writer = new BufferedWriter(file);
            for (int i = 0; i < list.size(); i++) {
                Task task = list.get(i);
                String taskStr = task.convertTask();
                writer.write(taskStr);
                writer.newLine();
                writer.flush();
            }
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void listTasks() {
        if (list.size() == 0) {
            System.out.println("(o´ω`o)ﾉ You have no upcoming tasks!\n" + line);
        } else {
            String result = "";
            for (int i = 0; i < list.size(); i++) {
                int index = i + 1;
                Task task = list.get(i);
                result += index + ". " + task.toString() + "\n";
            }
            System.out.println("(⇀‸↼‶)⊃━☆ﾟ.*･｡ﾟ Here are your tasks for the day:");
            System.out.println(result + line);
        }
    }

    public static boolean deleteTask(int taskID) throws IOException {
        if (!isValidTaskID(taskID)) {
            return false;
        } else {
            Task toRemove = list.get(taskID);
            list.remove(taskID);
            updateFile();
            System.out.println("ଘ(੭ˊᵕˋ)੭ Ok! I've removed this task:");
            System.out.println(toRemove);
            getNumberOfTasks(list);
            return true;
        }
    }

    public static boolean isValidTaskID(int taskID) {
        if (taskID > list.size() - 1 || taskID < 0) {
            System.out.println("(・´з`・) Uh oh... invalid taskID\n" + line);
            return false;
        }
        return true;
    }

    public static void markTask(int taskID) throws IOException {
        list.get(taskID).mark();
        updateFile();
    }

    public static void unmarkTask(int taskID) throws IOException {
        list.get(taskID).unmark();
        updateFile();
    }

    public static boolean isValidCommand(String input) throws InvalidCommandException,
            NoDescException, NoDateException, NoStartException, NoEndException,
            IOException {
        boolean isValid = true;
        String[] inputArr = input.split(" ");
        String command = inputArr[0];
        if (command.equals("list")) {
            listTasks();
        } else if (command.equals("delete")) {
            if (inputArr.length == 1) {
                System.out.println("(・´з`・) Uh oh... please provide a taskID\n" + line);
            } else {
                String strIndex = inputArr[1];
                if (isNumber(strIndex)) {
                    int index = Integer.parseInt(strIndex) - 1; //because index starts from 1
                    isValid = deleteTask(index);
                } else {
                    //case where a number was not provided
                    System.out.println("(・´з`・) Uh oh... invalid taskID\n" + line);
                    isValid = false;
                }
            }
        } else if (command.equals("mark")) {
            if (inputArr.length == 1) {
                System.out.println("(・´з`・) Uh oh... please provide a taskID\n" + line);
            } else {
                String strIndex = inputArr[1];
                if (isNumber(strIndex)) {
                    int index = Integer.parseInt(strIndex) - 1; //because index starts from 1
                    if (isValidTaskID(index)) {
                        markTask(index);
                    } else {
                        isValid = false;
                    }
                } else {
                    System.out.println("(・´з`・) Uh oh... invalid taskID\n" + line);
                    isValid = false;
                }
            }
        } else if (command.equals("unmark")) {
            if (inputArr.length == 1) {
                System.out.println("(・´з`・) Uh oh... please provide a taskID\n" + line);
            } else {
                String strIndex = inputArr[1];
                if (isNumber(strIndex)) {
                    int index = Integer.parseInt(strIndex) - 1; //because index starts from 1
                    if (isValidTaskID(index)) {
                        unmarkTask(index);
                    } else {
                        isValid = false;
                    }
                } else {
                    System.out.println("(・´з`・) Uh oh... invalid taskID\n" + line);
                    isValid = false;
                }
            }
        } else if (command.equals("todo")) {
            isValid = isValidToDo(input);

        } else if (command.equals("deadline")) {
            isValid = isValidDeadline(input);

        } else if (command.equals("event")) {
            isValid = isValidEvent(input);
        } else {
            throw new InvalidCommandException();
        }
        return isValid;
    }

    public static boolean isValidToDo(String input) throws NoDescException, IOException {
        String[] inputArr = input.split(" ", 2);
        if (inputArr.length == 1) {
            throw new NoDescException();
        } else {
            if (inputArr[1].isBlank()) {
                throw new NoDescException();
            } else {
                //0 for unmarked, any other number for marked
                ToDo toDo = new ToDo(0, inputArr[1]);
                list.add(toDo);
                updateFile();
                newTaskAdded(toDo);
                getNumberOfTasks(list);
            }
        }
        return true;
    }

    public static boolean isValidDeadline(String input) throws NoDescException,
            NoDateException, IOException {
        String[] inputArr = input.split(" ", 2);
        if (inputArr.length == 1) {
            throw new NoDescException();
        } else {
            String afterCommand = inputArr[1];
            //now we check whether there is a deadline
            String[] arr = afterCommand.split(" /by ", 2);
            if (arr[0].isBlank()) {
                throw new NoDescException();
            } else if (arr.length == 1) {
                throw new NoDateException();
            } else {
                String task = arr[0];
                String date = arr[1];
                if (date.isBlank()) {
                    throw new NoDateException();
                } else {
                    Deadline deadline = new Deadline(0, task, date);
                    list.add(deadline);
                    updateFile();
                    newTaskAdded(deadline);
                    getNumberOfTasks(list);
                }
            }
        }
        return true;
    }

    public static boolean isValidEvent(String input) throws
            NoDescException, NoStartException, NoEndException, IOException {
        String[] inputArr = input.split(" ", 2);
        if (inputArr.length == 1) {
            throw new NoDescException();
        } else if (inputArr[1].isBlank()) {
            throw new NoDescException();
        } else {
            String afterCommand = inputArr[1];
            String[] arr = afterCommand.split(" /from ", 2);
            if (arr[0].isBlank()) {
                throw new NoDescException();
            } else if (arr.length == 1) { //no start date added
                throw new NoStartException();
            } else {
                String task = arr[0];
                String start = arr[1].split(" /to ", 2)[0];
                if (start.isBlank()) {
                    throw new NoStartException();
                } else {
                    String[] arrWithEnd = afterCommand.split(" /to ", 2);
                    if (arrWithEnd.length == 1) { //no end date added
                        throw new NoEndException();
                    } else {
                        String end = arrWithEnd[1];
                        if (end.isBlank()) {
                            throw new NoEndException();
                        } else {
                            Event event = new Event(0, task, start, end);
                            list.add(event);
                            updateFile();
                            newTaskAdded(event);
                            getNumberOfTasks(list);
                        }
                    }
                }
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        loadTasks();
        String intro = "(｡･o･｡)ﾉ Hey there! I'm BUTTER.\n" +
                "How can I help you today?\n";
        String bye = "彡໒(⊙ᴗ⊙)७彡 Signing off, see you later!\n";

        System.out.println(line + "\n" + intro + line); //greeting
        Scanner scanner = new Scanner(System.in); //create Scanner object

        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            try {
                if (input.equals("bye")) {
                    break;
                } else {
                    isValidCommand(input);
                }
            } catch (InvalidCommandException | NoDescException | NoDateException |
                     NoStartException | NoEndException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println(bye + line);
        scanner.close();
    }
}