# Samantha ChatBot

Samantha ChatBot is a **desktop app for tracking tasks** using a sophusticated GUI(Graphic User Interface).

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `addressbook.jar` from [here](https://github.com/se-edu/addressbook-level3/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your AddressBook.

1. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar addressbook.jar`
   command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

### 1) Adding tasks

You can add three types of tasks : `Todo`, `Deadline` and `Event`

1. Adding **todo** task : `todo DESCRIPTION`
2. Adding **deadline** task : `deadline DESCRIPTION /by DATETIME`
3. Adding **event** task: `event DESCRIPTION /from DATETIME /to DATETIME`

* DATETIME must be in the format {DD/MM/YYYY HHMM}
    * example: 02/05/2023 1900

### 2) Listing all persons : `list`

Shows a list of all persons in the address book.

Format: `list`

### 3) Editing a person : `delete`

Deletes an existing task in the task list.

Format: `delete INDEX`

* Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list.
  The index **must be a positive integer** 1, 2, 3, …​

### 4) Locating tasks by description: `find`

Find tasks with description that contain any of the given keywords.

Format: `find KEYWORD`

### 5) Marking tasks : `mark`

Mark tasks to indicate if they are completed

Format: `mark INDEX`

* To unmark a task, use `unmark INDEX` instead
* To get the specific index number, use `list` command first

### 6) Tagging

Create tags for tasks to group them together.

Format: `tag INDEX #TAG_NAME`

### 7) Exiting the program : `bye`

Exits the program.

Format: `bye`

### Saving the data

Task data are saved into the tasks.txt file automatically after any command that changes the data. There is no need to
save manually.

--------------------------------------------------------------------------------------------------------------------

## Command summary

 Action     | Format, Examples                                                                                                 
------------|------------------------------------------------------------------------------------------------------------------
 **Add**    | `todo DESCRIPTION` <br> `deadline DESCRIPTION /by DATETIME` <br> `event DESCRIPTION /from DATETIME /to DATETIME` 
 **Mark**   | `mark INDEX` <br> `unmark INDEX`                                                                                 
 **Delete** | `delete INDEX`<br> e.g., `delete 2`                                                                              
 **Tag**    | `tag INDEX #TAGNAME` <br> e.g.,`tag 2 #urgent`                                                                   
 **Find**   | `find KEYWORD`<br> e.g., `find homework2`                                                                        
 **List**   | `list`                                                                                                           
 **Exit**   | `bye`                                                                                                            
