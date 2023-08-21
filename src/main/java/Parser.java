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
     * if it is a valid input
     */
    private boolean valid = true;

    /**
     * Constructor for the Parser
     * 
     * @param input - the input string that needs to be parsed
     */
    public Parser(String input) {
        this.input = input;
        String[] splitInput = input.split(" ");
        switch (splitInput[0]) {
            case "list":
                this.command = Commands.LIST;
                break;
            case "mark":
                this.command = Commands.MARK;
                if (splitInput.length != 2) {
                    this.valid = false;
                    this.param = "Quack requires a number after the mark command";
                    break;
                }
                this.param = splitInput[1];
                break;
            case "unmark":
                this.command = Commands.UNMARK;
                if (splitInput.length != 2) {
                    this.valid = false;
                    this.param = "Quack requires a number after the unmark command";
                    break;
                }
                this.param = splitInput[1];
                break;
            case "todo":
                this.command = Commands.TODO;
                this.param = this.input.replace("todo ", "");
                if (this.param.equals("todo") || this.param.equals("")) {
                    this.valid = false;
                    this.param = "Please provide a task to do!!";
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
     */
    public void findFlags(String[] splitInputs, String... flags) {

        int[] flagIndex = this.find(splitInputs, flags);
        if (!this.valid) {
            return;
        }
        this.param = String.join(" ", Arrays.copyOfRange(splitInputs, 1, flagIndex[0]));
        for (int i = 0; i < flagIndex.length - 1; i++) {
            if (flagIndex[i] == -1) {
                this.valid = false;
                this.param = "I cant find the required " + flags[i] + " flags";
                return;
            }
            this.flag.put(splitInputs[flagIndex[i]],
                    String.join(" ", Arrays.copyOfRange(splitInputs, flagIndex[i] + 1, flagIndex[i + 1])));

        }

    }

    public int[] find(String[] arr, String[] items) {
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
                        this.valid = false;
                        this.param = "There are too many of the" + items[j] + " flag";
                        return ret;
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
     * getter for the validitiy of the input
     * 
     * @return true if it is valid
     */
    public boolean getValidity() {
        return this.valid;
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
