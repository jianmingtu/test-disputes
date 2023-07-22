package ca.bc.gov.iamp.justindisputesapi.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "ords.timeout")
public class TimeoutProperties {
    private int connect;
    private int read;

    public int getConnect() {
        return connect;
    }

    public void setConnect(int connect) {
        this.connect = connect;
    }

    public int getRead() {
        return read;
    }

    public void setRead(int read) {
        this.read = read;
    }
}
