(ns mdc-test.app
  (:require [rum.core :as rum]
            [mdc-test.views :as views])) 


(defn init []
  (rum/mount (views/page) (. js/document (getElementById "container"))))
