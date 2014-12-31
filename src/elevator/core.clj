(ns elevator.core)

(def elevator
  "An elevator for illustration."
  {:floor 1 :direction :up})

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
