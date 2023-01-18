package com.tienda

import grails.gorm.transactions.Transactional

@Transactional
class FuncionesService {

    def mensajeObligatorio = "Falta '#' para continuar la operacion"
    def mensajeInvalido = "El '#' es inválido, no es posible continuar con la operacion"

    def getMensajeObligatorio(param){
        return mensajeObligatorio.replace("#", param)
    }

    def getMensajeInvalido(param){
        return mensajeInvalido.replace("#", param)
    }
}
