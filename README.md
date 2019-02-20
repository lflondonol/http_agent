# Arquitectura HTTP AGENT
John Faber Florez Vasco <br/>
Luisa Fernanda Restrepo Gutierrez <br/>
Luis Fernando Londoño Londoño <br/>
-----------------------------------------------------------------------------------------------------------------------------

# 1. RESUMEN

La idea de desarrollar el servidor http, la basamos en dos conceptos que los tomamos como referencia para proponer un servidor http con caracteristicas adiconales. Los conceptos están basados en una arquitectura de agentes (BDI) y el cocnepto de validación de información basada en busqueda de validación de bloques.

Las arquitecturas BDI para agentes, tiene tres elementos que hace parte de la propuesta:
  - Belief (qué es lo que piensa): componente que tiene conocimiento sobre su contexto. Este es actualizado cuando se cumple su condición.
  - Desires (qué es lo que quiere): contiene los objetivos que el agente quisiera alcanzar.
  - Intention (qué es lo que hace): lo que se ha decidido hacer.
  
Tomamos como referencia el concepto de cadena de bloques, que servirá para validar la autenticidad de una petición HTTP.

# 2. CARACTERÍSTICAS E INTERESADOS
## 2.1. Características
- Recibir peticiones: servidor recibirá las peticiones enviadas por el web browser y las redireccionará al componente responsable.
- Validar peticiones: servidor tendrá la capacidad de validar las peticiones a travez de un conjunto de archivos que funcionarán como certificadores.
- Procesar peticiones: servidor sacará la información necesaria del request y buscará el recurso requerido de las peticiones válidas.
- Almacenar trazabilidad: servidor almacenará la trazabilidad de las peticiones.
- Responder peticiones: servidor responderá a las peticiones según resultado de la validación y procesamiento de este.

## 2.2. Interesados
Usuarios que harán peticiones al servidor

# 3. MODELO ARQUITECTONICO

<img src="https://s3.us-east-2.amazonaws.com/eafitrequisitos/Http+Agent+Architecture.png" />

<table border="2">
  <tr> 
    <td> <strong> Tipo Aplicación</strong> </td>
    <td> Servidor Web  </td>
  </tr>
  <tr> 
    <td> <strong> Estili Arquitectónico </strong> </td>
    <td> Modelo BDI </td>
  </tr>  
  <tr> 
    <td> <strong> Estrategia de Despliegue </strong> </td>
    <td> Cliente / Servidor  </td>
  </tr>  
  <tr> 
    <td> <strong> Aspectos Técnicos </strong> </td>
    <td> Archivos de texto para almacenar el log  </td>
  </tr>  
  <tr> 
    <td> <strong> Lenguaje </strong> </td>
    <td> Java  </td>
  </tr>  
  <tr> 
    <td> <strong> Frameworks </strong> </td>
    <td> Ninguno  </td>
  </tr>  
</table>

# 4. OPERACIONES

## 4.1.  Operaciones Anwer/Listener (Belief):

Cuando se realiza la petición HTTP el listener recibe la petición y  posteriormente solicita la verificación con el fin de saber si es una petición válida. Esta petición es recivida y verificada a través de una cadena de bloques para comprobar su autenticidad,  por último un componente procesa la respuesta de minero y redirecciona el resultado que realicen operaciones de rechazo o para procesar la petición..

## 4.2. Operaciones de Validación (Desired):

Las operaciones del agente validador, busca en una cadena de bloques la autenticidad de la petición y la refulta o solicita su atención al agente de operaciones.

## 4.3. Operaciones Agente Procesador

Las operaciones de este agente, se encarga de procesar la peticiónes, gestionando una cola que permite procesar y redireccionar la petición dondede corresponde. La petición puede resultar procesada satisfactoriamente o ser rechazada.

# 5. REGLAS CORRESPONDIENTES
Las restricciones del proyecto son dadas por la evaluación de la práctica, en donde se tienen las siguientes:
- Desarrollar el servidor Http 1.1 desde cero, sin usar frameworks.
- Implementación exclusica de la atención de peticiones
- Se debe almacenar la traza de las peticiones

# 6. REFERENCIAS





