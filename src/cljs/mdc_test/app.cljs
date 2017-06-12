(ns mdc-test.app
  (:require [rum.core :as rum]
            [bide.core :as router]
            [potok.core :as ptk]
            [mdc-test.store :as store]
            [mdc-test.events :as events]
            [mdc-test.views :as views]))

(def ^:private routes
  (router/router [["/" :page/home]
                  ["/info" :page/info]]))

(defn init []
  (router/start!
   routes
   {:default     :showrum/index
    :on-navigate (fn [name params query]
                   (ptk/emit! store/main (events/->RouteMatched name params query)))})
  (rum/mount (views/page store/main) (. js/document (getElementById "container"))))
