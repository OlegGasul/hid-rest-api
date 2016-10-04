import com.hid.api.model.Clazz
import com.hid.api.model.Data
import com.hid.api.security.AppUser
import com.hid.api.security.AppUserRole
import com.hid.api.security.Role

class BootStrap {

    def init = { servletContext ->
        def userRole = Role.findOrSaveByAuthority('ROLE_USER')
        def testUser = AppUser.findOrSaveByUsernameAndPassword('root', 'se3ret123')

        AppUserRole.create testUser, userRole

        AppUserRole.withSession {
            it.flush()
            it.clear()
        }
    }

    def destroy = {
    }
}
