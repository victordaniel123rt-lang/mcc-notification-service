# MCC Notification Service 📧

Microservicio de notificaciones por correo electrónico para una arquitectura de microservicios bancaria. Este servicio se encarga de enviar notificaciones al correo electrónico del cliente a través de un sistema de colas implementado con Azure Service Bus.

## 🏗️ Arquitectura General

Este microservicio es parte de una arquitectura completa de 6 microservicios para un sistema bancario:

![Arquitectura de Microservicios MCC](https://github.com/victordaniel123rt-lang/mcc-credit-disbursement-service/raw/master/docs/architecture.png)

**Componentes del sistema:**
- **mcc-gateway-service**: Gateway API que gestiona las solicitudes entrantes
- **mcc-account-service**: Servicio de gestión de cuentas (comunicación síncrona)
- **mcc-customer-service**: Servicio de clientes
- **mcc-credit-disbursement-service**: Servicio de desembolsos de crédito
- **mcc-security-service**: Servicio de seguridad
- **mcc-notification-service**: Servicio de notificaciones (este repositorio)

## 📋 Descripción

El **MCC Notification Service** recibe eventos de otros microservicios a través de Azure Service Bus y envía notificaciones por correo electrónico a los clientes. Implementa un patrón de comunicación **asíncrona** para garantizar la confiabilidad y escalabilidad del sistema.

## 🛠️ Tecnologías Utilizadas

- **Java 17**: Lenguaje de programación principal
- **Spring Boot 4.0.7**: Framework web y aplicaciones Java
- **Spring Cloud Azure 7.3.0**: Integración con servicios de Azure
- **Azure Service Bus**: Sistema de colas para comunicación asíncrona
- **Spring Mail**: Envío de correos electrónicos
- **Lombok**: Reducción de código boilerplate
- **Maven**: Gestor de dependencias y construcción
- **Docker**: Containerización de la aplicación

## 🚀 Características Principales

- ✅ Recepción de mensajes desde Azure Service Bus
- ✅ Envío de notificaciones por correo electrónico
- ✅ Comunicación asíncrona y escalable
- ✅ Integración con Azure Services
- ✅ Containerización con Docker
- ✅ Despliegue en Azure Container Apps

## 📦 Requisitos

- Java 17 o superior
- Maven 3.6 o superior
- Cuenta de Azure con acceso a Service Bus
- Configuración de Azure para envío de correos

## ⚙️ Configuración

### Variables de Entorno

Configure las siguientes variables de entorno en `application.properties` o `application.yml`:

```properties
# Azure Service Bus
spring.cloud.azure.servicebus.connection-string=<tu-connection-string>
spring.cloud.azure.servicebus.queue-name=<nombre-de-la-cola>

# Email Configuration
spring.mail.host=<smtp-host>
spring.mail.port=<smtp-port>
spring.mail.username=<tu-email>
spring.mail.password=<tu-password>
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
```

## 🔧 Instalación y Compilación

### 1. Clonar el repositorio

```bash
git clone https://github.com/victordaniel123rt-lang/mcc-notification-service.git
cd mcc-notification-service
```

### 2. Compilar el proyecto

```bash
mvn clean package
```

### 3. Ejecutar la aplicación

```bash
mvn spring-boot:run
```

## 🐳 Docker

### Compilar la imagen Docker

```bash
mvn clean package
docker build -t mcc-notification-service:latest .
```

### Ejecutar el contenedor

```bash
docker run -p 8080:8080 \
  -e SPRING_CLOUD_AZURE_SERVICEBUS_CONNECTION_STRING="<connection-string>" \
  mcc-notification-service:latest
```

## 📊 Estructura del Proyecto

```
mcc-notification-service/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/vdgarcia/
│   │   │       ├── controller/       # Controladores REST
│   │   │       ├── service/          # Servicios de negocio
│   │   │       ├── listener/         # Listeners de Service Bus
│   │   │       ├── config/           # Configuración de la app
│   │   │       ├── model/            # Entidades y DTOs
│   │   │       └── MccNotificationServiceApplication.java
│   │   └── resources/
│   │       └── application.properties # Configuración
│   └── test/
│       └── java/
├── Dockerfile
├── pom.xml
└── README.md
```

## 🔄 Flujo de Funcionamiento

1. **Recepción de Eventos**: El servicio escucha en una cola de Azure Service Bus
2. **Procesamiento**: Los eventos son procesados por los listeners configurados
3. **Envío de Email**: Se construye y envía un correo electrónico al cliente
4. **Confirmación**: El mensaje es confirmado en la cola una vez procesado exitosamente

## 🌐 API REST

### Health Check

```bash
GET /actuator/health
```

Respuesta:
```json
{
  "status": "UP"
}
```

## 🧪 Testing

Para ejecutar las pruebas unitarias:

```bash
mvn test
```

**Nota**: Las pruebas están configuradas para ser omitidas en la compilación mediante `maven.test.skip=true`. Habilita los tests según sea necesario.

## 📝 Configuración de Azure Container Apps

El proyecto incluye configuración Maven para desplegar en Azure Container Apps:

```bash
mvn azure-container-apps:deploy
```

Asegúrate de configurar los siguientes parámetros en `pom.xml`:
- `subscriptionId`: Tu ID de suscripción de Azure
- `resourceGroup`: Grupo de recursos
- `appEnvironmentName`: Nombre del ambiente
- `region`: Región de Azure

## 🤝 Contribución

Las contribuciones son bienvenidas. Para grandes cambios, por favor:

1. Fork el repositorio
2. Crea una rama para tu feature (`git checkout -b feature/AmazingFeature`)
3. Commit tus cambios (`git commit -m 'Add some AmazingFeature'`)
4. Push a la rama (`git push origin feature/AmazingFeature`)
5. Abre un Pull Request

## 📄 Licencia

Este proyecto está bajo licencia. Para más detalles, consulta el archivo LICENSE.

## 👨‍💻 Autor

**Victor Daniel García**
- GitHub: [@victordaniel123rt-lang](https://github.com/victordaniel123rt-lang)

## 📞 Soporte

Para reportar problemas o sugerencias, abre un [issue](https://github.com/victordaniel123rt-lang/mcc-notification-service/issues) en el repositorio.

---

**Tags**: `java` `spring-boot` `servicebus` `asíncrono` `microservicios` `azure`
