# ekuD Chatbot User Guide

Are you struggling with keeping up with To-dos and managing your deadlines? Fret not, as **ekuD** is willing to help you!

ekuD is a **Command-Line Interface (CLI)** chatbot optimized for fast-typers that provides **task managing** abilities to the user through **Graphical User Interface (GUI)**.  

## Quick Start
1. Ensure you have **Java 11** or above installed in your Computer.

2. Download the latest ekuD.jar from [here]().

3. Copy the file to the folder you want to use as the home folder for your ekuD chatbot.

4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar` ekuD.jar command to run the application.
5. A GUI similar to the below should appear in a few seconds. The App shows a default help message.
<img width="398" alt="image" src="https://github.com/brandon-nam/ip/assets/52233457/cd93d64f-3f6d-4222-b72d-411f4246a52a">

6. Type the command in the command box and press Enter to execute it. e.g. typing `todo read book` and pressing Enter will add a todo task of title 'read book' to the task list, which you can view by typing `list`. 
<br>

## Features


### `todo`: Adding a todo task

Adds a todo task with a title and optional tags. 

Format: `todo TITLE #TAG_NAME1 #TAG_NAME2`
- The `TITLE` is the title of the todo task.
- `TAG_NAME` is the name of the tag of the task. 
- Tags may or may not exist.
- Each tag must be followed by a hashtag (#). 
- Multiple tags could be attached to a task. 

Examples:
- `todo read book #read #book`

<br>

### `deadline`: Adding a deadline task

Adds a deadline task with a title, deadline, and optional tags. 

Format: `deadline TITLE /by DEADLINE #TAG_NAME1 #TAG_NAME2...`
- The `TITLE` is the title of the deadline task.
- The `DEADLINE` is the deadline of the task in the format `YYYY-MM-DD`.
- `TAG_NAME` is the name of the tag of the task. 
- Just like a todo task, tags can be optional and multiple.  

Examples:
- `deadline submit ip /by 2023-09-22 #submit #cs2103T`

<br>

### `event`: Adding an event task

Adds an event task to the task list.

Format: `event TITLE /from STARTING_DATE /to ENDING_DATE #TAG_NAME1 #TAG_NAME2`
- The `TITLE` is the title of the event task.
- The `STARTING_DATE` and `ENDING_DATE` are the starting date and the ending date of the event task in the format of `YYYY-MM-DD`.
- `TAG_NAME` is the name of the tag of the task.
- Tags are optional and can be multiple. 

Examples:
- `event work on tP /from 2023-09-22 /to 2023-09-24 #tP #userGuide`

<br>

### `find`: Finding tasks
Finds tasks with a specified name and optional tags. 

Format: `find TITLE #TAG_NAME1 #TAG_NAME2`
- The `TITLE` is the title of the task that you are looking for.
- `TAG_NAME` is the name of the tag of the task you are looking for.
- Either one of the title or the tag of a task must exist. 
- If the title is omitted, the tasks with the specified tags are found.
- If the tags are omitted, the tasks with the given title are found.
- If both the title and the tags are included, then the tasks with the title and the tags are found. 

### `list`: Listing all tasks in task list 

Lists out all tasks in the task list currently. 

Format: `list`

<br>

### `delete`: Deleting a task

Deletes a task from the task list.

Format: `delete TASK_INDEX`
- Deletes the task at the specified `TASK_INDEX`.
- The index refers to the index number shown in the task list displayed from the `list` command.
- The index **must be a positive integer** 1, 2, 3, ...

Examples:
- `list` followed by `delete 1` deletes the first task in the task list.

<br>

### `mark`: Marking a task as done

Marks a task as done.

Format: `mark TASK_INDEX`
- `TASK_INDEX` is the index of the task shown after listing out the tasks using `list` command. 

Examples:


<br>

### `unmark`: Marking a task as undone88

Marks a task as undone.

Format: `unmark TASK_INDEX`
- `TASK_INDEX` is the index of the task shown after listing out the tasks using `list` command.

Examples:

<br>

### `help`: Prints help message

Prints out a help message to guide you what ekuD is capable of. 

Format: `help`

<br>

### `bye`: Exiting the program

Exits the program.

Format: `bye`

<br>

