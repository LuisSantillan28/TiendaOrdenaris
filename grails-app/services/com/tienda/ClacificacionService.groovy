package com.tienda

import grails.gorm.transactions.Transactional
import org.tienda.Color
import org.tienda.Tipo
import org.tienda.Categoria

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
    def gestionarTipo( data, uuid = null){
        Tipo.withTransaction{tStatus->
        def nTipo
        try{
            nTipo //= nTipo.findByUuid( uuid ) //Resolver el porque me lo está haciendo nulo

            if(!nTipo){
                nTipo = new Tipo()
            }

            nTipo.nombre = data.nombre
            nTipo.descripcion = data.descripcion
            nTipo.save(flush: true, failOnError: true)
            return[success: true]
        }catch(error){
            println "${new Date()} | Clacificacion Service | Gestionar Tipo | Error | ${error.getMessage()}"
            tStatus.setRollbackOnly()
            return[ success: false, mensaje: error.getMessage()]
        }
        }
    }

    def info_categoria(){
        
    }

    def gestionarCategoria(data, uuid = null){
        Categoria.withTransaction{tStatus->
        def nCategoria
       try{ 
        // nCategoria = nCategoria.findByUuid(uuid)
        if(!nCategoria){
            nCategoria = new Categoria()
        }        

        nCategoria.nombre = data.nombre
        nCategoria.categoriaPadre = data.categoriaPadre
        nCategoria.save(flush: true, failOnError: true)
        return[success: true]}catch(error){
            println "${new Date()} | Clacificacion Service | Gestionar Categoria | Error | ${error.getMessage()}"
            return[success: false, mensaje: error.getMessage]
        }}
    }
}
