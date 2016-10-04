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

        if (MediaType.APPLICATION_OCTET_STREAM_VALUE.equals(contentType)) {
            // todo
            // save to file
            data.value = null
            data.file = true;

            def path = "${grailsApplication.config.storage.path}/${clazzName}/${keyName}"
            new File(path).mkdirs()

            def file = new File("${path}/content.data")
            if (!file.exists()){
                file.createNewFile()
            }

            file << inputStream
        } else {
            // todo
            // store in database
            data.value = inputStream.text
            data.file = false;
        }

        data.save(flush: true)
    }
}
