(ns mdc-test.store
  (:require [potok.core :as ptk]))


(def initial-state
  {:page/current :page/home
   :input/value
   {:email nil
    :name nil
    :street nil
    :city nil
    :zip nil
    :country nil
    :eula nil}})

(def main (ptk/store {:state initial-state}))

