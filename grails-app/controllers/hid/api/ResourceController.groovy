package hid.api

import grails.core.GrailsApplication
import grails.plugin.springsecurity.annotation.Secured
import grails.rest.*
import grails.converters.*
import org.springframework.beans.factory.annotation.Autowired

class ResourceController {
    @Autowired
    GrailsApplication grailsApplication

    def clazz
    def key

    @Secured("ROLE_USER")
    def index() {
        response.setContentType("application/octet-stream")
        response.setHeader("Content-disposition", "attachment; filename=\"myFileName\"")
        response.outputStream << new File(grailsApplication.config.storage.path).newInputStream()
    }

    @Secured("ROLE_USER")
    def insert() {
        response.setContentType("application/octet-stream")
        response.setHeader("Content-disposition", "attachment; filename=\"myFileName\"")
        response.outputStream << new File(grailsApplication.config.storage.path).newInputStream()
    }
}
