package org.tienda
import java.util.UUID
class Producto {
    String uuid = UUID.randomUUID().toString().replaceAll('\\-','')

    Modelo modelo
    Marca marca
    Color color
    String sku
    Distribuidor distribuidor

    String noSerie
    String nombre

    BigDecimal peso
    int condicion = 1//nuevo, uso, defectuoso
    String talla //la determina la empresa de acuerdo al producto
    BigDecimal precio
    Date garantia
    String descripcion //Características generales del producto
    int estatus = 1//1-en stock, 2-proximamente, 3-eliminado
    Date fechaIngreso = new Date()
    Date fechaSalida

    static constraints = {
        uuid unique: true
        talla nullable: true, blank: true
        garantia nullable: true, blank: true
        fechaSalida nullable: true, blank: true
        observacion nullable: true, blank: true

    }

    static mapping = {
        version false
    }
}
