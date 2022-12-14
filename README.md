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
   





    
   
   


