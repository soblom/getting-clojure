(ns blottsbooks.core
  (:gen-class))

(def dracula {:title "Dracula"
              :price 1.99
              :genre :horror})

(defn for-dracula [f] (f dracula))

(defn cheap? [book] (when (< (:price book) 9.99) book))
(defn pricey? [book] (when (>= (:price book) 9.99) book))

(defn cheaper-f [max-price]
  (fn [book]
    (when (<= (:price book) max-price)
    book)))

(def real-cheap? (cheaper-f 1.00))
(def kind-of-cheap? (cheaper-f 1.99))

(defn cheaper-than [max-price book]
  (when (<= (:price book) max-price) book))

(def real-cheap-partial? (partial cheaper-than 1.00))
(def kind-of-cheap-partial? (partial cheaper-than 1.99))

(defn -main
  "empty"
  [& args]

  (println "Basic Stuff")
  (println (for-dracula cheap?))
  (println (for-dracula pricey?))

  (println "\nLambda")
  (println (for-dracula kind-of-cheap?))
  (println (for-dracula real-cheap?))

  (println "\nApply")
  (println (apply + [1 2 3 4]))

  (println "\nPartial")
  (println (for-dracula kind-of-cheap-partial?))
  (println (for-dracula real-cheap-partial?))

  (println (update-in dracula [:price] + 1.00))
  (println (assoc-in dracula [:price] 2.99))
)
