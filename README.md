### To run the application :

- in one terminal execute : `mvn tomcat7:run -pl *-server -am -Denv=dev` 
- in another terminal execute : `mvn gwt:codeserver -pl *-client -am`
- access the application from your browser at `http://localhost:8080`