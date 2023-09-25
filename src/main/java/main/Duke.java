package main;

import command.Command;
import exception.DukeException;
import task.TaskList;

import java.io.IOException;
import java.util.Scanner;

/**
 * Duke class - Main class
 */
public class Duke {
    Scanner userInput = new Scanner(System.in);
    TaskList tasks = new TaskList();
    Storage storage;
    UI ui;

    public static void main(String[] args) {

        Duke duke = new Duke("./data/data.txt");
        duke.start();

    }

    /**
     * Constructor for Duke class
     *
     * @param filePath file path from which past saved date, if available, should be read from
     */
    public Duke(String filePath) {
        this.storage = new Storage(filePath, tasks);
        this.ui = new UI();

        this.ui.printWelcomeMessage();

        try {
            this.storage.loadList();
        } catch (DukeException | IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Method to start the Duke program
     */
    private void start() {

        boolean isContinue = true;
        while (isContinue) {
            try {
                String input = userInput.nextLine();
                Command command = Parser.parse(input);
                command.execute(this.tasks, this.ui, this.storage);
                isContinue = command.isContinue();
                if (isContinue) {
                    System.out.println("Now you have " + tasks.getSize() + " tasks in the list.");
                    this.ui.printDivider();
                }
            } catch (DukeException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }

    /**
     * getResponse fetches an output from the bot.
     *
     * @param input Input typed in by the user.
     * @return Output from the bot in response to the input.
     */
    public String getResponse(String input) {

        StringBuilder botResponse = new StringBuilder();
        boolean isContinue;

        try {
            Command command = Parser.parse(input);
            botResponse.append(command.execute(this.tasks, this.ui, this.storage));
            isContinue = command.isContinue();
            if (isContinue) {
                botResponse.append("\nNow you have " + tasks.getSize() + " tasks in the list.");
                botResponse.append(this.ui.printDivider());
            }
        } catch(DukeException e){
                return e.getMessage();
            }
            return String.valueOf(botResponse);
        }
    }

