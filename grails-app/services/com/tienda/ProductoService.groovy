package com.tienda
import org.tienda.Producto
import org.tienda.Categoria
import org.tienda.Distribuidor
import org.tienda.Modelo
import org.tienda.Marca
import org.tienda.Color
import org.tienda.Categoria
import org.tienda.Tipo
import org.tienda.ProductoCategoria
import org.tienda.ColorProducto
import org.tienda.ProductoTipo

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
    def listaFiltrada(){
        try{
            def lista = []
            Producto.withCriteria{
            ne("estatus", 3)
            maxResults 10
            order("fechaIngreso","desc")
            
        }.each{_producto ->
            def info = info_producto(_producto)
            
            
            lista.add(info)
            // println lista
        }

        return[success:true, lista:lista]       
        }catch(error){
            println"${new Date()} | Producto Service | ListaFiltrada don't works | Error | ${error.getMessage()}"
            return[success:false, mensaje: error.getMessage()]
        }
    }

    def listaFiltradaDistribuidor(data){
        try{
            def nDistribuidor
            nDistribuidor = Distribuidor.findByUuid(data.uuid)
            if(!nDistribuidor){
                "Distribuidor inexistente"
            }

            def lista=[]
            def _max = data.max ? data.max.toInteger() : 10
            Producto.withCriteria{
                ne("estatus", 3)
                eq("distribuidor", nDistribuidor)
                maxResults _max
                order("fechaIngreso","desc")
            }.each{_producto->
            def info= info_producto(_producto)
            lista.add(info)}
        return[success: true, lista:lista]
        }catch(error){
        println"${new Date()} | Producto Service | Lista Filtrada por Distribuidor doesn't works!| ERROR | ${error.getMessage()}"
        return[success: false, mensaje: error.getMessage()]}
    }

    def lista(){
        try{
            def lista=[]
            Producto.findAllByEstatus(1).each {producto ->
            // println "----"
            // println producto
            // println producto.distribuidor
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

    def gestionar(data, uuid = null){
        Producto.withTransaction{tStatus->
            def nProducto
            def nModelo

            try{
                nProducto = Producto.findAllByUuid(uuid)
                nModelo = Modelo.findAll()

                if(!nProducto){
                    nProducto = new Producto()
                }

                def colores = Color.findAllByUuidInList(data.colores)
                if(!colores) return [success: false, mensaje: "Color no encontrado"]

                def categorias = Categoria.findAllByUuidInList(data.categorias)
                if(!categorias) return[success: false, mensaje: "Categoría no encontrado"]

                def tipos = Tipo.findAllByUuidInList(data.tipos)
                if(!tipos) return[success: false, mensaje: "Tipo no encontrado"]
                nModelo = new Modelo()
                //Registro del modelo
                nModelo.nombre = data.nombreModelo
                nModelo.save(flush: true, failOnError: true)
                //Registro del producto
                nProducto.sku = data.sku
                nProducto.noSerie = data.noSerie
                nProducto.nombre = data.nombreProducto
                nProducto.peso = data.peso
                nProducto.talla = data.talla
                nProducto.precio = data.precio
                nProducto.garantia = data.garantia
                nProducto.descuento = data.descuento
                // println data.expDescuento
                data.expDescuento? nProducto.expDescuento = data.expDescuento : ''
                nProducto.descripcion = data.descripcion
                nProducto.stock = data.stock
                nProducto.image = data.image

                nProducto.modelo = Modelo.findByUuid(nModelo.uuid)
                nProducto.marca = Marca.findByUuid(data.marca)
                nProducto.distribuidor = Distribuidor.findByUuid(data.distribuidor)

                // def listaCategorias = []
                // nProducto.categorias.each{_categoria->
                //     listaCategorias.add(Categoria.get(_categoria.id))

                // }
                // nProducto.categorias = []

                // def nProdCate = new ProductoCategoria()


                // categorias.each{obj->
                // println "-----------"
                // println categorias
                // println obj
                // println "-----------"
                //     nProdCate.categorias.addToCategorias(obj)
                //     nProdCate.save(flush: true, failOnError: true)
                // }
                
                // def listaColores = []
                // nProducto.colores.each{_color->
                //     listaColores.add(Color.get(_color.id))
                // }

                // nProducto.colores = []
                // nProducto.colores.each{obj->
                //     nProducto.addToColores(obj)
                // }

                // def listaTipos = []
                // nProducto.tipo.each{_tipo->
                //     listaTipos.add(Tipo.get(_tipo.id))
                // }
                
                // nProducto.tipo = []

                // nProducto.tipo.each{obj->
                //     nProducto.addToTipo(obj)
                // }
                
                nProducto.save(flush: true, failOnError: true)

                data.categorias.each{_categoria->
                    def categoriaObj = Categoria.findByUuid(_categoria)
                    if( !categoriaObj ) return 
                    new ProductoCategoria([
                        categorias: Categoria.findByUuid(_categoria),
                        productos: nProducto
                    ]).save(flush:true, failOnError:true)
                }

                data.colores.each{_color->
                    def colorObj = Color.findByUuid(_color)
                    if(!colorObj) return
                    new ColorProducto([
                        colores: colorObj,
                        productos: nProducto
                    ]).save(flush: true, failOnError: true)
                }

                data.tipos.each{_tipo->
                def tipoObj = Tipo.findByUuid(_tipo)
                if(!tipoObj) return
                new ProductoTipo([
                    tipos: tipoObj,
                    productos: nProducto
                ]).save(flush: true, failOnError: true)
                }


                return[success: true]
            }catch(error){
                println "${new Date()} | Producto Service | Gestionar doesn't works! | ERROR | ${error.getMessage()}"
                tStatus.setRollbackOnly()

                error.stackTrace.each { newErr ->
                    if(newErr.className.startsWith("com.tienda.ProductoService")){
                        println newErr
                    }
                }
                return [success: false, mensaje: error.getMessage()]
            }
        }
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

    // println _producto.id

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
    Random cantidadAleatoria = new Random()

    // def productoModelo = []
    // println _producto.modelo.nombre

    return _producto ? [
        uuid: _producto.uuid,
        nombre: "${_producto.nombre}, ${_producto.modelo.nombre}",
        categorias: listaCategorias,
        precioOriginal: _producto.precio,
        precioDescuento: descuento,
        descripcion: _producto.descripcion,
        porcentajeDescuento: _producto.descuento,
        fechaLimPromocion: _producto.expDescuento,
        cantidad: cantidadAleatoria.nextInt(100)
    ] : [:]
    }
    }
