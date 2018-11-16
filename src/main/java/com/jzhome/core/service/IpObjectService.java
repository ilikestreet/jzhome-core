package com.jzhome.core.service;

import com.jzhome.core.domain.IpObject;
import com.jzhome.core.exception.DataExistException;
import com.jzhome.core.repository.IpObjectRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IpObjectService {

    private final Logger log = LoggerFactory.getLogger(IpObjectService.class);

    @Autowired
    private IpObjectRepository ipObjectRepository;

    public IpObject getIpObjectByWhere(String where) {
        IpObject ipObject = ipObjectRepository.findIpObjectByWhere(where);
        return ipObject;
    }

    public void addIpObject(String ip, String where) throws DataExistException {
        if (getIpObjectByWhere(where) != null) {
            throw new DataExistException("Data already exists in database");
        }
        IpObject ipObject = new IpObject();
        ipObject.setIp(ip);
        ipObject.setWhere(where);
        ipObjectRepository.save(ipObject);
    }
}
