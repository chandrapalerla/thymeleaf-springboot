package org.certdetails.model;

import lombok.*;

@Getter
@Setter
@Data
public class DropdownRequest {
    private boolean dev;
    private boolean qa;
    private boolean prod;
}