package com.hid.api.service

import grails.core.GrailsApplication
import grails.transaction.Transactional
import com.hid.api.model.Clazz
import com.hid.api.model.Data
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType

@Transactional
class ResourceService {
    @Autowired
    GrailsApplication grailsApplication

    def findData(def clazzName, def keyName) {
        def clazz = Clazz.findByName(clazzName)
        if (!clazz)
            return
        Data.findByClazzAndKey(clazz, keyName)
    }

    def setData(def clazzName, def keyName, def contentType, def contentLength, def inputStream) {
        def clazz = Clazz.findOrSaveByName(clazzName.toLowerCase())

        def data = Data.findByClazzAndKey(clazz, keyName)
        if (!data) {
            data = new Data(clazz: clazz, key: keyName.toLowerCase())
        }

        data.contentLength = contentLength
        data.contentType = contentType
        data.value = inputStream.text.getBytes()
        data.save(failOnError: true, flush: true)
    }

    def deleteData(def clazzName, def keyName) {
        def data = findData(clazzName, keyName)
        if (!data)
            return false

        data.delete(failOnError: true, flush: true)

        true
    }
}
