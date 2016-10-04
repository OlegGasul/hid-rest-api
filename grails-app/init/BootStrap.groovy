import com.hid.api.model.Clazz
import com.hid.api.model.Data
import com.hid.api.security.AppUser
import com.hid.api.security.AppUserRole
import com.hid.api.security.Role

class BootStrap {

    def init = { servletContext ->
//        def userRole = Role.findOrSaveByAuthority('ROLE_USER')
//        def testUser = AppUser.findOrSaveByUsernameAndPassword('root', 'se3ret123')
//
//        AppUserRole.create testUser, userRole
//
//        AppUserRole.withSession {
//            it.flush()
//            it.clear()
//        }
//
//        def clazz = Clazz.findOrSaveByName("ARTWORK".toLowerCase())
//        def data = new Data(clazz: clazz, key: "PN0001".toLowerCase(), value: '{test: "test"}', contentLength: 14, contentType: 'application/json')
//        data.save(failOnError: true, flush: true)
    }

    def destroy = {
    }
}
