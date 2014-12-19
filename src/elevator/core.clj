(ns elevator.core)

(def max 10)
(def min 1)

(def down 0)

(def elevator {:floor 1 :direction :up})

(defn move
  [el]
  (let [was (:direction el)
        how (was {:up + :down -})
        floor (how 1 (:floor el))
        [floor now] (cond (> floor max) [max :down]
                          (< floor min) [min :up]
                          :else [floor was])]
    {:how how :floor floor}
    {:floor floor :direction now}))

(move elevator)
{:floor 2, :direction :up}

(move {:floor 9, :direction :up})
(move {:floor 10, :direction :up})
(move {:floor 1, :direction :down})
{:floor 1, :direction :up}
{:floor 10, :direction :down}

