Introduction
=============
This is a web api that processes rules and inserts data into the Drools Engine KieSession.

The basic idea of this webapp is to show a simple UI where you can insert data. After a certain number of records have been reached for a particular id, a special alert will trigger.

There is a custom .drl file that is manually written into the webapp on initialization. This can be found in the resources folder

All information is shown in the system logs output.

#### Deployment
After building project, run following command to deploy.
````
docker run -d -p 5000:8080 drools_api
````
The web app can be accessed at
````
192.168.99.100:5000/drools/rest/drools
````
#### Home Page (UI)
/drools/rest/drools/

The Home page includes buttons that will trigger the below requests to the api

#### Insert Data
/drools/rest/drools/datum

Inserts one piece of data into the KieSession.

The data is a Hashmap with an id and data

#### Fire Rules
/drools/rest/drools/firerules

Fires off all rules currently stored in KieSession

#### Delete and Restart KieSession
/drools/rest/drools/delete

Deletes the current KieSession and restarts another session. This deletes all rules and data in the previous session.

#### Show number of facts
/asc/rest/drools/showall

Displays the number of facts within the KieSession