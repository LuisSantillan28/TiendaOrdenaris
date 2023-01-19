package com.tienda

import grails.gorm.transactions.Transactional
import org.tienda.Marca

@Transactional
class MarcaService {

    def gestionar(data, uuid=null) {
        Marca.withTransaction{tStatus->
        def nMarca
        try{
            nMarca = Marca.findByUuid(uuid)
            if(!nMarca){
                nMarca = new Marca()
            }

            nMarca.nombre = data.nombre
            nMarca.save(flush: true, failOnError: true)
            return [success: true]
        }catch(error){
            println "${new Date()} | Marca Service | Gestionar | Error | ${error.getMessage}"
            tStatus.setRollbackOnly()
            return[ success: false, mensaje: error.getMessage()]
        }
        }
    }
}
