(ns mdc-test.events
  (:require [potok.core :as ptk]))

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
