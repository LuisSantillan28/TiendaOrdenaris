package backendtienda
import org.tienda.*

class BootStrap {

    def init = { servletContext ->
        // def role1 = new Role(authority:"ROLE_CAPTURISTA").save(flush: true)
        // def user1 = new User(username: "user2", password: "pwr2", email: "alguien@ordenaris.com",  nombre: "someone", apaterno: "anylastname", amaterno: "anyone").save(flush:true)
        // UserRole.create(user2, role2)
    }
    def destroy = {
    }
}
