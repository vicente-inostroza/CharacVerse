# TODO: Implementación del Microservicio de Usuario y Roles

## Pasos a Completar

- [ ] Actualizar pom.xml con dependencias necesarias (Spring Web, JPA, Security, JWT, H2 DB)
- [ ] Crear entidad Usuario (id, username, password, nombre, avatar, roles)
- [ ] Crear entidad Rol
- [ ] Crear repositorio UsuarioRepository
- [ ] Crear repositorio RolRepository
- [ ] Crear servicio UsuarioService (registro, login, gestión de perfil)
- [ ] Crear servicio JwtService para tokens
- [ ] Crear controlador AuthController (registro, login, logout)
- [ ] Crear controlador UsuarioController (perfil, roles)
- [ ] Configurar seguridad con JWT (SecurityConfig)
- [ ] Actualizar application.properties para configuración de base de datos
- [ ] Probar la aplicación ejecutándola y verificando endpoints
