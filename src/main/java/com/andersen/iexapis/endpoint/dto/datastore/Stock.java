package com.andersen.iexapis.endpoint.dto.datastore;

import com.google.cloud.Timestamp;
import com.google.cloud.datastore.Key;
import lombok.Data;
import org.springframework.cloud.gcp.data.datastore.core.mapping.Entity;
import org.springframework.cloud.gcp.data.datastore.core.mapping.Unindexed;
import org.springframework.data.annotation.Id;

@Data
@Entity
public class Stock {

    @Id
    private Key purchasedItemKey;
    private String companySymbol;
    private Timestamp dateTime;

    @Unindexed
    private double price;
}
