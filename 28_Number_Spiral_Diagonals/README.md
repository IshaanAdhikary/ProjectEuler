It's easy to observe that the top right number of every ring is a perfect square, as since we are filling in numbers within a square without ever skipping numbers, and the top-right corner is the last filled in, it must be a square number based off the square size length. Each ring adds 2 to the side length, and we start with a side length of one. So, the side length at each ring is 2n - 1. Therefore, the top right value of each ring is (2n - 1)^2. We can then calculate the other three corners in each ring by subtracting the side length and adding 1 six times, or - 6 * (2n - 1) + 6 * 1 = -6 * (2n - 2). This is since the the top left will be the top right - side length + 1, while the bottom left will be the top left - side length + 1 AKA the top right - 2(side length) + 2, etc. This simplifies to the quadratic equation: 16n^2 - 28n + 16.To get the sum of diagonals in a 1001 by 1001 spiral, we just need to find sum of all rings until we reach a side length of 1001. These calculations only work for ring 2 and onwards, so we will need to start with 1 for the first ring, which consists solely of the number 1.