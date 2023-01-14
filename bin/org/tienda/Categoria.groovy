package org.tienda
import java.util.UUID
class Categoria {

    static 

    String uuid = UUID.randomUUID().toString().replaceAll('\\-','')
    String nombre
    int estatus = 1 //1-activa, 2-inactiva, 3-eliminada
    //String subcategoriaDe //Llave foranea que se referencía a si misma
    Date registro = new Date()

    static constraints = {
        subcategoria nullable: true, blank: true
    }

    static mapping = {
        version false
    }
}
