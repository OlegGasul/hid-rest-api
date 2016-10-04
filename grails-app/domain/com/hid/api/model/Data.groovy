package com.hid.api.model

class Data {
    static belongsTo = [clazz: Clazz]

    static constraints = {
        value nullable: true
    }

    String key
    String contentType
    int contentLength
    Boolean file = false
    byte[] value
}
