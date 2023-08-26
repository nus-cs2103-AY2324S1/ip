#!/usr/bin/env bash

# create bin directory if it doesn't exist
if [ ! -d "../bin" ]
then
    mkdir ../bin
fi

# delete output from previous run
if [ -e "./ACTUAL.TXT" ]
then
    rm ACTUAL.TXT
fi

# set up the tasks.txt file
if [ -e "./data/tasks.txt" ]
then
  echo "E|T|splashdown|2022-04-24T00:00|2022-04-25T00:00
D|F|finish cs career|9999-12-31T00:00
T|F|finish cs2100 lecture
E|F|very big event oh no|2023-12-25T00:00|2023-12-26T00:00" > ./data/tasks.txt
fi

# compile the code into the bin folder, terminates if error occurred
if ! javac -cp \
../src/main/java/juke:\
../src/main/java/juke/actions:\
../src/main/java/juke/exceptions:\
../src/main/java/juke/exceptions/arguments:\
../src/main/java/juke/exceptions/storage:\
../src/main/java/juke/parsers:\
../src/main/java/juke/storage:\
../src/main/java/juke/tasks:\
../src/main/java/juke/core \
-Xlint:none -d ../bin \
../src/main/java/juke/*.java \
../src/main/java/juke/actions/*.java \
../src/main/java/juke/exceptions/*.java \
../src/main/java/juke/exceptions/arguments/*.java \
../src/main/java/juke/exceptions/storage/*.java \
../src/main/java/juke/parsers/*.java \
../src/main/java/juke/storage/*.java \
../src/main/java/juke/tasks/*.java \
../src/main/java/juke/core/*.java
then
    echo "********** BUILD FAILURE **********"
    exit 1
fi

# run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath ../bin main.java.Juke < input.txt > ACTUAL.TXT

# compare the output to the expected output, ignoring ending whitespaces, since the output generates them but the
# expected file erases them
diff ACTUAL.TXT EXPECTED.TXT -b --ignore-blank-lines
if [ $? -eq 0 ]
then
    echo "Test result: PASSED"
    exit 0
else
    echo "Test result: FAILED"
    exit 1
fi