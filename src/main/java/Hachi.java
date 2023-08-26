import exceptions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Hachi {

    private static String dataPath = "./data";
    private static String taskPath = "./data/tasks.txt";

    public static void main(String[] args) throws HachiException {
        String name = "Hachi";

        // creating the directory and file to store the tasks in

        File dataDirectory = new File(dataPath);
        if (!dataDirectory.exists()) {
            dataDirectory.mkdir();
        }
        File taskFile = new File(taskPath);
        if (!taskFile.exists()) {
            try {
                taskFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }

        // Obtaining tasks from stored file
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            Scanner fileScanner = new Scanner(taskFile);
            while (fileScanner.hasNext()) {
                tasks.add(txtToTask(fileScanner.nextLine()));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }

        // Printing opening line
        line();
        System.out.println("Hello! I'm " + name + "\nWhat can I do for you?");
        line();

        Scanner sc = new Scanner(System.in);

        // repeats until user types bye, which quits the program
        while (true) {
            // getting command and argument separately
            String input = sc.nextLine();
            String[] words = input.split(" ");
            String command = words[0];
            String[] arguments = Arrays.copyOfRange(words, 1, words.length);

            line();
            try {
                if (command.equals("bye")) {
                    if (arguments.length > 0) {
                        throw new TooManyArgumentsException("bye", 0, arguments.length);
                    }
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                } else if (command.equals("list")) {
                    if (arguments.length > 0) {
                        throw new TooManyArgumentsException("list", 0, arguments.length);
                    }
                    for (int i = 0; i < tasks.size(); i++) {
                        int num = i + 1;
                        System.out.println(num + ". " + tasks.get(i));
                    }
                } else if (command.equals("mark")) {
                    if (arguments.length > 1) {
                        throw new TooManyArgumentsException("mark", 1, arguments.length);
                    }
                    if (arguments.length < 1) {
                        throw new EmptyNumberException("mark");
                    }
                    try {
                        int number = Integer.parseInt(arguments[0]);
                        int i = number - 1;
                        if (number > tasks.size()) {
                            throw new NumberOutOfBoundsException(tasks.size());
                        }
                        tasks.get(i).mark();
                        System.out.println("Nice! I've marked this task as done");
                        System.out.println("   " + tasks.get(i));
                        updateTaskFile(tasks);
                    } catch (NumberFormatException e) {
                        throw new InvalidArgumentException("mark");
                    }
                } else if (command.equals("unmark")) {
                    if (arguments.length > 1) {
                        throw new TooManyArgumentsException("unmark", 1, arguments.length);
                    }
                    if (arguments.length < 1) {
                        throw new EmptyNumberException("unmark");
                    }
                    try {
                        int number = Integer.parseInt(words[1]);
                        int i = number - 1;
                        if (number > tasks.size()) {
                            throw new NumberOutOfBoundsException(tasks.size());
                        }
                        tasks.get(i).unmark();
                        System.out.println("OK, I've marked this task as not done yet:");
                        System.out.println("   " + tasks.get(i));
                        updateTaskFile(tasks);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid argument for command \"unmark\"");
                    }
                } else if (command.equals("delete")) {
                    if (arguments.length > 1) {
                        throw new TooManyArgumentsException("delete", 1, arguments.length);
                    }
                    if (arguments.length < 1) {
                        throw new EmptyNumberException("delete");
                    }
                    try {
                        int number = Integer.parseInt(arguments[0]);
                        int i = number - 1;
                        if (number > tasks.size()) {
                            throw new NumberOutOfBoundsException(tasks.size());
                        }
                        Task t = tasks.remove(i);
                        System.out.println("Noted. I've removed this task: ");
                        System.out.println("   " + t);
                        System.out.println(String.format("Now you have %d tasks in the list.", tasks.size()));
                        updateTaskFile(tasks);
                    } catch (NumberFormatException e) {
                        throw new InvalidArgumentException("delete");
                    }
                } else if (command.equals("todo")) {
                    if (arguments.length < 1) {
                        throw new EmptyTaskException("todo");
                    }
                    String todoTask = String.join(" ", arguments);
                    System.out.println("Got it. I've added this task:");
                    Todo td = new Todo(todoTask);
                    tasks.add(td);
                    System.out.println("   " + td);
                    System.out.println(String.format("Now you have %d tasks in the list.", tasks.size()));
                    updateTaskFile(tasks);
                } else if (command.equals("deadline")) {
                    if (arguments.length < 1) {
                        throw new EmptyTaskException("deadline");
                    }
                    int byIndex = -1;
                    for (int i = 0; i < arguments.length; i++) {
                        if (arguments[i].equals("/by")) {
                            byIndex = i;
                            break;
                        }
                    }
                    if (byIndex == -1) {
                        throw new NoDeadlineException();
                    }
                    if (byIndex == arguments.length - 1) {
                        throw new EmptyDeadlineException("deadline");
                    }
                    System.out.println("Got it. I've added this task:");
                    String deadlineTask = String.join(" ",
                            Arrays.copyOfRange(arguments, 0, byIndex));
                    String deadlineDate = String.join(" ",
                            Arrays.copyOfRange(arguments, byIndex + 1, arguments.length));
                    Deadline dl = new Deadline(deadlineTask, deadlineDate);
                    tasks.add(dl);
                    System.out.println("   " + dl);
                    System.out.println(String.format("Now you have %d tasks in the list.", tasks.size()));
                    updateTaskFile(tasks);
                } else if (command.equals("event")) {
                    if (arguments.length < 1) {
                        throw new EmptyTaskException("event");
                    }
                    int fromIndex = -1;
                    int toIndex = -1;
                    for (int i = 0; i < arguments.length; i++) {
                        if (arguments[i].equals("/from")) {
                            fromIndex = i;
                        } else if (arguments[i].equals("/to")) {
                            toIndex = i;
                        }
                    }
                    if (fromIndex == -1 && toIndex == -1) {
                        throw new EventDateException("/from and /to");
                    } else if (toIndex == -1){
                        throw new EventDateException("/to");
                    } else if (fromIndex == -1) {
                        throw new EventDateException("/from");
                    } else {
                        String eventTask = String.join(" ",
                                Arrays.copyOfRange(arguments, 0, fromIndex));
                        String eventStartDate = String.join(" ",
                                Arrays.copyOfRange(arguments, fromIndex + 1, toIndex));
                        String eventEndDate = String.join(" ",
                                Arrays.copyOfRange(arguments, toIndex + 1, arguments.length));
                        if (eventTask.equals("")) {
                            throw new EmptyTaskException("event");
                        } else if (eventStartDate.equals("") && eventEndDate.equals("")) {
                            throw new EventDateException("start date and end date");
                        } else if (eventEndDate.equals("")) {
                            throw new EventDateException("end date");
                        } else if (eventStartDate.equals("")) {
                            throw new EventDateException("start date");
                        }
                        Event ev = new Event(eventTask, eventStartDate, eventEndDate);
                        tasks.add(ev);
                        System.out.println("Got it. I've added this task:");
                        System.out.println("   " + ev);
                        System.out.println(String.format("Now you have %d tasks in the list.", tasks.size()));
                        updateTaskFile(tasks);
                    }
                } else {
                    throw new InvalidCommandException(command);
                }
            } catch (HachiException e) {
                System.out.println(e.getMessage());
            }
            line();
        }

    }

    private static void appendToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAdd);
        fw.close();
    }

    private static void updateTaskFile(ArrayList<Task> tasks) {
        // clear file first
        try {
            new FileWriter(taskPath).close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // add every task in current task list
        tasks.forEach(task -> {
            try {
                appendToFile(taskPath, task.toData() + "\n");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private static Task txtToTask(String txt) {
        String[] s = txt.split(" \\| "); // need to escape | character as it means something in regex
        Task temp = null;
        // set Task to the respective task type
        try {
            if (s[0].equals("T")) {
                temp = new Todo(s[2]);
            } else if (s[0].equals("D")) {
                temp = new Deadline(s[2], s[3]);
            } else if (s[0].equals("E")) {
                temp = new Event(s[2], s[3], s[4]);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Task stored in the wrong format! Please check the file at 'data/tasks.txt'");
        }

        // mark task based on '0' or '1' in the file
        if (s[1].equals("1")) {
            temp.mark();
        } else {
            temp.unmark();
        }

        return temp;
    }


    public static void line() {
        System.out.println("____________________________________________________________");
    }

}
