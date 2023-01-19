package com.tienda
import org.tienda.Producto
import org.tienda.Categoria

import grails.gorm.transactions.Transactional


@Transactional
class ProductoService {
def ClacificacionService

    def paginar(data){
       try{
        def _max = data.max ? data.max.toInteger() : 10
        def offset = ( --data.page.toInteger() * _max.toInteger())

        def lista = []
        def total = Producto.countByEstatusNotEqual(3)

        Producto.withCriteria{
            ne("estatus", 3)
            firstResult(offset)
            maxResults _max
            order(data.sort, data.order)
        }.each { _producto ->
            def info = info_producto(_producto)
            lista.add(info)
        }

        return[success: true, lista: lista, total:total]
        }catch(error){
            return[success: false, mensaje: error.getMessage()]
        }
    }

    def lista(){
        try{
            def lista=[]
            Producto.findAllByEstatus(1).each {producto ->
            lista.add([
                uuid: producto.uuid, //
                nombre: producto.nombre, //
                marca: producto.marca.nombre, //
                modelo: producto.modelo.nombre, //
                sku: producto.sku, //
                peso: producto.peso, //
                condicion: producto.condicion, //
                talla: producto.talla, //
                precio: producto.precio, //
                // garantia: producto.garantia, //
                descripcion: producto.descripcion, //
                estatus: producto.estatus, //7
                // fechaIngreso: producto.fechaIngreso, //
                stock: producto.stock, //
                distribuidor: producto.distribuidor
            ])}
            return[success: true, lista:lista]
        }catch(error){
            println "${new Date()} | Area Service | Lista | Error | ${error.getMessage()}"
            return [ success: false, mensaje: error.getMessage()]
        }
    }

    def gestionar(){}

    private info_producto = {_producto->
    def categorias = []
    _producto.categorias.each { _categoria ->
        
        // println(Categoria.get(_categoria.id).properties)
        // println(Categoria.get(_categoria.id).nombre)
        // println(Categoria.get(_categoria.id).id)
        // println(Categoria.get(_categoria.id).producto)

        categorias.add([            
            categorias.add(Categoria.get(_categoria.id))
        ])
    }

    return _producto ? [
        uuid: _producto.uuid,
        nombre: "${_producto.nombre} ${_producto.modelo}",
        categorias: categorias,
        precio: _producto.precio,
        descripcion: _producto.descripcion
    ] : []
    }
}
