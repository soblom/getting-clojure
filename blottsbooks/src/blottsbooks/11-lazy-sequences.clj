;; Lazy Sequences
(ns blottsbooks.core
  (:gen-class))

(def jack "All work and no play makes Jack a dull boy")

(def repeated-jack (repeat jack))

(def numbers (iterate inc 1))

(def evens (map #(* 2 %) (iterate inc 1)))

(def test-books
  [{:author "Bob Jordan", :title "Wheel of Time, Book 1"}
   {:author "Jane Austen", :title "Wheel of Time, Book 2"}
   {:author "Chuck Dickens", :title "Wheel of Time, Book 3"}
   {:author "Leo Tolstoy", :title "Wheel of Time, Book 4"}
   {:author "Bob Poe", :title "Wheel of Time, Book 5"}
   {:author "Jane Jordan", :title "Wheel of Time, Book 6"}
   {:author "Chuck Austen", :title "Wheel of Time, Book 7"}])

(def numbers [1 2 3])

(def trilogy (map #(str "Wheel of Time, Book " %) numbers))

(def numbers (iterate inc 1))
(def titles (map #(str "Wheel of Time, Book " %) numbers))

(def first-names ["Bob" "Susan" "Jack" "Nicole" "Leif"])
(def last-names ["Miller" "Da Santos" "Hubner" "Stark" "Olderson" "Benson"])
(defn combine-first-last [fname lname]
  (str fname " " lname))

(def authors
  (map combine-first-last
    (cycle first-names)
    (cycle last-names)))

(defn make-book [title author]
  {:author author :title title})

(def test-books (map make-book titles authors))

(defn chatty-vector []
  (println "Here we go")
  [1 2 3])

(def s (lazy-seq (chatty-vector)))

(defn my-repeat [x]
  (cons x (lazy-seq (my-repeat x))))

(defn my-iterate [f x]
  (cons x (lazy-seq (my-iterate f (f x)))))

(defn my-map [f col]
  (when-not (empty? col)
    (cons (f (first col))
      (lazy-seq (my-map f (rest col))))))

(def chapters
  (take 10
    (map slurp
      (map #(str "../book-examples/lazy/chap" % ".txt") numbers))))

(doseq [c chapters]
  (println "The chapter text is" c))


(println
  (first repeated-jack) "\n"
  (nth repeated-jack 10) "\n"
  (take 10 repeated-jack) "\n"
  (nth numbers 10) "\n"
  (first numbers) "\n"
  (take 20 (take 10000000 (iterate inc 1))) "\n"
  (take 20 evens) "\n")
