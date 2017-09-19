(ns demoapp.events
  (:require [re-frame.core :as re-frame]
            [demoapp.db :as db]))

(re-frame/reg-event-db
 :initialize-db
 (fn  [_ _]
   db/default-db))


(re-frame/reg-event-db
 :toggle-flag
 (fn  [db _]
   (let [new-value (not (:flag db))]
     (assoc db :flag new-value))))
