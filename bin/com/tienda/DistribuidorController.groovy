package com.tienda


import grails.rest.*
import grails.converters.*

class DistribuidorController {
	static responseFormats = ['json', 'xml']
    def DistribuidorService, FuncionesService
	
    def gestionar() {
        def data = request.JSON
        render(DistribuidorService.gestionar(data) as JSON)
    }

    def listar(){
        render(DistribuidorService.listar() as JSON)
    }
    def informacion(){
        render(DistribuidorService.informacion(params.uuid) as JSON)
    }

}
