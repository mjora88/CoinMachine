============================================
************* HOW TO SET UP ****************
============================================

This is a Spring Boot application, built with Maven. 

1. You need to update the properties yaml file in src/main/resources/application.yml. In this file you only have to change the database login details. 

2. You need to create a databsae shcema on your local machine and table generation would be automated.
 <code> create schema kjKhoza; </code>


=================================================
**************** HOW TO RUN *********************
=================================================

Assuming that you have maven on your machine, run the following commands on inside the project root:

1. mvn clean package (this will build and package your project).
2. mvn spring-boot:run (this will run lauch this rocket to the moon! No, don't stress, nothing's doing  to the moon, but this command should launch this app anyway!)
3. Now open the browser and visit: http://locahost:8080/
4. Be happy!

NB: The first user to register will be granted admin rights! This is to avoid having to go to the database to manually grant admin rights! 
