piscine-java, d04, ex01
----------------------------

COMPILATION

$ mkdir target
$ javac -d target src/java/edu/school21/printer/*/*
$ cp -r src/resources target

PACKAGING TO JAR

$ jar cmf src/manifest.txt target/images-to-chars-printer.jar -C target edu -C src resources

EXECUTION

$ java -jar target/images-to-chars-printer.jar <WHITE_CHAR> <BLACK_CHAR>
