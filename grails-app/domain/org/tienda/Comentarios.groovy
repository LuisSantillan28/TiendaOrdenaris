package org.tienda
import java.util.UUID

class Comentarios {

    static hasMany=[modelos:Modelo]

    String uuid = UUID.randomUUID().toString().replaceAll('\\-','')
    String comentario
    Date fechaReg = new Date()
    String usuario = 'unknow'
    

    static constraints = {
    uuid unique: true
    }

    static mapping = {
        version false
    }
}
