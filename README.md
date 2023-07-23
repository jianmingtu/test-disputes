# Justin Disputes Api

To build this API, run:

```bash
mvn clean install
```

To run tests, use
```
mvn clean verify
```

## Environment variables
| Variable                  |                Example Value |
| ------------------------- | ---------------------------: |
|ORDS_USERNAME | user |
|ORDS_PASSWORD | password |
|ORDS_BASE_URL | http://example.com/ - make sure to include a trailing /|
|ORDS_GET_DISPUTES_ENDPOINT | vticbcfinddata |
|ORDS_CONNECT_TIMEOUT | 3000 |
|ORDS_READ_TIMEOUT | 3000 |
|KEYCLOAK_REALM | isb-api |
|KEYCLOAK_AUTH_SERVER_URL | https://keycloak-060d15-dev.apps.silver.devops.gov.bc.ca/auth - make sure NOT to include a trailing /|
|KEYCLOAK_SSL_REQUIRED | either external or none |
|KEYCLOAK_RESOURCE | justin-disputes-api |
|KEYCLOAK_CREDENTIALS_SECRET | GUID |
|KEYCLOAK_USE_ROLE_MAPPINGS | either true or false |
|MANAGEMENT_SERVER_PORT | 8086 |
|ORDS_NOTIFY_PROCESS_STATUS_ENDPOINT | vticbcfindresponse |
|ORDS_CREATE_CITATION_DISPUTE_ENDPOINT | vtdispute |
|ORDS_UPDATE_CITATION_DISPUTE_ENDPOINT | vtdisputestatus |

For exact values of secrets not specified here, refer to the secrets for the Dev environment in OpenShift