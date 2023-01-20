package com.tienda
import org.tienda.Producto
import org.tienda.Categoria
import org.tienda.ProductoCategoria

import grails.gorm.transactions.Transactional


@Transactional
class ProductoService {

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
    def carousel(){
        try{
            def lista = []
            Producto.withCriteria{
            ne("estatus", 3)
            maxResults 10
            order("fechaIngreso","desc")
            
        }.each{_producto ->
            def info = info_producto(_producto)
            
            
            lista.add(info)
            println lista
        }

        return[success:true, lista:lista]       
        }catch(error){
            println"${new Date()} | Producto Service | Carousel don't works | Error | ${error.getMessage()}"
            return[success:false, mensaje: error.getMessage()]
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
                distribuidor: producto.distribuidor,
                // categorias: categoria.id
                descuento: producto.descuento
            ])}
            return[success: true, lista:lista]
        }catch(error){
            println "${new Date()} | Producto Service | Lista | Error | ${error.getMessage()}"
            return [ success: false, mensaje: error.getMessage()]
        }
    }

    def gestionar(){

    }

    private info_producto = {_producto->
    // def categorias = []
    // _producto.categorias.each { _categoria ->
        
    //     // println(Categoria.get(_categoria.id).properties)
    //     // println(Categoria.get(_categoria.id).nombre)
    //     // println(Categoria.get(_categoria.id).id)
    //     categorias.add([            
    //         categorias.add(ProductoCategoria.get(_categoria.id))
    //     ])}
    // _producto.categorias.each{_categoria->
    //         categorias.add([
    //             uuid = _categoria.uuid,
    //             nombre = _categoria.nombre
    //         ])}

    // println "-------------------------------------"
    // println _producto.categorias
    // println _producto.precio
    // println _producto.descuento

    def oferta = (_producto.precio *_producto.descuento)/100
    def descuento
    def expira
    if(_producto.descuento){
        descuento = _producto.precio - oferta
        // expira = _producto.expDescuento
    }else{
        descuento = 0
    }

    def listaCategorias = []
    _producto.categorias.each { pc ->
        // println "...Me regresa el id de la relacion entre produto y categoria..."
        // println pc
        // println "...Me regresa los datos de la categoria..."
        // println pc.categorias
        // println "...Me regresa el nombre de la categoria..."
        // println pc.categorias.nombre

        listaCategorias.add([
            uuid: pc.categorias.uuid,
            nombre: pc.categorias.nombre //pc- nueva lista, categorias-la propiedad de la tabla relacional ProductoCategoria, nombre la propiedad específica
        ])
    }

    def productoModelo = []
    // println _producto.modelo.nombre

    return _producto ? [
        uuid: _producto.uuid,
        nombre: "${_producto.nombre} ${_producto.modelo.nombre}",
        categorias: listaCategorias,
        precioOri: _producto.precio,
        precioDesc: descuento,
        descripcion: _producto.descripcion,
        Promocion: _producto.descuento
    ] : []
    }
}
