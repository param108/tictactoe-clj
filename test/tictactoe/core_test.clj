(ns tictactoe.core-test
  (:require [clojure.test :refer :all]
            [tictactoe.core :refer :all]))

(deftest test-updatexy
  (testing "Makes sure we can update [x,y] with :x"
    (let [input [[0 0 0] [0 0 0] [0 0 0 ]]
          x 0
          y 1
          value :x
          expected [[0 0 0] [:x 0 0] [0 0 0]]
          ]
      (is (= expected (updatexy input x y value))))
    )
  (let [input [[0 0 0] [0 0 0] [0 0 0 ]]
        x 1
        y 0
        value :o
        expected [[0 :o 0] [0 0 0] [0 0 0]]
        ]
    (is (= expected (updatexy input x y value)))
    )
  (testing "Make sure we can update only empty cell"
    (let [input [[:x 0 0] [0 0 0] [0 0 0 ]]
          x 0
          y 0
          value :o
          expected [[:x 0 0] [0 0 0] [0 0 0]]
          ]
      (is (= expected (updatexy input x y value))))
    )
  )

(deftest test-switch-player
  (testing "Switch player to :o when the player is :x"
    (let [c (atom :x)
          expected  :o]
         (switch-player c)
      (is (= expected  @c))
      )
    )
  )
