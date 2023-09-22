# Adam’s ChatBot
Welcome to the Adam’s ChatBot user guide. Adam is capable of tracking your to do list.
It is an application that uses Command Line Interface (user text inputs) and
Graphics User Interface to showcase interactive list management.
## Getting Started
1.	Ensure that you have [installed Java 11](https://www.oracle.com/java/technologies/javase/jdk11-archive-downloads.html) or above installed.
2.	Download the latest [Duke.jar]().
3.	Using the command terminal, go to the destination folder of your Duke.jar and then run the command `java -jar Duke.jar`.
4.	A Window should appear.
5.	Now type in your inputs and start using the ChatBot.
      .

## 2. Commands
In Adam’s ChatBot application, you will use text to interact with the application. Below are the commands you can use:


### Command List:
1. [ToDo](#todo)
2. [Deadline](#deadline)
3. [Event](#event)
4. [List](#list)
5. [Delete](#delete)
6. [Mark](#mark)
7. [Unmark](#unmark)
8. [Find](#find)
9. [Update](#update)

<br><br><br>

### ToDo
#### Usage: 
todo *TASK_DESCRIPTION*

#### Description: 
Adds a to-do task with the specified description. Replace the `TASK_DESCRIPTION` with the desired name of the task you want to add to the list.

#### Example:
`todo Read a book`

<br><br><br>

### Deadline
#### Usage: 
deadline *TASK_DESCRIPTION* /by *DATE_AND_TIME*
#### Description: 
Adds a deadline task with a specified description and due date.

The date and time should be in one of the supported formats: *yyyy-MM-dd HH:mm*, *dd/MM/yyyy HH:mm*, *MM-dd-yyyy HH:mm*.

#### Example: 
`deadline Submit report /by 2023-09-01 18:00`

`deadline Buy snacks /by 01/09/2023 18:00`

`deadline Buy computer /by 09-01-2023 18:00`

<br><br><br>

### Event

#### Usage: 
event *TASK_DESCRIPTION* /from *DATE_AND_TIME* /to *DATE_AND_TIME*

#### Description: 
Adds an event task with a specified description, start date, and end date. 
The dates and times should be in one of the supported formats mentioned above.

#### Example: 
`event Team meeting /from 2023-09-01 09:00 /to 2023-09-01 11:00`

<br><br><br>

### List

#### Usage: 
list

#### Description: 
Lists all the tasks that you have. 

`[T]` represents todo tasks, `[D]` presents deadline tasks, and `[E]` represents event tasks.

It also indicates if the tasks are cocmpleted, `[ ]` for incomplete and `[X]` for completed.

<br><br><br>

### Delete

#### Usage: 
delete *TASK_NUMBER*

#### Description: 
Deletes the task corresponding to the specified task number from the list.

#### Example: 
`delete 3`

<br><br><br>

### Mark

#### Usage: 
mark *TASK_NUMBER*

#### Description: 
Marks the task corresponding to the specified task number as done.  

It is represented by `[X]` in the list.

#### Example: 
`mark 2`

<br><br><br>

### Unmark

#### Usage: 
unmark *TASK_NUMBER*

#### Description: 
Unmarks the task corresponding to the specified task number, reverting it to undone.

It is represented by `[ ]` in the list.

#### Example: 
`unmark 2`

<br><br><br>

### Find

#### Usage: 
find *KEYWORD*

#### Description: 
Finds and lists all tasks that contain the specified keyword in the task description.

#### Example: 
`find book`

<br><br><br>

### Update

#### Usage: 
update *TASK_NUMBER* *FIELD* *NEW_VALUE*

#### Description: 
Updates the specified field of the task corresponding to the specified task number with the new value.

#### Example: 
`update 1 /by 2023-10-10 23:59`

`update 1 taskDescription Boil water`

`update 1 /from 2023-10-10 23:59`

`update 1 /to 2023-10-10 23:59`

<br><br><br>

### bye
#### Usage: 
bye

#### Description: 
Exits the application.

<br><br><br>

## Command Summary

| Command     | Usage   | Example                                                                                                                                           |
|-------------| ------- |---------------------------------------------------------------------------------------------------------------------------------------------------|
| todo        | todo *TASK_DESCRIPTION*   | `todo Read a book`                                                                                                                                |
| deadline    | deadline *TASK_DESCRIPTION* /by *DATE_AND_TIME*   | `deadline Submit report /by 2023-09-01 18:00`<br>`deadline Buy snacks /by 01/09/2023 18:00`<br>`deadline Buy computer /by 09-01-2023 18:00`       |
| event       | event *TASK_DESCRIPTION* /from *DATE_AND_TIME* /to *DATE_AND_TIME* | `event Team meeting /from 2023-09-01 09:00 /to 2023-09-01 11:00`                                                                                  |
| list        | list | `list`                                                                                                                                            |
| delete      | delete *TASK_NUMBER*   | `delete 3`                                                                                                                                        |
| mark        | mark *TASK_NUMBER*   | `mark 2`                                                                                                                                          |
| unmark      | unmark *TASK_NUMBER* | `unmark 2`                                                                                                                                        |
| find        | find *KEYWORD*   | `find book`                                                                                                                                       |
| update      | update *TASK_NUMBER* *FIELD* *NEW_VALUE*   | `update 1 /by 2023-10-10 23:59` <br>`update 1 taskDescription Boil water`<br>`update 1 /from 2023-10-10 23:59`<br>`update 1 /to 2023-10-10 23:59` |

<br><br><br>

## 3. Error Handling
In case of any errors, such as invalid date format or missing task description, the application will display an error message with details about what went wrong to guide you to fix the issue.


## 4. Auto-save List
Ending the  application with a `bye` command will save all the data of Task List into `./data/duke.txt`.
As such all the data will be saved in the hard disk automatically.


## 5. Conclusion
Thank you for using the Adam’s ChatBot. We hope this user guide helps you manage your tasks effectively and efficiently. Happy task managing!
