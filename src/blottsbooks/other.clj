

;; Functional Things

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



;; recursion and such

(ns blottsbooks.core
  (:gen-class))

(def books
  [{:title "Jaws" :copies-sold 2000000}
   {:title "Emma" :copies-sold 3000000}
   {:title "2001" :copies-sold 4000000}])

(defn sum-copies
  ([books] (sum-copies books 0))
  ([books total]
      (if (empty? books)
        total
      (sum-copies (rest books) (+ total (:copies-sold (first books)))))))

(defn sum-copies-recur
  ([books] (sum-copies-recur books 0))
  ([books total]
      (if (empty? books)
        total
        (recur (rest books) (+ total (:copies-sold (first books)))))
  )
)

(defn sum-copies-loop [books]
  (loop [books books total 0]
    (if (empty? books)
      total
      (recur (rest books) (+ total (:copies-sold (first books)))))))

(defn sum-copies-map "Using map here" [books] (apply + (map :copies-sold books)))

(defn sum-copies-recur-switched
  ([books] (sum-copies-recur-switched 0 books))
  ([total books]
      (if (empty? books)
        total
      (recur (+ total (:copies-sold (first books))) (rest books)))))

(defn -main
  "empty"
  [& args]
  (println "empty vector:" (sum-copies []))
  (println "predefinied books:" (sum-copies books))

  (println "\nAND optimised...")
  (println "empty vector:" (sum-copies []))
  (println "predefinied books:" (sum-copies books))

  (println "\nAND with loop...")
  (println "empty vector:" (sum-copies-loop []))
  (println "predefinied books:" (sum-copies-loop books))

  (println "\nAND with map...")
  (println "empty vector:" (sum-copies-map []))
  (println "predefinied books:" (sum-copies-map books))

  (println "\nAND switched...")
  (println "empty vector:" (sum-copies []))
  (println "predefinied books:" (sum-copies books))
)


;; multi-method examples

(defn shipping-charge[preferred-customer order-amount]
  (if preferred-customer
    (do
      (println "preferred-customer, free shipping")
      0.0)
    (do
      (println "Regular customer, shipping costs of apply")
      (* order-amount 0.1))))

(defmulti book-description :genre)

(defmethod book-description :romance [book]
  (str "the heartwarming new romance by " (:author book)))

(defmethod book-description :zombie [book]
  (str "the heart stopping undead horror by " (:author book)))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (def books
    [{:title "Pride and Prejudice" :author "Austen" :genre :romance}
     {:title "World War Z" :author "Brooks" :genre :zombie }])
  (println (book-description (first books)))
  (println (book-description (last books)))
)
