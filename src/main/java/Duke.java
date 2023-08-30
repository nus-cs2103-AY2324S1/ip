import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.File;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Duke {
    public static void main(String[] args) throws DukeException {
        try {
            File dir = new File("./data");
            if (!dir.exists()) {
                dir.mkdir();
            }

            File f = new File("./data", "/duke.txt");
            if (!f.exists()) {
                f.createNewFile();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        String horizontalLine = "\t_______________________________________________________________________";
        System.out.println(horizontalLine);
        System.out.println("\tHello! I'm Ari.");
        System.out.println("\tWhat can I do for you?");
        System.out.println(horizontalLine);
        try {
            echo();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(horizontalLine);
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println(horizontalLine);
    }

    /**
     * Runs the Ari chatbot.
     * or is not one of 'events', 'todos', or 'deadline'
     * @throws DukeException throws DukeException if input is not formatted correctly
     */
    public static void echo() throws DukeException, FileNotFoundException {
        ArrayList<Task> lst = new ArrayList<>();

        File f = new File("./data/duke.txt");
        Scanner scanner = new Scanner(f);
        while (scanner.hasNext()) {
            lst.add(textToTask(scanner.nextLine()));
        }

        Scanner input = new Scanner(System.in);
        String commands = input.nextLine();

        while (true) {
            if (commands.equals("bye")) {
                return;
            } else if (commands.equals("list")) {
                showList(lst);
            } else if (commands.indexOf("mark") == 0) {
                try {
                    mark(lst, commands);
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            } else if (commands.indexOf("unmark") == 0) {
                try {
                    unmark(lst, commands);
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            } else if (commands.indexOf("deadline") == 0) {
                addDeadline(lst, commands);
            } else if (commands.indexOf("todo") == 0) {
                addToDo(lst, commands);
            } else if (commands.indexOf("event") == 0) {
                addEvent(lst, commands);
            } else if (commands.indexOf("delete") == 0) {
                try {
                    deleteTask(lst, commands);
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                throw new DukeException("\t  OOPS!!! I'm sorry, but I don't know what that means :-(");
            }

            commands = input.nextLine();
        }
    }

    /**
     * Prints out all the tasks stored in the ArrayList.
     * @param lst an ArrayList of tasks storing all the tasks for the user
     */
    public static void showList(ArrayList<Task> lst) {
        String horizontalLine = "\t_______________________________________________________________________";

        System.out.println(horizontalLine);
        System.out.println("\tHere are the task in your list:");
        for (int i = 0; i < lst.size(); i++) {
            System.out.println("\t" + (i + 1) + "." + lst.get(i).toString());
        }
        System.out.println(horizontalLine);
    }

    public static void clearFile(String filePath) throws IOException {
        File f = new File(filePath);
        f.delete();
        f.createNewFile();
    }


    public static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAppend);
        fw.close();
    }

    public static void updateFileAfterMark(String filePath, int lineNumber) throws IOException {
        String updatedContent = "";
        File f = new File(filePath);
        Scanner scanner = new Scanner(f);
        int i = 1;
        while (scanner.hasNext()) {
            if (i == lineNumber) {
                String updatedLine = scanner.nextLine();
                updatedContent += updatedLine.substring(0, updatedLine.indexOf("|") + 2) +
                        "[X]" + updatedLine.substring(7) + System.lineSeparator();
                i++;
                continue;
            }
            updatedContent += scanner.nextLine() + System.lineSeparator();
            i++;
        }
        clearFile(filePath);
        appendToFile(filePath, updatedContent);
    }

    public static void updateFileAfterUnmark(String filePath, int lineNumber) throws IOException {
        String updatedContent = "";
        File f = new File(filePath);
        Scanner scanner = new Scanner(f);
        int i = 1;
        while (scanner.hasNext()) {
            if (i == lineNumber) {
                String updatedLine = scanner.nextLine();
                updatedContent += updatedLine.substring(0, updatedLine.indexOf("|") + 2) +
                        "[ ]" + updatedLine.substring(7) + System.lineSeparator();
                i++;
                continue;
            }
            updatedContent += scanner.nextLine() + System.lineSeparator();
            i++;
        }
        clearFile(filePath);
        appendToFile(filePath, updatedContent);
    }


    public static void updateFileAfterDelete(String filePath, int lineNumber) throws IOException {
        String updatedContent = "";
        File f = new File(filePath);
        Scanner scanner = new Scanner(f);
        int i = 1;
        while (scanner.hasNext()) {
            if (i == lineNumber) {
                scanner.nextLine();
                i++;
                continue;
            }
            updatedContent += scanner.nextLine() + System.lineSeparator();
            i++;
        }
        clearFile(filePath);
        appendToFile(filePath, updatedContent);
    }


    public static Task textToTask(String text) throws DukeException{
        String identifier = text.substring(0, 1);
        String status = text.substring(4, 7);
        boolean isDone;
        Task task = null;
        if (status.equals("[ ]")) {
            isDone = false;
        } else {
            isDone = true;
        }

        switch (identifier) {
            case "T":
                String todo = text.substring(10);
                task = new ToDo(todo, isDone);
                break;
            case "D":
                String deadline = text.substring(10, text.indexOf(" | ", 10));

                String by = text.substring(text.indexOf("|", 10) + 2);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd HHmm");
                LocalDateTime dateTime = LocalDateTime.parse(by, formatter);

                task = new Deadline(deadline, dateTime, isDone);
                break;
            case "E":
                String event = text.substring(10, text.indexOf(" | ", 10));

                String from = text.substring(text.indexOf("|", 10) + 2, text.indexOf("-"));
                DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyyMMdd HHmm");
                LocalDateTime dateTimeFrom = LocalDateTime.parse(from, formatter1);

                String to = text.substring(text.indexOf("-") + 1);
                DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("HHmm");
                LocalTime dateTimeTo = LocalTime.parse(to, formatter2);

                task = new Event(event, dateTimeFrom, dateTimeTo, isDone);
                break;
        }
        if (task == null) {
            throw new DukeException("Incorrect format of the file :(");
        } else {
            return task;
        }
    }

    /**
     * Deletes a specified task in the commands
     * @param lst an ArrayList of tasks storing all the tasks for the user
     * @param commands a command indicating which task to delete
     */
    public static void deleteTask(ArrayList<Task> lst, String commands) throws IOException {
        String horizontalLine = "\t_______________________________________________________________________";
        int index = java.lang.Integer.parseInt(commands.substring("delete".length() + 1)) - 1;
        Task removedTask = lst.remove(index);
        updateFileAfterDelete("./data/duke.txt", index + 1);
        System.out.println(horizontalLine);
        System.out.println("\tNoted. I've removed this task:");
        System.out.println("\t  " + removedTask.toString());
        System.out.println("\tNow you have " + lst.size() + " tasks in the list.");
    }

    /**
     * Adds a task with a deadline to the ArrayList of tasks
     * @param lst an ArrayList of tasks storing all the tasks for the user
     * @param commands a command with descriptions about a deadline and a date
     * @throws DukeException throws DukeException if input is not formatted correctly
     */
    public static void addDeadline(ArrayList<Task> lst, String commands) throws DukeException {
        String horizontalLine = "\t_______________________________________________________________________";

        int byIndex = commands.indexOf("/by");
        int commandIndex = commands.indexOf(" ");

        if (byIndex == -1 || commandIndex == -1) {
            throw new DukeException("\t  OOPS!!! The description of a deadline and time cannot be empty.");
        }

        String by = commands.substring(byIndex + "/by".length() + 1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd HHmm");
        LocalDateTime dateTime = LocalDateTime.parse(by, formatter);

        String command = commands.substring(commandIndex + 1, byIndex - 1);

        Task deadline = new Deadline(command, dateTime, false);
        lst.add(deadline);

        String file = "./data/duke.txt";

        try {
            appendToFile(file, "D | " + deadline.getStatusIcon() + " | " + deadline.taskDescription +
                    " | " + by + System.lineSeparator());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        System.out.println(horizontalLine);
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t  " + lst.get(lst.size() - 1).toString());
        System.out.println("\tNow you have " + lst.size() + " tasks in the list.");
        System.out.println(horizontalLine);
    }

    /**
     * Adds a to-do to the ArrayList of tasks
     * @param lst an ArrayList of tasks storing all the tasks for the user
     * @param commands a command with descriptions about a to-do
     * @throws DukeException throws DukeException if input is not formatted correctly
     */
    public static void addToDo(ArrayList<Task> lst, String commands) throws DukeException {
        String horizontalLine = "\t_______________________________________________________________________";

        int commandIndex = commands.indexOf(" ");
        String command = commands.substring(commandIndex + 1);

        if (commandIndex == -1 || command.equals("")) {
            throw new DukeException("\t  OOPS!!! The description of a todo cannot be empty.");
        }

        Task todo = new ToDo(command, false);
        lst.add(todo);

        String file = "./data/duke.txt";

        try {
            appendToFile(file, "T | " + todo.getStatusIcon() + " | " + todo.taskDescription + System.lineSeparator());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        System.out.println(horizontalLine);
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t  " + lst.get(lst.size() - 1).toString());
        System.out.println("\tNow you have " + lst.size() + " tasks in the list.");
        System.out.println(horizontalLine);
    }

    /**
     * Adds an event to the ArrayList of tasks
     * @param lst an ArrayList of tasks storing all the tasks for the user
     * @param commands a command with descriptions about an event and its duration
     * @throws DukeException throws DukeException if input is not formatted correctly
     */
    public static void addEvent(ArrayList<Task> lst, String commands) throws DukeException {
        String horizontalLine = "\t_______________________________________________________________________";

        int fromIndex = commands.indexOf("/from");
        int toIndex = commands.indexOf("/to");
        int commandIndex = commands.indexOf(" ");

        if (fromIndex == -1 || toIndex == -1 || commandIndex == -1) {
            throw new DukeException("\t  OOPS!!! The description of an event and time cannot be empty.");
        }

        String from = commands.substring(fromIndex + "/from".length() + 1, toIndex - 1);
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyyMMdd HHmm");
        LocalDateTime dateTimeFrom = LocalDateTime.parse(from, formatter1);

        String to = commands.substring(toIndex + "/to".length() + 1);
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("HHmm");
        LocalTime dateTimeTo = LocalTime.parse(to, formatter2);

        String command = commands.substring(commandIndex + 1, fromIndex - 1);
        Task event = new Event(command, dateTimeFrom, dateTimeTo, false);
        lst.add(event);

        String file = "./data/duke.txt";

        try {
            appendToFile(file, "E | " + event.getStatusIcon() + " | " + event.taskDescription +
                    " | " + from + "-" + to + System.lineSeparator());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }


        System.out.println(horizontalLine);
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t  " + lst.get(lst.size() - 1).toString());
        System.out.println("\tNow you have " + lst.size() + " tasks in the list.");
        System.out.println(horizontalLine);

    }

    /**
     * Marks a specified task as done
     * @param lst an ArrayList of tasks storing all the tasks for the user
     * @param commands a command indicating which task to mark as done
     */
    public static void mark(ArrayList<Task> lst, String commands) throws IOException {
        String horizontalLine = "\t_______________________________________________________________________";

        int index = java.lang.Integer.parseInt(commands.substring("mark".length() + 1)) - 1;
        lst.get(index).changeStatus();
        updateFileAfterMark("./data/duke.txt", index + 1);
        System.out.println(horizontalLine);
        System.out.println("\tNice! I've marked this task as done:");
        System.out.println("\t  " + lst.get(index).toString());
        System.out.println(horizontalLine);
    }

    /**
     * Unmarks a specified task from 'done'
     * @param lst an ArrayList of tasks storing all the tasks for the user
     * @param commands a command indicating which task to unmark
     */
    public static void unmark(ArrayList<Task> lst, String commands) throws IOException {
        String horizontalLine = "\t_______________________________________________________________________";

        int index = java.lang.Integer.parseInt(commands.substring("unmark".length() + 1)) - 1;
        lst.get(index).changeStatus();
        updateFileAfterUnmark("./data/duke.txt", index + 1);
        System.out.println(horizontalLine);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("\t  " + lst.get(index).toString());
        System.out.println(horizontalLine);
    }
}