package com.tienda
import org.tienda.Producto

import grails.gorm.transactions.Transactional

@Transactional
class ProductoService {

    def paginar(data){
       try{
        def _max = data.max ? data.max.toInteger() : 5
        def offset = ( --data.page.toInteger() * _max.toInteger())

        def lista = []
        // def total = Producto.countByEstatusNotEqual(3)

        Producto.withCriteria{
            ne("estatus", 3)
            firstResult(offset)
            maxResult _max
            order(data.sort, data.order)
        }.each {producto ->
            lista.add([
                uuid: producto.uuid,
                marca: producto.marca,
                modelo: producto.modelo,
                sku: producto.sku,
                nombre: producto.nombre,
                peso: producto.peso,
                condicion: producto.condicion,
                talla: producto.talla,
                precio: producto.precio,
                garantia: producto.garantia,
                descripcion: producto.descripcion,
                estatus: producto.estatus,
                fechaIngreso: producto.fechaIngreso,
                stock: producto.stock
            ])
        }

        return[success: true, lista: lista, total: total]
        }catch(error){
            println ">:V"
            return[success: false, mensaje: error.getMessage()]
        }
    }

    def lista(){
        try{
            def lista=[]
            Producto.findAllByEstatus(1).each {producto ->
            lista.add([
                uuid: producto.uuid, //
                marca: producto.marca, //
                modelo: producto.modelo, //
                sku: producto.sku, //
                nombre: producto.nombre, //
                peso: producto.peso, //
                condicion: producto.condicion, //
                talla: producto.talla, //
                precio: producto.precio, //
                garantia: producto.garantia, //
                descripcion: producto.descripcion, //
                estatus: producto.estatus, //7
                fechaIngreso: producto.fechaIngreso, //
                stock: producto.stock //
            ])}
            return[success: true, lista:lista]
        }catch(error){
            println "${new Date()} | Area Service | Lista | Error | ${error.getMessage()}"
            return [ success: false, mensaje: error.getMessage()]
        }
    }

    def gestionar(){}
}
