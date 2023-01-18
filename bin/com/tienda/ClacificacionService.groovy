package com.tienda

import grails.gorm.transactions.Transactional
import org.tienda.Color
import org.tienda.Tipo

@Transactional
class ClacificacionService {
    def listaColor(){
        try{
            def lista=[]
            Color.findAll().each{ color->
            lista.add([
                uuid: color.uuid,
                nombre: color.nombre,
                hxdecimal: color.hxdecimal
            ])}
            return [success: true, lista:lista]
        }catch(error){
            println "${new Date()} | Clacificacion Service | Lista Color | Error | ${error.getMessage()}"
            return [ success: false, mensaje: error.getMessage()]
        }
    }
    
    def gestionarColor(data, uuid= null){
        Color.withTransaction {tStatus->
            def nColor
            try{
                nColor = Color.findByUuid( uuid )

                if(!nColor){
                  nColor =  new Color()
                }

                nColor.nombre = data.nombre
                nColor.hxdecimal = data.hxdecimal
                nColor.save(flush: true, failOnError: true)
                return[success: true]
            }catch(error){
                println "${new Date()} | Clacificacion Service | Gestonar Color | Error | ${error.getMessage()}"
                tStatus.setRollbackOnly()
                return[ success: false, mensaje: error.getMessage()]
            }
        }
    }

    def listaTipo(){}
    def gestionarTipo(){}
}
