package com.hid.api

import grails.core.GrailsApplication
import grails.plugin.springsecurity.annotation.Secured
import grails.converters.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus

@Secured("ROLE_USER")
class ResourceController {
    @Autowired
    GrailsApplication grailsApplication

    def resourceService

    def clazz
    def key

    def post() {
        try {
            resourceService.setData(params.clazz, params.key, request.contentType, request.contentLength, request.inputStream)
            render okResult()
        } catch (Throwable t) {
            log.error "Error: ${t.message}", t
            render serverError(t.message)
        }
    }

    def get() {
        try {
            def data = resourceService.findData(params.clazz, params.key)
            if (!data) {
                render notFound()
                return
            }

            response.setContentType data.contentType
            response.setContentLength data.contentLength
            response.outputStream << data.value
        } catch (Throwable t) {
            log.error "Error: ${t.message}", t
            render serverError(t.message)
        }
    }

    def delete() {
        try {
            render resourceService.deleteData(params.clazz, params.key) ? okResult() : notFound()
        } catch (Throwable t) {
            log.error "Error: ${t.message}", t
            render serverError(t.message)
        }
    }

    def head() {
        try {
            def data = resourceService.findData(params.clazz, params.key)
            if (!data) {
                render notFound()
                return
            }

            response.setContentType data.contentType
            response.setHeader "X-Content-Length", data.contentLength + ''
            render okResult()
        } catch (Throwable t) {
            log.error "Error: ${t.message}", t
            render serverError(t.message)
        }
    }

    def okResult() {
        response.status = HttpStatus.OK.value
        [result: true] as JSON
    }

    def notFound() {
        response.status = HttpStatus.NOT_FOUND.value
    }

    def serverError(def message) {
        response.status = HttpStatus.SERVICE_UNAVAILABLE.value
        [result: false, message: message] as JSON
    }
}
