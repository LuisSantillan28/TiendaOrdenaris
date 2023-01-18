package org.tienda
import java.util.UUID
class ProductoTipo {
    String uuid = UUID.randomUUID().toString().replaceAll('\\-','')
    Producto productos
    Tipo tipos

    static constraints = {
        uuid unique: true
    }

    static mapping = {
        version false
    }
}
