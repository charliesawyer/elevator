(ns elevator.core)

(def elevator
  "An elevator for illustration."
  {:floor 1 :direction :up :passengers 2 :stops #{2 3}})

(def direction?
  "Nil or the function to apply to :floor for direction :down or :up."
  {:down dec :up inc})

(def bottom
  "The elevator's bottom floor."
  1)

(def top
  "The elevator's top floor."
  10)

(defn move
  "Move an elevator subject to TOP and BOTTOM."
  [{:keys [floor direction]}]
  {:pre [(integer? floor) (direction? direction)]}
  (let [floor ((direction? direction) floor)]
    (cond (> floor top)    {:floor top    :direction :down}
          (< floor bottom) {:floor bottom :direction :up}
          :else            {:floor floor  :direction direction})))

(move elevator)

(comment

  (clojure.pprint/pprint (take 23 (iterate move elevator)))

  "... prints ..."

  ({:floor  1, :direction :up}
   {:floor  2, :direction :up}
   {:floor  3, :direction :up}
   {:floor  4, :direction :up}
   {:floor  5, :direction :up}
   {:floor  6, :direction :up}
   {:floor  7, :direction :up}
   {:floor  8, :direction :up}
   {:floor  9, :direction :up}
   {:floor 10, :direction :up}
   {:floor 10, :direction :down}
   {:floor  9, :direction :down}
   {:floor  8, :direction :down}
   {:floor  7, :direction :down}
   {:floor  6, :direction :down}
   {:floor  5, :direction :down}
   {:floor  4, :direction :down}
   {:floor  3, :direction :down}
   {:floor  2, :direction :down}
   {:floor  1, :direction :down}
   {:floor  1, :direction :up}
   {:floor  2, :direction :up}
   {:floor  3, :direction :up})

  "... in the REPL.")

(def building
  "A building with some elevators."
  {:elevators [{:floor 1 :direction :up   :passengers 1}
               {:floor 9 :direction :down :passengers 2}]
   :stops #{1 3}})

(def reverse-direction
  "Reverse an elevator's direction."
  {:down :up :up :down})

(defn do-in
  "Map m with f applied to value at ks."
  [f m ks]
  (assoc-in m ks (f (get-in m ks))))


(defn board
  "Add a passenger for stop to elevator."
  [elevator stop]
  (do-in (fn [stops] (conj stops stop))
         (do-in inc elevator [:passengers])
         [:stops]))

(board elevator 7)

(do-in inc building [:elevators 1 :floor])

(do-in reverse-direction building [:elevators 1 :direction])

(def movies #{{:title "Citizen Kane" :year 1941}
              {:title "Birth of a Nation" :year 1915}})

(:title (first (sort-by :year movies)))
