(ns loja.aula5
  (:require [loja.db :as l.db]
            [loja.logic :as l.logic]))

(defn gastou-bastante?
  [info-do-usuario]
  (> (:preco-total info-do-usuario) 500))

(let [pedidos (l.db/todos-pedidos)
      resumo (l.logic/resumo-por-ususario pedidos)]
  (println "keep > 500" (keep gastou-bastante? resumo)))

; keep works like a map, with the possibility to use a filter (returning null from the map function)
 
(defn gastou-bastante?
  [info-do-usuario]
  (println "gastou bastante?" (:usuario info-do-usuario))
  (> (:preco-total info-do-usuario) 500))

(println (take 2 (range 10)))


; range creates a lazy evaluated sequence of values
(let [sequencia (range 1000000000000)]
  (println (take 2 sequencia))
  (println (take 10 sequencia)))

; lazyness and eagerness
(defn filtro1
  [x]
  (println "filtro1" x)
  x)

(defn filtro2
  [x]
  (println "filtro2" x)
  x)

(println (map filtro1 (range 10)))

(->> (range 10)
     (map filtro1)
     (map filtro2)
     println)