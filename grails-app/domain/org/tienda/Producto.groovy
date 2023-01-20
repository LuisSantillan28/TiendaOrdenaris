package org.tienda
import java.util.UUID

class Producto {
    String uuid = UUID.randomUUID().toString().replaceAll('\\-','')
    static hasMany= [colores: ColorProducto, categorias: ProductoCategoria, tipo: ProductoTipo]
    // static belongsTo = [Categoria, Tipo, Color]

    Modelo modelo
    Marca marca
    Distribuidor distribuidor
    Calificacion calificacion

    String sku
    String noSerie
    String nombre

    BigDecimal peso
    int condicion = 1//nuevo, uso, defectuoso
    String talla //la determina la empresa de acuerdo al producto
    BigDecimal precio
    int garantia
    int descuento
    int expDescuento
    String descripcion //Características generales del producto
    int estatus = 1//1-en stock, 2-proximamente, 3-eliminado
    Date fechaIngreso = new Date()
    int stock = 0

    static constraints = {
        uuid unique: true
        talla nullable: true, blank: true
        garantia nullable: true, blank: true
        colores nullable: true, blank: true
        stock nullable: true, blank: true
        calificacion nullable: true, blank: true
        descuento nullable: true, blank: true
        expDescuento nullable: true, blank: true

    }

    static mapping = {
        version false
    }
}
