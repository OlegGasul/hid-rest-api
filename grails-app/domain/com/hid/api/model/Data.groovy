package com.hid.api.model

class Data {
    static belongsTo = [clazz: Clazz]

    static constraints = {
    }

    String key
    String value
    String contentType
}
