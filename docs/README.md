# Project Rock
## The simple chatbot YOU need to run keep track of your daily tasks.

Rock is a chatbot created by Alvis Ng (supermii2) for the CS2103T individual project.
It is a simple-to-use programme created in java to allow users to keep track of their tasks.

## How to use it
1. Download a release and place it in an empty folder.
2. Open the command window in that folder
3. Run the command `java -jar Rock.jar`

## Features!
- Add To-Do, Deadline and Event tasks to a task list.
- Automatically save the task list and load it where you left off!
- Mark tasks as complete and remove them as necessary!
- Use tag-based commands to intuitively add tasks with the information you need
- See below for a list of commands

## Commands
Below is a table of commands that can be used to interact with the bot.\
All commands are sent in the format of `[command] [default input] [tagged inputs]`\
For example, to add a deadline task with name `Assignment` and deadline `2001-01-01`, type
`deadline /by 2001-01-01` \
Tagged inputs can be placed in any order!

|  Command   |                                               Description                                                |                  Default Input                  |                                                   Tagged Input                                                    |
|:----------:|:--------------------------------------------------------------------------------------------------------:|:-----------------------------------------------:|:-----------------------------------------------------------------------------------------------------------------:|
|    todo    |                          Creates a ToDo Task and inserts it into the task list                           |                  Name of Task                   |                                                       None                                                        |
|  deadline  |             Creates a Deadline Task, with a deadline time and inserts it into the task list              |                  Name of Task                   |                              &bull; /by : Date of Deadline time in YY-MM-DDDD format                              |
|   event    |         Creates an Event Task, with a start time and end time, and inserts it into the task list         |                  Name of Task                   | &bull; /from : Date of start time in YY-MM-DDDD format <br/>&bull; /to Date of Deadline time in YY-MM-DDDD format |
|    mark    |                              Marks the task at the given index as completed                              |  Index of task to mark as a numeral (e.g. "1")  |                                                       None                                                        |
|   unmark   |                             Marks the task at the given index as uncompleted                             | Index of task to unmark as a numeral (e.g. "1") |                                                       None                                                        |
|   delete   |                                     Removes task at the given index                                      | Index of task to remove as a numeral (e.g. "1") |                                                       None                                                        |
|   reset    |                                   Removes all tasks from the task list                                   |                      None                       |                                                       None                                                        |
|    list    |                                       Lists all tasks in task list                                       |                      None                       |                                                       None                                                        |
| listbydate |                              Lists all task whose by date is the one given                               |          By date in YYYY-MM-DD format.          |                                                       None                                                        |
|    find    |                         Lists all task which have the same name as the one given                         |           Name of task to search for            |                                                       None                                                        |
|    bye     | Terminates the program. Program is left open for user to see logs but no further commands can be entered |                      None                       |                                                       None                                                        |

## Acknowledgements
This project was built upon the base [Duke](https://github.com/nus-cs2103-AY2324S1/ip) provided by the CS2103T team!
Much of the GUI was heavily adapted from [se-education](https://se-education.org/guides/tutorials/javaFx.html)

Thank you to [Gracia](https://twitter.com/Hyrchurn/) and Jeremy for kindly providing me with user and bot avatars to use!