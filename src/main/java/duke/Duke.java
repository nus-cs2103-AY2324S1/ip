package duke;

import java.util.Scanner;
public class Duke {
    protected static String name = "Alfred";

    protected static String[] list = new String[100];
    protected static int counter = 0;

    public static void println() {
        System.out.println("____________________________________________________________");
    }

    public static String getName() {
        return Duke.name;
    }
    public static int getCounter(){
        return Duke.counter;
    }

    public static void incrementCounter(){
        Duke.counter++;
    }

    public static void setList(String item){
        list[getCounter()] = item;

    }

    public static int listLength(){
        return list.length;
    }

    public static String listOutput(int i){
        return list[i];
    }

    public static void main(String[] args) {
        Scanner scanIn = new Scanner(System.in);

        println();
        String sf1 = String.format("Hello I'm %s, your personal assistant.",getName());
        System.out.println(sf1);
        System.out.println("What can I do for you today, sir?");
        println();




        while (true) {

            String text = scanIn.nextLine();
            switch (text){

                case "list":
                    println();
                    System.out.println();
                    for (int i = 0; i < listLength() ; i++){
                        if (listOutput(i) == null) {
                            break;
                        } else {
                            System.out.println(i + 1 + ". " + listOutput(i));
                        }
                    }
                    println();
                    continue;
                case "bye":

                    println();
                    System.out.println("Goodbye. Hope to be of service again soon!");
                    println();
                    break;

                default:
                    setList(text);
                    incrementCounter();
                    println();
                    System.out.println("I have added '" + text + "' into your tasks list, sir.");
                    println();
                    continue;

            }
            break;

        }

    }
}


