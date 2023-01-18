package org.tienda
import java.util.UUID
class Tipo {
    String uuid = UUID.randomUUID().toString().replaceAll('\\-','')

    static hasMany=[productos:ProductoTipo]

    String nombre //la línea a la que pertenece el producto
    String descripcion //Control interno
    int estatus = 1 //1-activo, 2-inactivo, 3-inactivo

    static constraints = {
        uuid unique: true
    }

    static mapping = {
        version false
    }
}
