package org.tienda
import java.util.UUID

class Marca {
    String uuid = UUID.randomUUID().toString().replaceAll('\\-','')

    static hasMany = [
                        productos:Producto
                    ]
                    
    String nombre
    int estatus //1-activa, 2-inactiva, 3-baja
    Date fechaIngreso = new Date()

    static constraints = {
        uuid unique: true
    }

    static mapping = {
        version false
    }
}
