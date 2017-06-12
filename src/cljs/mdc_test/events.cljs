(ns mdc-test.events
  (:require [potok.core :as ptk]
            [beicon.core :as rxt]))

(defrecord ShowRegistration []
  ptk/UpdateEvent
  (update [_ state]
    (assoc state :ui/show-registration true))
  ptk/EffectEvent
  (effect [_ _ _]
    (-> js/document.body .-classList (.add "mdc-dialog-scroll-lock"))))


(defrecord HideRegistration []
  ptk/UpdateEvent
  (update [_ state]
    (assoc state :ui/show-registration false))
  ptk/EffectEvent
  (effect [_ _ _]
    (-> js/document.body .-classList (.remove "mdc-dialog-scroll-lock"))))


(defn- all-present? [vals]
  (not (some #(or (nil? %) (false? %) (and (string? %) (empty? %))) vals)))

(defrecord SubmitRegistration []
  ptk/UpdateEvent
  (update [_ state]
    (if (all-present? (vals (:input/value state)))
      state
      (assoc state :ui/registration-error "All fields are required")))
  ptk/WatchEvent
  (watch [_ state _]
    (if (all-present? (vals (:input/value state)))
      (rxt/just (->HideRegistration))
      (rxt/empty))))


(deftype RouteMatched [name params query]
  ptk/UpdateEvent
  (update [_ state]
    (assoc state :page/current name)))


(defrecord InputChanged [id value]
  ptk/UpdateEvent
  (update [_ state]
    (assoc-in state [:input/value id] value)))
