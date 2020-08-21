(ns hospital.logic)

 (defn cabe-na-fila?
   [hospital departamento]
   (-> hospital
       (get departamento)
       count
       (< 5)))

(defn chega-em
  [hospital departamento pessoa]
  (if (cabe-na-fila? hospital departamento)
    (update hospital departamento conj pessoa)
    (throw (ex-info "fila ja esta cheia" {:tentando-adicionar-pessoa pessoa}))))

(defn atende
  [hospital departamento]
  (update hospital departamento pop))