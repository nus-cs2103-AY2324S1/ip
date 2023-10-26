package duke.helper;
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


    /**
     * Process the user input to extract relevant information and store them correctly for executing user command.
     * @param userInput the input user command
     */
    public void processUserCommand(String userInput) {

        List<String> tokens = tokenize(userInput);
        command = tokens.get(0);

        List<String> attributes = tokensToAttributes(tokens);

        switch (attributes.size()) {
        case 3:
            secondEnteredTime = attributes.get(2);

        case 2:
            firstEnteredTime = attributes.get(1);

        case 1:
            taskName = attributes.get(0);

        }

    }


    /**
     * Converts user input into a list of tokens.
     *
     * @param userInput The user input to be converted into list of tokens.
     * @return The list of tokens.
     */
    public List<String> tokenize(String userInput) {
        List<String> tokens = new ArrayList<>();
        Scanner lineScanner = new Scanner(userInput);
        while (lineScanner.hasNext()) {

            String token = lineScanner.next();
            tokens.add(token);

        }
        lineScanner.close();
        return tokens;
    }

    /**
     * Converts a list of tokens into a list of attributes.
     *
     * @param tokens The list of tokens to be converted into list of attributes.
     * @return The list of attributes.
     */
    public List<String> tokensToAttributes(List<String> tokens) {
        List<String> attributes = new ArrayList<>();
        StringBuilder attributeName = new StringBuilder();

        for (int a = 1; a < tokens.size(); a++) {
            String element = tokens.get(a);
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
        return attributes;
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
