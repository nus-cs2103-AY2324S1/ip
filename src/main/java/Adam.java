import java.util.ArrayList;
import java.util.Scanner;
public class Adam {
    ArrayList<Task> array = new ArrayList<>();
    boolean running = true;
    Scanner scaner = new Scanner(System.in);

    public void add(String text){
        array.add(new Task(text));
        System.out.println("added: " + text);
    }

    public void addTodo(String text) {
        ToDos curr = new ToDos(text);
        array.add(curr);
        System.out.println("Got it i have added this todo to the list:");
        System.out.println(curr.toString());
        System.out.println(String.format("Now you have %d tasks in this list", array.size()));
    }

    public void addDeadline(String text, String by) {
        Deadlines curr = new Deadlines(text, by);
        array.add(curr);
        System.out.println("Got it i have added this todo to the list:");
        System.out.println(curr.toString());
        System.out.println(String.format("Now you have %d tasks in this list", array.size()));
    }

    public void addEvent(String text, String from, String to) {
        Events curr = new Events(text, from, to);
        array.add(curr);
        System.out.println("Got it i have added this todo to the list:");
        System.out.println(curr.toString());
        System.out.println(String.format("Now you have %d tasks in this list", array.size()));
    }

    public void list(){
        System.out.println("Here are the task in your lists:");
        int count = 1;
        for(Task item: array){
            System.out.println(count + ". " + item.toString());
            count++;
        }
    }

    public void start(){
        System.out.println("Hello! I am Adam\n" + "What can I do for you?");
            while (running) {
                String li = scaner.nextLine();
                String[] tokens = li.split(" ");
                //check if its divided by a space or not
                    // check if its marked
                    switch(tokens[0]) {
                        case "bye":
                            try {
                                if(tokens.length>1){
                                    throw new AdamException();
                                }
                                bye();
                            } catch (AdamException e){
                                System.out.println("OOPS!!! To End this session please type bye");
                            }
                            break;
                        case "list":
                            try {
                                if(tokens.length>1){
                                    throw new AdamException();
                                }
                                list();
                            }catch (AdamException e){
                                System.out.println("OOPS!!! To see the current list of Tasks type list");
                            }
                            break;
                        case "todo":
                            try {
                                if(tokens.length == 1){
                                    throw new AdamException();
                                }
                                int length = tokens[0].length();
                                String item = li.substring(length+1, li.length());
                                addTodo(item);
                            } catch (AdamException e) {
                                System.out.println("OOPS!!! The description of a todo cannot be empty.");
                            }
                            break;
                        case "deadline":
                            try {
                                if(tokens.length == 1){
                                    throw new AdamException();
                                }
                                int length1 = tokens[0].length();
                                String item1 = li.substring(length1 + 1, li.length());
                                String[] by = item1.split(" /by ");
                                try {
                                    if (by.length == 2) {
                                        addDeadline(by[0], by[1]);
                                    } else{
                                        throw new AdamException();
                                    }
                                } catch (AdamException e){
                                    System.out.println("You need to add only one /by in the input before you enter");
                                }
                            } catch (AdamException e) {
                                System.out.println("OOPS!!! The description of deadline cannot be empty");
                            }
                            break;
                        case "event":
                            try {
                                if(tokens.length == 1){
                                    throw new AdamException();
                                }
                                int length2 = tokens[0].length();
                                String item2 = li.substring(length2 + 1, li.length());
                                String[] divide1 = item2.split(" /from ");
                                if (divide1.length == 2) {
                                    String text = divide1[0];
                                    String[] divide2 = divide1[1].split(" /to ");
                                    if (divide2.length == 2) {
                                        String from = divide2[0];
                                        String to = divide2[1];
                                        addEvent(text, from, to);
                                    } else{
                                        try{
                                            throw new AdamException();
                                        } catch (AdamException e) {
                                            System.out.println("There are too many /to");
                                        }
                                    }
                                } else {
                                    try{
                                        throw new AdamException();
                                    } catch (AdamException e){
                                        System.out.println("There are too many /from");
                                    }
                                }
                            } catch(AdamException e){
                                System.out.println("OOPS!!! The description of event cannot be empty");
                            }
                            break;
                        case "mark":
                            try {
                                if (tokens.length == 2) {
                                    if (tokens[1].matches("[0-9]+")) {
                                        int number = Integer.valueOf(tokens[1]);
                                        if (number <= array.size()) {
                                            Task curr = array.get(number - 1);
                                            curr.markAsDone();
                                        } else {
                                            System.out.println("This task does not exist");
                                        }
                                    } else {
                                        throw new AdamException();
                                    }
                                } else {
                                    throw new AdamException();
                                }
                            } catch (AdamException e) {
                                System.out.println("Mark is only supposed to be accompanied by " +
                                        "one other multi digit number");
                            }
                            break;
                        case "unmark" :
                            try {
                                if (tokens.length == 2) {
                                    if (tokens[1].matches("[0-9]+")) {
                                        int number = Integer.valueOf(tokens[1]);
                                        if (number <= array.size()) {
                                            Task curr = array.get(number - 1);
                                            curr.unmarkAsDone();
                                        } else {
                                            System.out.println("This task does not exist");
                                        }
                                    } else {
                                        throw new AdamException();
                                    }
                                }  else {
                                    throw new AdamException();
                                }
                            } catch (AdamException e) {
                                System.out.println("Unmark is only supposed to be accompanied by " +
                                        "one other multi digit number");
                            }
                            break;
                        default:
                            try {
                                throw new AdamException();
                            } catch (AdamException e){
                                System.out.println("OOPS!!! I don't know what this means");
                            }
                    }
            }
    }
    public void bye() {
        running = false;
        System.out.println("Bye. Hope to see you again soon!");
    }
    public static void main(String[] args) {
        Adam test = new Adam();
        test.start();
    }
}
