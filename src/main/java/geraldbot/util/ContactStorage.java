package geraldbot.util;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import geraldbot.person.Person;

/**
 * Handles reading and writing persons (contacts) to the storage file.
 */
public class ContactStorage extends Storage<Person> {
    /**
     * Constructor for ContactStorage.
     *
     * @param path The path to the storage file for contacts.
     */
    public ContactStorage(String path) {
        super(path);
    }

    /**
     * Reads persons (contacts) from the storage file.
     *
     * @return An ArrayList of Person objects representing contacts.
     */
    @Override
    public ArrayList<Person> read() {
        ArrayList<Person> contactList = new ArrayList<>();

        try {
            Scanner sc = new Scanner(this.file);

            while (sc.hasNextLine()) {
                String[] line = sc.nextLine().split("\\|");
                String[] contact = new String[line.length];
                for (int i = 0; i < line.length; i++) {
                    contact[i] = line[i].trim();
                }

                if (contact.length >= 3) {
                    Person newPerson = new Person(contact[0], contact[1], contact[2]);

                    contactList.add(newPerson);
                } else {
                    System.out.println("Invalid contact format: " + String.join("|", contact));
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println(e);
            createFile();
        }

        return contactList;
    }

    /**
     * Adds a person (contact) in file format to the storage file.
     *
     * @param person The formatted person (contact) to add.
     */
    public void addPerson(String person) {
        try {
            FileWriter fw = new FileWriter(this.file, true);

            if (this.file.length() != 0) {
                fw.write("\n");
            }

            fw.write(person);
            fw.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /**
     * Updates a person (contact) in the storage file.
     *
     * @param index The index of the person (contact) to update.
     * @param updatedPerson The updated person (contact) in file format.
     */
    public void updatePerson(int index, String updatedPerson) {
        try {
            Scanner sc = new Scanner(this.file);
            ArrayList<String> updatedContactList = new ArrayList<>();
            int idx = 0;

            while (sc.hasNextLine()) {
                String currContact = sc.nextLine();

                if (idx != index) {
                    updatedContactList.add(currContact);
                } else {
                    if (updatedPerson != null) {
                        updatedContactList.add(updatedPerson);
                    }
                }

                idx++;
            }

            sc.close();

            FileWriter fw = new FileWriter(this.file);
            fw.write(String.join("\n", updatedContactList));
            fw.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
