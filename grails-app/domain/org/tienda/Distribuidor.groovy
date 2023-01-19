package org.tienda
import java.util.UUID

class Distribuidor {
    String uuid = UUID.randomUUID().toString().replaceAll('\\-','')
    
    static hasMany=[productos:Producto]

    Direccion direccion

    String nombre
    String telefono
    String correo
    String logotipo
    String url

    static constraints = {
        uuid unique: true
        direccion unique: true
    }

    static mapping = {
        version: false
    }
}
