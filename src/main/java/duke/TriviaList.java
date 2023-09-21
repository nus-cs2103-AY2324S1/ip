package duke;

import java.io.IOException;
import java.util.HashMap;

/**
 * Stores all trivia given to the DukeBot
 */
public class TriviaList {

    //Stores and loads the current trivia given
    private Storage storage;

    //The path of the trivia storage file
    private String path;

    //The hashmap used to store all trivia, with a question and answer.
    private HashMap<String, String> trivia;

    /**
     * Instantiates an instance of TriviaList
     * @param path The filepath for trivia.s
     */
    public TriviaList(String path) {
        this.path = path;
        try {
            this.storage = new Storage(path);
            this.trivia = storage.loadTrivia();
        } catch (IOException | ClassNotFoundException e) {
            Ui.errorMessage(e.getMessage());
        }
    }

    /**
     * Adds a trivia question and answer to the TriviaList
     * @param question Trivia Question
     * @param answer Trivia answer
     */
    public void addTrivia(String question, String answer) {
        trivia.put(question, answer);
    }

    /**
     * Retrieves the answer of a question
     * @param question Trivia question
     * @return The trivia answer
     */
    public String getTrivia(String question) {
        if (trivia.containsKey(question)) {
            return trivia.get(question);
        } else {
            return "I don't know...";
        }
    }

    /**
     * Removes a trivia question.
     * @param question The question to be removed
     * @return The removed question, if it exists.
     */
    public String removeTrivia(String question) {
        if (trivia.containsKey(question)) {
            trivia.remove(question);
            return question;
        } else {
            return "I don't know what you're talking about...";
        }
    }

    /**
     * Edits the answer to a trivia question
     * @param question The original question
     * @param newAnswer The new answer
     * @return The question
     */
    public String editAnswer(String question, String newAnswer) {
        if (trivia.containsKey(question)) {
            trivia.remove(question);
            trivia.put(question, newAnswer);
            return question;
        } else {
            addTrivia(question, newAnswer);
            return question;
        }
    }

    /**
     * Saves the data stored in the TriviaList into the trivia.s file.
     */
    public void save() {
        storage.storeTrivia(this.trivia);
    }
}


