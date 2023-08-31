package io;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.NoSuchElementException;
/**
 * Used parse the additional data
 * given in a command based on tags.
 * @author Alvis Ng (supermii2)
 */
public class Parser {
    /** Untagged input*/
    private String defaultInput;
    /** Dictionary of tagged inputs*/
    private Dictionary<String, String> taggedInputs;
    /**
     * Creates a parsed representation
     * of the input string given by
     * the user's input.
     * @param input
     */
    public Parser(String input) {
        taggedInputs = new Hashtable<>();
        String[] phrases = input.split("/");
        this.defaultInput = phrases[0];
        for (int i = 1; i < phrases.length; i++) {
            String[] words = phrases[i].split(" ", 2);
            taggedInputs.put(words[0], words[1]);
        }
    }
    /**
     * Get the untagged input
     * @return Untagged input of parsed message
     */
    public String getDefaultString() {
        return defaultInput;
    }
    /**
     * Get the tagged input with the
     * given tag
     * @param tag Tag used to find tagged input.
     * @return Tagged input of the given tag
     * @throws NoSuchElementException Tag not found in the inputs.
     */
    public String getTaggedInput(String tag) throws NoSuchElementException {
        String result = taggedInputs.get(tag);
        if (result == null) {
            throw new NoSuchElementException("Invalid Tag!");
        }
        return result;
    }
}
