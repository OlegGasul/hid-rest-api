package com.hid.api.service

import grails.transaction.Transactional

@Transactional
class ResourceService {

    def findData(def clazzName, def keyName) {
        def clazz = Clazz.findByName(clazzName)
        if (!clazz)
            return
        Data.findByClazzAndKey(clazz: clazz, key: keyName)
    }
}
