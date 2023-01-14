package org.tienda
import java.util.UUID
class Color {
    String uuid = UUID.randomUUID().toString().replaceAll('\\-','')
    String nombre
    //String img //Probablemente esta no sea necesaria ya que pueden usarse las paletas de colores del sistema y podamos hacer la converción por los códigos de colores rgb
    int estatus = 1 //1-existe, 2-no existe, 3-descontinuado

    static constraints = {
        uuid unique: true
    }

    static mapping ={
        version false
    }
}
