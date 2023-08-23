import java.util.Scanner;

public class Chat {
    private UserInput[] toDos;
    private int count;

    public Chat(Scanner sc) {
        this.toDos = new UserInput[100];
        this.count = 0;
    }

    //keep taking in userInput
    //add userInput to the list
    public void addInput(String textInput) {
        UserInput userInput = new UserInput(textInput);
        if (!userInput.text.equals("list") && !userInput.text.equals("mark") && !userInput.text.equals("unmark") ) {
            this.toDos[count] = userInput;
            count++;
            System.out.println("added:" + userInput.text.toString());
        }
    }

    public void markTask(int taskNumber) {
        UserInput task = this.toDos[taskNumber - 1];
        task.markAsDone();
        System.out.println("Done. You're Done. \n" + "[" + task.getStatusIcon() + "]" + task.toString());
    }

    public void unMarkTask(int taskNumber) {
        UserInput task = this.toDos[taskNumber - 1];
        task.unMark();
        System.out.println("Done. Stop being lazy.\n" + "[" + task.getStatusIcon() + "]" + task.toString());
    }


    public void listout() {
        for (int i = 0; i < count; i++) {
            System.out.println((i + 1) + "." + "[" + this.toDos[i].getStatusIcon() + "]" + this.toDos[i].toString());
        }
    }

    public void start() {
        String logo = "____________________________________________________________\n"
                + "YO! The name's Bond, James Bond.  \n"
                + "What can I do for you? \n"
                + "____________________________________________________________\n";
        System.out.println(logo);
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String firstWord = sc.next();
            if (firstWord.equalsIgnoreCase("mark")) {
                int taskNumber = sc.nextInt();
                markTask(taskNumber);
            }
            if (firstWord.equalsIgnoreCase("unmark")) {
                int taskNumber = sc.nextInt();
                unMarkTask(taskNumber);
            }
                String input = sc.nextLine();
                if (input.equalsIgnoreCase("bye")) {
                    System.out.println("Bye. Till the next time.");
                    break;
                }
                if (firstWord.equalsIgnoreCase("list")) {
                    listout();
                } else {
                    addInput(firstWord + input);
                }
            }
    }
}
