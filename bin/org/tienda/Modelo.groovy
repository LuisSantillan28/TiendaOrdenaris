package org.tienda
import java.util.UUID
class Modelo {
    String uuid = UUID.randomUUID().toString().replaceAll('\\-','')

    String nombre
    int estatus //1-existe, 2-no existe, 3-descontinuado
    Date fechaIngreso = new Date()
    Date fechaFin

    static constraints = {
        fechaFin nullable: true, blank: true
    }

    static mapping = {
        version false
    }
}
