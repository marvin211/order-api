# Order API
Order API es una aplicación basada en Spring Boot que gestiona órdenes, productos y usuarios. Esta API proporciona funcionalidades esenciales para administrar un sistema de pedidos, lo que incluye la creación, actualización, eliminación, consulta de órdenes y productos, así como la autenticación de usuarios.

## Características Principales
- **Órdenes de Compra:** La API permite a los usuarios crear órdenes de compra, especificando los productos y sus cantidades asociadas. Además, calcula automáticamente el precio total de cada orden.
- **Productos:** Puede listar todos los productos disponibles, obtener detalles de un producto específico por su ID, crear nuevos productos, actualizar productos existentes y eliminar productos.
- **Usuarios:** Los usuarios pueden registrarse utilizando la ruta /signup y luego iniciar sesión con sus credenciales utilizando la ruta /login.

## Requisitos de Desarrollo

Para ejecutar esta API en su entorno local, asegúrese de tener instalados los siguientes requisitos:

- [Java 17](https://www.oracle.com/java/technologies/javase-downloads.html)
- [PostgreSQL](https://www.postgresql.org/download/)
- Las dependencias de Spring Boot se gestionan automáticamente, utilizando Maven.

## Configuración del Entorno

1. **Clonar el repositorio**

    ```bash
    git clone https://github.com/marvin211/order-api.git
    ```
2. Abre el proyecto en tu IDE de desarrollo preferido
3. Crea una base de datos PostgreSQL llamada `orderapi`
4. Actualiza el archivo `application.properties` con tu configuración de base de datos
5. Ejecuta la aplicación y navega a `http://localhost:8080/api/v1` para ver la aplicación en funcionamiento
6. Puedes probar la API en la documentación de Swagger en  `http://localhost:8080/api/v1/swagger-ui/index.html`

¡Gracias por utilizar la Order API! Espero que esta descripción le ayude a comprender las capacidades y la funcionalidad de la aplicación. Si tiene alguna pregunta o necesita asistencia adicional, no dude en ponerse en contacto conmigo.