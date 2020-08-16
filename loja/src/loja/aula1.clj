(ns loja.aula1)

(def names ["daniela" "guilherme" "carlos" "paulo" "lucia" "ana"])

(map println names)
(first [])
(first names)
(rest names)
(rest [])
(next names)
(next [])

(seq [1 2 3 4])
(seq [])

; loops and for are created using recursion
(defn my-map
  [funcao sequencia]
  (let [primeiro (first sequencia)]
    (if primeiro ; doesn't work if there's a falsy element in de sequence
      (do
        (funcao primeiro)
        (meu-mapa funcao (rest sequencia))))))

(my-map println names)

(defn my-map-nil
  [funcao sequencia]
  (let [primeiro (first sequencia)]
    (if (not (nil? primeiro))
      (do
        (funcao primeiro)
        (meu-mapa funcao (rest sequencia))))))

(my-map-nil println names)

; looping using the map above easily throws StackOverflowError
; due to function call memory stacking

; TAIL RECURSION
; recur can only be used as the last function call
(defn my-map
  [funcao sequencia]
  (let [primeiro (first sequencia)]
    (if (not (nil? primeiro))
      (do
        (funcao primeiro)
        (recur funcao (rest sequencia))))))

(my-map println (range 5000))
