(ns loja.aula2)

(defn count-size
  [total-ate-agora elementos]
  (let [primeiro (first elementos)]
    (if (not (nil? primeiro))
      (recur (inc total-ate-agora) (rest elementos))
      total-ate-agora)))

(def names ["daniela" "guilherme" "carlos" "paulo" "lucia" "ana"])

(count-size 0 names)

; poly functions
; Ad-hoc polymorphism-ish
(defn poly
  ([one-param] (println "one" one-param))
  ([one-param two-param] (println "two" one-param two-param)))

(poly 1)
(poly 1 2)


(defn count-size
  ([elementos]
   (count-size 0 elementos))

  ([total-ate-agora elementos]
  (let [primeiro (first elementos)]
    (if (not (nil? primeiro))
      (recur (inc total-ate-agora) (rest elementos))
      total-ate-agora))))

(count-size 0 names)
(count-size names)


(defn count-size
  [elementos]
  (loop [total-ate-agora 0
         elementos-restantes elementos]
    (if (seq elementos-restantes)
      (recur (inc total-ate-agora) (next elementos-restantes))
      total-ate-agora)))

(count-size names)
