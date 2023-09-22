# Nano Chatbot User Guide

Nano is a **Chatbot for tracking tasks, optimised for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you possess fast typing skills, Nano has the potential to expedite your task management more efficiently than conventional GUI applications.


## Quick start
1. Ensure that you have Java `11` or above installed in your Computer.
2. Download the latest `duke.jar` from [here](https://github.com/richiehx/ip/releases/latest).
3. Copy the file to the folder you want to use as the _home folder_ for your Nano Chatbot.
4. To run the application, open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar duke.jar` command to run the application.
5. A GUI similar to the below should appear in a few seconds.<br>
![Screenshot of Nano Chatbot Ui](Ui.png)

6. Type the command in the command box and press Enter or click the SEND button to execute it. e.g. typing `list` and pressing Enter will list out all the current tasks saved.<br>
   Some example commands you can try:
   * `list` : Lists all tasks.
   * `todo Task` : Adds a new task of type **Todo** with the title `Task` to the Chatbot.
   * `delete 3` : Deletes the 3rd task shown in the task list.
   * `mark 1` : Marks the 1st task shown in the task list.
   * `bye` : Exits the chat.
     
8. Refer to the Features below for details of each command.
   
--------------------------------------------------------------------------------------------------------------------

## Features 

### Creation of Tasks

There are 3 types of tasks that can be created: **Todo**, **Deadline** and **Event**.
* **Todo** : A task with a description.
* **Deadline** : A task with a description and a due date and time.
* **Event** : A task with a description and the starting and ending date and time for the task.

### Task Marking

Tasks can be marked as completed and unmarked when needed, allowing for better task tracking.

### Task Editing

Tasks can be edited if needed.

## Feature Commands
<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `todo DESCRIPTION`, `DESCRIPTION` is a parameter which can be used as `todo New Task`.

* Parameters must be in the specified order.<br>
  e.g. if the command specifies `DESC /by DEADLINE`, the parameter order must be followed.

* Extraneous parameters for commands that do not take in parameters (such as `list` and `bye`) will be ignored.<br>
  e.g. if the command specifies `list 123`, it will be interpreted as `list`.

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.
</div>


### Adding a Todo Task: `todo`

Adds a **Todo** Task to the Task List.

Format: `todo DESCRIPTION`

Examples:
* `todo Watch CS2103T Lecture`

Expected Output:
```
Noted. I've added this task: 
[T][ ] Watch CS2103T Lecture
Now you have 1 task in the list.
```

### Adding a Deadline Task: `deadline`

Adds a **Deadline** Task to the Task List.

Format: `deadline DESCRIPTION /by DUE_DATE`
* All parameter fields must be provided.
* `DUE_DATE` must follow the format of `yyyy-mm-dd HH:mm` (year-month-day hours:minutes)

Examples:
* `deadline Do CS2103T project /by 2023-09-22 23:59` : Creates a new Deadline Task with the description `Do CS2103T project` and due by `22 September 2023 23:59`

Expected Output:

```
Noted. I've added this task:
[D][ ] Do CS2103T project (by: 22 September 2023 23:59)
Now you have 2 tasks in the list.
```

### Adding an Event Task: `event`

Adds an **Event** Task to the Task List.

Format: `event DESCRIPTION /from START_DATE /to END_DATE`
* All parameter fields must be provided.
* `START_DATE` and `END_DATE` must follow the format of `yyyy-mm-dd HH:mm` (year-month-day hours:minutes)

Examples:
* `event Watch CS2103T Lecture /from 2023-09-15 16:00 /to 2023-09-15 18:00` : Creates a new Event Task with the description `Watch CS2103T Lecture` starting from `15 September 2023 16:00` and ending on `15 September 2023 18:00`

Expected Output:

```
Noted. I've added this task:
[E][ ] Watch CS2103T Lecture (from: 15 September 2023 16:00 to: 15 September 2023 18:00)
Now you have 3 tasks in the list.
```

### Listing all tasks: `list`

Shows a list of all tasks in the Task List.

Format: `list`

Expected Output:
```
Here are the tasks in your list:
1. [T][ ] Watch CS2103T Lecture
2. [D][ ] Do CS2103T project (by: 22 September 2023 23:59)
3. [E][ ] Watch CS2103T Lecture (from: 15 September 2023 16:00 to: 15 September 2023 18:00)
```

### Marking a task: `mark`
Marks the specified task as complete.

Format: `mark INDEX`

* Marks the task at the specified `INDEX`.
* The index refers to the index number shown in the full Task List.
* The index **must be a positive integer** 1, 2, 3, …​
  
Examples:
* `mark 1` : Marks the 1st task in the Task List.

Expected Output:

```
Nice! I've marked this task as done:
[T][X] Watch CS2103T Lecture
```

### Unmarking a task: `unmark`
Unmarks the specified task as incomplete.

Format: `unmark INDEX`

* Unmarks the task at the specified `INDEX`.
* The index refers to the index number shown in the full Task List.
* The index **must be a positive integer** 1, 2, 3, …​
  
Examples:
* `unmark 1` : Unmarks the 1st task in the Task List.

Expected Output:

```
OK, I've marked this task as not done yet:
[T][ ] Watch CS2103T Lecture
```

### Editing a task: `edit`

Edits an existing task in the Task List.

Format: `edit INDEX FIELD_TO_EDIT NEW_VALUE`

* Edits the task at the specified `INDEX`. The index refers to the index number shown in the full Task List. The index **must be a positive integer** 1, 2, 3, …​
* All parameter fields must be provided.

#### 1. Editing a Todo Task
For **Todo** tasks, only the description can be edited.
* `FIELD_TO_EDIT` : Possible fields are `/desc`
* `NEW_VALUE` : The new value must follow the restrictions of the specified field. (For `/desc`, Description provided cannot be empty)
  
Examples:
*  `edit 1 /desc Do CS2103T Tutorial` : Edits the description of the 1st task to be `Do CS2103T Tutorial`.

Expected Output:

```
Noted. I've updated this task:
[T][ ] Do CS2103T Tutorial
Now you have 3 tasks in the list.
```

#### 2. Editing a Deadline Task
For **Deadline** tasks, the description and due date can be edited.
* `FIELD_TO_EDIT`: Possible fields are `/desc`, `/by`
* `NEW_VALUE`: The new value must follow the restrictions of the specified field. (For `/by`, the `NEW_VALUE` must follow the format of `yyyy-mm-dd HH:mm`)

Examples:
*  `edit 2 /desc Submit Project` : Edits the description of the 2nd task to be `Submit Project`.
*  `edit 2 /by 2023-10-12 22:12` : Edits the due date of the 2nd task to be `12 October 2023 22:12`.

Expected Output:

```
Noted. I've updated this task:
[D][ ] Submit Project (by: 12 October 2023 22:12)
Now you have 3 tasks in the list.
```


#### 3. Editing an Event Task
For **Event** tasks, the description, start time and end time can be edited.
* `FIELD_TO_EDIT`: Possible fields are `/desc`, `/from`, `/to`
* `NEW_VALUE`: The new value must follow the restrictions of the specified field. (For `/from` and `/to`, the `NEW_VALUE` must follow the format of `yyyy-mm-dd HH:mm`)

Examples:
*  `edit 3 /desc Watch CS2100 Lecture` : Edits the description of the 3rd task to be `Watch CS2100 Lecture`.
*  `edit 3 /from 2023-10-12 20:00` : Edits the start time of the 3rd task to be `12 October 2023 20:00`.
*  `edit 3 /to 2023-10-13 22:00` : Edits the end time of the 3rd task to be `13 October 2023 22:00`.

Expected Output:

```
Noted. I've updated this task:
[E][ ] Watch CS2100 Lecture (from: 12 October 2023 20:00 to: 13 October 2023 22:00)
Now you have 3 tasks in the list.
```


### Locating tasks by description: `find`

Finds tasks whose description contain the given keyword.

Format: `find KEYWORD`

* The search is case-sensitive. e.g. `task` will **NOT** match `Task`
  
Examples:
- Current List : 
```
Here are the tasks in your list:
1. [T][ ] Watch CS2103T Lecture
2. [D][ ] Do CS2103T project (by: 22 September 2023 23:59)
3. [E][ ] Watch CS2100 Lecture (from: 15 September 2023 16:00 to: 15 September 2023 18:00)
```

* `find Watch` : returns `Watch CS2103T Lecture` and `Watch CS2100 Lecture`

Expected Output:

```
Here are the tasks in your list:
1. [T][ ] Watch CS2103T Lecture
2. [E][ ] Watch CS2100 Lecture (from: 15 September 2023 16:00 to: 15 September 2023 18:00)
```

### Deleting a task : `delete`

Deletes the specified task from the Task List.

Format: `delete INDEX`

* Deletes the task at the specified `INDEX`.
* The index refers to the index number shown in the full Task List.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
- Current List :
```
Here are the tasks in your list:
1. [T][ ] Watch CS2103T Lecture
2. [D][ ] Do CS2103T project (by: 22 September 2023 23:59)
3. [E][ ] Watch CS2100 Lecture (from: 15 September 2023 16:00 to: 15 September 2023 18:00)
```

* `delete 2` : deletes the 2nd task in the Task List.

Expected Output:
```
Noted. I've removed this task:
[D][ ] Do CS2103T project (by: 22 September 2023 23:59)
Now you have 2 tasks in the list.
```



### Exiting the Chat : `bye`

Exits the Chat. The Program will exit upon the next input entered.

Format: `bye`

Expected Output:
```
Chat has ended! Please Exit.
```

### Saving the data

Nano Chatbot data is saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

Nano Chatbot data is saved automatically as a Text file `[JAR file location]/data/duke.txt`. Advanced users can update the data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file make its format invalid, Nano may process the data wrongly and load corrupted data. Hence, it is recommended to make a backup of the file before editing it.
</div>

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add Todo** | `todo DESCRIPTION` <br> e.g., `todo Watch CS2103T Lecture`
**Add Deadline** | `deadline DESCRIPTION /by DUE_DATE` <br> e.g., `deadline Do CS2103T project /by 2023-09-22 23:59`
**Add Event** | `event DESCRIPTION /from START_DATE /to END_DATE` <br> e.g., `event Watch CS2100 Lecture /from 2023-09-15 16:00 /to 2023-09-15 18:00`
**Mark Task** | `mark INDEX`<br> e.g., `mark 2`
**Unmark Task** | `unmark INDEX`<br> e.g., `unmark 2`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Edit** | `edit INDEX FIELD_TO_EDIT NEW_VALUE` <br> e.g.,`edit 2 /desc newDesc`, `edit 3 /from 2023-09-15 17:00`
**Find** | `find KEYWORD`<br> e.g., `find tutorial`
**List** | `list`
**Exit** | `bye`
