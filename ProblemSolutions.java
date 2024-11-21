
    /******************************************************************
     *
     *   Meghan Garcia / SECTION 002
     *
     *   This java file contains the problem solutions for the methods selectionSort,
     *   mergeSortDivisibleByKFirst, asteroidsDestroyed, and numRescueCanoes methods.
     *
     ********************************************************************/


    import java.util.Arrays;

    public class ProblemSolutions2 {

        /**
         * Method SelectionSort
         * <p>
         * This method performs a selection sort. This file will be spot checked,
         * so ENSURE you are performing a Selection Sort!
         * <p>
         * This is an in-place sorting operation that has two function signatures. This
         * allows the second parameter to be optional, and if not provided, defaults to an
         * ascending sort. If the second parameter is provided and is false, a descending
         * sort is performed.
         *
         * @param values    - int[] array to be sorted.
         */

        public void selectionSort(int[] values) {
            selectionSort(values, true); // Default to ascending order
        }

        public static void selectionSort(int[] values, boolean ascending) {
            int n = values.length;

            // Outer loop for selecting the current element
            for (int i = 0; i < n - 1; i++) {
                int extremeIndex = i;
                // Inner loop for finding the smallest or largest element
                for (int j = i + 1; j < n; j++) {
                    if ((ascending && values[j] < values[extremeIndex]) ||
                            (!ascending && values[j] > values[extremeIndex])) {
                        extremeIndex = j;
                    }
                }
                // Swap if the extreme element is not at the current index
                if (extremeIndex != i) {
                    int temp = values[i];
                    values[i] = values[extremeIndex];
                    values[extremeIndex] = temp;
                }
            }
        }

        /**
         * Method mergeSortDivisibleByKFirst
         * <p>
         * This method will perform a merge sort algorithm. However, all numbers
         * that are divisible by the argument 'k', are returned first in the sorted
         * list. Example:
         * values = { 10, 3, 25, 8, 6 }, k = 5
         * Sorted result should be --> { 10, 25, 3, 6, 8 }
         * <p>
         * values = { 30, 45, 22, 9, 18, 39, 6, 12 }, k = 6
         * Sorted result should be --> { 30, 18, 6, 12, 9, 22, 39, 45 }
         * <p>
         * As shown above, this is a normal merge sort operation, except for the numbers
         * divisible by 'k' are first in the sequence.
         *
         * @param values - input array to sort per definition above
         * @param k      - value k, such that all numbers divisible by this value are first
         */

        public void mergeSortDivisibleByKFirst(int[] values, int k) {
            // Protect against bad input values
            if (k == 0) return;
            if (values.length <= 1) return;

            mergeSortDivisibleByKFirst(values, k, 0, values.length - 1);
        }

        private void mergeSortDivisibleByKFirst(int[] values, int k, int left, int right) {
            if (left >= right) return;

            int mid = left + (right - left) / 2;
            mergeSortDivisibleByKFirst(values, k, left, mid);
            mergeSortDivisibleByKFirst(values, k, mid + 1, right);
            mergeDivisibleByKFirst(values, k, left, mid, right);
        }

        /*
         * The merging portion of the merge sort, divisible by k first
         */
        private void mergeDivisibleByKFirst(int[] arr, int k, int left, int mid, int right) {
            int[] temp = new int[right - left + 1];
            int i = left;
            int j = mid + 1;
            int tempIndex = 0;

            // First, copy all numbers divisible by k to the temp array
            while (i <= mid && arr[i] % k == 0) {
                temp[tempIndex++] = arr[i++];
            }
            while (j <= right && arr[j] % k == 0) {
                temp[tempIndex++] = arr[j++];
            }

            // Now merge the remaining numbers (those not divisible by k)
            while (i <= mid && j <= right) {
                if (arr[i] % k != 0 && arr[i] < arr[j]) {
                    temp[tempIndex++] = arr[i++];
                } else if (arr[j] % k != 0 && arr[j] <= arr[i]) {
                    temp[tempIndex++] = arr[j++];
                } else if (arr[i] % k == 0) {
                    temp[tempIndex++] = arr[i++];
                } else {
                    temp[tempIndex++] = arr[j++];
                }
            }

            // Handle any remaining elements in the left or right side
            while (i <= mid) {
                temp[tempIndex++] = arr[i++];
            }
            while (j <= right) {
                temp[tempIndex++] = arr[j++];
            }

            // Copy the merged array back to the original array
            System.arraycopy(temp, 0, arr, left, temp.length);
        }

        /**
         * Method asteroidsDestroyed
         * <p>
         * You are given an integer 'mass', which represents the original mass of a planet.
         * You are further given an integer array 'asteroids', where asteroids[i] is the mass
         * of the ith asteroid.
         * <p>
         * You can arrange for the planet to collide with the asteroids in any arbitrary order.
         * If the mass of the planet is greater than or equal to the mass of the asteroid, the
         * asteroid is destroyed and the planet gains the mass of the asteroid. Otherwise, the
         * planet is destroyed.
         * <p>
         * Return true if possible for all asteroids to be destroyed. Otherwise, return false.
         * <p>
         * Example 1:
         * Input: mass = 10, asteroids = [3,9,19,5,21]
         * Output: true
         * <p>
         * Example 2:
         * Input: mass = 5, asteroids = [4,9,23,4]
         * Output: false
         * <p>
         * Constraints:
         * 1 <= mass <= 105
         * 1 <= asteroids.length <= 105
         * 1 <= asteroids[i] <= 105
         *
         * @param mass      - integer value representing the mass of the planet
         * @param asteroids - integer array of the mass of asteroids
         * @return - return true if all asteroids destroyed, else false.
         */
        public static boolean asteroidsDestroyed(int mass, int[] asteroids) {


            Arrays.sort(asteroids);

            long currentMass = mass; //


            for (int asteroid : asteroids) {
                if (currentMass >= asteroid) {
                    currentMass += asteroid;
                } else {
                    return false;
                }
            }

            return true;
        }



            /**
             * Method numRescueSleds
             * <p>
             * You are given an array people where people[i] is the weight of the ith person,
             * and an infinite number of rescue sleds where each sled can carry a maximum weight
             * of limit. Each sled carries at most two people at the same time, provided the
             * sum of the weight of those people is at most limit. Return the minimum number
             * of rescue sleds to carry every given person.
             *
             * @param people - an array of weights for people that need to go in a sled
             * @param limit  - the weight limit per sled
             * @return - the minimum number of rescue sleds required to hold all people
             */
        public static int numRescueSleds(int[] people, int limit) {

            if (people.length == 0 || limit == 0) return 0;

            // Sort the people array in ascending order
            Arrays.sort(people);

            int sledCount = 0;
            int i = 0; // Pointer to the lightest person
            int j = people.length - 1; // Pointer to the heaviest person

            while (i <= j) {
                // If the lightest person and the heaviest person can share a sled
                if (people[i] + people[j] <= limit) {
                    i++; // Place the lightest person in the sled
                }
                // Always place the heaviest person in the sled
                j--;

                // Increment sled count after placing people in the sled
                sledCount++;
            }

            return sledCount;
        }
    }


