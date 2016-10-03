package hid.api

import grails.plugin.springsecurity.annotation.Secured
import grails.rest.*
import grails.converters.*

class ResourceController {
    @Secured("ROLE_USER")
    def index() {
        response.setContentType("application/octet-stream")
        response.setHeader("Content-disposition", "attachment; filename=\"myFileName\"")
        response.outputStream << new File("C:\\books\\Oleg_Gasiul_CV.pdf").newInputStream()
    }
}
