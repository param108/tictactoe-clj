(ns tictactoe.core
  (:gen-class)
  (:require  [org.httpkit.server :refer :all]
             [compojure.core :refer :all]
             [compojure.route :as route]
             [cheshire.core :refer :all]))



(defonce the-board (atom [[0 0 0] [0 0 0] [0 0 0]]))
(defonce current-player :x)
(defonce server (atom nil))

(defn updatexy
  "update position x y in board b in the board with o"
  [board x y o]
  (map-indexed
   (fn [index item]
     ( if (= index y)
      (assoc item x
             (if (= (get item x) 0)
               o
               (get item x)
               )
             )
      item
      )
     )
   board)
  )

(defn switch-player [player]
  (swap! player
         (fn [x]
           (if (= x :x) :o :x)))
  @player
  )



(defn move-impl [x y input-board input-player]
  ;;(swap! input-board (fn [board]
  ;;                     (updatexy board x y @input-player)))
  ;;(switch-player input-player)
  )

(defn move-handler [req]
  (let [x ( -> req :body :x)
        y ( -> req :body :y)]
    (println x y req)
    (move-impl x y the-board current-player)
    {:status  200
     :headers {"Content-Type" "application/json"}
     :body    (generate-string @the-board)}
    )
  )

(defroutes all-routes
  (PUT "/move" [] move-handler)
  (route/not-found "<p>Page not found.</p>"))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]

  (reset! server (run-server #'all-routes {:port 8080}))
  (println "Hello, World!"))


