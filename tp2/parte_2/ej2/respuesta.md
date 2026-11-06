### Enunciado:
- Indicar por qué es un problema NP
- Encontrar un problema conocido NP-completo para poder reducir Coalición Sospechosa
- Describir un algoritmo que, en tiempo polinomial, realiza la reducción y probar su validez.

### Resolución:

## - Inciso 1
Se dice que un problema está en NP cuando se puede mostrar un certificado que se puede verificar en tiempo polinomial. Es decir, si podemos exhibir un algoritmo que, en tiempo polinomial, demuestre si una asignación de valores es válida.

Para el problema, podemos encontrar un algoritmo verificador, que chequee si una asignación de orador por semana es válida. Para esto debemos chequear:

1. Que cada orador elegido esté libre esa semana
2. Que cada proyecto tenga al menos un orador relevante entre los elegidos

Y esto es posible de hacer en tiempo polinomial, ya que para cumplir el (1) debemos recorrer cada semana (son l semanas) buscando que el orador (habiendo n oradores) esté disponible esa semana (es decir con costo polinomial O(l\*n)), y para cumplir el (2) debemos verificar que cada proyecto tenga al menos un orador relevante, es decir un bucle anidado que itera sobre cada proyecto y cada semana (es decir con costo polinomial O(p\*l\*n)).

Por lo tanto, como se logró mostrar un algoritmo que opera en tiempo polinomial y permite verificar una asignación, podemos afirmar que el problema es NP.

## - Inciso 2
Un problema que se puede reducir al problema *Problema de la Planificación de Conferencia* es el 3-SAT, el cual es de los más utilizados para demostrar la NP-Completitud de muchos problemas, pero uno más parecido y con una reducción más directa es el problema *Vertex Cover*. Este problema plantea un grafo G = (V, E) y un entero k, y pregunta ¿existe un conjunto de a lo sumo k vértices tal que cada arista tenga al menos uno de sus extremos en el conjunto?.
La correspondencia que se realiza es:

| Vertex Cover | Planificación de Conferencia |
|---|---|
| Grafo G = (V, E) y entero k | Instancia (n, l, p, L₁..L_l, P₁..P_p) |
| Cada vértice v ∈ V | Un orador (n = \|V\|) |
| Tamaño máximo del cover: k | Cantidad de semanas de conferencia: l = k |
| Cualquier vértice puede elegirse | Todos los oradores disponibles en cada semana: Lᵢ = V para i = 1..k |
| Cada arista e = {u, v} ∈ E | Un proyecto con Pₑ = {u, v} (p = \|E\|) |
| Vertex cover C con \|C\| ≤ k | Asignación de un orador por semana que cubre todos los proyectos |

## - Inciso 3
El algoritmo de reducción, basado en Vertex Cover tiene como entrada una instancia de Vertex Cover, y como salida una instancia del problema de Planificación de COnferencia, y es como sigue:
```
Algoritmo Reducir(G = (V, E), k):
    si k ≥ |V|:
        k ← |V|          // con |V| vértices siempre hay cover: V mismo

    n ← |V|              // un orador por vértice
    l ← k                // una semana por cada vértice permitido en el cover
    p ← |E|              // un proyecto por arista

    para i = 1 hasta l:
        Lᵢ ← V           // todos los oradores disponibles todas las semanas

    para cada arista e = {u, v} ∈ E:
        Pₑ ← {u, v}      // el proyecto e requiere haber visto a u o a v

    devolver (n, l, p, L₁..L_l, P₁..P_p)
```
### Prueba de validez

Hay que probar que:

> G tiene un vertex cover de tamaño ≤ k **⇔** la instancia construida tiene respuesta SÍ.

#### (⇒) Si G tiene un vertex cover, la instancia es SÍ

Sea C ⊆ V un vertex cover con |C| ≤ k. Como k ≤ |V|, se pueden agregar vértices arbitrarios a C hasta que |C| = k. Sigue siendo un vertex cover, porque agregar vértices no descubre ninguna arista.

Escribimos C = {c₁, c₂, ..., c_k} y asignamos **sᵢ = cᵢ** para la semana i = 1..k.

- **Disponibilidad:** sᵢ ∈ V = Lᵢ para toda semana i. ✔
- **Cobertura:** sea Pₑ el proyecto de una arista e = {u, v}. Como C es vertex cover, u ∈ C o v ∈ C, es decir, alguno de los dos es un sᵢ elegido. Entonces {s₁..s_k} ∩ Pₑ ≠ ∅. ✔

La asignación es válida, así que la respuesta es SÍ.

#### (⇐) Si la instancia es SÍ, G tiene un vertex cover

Sea (s₁, ..., s_l) una asignación válida, y sea C = {s₁, ..., s_l} el conjunto de oradores elegidos.

- **Tamaño:** C tiene a lo sumo l = k elementos (uno por semana, y puede haber repetidos). Entonces |C| ≤ k. ✔
- **Es cover:** sea e = {u, v} ∈ E cualquier arista. Como la asignación es válida, el proyecto Pₑ = {u, v} tiene algún orador elegido, es decir, C ∩ {u, v} ≠ ∅. Por lo tanto la arista e tiene un extremo en C. ✔

C es un vertex cover de tamaño ≤ k.

### Conclusión

La transformación se calcula en tiempo polinomial y preserva las respuestas: la instancia de Vertex Cover es SÍ si y solo si la instancia construida de Planificación de Conferencia es SÍ. Entonces **Vertex Cover ≤ₚ Planificación de Conferencia**.

Como Vertex Cover es NP-completo, Planificación de Conferencia es **NP-difícil**. Junto con lo probado antes (el problema está en NP), resulta que es **NP-completo**.