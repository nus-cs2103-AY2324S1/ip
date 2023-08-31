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

# Change to root directory to compile the jar
cd ../

# compile the code into the bin folder, terminates if error occurred
if ! ./gradlew shadowJar
then
    echo "********** BUILD FAILURE **********"
    exit 1
fi

# Temporarily move the existing save file to another name so that we can test and tear down
mv data/data.json data/backup.json

# run the program, feed cyrus.commands from input.txt file and redirect the output to the ACTUAL.TXT
# Execute the jar instead
# Must run the jar from root directory
java -jar build/libs/duke.jar < text-ui-test/input.txt > text-ui-test/ACTUAL.txt

cd text-ui-test/

# convert to UNIX format
cp EXPECTED.TXT EXPECTED-UNIX.TXT
dos2unix ACTUAL.TXT EXPECTED-UNIX.TXT

# Once done with test, restore original data file
rm ../data/data.json
mv ../data/backup.json ../data/data.json

# compare the output to the expected output
diff ACTUAL.TXT EXPECTED-UNIX.TXT
if [ $? -eq 0 ]
then
    echo "Test result: PASSED"
    exit 0
else
    echo "Test result: FAILED"
    exit 1
fi