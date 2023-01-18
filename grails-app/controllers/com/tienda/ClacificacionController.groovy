package com.tienda


import grails.rest.*
import grails.converters.*

class ClacificacionController {
	static responseFormats = ['json', 'xml']
    def ClacificacionService, FuncionesService

    def listaColor(){
        render(ClacificacionService.listaColor() as JSON)
    }

    def gestionarColor(){
        def data=request.JSON
        if(!data.nombre){
            render([success: false, mensaje:FuncionesService.getMensajeObligatorio("nombre")] as JSON)
            return
        }
        if(data.nombre.size() < 3){
            render([success: false, mensaje: FuncionesService.getMensajeInvalido("nombre")] as JSON)
            return
        }
        if(!data.hxdecimal){
            render([success: false, mensaje:FuncionesService.getMensajeObligatorio("color")] as JSON)
            return
        }
        if(data.hxdecimal.size() < 7){
            render([success: false, mensaje: FuncionesService.getMensajeInvalido("color")] as JSON)
            return
        }
        render(ClacificacionService.gestionarColor(data, params.uuid) as JSON)
    }

    def listaTipo(){
        render(ClacificacionService.listaTipo() as JSON)
    }

    def gestionarTipo(){
        def data = request.JSON
        if(!data.nombre){
            render([success: false, mensaje: FuncionesService.getMensajeObligatorio("nombre")])
            return
        }
        if(data.nombre < 3){
            render([success: false, mensaje: FuncionesService.getMensajeInvalido("nombre")])
            return
        }
    }
}
