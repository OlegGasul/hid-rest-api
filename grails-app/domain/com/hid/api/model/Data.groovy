package com.hid.api.model

class Data {
    static belongsTo = [clazz: Clazz]

    static constraints = {
    }

    String key
    String contentType
    Long contentLength
    Boolean file
    String value
}
