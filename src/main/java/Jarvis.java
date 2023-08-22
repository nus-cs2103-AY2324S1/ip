import java.util.Scanner;
import java.util.ArrayList;

public class Jarvis {
    public static void main(String[] args) {
        String name = "Jarvis";
        String logo = "      **     **     *******   **      ** **  ********\n" +
                "     /**    ****   /**////** /**     /**/** **////// \n" +
                "     /**   **//**  /**   /** /**     /**/**/**       \n" +
                "     /**  **  //** /*******  //**    ** /**/*********\n" +
                "     /** **********/**///**   //**  **  /**////////**\n" +
                " **  /**/**//////**/**  //**   //****   /**       /**\n" +
                "//***** /**     /**/**   //**   //**    /** ******** \n" +
                " /////  //      // //     //     //     // ////////  ";
        System.out.println(logo);
        String line = "____________________________________________________________";
        String greeting = "Good day Sir! I'm ";
        String question = "How can I help you today Sir?";
        String signOff = "Good bye Sir!";
        String listInforming = "Here are the task in your list Sir:";
        String markInforming = "Roger that Sir, I've marked this task as done:";
        String unmarkInforming = "Noted Sir, I've marked this task as NOT done yet:";

        System.out.println(line);
        System.out.println(greeting + name + "!");
        System.out.println(question);
        System.out.println(line);

        ArrayList<Task> taskList = new ArrayList<Task>(); // list of Tasks

        while (true) {
            Scanner userInput = new Scanner(System.in);
            String taskDescription = userInput.nextLine();

            if (taskDescription.equals("list")) { // if "list" is entered
                System.out.println(line);
                System.out.println(listInforming);
                for (int i = 0; i < taskList.size(); i++) { // listing out the current task
                    int count = i + 1;
                    Task currentTask = taskList.get(i);
                    System.out.println(count + "." + currentTask.getTaskString());
                }
                System.out.println(line);

            } else if (taskDescription.equals("bye")) { // if "bye" is entered
                System.out.println(line);
                System.out.println(signOff);
                System.out.println(line);
                break;

            } else if (taskDescription.contains("mark") || taskDescription.contains("unmark")) { // if "mark" or "unmark" is entered
                int taskNum = Integer.parseInt(taskDescription.substring(taskDescription.length() - 1));
                if (taskDescription.contains("unmark")) { // if "unmark" is entered
                    Task currentTask = taskList.get(taskNum - 1);
                    currentTask.setDone(false);

                    System.out.println(line);
                    System.out.println(unmarkInforming);
                    System.out.println(currentTask.getTaskString());
                    System.out.println(line);
                } else { // if "mark" is entered
                    Task currentTask = taskList.get(taskNum - 1);
                    currentTask.setDone(true);

                    System.out.println(line);
                    System.out.println(markInforming);
                    System.out.println(currentTask.getTaskString());
                    System.out.println(line);
                }

            } else { // if any other request
                Task task = new Task(taskDescription);
                taskList.add(task); // add new task to the end of task list
                System.out.println(line);
                System.out.println("added: " + task.description);
                System.out.println(line);
            }
        }
    }
}