# PoC del patrón Circuit Breaker

## Descripción de la prueba
Esta prueba de concepto consiste en la implementacion del patrón de arquitectura llamado circuit breaker el cual permite
romper un ciclo interminable de reintentos cuando es improbable que la respuesta sea exitosa, de esta forma se protege
al resto del sistema de causar fallos en otros componentes.

## Objetivos de la prueba
El objetivo de esta prueba es implementar de manera simple el patrón Circuit Breaker haciendo un llamado a una API de 
uso libre, ya sea utilizando una libreria o haciendo una implementacion propia del patrón y de esta forma entender mejor
en que consiste y su funcionamiento.

## Pasos implementados para la prueba

### Configuracion del proyecto
Se creo un nuevo proyecto de spring utilizando [Spring Initializr](https://start.spring.io/) donde es posible seleccionar
algunas de las librerias comunes de spring framework.

### Seleccion del API
Se buscaron APIs libres para pruebas que se pudieran usar en esta prueba. Finalmente se selecciono la API llamada 
[Cat Facts](https://catfact.ninja/), en especifico el endpoint **GET /facts** el cual retorna datos curiosos sobre los gatos.

### Creacion de los componentes
Se creo un controller que expone el endpoint **GET /cat_fact** y un service que consume el endpoint original y se encarga
de implementar la logica del circuit breaker, en este caso, las clases y funciones de la libreria utilizada.

### Configuracion de la libreria
Se aplicaron las configuraciones respectivas a la libreria utilizando el archivo application.yaml el cual permite definir 
las diferentes propiedades como el umbral de porcentaje de fallos, la duracion del estado abierto del circuito, la cantidad
de llamadas permitidas en un estado semi abierto y el minimo numero de llamados antes de calcular el porcentaje de fallos.

## Tecnologias usadas en la prueba
- Kotlin
- Spring boot (Web y Reactive Web)
- Spring cloud (Circuit Breaker reactivo para Resillience4J)
- Resillience4J

## Resultados
La aplicacion es capaz de detectar cuando las peticiones estan fallando y abrir el circuito exitosamente, tiene un tiempo
en el que el circuito se mantiene abierto por 10 segundos, despues de ese tiempo pasa a un estado semi abierto donde recibe
una peticion como maximo y si falla vuelve a estar abierto, pero si funciona pasa a estar cerrado como al principio, 
dejando pasar las peticiones normalmente.

## Conclusiones
Circuit Breaker es un patron efectivo para evitar la sobrecarga del sistema en casos de falla donde los usuarios podrian
hacer muchos reintentos que no tendran respuesta o no seran exitosos, ademas las soluciones existentes de Spring Framework
sobre la libreria Resillience4J hacen su implementacion mas sencilla y confiable.