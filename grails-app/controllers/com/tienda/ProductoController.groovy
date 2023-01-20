package com.tienda


import grails.rest.*
import grails.converters.*

class ProductoController {
	static responseFormats = ['json', 'xml']

    def ProductoService, FuncionesService

    def paginar(){
        if(!params.sort){
            render([success: false, mensaje:FuncionesService.getMensajeObligatorio("sort")] as JSON)
            return
        }
        if(params.sort != "nombre" && params.sort != "estatus"){
                render( [success:false, mensaje:FuncionesService.getMensajeInvalido("sort")] as JSON)
                return
        }
        if(!params.order){
            render([success:false, mensaje:FuncionesService.getMensajeObligatorio("order")] as JSON)
            return
        }
        if(params.order != "asc" && params.order != "desc"){
            render([success:false, mensaje:FuncionesService.getMensajeInvalido("order")] as JSON)
            return
        }
        if(!params.page){
            render([success:false, mensaje:FuncionesService.getMensajeObligatorio("page")] as JSON)
            return
        }
        try{
            if(params.page.toInteger()< 1){
                render([success: false, mensaje: FuncionesService.getMensajeInvalido("page")] as JSON)
                return
            }
        }catch(error){
            render([success: false, mensaje: FuncionesService.getMensajeInvalido("page")] as JSON)
            return
        }

        if(params.max){
            try{
                if(params.max.toInteger() < 1){
                    render([success: false, mensaje: FuncionesService.getMensajeInvalido("max")] as JSON)
                    return
                }
            }catch(error){
                render([success: false, mensaje: FuncionesService.getMensajeInvalido("max")] as JSON)
                return
            }
        }
        render(ProductoService.paginar(params) as JSON)
    }

    def lista(){
        render(ProductoService.lista() as JSON)
    }
    def carousel(){
        render(ProductoService.carousel() as JSON)
    }

    // def gestionar(data){
    //     def data.request.JSON
    //     if(!data.nombre){

    //     }
    //     render(ProductoService.gestionar(data,params.uuid) as JSON)
    // }
}
