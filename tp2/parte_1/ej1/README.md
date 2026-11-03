# Gale–Shapley con pares prohibidos (H–M)

Extensión del problema clásico de emparejamiento estable: dados un conjunto de hombres `H`, un conjunto de mujeres `M` y un conjunto de pares prohibidos `P ⊆ H × M`, cada persona ranquea únicamente a quienes **no** tiene prohibidos. Puede quedar gente soltera si las prohibiciones lo fuerzan.

## Contenido

- `GaleShapleyProhibidos.java`: implementación en Java (versión "H proponen").
- `entrada.txt`: ejemplo de entrada.

## Definición de estabilidad

Un matching es estable si cumple:

1. **(i)** Es uno a uno: cada persona tiene a lo sumo una pareja.
2. **(ii)** Ningún par asignado pertenece a `P`.
3. **(iii)** No hay dos solteros mutuamente aceptables: si `h` y `m` están solteros, entonces `(h,m) ∈ P`.
4. **(iv)** No hay pares bloqueantes: no existe `(h,m) ∉ P` tal que `h` esté soltero o prefiera a `m` sobre su pareja, **y** `m` esté soltera o prefiera a `h` sobre su pareja.

## Formato de entrada y salida

**Entrada:**

```
n
matriz_prohibidos n x n (0/1)     // 1 = prohibido (h,m)
preferencias_H: n líneas, cada una con la lista de M no prohibidas, en orden
preferencias_M: ídem para H no prohibidos
```

**Salida:** pares asignados `h m`, y listado de solteros (si los hubiera).

## Compilar y ejecutar

```bash
javac GaleShapleyProhibidos.java
Get-Content entrada.txt | java GaleShapleyProhibidos.java
```

### Ejemplo

`entrada.txt` (n = 3, con `(h0,m2)` y `(h1,m0)` prohibidos):

```
3
0 0 1
1 0 0
0 0 0
1 0
1 2
0 1 2
2 0
1 0 2
2 1
```

Salida esperada:

```
Pares:
1 1
2 0
Solteros H: 0
Solteras M: 2
```

Traza: `h0` propone a `m1` y es aceptado. `h1` propone a `m1`, que lo prefiere, y desplaza a `h0`. `h2` propone a `m0` y es aceptado. `h0` propone a `m0`, que prefiere a `h2`, y lo rechaza; como se le acaba la lista, queda soltero. `m2` queda soltera porque `(h0,m2)` está prohibido. Es un caso de gente soltera con matching estable.

### Casos borde sugeridos

- `n = 1` con `(0,0)` prohibido: ambos quedan solteros.
- Todo prohibido: nadie se empareja.
- Nada prohibido: debe dar el Gale–Shapley clásico con matching perfecto.
- Una lista vacía (línea en blanco en el archivo).

Una forma de ganar confianza es escribir un verificador que compruebe (i)–(iv) en O(n²) sobre el resultado y correrlo contra casos aleatorios.

