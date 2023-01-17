package org.tienda
import java.util.UUID
class TipoModelo {
    String uuid = UUID.randomUUID().toString().replaceAll('\\-','')
    Tipo tipos
    Modelo modelo

    static constraints = {
        uuid unique: true
        tipo unique: true
        modelo unique: true
    }

    static mapping = {
        version false
    }
}
