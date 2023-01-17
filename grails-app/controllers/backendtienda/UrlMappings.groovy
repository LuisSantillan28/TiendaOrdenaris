package backendtienda

class UrlMappings {

    static mappings = {

        group "/tienda-ordenaris/api", {
            group "/sku",
            "/registrar"()
        }

        "/"(controller: 'application', action:'index')
        "500"(view: '/error')
        "404"(view: '/notFound')
    }
}
