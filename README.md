# Duke Personal Assistant

Have you ever needed to organize your tasks, deadlines and event in a neat way? Are 
you sick of other task organization apps on the internet with actual quality? Never
fear, because DukeBot is here to cure you of your malaise.


## Starting the application

Prerequisites: Java 11

1. Download the Duke.jar from the latest releases into a directory. 
2. Run the command `java -jar Duke.jar` in that directory.
3. A window should appear on your screen, and our Duke agent will greet you.
 
Note: A `data` folder will be created in that directory if it does not exist to store
Duke-related data. 

## Task Management

Duke classifies tasks into 3 categories: Todo, Deadline and Event. You can add, delete
and list tasks by typing commands in the text area at the bottom of the window and pressing
'Enter' to submit, after which you will receive a reply from Duke either confirming the action
has been taken or that there was an error in parsing the command. 

### Adding Todo tasks

To add a todo task, type `todo [task description]` . (eg. `todo feed cat`)

### Adding Deadline tasks

To add a deadline task, type `deadline [task description] \by [date] `. Only 
accepted datetime formats (see Accepted Datetimes section for more details) will be recognized by Duke. (eg. `deadline submit PS1 /by 25/9/2023`)

### Adding Event tasks

To add an event task, type `event [task description] \from [date] \to [date]`. Only
accepted datetime formats (see Accepted Datetimes section for more details) will be recognized by Duke.
(eg. `event code review /from 8/8/2023 1700 /to 8/8/2023 1800`)

### Listing tasks

To list all tasks, type `list`. When listed, a task may appear as follows 
`1. [E][X] code review (from: 2023-08-08 17:00 to: 2023-08-08 18:00) [Prority: High]`,
where the number indicates its position in the list, the first box indicates the task type
(either T, D or E), the second box indicates whether it is marked as completed (X if marked, empty if not),
followed by the task description. This is followed by a parentheses block if there are the associated
datetimes of the task (only applicable for event and deadlines). This is followed by square bracket block 
indicating the task is of high priority if it has been marked as such. 

### Removing tasks
To remove a task, type `remove [task_no]` where task_no is the 1-based index of the 
task in the list. You can view this the indexes of all tasks in the list with the `list` command.

### Marking tasks
To mark a task as completed, type `mark [task_no]` where task_no is the 1-based index of the
task in the list. 

### Unmarking tasks
To mark a task as not completed, type `unmark [task_no]` where task_no is the 1-based index of the
task in the list. 

### Find tasks
To locate a task in the list by a certain substring, type `find [substring]`.
Duke will list all tasks whose task descriptions contains the substring. 

### Saving tasks
All data is saved after any command besides `list` has been successfully carried out. 