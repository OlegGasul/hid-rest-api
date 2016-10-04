package com.hid.api.model

class Data {
    static belongsTo = [clazz: Clazz]

    static constraints = {
        value nullable: true
    }

    String key
    String contentType
    Long contentLength
    Boolean file = false
    String value
}
