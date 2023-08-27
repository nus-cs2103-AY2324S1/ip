import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;


public class CommandProcessor {
    private final TaskList tasks;
    private static final String[] VALIDCOMMANDS = {"mark", "unmark", "list", "todo", "event", "deadline", "delete"};

    private static void writeToFile(String textToAdd) throws DukeException {
        try {
            File theDir = new File("\\data");
            if (!theDir.exists()) {
                theDir.mkdirs();
                File dataFile = new File("\\data\\duke.txt");
                dataFile.createNewFile();
            }

            FileWriter fw = new FileWriter("\\data\\duke.txt", true);

            fw.write(System.lineSeparator());
            fw.write(textToAdd);

            fw.close();
        } catch(IOException e) {
            throw new DukeException(e.getMessage());
        }
    }
    private static TaskList loadFromFile() {
        try {
            File dataFile = new File("\\data\\duke.txt");
            if (dataFile.exists()) {
                Scanner sc = new Scanner(dataFile);
                TaskList tasks = new TaskList();
                while (sc.hasNext()) {
                    String taskInfo = sc.nextLine();
                    String taskName = taskInfo.substring(3);
                    boolean marked = taskInfo.charAt(1) == 'x';

                    if (taskInfo.charAt(0) == 'T') {
                        Task task = new Todo(taskName);
                        tasks.add(task);
                    } else if (taskInfo.charAt(0) == 'D') {
                        Task task = new Todo(taskName);
                        tasks.add(task);
                    } else if (taskInfo.charAt(0) == 'E') {
                        Task task = new Deadline(taskName);
                        tasks.add(task);
                    }

                    if (marked) {
                        tasks.mark(tasks.size() - 1);
                    }
                }
                sc.close();

                return tasks;
            }
            return new TaskList();
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }

        return new TaskList();
    }

    CommandProcessor() {
        this.tasks = loadFromFile();
    }


    private String[] parseCommand(String command) throws DukeException {
        String [] splitCommand = command.split(" ", 2);
        String commandType = splitCommand[0];
        if (!Arrays.asList(VALIDCOMMANDS).contains(commandType)) {
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        if (splitCommand.length != 2 && !commandType.equals("list")) {
            throw new DukeException("OOPS!!! The description of a " + commandType + " cannot be empty.");
        }

        String commandDescription = splitCommand[0].trim();

        if (commandDescription.isEmpty()) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
        }

        return splitCommand;


    }


    //processCommand is a method that process the command and prints the relevant output
    void processCommand(String command) {

        try {

            String [] splitCommand = parseCommand(command);
            String commandType = splitCommand[0];
            // print the list of tasks
            if (commandType.equals("list")) {
                this.tasks.listContent();
                return;
            }

            String taskName = splitCommand[1];

            // process command types: mark, unmark
            if (commandType.equals("mark")) {
                tasks.mark(taskName);
                return;
            } else if (commandType.equals("unmark")) {
                tasks.unMark(taskName);
                return;
            }

            if (commandType.equals("delete")) {
                tasks.delete(taskName);
                return;
            }

            // process commands involving tasks (todo, deadline, event)
            if (commandType.equals("todo")) {
                Task task = new Todo(taskName);
                writeToFile("Tu " + taskName);
                tasks.addToList(task);
            } else if (commandType.equals("deadline")) {
                Task task = new Deadline(taskName);
                writeToFile("Du " + taskName);
                tasks.addToList(task);



            } else if (commandType.equals("event")) {
                Task task = new Event(taskName);
                writeToFile("Eu " + taskName);
                tasks.addToList(task);
            }

        } catch(DukeException e) {
            System.out.println(e.getMessage());
        }


    }
}
