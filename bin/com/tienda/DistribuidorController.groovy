package com.tienda


import grails.rest.*
import grails.converters.*

class DistribuidorController {
	static responseFormats = ['json', 'xml']
    def DistribuidorService, FuncionesService
	
    // def gestionar() {
    //     def data = request.JSON
        

    // }

    def listar(){
        println "):V Distribuidor servicio si pasa"
        render(DistribuidorService.listar() as JSON)
    }

}
