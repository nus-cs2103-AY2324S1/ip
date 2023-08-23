import java.util.Scanner;
import java.util.ArrayList;
public class Duke {

    ArrayList<Task> list = new ArrayList<>();

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


    public String addTask(String[] taskArray) throws DukeException{

        String action = taskArray[0];
        String task = taskArray[1];
        String by = taskArray[2];
        String from = taskArray[3];
        String to = taskArray[4];

        String res = "";
        switch (action) {
            case "todo" : {
                list.add(new ToDo(task, TaskType.TODO));
                res = "Got it. I've added this task : \n" + list.get(list.size()-1).toString() + "\n";
                res += getTaskLeft();
                break;
            }
            case "deadline" : {
                list.add(new Deadline(task, by, TaskType.DEADLINE));
                res = "Got it. I've added this task : \n" + list.get(list.size()-1).toString() + "\n";
                res += getTaskLeft();
                break;
            }
            case "event" : {
                list.add(new Event(task,from,to, TaskType.EVENT));
                res = "Got it. I've added this task : \n" + list.get(list.size()-1).toString() + "\n";
                res += getTaskLeft();
                break;
            }
        }

        return res;
    }

    //get task left as a string
    public String getTaskLeft() {
        return "Now you have " + list.size() + (list.size()==1 ? " task" : " tasks") + " in the list.";
    }

    //for list command
    public String getAllToDo() throws DukeException{
        StringBuilder res = new StringBuilder();
        if(list.size() == 0) {
            throw new DukeException("Oh no! No tasks for now! Add more tasks :)");
        }
        res.append("Here are the tasks in your list:\n");
        for (int i = 0; i < list.size(); i++) {
            res.append(i + 1).append(".")
                    .append(list.get(i).toString());
            if (i != list.size() - 1) res.append("\n");
        }
        return res.toString();
    }

    // for mark and unmark command
    public String markToDo(String action, int index) throws DukeException{
        StringBuilder res = new StringBuilder();

        //No task to mark/unmark
        if(list.size() == 0) {
            throw new DukeException("No tasks! Add more tasks to mark/unmark!\n");
        }

        //index entered is more than totalTodos
        if (index >= list.size()) {
            throw new DukeException("Enter mark/umark command with index lesser than " + (list.size()+1) +"\n");
        }

        if (action.equals("mark")) {
             return list.get(index).setMarked();
        }
        return list.get(index).setUnmarked();
    }

    public int checkMarkCommand(String input) throws DukeException{

        String[] parts = input.split(" ");

        //No index to mark/unmark
        if(input.equals("mark") && parts.length == 1) {
            throw new DukeException("Specify index to mark/unmark task!\n");
        }

        if((parts[0].equals("mark") || parts[0].equals("unmark")) && parts.length == 2) {
            String sec = parts[1];

            //index is not valid integer
            try {
                return Integer.parseInt(sec);
            } catch (NumberFormatException e) {
                throw new DukeException("Enter a valid positive integer after your mark/unmark command!\n");
            }
        }
            return -1;
    }

    public int checkDeleteCommand(String input) throws DukeException{

        String[] parts = input.split(" ");

        //No index to mark/unmark
        if(input.equals("delete") && parts.length == 1) {
            throw new DukeException("Specify index to delete task!\n");
        }

        if((parts[0].equals("delete")) && parts.length == 2) {
            String sec = parts[1];

            //index is not valid integer
            try {
                return Integer.parseInt(sec);
            } catch (NumberFormatException e) {
                throw new DukeException("Enter a valid positive integer after your mark/unmark command!\n");
            }
        }
        return -1;
    }

    public String deleteTask(int index) throws DukeException{
        StringBuilder res = new StringBuilder();

        //No task to mark/unmark
        if(list.size() == 0) {
            throw new DukeException("No tasks to delete! Add more tasks to mark/unmark!\n");
        }


        String removedTask = list.get(index).toString();
        list.remove(index);
        return "Noted. I've removed this task: \n " + "  " + removedTask + "\n" + getTaskLeft();

    }

    public String[] checkActionAndTask(String input) throws DukeException, InvalidInputExpression{
        String action = "";
        String task ="";
        String from="";
        String to="";
        String by="";

        String[] parts = input.split(" ");
        action = parts[0];

        //empty input or empty action
        if(action.equals("") || !action.equals("todo") && !action.equals("deadline") && !action.equals("event")) {
            throw new InvalidInputExpression("Invalid input!! Specify task as deadline, event or todo followed by the task please la dei!\n");
        }

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

        //empty description
        if (task.equals("")) {
            throw new DukeException("No description specified la dei!! How to do work when no work is said!! Enter again!\n");
        }
        //No from &/ to in event task
        if (action.equals("event") && (from.isEmpty() || to.isEmpty())) {
            throw new DukeException("event task must have both /from and /to times");
        }
        //No by in deadline task
        if (action.equals("deadline") && (by.isEmpty())) {
            throw new DukeException("deadline task must have /by time");
        }
        return new String[]{action, task, by, from, to};
    }
    public static void main(String[] args) {
        Scanner obj = new Scanner(System.in);
        Duke duke = new Duke();
        System.out.println(duke.greet);

        while (true) {
            String userInput = obj.nextLine();

            if (userInput.equals("bye")) {
                break;
            }

            try {
                int resultIndex = duke.checkMarkCommand(userInput);
                if (resultIndex != -1) {
                    System.out.println(duke.divider +
                            duke.markToDo((userInput.charAt(0) == 'u' ? "unmark" : "mark"), resultIndex - 1)
                            + "\n" + duke.divider);
                } else if (userInput.equals("list")) {
                    System.out.println(duke.divider +
                            duke.getAllToDo()
                            + "\n" + duke.divider);
                } else if (duke.checkDeleteCommand(userInput) != -1) {
                    int index = duke.checkDeleteCommand(userInput);
                    System.out.println(duke.divider +
                            duke.deleteTask(index - 1)
                            + "\n" + duke.divider);
                } else
                    {
                        try {
                            String[] taskEvent = duke.checkActionAndTask(userInput);
                            System.out.println(duke.divider +
                                    duke.addTask(taskEvent)
                                    + "\n" + duke.divider);
                        } catch (InvalidInputExpression | DukeException e) {
                            System.out.println(e.getMessage() + "\n");
                        }
                    }

            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.println(duke.exit);
    }

}