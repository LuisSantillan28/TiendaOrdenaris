package org.tienda
import java.util.UUID
class Modelo {
    String uuid = UUID.randomUUID().toString().replaceAll('\\-','')
    
    static hasMany=[
                    productos:Producto,
                    categorias:CategoriaModelo,
                    colores: ColorModelo,
                    tipo: TipoModelo
                   ]

    Marca marca
    Comentarios comentario
    Calificacion calificacion

    String nombre
    int estatus = 1 //1-en stock, 2-proximamente, 3-eliminado
    Date fechaIngreso = new Date()
    Date fechaFin

    static constraints = {
        uuid unique: true
        fechaFin nullable: true, blank: true
        descripcion nullable: true, blank: true
        comentario nullable: true, blank: true
        calificacion nullable: true, blank: true
    }

    static mapping = {
        version false
    }
}
