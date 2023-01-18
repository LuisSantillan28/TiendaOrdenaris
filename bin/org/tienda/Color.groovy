package org.tienda
import java.util.UUID
class Color {
    String uuid = UUID.randomUUID().toString().replaceAll('\\-','')

    static hasMany =[
                    productos:ColorProducto
                    ]

    String nombre
    String hxdecimal

    static constraints = {
        uuid unique: true
    }

    static mapping ={
        version false
    }
}
