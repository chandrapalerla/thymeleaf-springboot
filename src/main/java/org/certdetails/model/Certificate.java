package org.certdetails.model;

import java.util.Date;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Certificate {
    private int id;
    private String certificateName;
    private Date expirationDate;
    private String status;
    private String serverName;
}