package HelperClass;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Parser {
    private String command = "";
    private String taskName = "";
    private String firstEnteredTime = "";
    private String secondEnteredTime = "";


    public Parser() {

    }

    public String getUserInput() {
        Scanner getUserInput = new Scanner(System.in);
        String userInput = getUserInput.nextLine();
        getUserInput.close();
        return userInput;
    }

    /**
     * Process the user input to extract relevant information and store them correctly for executing user command.
     * @param userInput the input user command
     */
    public void processUserCommand(String userInput) {


        List<String> formattedLine = new ArrayList<>();
        Scanner lineScanner = new Scanner(userInput);
        while (lineScanner.hasNext()) {

            String token = lineScanner.next();
            formattedLine.add(token);

        }
        lineScanner.close();

        command = formattedLine.get(0);

        List<String> attributes = new ArrayList<>();
        StringBuilder attributeName = new StringBuilder();

        for (int a = 1; a < formattedLine.size(); a++) {
            String element = formattedLine.get(a);
            if (element.charAt(0) == '/') {
                attributes.add(attributeName.toString());
                attributeName = new StringBuilder();
            } else {
                if (attributeName.length() == 0) {
                    attributeName.append(element);
                } else {
                    attributeName.append(" ").append(element);
                }


            }
        }

        attributes.add(attributeName.toString());

        switch (attributes.size()) {
            case 3:
                secondEnteredTime = attributes.get(2);

            case 2:
                firstEnteredTime = attributes.get(1);

            case 1:
                taskName = attributes.get(0);

        }







    }


    public String getCommand() {
        return command;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getFirstEnteredTime() {
        return firstEnteredTime;
    }

    public String getSecondEnteredTime() {
        return secondEnteredTime;
    }
}
