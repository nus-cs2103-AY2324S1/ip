import java.util.Scanner;
public class Duke {

    Task[] list = new Task[100];

    private static int totalTodo = 0;

    String divider = "------------------------------------\n";
    String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    String greet = divider +
            "Hello! I'm Khaleelur!\n" +
            "What can I do for you?\n " +
            divider;

    String exit = divider +
            "Bye. Hope to see you again soon!\n" +
            divider;


    public String addTask(String[] taskArray) {
        String action = taskArray[0];
        String task = taskArray[1];
        String by = taskArray[2];
        String from = taskArray[3];
        String to = taskArray[4];

        String res = "";
        switch (action) {
            case "todo" : {
                list[totalTodo] = new ToDo(task);
                res = "Got it. I've added this task : \n" + list[totalTodo].toString() + "\n";
                totalTodo++;
                res += getTaskLeft();
                break;
            }
            case "deadline" : {
                list[totalTodo] = new Deadline(task, by);
                res = "Got it. I've added this task : \n" + list[totalTodo].toString() + "\n";
                totalTodo++;
                res += getTaskLeft();
                break;
            }
            case "event" : {
                list[totalTodo] = new Event(task,from,to);
                res = "Got it. I've added this task : \n" + list[totalTodo].toString() + "\n";
                totalTodo++;
                res += getTaskLeft();
                break;
            }
        }

        return res;
    }

    //get task left as a string
    public String getTaskLeft() {
        return "Now you have " + totalTodo + (totalTodo==1 ? " task" : " tasks") + " in the list.";
    }

    //for list command
    public String getAllToDo() {
        StringBuilder res = new StringBuilder();
        res.append("Here are the tasks in your list:\n");
        for (int i = 0; i < totalTodo; i++) {
            res.append(i + 1).append(".")
                    .append(list[i].toString());
            if (i != totalTodo - 1) res.append("\n");
        }
        return res.toString();
    }

    // for mark and unmark command
    public String markToDo(String action, int index) {
        StringBuilder res = new StringBuilder();
        if (action.equals("mark")) {
             return list[index].setMarked();
        }
        return list[index].setUnmarked();
    }

    public int checkMarkCommand(String input) {
        String[] parts = input.split(" ");
        if((parts[0].equals("mark") || parts[0].equals("unmark")) && parts.length == 2) {
            String sec = parts[1];
            try {
                return Integer.parseInt(sec);
            } catch (NumberFormatException e) {
                return -1;
            }
        }
            return -1;
    }

    public String[] checkActionAndTask(String input) {
        String action = "";
        String task ="";
        String from="";
        String to="";
        String by="";

        String[] parts = input.split(" ");
        action = parts[0];

        switch(action) {
            case "deadline" : {
                boolean found = false;
                for(int i=1;i< parts.length;i++) {
                    if(parts[i].equals("/by")) {
                        found = true;
                    }
                    else if (found) {
                        by += parts[i] + " ";
                    } else {
                        task += parts[i] + " ";
                    }
                }
                break;
            }
            case "event" : {
                boolean startFound = false;
                boolean endFound = false;
                for(int i=1;i< parts.length;i++) {
                    if (parts[i].equals("/from")) {
                        startFound = true;
                    } else if (parts[i].equals("/to")){
                        startFound = false;
                        endFound = true;
                    } else if(startFound) {
                        from += parts[i] + " ";
                    } else if(endFound) {
                        to += parts[i] + " ";
                    } else {
                        task += parts[i] + " ";
                    }
                }
                break;
            }
            case "todo" : {
                for(int i = 1;i< parts.length;i++) {
                    task+=parts[i]+" ";
                }
            }

            default:
        }
        return new String[]{action, task, by, from, to};
    }
    public static void main(String[] args) {
        Scanner obj = new Scanner(System.in);
        Duke duke = new Duke();

        System.out.println(duke.greet);

        String userInput = obj.nextLine();

//        String[] res= duke.checkActionAndTask(userInput);
//        for(String i : res) {
//            System.out.println(i);
//        }

        while(!userInput.equals("bye")) {
            int resultIndex = duke.checkMarkCommand(userInput);
            if(resultIndex != -1) {
                System.out.println(duke.divider +
                        duke.markToDo((userInput.charAt(0) == 'u' ? "unmark" : "mark"), resultIndex-1)
                        + "\n" + duke.divider);

            } else if (userInput.equals("list")){
                System.out.println(duke.divider +
                        duke.getAllToDo()
                        + "\n" + duke.divider);
            } else {
                String[] taskEvent = duke.checkActionAndTask(userInput);
                String action = taskEvent[0];
                String list = taskEvent[1];

                System.out.println(duke.divider +
                        duke.addTask(taskEvent)
                        + "\n" + duke.divider);
            }
            userInput = obj.nextLine();
        }
        System.out.println(duke.exit);

    }
}