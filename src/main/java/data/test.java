package data;
import java.io.FileWriter;
import java.io.IOException;


public class test {
    public static void main(String[] args) {
        System.out.println("Hello World!");
         String fileName = "output.txt"; // Name of the file to write to

        try {
            // Create a FileWriter object with the given file name
            FileWriter writer = new FileWriter(fileName);

            // Write data to the file
            writer.write("Hello, this is a simple example of file writing in Java!");

            // Close the writer to ensure data is flushed and the file is saved
            writer.close();

            System.out.println("Data written to " + fileName);

        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
    }



