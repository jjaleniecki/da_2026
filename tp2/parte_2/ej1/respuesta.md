

**Problema (Coalición Sospechosa).** Dados el conjunto de usuarios U, los valores I(u,m) para u ∈ U y m ∈ {1..t}, la secuencia de IPs i₁,…,i_t y un entero k, ¿existe S ⊆ U con |S| ≤ k tal que para todo m existe u ∈ S con I(u,m) = i_m?

## 1. Por qué está en NP

- **Certificado:** el subconjunto S ⊆ U. Tiene tamaño a lo sumo |U|, es decir, polinomial en la entrada.
- **Verificador:** comprobar que |S| ≤ k, y para cada minuto m = 1..t recorrer los usuarios de S buscando uno con I(u,m) = i_m. Cuesta O(|S|·t) ≤ O(|U|·t), que es polinomial en el tamaño de la entrada (|U|·t valores).

Por lo tanto Coalición Sospechosa ∈ NP.

## 2. Problema NP-completo elegido: Set Cover

**Set Cover (decisión).** Dados un universo X = {1,…,n}, subconjuntos S₁,…,S_m ⊆ X y un entero k, ¿existen a lo sumo k subconjuntos cuya unión es X?

Es NP-completo (Karp, 1972; también lo es Vertex Cover, que es el caso particular donde los elementos son aristas y los conjuntos son vértices). Se prueba **Set Cover ≤ₚ Coalición Sospechosa**. La analogía es directa: los minutos son los elementos a cubrir y los usuarios son los subconjuntos que los cubren.

## 3. La reducción

**Preproceso (casos triviales).** Se descartan los subconjuntos vacíos, porque nunca ayudan a cubrir y además un usuario sin ningún acceso violaría la hipótesis del enunciado. Si algún Sₛ = X (y k ≥ 1), el enunciado de Coalición Sospechosa no admite una instancia donde un solo usuario haga todo el ataque, así que la reducción devuelve directamente una instancia fija con respuesta "sí" (por ejemplo, 2 usuarios con accesos complementarios y k = 2). Si k = 0, la respuesta es "no" (con n ≥ 1), y se devuelve una instancia fija con respuesta "no".

**Construcción** (para el caso general, con n ≥ 1 y ningún Sₛ = X):

- Cantidad de minutos: **t = n**, un minuto por elemento.
- IPs del ataque: i₁,…,i_n, **n direcciones distintas** cualesquiera (por ejemplo, i_j = "10.0.0.j").
- Usuarios: **U = {u₁,…,u_m}**, un usuario por subconjunto.
- Registro: para cada usuario u_s y cada minuto j,

  I(u_s, j) = i_j si j ∈ S_s, y I(u_s, j) = | en caso contrario.

- Parámetro: el mismo **k**.

Cumple las hipótesis: cada usuario accede al menos a una IP (los Sₛ no vacíos), y ningún usuario cubre todo el ataque (ningún Sₛ = X).

**Costo.** Se llena una tabla de m × n valores, en O(m·n), que es polinomial en el tamaño de la instancia de Set Cover.

## 4. Validez de la reducción

**Afirmación.** Existen a lo sumo k subconjuntos que cubren X ⟺ existe una coalición sospechosa de a lo sumo k usuarios.

**(⇒)** Sea F ⊆ {1..m} con |F| ≤ k y ⋃_{s∈F} Sₛ = X. Sea S = {u_s : s ∈ F}, con |S| ≤ k. Para cada minuto j, como j ∈ X, existe s ∈ F con j ∈ Sₛ, y por construcción I(u_s, j) = i_j con u_s ∈ S. Entonces S es una coalición sospechosa de tamaño a lo sumo k.

**(⇐)** Sea S una coalición sospechosa con |S| ≤ k, y sea F = {s : u_s ∈ S}, con |F| ≤ k. Para cada j ∈ {1..n} existe u_s ∈ S con I(u_s, j) = i_j. Por construcción, la única forma de que I(u_s, j) valga i_j es que j ∈ Sₛ. Luego todo j está cubierto por algún Sₛ con s ∈ F, y esos a lo sumo k subconjuntos cubren X.

**Conclusión.** La transformación es polinomial y preserva las respuestas "sí" y "no", por lo que Set Cover ≤ₚ Coalición Sospechosa. Como Coalición Sospechosa ∈ NP (sección 1) y Set Cover es NP-completo, **Coalición Sospechosa es NP-completo**. ∎
