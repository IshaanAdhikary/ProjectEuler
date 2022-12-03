This problem can be treated recursively. Logically, each combination amount is equivalent to the total of the amount of combinations without the coin at all for the toal and the amount of combinations where at least one coin is used to get to the target. So, each combination amount is equivalent to the combinations using just other coins at the same target, along with finding the combinations using the coin at the target minus the coin's value.
An easy example is with a table:
   Pence:   0   1   2   3   4   5   6   7   8   9   10
No coins:   1   0   0   0   0   0   0   0   0   0   0
1p      :   1   1   1   1   1   1   1   1   1   1   1
These are easy, as using only 1p coins there is only one possible combination. For the 2p line, you can replace any 2 1p coins with 1 2p coin. So, every 2p, you can add one more combination to the total.
1p, 2p  :   1   1   2   2   3   3   4   4   5   5   6
Now, we can use the logic above. For example, at 5p, we get 3 + 1 = 4, and at 7p we get 4 + 2 = 6.
1p,2p,5p:   1   1   2   2   3   4   5   6   7   8   9
In essence, each time you add 1p, you add new combinations as described in the first paragraph.
Since we never need to look back more than one level, we do not need to save earlier rows of the graph. Therefore, we can use a dynamic 1-dimensional array to represent the chart instead of filling out a 2-dimensional array.
Repeat this process with a 1p, 2p, 5p, 10p, 20p, 50p, 100p, and 200p coin with an array for 200p, or 201 elements long (include 0).