# Duke User Guide

## Description
Duke is a chatbot which can store tasks such as todos, deadlines and events.

## Setup
1. Ensure that you have [Java 11](https://www.oracle.com/sg/java/technologies/javase/jdk11-archive-downloads.html) downloaded.
2. Download Duke [here](https://github.com/jedkohjk/ip/releases).
3. Find the location of duke.jar in the file explorer.
4. Copy the file path to duke.jar.
5. Open the terminal and type ```cd ``` and paste in the file path copied. Enter the command.
6. Then type ```java -jar duke.java``` and enter the command to start Duke.

## Features 

### Add tasks

#### ToDo

Todos are tasks with only a description.

The following syntax is used to add a todo:

```
todo {description}
```

Examples:
```
todo eat
todo sleep
```

#### Deadline

Deadlines are tasks with a description and a due date.

The following syntax is used to add a deadline:

```
deadline {description} /by {due date}
```

Note: The date should be in YYYY-MM-DD format.

Examples:
```
deadline eat /by 2050-01-01
deadline sleep /by 2030-01-01
```

#### Event

Events are tasks with a description, a start date, and an end date.

The following syntax is used to add an event:

```
event {description} /from {start date} /to {end date}
```

Note: The dates should be in YYYY-MM-DD format.

Examples:
```
event eat /from 2050-01-01 /to 2070-01-01
event sleep /from 2030-01-01 /to 2080-01-01
```

### List tasks

List all tasks saved by the chatbot by entering the following command:

```
list
```

### Find tasks

Find relevant tasks using a substring of their description using the following command:

```
find {substring of description}
```

Examples:

If this is the list of tasks:

```
1. [T][ ] abc
2. [T][ ] a b c
```

The command ```find b``` will return:

```
1. [T][ ] abc
2. [T][ ] a b c
```

The command ```find ab``` will return:

```
1. [T][ ] abc
```

### Remove tasks

Remove tasks based on their index by which they appear when you use ```list``` using the following command:

```
remove {index}
```

Example:

If this is the list of tasks:

```
1. [T][ ] eat
2. [T][ ] sleep
```

The command ```remove 1``` will remove ```1. [T][ ] eat```.

### Mark as complete

Mark task as complete based on their index by which they appear when you use ```list``` using the following command:

```
mark {index}
```

Whether a task is complete is indicated by whether or not there is an ```X``` in the box beside it when it is listed.

### Mark as incomplete

Mark task as incomplete based on their index by which they appear when you use ```list``` using the following command:

```
unmark {index}
```

Whether a task is complete is indicated by whether or not there is an ```X``` in the box beside it when it is listed.

### Sort deadlines

List all deadlines sorted by their due dates using the following command:

```
queue
```

### Find ongoing events

List all ongoing events on a given date using the following command:

```
ongoing {date}
```
Note: The date should be in YYYY-MM-DD format.

### Exit the program

Exit the program by typing the following command:

```
bye
```

Your current data will be saved and auto-loaded upon restarting the program.
