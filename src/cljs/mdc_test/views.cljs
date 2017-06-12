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
     [mdc/toolbar-title [:a {:href "#/"} "MDC Test"]]]
    [mdc/toolbar-section-end
     [mdc/icon-link {:href "#" :on-click #(ptk/emit! store (events/->ShowRegistration))}
      "person_add"]
     [mdc/icon-link {:href "#/info"} "info_outline"]]]])


(rum/defc registration-dialog < rum/reactive mdc/attach-dialog [store]
  (let [state (rxt/to-atom store)
        show-registration (-> state (rum/cursor :ui/show-registration) rum/react)
        values (-> state (rum/cursor :input/value) rum/react)
        input-changer (fn [id]
                        (fn [ev]
                          (ptk/emit!
                           store (events/->InputChanged id (-> ev .-target .-value)))))
        checkbox-changer (fn [id]
                           (fn [ev]
                             (ptk/emit!
                              store (events/->InputChanged id (-> ev .-target .-checked)))))]
    (js/console.log values)
    [mdc/dialog
     {:class (when show-registration :mdc-dialog--open)}
     [mdc/dialog-surface
      [mdc/dialog-header
       [mdc/dialog-header-title "Registration Form"]]
      [mdc/dialog-body
       [:form
        {:on-submit #(.preventDefault %)}
        [mdc/form-field
         (mdc/textfield
          {:required true
           :auto-focus true
           :value (:email values)
           :on-change (input-changer :email)}
          :email "Email")]
        [mdc/form-field
         (mdc/textfield
          {:required true
           :value (:name values)
           :on-change (input-changer :name)}
          :name "Name")]
        [mdc/form-field
         (mdc/textfield
          {:required true
           :value (:street values)
           :on-change (input-changer :street)}
          :street "Street")]
        [mdc/form-field
         (mdc/textfield
          {:required true
           :value (:city values)
           :on-change (input-changer :city)}
          :city "City")]
        [mdc/short-form-field
         (mdc/textfield
          {:required true
           :value (:zip values)
           :on-change (input-changer :zip)}
          :zip "Zip")]
        [mdc/short-form-field
         [mdc/select
          {:id :country
           :value (:country values)
           :on-change (input-changer :country)}
          [:option {:default true :value "nil"} "Choose Country"]
          [:option {:value "cz"} "Czech Republic"]
          [:option {:value "sk"} "Slovak Republic"]]]
        [mdc/form-field
         (mdc/checkbox {:id :eula
                        :type :checkbox
                        :checked (:eula values)
                        :on-click (checkbox-changer :eula)})
         [:label {:for "eula"} "I read and understand EULA"]]]] 
      [mdc/dialog-footer
       (mdc/dialog-primary-button {:on-click #(ptk/emit! store (events/->SubmitRegistration))}
                                  "Register")
       (mdc/dialog-button {:on-click #(ptk/emit! store (events/->HideRegistration))}
                          "Later")]]
     [mdc/dialog-backdrop
      {:on-click #(ptk/emit! store (events/->HideRegistration))}]]))



(rum/defc home [store]
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
    [mdc/typo-body-1 "Pellentesque dapibus suscipit ligula.  Donec posuere augue in quam.  Etiam vel tortor sodales tellus ultricies commodo.  Suspendisse potenti.  Aenean in sem ac leo mollis blandit.  Donec neque quam, dignissim in, mollis nec, sagittis eu, wisi.  Phasellus lacus.  Etiam laoreet quam sed arcu.  Phasellus at dui in ligula mollis ultricies.  Integer placerat tristique nisl.  Praesent augue.  Fusce commodo.  Vestibulum convallis, lorem a tempus semper, dui dui euismod elit, vitae placerat urna tortor vitae lacus.  Nullam libero mauris, consequat quis, varius et, dictum id, arcu.  Mauris mollis tincidunt felis.  Aliquam feugiat tellus ut neque.  Nulla facilisis, risus a rhoncus fermentum, tellus tellus lacinia purus, et dictum nunc justo sit amet elit. "]]])


(rum/defc info []
  [mdc/layout-cell-8
   [mdc/typo-body-1 "Some more info"]])

(rum/defc page < rum/reactive [store]
  (let [state (rxt/to-atom store)
        current-page (-> state (rum/cursor :page/current) rum/react)]
    [:div
     (toolbar store)
     [:main
      mdc/adjust-fixed-toolbar
      (registration-dialog store)
      [:div.hero
       [mdc/typo-display-3 "Material Design Components"]]
      [mdc/layout-grid
       (case current-page
         :page/home (home store)
         :page/info (info))
       [mdc/layout-cell-4
        [mdc/section-elevation-3
         {:class :texts}
         [mdc/typo-title "Aliquam erat"]
         [mdc/unordered-list
          [mdc/list-item "Proin"]
          [mdc/list-item "Massa"]
          [mdc/list-item "Neque"]
          [mdc/list-item "Gravida"]
          (mdc/primary-button {:on-click #(ptk/emit! store (events/->ShowRegistration))} "Start registration")]]]]]
     [:footer.page "Material Design Components"]]))
