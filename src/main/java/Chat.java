import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Chat {
    private UserInput[] toDos;
    private int count;

    public Chat(Scanner sc) {
        this.toDos = new UserInput[100];
        this.count = 0;
    }

    //keep taking in userInput
    //add userInput to the list
    public void addInput(String textInput) {
        UserInput userInput = new UserInput(textInput);
        if (!userInput.text.equals("list") && !userInput.text.equals("mark") && !userInput.text.equals("unmark") ) {
            this.toDos[count] = userInput;
            count++;
            System.out.println("added:" + userInput.text);
        }
    }
    public void addToDo(String task) {
        ToDo toDo = new ToDo(task);
        int taskId = findTask(task);
        toDos[taskId] = toDo;
            System.out.println("GOT IT. ADDED:\n" + toDo.toString());
    }

    public void addDead(String task, String by) {
        Deadline dead = new Deadline(task, by);
        int taskId = findTask(task);
        toDos[taskId] = dead;
        System.out.println("GOT IT. ADDED:\n" + dead.toString());
    }

    public void markTask(int taskNumber) {
        UserInput task = this.toDos[taskNumber - 1];
        task.markAsDone();
        System.out.println("Done. You're Done. \n" + task.toString());
    }

    public void unMarkTask(int taskNumber) {
        UserInput task = this.toDos[taskNumber - 1];
        task.unMark();
        System.out.println("Done. Stop being lazy.\n" + task.toString());
    }


    public void listout() {
        for (int i = 0; i < count; i++) {
            System.out.println((i + 1) + "." + this.toDos[i].toString());
        }
    }

    public int findTask(String task) {
        for (int i = 0; i < count; i++) {
            if (task.equalsIgnoreCase(toDos[i].text)) {
                return i;
            }
        }
        return -1;
    }

    public void addEvent(String task, String from, String to) {
        Event event = new Event(task, from, to);
        int taskId = findTask(task);
        toDos[taskId] = event;
        System.out.println("GOT IT. ADDED:\n" + event.toString());

    }

    public void start() {
        String logo = "____________________________________________________________\n"
                + "YO! The name's Bond, James Bond.  \n"
                + "What can I do for you? \n"
                + "____________________________________________________________\n";
        System.out.println(logo);
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String firstWord = sc.next();
            if (firstWord.equalsIgnoreCase("mark")) {
                int taskNumber = sc.nextInt();
                markTask(taskNumber);
            } else if (firstWord.equalsIgnoreCase("unmark")) {
                int taskNumber = sc.nextInt();
                unMarkTask(taskNumber);
            }
            else {
                String input = sc.nextLine().trim();
                if (firstWord.equalsIgnoreCase("todo")) {
                    addToDo(input);
                } else if (firstWord.equalsIgnoreCase("deadline")) {
                    int byIndex = input.indexOf("/by");
                    String taskDescription = input.substring(0, byIndex).trim();
                    String dueDate = input.substring(byIndex + 4).trim();
                    addDead(taskDescription, dueDate);
                } else if (firstWord.equalsIgnoreCase("event")) {
                    int fromIndex = input.indexOf("/from");
                    int toIndex = input.indexOf("/to");
                    if (fromIndex != -1 && toIndex != -1) {
                        String taskDescription = input.substring(0, fromIndex).trim();
                        System.out.println(taskDescription);
                        String startTime = input.substring(fromIndex + 6, toIndex).trim();
                        String endTime = input.substring(toIndex + 4).trim();
                        addEvent(taskDescription, startTime, endTime);
                    }
                } else if (firstWord.equalsIgnoreCase("bye")) {
                    System.out.println("Bye. Till the next time.");
                    break;
                } else if (firstWord.equalsIgnoreCase("list")) {
                    listout();
                } else {
                    addInput(firstWord + " " + input);
                }
            }
        }
    }
}
