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

    public void processUserCommand() {
        Scanner getUserInput = new Scanner(System.in);
        String userInput = getUserInput.nextLine();
        List<String> formattedLine = new ArrayList<>();
        Scanner lineScanner = new Scanner(userInput);
        while (lineScanner.hasNext()) {

            String token = lineScanner.next();
            formattedLine.add(token);

        }
        lineScanner.close();

        List<String> attributes = new ArrayList<>();
        StringBuilder attributeName = new StringBuilder();

        for (String element : formattedLine) {
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
            case 4:
                secondEnteredTime = attributes.get(3);

            case 3:
                firstEnteredTime = attributes.get(2);

            case 2:
                taskName = attributes.get(1);

            case 1:
                command = attributes.get(0);
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
