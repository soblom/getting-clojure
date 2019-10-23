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

§
