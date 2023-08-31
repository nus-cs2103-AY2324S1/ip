package duke.parser;

import duke.task.*;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Scanner;

/**
 *  This Class is mainly dealing with user input and logic process
 */
public class Parser {
    private String input;
    private String[] inputArray;

    /**
     * Constructs a Parser based on the user input and register user's input and sliced input
     *
     * @param input input from user
     */
    public Parser(String input) {
        this.input = input;
        inputArray= this.input.split(" ");
    }

    /**
     *  Return input that has been pre-processed become sliced Array
     *
     * @return inputArray
     */
    public String[] parseInput() {

        return inputArray;
    }


    /**
     *  Return command after parsing the input
     *
     * @return the command to be done : todo deadline
     */

    public String getCommand() {
        return parseInput()[0];
    }

    /**
     *  Return index after parsing the input like for mark/unmark index based on user
     *
     * @return the index has been registered
     */
    public String getIndex() {
        return parseInput()[1];
    }

    public Task processEvent() {
        if(inputArray.length <= 2) {
            return null;
        }


        Integer startIndex = -1;
        String extractedTask = String.join(" ", Arrays.copyOfRange(inputArray, 1, inputArray.length));

        String endDate = "";
        String startDate = "";
        for(int i = 0; i < inputArray.length; i++) {
            if(inputArray[i].equals("/from") && startIndex == -1) {
                startIndex = i;
                extractedTask = String.join(" ", Arrays.copyOfRange(inputArray, 1, i ));
            } else if(inputArray[i].equals("/to") && startIndex != -1) {
                endDate = String.join(" ", Arrays.copyOfRange(inputArray, i+1, inputArray.length));
                startDate = String.join(" ", Arrays.copyOfRange(inputArray, startIndex + 1, i));
            }
        }

        Task newTask = new Event(extractedTask,convertDateTime(startDate),convertDateTime(endDate));
        return newTask;

    }

        //Using format given by textbook dd-mm-yyyy HHmm example :"02/12/2019 1800"
    /**
     * Convert the LocalDateTime based on String formatted input
     *
     * @param input the input has to be format of dd-mm-yyyy HHmm, example : "02/12/2019 1800"
     * @return The "translated" datetime based on input
     */
    public LocalDateTime convertDateTime(String input) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        LocalDateTime dateTime = LocalDateTime.parse(input, formatter);

        return dateTime;
    }


    /**
     * ProcessDeadline based on the userinput, slicing and categorise with text, dueDate
     * then create and return a deadline task
     *
     * @return Deadline Task
     */
    public Task processDeadline() {
        if(inputArray.length <= 2) {
            return null;
        }

        String dueDate = "";
        String extractedTask = String.join(" ", Arrays.copyOfRange(inputArray, 1, inputArray.length));

        for(int i = 0; i < inputArray.length; i++) {
            if(inputArray[i].equals("/by")) {
                dueDate = String.join(" ", Arrays.copyOfRange(inputArray, i+1, inputArray.length));
                extractedTask = String.join(" ", Arrays.copyOfRange(inputArray, 1, i));

                break;
            }
        }

        Task newTask = new Deadline(extractedTask,convertDateTime(dueDate));
        return newTask;
    }

    /**
     * ProcessToDo based on the userinput, slicing, save and create todo task
     *
     * @return Todo Task
     */
    public Task processToDo() {
        if(inputArray.length <= 1) {
            return null;
        }

        String extractedTask = String.join(" ", Arrays.copyOfRange(inputArray, 1, inputArray.length));
        Task newTask = new ToDo(extractedTask);

        return newTask;
    }

    public String getExtracted() {

        if(inputArray.length <= 1) {
            return "";
        }

        String extractedWord = String.join(" ", Arrays.copyOfRange(inputArray, 1, inputArray.length));

        return extractedWord;
    }



    /**
     * Return an Integer Index to be marked based on String input, and -1 if invalid
     *
     * @return Integer Index to be marked
     */
    public int processMarkIndex() {
        try {
            //from string to index
            int index = Integer.parseInt(getIndex());
            return index;
        }catch(Exception e) {
            return -1;
        }
    }
    /**
     * Return an Integer Index to be unmarked based on String input, and -1 if invalid
     *
     * @return Integer Index to be unmarked
     */
    public int processUnmarkIndex() {
        try {
            int index = Integer.parseInt(getIndex());
            return index;
        }catch(Exception e) {
            return -1;
        }
    }
    /**
     * Return an Integer Index to be deleted based on String input, and -1 if invalid
     *
     * @return Integer Index to be deleted
     */
    public int processDeleteIndex() {
        if(inputArray.length != 2) {
            return -1;
        }else{
            try {
                int index = Integer.parseInt(getIndex());
                return index;
            }catch(Exception e){
                return -1;
            }
        }
    }








}
