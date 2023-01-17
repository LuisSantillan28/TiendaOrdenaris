package org.tienda
import java.util.UUID
class Tipo {
    String uuid = UUID.randomUUID().toString().replaceAll('\\-','')

    static hasMany=[modelos:TipoModelo]

    String nombre //la línea a la que pertenece el producto
    String descripcion
    int estatus = 1 //1-activo, 2-inactivo, 3-inactivo

    static constraints = {
    }

    static mapping = {
        version false
    }
}
