(in-ns 'inventory.core)

(println "In 14-tests")

;;:title :author :copies

(defn find-by-title
  "Search for a book by title, where title is a string and books is a
  collection of book maps, each of which must have a :title entry"
  [title books]
  (some #(when (= (:title %) title) %) books))

(defn number-of-copies
  "Return the number of copies in inventory of the given title, where title is
  a string and books is a collection of book maps each of which must have a
  :title entry"
  [title books]
  (:copies (find-by-title title books)))
