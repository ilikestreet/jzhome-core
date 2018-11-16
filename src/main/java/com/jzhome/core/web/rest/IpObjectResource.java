package com.jzhome.core.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.jzhome.core.domain.IpObject;
import com.jzhome.core.exception.DataExistException;
import com.jzhome.core.service.IpObjectService;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class IpObjectResource {

    private final Logger log = LoggerFactory.getLogger(IpObjectResource.class);

    @Autowired
    private IpObjectService ipObjectService;

    @PostMapping("/ipByWhere")
    @Timed
    public ResponseEntity<Map<String, Object>> ipByWhere(@RequestPart String where) {
        HttpHeaders httpHeaders = new HttpHeaders();
        Map<String, Object> response = new HashMap<>();
        IpObject ipObject = ipObjectService.getIpObjectByWhere(where);
        if (ipObject != null) {
            response.put("status", true);
        } else {
            response.put("status", false);
        }
        response.put("data", ipObject);
        return new ResponseEntity<>(
            response,
            httpHeaders,
            HttpStatus.OK);
    }

    @PostMapping("/addIp")
    @Timed
    public ResponseEntity addIp(@RequestPart String ip, @RequestPart String where) {
        Map<String, Object> response = new HashMap<>();
        HttpHeaders httpHeaders = new HttpHeaders();
        try {
            ipObjectService.addIpObject(ip, where);
            response.put("status", true);
            response.put("message", "success");

        } catch (DataExistException dee) {
            response.put("status", false);
            response.put("message", dee.getMessage());
        }
        return new ResponseEntity<>(
            response,
            httpHeaders,
            HttpStatus.OK);
    }
}
