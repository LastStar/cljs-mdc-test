(ns mdc-test.app
  (:require [rum.core :as rum]
            [mdc-test.store :as store]
            [mdc-test.views :as views])) 


(defn init []
  (rum/mount (views/page store/main) (. js/document (getElementById "container"))))
