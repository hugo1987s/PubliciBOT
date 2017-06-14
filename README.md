# PubliciBot
Sistema para generar camapañas publicitarias en redes sociales.
FIXME:

ARREGLAR EL DURACION HORRIBLE QUE QUEDO TODO TORCIDO POR MI CULPA ASDASDASDASD
1)Arreglar bug camapaña vacia y que no crashee al guardar camapañas vacias.

2)no se permite DESseleccioanr Tags hacer un boton Deseleccionar?

3)No hace falta el boton Cerrar deberia cerrar sola la pantalla de tags al hacer click en selecionar.

4)Sacar el boton Cargar deberia cargarse solo cuando abre la pantalla de campañas y actualizarse cuadno guarda

5) Agregar un boton eliminar campaña

TODO:
//1)Asignarle una accion a una campaña

//2)Que la campaña mande un mail (texto) con la frecuencia seleccionada

//3)Que la camapaña mande fotos

//4)Data Binding Y AddressBook

//5)Mejorar la Interfaz de campaña con una ui como esta
//https://github.com/vaadin/tutorial/

 

BACKLOG:
https://especificacionsoftwareungs17.myjetbrains.com/youtrack/agiles/88-4/89-5?backlog

**Como configurar el proyecto:**
1) Descargar IntelliJ Comunity (con eclipse funciona pero es mas complicado)
https://www.jetbrains.com/idea/download/download-thanks.html?platform=windows&code=IIC
2) Descargar Git Hub desktop (es lo mas practico aunque se puede hacer directamente desde intellij bajando Git.exe)
https://github-windows.s3.amazonaws.com/GitHubSetup.exe
3) si confirmaste la invitacion al proyecto iniciar sesion en Git Hub Desktop te si le das al (+) te deberia aparecer el proyecto y pones Clone
4) abris intelliJ -->importar proyecto --->seleccionas la ubicacion C/users/"tusuario"/documentos/GiHub/PubliciBot
5)Seleccionas Maven y le das a todo siguiente
6) En intelliJ vas a VIEW--->TOOL WINDOWS--->MAVEN
7) Se despliega una barra a la derecha seleccionas plugins---jetty---run y le das click derecho y pones Create Publicibot
8) dale al boton de Play en la barra y listo vas a http://localhost:8080/debug? en el navegador


-------------------------
Se cambia la versión de Vaadin en el POM pues la 8 no trae los controles Tree.
Así estaba el POM antes:
		<vaadin.version>8.0.5</vaadin.version>
		<vaadin.plugin.version>8.0.5</vaadin.plugin.version>

-------------------------


**LINKS UTILES:**

Para integrar telegram
https://gist.github.com/rubenlagus/bc36b532d975bbe5070e

Para mandar mails
https://www.tutorialspoint.com/java/java_sending_email.htm

Para tareas programadas
https://www.adictosaltrabajo.com/tutoriales/quartz/

