;; Sequences

(ns blottsbooks.core
  (:gen-class))

(def title-seq (seq ["Emma" "Oliver Twist" "Robinson Crusoe"]))

(defn my-count [col]
  (let [the-seq (seq col)]
    (loop [n 0 s the-seq]
      (if (seq s)
        (recur (inc n) (rest s))
      n))))

(def titles ["Jaws" "Jabberwocky" "Heat" "Dracula" "2001"])

(def some-numbers [1, 53, 811])

(def books
  [{:title "Deep Six" :price 13.99 :genre :sci-fi :rating 6}
   {:title "Dracula" :price 1.99 :genre :horror :rating 7}
   {:title "Emma" :price 7.99 :genre :comedy :rating 9}
   {:title "2001" :price 10.50 :genre :sci-fi :rating 5}])

(defn cheap? [book]
 (when (<= (:price book) 9.99)
   book))

(def count-title (comp count :title))

(def some-numbers [10 20 30 40 50])
(defn add2 [a b] (+ a b))

(defn hi-price [max book]
  (if (>= (:price book) max)
    (:price book)
    max))

(defn top-rated-titles [books]
  (interpose
    "//"
    (map :title (take 3 (reverse (sort-by :rating books))))))

(require '[clojure.java.io :as io])

(defn listed-author? [author]
  (with-open [r (io/reader "./src/blottsbooks/authors.txt")]
    (some (partial = author) (line-seq r))))

(defn -main
  "empty"
  [& args]
  (println (my-count [12 11 10 9 8 7]))
  (println (my-count #{12 11 10 9 8 7}))

  (println (my-count {:key1 "one" :key2 "two"}))
  (println (cons 0 #{1 2 3}))

  (println (map #(* 2 %) some-numbers))
  (println (map :title (filter cheap? books)))

  ;;how long are the book titles?
  (println (map (fn [book] (count (:title book))) books))
  (println (map (comp count :title) books))
  (println (map count-title books))

  (println (reduce add2 0 some-numbers))
  (println (reduce + some-numbers))

  (println (reduce hi-price 0 books))

  (println (take 3 (reverse (sort-by :title books))))
  (println (map :title (take 3 (reverse (sort-by :title books)))))
  (println (top-rated-titles books))

  (println (listed-author? "Clarke"))
  (println (listed-author? "Blom"))
)
