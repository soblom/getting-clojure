(ns inventory.core-test
  (:require [clojure.test :refer :all]
            [clojure.test.check :as tc]
            [clojure.test.check.generators :as gen]
            [clojure.test.check.properties :as prop]
            [clojure.test.check.clojure-test :as ctest]
            [inventory.core :as i]))

(def title-gen (gen/such-that not-empty gen/string-alphanumeric))

(def author-gen (gen/such-that not-empty gen/string-alphanumeric))

(def copies-gen (gen/such-that (complement zero?) gen/pos-int))

(def book-gen
  (gen/hash-map :title title-gen :author author-gen :copies copies-gen))

(def inventory-gen
  (gen/not-empty (gen/vector book-gen)))


(def inventory-and-book-gen
  (gen/let [inventory inventory-gen book (gen/elements inventory-gen)]
    {:inventory inventory :book book}))

(tc/quick-check 50
  (prop/for-all [i-and-b inventory-and-book-gen]
    (= (i/find-by-title (-> i-and-b :book :title)(:inventory i-and-b))
       (:book i-and-b))))

(ctest/defspec finds-by-title-finds-books 50
  (prop/for-all [i-and-b inventory-and-book-gen]
    (= (i/find-by-title (-> i-and-b :book :title)(:inventory i-and-b))
       (:book i-and-b))))

(ctest/defspec more-complex-spec 10000
  (prop/for-all [a gen/pos-int b gen/pos-int]
    (is (= (* b (f a b)
            a)))))
