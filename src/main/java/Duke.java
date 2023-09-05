import java.util.ArrayList;
import java.util.Scanner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui = new Ui();
    private static final String filePath = "./data/duke.txt";


    public static void main(String[] args) {
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Jarvis");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");


        ArrayList<Task> list;

        try {
            list = load();
        } catch (IOException e) {
            throw new DukeException("Unable to find file with data.");
        }

        Scanner userInput = new Scanner(System.in);
        String command = userInput.nextLine();
        while (!command.startsWith("bye")) {

            try{
                if (command.startsWith("list")) {
                    System.out.println("____________________________________________________________");
                    System.out.println(" Here are the tasks in your list:");
                    int counter = 0;
                    while (counter != list.size()) {
                        counter++;
                        System.out.println(" " + counter + "." + list.get(counter - 1).toString());
                    }
                    System.out.println("____________________________________________________________");
                } else if (command.startsWith("mark")) {
                    list.get(Integer.valueOf(command.split(" ")[1]) - 1).markAsDone();
                    System.out.println("____________________________________________________________");
                    System.out.println(" Nice! I've marked this task as done:");
                    System.out.println("  " + list.get(Integer.valueOf(command.split(" ")[1]) - 1).toString());
                    System.out.println("____________________________________________________________");
                    rewriteFile(list);
                } else if (command.startsWith("todo")) {
                    if (command.split(" ", 2).length == 1) {
                        throw new DukeException(" OOPS!!! The description of a todo cannot be empty.");
                    }
                    ToDo newToDo = new ToDo(command.split(" ", 2)[1]);
                    list.add(newToDo);
                    System.out.println("____________________________________________________________");
                    System.out.println(" Got it. I've added this task:");
                    System.out.println("   " + newToDo.toString());
                    System.out.println(" Now you have " + list.size() + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                    addToFile(list);
                } else if (command.startsWith("deadline")) {
                    String deadline = command.split(" /by ", 2)[1];
                    String name = command.split(" /by ", 2)[0].split(" ", 2)[1];
                    Deadline newDeadline = new Deadline(deadline, name);
                    list.add(newDeadline);
                    System.out.println("____________________________________________________________");
                    System.out.println(" Got it. I've added this task:");
                    System.out.println("   " + newDeadline.toString());
                    System.out.println(" Now you have " + list.size() + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                    addToFile(list);
                } else if (command.startsWith("event")) {
                    String startTime = command.split(" /from ", 2)[1]
                            .split(" /to ", 2)[0];
                    String endTime = command.split(" /to ", 2)[1];
                    String name = command.split(" /from ", 2)[0].split(" ", 2)[1];
                    Event newEvent = new Event(name, startTime, endTime);
                    list.add(newEvent);
                    System.out.println("____________________________________________________________");
                    System.out.println(" Got it. I've added this task:");
                    System.out.println("   " + newEvent.toString());
                    System.out.println(" Now you have " + list.size() + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                    addToFile(list);
                } else if (command.startsWith("delete")) {
                    Task re = list.remove(Integer.valueOf(command.split(" ")[1]) - 1);
                    System.out.println("____________________________________________________________");
                    System.out.println(" Noted. I've removed this task:");
                    System.out.println("   " + re.toString());
                    System.out.println(" Now you have " + list.size() + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                    rewriteFile(list);
                } else {
                    throw new DukeException(" OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                System.out.println("____________________________________________________________");
                System.out.println(" " + e.getMessage());
                System.out.println("____________________________________________________________");
            } finally {
                command = userInput.nextLine();
            }
        }

        System.out.println("____________________________________________________________");
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    public static ArrayList<Task> load() throws IOException {
        File dir = new File("./data");
        if (!dir.exists()) {
            dir.mkdir();
        }
        File f = new File(filePath);
        f.createNewFile();
        Scanner s = new Scanner(f);
        ArrayList<Task> tasks = new ArrayList<>();
        while (s.hasNext()) {
            tasks.add(addTask(s.nextLine()));
        }
        return tasks;
    }

    private static Task addTask(String input) {
        String taskType = input.split(" \\| ")[0];
        boolean isComplete = input.split(" \\| ")[1].equals("1");
        String description = input.split(" \\| ")[2];
        if (taskType.equals("T")) {
            return new ToDo(description, isComplete);
        } else if (taskType.equals("D")) {
            return new Deadline(description, isComplete, input.split(" \\| ")[3]);
        } else {
            return new Event(description, isComplete, input.split(" \\| ")[3], input.split(" \\| ")[4]);
        }
    }


    public static void addToFile(ArrayList<Task> list) throws DukeException {
        try {
            FileWriter fw = new FileWriter(filePath, true);
            if (list.size() == 1) {
                fw.write(list.get(0).toTxt());
            } else {
                fw.write("\n" + list.get(list.size() - 1).toTxt());
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Unable to write to file.");
        }
    }

    public static void rewriteFile(ArrayList<Task> list) throws DukeException {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (int i = 0; i < list.size(); i++) {
                if (i == list.size() - 1) {
                    fw.write(list.get(i).toTxt());
                } else {
                    fw.write(list.get(i).toTxt() + "\n");
                }
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Unable to write to file.");
        }
    }
}
