package edu.aam.app.model;

import edu.aam.app.model.enums.LogType;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "logs", schema = "BEN", indexes = {@Index(name = "logs_create_time", columnList = "create_time")})
public class Log extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "custom-id")
    @GenericGenerator(name = "custom-id", strategy = "edu.aam.app.model.support.CustomIdGenerator")
    private Long id;

    /**
     * Log key.
     */
    @Column(name = "log_key", length = 1023)
    private String logKey;

    /**
     * Log type.
     */
    @Column(name = "type", nullable = false)
    private LogType type;

    /**
     * Log content.
     */
    @Column(name = "content", length = 1023, nullable = false)
    private String content;

    /**
     * Operator's ip address.
     */
    @Column(name = "ip_address", length = 127)
    private String ipAddress;

    @Override
    public void prePersist() {
        super.prePersist();

        if (logKey == null) {
            logKey = "";
        }

        // Get ip address
        // ###!!! Do not get request IP from here due to asynchronous
        // ipAddress = ServletUtils.getRequestIp();

        if (ipAddress == null) {
            logKey = "";
        }
    }

    public Long getId() {
        return id;
    }

    public String getLogKey() {
        return logKey;
    }

    public void setLogKey(String logKey) {
        this.logKey = logKey;
    }

    public LogType getType() {
        return type;
    }

    public void setType(LogType type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
}
