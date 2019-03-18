package com.andersen.iexapis.endpoint.dto.datastore;

import lombok.Data;
import org.springframework.cloud.gcp.data.datastore.core.mapping.Entity;
import org.springframework.data.annotation.Id;

@Data
@Entity
public class Company {

    @Id
    private String symbol;
    private String logo;
    private String name;
}
