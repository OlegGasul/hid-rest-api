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
            
            render data?.value
        } catch (Throwable t) {
            return renderServerError(t)
        }

    }

    @Secured("ROLE_USER")
    def insert() {
        response.setContentType("application/octet-stream")
        response.setHeader("Content-disposition", "attachment; filename=\"myFileName\"")
        response.outputStream << new File(grailsApplication.config.storage.path).newInputStream()
    }

    @Secured("ROLE_USER")
    def update() {

    }

    @Secured("ROLE_USER")
    def delete() {

    }

    def renderNotFound() {
        response.status = 404;
    }

    def renderServerError(Throwable t) {
        response.status = 404;
        [result: false, message: t.message] as JSON
    }
}
