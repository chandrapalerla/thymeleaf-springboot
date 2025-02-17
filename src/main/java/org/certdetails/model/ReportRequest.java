package org.certdetails.model;

import lombok.*;

@Getter
@Setter
@Data
public class ReportRequest {
    private boolean dev;
    private boolean qa;
    private boolean prod;
    private String devServer;
    private String qaServer;
    private String prodServer;
}