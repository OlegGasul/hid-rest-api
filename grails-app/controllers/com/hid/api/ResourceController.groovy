package com.hid.api

import grails.core.GrailsApplication
import grails.plugin.springsecurity.annotation.Secured
import grails.rest.*
import grails.converters.*
import org.springframework.beans.factory.annotation.Autowired

class ResourceController {
    @Autowired
    GrailsApplication grailsApplication

    def resourceService

    def clazz
    def key

    @Secured("ROLE_USER")
    def get() {
        try {
            def data = resourceService.findData(params.clazz, params.key)
            if (!data) {
                return renderNotFound()
            }

            response.setContentType data.contentType
            response.setContentLengthLong data.contentLength

            if (data.file) {
                // todo
                // read data from file
                response.outputStream << new File("${grailsApplication.config.storage.path}/${params.clazz}/${params.key}/${grailsApplication.config.storage.default_file_data_name}").newInputStream()
            } else {
                // todo
                // return data from value
                response.outputStream << data.value
            }
        } catch (Throwable t) {
            return renderServerError(t.message)
        }
    }

    @Secured("ROLE_USER")
    def insert() {
        resourceService.setData(params.clazz, params.key, request.contentType, request.contentLength, request.inputStream)
        renderOK()
    }

    @Secured("ROLE_USER")
    def head() {
        try {
            def data = resourceService.findData(params.clazz, params.key)
            if (!data) {
                return renderNotFound()
            }

            response.setContentType data.contentType
            response.setContentLengthLong data.contentLength

            renderOK()
        } catch (Throwable t) {
            return renderServerError(t.message)
        }
    }

    @Secured("ROLE_USER")
    def delete() {
        resourceService.deleteData(params.clazz, params.key) ? renderOK() : renderNotFound()
    }

    def renderOK() {
        response.status = 200
        [result: true] as JSON
    }

    def renderNotFound() {
        response.status = 404
    }

    def renderServerError(def message) {
        response.status = 503
        [result: false, message: message] as JSON
    }
}
