import java.util.Arrays;
import java.util.HashMap;

public class Parser {

    /**
     * Hashmap to map the flags to its corresponding string
     */
    private HashMap<String, String> flag = new HashMap<>();

    /**
     * type of command
     */
    private Commands command;

    /**
     * param that came with the command, stores the error message if it is not a
     * valid input
     */
    private String param = "";

    /**
     * the original input
     */
    private String input;

    /**
     * Constructor for the Parser
     * 
     * @param input - the input string that needs to be parsed
     * @throws BadInputException
     */
    public Parser(String input) throws BadInputException {
        this.input = input.trim();
        String[] splitInput = input.split(" ");
        switch (splitInput[0]) {
            case "list":
                this.command = Commands.LIST;
                break;
            case "mark":
                this.command = Commands.MARK;
                if (splitInput.length != 2) {
                    throw new BadInputException(
                            "Quack requires exactly one number after the mark command");
                }
                this.param = splitInput[1];
                break;
            case "unmark":
                this.command = Commands.UNMARK;
                if (splitInput.length != 2) {
                    throw new BadInputException(
                            "Quack requires exactly one number after the unmark command");
                }
                this.param = splitInput[1];
                break;
            case "todo":
                this.command = Commands.TODO;
                this.param = this.input.replace("todo ", "");
                if (this.param.equals("todo")) {
                    throw new BadInputException(
                            "Quack doesnt understand an empty todo description, please provide one!!");
                }
                break;
            case "deadline":
                this.command = Commands.DEADLINE;
                this.findFlags(splitInput, "/by");
                break;
            case "event":
                this.command = Commands.EVENT;
                this.findFlags(splitInput, "/from", "/to");
                break;
            default:
                this.command = Commands.UNRECOGNISED;

        }
    }

    /**
     * function to find the flags and update both the flags and param field
     * 
     * @param splitInputs - input string that has been split into words
     * @param flags       - the flags that needs to be found
     * @throws BadInputException
     */
    private void findFlags(String[] splitInputs, String... flags) throws BadInputException {

        int[] flagIndex = this.find(splitInputs, flags);

        for (int i = 0; i < flagIndex.length - 1; i++) {

            if (flagIndex[i] == -1) {
                throw new BadInputException(
                        "Quack cant find the required " + flags[i] + " flags, please provide quack with one please");
            }

            if (flagIndex[i + 1] == -1) {
                throw new BadInputException(
                        "Quack cant find the required " + flags[i + 1]
                                + " flags, please provide quack with one please");
            }

            String value = String.join(" ", Arrays.copyOfRange(splitInputs, flagIndex[i] + 1, flagIndex[i + 1]));
            if (value.isBlank()) {
                throw new BadInputException(
                        "Please provide quack a description for the " + flags[i] + " flag");
            }
            this.flag.put(splitInputs[flagIndex[i]], value);

        }
        this.param = String.join(" ", Arrays.copyOfRange(splitInputs, 1, flagIndex[0]));
        if (this.param.isBlank()) {
            throw new BadInputException(
                    "Quack doesnt understand an empty description, please provide one!!");
        }
    }

    /**
     * Finds the required flags in the array of strings
     * 
     * @param arr   - the array of strings that you want to find the flags from
     * @param items - the array of flags you want to find from the array
     * @return an array of the index of the flags
     * @throws BadInputException
     */
    private int[] find(String[] arr, String[] items) throws BadInputException {
        int[] ret = new int[items.length + 1];
        // initialise values
        for (int i = 0; i < items.length + 1; i++) {
            // to capture all text in the flag
            if (i == items.length) {
                ret[i] = arr.length;
                continue;
            }
            ret[i] = -1;
        }
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < items.length; j++) {
                if (arr[i].equals(items[j])) {
                    if (ret[j] != -1) {
                        throw new BadInputException(
                                "There are too many of the " + items[j] + " flag, please just provide one");
                    }
                    ret[j] = i;
                    break;
                }
            }
        }
        return ret;
    }

    /**
     * getter for the type of command
     * 
     * @return the type of command
     */
    public Commands getCommand() {
        return this.command;
    }

    /**
     * getter for the value of the flag
     * 
     * @param key - the flag
     * @return the value associated with the key
     */
    public String getFlag(String key) {
        return this.flag.get(key);
    }

    /**
     * getter for the param of the input
     * 
     * @return param
     */
    public String getParam() {
        return this.param;
    }
}
