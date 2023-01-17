package org.tienda
import java.util.UUID

class ColorModelo {
    String uuid = UUID.randomUUID().toString().replaceAll('\\-','')

    Modelo modelos
    Color colores

    static constraints = {
        uuid unique: true
        modelos unique: true
        colores unique: true
    }
}
