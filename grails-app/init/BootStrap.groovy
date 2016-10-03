import com.hid.api.security.AppUser
import com.hid.api.security.AppUserRole
import com.hid.api.security.Role

class BootStrap {

    def init = { servletContext ->
//        def adminRole = Role.findOrSaveByAuthority('ROLE_ADMIN')
//        def userRole = Role.findOrSaveByAuthority('ROLE_USER')
//        def testUser = AppUser.findOrSaveByUsernameAndPassword('me', 'password')
//        def adminUser = AppUser.findOrSaveByUsernameAndPassword('admin', 'admin')
//
//        AppUserRole.create testUser, userRole
//        AppUserRole.create adminUser, adminRole
//
//        AppUserRole.withSession {
//            it.flush()
//            it.clear()
//        }
    }

    def destroy = {
    }
}
