package com.tienda


import grails.rest.*
import grails.converters.*

class MarcaController {
	static responseFormats = ['json', 'xml']
    def MarcaService, FuncionesService
	
    def gestionar(){
        def data = request.JSON
        if(!data.nombre){
            render([success: false, mensaje:FuncionesService.getMensajeObligatorio("nombre")] as JSON)
            return
        }
        if(data.nombre.size() < 3){
            render([success: false, mensaje:FuncionesService.getMensajeInvalido("nombre")] as JSON)
            return
        }
        render(MarcaService.gestionar(data, params.uuid) as JSON )
    }
}
