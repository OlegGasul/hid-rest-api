package com.hid.api.service

import grails.transaction.Transactional
import com.hid.api.model.Clazz
import com.hid.api.model.Data

@Transactional
class ResourceService {

    def findData(def clazzName, def keyName) {
        def clazz = Clazz.findByName(clazzName)
        if (!clazz)
            return
        Data.findByClazzAndKey(clazz, keyName)
    }
}
