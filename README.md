# Technical Test Java API

## Descripción

Este proyecto es una API construida en Java utilizando Spring Boot. La API permite registrar usuarios con sus respectivos teléfonos, validar los datos de entrada, y generar tokens JWT para la autenticación. Se implementaron todas las funciones solicitadas en la prueba técnica, incluyendo validaciones de correo y contraseña, manejo de excepciones y pruebas unitarias.

## Requisitos

- **Java**: Version 17 o superior.
- **Gradle**: Gestor de dependencias y construcción del proyecto.
- **Postman**: Para probar la API de manera manual.
- **Git**: Para gestionar el código fuente.

## Diagrama de la solución
![Diagrama Prueba](https://github.com/user-attachments/assets/817caf86-ee33-4bc0-abfb-6ce67ddaa645)


## Configuración del Proyecto

### Clonar el Repositorio

```bash
git clone https://github.com/santihz98/technical_test_java.git
cd technical_test_java
```
### Compilar y Ejecutar el Proyecto

Puedes compilar y ejecutar el proyecto utilizando Gradle. Asegúrate de estar en el directorio raíz del proyecto y ejecuta:

```bash
./gradlew bootRun
```

### Acceso a la Consola H2

La consola H2 está disponible en:

```bash
http://localhost:8080/h2-console
```
- **JDBC URL:** jdbc:h2:mem:testdb
- **User Name:** sa
- **Password:** password

### Acceso a Swagger

La documentación de la API generada automáticamente por Swagger está disponible en:

```bash
http://localhost:8080/swagger-ui.html
```
## Endpoints

### Registro de Usuario

**Endpoint:** `POST /api/v1/users/register`

**Body Ejemplo:**

```json
{
    "name": "Juan Perez",
    "email": "juan@perez.com",
    "password": "Hunter2",
    "phones": [
        {
            "number": "1234567",
            "citycode": "1",
            "contrycode": "57"
        }
    ]
}
```
### Respuestas Posibles:

- **201 Created:** Si el usuario se registra exitosamente.
- **400 Bad Request:** Si el correo ya está registrado o si hay errores de validación en los campos.

## Probar la API con Postman

### Registrar un Usuario:

1. Configura una solicitud `POST` en Postman hacia `http://localhost:8080/api/v1/users/register`.
2. En la pestaña "Body", selecciona `raw` y `JSON`, luego ingresa el JSON de ejemplo mostrado anteriormente.
3. Haz clic en "Send" para registrar el usuario.

### Verificar Respuesta:

- Si todo es correcto, deberías recibir un código `201 Created` y un JSON con los detalles del usuario y el token generado.

## Pruebas Unitarias

El proyecto incluye pruebas unitarias para los servicios principales. Para ejecutar las pruebas unitarias:

```bash
./gradlew test
```
## Contacto
Para cualquier duda me puedes contactar a mi correo sahz1998@gmail.com

