package com.jzhome.core.domain;

import java.time.ZonedDateTime;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "ipObject")

public class IpObject {

    @Id
    private ObjectId objectId;
    private String ip;
    private String where;
    private ZonedDateTime when;

    @Override
    public String toString() {
        return "IpObject{" +
            "objectId=" + objectId +
            ", ip='" + ip + '\'' +
            ", where='" + where + '\'' +
            '}';
    }


    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getWhere() {
        return where;
    }

    public void setWhere(String where) {
        this.where = where;
    }

    public ObjectId getObjectId() {
        return objectId;
    }

    public void setObjectId(ObjectId objectId) {
        this.objectId = objectId;
    }

    public ZonedDateTime getWhen() {
        return when;
    }

    public void setWhen(ZonedDateTime when) {
        this.when = when;
    }
}
