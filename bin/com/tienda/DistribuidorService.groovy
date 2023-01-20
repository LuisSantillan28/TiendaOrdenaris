package com.tienda

import grails.gorm.transactions.Transactional
import org.tienda.Distribuidor

@Transactional
class DistribuidorService {
    def FuncionesService, ProductoService

    def listar() {
        try{
            def lista=[]
            Distribuidor.findAll().each{distribuidor->
            lista.add([
                uuid: distribuidor.uuid,
                direccion: distribuidor.direccion.uuid,
                nombre: distribuidor.nombre,
                telefono: distribuidor.telefono,
                correo: distribuidor.correo,
                logotipo: distribuidor.logotipo,
                url: distribuidor.url
            ])
            }
            return[success: true, lista:lista]
        }catch(error){
            println "${new Date()} | Distribuidor Service | Listar doesn't works | Error | ${error.getMessage()} "
            return[success: false, mensaje: error.getMessage()]
        }
    }

    def informacion(uuid){
        def iDistribuidor

        try{
            iDistribuidor.findAllByUuid()

            if(!iDistribuidor){
                return[success: false, mensaje: "Compañia no encontrada"]
            }

            def listaProducto = []
            iDistribuidor.productos.each{producto->
                listaProducto.add(DistribuidorService.info_producto(producto))
            }

            def informacion =[
                uuid: iDistribuidor.uuid,
                nombre: iDistribuidor.nombre,
                telefono: iDistribuidor.telefono,
                email: iDistribuidor.correo,
                productos: Producto
            ]
        }catch(error){

        }
    }
}
