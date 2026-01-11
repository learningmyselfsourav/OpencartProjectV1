Github:
learningmyself.sourav@gmail.com -- S0urav@19

Jenkins:
learningmyself.sourav@gmail.com -- S0urav@19
SouravAdmin - S0urav@19
url: http://localhost:8080/

github token: 08789d121cb14556bea0f5af37d3bf0f

Step 1: Open XAMPP in admin mode and start Apache + MySQL + Tomcat --> to run the Opencart application hosted local system
 	frontend URL: http://localhost/opencart/upload/
 	backend URL: http://localhost/opencart/upload/admin/	[Username: admin / Password: admin]

Step 2: Select "execution_env" as local or remote per requirement from "config.properties" file
 	if you select "local" then no additional setup is required
 	if you select "remote" then perform following steps to start selenium grid hub in standalone setup:
 	Step a: go to <path to the project location>\OpenCartV1
 	Step b: open command prompt and then type java -jar selenium-server-4.39.0.jar standalone and let it be up and running
 		grid URL: http://localhost:4444/ui/	[here you can see number of available session for each browser]
 	step c: now run automation
 	if you select "remote" and run selenium grid in docker container then follow docker doc

Step 3: To run automation in Jenkins CICD tool (hosted in local system) follow below steps:
 	Step a: go to <path to the project location>\OpenCartV1
 	Step b: open command prompt and then type java -jar jenkins.war --enable-future-java and let it be up and running
 		Jenkins URL: http://localhost:8080/login?form=%2F	[sign in with credentials]
