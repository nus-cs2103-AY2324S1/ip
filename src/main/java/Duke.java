import java.util.Scanner;
public class Duke {

    ToDoList[] list = new ToDoList[100];

    private static int totalTodo = 0;

    public Duke() {
        for(int i=0;i<100;i++) {
            list[i] = new ToDoList();
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
        String res="";
        list[totalTodo++].setList(toDo);
        return "added: " + echo(toDo);
    }

    public String getAllToDo() {
        String res = "";
        for(int i=0;i<totalTodo;i++) {
            res += (i+1) + ". " + list[i].getList() + "\n";
        }
        return res;
    }
    public static void main(String[] args) {
        Scanner obj = new Scanner(System.in);
        Duke duke = new Duke();

        System.out.println(duke.greet);

        String userInput = obj.nextLine();
        while(!userInput.equals("bye")) {
            System.out.println(duke.divider +
                    (userInput.equals("list") ? duke.getAllToDo() : duke.addToDo(userInput))
                    + "\n" + duke.divider);
            userInput = obj.nextLine();
        }
        System.out.println(duke.exit);
    }
}