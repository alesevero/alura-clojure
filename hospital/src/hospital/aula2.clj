(ns hospital.aula3
  (:use [clojure pprint])
  (:require [hospital.logic :as h.logic]
            [hospital.model :as h.model]))

; root binding (global): simbolo que qualquer thread que acessar esse namespace 
; vai ter acesso a ele com o valor padrão definido
(def nome "alexandre")

; é possível redefinir (com qualquer coisa!)
(def nome 1234)


(let [nome "alexandre"]
  (println nome)
  (let [nome "daniel"]
    ; SHADOWING: criando um simbolo local que "esconde" um simbolo de escopo maior
    (println nome))
  (println nome))

; clojure is able to handle all swaps concurrently
(defn testa-atom
  []
  (let [hospital-silveira (atom {:espera h.model/fila-vazia})]
    (println hospital-silveira)
    (pprint hospital-silveira)
    ; deref or @: access the value inside the atom (not to mutate it!)
    (pprint (deref hospital-silveira))
    ; mesma coisa que:
    (pprint @hospital-silveira)
    (pprint (assoc @hospital-silveira :laboratorio1 h.model/fila-vazia))
    (pprint @hospital-silveira)
    ; swap!...swaps the value of atoms for another thing
    ; ! is used in functions with side-effects
    (swap! hospital-silveira assoc :laboratorio1 h.model/fila-vazia)
    (pprint @hospital-silveira)
    (swap! hospital-silveira assoc :laboratorio2 h.model/fila-vazia)
    (pprint @hospital-silveira)
    ; update imutável com dereferencia, sem side-effects
    (update @hospital-silveira :laboratorio1 conj "111")
    ; update com swap!
    (swap! hospital-silveira update :laboratorio1 conj "111")
    (pprint @hospital-silveira)))

(testa-atom)

(defn chega-em-malvado!
  [hospital pessoa]
  ; swap dereference and puts as first argument of function
  (swap! hospital h.logic/chega-em :espera pessoa)
  (println "apos inserir" pessoa)
  (pprint @hospital))

(defn simula-um-dia-em-paralelo
  []
  (let [hospital (atom (h.model/novo-hospital))]
    (.start (Thread. (fn [] (chega-em-malvado! hospital "111"))))
    (.start (Thread. (fn [] (chega-em-malvado! hospital "222"))))
    (.start (Thread. (fn [] (chega-em-malvado! hospital "333"))))
    (.start (Thread. (fn [] (chega-em-malvado! hospital "444"))))
    (.start (Thread. (fn [] (chega-em-malvado! hospital "555"))))
    (.start (Thread. (fn [] (chega-em-malvado! hospital "666"))))))

(simula-um-dia-em-paralelo)