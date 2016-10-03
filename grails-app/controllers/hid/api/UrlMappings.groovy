package hid.api

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/api/resource/$clazz/key/$key"(controller: 'resource', action: 'index', method: 'GET')
        "/api/resource/$clazz/key/$key"(controller: 'resource', action: 'insert', method: 'POST')

        "/"(controller: 'application', action:'index')
        "500"(view: '/error')
        "404"(view: '/notFound')
    }
}
