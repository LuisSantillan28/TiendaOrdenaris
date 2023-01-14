package org.tienda
import java.util.UUID
class Producto {

    static hasMany =[categorias: Categoria]

    String uuid = UUID.randomUUID().toString().replaceAll('\\-','')
    String sku
    String nombre
    //String marca
    // String modelo
    String peso
    // String color
    int condicion //nuevo, uso
    Date garantia
    String tipo
    // String categoria
    Float precio
    //int calificacion //5-buena, 4-regularmente bueno,3-regular,2-regularmente malo,1-malo
    int talla //1-chica, 2-mediana, 3-grande, 4-extragrande
    String descripcion
    String imagen
    int estatus //1-en stock, 2-vendido, 3-eliminado
    String comentarios
    String distribuidor
    Date fechaIngreso = new Date()

    static constraints = {
        uuid unique: true
        talla nullable: true, blank: true
        garantia nullable: true, blank: true

    }

    static mapping = {
        version false
    }
}
