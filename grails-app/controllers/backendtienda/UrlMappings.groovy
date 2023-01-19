package backendtienda

class UrlMappings {

    static mappings = {

        group "/tienda/ordenaris/api", {
            group "/producto", {
                "/paginar"(controller: "producto", action: "paginar", method: "GET")
                "/listar"(controller: "producto", action: "lista", method: "GET")
                "/registrar"(controller: "producto", action: "gestionar", method: "POST")
            }
            group "/colores",{
                "/registrar"(controller: "clacificacion", action:"gestionarColor", method: "POST")
                "/listar"(controller: "clacificacion", action: "listaColor", method:"GET")
            }
            group "/tipo",{
                "/registrar"(controller: "clacificacion", action:"gestionarTipo", method: "POST")
                "/listar"(controller: "clacificacion", action: "listaTipo", method:"GET")
            }
            group "/categoria",{
                "/registrar"(controller: "clacificacion", action:"gestionarCategoria", method: "POST")
                "/listar"(controller: "clacificacion", action: "listaCategoria", method:"GET")
            }
            group "/distribuidor",{
                "/registrar"(controller: "distribuidor", action:"gestionar", method:"POST")
                "/listar"(controller: "distribuidor", action:"lista", method:"GET")
            }
            group "/marca",{
                "/registrar"(controller: "marca", action:"gestionar", method:"POST")
                "/listar"(controller: "marca", action:"lista", method:"GET")    
            }
        }

        "/"(controller: 'application', action:'index')
        "500"(view: '/error')
        "404"(view: '/notFound from mapping')
    }
}
