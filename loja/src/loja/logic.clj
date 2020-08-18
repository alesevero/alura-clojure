(ns loja.logic)

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

(defn resumo-por-ususario
    [pedidos]
    (->> pedidos
         (group-by :usuario)
         (map conta-total-por-usuario)))
