# Arquitectura HTTP AGENT
--------------------------------------

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

# 3. MODELO ARQUITECTONICO

![Screenshot](Http_Agent_Architecture)

# 4. OPERACIONES

# 5. REGLAS CORRESPONDIENTES

# 6. REFERENCIAS





