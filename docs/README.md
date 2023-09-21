# ekuD Chatbot User Guide

Are you struggling with keeping up with To-dos and managing your deadlines? Fret not, as **ekuD** is willing to help you!

ekuD is a **Command-Line Interface (CLI)** chatbot optimized for fast-typers that provides **task managing** abilities to the user through **Graphical User Interface (GUI)**.  

## Quick Start
1. Ensure you have **Java 11** or above installed in your Computer.

2. Download the latest ekuD.jar from [here]().

3. Copy the file to the folder you want to use as the home folder for your ekuD chatbot.

4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar` ekuD.jar command to run the application.
5. A GUI similar to the below should appear in a few seconds. The App shows a default help message.
   
  <img width="512" alt="image" src="https://github.com/brandon-nam/ip/assets/52233457/fb1faaef-f551-4604-aa86-1eb8a8a94b04">

7. Type the command in the command box and press Enter to execute it. e.g. typing `todo read book` and pressing Enter will add a todo task of title 'read book' to the task list.
<br>

## Features


### `todo`: Adding a todo task

Adds a todo task with a title and optional tags. 

**Example**:
- `todo read book #read #book`

**Format**: `todo TITLE #TAG_NAME1 #TAG_NAME2`
- The `TITLE` is the title of the todo task.
- `TAG_NAME` is the name of the tag of the task. 
- **Tags are optional**; they may or may not exist.
- Each tag must be followed by a **hashtag (#)**. 
- **Multiple tags** could be attached to a task. 

<br>

### `deadline`: Adding a deadline task

Adds a deadline task with a title, deadline, and optional tags. 

**Example**:
- `deadline submit ip /by 2023-09-22 #submit #cs2103T`

**Format**: `deadline TITLE /by DEADLINE #TAG_NAME1 #TAG_NAME2...`
- The `TITLE` is the title of the deadline task.
- The `DEADLINE` is the deadline of the task in the format **`YYYY-MM-DD`**.
- `TAG_NAME` is the name of the tag of the task. 
- Just like a todo task, tags can be optional and multiple.  

<br>

### `event`: Adding an event task

Adds an event task to the task list.

**Example**:
- `event work on tP /from 2023-09-22 /to 2023-09-24 #tP #userGuide`

**Format**: `event TITLE /from STARTING_DATE /to ENDING_DATE #TAG_NAME1 #TAG_NAME2`
- The `TITLE` is the title of the event task.
- The `STARTING_DATE` and `ENDING_DATE` are the starting date and the ending date of the event task in the format of **`YYYY-MM-DD`**.
- `TAG_NAME` is the name of the tag of the task.
- Tags are optional and can be multiple. 

<br>

### `find`: Finding tasks
Finds tasks with a specified name and optional tags. 

**Example**: find work on tP #tP #userGuide

**Format**: `find TITLE #TAG_NAME1 #TAG_NAME2`
- The `TITLE` is the title of the task that you are looking for.
- `TAG_NAME` is the name of the tag of the task you are looking for.
- **Either one of the title or the tag name of a task must exist**. 
- If the title is omitted, the tasks with the specified tags are found.
- If the tags are omitted, the tasks with the given title are found.
- If both the title and the tags are included, then the tasks with the title and the tags are found. 

### `list`: Listing all tasks in task list 

Lists out all tasks in the task list currently. 

**Format**: `list`

<br>

### `delete`: Deleting a task

Deletes a task from the task list.

**Example**:

<img width="512" alt="image" src="https://github.com/brandon-nam/ip/assets/52233457/29fe18ec-f6d8-4c82-8d64-4c0067843b03">

`delete 1` deletes the first task in the task list.
<br>

**Format**: `delete TASK_INDEX`
- Deletes the task at the `TASK_INDEX` in the task list.
- `TASK_INDEX` is the index of the task shown after listing out the tasks using `list` command.
- The index **should be in the range of the list** (e.g. index cannot be 4 when there are only 3 tasks in the list). 

<br>

### `mark`: Marking a task as done

Marks a task as done.

**Format**: `mark TASK_INDEX`
- `TASK_INDEX` is the index of the task shown in the list.
- The index **should be in the range of the list**.

**Examples**:

<img width="512" alt="image" src="https://github.com/brandon-nam/ip/assets/52233457/15834a4b-2be3-4fa6-909e-3271167b4224">

The result of `mark 1` is shown above: the first task has a [X] next to its title.

<br>

### `unmark`: Marking a task as undone

Marks a task as undone.

**Format**: `unmark TASK_INDEX`
- `TASK_INDEX` is the index of the task shown after listing out the tasks using `list` command.

**Examples**:

<img width="512" alt="image" src="https://github.com/brandon-nam/ip/assets/52233457/448891b3-3b15-4adf-b84b-911873847a8a">

The result of `unmark 1` is shown above: while before `unmark 1` the task has [X], after `unmark 1` the tick is gone.
<br>

### `help`: Prints help message

Prints out a help message to guide you what ekuD is capable of. 

**Format**: `help`

<br>

### `bye`: Exiting the program

Exits the program.

Format: `bye`

<br>

