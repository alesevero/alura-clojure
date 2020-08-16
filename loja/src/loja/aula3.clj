(ns loja.aula3
  (:require [loja.db :as l.db]))

(l.db/todos-pedidos)

(group-by :usuario (l.db/todos-pedidos))

(defn minha-funcao-de-agrupamento
  [elemento]
  (println "elemento" elemento)
  (:usuario elemento))

(group-by minha-funcao-de-agrupamento (l.db/todos-pedidos))

(map count (vals (group-by :usuario (l.db/todos-pedidos))))
; same thing as:
(->> (l.db/todos-pedidos)
     (group-by :usuario)
     vals
     (map count))

;; (defn conta-total-por-usuario
;;   [[usuario pedidos]]
;;   {:usuario usuario
;;    :total-de-pedidos (count pedidos)})

;; (->> (l.db/todos-pedidos)
;;      (group-by :usuario)
;;      (map conta-total-por-usuario))

(defn total-do-item
  [[_ detalhes]]
  (* (get detalhes :quantidade 0) (get detalhes :preco-unitario 0)))

(defn total-do-pedido
  [pedido]
  (->> pedido
       (map total-do-item)
       (reduce +)))

(defn total-dos-pedidos
  [pedidos]
  (->> pedidos
       (map :itens)
       (map total-do-pedido)
       (reduce +)))

(defn conta-total-por-usuario
  [[usuario pedidos]]
  {:usuario usuario
   :total-de-pedidos (count pedidos)
   :preco-total (total-dos-pedidos pedidos)})

(->> (l.db/todos-pedidos)
     (group-by :usuario)
     (map conta-total-por-usuario))
