package org.certdetails.model;

public class ReportRequest {
    private boolean dev;
    private boolean qa;
    private boolean prod;
    private String devServer;
    private String qaServer;
    private String prodServer;

    // Getters and setters for all fields (or use Lombok's @Data)

    public boolean isDev() {
        return dev;
    }

    public void setDev(boolean dev) {
        this.dev = dev;
    }

    public boolean isQa() {
        return qa;
    }

    public void setQa(boolean qa) {
        this.qa = qa;
    }

    public boolean isProd() {
        return prod;
    }

    public void setProd(boolean prod) {
        this.prod = prod;
    }

    public String getDevServer() {
        return devServer;
    }

    public void setDevServer(String devServer) {
        this.devServer = devServer;
    }

    public String getQaServer() {
        return qaServer;
    }

    public void setQaServer(String qaServer) {
        this.qaServer = qaServer;
    }

    public String getProdServer() {
        return prodServer;
    }

    public void setProdServer(String prodServer) {
        this.prodServer = prodServer;
    }
}