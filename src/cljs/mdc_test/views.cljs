(ns mdc-test.views
  (:require [rum.core :as rum]
            [beicon.core :as rxt]
            [potok.core :as ptk]
            [mdc-test.events :as events]
            [mdc-test.mdc :as mdc]))

(rum/defc toolbar < rum/reactive [store]
  [mdc/fixed-toolbar
   [mdc/toolbar-row
    [mdc/toolbar-section-start
     [mdc/toolbar-title "MDC Test"]]
    [mdc/toolbar-section-end
     [mdc/icon-link {:href "#" :on-click #(ptk/emit! store (events/->ShowRegistration))}
      "account_circle"]
     [mdc/icon-link {:href "#/info"} "info"]]]])


(rum/defc registration-dialog < rum/reactive [store]
  (let [state (rxt/to-atom store)
        show-registration (-> state (rum/cursor :ui/show-registration) rum/react)]
    [:aside.mdc-dialog
     {:class (when show-registration "mdc-dialog--open")}
     [:div.mdc-dialog__surface
      [:header.mdc-dialog__header
       [:h2.mdc-dialog__header__title "Registration Form"]]
      [:div.mdc-dialog__body
       [:form
        {:on-submit #(.preventDefault %)}
        [:div.textfield-wrapper
         (mdc/textfield {:on-change #(-> % .-target .-value js/console.log)} :email "Email")
         (mdc/textfield {} :name "Name")]
        [:div.textfield-wrapper
         (mdc/textfield {:on-change #(-> % .-target .-value js/console.log)} :street "Street")
         (mdc/textfield {} :city "City")
         (mdc/textfield {} :zip "Zip")]]]
      [:footer.mdc-dialog__footer
       (mdc/dialog-primary-button "Register")
       (mdc/dialog-button {:on-click #(ptk/emit! store (events/->HideRegistration))}
                          "Later")]]
     [:div.mdc-dialog__backdrop
      {:on-click #(ptk/emit! store (events/->HideRegistration))}]]))



(rum/defc texts [store]
  [mdc/layout-grid
   [mdc/layout-cell-8
    [:section
     {:class :texts}
     [mdc/typo-display-2 "What?"]
     [mdc/typo-body-1 "Here I am trying to try how the new Material Design Components works on the standart webpage, used everywhere"]
     [mdc/typo-body-1 "Lorem ipsum dolor sit amet, consectetuer adipiscing elit.  Donec hendrerit tempor tellus.  Donec pretium posuere tellus.  Proin quam nisl, tincidunt et, mattis eget, convallis nec, purus.  Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.  Nulla posuere.  Donec vitae dolor.  Nullam tristique diam non turpis.  Cras placerat accumsan nulla.  Nullam rutrum.  Nam vestibulum accumsan nisl. "]
     [mdc/typo-display-2 "Donec at pede."]
     [mdc/typo-body-1 "Aliquam erat volutpat.  Nunc eleifend leo vitae magna.  In id erat non orci commodo lobortis.  Proin neque massa, cursus ut, gravida ut, lobortis eget, lacus.  Sed diam.  Praesent fermentum tempor tellus.  Nullam tempus.  Mauris ac felis vel velit tristique imperdiet.  Donec at pede.  Etiam vel neque nec dui dignissim bibendum.  Vivamus id enim.  Phasellus neque orci, porta a, aliquet quis, semper a, massa.  Phasellus purus.  Pellentesque tristique imperdiet tortor.  Nam euismod tellus id erat."]
     [mdc/typo-body-2 "Proin neque massa, cursus ut, gravida ut, lobortis eget, lacus.  "]
     [mdc/typo-display-1 "Aenean in sem ac leo mollis blandit.  "]
     [mdc/typo-body-1 "Lorem ipsum dolor sit amet, consectetuer adipiscing elit.  Donec hendrerit tempor tellus.  Donec pretium posuere tellus.  Proin quam nisl, tincidunt et, mattis eget, convallis nec, purus.  Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.  Nulla posuere.  Donec vitae dolor.  Nullam tristique diam non turpis.  Cras placerat accumsan nulla.  Nullam rutrum.  Nam vestibulum accumsan nisl."]
     [mdc/typo-display-1 "Etiam vel tortor sodales tellus ultricies commodo."]
     [mdc/typo-body-1 "Pellentesque dapibus suscipit ligula.  Donec posuere augue in quam.  Etiam vel tortor sodales tellus ultricies commodo.  Suspendisse potenti.  Aenean in sem ac leo mollis blandit.  Donec neque quam, dignissim in, mollis nec, sagittis eu, wisi.  Phasellus lacus.  Etiam laoreet quam sed arcu.  Phasellus at dui in ligula mollis ultricies.  Integer placerat tristique nisl.  Praesent augue.  Fusce commodo.  Vestibulum convallis, lorem a tempus semper, dui dui euismod elit, vitae placerat urna tortor vitae lacus.  Nullam libero mauris, consequat quis, varius et, dictum id, arcu.  Mauris mollis tincidunt felis.  Aliquam feugiat tellus ut neque.  Nulla facilisis, risus a rhoncus fermentum, tellus tellus lacinia purus, et dictum nunc justo sit amet elit. "]]]
   [mdc/layout-cell-4
    [mdc/section-elevation-3
     {:class :texts}
     [mdc/typo-title "Aliquam erat"]
     [mdc/unordered-list
      [mdc/list-item "Proin"]
      [mdc/list-item "Massa"]
      [mdc/list-item "Neque"]
      [mdc/list-item "Gravida"]]
     (mdc/primary-button {:on-click #(ptk/emit! store (events/->ShowRegistration))} "Start registration")]]])


(rum/defc page < rum/reactive [store]
  [:div
   (toolbar store)
   [:main
    mdc/adjust-fixed-toolbar
    (registration-dialog store)
    [:div.hero
     [mdc/typo-display-3 "Material Design Components"]]
    (texts store)]
   [:footer.page "Material Design Components"]])
