# EnService For  Student Management

EnService is a user-friendly application that makes it easy for students to manage their marks and documents. 
With EnService, students can easily access and view their grades and academic records, as well as download important documents such as transcripts and certificates.

# Features
   Admins can :
   
       - add Students individualy or a batch in the form of a .csv file
       - search a specific student or a teacher 
       - announce and view the historical of their announcements
       - Easy-to-use interface with clear navigation
       
   Students can :
   
       - View and manage grades and academic records
       - Download important documents such as transcripts and certificates
       - Easy-to-use interface with clear navigation
       - Secure login to protect students' personal information
       
 # Installation
 
 in order to use this application you need to have docker installed and keycloak , postgres containers runing on you machines 
 
   1- To install Docker on Ubuntu, you can use the following command
   
      sudo apt-get install docker-ce
     
   2- Once Docker is installed, you can pull the Keycloak and Postgres containers using the docker pull command : 
   
      docker pull jboss/keycloak
      docker pull postgres
      
   3- To start the Keycloak and Postgres containers, you can use the docker run command:
   
      docker run -d --name keycloak -p 8080:8080 jboss/keycloak
      docker run -d --name postgres -p 5432:5432 postgres
   4- set your environnements variables 
   
   To set environment variables in the IntelliJ IDEA editor, you can follow these steps:

    1- Open your Spring Boot project in IntelliJ IDEA.

    2- In the top menu bar, click on the Run menu and select Edit Configurations.

    3- In the Edit Configurations window, select the configuration for your Spring Boot application and click on the Environment tab.

    4- In the Environment tab, click on the + button to add a new environment variable.

    5- In the Name field, enter the name of the environment variable. In the Value field, enter the value of the environment variable.

    6- Repeat steps 4 and 5 for each environment variable you want to set.

    7- Click on the Apply button to save the changes.

    8- Click on the OK button to close the Edit Configurations window.
    
   This is a list of the evn variables that you should set on your project :
    
    KEYCLOAK_ADMIN_PASSWORD=xxxxxxxxxxxxx
    KEYCLOAK_ADMIN_USERNAME=xxxxxxxxxxxxx
    KEYCLOAK_CLIENT_SECRET=xxxxxxxxxxxxx
    KEYCLOAK_REALM=xxxxxxxxxxxxxxx
    KEYCLOAK_SERVER_URL=xxxxxxxxxxx
    SPRING_DATASOURCE_PASSWORD=xxxxxxxxxxxx
    SPRING_DATASOURCE_URL=jdbc:xxxxxxxxxx
    SPRING_DATASOURCE_USERNAME=xxxxxx





    
   
   


