package org.tienda
import java.util.UUID

class ProductoCategoria {
    String uuid = UUID.randomUUID().toString().replaceAll('\\-','')
    Categoria categorias
    Producto productos

    static constraints = {
        uuid unique: true
    }
    
    static mapping = {
        version false
    }
}
