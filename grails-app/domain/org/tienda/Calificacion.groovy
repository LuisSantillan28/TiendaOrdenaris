package org.tienda
import java.util.UUID
class Calificacion {
    String uuid = UUID.randomUUID().toString().replaceAll('\\-','')

    int estrella // 5-buena, 4-regularmente bueno,3-regular,2-regularmente malo,1-malo
    String usuario //La persona que lo ha escrito
    int estatus = 1 //1-activo, 2-editado, 3 eliminado
    Date fechaRegistro = new Date()

    static constraints = {
        uuid unique: true
    }

    static mapping = {
        version false
    }
}
