(ns blottsbooks.core)

(defrecord FictionalCharacter[name appears-in author])

(def watson
  (->FictionalCharacter "John Watson" "Sign of the Four" "Doyle"))

(def elizabeth
  (map->FictionalCharacter {:name "Elizabeth Bennet"
                            :appears-in "Pride and Prejudice"
                            :author "Austen"}))

(def specific-watson (assoc watson :appears-in "Sign of the REAL four"))
(def more-about-watson (assoc watson :address "221B Baker Street"))

(defrecord SuperComputer [cpu num-cpus storage-gb])
(def watson-2 (->SuperComputer "Power7" 2880 4000))

(defrecord Employee [first-name last-name department])
(def alice (->Employee "Alice" "Smith" "Engineering"))
(defprotocol Person
  (full-name [this])
  (greeting [this msg])
  (description [this]))

(defrecord FictionalCharacter [name appears-in author]
  Person
  (full-name [this] (:name this))
  (greeting [this msg] (str msg " " (:name this)))
  (description [this]
    (str (:name this) " is a character in " (:appears-in this))))

(defrecord Employee [first-name last-name department]
  Person
  (full-name [this] (str first-name " " last-name))
  (greeting [this msg] (str msg " " (:first-name this)))
  (description [this]
    (str (:first-name this) " works in " (:department this))))

(def sofia (->Employee "Sofia" "Diego" "Finance"))
(def sam (->FictionalCharacter "Sam Weller" "The Pickwick Papers" "Dickens"))

(defprotocol Marketable
  (make-slogan [this]))

(extend-protocol Marketable
  Employee
    (make-slogan [e] (str (:first-name e) " is the BEST employee"))
  FictionalCharacter
    (make-slogan [fc] (str (:name fc " is the GREATEST character")))
  SuperComputer
    (make-slogan [sc] (str "This SuperComputer has " (:num-cpus sc) " CPUs")))

(extend-protocol Marketable
  String
    (make-slogan [s] (str \' s\' "is a string!"))
  Boolean
    (make-slogan [b] (str "This sentence is " b)))

(defprotocol Lifecycle
  (start [component])
  (stop [component]))

(def test-component
  (reify Lifecycle
         (start [this] (println "Start!") this)
         (stop [this] (println "Stop!") this)))

(map->FictionalCharacter {:full-name "EB"
                          :book "P&P"
                          :written-by "A"})
