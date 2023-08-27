import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
public class Mimi {
    public static void main(String[] args) {

        Storage previousCommands = new Storage();
        String DIRECTORY = "./data/";
        String DATA_PATH = "./data/Mimi.txt";

        String LINE = "_________________________________________________\n";

        File directory = new File(DIRECTORY);
        File dataFile = new File(DATA_PATH);


        //if folder exists, do nothing. if it doesn't, create it.
        if (directory.mkdir()) {}

        //if file exists, do nothing. if it doesn't, create it.
        try {
            if (dataFile.createNewFile()) {}
        } catch (IOException e) {
            System.out.println("unable to create new file: " + e.getMessage());
        }
        ReadWriteData readWriteData = new ReadWriteData(dataFile, previousCommands);

        readWriteData.onInitialise();

        System.out.println(
                LINE
                + "Hello! I'm Mimi.\n"
                + "What can I do for you?\n"
                + LINE
        );

        Scanner inputReader = new Scanner(System.in);

        while (true) {
            String command = inputReader.nextLine();
            System.out.println(LINE);
            Task task = new Task(command);

            if (task.isExit()) {
                System.out.println(Task.EXIT_MESSAGE + LINE);
                break;
            }

            if (task.isList()) {
                previousCommands.listItems();
                System.out.println(LINE);
                continue;
            }

            if (task.isUnmark()) {
                int task_Number = Integer.parseInt(command.replaceAll("[^0-9]", ""));

                previousCommands.unmark(task_Number);
                readWriteData.updateFile();
                System.out.println(LINE);
                continue;
            }

            if (task.isMark()) {
                int task_Number = Integer.parseInt(command.replaceAll("[^0-9]", ""));

                previousCommands.mark(task_Number);
                readWriteData.updateFile();
                System.out.println(LINE);
                continue;
            }

            if (task.isDelete()) {
                int task_Number = Integer.parseInt(command.replaceAll("[^0-9]", ""));

                previousCommands.delete(task_Number);
                readWriteData.updateFile();
                System.out.println(LINE);
                continue;
            }

            try {
                if (task.isCompleteCommand()) {
                    int i = task.get().indexOf(' ');
                    String actual_task = task.get().substring(0, i);

                    switch (actual_task) {
                        case "todo":
                            Todo todo = new Todo(command);
                            previousCommands.add(todo);
                            readWriteData.write(todo);
                            System.out.println(LINE);
                            break;
                        case "deadline":
                            try {
                                Deadline deadline = new Deadline(command);
                                previousCommands.add(deadline);
                                readWriteData.write(deadline);
                                System.out.println(LINE);
                            } catch (DateTimeParseException e) {
                                System.out.println(
                                        "OOPS!! Looks like your time format is wrong, make sure to use " +
                                                "this format: DD/MM/YYYY HHmm.\nExample is 30/05/2023 2100.\n" +
                                                "You typed: " + command
                                );
                            } finally {
                                System.out.println(LINE);
                                break;

                            }

                        case "event":
                            try {
                                Event event = new Event(command);
                                previousCommands.add(event);
                                readWriteData.write(event);
                                System.out.println(LINE);
                            } catch (DateTimeParseException e) {
                                System.out.println(
                                        "OOPS!! Looks like your time format is wrong, make sure to use " +
                                        "this format: DD/MM/YYYY HHmm.\nExample is 30/05/2023 2100.\n" +
                                        "You typed: " + command
                                );
                            } finally {
                                System.out.println(LINE);
                                break;
                            }
                    }
                } else {
                    if (task.isValidCommand()) {
                        Task temp = new Task("");
                        switch(command) {
                            case "todo":
                                temp = new Todo(command);
                                break;
                            case "deadline":
                                temp = new Deadline(command);
                                break;
                            case "event":
                                temp = new Event(command);
                                break;
                        }
                        System.out.println(temp.missingDescription() + "\n" + LINE);
                    }
                }
            } catch (InvalidTask e) {
                System.out.println("☹ OOPS!!! I'm sorry but I don't know what that means :-(\n" + LINE);
            }


        }
    }
}
