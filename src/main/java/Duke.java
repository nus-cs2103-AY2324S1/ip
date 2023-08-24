import java.util.Scanner;
public class Duke {
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
        Scanner echoObject = new Scanner(System.in);
        String echo = echoObject.nextLine();
        while(!echo.equals("bye")) {
            if(!echo.equals("list")) {
                String[] parts = echo.split(" ");
                if (!parts[0].equals("mark")) {
                    if(!parts[0].equals("unmark")) {
                        if(parts[0].equals("deadline")) {
                            String removeDdl = echo.replace("deadline", "");
                            String removeBy = removeDdl.replace("by", "");
                            String[] ddl = removeBy.split("/");
                            Deadline x = new Deadline(ddl[0], ddl[1]);
                            System.out.println("____________________________________________________________");
                            System.out.println("Okk... I've... I've added this task:");
                            tasks.addToList(x);
                            System.out.println(x.toString());
                            tasks.numOfTask();
                            System.out.println("____________________________________________________________");
                        }
                        if(parts[0].equals("event")) {
                            String removeEvent = echo.replace("event", "");
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
                        }
                        if(parts[0].equals("todo")) {
                            String removeTodo = echo.replace("todo", "");
                            ToDo x = new ToDo(removeTodo);
                            System.out.println("____________________________________________________________");
                            System.out.println("Okk... I've... I've added this task:");
                            tasks.addToList(x);
                            System.out.println(x.toString());
                            tasks.numOfTask();
                            System.out.println("____________________________________________________________");
                        }
                    } else { //equals unmarked
                        int taskNum = Integer.parseInt(parts[1]);
                        System.out.println("____________________________________________________________");
                        System.out.println("Okk... this is not done yet (ᗒᗣᗕ)՞");
                        tasks.markUndone(taskNum - 1);
                        System.out.println(tasks.printTask(taskNum - 1));
                        System.out.println("____________________________________________________________");
                    }
                } else { // equals mark
                    int taskNum = Integer.parseInt(parts[1]);
                    tasks.markDone(taskNum - 1);
                    System.out.println("____________________________________________________________");
                    System.out.println("O...Omedeto! I have... have marked this as done!!o(〃’▽’〃)o");
                    System.out.println(tasks.printTask(taskNum - 1));
                    System.out.println("____________________________________________________________");
                }
            } else { //equals list
                System.out.println("____________________________________________________________");
                System.out.println("Here are the tasks in your list:");
                tasks.printList();
                System.out.println("____________________________________________________________");
            }
            Scanner nextEchoObject = new Scanner(System.in);
            echo = nextEchoObject.nextLine();
        }
        System.out.println("____________________________________________________________");
        System.out.println("B... b...bye bye!... And ... see you! (〃ω〃)");
        System.out.println("____________________________________________________________");


    }
}
