# Zac

Zac is a very helpful chatbot that helps you plan your tasks. It's named after the Java mascot _Duke_. Given below are instructions on how to use it.

## Setting up in Intellij

Prerequisites: JDK 11, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
1. Open the project into IntelliJ as follows:
   1. Click `Open`.
   1. Select the project directory, and click `OK`.
   1. If there are any further prompts, accept the defaults.
1. Configure the project to use **JDK 11** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialogue, set the **Project language level** field to the `SDK default` option.
3. After that, locate the `src/main/java/duke/Launcher.java` file, right-click it, and choose `Run Launcher.main()` (if the code editor is showing compile errors, try restarting the IDE).

## Features
### Listing all tasks: `list`
Shows a list of all persons in the address book.

Format: `list`

### Creating new tasks

#### Todo: `Todo`
Create a new task with a simple description

Format: `todo [description]`

Example: `todo borrow book`

#### Deadline: `Deadline`
Create a new task with a deadline

Format: `deadline [description] /by [date]`

Example: `deadline return book /by 2019-10-15`

#### Event: `Event`
Create a new task with a from and to

Format: `event project meeting /from [datetime] /to [datetime]`

Example: `event project meeting /from 2023-09-09T11:50 /to 2023-09-09T11:55`

### Mark tasks as done: `mark`
Format: `mark INDEX`

Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed task list. The index must be a positive integer 1, 2, 3, …​

### Mark tasks as not done: `unmark`
Format: `unmark INDEX`

### Delete task: `delete`
Format: `delete INDEX`

### Finding a specific task: `find`
Finds tasks whose description contains the given keyword.

Format: `find KEYWORD`

- The search is case-sensitive. e.g `hans` will not match `Hans`
- Partial words will be matched e.g. `Han` will match `Hans`
- All matching tasks will be returned

### Finding the next available time: `free`
Format: `free`

Will look through all the current events and find the next timeslot where the user is available.

Sample output:

`You have free time From: Sep 19 2023 22:55` when there is no free time until the last event is completed.

`You have free time From: Sep 19 2023 22:55 To: Sep 20 2023 00:20` when there is a time gap between events which is free. 


### Exiting the program: `bye`
Exits the program.

Format: `bye`

## Saving the data
Tasklist data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

## Editing the data file
Tasklist data are saved automatically as a JSON file [JAR file location]/data/duke.txt. Advanced users are welcome to update data directly by editing that data file.

