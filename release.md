[10:18 PM, 5/21/2017] Hugo UNGS: Release notes:
- Se permite guardar en la base el arbol (hoy en dia no verifica que ya exista, sino que lo guarda, entonces se puede guardar mas de una vez el arbol. Hoy en día al momento de recuperar desde la base hace un getFirst(), así habría un inconveniente ahí.

- Se recupera el arbol a la clase ArbolTags, y luego desde la vista mediante un método se transforma en Tree (de vaadin).
- Cuando se va creando un arbol dibuja de nuevo el arbol por lo que contrae todos los nodos :( habría que buscarle algo mejorr a eso.
- Habría que evaluar de nuevo el Eliminar (pido perdón por esto) y el agregado de duplicados, no llegué a hacerlo/verificarlo.

Comentarios de funcionalidad de la pagina de test
- Cuando se pulsa el boton Guardar Tree se persiste en la base
- Cuando se pulsa el boton Recuperar Tree, se limpia el arbol y se carga de nuevo (para evitar duplicar los datos)
- Cuando se pulsa el boton Agregar, se van agregando nodos/tags a la clase ArbolTags y luego dibuja en pantalla el mismo
- No se revisó la funcionalidad de eliminar.                        
[10:20 PM, 5/21/2017] Hugo UNGS: Al se un ArraList el contenedor de los Tags quizas sea mas facil detectar duplicados (entiendo que si es un SET evitaria duplicados). Desconozco un poco como funca Set, si lo consideran util se podría cambiar la implementacion                        
[10:21 PM, 5/21/2017] Hugo UNGS: si me quieren putear tambien están habilitados... ja