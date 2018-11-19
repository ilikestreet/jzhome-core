package com.jzhome.core.repository;

import com.jzhome.core.domain.IpObject;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IpObjectRepository extends MongoRepository<IpObject, String> {

    IpObject findIpObjectByWhere(String where);

    List<IpObject> findAllByWhere(String where);
}
