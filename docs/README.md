# User Guide for LilBro

## Requirements

1. Windows/MacOS/Linux

2. Java 11

## Quick Start

1. Download Java 11 from [here](https://www.oracle.com/sg/java/technologies/javase/jdk11-archive-downloads.html).
2. Download the latest jar file from the [releases tab](https://github.com/HugeNoob/ip/releases).
3. Run with java -jar duke.jar from your terminal.

## Features

### Task Management Commands

LilBro allows you to manage tasks by adding todos, setting deadlines, scheduling events, listing tasks, marking them as done, unmarking, deleting, and searching for keywords.

### Task Persistence

LilBro has the ability to store your tasks locally. Your tasks at the end of each session will be saved, allowing you to access them the next time you start LilBro again.

## Usage

### 1. Add todo task

Adds a todo task to your task list.

Command format:

```
todo <description>
or
td <description>
```

Example of usage:

```
todo CS2103T iP
or
td CS2103T iP
```

Expected outcome:

LilBro will add your todo to your task list!

```
Got it. I've added this task:
[T][ ] CS2103T iP
Now you have 1 tasks in the list.
```

### 2. Add deadline task

Adds a deadline task to your task list.

Command format:

```
deadline <description> /by <YYYY-MM-dd HH:mm>
or
dl <description> /by <YYYY-MM-dd HH:mm>
```

Example of usage:

```
deadline CS2105 Assignment /by 2023-09-25 23:59
or
dl CS2105 Assignment /by 2023-09-25 23:59
```

Expected outcome:

LilBro will add your deadline to your task list!

```
Got it. I've added this task:
[D][ ] CS2105 Assignment (by: Mon 25 Sep 2023 23:59)
Now you have 1 tasks in the list.
```

### 3. Add event task

Adds a event task to your task list.

Command format:

```
event <description> /from <YYYY-MM-dd HH:mm> /to <YYYY-MM-dd HH:mm>
or
ev <description> /from <YYYY-MM-dd HH:mm> /to <YYYY-MM-dd HH:mm>
```

Example of usage:

```
event CS2103T meeting /from 2023-09-18 16:00 /to 2023-09-18 18:00
or
ev CS2103T meeting /from 2023-09-18 16:00 /to 2023-09-18 18:00
```

Expected outcome:

LilBro will add your event to your task list!

```
Got it. I've added this task:
[E][ ] CS2103T meeting (from: Mon 18 Sep 2023 16:00 to: Mon 18 Sep 2023 18:00)
Now you have 1 tasks in the list.
```

### 4. List all tasks

Lists all tasks in your task list.

Command format:

```
list
or
ls
```

Example of usage:

```
list
or
ls
```

Expected outcome:

LilBro will list all your tasks!

```
Here are the tasks in your list:
1. [T][ ] CS2103T iP
2. [D][ ] CS2105 Assignment (by: Mon 25 Sep 2023 23:59)
3. [E][ ] CS2103T meeting (from: Mon 18 Sep 2023 16:00 to: Mon 18 Sep 2023 18:00)
```

### 5. Mark tasks as done

Marks a task as done.

Command format:

```
mark <task number>
or
m <task number>
```

Example of usage:

```
mark 1
or
m 1
```

Expected outcome:

LilBro will mark your task as done!

```
Nice! I've marked this task as done:
[T][X] CS2103T iP
```

### 6. Unmark tasks as done

Unmarks a task as done.

Command format:

```
unmark <task number>
or
u <task number>
```

Example of usage:

```
unmark 1
or
u 1
```

Expected outcome:

LilBro will unmark your task as done!

```
OK, I've marked this task as not done yet:
[T][ ] CS2103T iP
```

### 7. Delete tasks

Deletes a task from your task list.

Command format:

```
delete <task number>
or
rm <task number>
```

Example of usage:

```
delete 1
or
rm 1
```

Expected outcome:

LilBro will delete your task!

```
Noted. I've removed this task:
[T][ ] CS2103T iP
```

### 8. Find tasks

Finds tasks that contain the keyword.

Command format:

```
find <keyword>
or
f <keyword>
```

Example of usage:

```
find CS2105
or
f CS2105
```

Expected outcome:

LilBro will list all tasks that contain the keyword!

```
Nice! I've found some related tasks:
1. CS2105 Assignment (by: Mon 25 Sep 2023 23:59)
```

### 9. Exit

Exits the program.

Command format:

```
bye
```

Example of usage:

```
bye
```

Expected outcome:

LilBro will close after 1 second!

```
Bye. Hope to see you again soon!
```
