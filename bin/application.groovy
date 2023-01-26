

// Added by the Spring Security Core plugin:
grails.plugin.springsecurity.userLookup.userDomainClassName = 'org.tienda.User'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'org.tienda.UserRole'
grails.plugin.springsecurity.authority.className = 'org.tienda.Role'
grails.plugin.springsecurity.controllerAnnotations.staticRules = [
	[pattern: '/',               access: ['permitAll']],
	[pattern: '/error',          access: ['permitAll']],
	[pattern: '/index',          access: ['permitAll']],
	[pattern: '/index.gsp',      access: ['permitAll']],
	[pattern: '/shutdown',       access: ['permitAll']],
	[pattern: '/assets/**',      access: ['permitAll']],
	[pattern: '/**/js/**',       access: ['permitAll']],
	[pattern: '/**/css/**',      access: ['permitAll']],
	[pattern: '/**/images/**',   access: ['permitAll']],
	[pattern: '/**/favicon.ico', access: ['permitAll']],
	// [pattern: '/producto', access: ['ROLE_CAPTURISTA']],
	[pattern: '/distribuidor/listar', access: ['permitAll']],
	[pattern: '/producto/**', access: ['isFullyAuthenticated()']],
	[pattern: '/clacificacion/listaColor', access: ['permitAll']],

	[pattern: '/producto/gestionar', access: ['ROLE_ADMINISTRADOR']],
	[pattern: '/distribuidor/gestionar', access: ['ROLE_ADMINISTRADOR']],
	[pattern: '/clacificacion/gestionarColor', access: ['ROLE_ADMINISTRADOR']],
	[pattern: '/clacificacion/gestionarTipo', access: ['ROLE_ADMINISTRADOR']],
	[pattern: '/clacificacion/gestionarCategoria', access: ['ROLE_ADMINISTRADOR']],
	[pattern: '/marca/gestionar', access: ['ROLE_ADMINISTRADOR']],
	[pattern: '/distribuidor/gestionar', access: ['ROLE_ADMINISTRADOR']],
	[pattern: '/distribuidor/informacion', access: ['ROLE_ADMINISTRADOR']],
	// [pattern: '/tienda/ordenaris/api/logout', access: ['isFullyAuthenticated()']],
	// [pattern: '/tienda/ordenaris/api/producto', access: ['isFullyAuthenticated()']],
	// [pattern: '/tienda/ordenaris/api/distribuidor/listar', access: ['permitAll']],
	// [pattern: '/**', access:['isFullyAuthenticated()']]
]
grails.plugin.springsecurity.filterChain.chainMap = [
	[pattern: '/assets/**',      filters: 'none'],
	[pattern: '/**/js/**',       filters: 'none'],
	[pattern: '/**/css/**',      filters: 'none'],
	[pattern: '/**/images/**',   filters: 'none'],
	[pattern: '/**/favicon.ico', filters: 'none'],
	// [pattern: '/**', filters: 'JOINED_FILTERS'],
    [pattern:'**/tienda/ordenaris/api/distribuidor/listar', filters:'anonymousAuthenticationFilter,restTokenValidationFilter,restExceptionTranslationFilter,filterInvocationInterceptor'],
    [pattern:'**/tienda/ordenaris/api/producto/listar', filters:'anonymousAuthenticationFilter,restTokenValidationFilter,restExceptionTranslationFilter,filterInvocationInterceptor'],
    [pattern:'**/tienda/ordenaris/api/colores/listar', filters:'anonymousAuthenticationFilter,restTokenValidationFilter,restExceptionTranslationFilter,filterInvocationInterceptor'],
 
 	[pattern:'**/tienda/ordenaris/api/producto/registrar', filters:"JOINED_FILTERS,-exceptionTranslationFilter,-authenticationProcessingFilter,-securityContextPersistenceFilter,-authenticationFilter"],
 	[pattern:'**/tienda/ordenaris/api/producto/$uuid/actualizar', filters:"JOINED_FILTERS,-exceptionTranslationFilter,-authenticationProcessingFilter,-securityContextPersistenceFilter,-authenticationFilter"],
 	[pattern:'**/tienda/ordenaris/api/colores/registrar', filters:"JOINED_FILTERS,-exceptionTranslationFilter,-authenticationProcessingFilter,-securityContextPersistenceFilter,-authenticationFilter"],
 	[pattern:'**/tienda/ordenaris/api/tipo/registrar', filters:"JOINED_FILTERS,-exceptionTranslationFilter,-authenticationProcessingFilter,-securityContextPersistenceFilter,-authenticationFilter"],
 	[pattern:'**/tienda/ordenaris/api/categoria/registrar', filters:"JOINED_FILTERS,-exceptionTranslationFilter,-authenticationProcessingFilter,-securityContextPersistenceFilter,-authenticationFilter"],
 	[pattern:'**/tienda/ordenaris/api/marca/registrar', filters:"JOINED_FILTERS,-exceptionTranslationFilter,-authenticationProcessingFilter,-securityContextPersistenceFilter,-authenticationFilter"],
 	[pattern:'**/tienda/ordenaris/api/distribuidor/registrar', filters:"JOINED_FILTERS,-exceptionTranslationFilter,-authenticationProcessingFilter,-securityContextPersistenceFilter,-authenticationFilter"],
 	[pattern:'**/tienda/ordenaris/api/distribuidor/informacion', filters:"JOINED_FILTERS,-exceptionTranslationFilter,-authenticationProcessingFilter,-securityContextPersistenceFilter,-authenticationFilter"],
	[pattern:"/**",filters:"JOINED_FILTERS,-restTokenValidationFilter,-restExceptionTranslationFilter"]
	]

