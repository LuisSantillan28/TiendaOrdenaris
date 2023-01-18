package org.tienda
import java.util.UUID
class Categoria {
    String uuid = UUID.randomUUID().toString().replaceAll('\\-','')

    static hasMany = [productos:ProductoCategoria]

    // Categoria categoriaPadre
    static belongsTo = [categoriaPadre: Categoria] //Llave foranea que se referencía a si misma
    

    String nombre
    int estatus = 1 //1-activa, 2-inactiva, 3-eliminada
    Date registro = new Date()

    static constraints = {
        uuid unique: true
        categoriaPadre nullable: true, blank: true
    }

    static mapping = {
        version false
    }
}
