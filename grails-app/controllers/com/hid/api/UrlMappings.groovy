package com.hid.api

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/api/resource/$clazz/key/$key"(controller: 'resource', action: 'post', method: 'POST')
        "/api/resource/$clazz/key/$key"(controller: 'resource', action: 'get', method: 'GET')
        "/api/resource/$clazz/key/$key"(controller: 'resource', action: 'post', method: 'PUT')
        "/api/resource/$clazz/key/$key"(controller: 'resource', action: 'delete', method: 'DELETE')
        "/api/resource/$clazz/key/$key"(controller: 'resource', action: 'head', method: 'HEAD')

        "/"(controller: 'application', action:'index')
        "500"(view: '/error')
        "404"(view: '/notFound')
    }
}
