package org.tienda
import java.util.UUID

class Direccion {
    String uuid = UUID.randomUUID().toString().replaceAll('\\-','')

    static hasMany=[distribuidor: Distribuidor]

    String pais
    String estado
    String ciudad
    String municipio
    String cp
    String colonia
    String calle
    String exterior
    String interior

    static constraints = {
        uuid unique: true
        interior nullable: true, blank: true
    }

    static mapping = {
        version false
    }
}
