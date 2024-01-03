# ChatterBox
ChatterBox is your friendly neighbourhood task manager! Never forget anything ever again!

### Overview
ChatterBox is a desktop application for simple task management. The application is tailed for CLI-input with GUI-Output. The GUI is made with JavaFX.
It is written in Java 11, built with Gradle, testing with JUnit, and spans about 2 KLoC. This individual project was completed as part of the course
requirements for [CS2103 Software Engineering](https://nusmods.com/courses/CS2103/software-engineering), taken in NUS during AY23/24 Semester 1. 
My code contributions can be found on my RepoSense dashboard [here](https://nus-cs2103-ay2324s1.github.io/ip-dashboard/?search=sp4ce&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=java~md~fxml~sh~bat~gradle&since=2023-08-18&until=2023-04-30%23%2F&tabOpen=true&tabType=authorship&tabAuthor=sp4ce-cowboy&tabRepo=sp4ce-cowboy%2Fip%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=java~md~sh~gradle&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false). 

More information can be found on the product landing page [here](https://sp4ce-cowboy.github.io/ip/).

## Features 
ChatterBox comes with a bunch of features such as:
- Managing tasks
    - Todos, Events, Deadlines
- Mark and Unmark tasks
- Delete a single task
- Find a task
- Save tasks locally
- Mass delete all tasks (and start afresh!)
- Get help if you're ever confused

### Functionality

ChatterBox's primary interaction method is the keyboard, so you can keep working without having to lift your palms! The currently available primary commands include:
- `todo` \<task\>
- `event` /from <start> /to <end>
- `deadline` /by \<date\> (Note: date must be in this format "YYYY-MM-DD")

Other commands include:
- `list`
- `find` \<keyword\>
- `delete` \<index\>
- `mark` \<index\>
- `unmark` \<index\>

And when you wanna start over, there's always:

- `delete_all`

Once you're done with ChatterBox, simply enter:

- `bye`

### Considerations

All commands must be in full lowercase (for now).

If you are ever confused, simply type "help" and the start screen will show!

Note: The help screen will not show the `delete_all` command, and this is intentional, to ensure that you aren't led to accidentally and irreversibly delete all your data! This User Guide is a better way to convey that information.

## Usage

You can download the latest release from [here](https://github.com/sp4ce-cowboy/ip/releases/tag/A-Release-3.2) and then:

1. Navigate into the directory with

```sh
cd <PATH>
```

2. Run Chatterbox

```sh
java -jar duke.jar
```

Note: running the program will create a "data.txt" file in the current directory, this file stores all the tasks such that they persist even after closing the app. Also, if the latest release doesn't work or happens to be causing problems, try an older, more extensively tested version [here](https://github.com/sp4ce-cowboy/ip/releases/tag/A-Release-3.2).
