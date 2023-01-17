package org.tienda
import java.util.UUID

class CategoriaModelo {
    String uuid = UUID.randomUUID().toString().replaceAll('\\-','')
    
    Modelo modelos
    Categoria categorias
    static constraints = {
        uuid unique:true
        modelos unique: true
        categorias unique: true
    }

    static mapping ={
        version false
    }
}
