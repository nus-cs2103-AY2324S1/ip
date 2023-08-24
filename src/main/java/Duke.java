import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class Duke {
    public final static String horiLine = "____________________________________________________________";

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo);

        greetFunction("Jack");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        String[] inputArray= input.split(" ");
        String firstInput = inputArray[0];


        TaskArray taskArrayList = new TaskArray();

        while(!firstInput.equals("bye")) {

            if (firstInput.equals("list")) {
                taskArrayList.printTaskArrayList();

            } else if (firstInput.equals("delete")){
                if(inputArray.length != 2){
                    System.out.println(horiLine);
                    System.out.println("☹ OOPS!!! Invalid Index!");
                    System.out.println(horiLine);

                    input = scanner.nextLine();
                    inputArray= input.split(" ");
                    firstInput = inputArray[0];
                    continue;
                }
                int index = Integer.parseInt(inputArray[1]);
                taskArrayList.removeTask(index - 1);

            } else if (firstInput.equals("mark")){
                int index = Integer.parseInt(inputArray[1]);
                taskArrayList.get(index - 1).mark();

            } else if (firstInput.equals("unmark")){
                int index = Integer.parseInt(inputArray[1]);
                taskArrayList.get(index - 1).unmark();

            }else if(firstInput.equals("todo")) {
                if(inputArray.length == 1){
                    System.out.println(horiLine);
                    System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                    System.out.println(horiLine);

                    input = scanner.nextLine();
                    inputArray= input.split(" ");
                    firstInput = inputArray[0];

                    continue;
                }

                String extractedTask = String.join(" ", Arrays.copyOfRange(inputArray, 1, inputArray.length));
                Task newTask = new ToDo(extractedTask);
                taskArrayList.add(newTask);


            }else if(firstInput.equals("deadline")) {

                if(inputArray.length == 1){
                    System.out.println(horiLine);
                    System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
                    System.out.println(horiLine);

                    input = scanner.nextLine();
                    inputArray= input.split(" ");
                    firstInput = inputArray[0];
                    continue;
                }

                String dueDate = "";
                String extractedTask = String.join(" ", Arrays.copyOfRange(inputArray, 1, inputArray.length));

                for(int i = 0; i < inputArray.length; i++){
                    if(inputArray[i].equals("/by")){
                        dueDate = String.join(" ", Arrays.copyOfRange(inputArray, i+1, inputArray.length));
                        extractedTask = String.join(" ", Arrays.copyOfRange(inputArray, 1, i));

                        break;
                    }
                }

                Task newTask = new Deadline(extractedTask,dueDate);
                taskArrayList.add(newTask);

            }else if(firstInput.equals("event")) {
                if(inputArray.length == 1){
                    System.out.println(horiLine);
                    System.out.println("☹ OOPS!!! The description of a event cannot be empty.");
                    System.out.println(horiLine);
                    input = scanner.nextLine();
                    inputArray= input.split(" ");
                    firstInput = inputArray[0];

                    continue;
                }

                Integer startIndex = -1;
                Integer endIndex = -1;
                String extractedTask = String.join(" ", Arrays.copyOfRange(inputArray, 1, inputArray.length));

                String endDate ="";
                String startDate = "";
                for(int i = 0; i < inputArray.length; i++){
                    if(inputArray[i].equals("/from") && startIndex == -1){
                        startIndex = i;
                        extractedTask = String.join(" ", Arrays.copyOfRange(inputArray, 1, i ));
                    }else if(inputArray[i].equals("/to") && startIndex != -1){
                        endDate = String.join(" ", Arrays.copyOfRange(inputArray, i+1, inputArray.length));
                        startDate = String.join(" ", Arrays.copyOfRange(inputArray, startIndex + 1, i));

                    }
                }

                Task newTask = new Event(extractedTask,startDate,endDate);
                taskArrayList.add(newTask);

            }else{
                System.out.println(horiLine);
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                System.out.println(horiLine);
            }

            input = scanner.nextLine();
            inputArray= input.split(" ");
            firstInput = inputArray[0];
        }

        byeFunction();
        scanner.close();

    }

    public static void greetFunction(String name){
        String greetings = horiLine +"\nHello! I'm " + name + "\n"
                + "What can I do for you?\n" + horiLine;
        System.out.println(greetings);
    }
    public static void byeFunction(){

        String byeword = horiLine + "\nBye. Hope to see you again soon\n" + horiLine;
        System.out.println(byeword);
    }
    public static void repeatFunction(String text){
        String byeword = horiLine +"\n" + text + "\n" + horiLine;
        System.out.println(byeword);
    }


}
