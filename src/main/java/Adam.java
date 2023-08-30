import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This is the main java class that contains instructions to create the chatbot Task manager Adam
 */
public class Adam {
    String home = System.getProperty("user.home");
    java.nio.file.Path path = java.nio.file.Paths.get(home, "Objtestdrive.txt");

    boolean directoryExists = java.nio.file.Files.exists(path);
    /**
     * This array holds all the Task that has been added
     */
    protected ArrayList<Task> array = new ArrayList<>();
    /**
     * This boolean is used to indicate whether or not the program is still running
     */
    protected boolean running = true;
    /**
     * This is used to take in user input
     */
    protected Scanner scaner = new Scanner(System.in);

    /**
     * This method reads from a file in the harddisk and puts an array inside the arraylist variable from it
     */
    public void read() {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(path.toFile()));
            array = (ArrayList<Task>) in.readObject();
            in.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * This method writes an array into a file in the harddisk
     */
    public  void write() {
        try {
            ObjectOutputStream ob = new ObjectOutputStream(new FileOutputStream(path.toFile()));
            ob.writeObject(array);
            ob.flush();
            ob.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * This method is used to create or read a file when first starting the program
     */
    public void initialize() {
        if(directoryExists) {
            read();
        } else {
            write();
        }
    }

    /**
     * This method is used to delete Items of the class or subclass Task from the Task array
     * @param num tells the method which item to remove from the array
     */
    public void deleteTask(int num) {
        Task curr = array.get(num-1);
        array.remove(num-1);
        System.out.println("I have removed the Task:");
        System.out.println(curr.toString());
        System.out.println(String.format("Now you have %d tasks in this list", array.size()));
    }

    /**
     * this method adds a todo object to the Task array
     * @param text this is the description of the todo
     */
    public void addTodo(String text) {
        ToDos curr = new ToDos(text);
        array.add(curr);
        System.out.println("Got it i have added this todo to the list:");
        System.out.println(curr.toString());
        System.out.println(String.format("Now you have %d tasks in this list", array.size()));
    }

    /**
     * This method is used to add a Deadline object to the Task array
     * @param text this param is used to describe the deadline task
     * @param by this param gives information on when the task is due
     */
    public void addDeadline(String text, String by) {
        Deadlines curr = new Deadlines(text, by);
        array.add(curr);
        System.out.println("Got it i have added this todo to the list:");
        System.out.println(curr.toString());
        System.out.println(String.format("Now you have %d tasks in this list", array.size()));
    }

    /**
     * This method adds an Event object to the Task array
     * @param text This param describes the event task
     * @param from This param gives information on when the event starts
     * @param to this param gives information on when the event ends
     */
    public void addEvent(String text, String from, String to) {
        Events curr = new Events(text, from, to);
        array.add(curr);
        System.out.println("Got it i have added this todo to the list:");
        System.out.println(curr.toString());
        System.out.println(String.format("Now you have %d tasks in this list", array.size()));
    }

    /**
     * This method is used to display all the Task available inside the Task array
     */
    public void list() {
        System.out.println("Here are the task in your lists:");
        int count = 1;
        for (Task item: array) {
            System.out.println(count + ". " + item.toString());
            count++;
        }
    }

    /**
     * This method is used to throw exceptions caused by incorrect input and also call the add Tasks methods
     * like addTodo addEvent addDedaline according to their designated inputs
     * @param tokens This param is an Array of Strings that contains words in each index and are seperated
     *               based of empty spaces
     * @param item This param holds the description of the Tasks
     * @param input This param specifies the specific kinds of Task it wants to add
     */
    public void taskCall(String[]tokens, String item, String input) {
        if (tokens.length == 1) {
            throw new NoDescriptionException();
        }
        switch (input) {
            case "todo":
                addTodo(item);
                break;
            case "deadline":
                String[] by = item.split(" /by ");
                if (by.length != 2) {
                    throw new DeadlineException();
                }
                addDeadline(by[0], by[1]);
                break;
            case "event":
                String[] divide1 = item.split(" /from ");
                if (divide1.length != 2) {
                    throw new EventsException();
                }
                String text = divide1[0];
                String[] divide2 = divide1[1].split(" /to ");
                if (divide2.length != 2) {
                    throw new EventsException();
                }
                String from = divide2[0];
                String to = divide2[1];
                addEvent(text, from, to);
                break;
            default:
                System.out.println("Wrong input");
        }
    }

    /**
     * This method is used to throw exceptions when the wrong input is used and call the correct editing method
     * according to its text param such as mark unmark and delete
     * @param tokens This param is an Array of Strings that contains words in each index and are seperated
     *               based of empty spaces
     * @param text This param specifies the specific kinds of editing methods it wants to use
     */
    public void edit(String[] tokens, String text) {
        if (tokens.length != 2) {
            throw new NumberException();
        }
        if (!tokens[1].matches("[0-9]+")) {
            throw new NumberException();
        }
        int number = Integer.valueOf(tokens[1]);
        if (number > array.size()) {
            throw new OutOfBoundsException();
        }
        Task curr = array.get(number - 1);
        switch (text) {
            case "mark":
                curr.markAsDone();
                break;
            case "unmark":
                curr.unmarkAsDone();
                break;
            case "delete":
                deleteTask(number);
                break;
            default:
                System.out.println("Wrong input");
        }
    }

    /**
     * This method is used to throw exceptions and call the correct single word methods such as bye and list
     * according to its input param
     * @param tokens This param is an Array of Strings that contains words in each index and are seperated
     *               based of empty spaces
     * @param input This param specifies wheteher it wants to call the bye or list method
     */
    public void single(String[] tokens, String input) {
        if (tokens.length > 1) {
            throw new OneWordException();
        }
        switch (input) {
            case "bye":
                bye();
                break;
            case "list":
                list();
                break;
            default:
                System.out.println("Wrong input");
        }

    }

    /**
     * This method receives the user input and checks if they are the same as the predetermined command words
     * and calls the appropriate method according to what type of command it is. This method also catches all the
     * exception specific to the Adam chatbot and prints out the approriate responds and instructions. This method also
     * starts the chabot
     */
    public void start() {
        initialize();
        System.out.println("Hello! I am Adam\n" + "What can I do for you?");
            while (running) {
                String li = scaner.nextLine();
                String[] tokens = li.split(" ");
                int length = tokens[0].length();
                String item = li.substring(length, li.length());
                try {
                    switch (tokens[0]) {
                        case "bye":
                        case "list":
                            single(tokens, tokens[0]);
                            break;
                        case "todo":
                        case "deadline":
                        case "event":
                            taskCall(tokens, item, tokens[0]);
                            write();
                            break;
                        case "mark":
                        case "unmark":
                        case "delete":
                            edit(tokens, tokens[0]);
                            write();
                            break;
                        default:
                                throw new AdamException();
                    }
                } catch (OutOfBoundsException e) {
                    System.out.println("OOPS!!! The number you put in is more than the current item in your list");
                } catch (NumberException e) {
                    System.out.println("OOPS!!! You need to follow this command by a number");
                } catch (EventsException e) {
                    System.out.println("OOPS!!! You need to add one /from and one /to command");
                } catch (DeadlineException e) {
                    System.out.println("OOPS!!! You need to add one /by command to indicate by when deadline is");
                } catch (NoDescriptionException e) {
                    System.out.println("OOPS!!! You need to add a description to these tasks");
                } catch (OneWordException e) {
                    System.out.println("OOPS!!! Type in the first word you just entered");
                } catch (AdamException e) {
                    System.out.println("OOPS!!! I don't know what this means");
                }
            }
    }

    /**
     * This method stops the chatbot and ending the whole program
     */
    public void bye() {
        running = false;
        System.out.println("Bye. Hope to see you again soon!");
    }
    public static void main(String[] args) {
        Adam test = new Adam();
        test.start();
    }
}
