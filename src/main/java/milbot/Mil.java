package milbot;

import extensions.TagList;

/**
 * Mil class represents a chatbot application for managing tasks.
 */
public class Mil {
    private static Storage storage;
    private static TaskList taskList;
    private static Ui ui;
    private static Parser parser;
    private static TagList tagList;

    /**
     * Constructs a new instance of the Mil chatbot.
     * Initializes the task list, user interface, storage, and parser.
     */
    public Mil() {
        ui = new Ui();
        storage = new Storage();
        tagList = storage.loadTagsFromFile();
        taskList = storage.loadTasksFromFile();
        parser = new Parser(taskList, ui, storage, tagList);
    }

    public String getResponse(String input) {
        String response = parser.parseInput(input);
        assert response != null: "The response should not be null.";
        return response;
    }
}