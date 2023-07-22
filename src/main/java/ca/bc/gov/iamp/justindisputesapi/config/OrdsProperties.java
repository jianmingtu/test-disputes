package ca.bc.gov.iamp.justindisputesapi.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "ords")
public class OrdsProperties {
    private String username;
    private String password;
    private String baseurl;
    private String getDisputesEndpoint;
    private String notifyProcessStatusEndpoint;
    private String createCitationDisputeEndpoint;
    private String updateCitationDisputeEndpoint;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBaseurl() {
        return baseurl;
    }

    public void setBaseurl(String baseurl) {
        this.baseurl = baseurl;
    }

    public String getGetDisputesEndpoint() {
        return getDisputesEndpoint;
    }

    public void setGetDisputesEndpoint(String getDisputesEndpoint) {
        this.getDisputesEndpoint = getDisputesEndpoint;
    }

    public String getNotifyProcessStatusEndpoint() {
        return notifyProcessStatusEndpoint;
    }

    public void setNotifyProcessStatusEndpoint(String notifyProcessStatusEndpoint) {
        this.notifyProcessStatusEndpoint = notifyProcessStatusEndpoint;
    }

    public String getCreateCitationDisputeEndpoint() {
        return createCitationDisputeEndpoint;
    }

    public void setCreateCitationDisputeEndpoint(String createCitationDisputeEndpoint) {
        this.createCitationDisputeEndpoint = createCitationDisputeEndpoint;
    }

    public String getUpdateCitationDisputeEndpoint() {
        return updateCitationDisputeEndpoint;
    }

    public void setUpdateCitationDisputeEndpoint(String updateCitationDisputeEndpoint) {
        this.updateCitationDisputeEndpoint = updateCitationDisputeEndpoint;
    }
}
