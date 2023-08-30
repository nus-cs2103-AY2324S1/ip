import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Bob {
    private ArrayList<Task> taskArr = new ArrayList<>();
    private String name = "Bob";
    private Task[] list = new Task[100];
    private String horizontal = "____________________________________________________________";
    private int count;

    public Bob() {
        this.count = 0;
    }

    public String messageCard(String message) {
        return horizontal + "\n" + message + "\n" + horizontal;
    }
    public void greet() {
        String ln1 = String.format("Hello! I'm %s", this.name);
        String ln2 = "What can I do for you?";
        String ln3 = ln1 + "\n"+ ln2;

        System.out.println(messageCard(ln3));
    }
    public void bye() {
        String ln3 = "Bye. Hope to see you again soon!";
        System.out.println(messageCard(ln3));
    }
    public static void main(String[] args) {
        Bob bob = new Bob();
        bob.fRead("./data/duke.txt");
        bob.bye();
    }

    public void rewriteFile() throws IOException {

        // Open the FileWriter without append mode.
        FileWriter fWriter = new FileWriter("./data/duke.txt");
        // delete whole text.
        fWriter.write("");
        // Close the FileWriter
        fWriter.close();

        // Open the FileWriter in append mode.
        FileWriter fWriter2 = new FileWriter("./data/duke.txt", true);

        for (int i = 0; i < taskArr.size(); i++) {
            Task t = taskArr.get(i);
            if (t instanceof Todo) {
                fWriter2.write("T | " + t.getNumber() + " | " + t.getDescription() + "\n");
            } else if (t instanceof Deadline) {
                Deadline d = (Deadline) t;
                fWriter2.write("D | " + d.getNumber() + " | " + d.getDescription() + " | " + d.getBy() + "\n");
            } else {
                //event
                Event e = (Event) t;
                fWriter2.write("E | " + e.getNumber() + " | " + e.getDescription() + " | " + e.getFrom() + " to " +
                        e.getTo() +"\n");
            }
        }

        // Close the FileWriter2
        fWriter2.close();
    }

    public static void fWrite(String text) {
        try {
            // Open the FileWriter in append mode
            FileWriter fWriter = new FileWriter("./data/duke.txt", true);

            // Write the new text to the file
            fWriter.write(text + "\n");

            // Close the FileWriter
            fWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void fRead(String path) {
        File file = new File(path);
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                String currLine = sc.nextLine();
                String[] wordArr = currLine.split("\\|"); // Escape the | and space characters
                String category = wordArr[0].trim();

                switch (category) {
                case "T":
                    Todo todo = new Todo(wordArr[2].trim());
                    if (wordArr[1].trim().equals("1")) {
                        todo.silentMark();
                    }
                    taskArr.add(todo);
                    break;
                case "D":
                    Deadline dl = new Deadline(wordArr[2].trim(), wordArr[3].trim());
                    if (wordArr[1].trim().equals("1")) {
                        dl.silentMark();
                    }
                    taskArr.add(dl);
                    break;
                case "E":
                    String time = wordArr[3].trim();
                    String[] timeline = time.split("to");
                    Event e = new Event(wordArr[2].trim(), timeline[0].trim(), timeline[1].trim());
                    if (wordArr[1].trim().equals("1")) {
                        e.silentMark();
                    }
                    taskArr.add(e);
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void addTodo(String[] text) throws IllegalChatBotExceptions {
        if (text.length < 2) {
            throw new IllegalChatBotExceptions(messageCard("☹ OOPS!!! The description of a todo cannot be empty."));
        }

        Todo task = new Todo(text[1]);
        //writing to the txt.file
        String addToText = "T | 0 | " + text[1];
        Bob.fWrite(addToText);


        //adding to taskArr
        taskArr.add(task);

        //printing messages
        String message = "Got it. I've added this task: \n" + task +
                "\nNow you have " + taskArr.size() + " tasks in the list.";

        System.out.println(messageCard(message));
    }

    public void addDeadline(String[] textArr) throws IllegalChatBotExceptions {
        if (textArr.length < 2) {
            throw new IllegalChatBotExceptions(messageCard("☹ OOPS!!! The description of a deadline cannot be empty."));
        }

        String text = textArr[1];
        String[] split = text.split(" /by ", 2);
        String desc = split[0];
        String by = split[1];
        Deadline task = new Deadline(desc, by);

        //writing to the txt.file
        String addToText = "D | 0 | " + desc + " | " + task.getBy();
        Bob.fWrite(addToText);

        //add task to taskArr
        taskArr.add(task);

        //printing the messages
        String message = "Got it. I've added this task: \n" + task +
                "\nNow you have " + taskArr.size() + " tasks in the list.";
        System.out.println(messageCard(message));
    }

    public void addEvent(String[] textArr) throws IllegalChatBotExceptions {
        if (textArr.length < 2) {
            throw new IllegalChatBotExceptions(messageCard("☹ OOPS!!! The description of a event cannot be empty."));
        }

        String text = textArr[1];
        String[] slice = text.split(" /from ", 2);
        String desc = slice[0].trim();
        String[] slicess = slice[1].split(" /to ", 2);
        String from = slicess[0].trim();
        String to = slicess[1].trim();

        Event task = new Event(desc, from, to);

        //writing to the txt.file
        String addToText = "E | 0 | " + desc + " | " + task.getFrom() + " to " + task.getTo();
        Bob.fWrite(addToText);

        //add task to taskArr
        taskArr.add(task);

        //print messages
        String message = "Got it. I've added this task: \n" + task +
                "\nNow you have " + taskArr.size() + " tasks in the list. ";
        System.out.println(messageCard(message));
    }

    public void listOut() {
        System.out.println(horizontal);
        System.out.println("Here are the tasks in your list:");

        for (int i = 1; i <= taskArr.size(); i++) {
            System.out.println(i + ". " + taskArr.get(i - 1));
        }

        System.out.println(horizontal);
    }

    public void markTask(int index) throws IllegalChatBotExceptions {
        if (index + 1 > taskArr.size()) {
            throw new IllegalChatBotExceptions(messageCard("The current number of tasks is " + taskArr.size() + ", " +
                    "so you can't mark task " + (index + 1) + "!!."));
        }
        Task task = taskArr.get(index);
        task.markAsDone();
    }

    public void unmarkTask(int index) throws IllegalChatBotExceptions{
        if (index + 1 > taskArr.size()) {
            throw new IllegalChatBotExceptions(messageCard("The current number of tasks is " + taskArr.size() + ", " +
                    "so you can't unmark task " + (index + 1) + "!!."));
        }
        Task task = taskArr.get(index);
        task.unmark();
    }

    public void deleteTask(int index) throws IllegalChatBotExceptions {
        if (index + 1 > taskArr.size()) {
            throw new IllegalChatBotExceptions(messageCard("The current number of tasks is " + taskArr.size() + ", " +
                    "so you can't delete task " + (index + 1) + "!!."));
        }
        Task task = taskArr.get(index);

        //remove task
        taskArr.remove(index);

        //printing messages
        String message = "Noted. I've removed this task: \n" + task + "\nNow you have "
                + taskArr.size() + " tasks in the list.";
        System.out.println(messageCard(message));
    }
}
