# API Build Instructions
* Project uses Spring Boot 2.6.1.
* Ideally project is run through IntelliJ for development.
* Project must be built as a Maven project.
* Upon opening project open the pom.xml file and click "trust maven project" on banner pop-up.
* Open configurations editor and create an application configuration for the API
* Add environmental variables for the DB_USER, DB_PASS, and DB_NAME in order to connect to the database.
* Click the big green play button and have fun.
# API package structure and class information
* Analytics contains classes to generate analyitcal information and tester class.
* The restClient class is used to interface with the actual API using http calls.
* The controllers package contains API controllers that handle http calls to the API.
* Repositories contains repositories for each database table, and inherit from JPA repository. the controllers call the functions stored in the repositories.
* Models contains the java object representations of the database entities/tables.
* Configuration has a config class for the API. you can use this to enable certain options for springboot API's such as multithreading.
# Multithreading in FamilyTree.java
* We use a data structure called "runnables" to allow for multithreaded calls to the API.
* In familyTree.java a collection of runnable objects are created in order to run the addChain() recursive function for each founder all at once accross several threads.
* This allows us to build the family tree for every founder all at once instead of one at a time iteratively.
* We do also have a function that converts the runnables to callables because the package we use to run all of the threads in bulk requires callables rather than runnables.