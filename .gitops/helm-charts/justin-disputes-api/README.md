## Parameters

### Section Title"

| Name                               | Description                                                                                                         | Value                 |
| ---------------------------------- | ------------------------------------------------------------------------------------------------------------------- | --------------------- |
| `replicaCount`                     | Number of replicas                                                                                                  | `1`                   |
| `image.repository`                 | what repo will be used                                                                                              | `justin-disputes-api` |
| `image.pullPolicy`                 |                                                                                                                     | `IfNotPresent`        |
| `image.tag`                        | Overrides the image tag whose default is the chart appVersion.                                                      | `""`                  |
| `nameOverride`                     |                                                                                                                     | `""`                  |
| `fullnameOverride`                 |                                                                                                                     | `""`                  |
| `podAnnotations`                   |                                                                                                                     | `{}`                  |
| `podSecurityContext`               |                                                                                                                     | `{}`                  |
| `securityContext`                  |                                                                                                                     | `{}`                  |
| `healthPort`                       | port to be used by the health endpoint                                                                              | `9999`                |
| `service.type`                     |                                                                                                                     | `ClusterIP`           |
| `service.port`                     |                                                                                                                     | `8080`                |
| `resources`                        |                                                                                                                     | `{}`                  |
| `probe.path`                       | defines the path to be reached when performing the HTTP get                                                         | `/actuator/health`    |
| `ords.username`                    | sets the username to communicate with ORDS. If none is given, and it does not exist, a random one will be generated | `""`                  |
| `ords.password`                    | sets the password to communicate with ORDS. If none is given, and it does not exist, a random one will be generated | `""`                  |
| `ords.baseurl`                     | sets the baseurl to base url with ORDS. If none is given, and it does not exist, a random one will be generated     | `""`                  |
| `ords.getDisputesEndpoint`         | sets the endpoint to communicate with ORDS. If none is given, and it does not exist, a random one will be generated | `""`                  |
| `ords.createCitationDisputeSecret` |                                                                                                                     | `""`                  |
| `ords.connect_timeout`             |                                                                                                                     | `30000`               |
| `ords.read_timeout`                |                                                                                                                     | `30000`               |
| `keycloak.realm`                   |                                                                                                                     | `""`                  |
| `keycloak.authServerUrl`           |                                                                                                                     | `""`                  |
| `keycloak.sslRequired`             |                                                                                                                     | `external`            |
| `keycloak.resource`                |                                                                                                                     | `""`                  |
| `keycloak.credentialsSecret`       |                                                                                                                     | `""`                  |
| `splunk.url`                       |                                                                                                                     | `""`                  |
| `splunk.token`                     |                                                                                                                     | `""`                  |


## How to generate the README.md parameters automatically from the values file

To accomplish that we can use the tool [readme-generator-for-helm](https://www.npmjs.com/package/readme-generator-for-helm).

To install it, run:
```shell
npm install -g readme-generator-for-helm
```
After that, you can use the tool, like for example: 
```shell
readme-generator --values ./values.yaml --readme ./README.md
```


