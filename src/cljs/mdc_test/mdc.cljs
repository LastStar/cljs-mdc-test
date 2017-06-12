(ns mdc-test.mdc
  (:require [rum.core :as rum]))

(def ^:const fixed-toolbar :header.mdc-toolbar.mdc-toolbar--fixed)

(def ^:const toolbar-row :div.mdc-toolbar__row)

(def ^:const adjust-fixed-toolbar [:div.mdc-toolbar-fixed-adjust])

(def ^:const toolbar-section-start :section.mdc-toolbar__section.mdc-toolbar__section--align-start)

(def ^:const toolbar-section-end :section.mdc-toolbar__section.mdc-toolbar__section--align-end)

(def ^:const toolbar-title :span.mdc-toolbar__title)

(def ^:const layout-grid :div.mdc-layout-grid)

(def ^:const icon-menu [:a.material-icons "menu"])

(def ^:const icon-link :a.material-icons)

(def ^:const typo-display-3 :div.mdc-typography--display3)

(def ^:const typo-display-2 :div.mdc-typography--display2)

(def ^:const typo-display-1 :div.mdc-typography--display1)

(def ^:const typo-title :div.mdc-typography--title)

(def ^:const typo-body-1 :p.mdc-typography--body1)

(def ^:const typo-body-2 :p.mdc-typography--body2)

(def ^:const layout-cell-4 :div.mdc-layout-grid__cell.mdc-layout-grid__cell--span-4)

(def ^:const layout-cell-6 :div.mdc-layout-grid__cell.mdc-layout-grid__cell--span-6)

(def ^:const layout-cell-8 :div.mdc-layout-grid__cell.mdc-layout-grid__cell--span-8)

(def ^:const section-elevation-3 :section.mdc-elevation--z3)

(def ^:const section-elevation-9 :section.mdc-elevation--z9)

(def ^:const unordered-list :ul.mdc-list)

(def ^:const list-item :li.mdc-list-item)

(def ^:const dialog :aside.mdc-dialog)

(def ^:const dialog-surface :div.mdc-dialog__surface)

(def ^:const dialog-header :header.mdc-dialog__header)

(def ^:const dialog-header-title :h2.mdc-dialog__header__title)

(def ^:const dialog-body :div.mdc-dialog__body)

(def ^:const dialog-footer :footer.mdc-dialog__footer)

(def ^:const dialog-backdrop :div.mdc-dialog__backdrop)

(def ^:const form-field :div.mdc-form-field)

(def ^:const short-form-field :div.mdc-form-field.short)

(def ^:const select :select#country.mdc-select)

(defn- attach-textfield [state]
  (-> state :rum/react-component .-_reactInternalInstance .-_renderedComponent .-_hostNode js/mdc.textfield.MDCTextfield.attachTo)
  state)


(rum/defc textfield < {:did-mount attach-textfield}
  [opts input-name label]
  (let [id (keyword (str (name input-name) "-input"))]
    [:div.mdc-textfield
     [:input.mdc-textfield__input (merge {:id id :name input-name} opts)]
     [:label.mdc-textfield__label {:for input-name} label]]))


(defn- attach-ripple [state]
  (-> state :rum/react-component .-_reactInternalInstance .-_renderedComponent .-_hostNode js/mdc.ripple.MDCRipple.attachTo)
  state)


(rum/defc dialog-primary-button < {:did-mount attach-ripple}
  [opts label]
  [:button.mdc-button.mdc-button__dialog__footer_button.mdc-button--primary.mdc-button--accent opts label])


(rum/defc dialog-button < {:did-mount attach-ripple}
  [opts label]
  [:button.mdc-button.mdc-button__dialog__footer_button opts label])


(rum/defc primary-button < {:did-mount attach-ripple}
  [opts label]
  [:button.mdc-button.mdc-button--raised.mdc-button--primary.mdc-button--accent opts label])


(rum/defc button < {:did-mount attach-ripple}
  [opts label]
  [:button.mdc-button opts label])

(def attach-dialog
  {:did-mount
   (fn [state]
     (-> state :rum/react-component .-_reactInternalInstance .-_renderedComponent .-_hostNode js/mdc.dialog.MDCDialog.attachTo)
     state)})

(rum/defc checkbox [opts]
  [:div.mdc-checkbox
   [:input.mdc-checkbox__native-control
    opts]
   [:div.mdc-checkbox__background
    [:svg.mdc-checkbox__checkmark
     {:view-box "0 0 24 24"}
     [:path.mdc-checkbox__checkmark__path
      {:fill "none"
       :stroke "white"
       :d "M1.73,12.91 8.1,19.28 22.79,4.59"}]]]])
