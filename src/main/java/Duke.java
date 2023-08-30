import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.tasks = new TaskList(storage.load());
    }

    public void run() {
        Ui.start();
        String input = Ui.getInput();
        while (!input.equals("bye")) {
            Parser.parseInput(input, this.tasks, this.storage);
            input = Ui.getInput();
        }
        Ui.bye();
    }

    public static void main(String[] args) {
        new Duke("./duke.txt").run();
        /*FileWriter writer = new FileWriter("./duke.txt");
        writer.write("");*/
    }

    /*
    public static void main(String[] args) throws InvalidTextException, EmptyDescriptionException, InvalidTaskException,
            DeadlineUnclearException, DurationUnclearException, FileNotFoundException, IOException {
        Scanner scan = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<Task>();
        File f = new File("./duke.txt");
        if (!f.exists()) {
            f.createNewFile();
        }
        Scanner fileScan = new Scanner(f);
        while (fileScan.hasNext()) {
            String taskString = fileScan.nextLine();
            if (taskString.charAt(0) == 'T') {
                Task task = new ToDo(taskString.substring(8));
                if (taskString.charAt(4) == '1') {
                    task.mark();
                }
                list.add(task);
            } else if (taskString.charAt(0) == 'D') {
                String[] details = taskString.substring(8).split(Pattern.quote(" | "));
                LocalDateTime dateTime = LocalDateTime.parse(details[1], DateTimeFormatter.ofPattern("MMM d yyyy HHmm"));
                Task task = new Deadline(details[0], dateTime);
                if (taskString.charAt(4) == '1') {
                    task.mark();
                }
                list.add(task);
            } else if (taskString.charAt(0) == 'E') {
                String[] details = taskString.substring(8).split(Pattern.quote(" | "));
                String[] duration = details[1].split(" -> ");
                LocalDateTime fromDateTime = LocalDateTime.parse(duration[0], DateTimeFormatter.ofPattern("MMM d yyyy HHmm"));
                LocalDateTime toDateTime = LocalDateTime.parse(duration[1], DateTimeFormatter.ofPattern("MMM d yyyy HHmm"));
                Task task = new Event(details[0], fromDateTime, toDateTime);
                if (taskString.charAt(4) == '1') {
                    task.mark();
                }
                list.add(task);
            }
        }
        System.out.println("    ____________________________________________________________");
        System.out.println("     " + "Hello! I'm ChatGP0");
        System.out.println("     " + "What can I do for you?");
        System.out.println("    ____________________________________________________________\n");
        String input = scan.nextLine();
        while (!input.equals("bye")) {
            try {
                if (input.equals("list")) {
                    System.out.println("    ____________________________________________________________");
                    System.out.println("     " + "Here are the tasks in your list:");
                    for (int i = 0; i < list.size(); i++) {
                        Task task = list.get(i);
                        System.out.println("     " + (i + 1) + "." + task.toString());
                    }
                    System.out.println("    ____________________________________________________________\n");
                } else if (input.startsWith("todo ") || (input.startsWith("todo") && input.length() == 4)) {
                    try {
                        if (input.length() <= 5 || input.substring(5).isBlank()) {
                            throw new EmptyDescriptionException();
                        }
                        Task task = new ToDo(input.substring(5));
                        list.add(task);
                        FileWriter fw;
                        Scanner sc = new Scanner(f);
                        if (sc.hasNextLine()) {
                            fw = new FileWriter("./duke.txt", true);
                            fw.write("\r\n");
                        } else {
                            fw = new FileWriter("./duke.txt");
                        }
                        fw.write(task.writeToFile());
                        fw.close();
                        System.out.println("    ____________________________________________________________");
                        System.out.println("     Got it. I've added this task:");
                        System.out.println("       " + task.toString());
                        System.out.println("     Now you have " + list.size() + " tasks in the list.");
                        System.out.println("    ____________________________________________________________\n");
                    } catch (EmptyDescriptionException e) {
                        System.out.println("    ____________________________________________________________");
                        System.out.println("     ☹ OOPS!!! The description of a todo cannot be empty.");
                        System.out.println("    ____________________________________________________________\n");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else if (input.startsWith("deadline ") || (input.startsWith("deadline") && input.length() == 8)) {
                    try {
                        if (input.length() <= 9 || input.substring(9).isBlank()) {
                            throw new EmptyDescriptionException();
                        }
                        String[] details = input.substring(9).split(" /by ");
                        if (details.length != 2) {
                            throw new DeadlineUnclearException();
                        }
                        LocalDateTime dateTime = LocalDateTime.parse(details[1], DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
                        Task task = new Deadline(details[0], dateTime);
                        list.add(task);
                        FileWriter fw;
                        Scanner sc = new Scanner(f);
                        if (sc.hasNextLine()) {
                            fw = new FileWriter("./duke.txt", true);
                            fw.write("\r\n");
                        } else {
                            fw = new FileWriter("./duke.txt");
                        }
                        fw.write(task.writeToFile());
                        fw.close();
                        System.out.println("    ____________________________________________________________");
                        System.out.println("     Got it. I've added this task:");
                        System.out.println("       " + task.toString());
                        System.out.println("     Now you have " + list.size() + " tasks in the list.");
                        System.out.println("    ____________________________________________________________\n");
                    } catch (EmptyDescriptionException e) {
                        System.out.println("    ____________________________________________________________");
                        System.out.println("     ☹ OOPS!!! The description of a deadline cannot be empty.");
                        System.out.println("    ____________________________________________________________\n");
                    } catch (DeadlineUnclearException e) {
                        System.out.println("    ____________________________________________________________");
                        System.out.println("     ☹ OOPS!!! The deadline is unclear.");
                        System.out.println("    ____________________________________________________________\n");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    } catch (DateTimeParseException e) {
                        System.out.println("    ____________________________________________________________");
                        System.out.println("     ☹ OOPS!!! Please follow the \"yyyy-MM-dd HHmm\" format.");
                        System.out.println("    ____________________________________________________________\n");
                    }
                } else if (input.startsWith("event ") || (input.startsWith("event") && input.length() == 5)) {
                    try {
                        if (input.length() <= 6 || input.substring(6).isBlank()) {
                            throw new EmptyDescriptionException();
                        }
                        String[] details = input.substring(6).split(" /from | /to ");
                        if (details.length != 3 || !input.contains(" /from ") || !input.contains(" /to ")) {
                            throw new DurationUnclearException();
                        }
                        LocalDateTime fromDateTime = LocalDateTime.parse(details[1], DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
                        LocalDateTime toDateTime = LocalDateTime.parse(details[2], DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
                        Task task = new Event(details[0], fromDateTime, toDateTime);
                        list.add(task);
                        FileWriter fw;
                        Scanner sc = new Scanner(f);
                        if (sc.hasNextLine()) {
                            fw = new FileWriter("./duke.txt", true);
                            fw.write("\r\n");
                        } else {
                            fw = new FileWriter("./duke.txt");
                        }
                        fw.write(task.writeToFile());
                        fw.close();
                        System.out.println("    ____________________________________________________________");
                        System.out.println("     Got it. I've added this task:");
                        System.out.println("       " + task.toString());
                        System.out.println("     Now you have " + list.size() + " tasks in the list.");
                        System.out.println("    ____________________________________________________________\n");
                    } catch (EmptyDescriptionException e) {
                        System.out.println("    ____________________________________________________________");
                        System.out.println("     ☹ OOPS!!! The description of a event cannot be empty.");
                        System.out.println("    ____________________________________________________________\n");
                    } catch (DurationUnclearException e) {
                        System.out.println("    ____________________________________________________________");
                        System.out.println("     ☹ OOPS!!! The duration is unclear.");
                        System.out.println("    ____________________________________________________________\n");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    } catch (DateTimeParseException e) {
                        System.out.println("    ____________________________________________________________");
                        System.out.println("     ☹ OOPS!!! Please follow the \"yyyy-MM-dd HHmm\" format.");
                        System.out.println("    ____________________________________________________________\n");
                    }
                } else if (input.startsWith("mark ") && input.length() > 5 && input.substring(5).matches("\\d+")) {
                    try {
                        int number = Integer.parseInt(input.substring(5));
                        if (number > list.size() || number <= 0) {
                            throw new InvalidTaskException();
                        }
                        Task task = list.get(number - 1);
                        task.mark();
                        FileWriter fw = new FileWriter("./duke.txt");
                        for (int i = 0; i < list.size() - 1; i++) {
                            fw.write(list.get(i).writeToFile() + "\r\n");
                        }
                        fw.write(list.get(list.size() - 1).writeToFile());
                        fw.close();
                        System.out.println("    ____________________________________________________________");
                        System.out.println("     " + "Nice! I've marked this task as done:");
                        System.out.println("       " + task.toString());
                        System.out.println("    ____________________________________________________________\n");
                    } catch (InvalidTaskException e) {
                        System.out.println("    ____________________________________________________________");
                        System.out.println("     ☹ OOPS!!! This task does not exist :O");
                        System.out.println("    ____________________________________________________________\n");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else if (input.startsWith("unmark ") && input.length() > 7 && input.substring(7).matches("\\d+")) {
                    try {
                        int number = Integer.parseInt(input.substring(7));
                        if (number > list.size() || number <= 0) {
                            throw new InvalidTaskException();
                        }
                        Task task = list.get(number - 1);
                        task.unmark();
                        FileWriter fw = new FileWriter("./duke.txt");
                        for (int i = 0; i < list.size() - 1; i++) {
                            fw.write(list.get(i).writeToFile() + "\r\n");
                        }
                        fw.write(list.get(list.size() - 1).writeToFile());
                        fw.close();
                        System.out.println("    ____________________________________________________________");
                        System.out.println("     " + "OK, I've marked this task as not done yet:");
                        System.out.println("       " + task.toString());
                        System.out.println("    ____________________________________________________________\n");
                    } catch (InvalidTaskException e) {
                        System.out.println("    ____________________________________________________________");
                        System.out.println("     ☹ OOPS!!! This task does not exist :O");
                        System.out.println("    ____________________________________________________________\n");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else if (input.startsWith("delete ") && input.length() > 7 && input.substring(7).matches("\\d+")) {
                    try {
                        int number = Integer.parseInt(input.substring(7));
                        if (number > list.size() || number <= 0) {
                            throw new InvalidTaskException();
                        }
                        Task task = list.remove(number - 1);
                        FileWriter fw = new FileWriter("./duke.txt");
                        for (int i = 0; i < list.size() - 1; i++) {
                            fw.write(list.get(i).writeToFile() + "\r\n");
                        }
                        if (list.size() > 0) {
                            fw.write(list.get(list.size() - 1).writeToFile());
                        }
                        fw.close();
                        System.out.println("    ____________________________________________________________");
                        System.out.println("     Noted. I've removed this task:");
                        System.out.println("       " + task.toString());
                        System.out.println("     Now you have " + list.size() + " tasks in the list.");
                        System.out.println("    ____________________________________________________________\n");
                    } catch (InvalidTaskException e) {
                        System.out.println("    ____________________________________________________________");
                        System.out.println("     ☹ OOPS!!! This task does not exist :O");
                        System.out.println("    ____________________________________________________________\n");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                else {
                    throw new InvalidTextException();
                }
            } catch (InvalidTextException e) {
                System.out.println("    ____________________________________________________________");
                System.out.println("     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                System.out.println("    ____________________________________________________________\n");
            } finally {
                input = scan.nextLine();
            }
        }
        System.out.println("    ____________________________________________________________");
        System.out.println("     " + "Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________");
    }
     */
}