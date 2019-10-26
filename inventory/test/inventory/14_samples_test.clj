(ns inventory.core-test
  (:require [clojure.test :refer :all]
            [clojure.test.check.generators :as gen]
            [inventory.core :as i]))

(gen/sample gen/string-alphanumeric)

(defn f [a b] (/ a b))

(deftest test-f
  (is (= 1/2 (f 1 2)))
  (is (= 1/2 (f 3 6)))
  (is (= 1 (f 10 10))))
