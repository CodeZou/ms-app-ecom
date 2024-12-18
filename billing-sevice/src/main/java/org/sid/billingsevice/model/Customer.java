package org.sid.billingsevice.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class Customer {
    private Long id;
    private String name;
    private String email;
}
