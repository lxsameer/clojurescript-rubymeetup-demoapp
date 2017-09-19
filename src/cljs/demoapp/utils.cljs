(ns demoapp.utils)

(defn button [label on-click]
  [:button {:on-click on-click} label])
