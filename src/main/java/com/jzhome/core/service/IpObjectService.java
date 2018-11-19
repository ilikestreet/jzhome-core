package com.jzhome.core.service;

import com.jzhome.core.domain.IpObject;
import com.jzhome.core.exception.DataExistException;
import com.jzhome.core.repository.IpObjectRepository;
import java.time.ZonedDateTime;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IpObjectService {

    private final Logger log = LoggerFactory.getLogger(IpObjectService.class);

    private final IpObjectRepository ipObjectRepository;

    @Autowired
    public IpObjectService(IpObjectRepository ipObjectRepository) {
        this.ipObjectRepository = ipObjectRepository;
    }

    public List<IpObject> getAllIpObjectByWhere(String where) {
        return ipObjectRepository.findAllByWhere(where);
    }

    private IpObject getIpObjectByWhere(String where) {
        return ipObjectRepository.findIpObjectByWhereOrderByObjectIdDesc(where);
    }

    public void addIpObject(String ip, String where, ZonedDateTime when) throws DataExistException {
        if (getIpObjectByWhere(where) != null && getIpObjectByWhere(where).getWhen()
            .isBefore(when)) {
            throw new DataExistException("Data already exists in database");
        }
        IpObject ipObject = new IpObject();
        ipObject.setIp(ip);
        ipObject.setWhere(where);
        ipObject.setWhen(when);
        ipObjectRepository.save(ipObject);
    }
}
