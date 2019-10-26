(ns inventory.core-test
  (:require [clojure.test :refer :all]
            [inventory.core :as i]))

(def books
  [{:title "2001" :author "Clarke" :copies 21}
   {:title "Emma" :author "Austen" :copies 10}
   {:title "Misery" :author "King" :copies 101}])

(deftest test-finding-books
  (is (not (nil? (i/find-by-title "Emma" books))))
  (is nil? (i/find-by-title "XYZ" books)))

(deftest test-basic-inventory
  (testing "Finding Books"
    (is (not (nil? (i/find-by-title "Emma" books))))
    (is nil? (i/find-by-title "XYZ" books)))
  (testing "Copies in inventory")
  (is (= 10 (i/number-of-copies "Emma" books))))

(load "14_samples_test")
(load "core_test_gen")
