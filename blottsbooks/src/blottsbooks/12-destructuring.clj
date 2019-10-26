(ns blottsbooks.core
  (:gen-class))

(def artists [:monet :austen])

(let [painter (first artists)
      novelist (second artists)]
  (println "The painter is" painter "and the novelist is" novelist))

(let [[painter novelist] artists]
  (println "The painter is" painter "and the novelist is" novelist))

(def artists [:monet :austen :beethoven :dickinson])

(let [[painter novelist composer poet] artists]
  (println "The painter is" painter
           "and the novelist is" novelist
           "and the composer is" composer
           "and the poet is" poet))

(let [[_ _ composer poet] artists]
  (println "The composer is" composer
           "and the poet is" poet))

(def artist-list '(:monet :austen :beethoven :dickinson))

(let [[_ _ composer] artist-list]
  (println "the composer is" composer))

(defn artist-description [[novelist poet]]
  (println "the novelist is " novelist
           "and the poet is " poet))

(defn artist-description [shout [novelist poet]]
  (let [msg (str "the novelist is" novelist
        "and the poet is" poet)]
    (if shout (.toUpperCase msg) msg)))

(def artist-map {:painter :monet :novelist :austen})

(let [{painter :painter novelist :novelist} artist-map]
  (println "The painter is" painter
           "and the novelist is" novelist)))

(def austen {:name "Jane Austen"
             :parents {:father "George"
                       :mother "Cassandra"}
             :dates   {:born 1775
                       :died 1817}})

(let [{{dad :father mom :mother} :parents} austen]
  (println "Who's the daddy?" dad
           "\nWho's the mommy?" mom))

;; Jane's name, mother's name, mothers year of birth

(let [{name :name
      {mom :mother} :parents
      {dob :born} :dates}  austen]
  (println ))


(def author {:name "Jane Austen"
             :books [{:title "Sense and Sensibility" :published 1811}
                     {:title "Emma" :published 1815}]})

(let [{name :name [_ book] :books} author]
  (println "Name of Author" name "A book she wrote" book))

(defn add-greeting [character]
  (let [{:keys [name age]} character]
  (assoc character :greeting (str "Name: " name " age: " age))))

(defn add-greeting [{:keys [name age] :as character}]
  (assoc character :greeting (str "Name: " name " ,age: " age)))
