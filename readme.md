Project is made of 3 modules:

    1. domain- domain objects annotated with JSON annotations
    2. service- Provides parsing and calculator services
    3. consoleapp- Has a console view and app with main method.

Gradle is used to build the project. Gradle build
creates an executable jar in the consoleapp module,
which can be run from command line, e.g.
java -jar consoleapp-1.0.jar

A copy of the latest jar is copied in the release folder.


Libraries and Frameworks:

1. Jackson- used to Object to JSON mapping
2. Jsoup- used for HTML parsing
3. Junit / Mockito for unit testing