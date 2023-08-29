package com.mimi.main;

import com.mimi.commands.Command;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
public class Mimi {

    private Storage storage;
    private Ui ui;
    private ReadWriteData readWriteData;

    public Mimi(String directory, String dataPath) {
        this.ui = new Ui();
        this.storage = new Storage(this.ui);

        File directoryFile = new File(directory);
        File dataFile = new File(dataPath);

        try {
            //checks if the directory and the file are created. If not, create it.
            if (directoryFile.mkdir()) {}
            if (dataFile.createNewFile()){}
            this.readWriteData = new ReadWriteData(dataFile, this.storage, this.ui);
        } catch (IOException e) {
            this.ui.showLoadingError();
        }
    }


    public void run() {
        this.readWriteData.onInitialise();

        this.ui.welcomeMessage();

        Scanner inputReader = new Scanner(System.in);

        while (true) {
            String input = inputReader.nextLine();

            ui.separator();

            Parser parser = new Parser(input, this.storage, this.readWriteData);

            Command c = parser.parse();

            c.execute();

            c.uiResponse(this.ui);

            if (c.isExit()) {
                break;
            }


        }
    }

    public static void main(String[] args) {
        String directory = "./data/";
        String dataPath = "./data/Mimi.txt";
        new Mimi(directory, dataPath).run();
    }



}
