(ns demoapp.core
  (:require [clojure.spec.alpha :as s]
            [clojure.spec.test.alpha :as st]
            [clojure.pprint :as p]))

(def bob
  {::firstname "foor"
   ::lastname  "bar"
   ;;::username "baz"
   ::age 30})

;;(s/def ::not-empty #(not (empty? %)))
(s/def ::firstname string?)
(s/def ::lastname string?)
(s/def ::username string?)
(s/def ::age int?)

(s/def ::person (s/keys :req [::firstname ::lastname ::username]
                        :opt [::age]))

(s/def ::people (s/coll-of ::person :kind vector? :distinct true))

(s/valid? ::person bob)
(s/conform ::person bob)
(s/explain ::person bob)

;; Excercise person spec
(s/exercise ::person 100)
;; Person generator
(s/gen ::person)

(s/exercise ::people)

(defn create-person
  [fname lname]
  {::firstname fname ::lastname lname})

(s/fdef create-person
        :args (s/cat :fname string? :lname string?)
        :ret ::person
        :fn #(s/valid? ::person %))

(s/exercise-fn `create-person)

;;(st/summarize-results)

;;(st/abbrev-result)
(first (st/check `create-person 100000))

(defn concat-people
  [& people
   people])

(s/fdef concat-people
        :args (s/+ ::person)
        :ret ::people
        :fn #(s/valid? ::people (:ret %)))

(s/exercise-fn `concat-people)
(p/pprint (st/check `concat-people))
