# ChatBot (Bobby Wasabi) #
Bobby Wasabi is your new best friend! He is a chatbot capable of **keeping track of all your deadlines, todos, and events!** Not only that with his somewhat **thorny personality**, he is sure to keep you on track to finishing all your tasks (or he will berate you!). It is a desktop app optimised via a Command Line Interface, together with a Graphical User Interface.

## Quick Start ##
1. Ensure you have Java 11 or above installed in your Computer.
2. Download the latest ChatBot.jar.
3. Copy the file to the folder you want to use as the home folder for your ChatBot
4. Open a command terminal, cd into the folder you put the jar file in, and use the java -jar ChatBot.jar command to run the application. A GUI should pop up!
5. Type in commands and press enter to execute! Here are some examples:
   - list: Lists all current tasks
   - todo Homework: adds a To-Do item onto your list named Homework
   - mark 1: Marks the first item on your list as done

## Features ##
> [NOTE]
> Words in UPPER_CASE need to be supplied by the user.

### Adding a To-Do: todo ###
Adds a To-Do to your list.

Format: todo NAME

Examples:
- todo Submit CS2103T IP
- todo Laundry

### Adding a Deadline: deadline ###
Adds a deadline to your list.

Format: deadline NAME /by DATE_AND_TIME

- Date must be in format yyyy-MM-dd HHmm
Examples:
- deadline CS2103T IP 2023-09-22 2359
- deadline IPPT 2023-12-26 2359

### Adding an Event: event ###
Adds an event to your list.

Format: event NAME /from START_TIME /to END_TIME
- START_TIME and END_TIME must be in format yyyy-MM-dd HHmm

Examples:
- event Birthday Party /from 2023-09-20 1400 /to 2023-09-20 2300
- event Orientation Camp /from 2023-09-30 1200 /to 2023-10-02 2200

### Listing items on the list: list ###
List all current items on the list. Displays whether they have been done or not.

Format: list

### Marking an item as done: mark ###
Marks an item on the list as done.

Format: mark INDEX
- INDEX must be a positive non-zero integer, e.g. 1, 2, 3
- INDEX must also be a valid index on the list.

Examples:
- mark 1

### Unmark an item as undone: unmark ###
Unmarks an item as undone.

Format: unmark INDEX
- INDEX must be a positive non-zero integer, e.g. 1, 2, 3
- INDEX must also be a valid index on the list.

Examples:
- unmark 1

### Delete an item off the list: delete ###
Deletes an item off the list.

Format: delete INDEX
- INDEX must be a positive non-zero integer, e.g. 1, 2, 3
- INDEX must also be a valid index on the list.

Example:
- delete 1

### Find an item on the list: find ###
Finds all items on the list containing the given prompts.

Format: find PROMPT
- Prompt is a string containing any character. 
- Returns nothing if nothing is found.

Example:
- find Birthday Party
- find CS2103T-IP-Assignment

### List all duplicates found: listduplicate ###
Lists all entries in the list with duplicate names.

Format: listduplicate

### Delete all duplicates found: deleteduplicate ###
Deletes duplicates found, and leaves the first entry behind.

Format: deleteduplicates

### Exiting the chatbot: bye ###
Exits the chat.

Format: bye

### Saving data ###
Data is automatically saved on a .txt file whenever queries are made.
