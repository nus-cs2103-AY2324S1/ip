# User Guide
**Miles** is a chatbot with a GUI that serves as a to-do list and provides support for various types of tasks (To-Dos, Deadlines, Events). 

## Getting Started
1. Ensure you have Java 11 installed in your machine.
2. Download `miles.jar` from this repository.
3. Navigate to the directory containing `miles.jar`.
4. Run the following command

   ```
   java -jar miles.jar
   ```
5. The chatbot GUI should appear.

## Features 

### Add a ToDo

```
todo [description]
```

Example of usage: `todo read book`

### Add a Deadline

```
deadline [description] /by [time]
```

Example of usage: `deadline finish essay /by 2023-09-20 2359`

### Add a Event
```
event [description] /from [start time] /to [end time]
```

Example of usage: `event attend wedding /from 2023-09-20 1900 /to 2023-09-20 2300`

### Delete a task
```
delete [task number]
```

Example of usage: `delete 5`

### Mark task as done
When user inputs a number `n`, the `nth` task in the list will be marked with an `X`.
```
mark [task number]
```

Example of usage: `mark 5`

### Mark task as not done
When user inputs a number `n`, the `nth` task in the list will be unmarked and if there was an `X`, the `X` will disappear.
By default, all tasks are marked as not done yet. User can unmark task in the event that a task was accidentally marked as done. 
```
unmark [task number]
```

Example of usage: `unmark 5`

### Listing all the tasks
```
list
```

### Find task by keyword
This will find all tasks with the particular keyword in their description.
```
find [keyword]
```

Example of usage: `find soju`

### Exit 
This will close the chatbot. All changes have already been saved.
```
bye
```