# Demo for Mu Server


#
Pequeño proyecto para ejecutar un par de APIS con tecnología Java 
y MuServer como servidor de aplicaciones. 


## Requisitos

Este demo necesita de MongoDB para poder funcionar, ya viene incluido.

## Estructura del proyecto.

    [mu-server-demo](mu-server-demo)
    Proyecto Maven + Java con el código.

    - doker-compose.yaml: Archivo con la definición de los servicios docker.
    - Dockerfile: Archivo para la cponstrucción de la imágen Docker.
    - run.sh: Script shell para desplegar el proyecto.


## Desarrollo

Dentro del proyecto *[mu-server-demo](mu-server-demo)* podemos apreciar las siguientes clases:

[App.java](mu-server-demo%2Fsrc%2Fmain%2Fjava%2Fmx%2Fcom%2Fbhit%2Fmicro%2FApp.java)
    Clase principal que inicia el servidor. En ella se declaran el controlador y otras configuraciones básicas.

[BookingController.java](mu-server-demo%2Fsrc%2Fmain%2Fjava%2Fmx%2Fcom%2Fbhit%2Fmicro%2Fapi%2FBookingController.java)
    Esta clase nos sirve para crear los métodos que son expuestos por las APIs REST. 

[MongoBookingService.java](mu-server-demo%2Fsrc%2Fmain%2Fjava%2Fmx%2Fcom%2Fbhit%2Fmicro%2Fservice%2FMongoBookingService.java)
    Clase de servicio de negocio donde se aloja la lógica de programación principal, esta clase es usada por el controlador para
    efectuar las operaciones a la base de datos. 

[Booking.java](mu-server-demo%2Fsrc%2Fmain%2Fjava%2Fmx%2Fcom%2Fbhit%2Fmicro%2Fmodel%2FBooking.java)
    Clase con el POJO que representa el objeto Booking. 

## Ejecución local
    Para poder ejecutar esta aplicación es necesario tener instalado maven, un jdk java versión 17 y docker.

### Compilación

* Entrar a la carpeta del proyecto:

    cd mu-server-demo

* Compilar y empaquetar

  mvn clean compile package -Dmaven.test.skip=true

Esto genera un jar ejecutable en la carpeta target.

[mu-server-demo-1.0-SNAPSHOT-jar-with-dependencies.jar](mu-server-demo%2Ftarget%2Fmu-server-demo-1.0-SNAPSHOT-jar-with-dependencies.jar)

Ahora ejecutamos:

    java -jar ./target/mu-server-demo-1.0-SNAPSHOT-jar-with-dependencies.jar

Deberíamos ver como resultado:

```bash
SLF4J: Failed to load class "org.slf4j.impl.StaticLoggerBinder".
SLF4J: Defaulting to no-operation (NOP) logger implementation
SLF4J: See http://www.slf4j.org/codes.html#StaticLoggerBinder for further details.
Server started at http://localhost:58371/bookings
```

De esa forma sabemos que el servidor Mu prendió, pero no responderá las peticiones debido a que le hace falta un MongoDB.
Para apagar solo hay que presionar ctrl + c 

### Ejecutar con Docker 

Para ver el ejemplo completo es necesario regresar a la carpeta raíz:

```
cd ..
```

y ejecutar el siguiente comando:

    docker-compose up --build -d
##

Esto nos ayudará a construir la imágen docker de nuestra aplicación y su base de datos mongo. 

Ver los logs de la aplicación:

    docker compose logs -f --tail 200 app

```cmd
app-1  | SLF4J: Failed to load class "org.slf4j.impl.StaticLoggerBinder".
app-1  | SLF4J: Defaulting to no-operation (NOP) logger implementation
app-1  | SLF4J: See http://www.slf4j.org/codes.html#StaticLoggerBinder for further details.
app-1  | Server started at http://localhost:8080/bookings
```

presionar *crtl + c* para salir

Ver los logs de la base de datos:

    docker compose logs -f --tail 200 database

```cmd
atabase-1  | {"t":{"$date":"2024-11-01T20:08:20.005+00:00"},"s":"W",  "c":"CONTROL",  "id":636300,  "ctx":"ftdc","msg":"Use of deprecated server parameter name","attr":{"deprecatedName":"wiredTigerConcurrentReadTransactions","canonicalName":"storageEngineConcurrentReadTransactions"}}
database-1  | {"t":{"$date":"2024-11-01T20:08:20.005+00:00"},"s":"W",  "c":"CONTROL",  "id":636300,  "ctx":"ftdc","msg":"Use of deprecated server parameter name","attr":{"deprecatedName":"wiredTigerConcurrentWriteTransactions","canonicalName":"storageEngineConcurrentWriteTransactions"}}
database-1  | {"t":{"$date":"2024-11-01T20:09:19.782+00:00"},"s":"I",  "c":"WTCHKPT",  "id":22430,   "ctx":"Checkpointer","msg":"WiredTiger message","attr":{"message":{"ts_sec":1730491759,"ts_usec":782177,"thread":"1:0x7f997ba006c0","session_name":"WT_SESSION.checkpoint","category":"WT_VERB_CHECKPOINT_PROGRESS","category_id":7,"verbose_level":"DEBUG_1","verbose_level_id":1,"msg":"saving checkpoint snapshot min: 4, snapshot max: 4 snapshot count: 0, oldest timestamp: (0, 0) , meta checkpoint timestamp: (0, 0) base write gen: 15"}}}
database-1  | {"t":{"$date":"2024-11-01T20:10:19.796+00:00"},"s":"I",  "c":"WTCHKPT",  "id":22430,   "ctx":"Checkpointer","msg":"WiredTiger message","attr":{"message":{"ts_sec":1730491819,"ts_usec":796679,"thread":"1:0x7f997ba006c0","session_name":"WT_SESSION.checkpoint","category":"WT_VERB_CHECKPOINT_PROGRESS","category_id":7,"verbose_level":"DEBUG_1","verbose_level_id":1,"msg":"saving checkpoint snapshot min: 5, snapshot max: 5 snapshot count: 0, oldest timestamp: (0, 0) , meta checkpoint timestamp: (0, 0) base write gen: 15"}}}
```

Si vemos que todo prendió de forma correcta ahora podemos ejecutar llamados REST para consumir nuestras apis. 

* Obtener todos los bookings

```
curl --location 'http://localhost:8080/bookings' \
--header 'Content-Type: application/json' \
--data ''
```

* Crear un booking

TODO


Apagar los contenedores

    docker compose down

Eliminar contenedores

    docker compose rm  --all -fv

