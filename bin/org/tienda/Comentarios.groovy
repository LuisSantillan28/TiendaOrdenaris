package org.tienda
import java.util.UUID

class Comentarios {
    String uuid = UUID.randomUUID().toString().replaceAll('\\-','')
    String comentario
    Date fechaReg = new Date()
    //usuario FK
    

    static constraints = {
    uuid unique: true
    }

    static mapping = {
        version false
    }
}
