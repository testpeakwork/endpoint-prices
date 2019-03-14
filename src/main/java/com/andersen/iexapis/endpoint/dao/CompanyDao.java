package com.andersen.iexapis.endpoint.dao;

import com.andersen.iexapis.endpoint.dto.datastore.Company;
import org.springframework.cloud.gcp.data.datastore.repository.DatastoreRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyDao extends DatastoreRepository<Company, String> {

}
