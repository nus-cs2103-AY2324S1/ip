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
        public void addInput (String textInput){
            if (!textInput.equals("list")) {
            UserInput userInput = new UserInput(textInput);
            this.toDos[count] = userInput;
            count++;
            System.out.println("added:" + userInput.toString());
            }

        }

        public void listout (){
            for(int i = 0; i < count; i++){
                System.out.println((i+1) + "." + this.toDos[i].toString());
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
                String input = sc.nextLine();
                addInput(input);
                if (input.equalsIgnoreCase("bye")) {
                    System.out.println("Bye. Till the next time.");
                    break;
                }
                if (input.equalsIgnoreCase("list")) {
                    listout();
                }
            }
    }
}
