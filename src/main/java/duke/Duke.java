package duke;

import java.util.Scanner;
public class Duke {
    protected static String name = "Alfred";

    protected static Task[] list = new Task[100];
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

    public static void setList(Task item){
        list[getCounter()] = item;

    }

    public static int listLength(){
        return list.length;
    }

    public static Task listOutput(int i){
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


            if (text.equals("list")) {
                println();
                System.out.println();
                for (int i = 0; i < listLength(); i++) {
                    if (listOutput(i) == null) {
                        break;
                    } else {
                        String sf2 = String.format("%d. [%s] %s", i+1, listOutput(i).getStatusIcon(), listOutput(i));
                        System.out.println(sf2);
                        }
                    }
                println();
                continue;
            }

            else if (text.contains("unmark")) {
                int numToUnmark = Integer.parseInt(text.substring(7)) - 1;
                listOutput(numToUnmark).markAsIncomplete();
                println();
                System.out.println("Alright! I'll uncheck this task for you: ");
                String sf4 = String.format("\t [%s] %s",listOutput(numToUnmark).getStatusIcon(), listOutput(numToUnmark));
                System.out.println(sf4);
                println();

                continue;
            }

            else if (text.contains("mark")) {
                int numToMark = Integer.parseInt(text.substring(5)) - 1;
                listOutput(numToMark).markAsComplete();
                println();
                System.out.println("Alright! I'll check this task as complete for you: ");
                String sf3 = String.format("\t [%s] %s",listOutput(numToMark).getStatusIcon(), listOutput(numToMark));
                System.out.println(sf3);
                println();

                continue;
            }


            else if (text.equals("bye"))   {

                println();
                System.out.println("Goodbye. Hope to be of service again soon!");
                println();
                break;

            } else {
                Task task = new Task(text);
                setList(task);
                incrementCounter();
                println();
                System.out.println("I have added '" + text + "' into your tasks list, sir.");
                println();
                continue;
            }

        }


    }

}



