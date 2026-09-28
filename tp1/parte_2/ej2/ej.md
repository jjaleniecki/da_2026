### Para buscar, mediante el algoritmo de Horspool, un patrón de longitud m en un texto de longitud n, con n <= m, dar un ejemplo para:
1. Mejor caso
2. Peor caso

El algoritmo Boyer Moore Horspool (o Horspool solito) se usa para encontrar un patrón en un texto. Funciona creando previamente una tablita (Bad Match Table) la cual contiene para cada letra del patrón un valor.

El valor se calcula como: value = length - index - 1
La última letra = length si no fue definida
Por ejemplo:

piph        p   i   h   *
0123        3           4   p: 4 - 0 - 1 
            2   1       4   i: 4 - 1 - 1
            1   1       4   p: 4 - 2 - 1
            1   1   5   4

Se comparan mirando el más a la derecha del patrón:
haphipiph
piph
h = h entonces se pasa a la siguiente
p = p entonces se pasa a la siguiente
i != a entonces se frena, como el valor asignado a la "a" no está, se usa el * comodin = 4 y se mueve el patron 4 posiciones a la derecha

haphipiph
    piph
h != p entonces se frena, como el valor asignado a p es 1, se mueve el patrón 1 posicion a la derecha

haphipiph
     piph
match !! ding ding ding

ahora que veo el enunciado dice m <= n? osea el patrón siendo más grande o igual que el texto?
si fuera ese caso
a. que la primer comparación de falso ya te asegura que el algoritmo frena y es O(1)
b. que la ultima comparación de falso ya que recorres todo el texto y es O(n) 
esto sería asumiendo n = m, porque si m < n entonces siempre retorna falso porque siempre el patrón es más grande y nunca lo vas a encontrar como substring de un texto más pequeño jej

y si no es un typo bueno 