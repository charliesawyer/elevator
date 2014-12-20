(ns elevator.core)

(def top 10)
(def bottom 1)

(def down 0)

(def direction? #{:down :up})

(def elevator {:floor 1 :direction :up})

(defn move
  [{:keys [floor direction]}]
  {:pre [(integer? floor) (direction? direction)]}
  (let [floor ((direction {:up inc :down dec}) floor)
        [floor direction] (cond (> floor top) [top :down]
                                (< floor bottom) [bottom :up]
                                :else [floor direction])]
    {:floor floor :direction direction}))

(move elevator)
{:floor 2, :direction :up}

(move {:floor 9, :direction :up})
(move {:floor 10, :direction :up})
(move {:floor 1, :direction :down})
{:floor 1, :direction :up}
{:floor 10, :direction :down}

(move {:floor 7 :direction :fnord})
