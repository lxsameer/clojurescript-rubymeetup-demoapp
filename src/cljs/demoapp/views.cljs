(ns demoapp.views
  (:require [re-frame.core :as re-frame]
            [demoapp.utils :as utils]))

(defn main-panel []
  (let [name (re-frame/subscribe [:name])
        flag (re-frame/subscribe [:flag])]
    (fn []
      (if @flag
        [:h2 "Hello " @name]
        [utils/button "click me" #(re-frame/dispatch [:toggle-flag])]))))
