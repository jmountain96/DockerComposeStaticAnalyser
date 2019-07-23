# DockerComposeStaticAnalyser
University of York Final Year Project

Static analysis is a significant component of code development. It detects software vulnerabilities before code execution, allowing defects to be found at the early stages of the software lifecycle. This project aims to design and implement a static analyser for the configuration files of Docker Compose.

The implementation of this tool provides the following features.


1) Parse YAML files - The analyser will parse all valid YAML files into a representation that can be stored within Java and manipulated  
2) Non-Halting Error Detection - The analyser will display all detectable errors rather than halting upon detection of the first error 
3) Version 2+ compatibility - The analyser must be compatible with version 2 + of Docker Compose
4) Testing for the following fatal errors 
a) Resolve Key - Each key must resolve to a Docker Compose key that is listed in the reference 
b) Valid Key Position - Each key must be declared in a valid position. E.g. ‘Version’ can only be declared at the top level. 
c) Resolve Field Type - Each field must resolve to a valid type e.g. String, Integer 
d) Valid Field Type - Each field must be parsed to a type that is valid for the corresponding key 
e) Valid Spacing - The spacing within the YAML file must conform to a valid YAML format 
f) Valid Dependencies - Dependencies must correspond to variables that have been declared within the Compose file. 
g) Required Fields - Certain fields must be declared within the Compose file.  
h) Valid Format - Some fields that are strings must conform to a valid format 
i) Valid File Location/Directory - When a field references a file, it must specify an existing file location/directory.  
j) Fields Contain Valid Values - Fields that can only contain certain discrete values must only contain these values. E.g. the port protocol can only be TCP or UDP. 
k) Version Validation - Keys and values that are used must correspond to the Docker Compose version that has been declared 
5) Testing for the following non-fatal errors
a) Duplicate Keys - Provides a warning when the same key is declared twice within the same context 
b) Duplicate Fields - Provides a warning when the same value is declared twice within the same context 
c) Unused Variables - Provides a warning when a top-level value isn’t used by any service. 

The average analysis time of a YAML file is 0.04 seconds.

The analysis is executed within Eclipse.

To run the linter, the path to the target file must be inserted into the main method as a string. An alternative to this, disabled by default, allows the user to insert the file name in the console at run time. After the run button is pressed the validation will start. All validation messages will be printed to the console and should be descriptive enough to allow the user to gain an understanding of what has caused the issue. Any uncaught exceptions, such as an IOException caused by an incorrect file path for the compose file, will cause the program to halt as the validation can’t take place while these errors are present. The linter can then be executed by running the main method of the YAMLParser class as a Java application. 
