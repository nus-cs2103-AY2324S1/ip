import java.io.IOException;

import java.util.Scanner;
import java.util.Arrays;

public class Duke {

    Scanner userInput = new Scanner(System.in);
    TaskList tasks = new TaskList();

    Storage storage = new Storage("./data/data.txt", tasks);


    public static void main(String[] args) {

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo);

        Duke duke = new Duke();
        duke.start();

    }

    static void line() {
        String line = "____________________________________________________________";
        System.out.println(line);
    }

    private void start() {
        line();
        System.out.println(" Hello! I'm JARVIS");
        System.out.println("What can I do for you?");
        line();

        try {
            this.storage.loadList();
        } catch (DukeException | IOException e) {
            System.out.println(e.getMessage());
        }

        while (true) {
            String input = userInput.nextLine();

            try {
                if (input.equals("bye")) {
                    exit();
                    break;
                } else if (input.equals("list")) {
                    tasks.list();
                } else if (input.startsWith("mark")) {
                    testMarkAndDelete(input);
                    int taskIndex = Integer.parseInt(input.substring(5)) - 1;
                    tasks.mark(taskIndex);
                } else if (input.startsWith("unmark")) {
                    testMarkAndDelete(input);
                    int taskIndex = Integer.parseInt(input.substring(7)) - 1;
                    tasks.unmark(taskIndex);
                } else {
                    if (input.startsWith("todo")) {

                        String description = input.substring(4).trim();
                        // test whether the todo is valid
                        testToDo(description);

                        tasks.addToDo(description);

                    } else if (input.startsWith("event")) {

                        testEvent(input);
                        tasks.addEvent(input);

                    } else if (input.startsWith("deadline")) {

                        testDeadline(input);
                        tasks.addDeadline(input);

                    } else if (input.startsWith("delete")) {

                        testMarkAndDelete(input);
                        tasks.deleteTask(input);

                    } else {
                        throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }

                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    line();
                }
            } catch (DukeException exception) {
                System.out.println(exception.getMessage());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void exit() throws DukeException, IOException {
        System.out.println("Bye. Hope to see you again soon!");
        storage.saveList();
        line();
    }

    private void testToDo(String description) throws DukeException {
        if (description.isEmpty()) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
        }
    }

    private void testMarkAndDelete(String description) throws DukeException {
        String[] words = description.split(" ");
        String index = String.join(" ", Arrays.copyOfRange(words, 1, words.length));

        if (index.isEmpty() || !index.matches("-?(0|[1-9]\\d*)")) {
            throw new DukeException("Following \"mark\" or \"unmark\" or \"delete\", an integer value is expected. Blanks or" +
                    " non-integer values are invalid.");
        }

        int intIndex = Integer.parseInt(index);

        // Passing the first case means the index is an integer
        if (intIndex < 1) {
            throw new DukeException("The index following \"mark\" or \"unmark\" should start from 1.");
        } else if (intIndex > tasks.size()) {
            throw new DukeException("The index following \"mark\" or \"unmark\" should not exceed the total number of "
                    + "tasks in the list");
        }
    }

    private void testEvent(String description) throws DukeException {
        String[] list = description.split("/");

        if (list.length != 3) {
            throw new DukeException("Invalid input. Fill up all fields. Do not forget the \"/\" symbol before your" +
                    " start and end time.");
        }

        String title = list[0].substring(6);
        String start = list[1].substring(5);
        String end = list[2].substring(3);

        if (start.isEmpty()) {
            throw new DukeException("\"from\" time missing!");
        } else if (end.isEmpty()) {
            throw new DukeException("\"to\" time missing!");
        }
    }

    private void testDeadline(String description) throws DukeException {
        String[] list = description.split("/");

        if (list.length != 2) {
            throw new DukeException("Invalid input. Fill up all fields. Do not forget the \"/\" symbol before your" +
                    " end time.");
        }

        String time = list[1];

        if (!time.startsWith("by")) {
            throw new DukeException("Invalid input. Start with \"by\".");
        } else if (time.substring(2).equals(" ") || time.substring(2).isEmpty()) {
            throw new DukeException("Invalid input. Field Empty.");
        }
    }

//    private void loadList() throws DukeException, IOException {
//        try {
//            File file = new File("./data/data.txt");
//            FileReader fileReader = new FileReader(file);
//            BufferedReader reader = new BufferedReader(fileReader); // BufferedReader wraps the fileReader
//
//            String line;
//            while ((line = reader.readLine()) != null) {
//                String[] inputArray = line.split(" \\| ");
//
//                switch(inputArray[0]) {
//                    case "T":
//                        ToDo toDo = new ToDo(inputArray[2]);
//                        if (inputArray[1] == "1") {
//                            toDo.taskDone(true);
//                        }
//                        tasks.addTask(toDo);
//                        break;
//
//                    case "E":
//                        Event event = new Event(inputArray[2], inputArray[3], inputArray[4]);
//
//                        if (inputArray[1] == "1") {
//                            event.taskDone(true);
//                        }
//                        tasks.addTask(event);
//                        break;
//
//                    case "D":
//                        Deadline deadline = new Deadline(inputArray[2], inputArray[3]);
//
//                        if (inputArray[1] == "1") {
//                            deadline.taskDone(true);
//                        }
//                        tasks.addTask(deadline);
//                        break;
//
//                    default:
//                        throw new DukeException("An unexpected error occurred while reading the text file. Error Code:" +
//                                " 01");
//                }
//            }
//
//        } catch (FileNotFoundException e) {
//            storage.saveList(tasks.getTaskArrayList());
//        } catch (IOException e) {
//            throw new DukeException("IO error occurred. Check the formatting of the text file - data.txt.");
//        }
//    }

//    private void saveList() throws DukeException, IOException {
//        try {
//            File file = new File("./data/data.txt");
//            file.getParentFile().mkdirs();
//
//            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
//            writer = tasks.printStoreFormat(writer);
//
//            writer.close();
//        } catch (IOException e) {
//            throw new DukeException("IO exception occurred.");
//        }
//    }
}
