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
        System.out.println(String.format("Hello I'm %s, your personal assistant.",getName()));
        System.out.println("What can I do for you today, sir?");
        println();


        while (true) {



            String text = scanIn.nextLine();


            if (text.length() > 3 && text.substring(0,4).equals("list")) {
                println();

                for (int i = 0; i < listLength(); i++) {
                    if (listOutput(i) == null) {
                        break;
                    } else {
                        System.out.println(String.format("%d. [%s] [%s] %s", i+1, listOutput(i).tag, listOutput(i).getStatusIcon(), listOutput(i)));
                        }
                    }
                println();
                continue;
            }
            // never use .contains as the command "unmark" may be present in other commands//
            else if (text.length() > 5 && text.substring(0,6).equals("unmark")) {
                String[] splitText = text.split(" ");
                int numToUnmark = Integer.parseInt(splitText[1]) - 1;
                listOutput(numToUnmark).markAsIncomplete();
                println();
                System.out.println("Alright! I'll uncheck this task for you: ");
                System.out.println(String.format("\t [%s] [%s] %s",listOutput(numToUnmark).tag, listOutput(numToUnmark).getStatusIcon(), listOutput(numToUnmark)));
                println();

                continue;
            }

            else if (text.length() > 3 && text.substring(0,4).equals("mark")) {
                String[] splitText = text.split(" ");
                int numToMark = Integer.parseInt(splitText[1]) - 1;
                listOutput(numToMark).markAsComplete();
                println();
                System.out.println("Alright! I'll check this task as complete for you: ");
                System.out.println(String.format("\t [%s] [%s] %s",listOutput(numToMark).tag, listOutput(numToMark).getStatusIcon(), listOutput(numToMark)));
                println();

                continue;
            }


            else if (text.equals("bye")) {

                println();
                System.out.println("Goodbye. Hope to be of service again soon!");
                println();
                break;
            } else if (text.length() > 3 && text.substring(0,4).equals("todo")){
                String description = text.substring(5);
                Todo todo = new Todo(description);
                setList(todo);
                incrementCounter();
                println();
                System.out.println("Noted Sir. I've added this task to your list: ");
                System.out.println(String.format("\t [%s] [%s] %s",todo.tag,todo.getStatusIcon(),todo.toString()));
                System.out.println(String.format("As of now, you have %d tasks on the agenda.",getCounter()));
                println();
            } else if (text.length() > 7 && text.substring(0,8).equals("deadline")){
                String[] splitText = text.split("/");
                String description = splitText[0].substring(9);
                String deadline = splitText[1].trim().substring(3);
                Deadline dl = new Deadline(description,deadline);
                setList(dl);
                incrementCounter();
                println();
                System.out.println("Noted Sir. I've added this task to your list: ");
                System.out.println(String.format("\t [%s] [%s] %s",dl.tag,dl.getStatusIcon(),dl.toString()));
                System.out.println(String.format("As of now, you have %d tasks on the agenda.",getCounter()));
                println();
            } else if (text.length() > 4 && text.substring(0,5).equals("event")){
                String[] splitText = text.split("/");
                String description = splitText[0].substring(6);
                String start = splitText[1].trim().substring(5);
                String end = splitText[2].trim().substring(3);
                Event event = new Event(description,start,end);
                setList(event);
                incrementCounter();
                println();
                System.out.println("Noted Sir. I've added this task to your list: ");
                System.out.println(String.format("\t [%s] [%s] %s",event.tag,event.getStatusIcon(),event.toString()));
                System.out.println(String.format("As of now, you have %d tasks on the agenda.",getCounter()));
                println();

            };

//             else {
//                Task task = new Task(text);
//                setList(task);
//                incrementCounter();
//                println();
//                System.out.println("I have added '" + text + "' into your tasks list, sir.");
//                println();
//                continue;
//            }

        }


    }

}



