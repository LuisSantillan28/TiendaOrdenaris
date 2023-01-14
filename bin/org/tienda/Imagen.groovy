package org.tienda
import java.util.UUID

class Imagen {
    String uuid = UUID.randomUUID().toString().replaceAll('\\-','')
    String nombre
    String ruta
    int vista //1-delantera, 2-trasera, 3-lat-izq, 4-lat-der, 5-modelo
    Date fechaReg = new Date()

    static constraints = {
        uuid unique: true
    }

    static mapping = {
        version false
    }
}
