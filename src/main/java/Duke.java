import java.io.FileNotFoundException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Locale;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Duke {
    private Ui ui;
//    public Duke(String filePath) {
//        this.ui = new Ui();
//    }
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("____________________________________________________________");
        System.out.println("Ko...ko...ko..nichi...wa!!! I... I am Gotoh... Hitori desu! Σ(っ °Д °;)っ");
        System.out.println("You... can call me... Bocchi. They usually... call me Bocchi chan...");
        System.out.println("What can... can I do for you? (°□°;) ");
        System.out.println("____________________________________________________________");
        TaskList tasks = new TaskList();
        try {
            tasks.readToList("./data/duke.txt");
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        Scanner echoObject = new Scanner(System.in);
        String echo = echoObject.nextLine();
        while(!echo.equals("bye")) {
            try {
                    if (!echo.equals("list")) {
                        String[] parts = echo.split(" ");
                        if (parts[0].equals("mark")) {
                            if (parts.length == 1) {
                                throw new DukeException("AAA...AGHHH!!! The number to be marked done... c...cannot be empty!!!°(°ˊДˋ°) °");
                            }
                            int taskNum = Integer.parseInt(parts[1]);
                            tasks.markDone(taskNum - 1);
                            System.out.println("____________________________________________________________");
                            System.out.println("O...Omedeto! I have... have marked this as done!!o(〃’▽’〃)o");
                            System.out.println(tasks.printTask(taskNum - 1));
                            System.out.println("____________________________________________________________");

                        } else if (parts[0].equals("unmark")) {
                            if (parts.length == 1) {
                                throw new DukeException("AAA...AGHHH!!! The number to be unmarked done... c...cannot be empty!!!°(°ˊДˋ°) °");
                            }
                            int taskNum = Integer.parseInt(parts[1]);
                            System.out.println("____________________________________________________________");
                            System.out.println("Okk... this is not done yet (ᗒᗣᗕ)՞");
                            tasks.markUndone(taskNum - 1);
                            System.out.println(tasks.printTask(taskNum - 1));
                            System.out.println("____________________________________________________________");
                        } else if (parts[0].equals("deadline")) {
                            String removeDdl = echo.replace("deadline", "");
                            if (removeDdl.equals("")) {
                                throw new DukeException("AAA...AGHHH!!! The description of a deadline... c...cannot be empty!!!°(°ˊДˋ°) °");
                            }
                            String removeBy = removeDdl.replace("by", "");
                            String[] ddl = removeBy.split("/");
                            String[] split = ddl[1].split(" ");
                            try {
                                LocalDate by = LocalDate.parse(split[1]);
                                Deadline x = new Deadline(ddl[0], by);
                                System.out.println("____________________________________________________________");
                                System.out.println("Okk... I've... I've added this task:");
                                tasks.addToList(x);
                                System.out.println(x.toString());
                                tasks.numOfTask();
                            } catch (DateTimeException e) {
                                throw new DukeException("Th...th...the ddate you have inputed is invalid!!");
                            }
                            System.out.println("____________________________________________________________");
                        } else if (parts[0].equals("event")) {
                            String removeEvent = echo.replace("event", "");
                            if (removeEvent.equals("")) {
                                throw new DukeException("AAA...AGHHH!!! The description of an event... c...cannot be empty!!!°(°ˊДˋ°) °");
                            }
                            String removeFrom = removeEvent.replace("from", "");
                            String removeTo = removeFrom.replace("to", "");
                            String[] event = removeTo.split("/");
                            Event x = new Event(event[0], event[1], event[2]);
                            System.out.println("____________________________________________________________");
                            System.out.println("Okk... I've... I've added this task:");
                            tasks.addToList(x);
                            System.out.println(x.toString());
                            tasks.numOfTask();
                            System.out.println("____________________________________________________________");
                        } else if (parts[0].equals("todo")) {
                            String removeTodo = echo.replace("todo", "");
                            if (removeTodo.equals("")) {
                                throw new DukeException("AAA...AGHHH!!! The description of the todo... c...cannot be empty!!!°(°ˊДˋ°) °");
                            }
                            ToDo x = new ToDo(removeTodo);
                            System.out.println("____________________________________________________________");
                            System.out.println("Okk... I've... I've added this task:");
                            tasks.addToList(x);
                            System.out.println(x.toString());
                            tasks.numOfTask();
                            System.out.println("____________________________________________________________");
                        } else if (parts[0].equals("delete")) {
                            if (parts.length == 1) {
                                throw new DukeException("AAA...AGHHH!!! The number to be deleted... c...cannot be empty!!!°(°ˊДˋ°) °");
                            }
                            int taskNum = Integer.parseInt(parts[1]);
                            System.out.println("____________________________________________________________");
                            System.out.println("O...Okk... I've re...removed this task:");
                            tasks.deleteTask(taskNum - 1);
                            tasks.numOfTask();
                            System.out.println("____________________________________________________________");
                        } else {
                            throw new DukeException("AAA...AGHHH!!! Go...Gomenasaiii!!! I don't understand!!!°(°ˊДˋ°) °");
                        }
                    } else { //equals list
                        System.out.println("____________________________________________________________");
                        System.out.println("H...here are the tasks in your list:");
                        tasks.printList();
                        System.out.println("____________________________________________________________");
                    }
            } catch (DukeException e){
                System.out.println("____________________________________________________________");
                System.out.println(e.getMessage());
                System.out.println("____________________________________________________________");
            }
            Scanner nextEchoObject = new Scanner(System.in);
            echo = nextEchoObject.nextLine();
        }
        try {
            File save = new File("./data/duke.txt");
            new FileWriter("./data/duke.txt", false).close();
            tasks.writeToFile("./data/duke.txt");
        } catch (IOException e) {
            System.out.println("error: " + e.getMessage());
        }
        System.out.println("____________________________________________________________");
        System.out.println("B... b...bye bye!... And ... see you! (〃ω〃)");
        System.out.println("____________________________________________________________");


    }
}
