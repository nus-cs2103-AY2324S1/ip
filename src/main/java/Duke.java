import java.util.Scanner;
public class Duke {

    Task[] list = new Task[100];

    private static int totalTodo = 0;

    public Duke() {
        for(int i=0;i<100;i++) {
            list[i] = new Task();
        }
    }
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

    public String echo(String userInput) {
        return userInput;
    }

    public String addToDo(String toDo) {
        list[totalTodo++].setList(toDo,totalTodo);
        return "added: " + echo(toDo);
    }

    public String getAllToDo() {
        StringBuilder res = new StringBuilder();
        res.append("Here are the tasks in your list:\n");
        for (int i = 0; i < totalTodo; i++) {
            res.append(i + 1).append(".")
                    .append(list[i].getCompleted()).append(" ")
                    .append(list[i].getList()).append("\n");
        }
        return res.toString();
    }

    public String markToDo(String action, int index) {
        StringBuilder res = new StringBuilder();
        if (action.equals("mark")) {
            list[index].setCompleted(true);
        } else {
            list[index].setCompleted(false);
        }
        res.append(action.equals("mark") ? "Nice! I've marked this task as done:\n" :  "OK, I've marked this task as not done yet:\n").append(list[index].getCompleted()).append(" ").append(list[index].getList());
        return res.toString();

    }

    public int markCommand(String input) {
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
    public static void main(String[] args) {
        Scanner obj = new Scanner(System.in);
        Duke duke = new Duke();

        System.out.println(duke.greet);

        String userInput = obj.nextLine();

//        int resultIndex = duke.markCommand(userInput);
//        System.out.println(resultIndex);

        while(!userInput.equals("bye")) {
            int resultIndex = duke.markCommand(userInput);
            if(resultIndex != -1) {
                System.out.println(duke.divider +
                        duke.markToDo((userInput.charAt(0) == 'u' ? "unmark" : "mark"), resultIndex-1)
                        + "\n" + duke.divider);

            } else if (userInput.equals("list")){
                System.out.println(duke.divider +
                        duke.getAllToDo()
                        + "\n" + duke.divider);
            } else {
                System.out.println(duke.divider +
                        duke.addToDo(userInput)
                        + "\n" + duke.divider);
            }
            userInput = obj.nextLine();
        }
        System.out.println(duke.exit);

//        String input = "unmark 2";
//        System.out.println(duke.markCommand("unmark 199"));
//        System.out.println(input.charAt(0) == 'u');
    }
}