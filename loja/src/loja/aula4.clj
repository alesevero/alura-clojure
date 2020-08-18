(ns loja.aula4
  (:require [loja.db :as l.db]
            [loja.logic :as l.logic]))

(let [pedidos (l.db/todos-pedidos)
      resumo (l.logic/resumo-por-ususario pedidos)]
  (println "Resumo" resumo)
  (println "Ordenado" (sort-by  :preco-total resumo))
  (println "Reverse" (reverse (sort-by :preco-total resumo)))
  (println "Ordenado por id" (sort-by :usuario-id resumo))
  (println (get-in pedidos [0 :itens :mochila :quantidade])))

(defn resumo-por-usuario-ordenado
   [pedidos]
   (->> pedidos
        l.logic/resumo-por-ususario
        (sort-by :preco-total)
        reverse))

(resumo-por-usuario-ordenado (l.db/todos-pedidos))

(defn top-2
  [resumo]
  (take 2 resumo))

(let [pedidos (l.db/todos-pedidos)
      resumo (resumo-por-usuario-ordenado pedidos)]
  (println "Resumo" resumo)
  (println "Primeiro" (first resumo))
  (println "Segundo" (second resumo))
  (println "Dois primeiros" (top-2 resumo))
  (println "Resto" (rest resumo))
  (println "Total" (count resumo))
  (println "Class" (class resumo))
  (println "Nth" (nth resumo 1))
  (println "Get" (get resumo 1))
  (println "Get" (take 2 resumo)))

(let [pedidos (l.db/todos-pedidos)
      resumo (resumo-por-usuario-ordenado pedidos)]
  (filter #(> (:preco-total %) 500) resumo))