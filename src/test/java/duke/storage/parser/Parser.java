package duke.storage.parser;

import duke.storage.Deadline;
import duke.storage.Event;
import duke.storage.Task;
import duke.storage.ToDo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class Parser {
    String input;
    String[] inputArray;

    public Parser(String input) {
        this.input = input;
        inputArray= this.input.split(" ");
    }

    public String[] parseInput() {
        return inputArray;
    }

    public String getCommand() {
        return parseInput()[0];
    }
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

    public LocalDateTime convertDateTime(String input) {
        //Using format given by textbook dd-mm-yyyy HHmm example :"02/12/2019 1800"
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        LocalDateTime dateTime = LocalDateTime.parse(input, formatter);

        return dateTime;
    }


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

    public Task processToDo() {
        if(inputArray.length <= 1) {
            return null;
        }

        String extractedTask = String.join(" ", Arrays.copyOfRange(inputArray, 1, inputArray.length));
        Task newTask = new ToDo(extractedTask);

        return newTask;
    }


    public int processMarkIndex() {
        try {
            int index = Integer.parseInt(getIndex());
            return index;
        }catch(Exception e) {
            return -1;
        }
    }
    public int processUnmarkIndex() {
        try {
            int index = Integer.parseInt(getIndex());
            return index;
        }catch(Exception e) {
            return -1;
        }
    }
    public int processDelete() {
        if(inputArray.length != 2) {
            return -1;
        }else{
            int index = Integer.parseInt(inputArray[1]);

            return index;
        }
    }








}
