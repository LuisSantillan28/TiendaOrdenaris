package org.tienda
import java.util.UUID
class ColorProducto {
    String uuid = UUID.randomUUID().toString().replaceAll('\\-','')

    Producto productos
    Color colores

    static constraints = {
        uuid unique: true
    }
}
