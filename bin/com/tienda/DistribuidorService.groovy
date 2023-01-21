package com.tienda

import grails.gorm.transactions.Transactional
import org.tienda.Distribuidor
import org.tienda.Direccion

@Transactional
class DistribuidorService {
    def FuncionesService, ProductoService

    def listar() {
        try{
            def lista=[]
            Distribuidor.findAll().each{distribuidor->
            lista.add([
                uuid: distribuidor.uuid,
                nombre: distribuidor.nombre
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
            iDistribuidor = Distribuidor.findByUuid(uuid)
            // println iDistribuidor.properties

            if(!iDistribuidor){
                return[success: false, mensaje: "Compañia no encontrada"]
            }

            // println "--------------------------------"
            // println iDistribuidor.productos
            // println "--------------------------------"
            def listaProducto = []

            iDistribuidor.productos.each{_producto->
                // println _producto    
                def srvP = ProductoService.info_producto(_producto)
                listaProducto.add(srvP)
            }

            def informacionP =[
                uuid: iDistribuidor.uuid,
                nombre: iDistribuidor.nombre,
                productos: listaProducto
            ]
            return [success: true, informacionP:informacionP]
        }catch(error){
            println "${new Date()} | Distribuidor Service | Informacion doesn't works | ERROR | ${error.getMessage()}"
            return[success: false, mensaje: error.getMessage()]
        }
    }

    def gestionar(data){
        // println "Existo ${data}"
        Distribuidor.withTransaction{tStatus->
            try{
            def nDistribuidor = Distribuidor.findByUuid(data.uuid)
            def nDireccion = Direccion.findAll()
            if(!nDistribuidor){
              nDistribuidor =  new Distribuidor()
            }

            //Inserción de datos a tabla Direccion
            nDireccion = new Direccion()
            nDireccion.pais = data.pais
            nDireccion.estado = data.estado
            nDireccion.ciudad = data.ciudad
            nDireccion.municipio = data.municipio
            nDireccion.cp = data.cp
            nDireccion.colonia = data.colonia
            nDireccion.calle = data.calle
            nDireccion.exterior = data.exterior
            nDireccion.interior = data.interior
            nDireccion.save(flush: true, failOnError: true)

            //Inserción de datos a la tabla Distribuidor
            nDistribuidor.nombre = data.nombre
            nDistribuidor.telefono = data.telefono
            nDistribuidor.correo = data.correo
            nDistribuidor.logotipo = data.logotipo
            nDistribuidor.url = data.url
            nDistribuidor.direccion = Direccion.findByUuid(nDireccion.uuid)
            nDistribuidor.save(flush: true, failOnError: true)

            return[success: true]
        }catch(error){
            println "${new Date()} | Distribuidor Service | Gestionar doesn't works | Error | ${error.getMessage()}"
            tStatus.setRollbackOnly()
            return [success: false, mensaje: error.getMessage()]
        }}
    }
}
