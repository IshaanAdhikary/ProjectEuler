Brute force solving. Test every number less than an upper bound, and if it is equal to the sum of it's powers add it to a total.
The upper bound can be calculated by hand, or even with an expression in code if desired. Estimate an inital amount of digits, like 5 digits. If every single digit in this number was a nine, then the maximum power sum would be 9^5 * 5, or 295245, which is six digits long, and therefore 5 digit numbers are still possible.
9^5 * 6 = 354294, which is still 6 digits and still possible.
9^5 * 7 = 413343, which is still 6 digits, but would require 7 digits. No 7 digit numbers are possible, so the above 354294 is the upper bound.
(Technically, to optimize further, one could stop each testing for amount of digits at a certain number value based on these calculations.)