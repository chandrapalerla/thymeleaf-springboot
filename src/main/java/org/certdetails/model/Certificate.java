package org.certdetails.model;

import java.util.Date;

public class Certificate {
    private int id;
    private String certificateName;
    private Date expirationDate;
    private String status;
    private String serverName;

    public Certificate(int id, String certificateName, Date expirationDate, String status, String serverName) {
        this.id = id;
        this.certificateName = certificateName;
        this.expirationDate = expirationDate;
        this.status = status;
        this.serverName = serverName;
    }

    // Getters and setters (or use Lombok's @Data)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCertificateName() {
        return certificateName;
    }

    public void setCertificateName(String certificateName) {
        this.certificateName = certificateName;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }
}