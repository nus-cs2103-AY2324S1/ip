import jdk.jfr.Event;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Scanner;
public class Duke {
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
                if(tokens.length >= 2){
                    // check if its marked
                    switch(tokens[0]) {
                        case "todo":
                           int length = tokens[0].length();
                           String item = li.substring(length+1, li.length());
                           addTodo(item);
                            break;
                        case "deadline":
                            int length1 = tokens[0].length();
                            String item1 = li.substring(length1+1, li.length());
                            String[] by = item1.split(" /by ");
                            if(by.length == 2) {
                                addDeadline(by[0], by[1]);
                            }
                            break;
                        case "event":
                            int length2 = tokens[0].length();
                            String item2 = li.substring(length2+1, li.length());
                            String[] divide1 = item2.split(" /from ");
                            if(divide1.length == 2){
                                String text = divide1[0];
                                String[] divide2 = divide1[1].split(" /to ");
                                if(divide2.length==2){
                                    String from = divide2[0];
                                    String to = divide2[1];
                                    addEvent(text, from, to);
                                }
                            }

                            break;
                        case "mark":
                            if(tokens.length == 2){
                                if(tokens[1].matches("[0-9]+")) {
                                    int number = Integer.valueOf(tokens[1]);
                                    if(number <= array.size()){
                                        Task curr = array.get(number-1);
                                        curr.markAsDone();
                                    } else {
                                        System.out.println("This task does not exist");
                                    }
                                } else {
                                    this.add(li);
                                }
                            }
                            break;
                        case "unmark" :
                            if(tokens.length == 2){
                                if(tokens[1].matches("[0-9]+")) {
                                    int number = Integer.valueOf(tokens[1]);
                                    if(number <= array.size()){
                                        Task curr = array.get(number-1);
                                        curr.unmarkAsDone();
                                    } else {
                                        System.out.println("This task does not exist");
                                    }
                                } else {
                                    this.add(li);
                                }
                            }
                            break;
                    }
                } else{
                        switch (li) {
                            case "bye":
                                bye();
                                break;
                            case "list":
                                list();
                                break;
                            default:
                                this.add(li);
                        }
                }
            }
    }
    public void bye() {
        running = false;
        System.out.println("Bye. Hope to see you again soon!");
    }
    public static void main(String[] args) {
        Duke test = new Duke();
        test.start();
    }
}
