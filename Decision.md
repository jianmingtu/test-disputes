### ORDS Exception Handling [DESC-1]

When an exception occurred during communication between the Justin Disputes API and ORDS, the exception was caught, and 
a null value was returned from the method calling ORDS. This null value was not checked for by the calling function, 
resulting in a null pointer error happening shortly after, causing the API to return a 500.

In order to handle ORDS communication exceptions in a cleaner way, the ORDS communication method has been updated to 
throw precise exceptions based on the error that has occurred, and the method no longer returns null values.

### create/updateCitationDispute and Response Body [DESC-2]

When either the createCitationDispute or updateCitationDispute endpoints were called, and a citation dispute was created/updated 
successfully, a new object was returned from the Justin Disputes API that contained null in all fields. 

This functionality has been maintained, but the new object is now created in the controller class rather than the service class. 
The service class methods have been changed to be void methods to make it clearer that no objects are returned from ORDS when a 
citation dispute is successfully created or updated.
