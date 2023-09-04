public class Noel {

    static String HELLO_MSG = " Hello! I'm Noel!\nWhat can I do for you?";
    static String BYE_MSG = " Bye. Hope to see you again soon!";
    private final Ui ui;
    private final Storage storage;
    private Tasklist tasks;

    public Noel(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new Tasklist(storage.load());
        } catch (NoelException e) {
            ui.showLoadingError();
            tasks = new Tasklist();
        } finally {
            storage.updateFile();
        }
    }

    public String chatHelper() {
        return this.ui.getNextLine();
    }

    public static void printFunction(String message){
        String filler = "____________________________________________________________";
        System.out.println(filler + "\n" + message + "\n" + filler);
    }

    public void run() {

        printFunction(HELLO_MSG);
        String nextLine = chatHelper();
        String command;

        while (!nextLine.equals("bye")){

            if (nextLine.equals("list")){
                tasks.printTaskList();

            } else if (nextLine.contains(" ")) {

                String[] result = nextLine.split(" ");

                command = result[0];

                switch (command) {

                    case "mark": {
                        int taskNum = Integer.parseInt(result[1]);
                        taskNum = taskNum - 1;
                        tasks.markAsDone(taskNum);
                        storage.writeToFile(tasks.getTaskAsList());
                        break;
                    }
                    case "unmark": {
                        int taskNum = Integer.parseInt(result[1]);
                        tasks.unMark(taskNum);
                        storage.writeToFile(tasks.getTaskAsList());
                        break;
                    }
                    case "todo":
                        result = nextLine.split("todo ");
                        if (result.length == 0) {
                            System.out.println("OOPS!!! The description of a todo cannot be empty.");
                        } else {
                            tasks.addToDo(result[1]);
                            storage.writeToFile(tasks.getTaskAsList());
                        }
                        break;

                    case "deadline":
                        result = nextLine.split("deadline ");
                        if (result.length == 0) {
                            System.out.println("OOPS!!! The description of a deadline cannot be empty.");
                        } else {
                            String[] deadlineHelper = result[1].split(" /by ");
                            if (deadlineHelper.length == 2) {
                                tasks.addDeadline(deadlineHelper[0], deadlineHelper[1]);
                                storage.writeToFile(tasks.getTaskAsList());
                            } else {
                                System.out.println("OOPS!!! Remember to add the date/description");
                            }
                        }
                        break;

                    case "event":
                        result = nextLine.split("event ");
                        if (result.length == 0) {
                            System.out.println("OOPS!!! The description of a event cannot be empty.");
                        } else {
                            String[] eventsHelper = result[1].split(" /from ");
                            command = eventsHelper[0];
                            if (eventsHelper.length == 2) {
                                eventsHelper = eventsHelper[1].split(" /to ");
                                if (eventsHelper.length == 2) {
                                    tasks.addEvent(command, eventsHelper[0], eventsHelper[1]);
                                    storage.writeToFile(tasks.getTaskAsList());
                                } else {
                                    System.out.println("Insufficient commands provided!");
                                }
                            } else {
                                System.out.println("Insufficient commands provided!");
                            }
                        }
                        break;

                    case "delete":
                        result = nextLine.split("delete ");
                        if (result.length == 0) {
                            System.out.println("OOPS!!! The description of a delete cannot be empty.");
                        } else {
                            int intToRemove = Integer.parseInt(result[1]) - 1;
                            Task taskToDel = tasks.get(intToRemove);
                            tasks.remove(intToRemove);

                            String delStart = "Noted. I've removed this task:\n";
                            String delEnd = "Now you have " + tasks.size() + " tasks in the list.";
                            printFunction(delStart + taskToDel + "\n" + delEnd);
                            storage.writeToFile(tasks.getTaskAsList());
                            break;
                        }

                    default:
                        System.out.println("Invalid Option!");
                }
            } else {
                System.out.println("Invalid Option!");
            }
            nextLine = ui.getNextLine();
        }
        printFunction(BYE_MSG);
    }

    public static void main(String[] args) {
        new Noel("./data/noel.txt").run();
    }
}

