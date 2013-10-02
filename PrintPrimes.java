public class PrintPrimes {

    public static final int NUMBER_OF_PRIMES = 300;
    public static final int MAX_ROWS = 50;
    public static final int MAX_COLUMNS = 4;
    public static final int ORD_MAX = 30;
	
    int numberOfPrimes;
    int maxRowsPerPage;
    int maxColumnsPerPage;
    int ORDMAX;
    int listOfPrimes[];

    public PrintPrimes(int numberOfPrimes, int maxRowsPerPage, int maxColumnsPerPage, int ORDMAX) {
        this.numberOfPrimes = numberOfPrimes;
        this.maxRowsPerPage = maxRowsPerPage;
        this.maxColumnsPerPage = maxColumnsPerPage;
        this.ORDMAX = ORDMAX;
        this.listOfPrimes = new int[numberOfPrimes + 1];
    }
    
    public static void main(String[] args) {
        PrintPrimes printPrimes = new PrintPrimes(NUMBER_OF_PRIMES, MAX_ROWS, MAX_COLUMNS, ORD_MAX);
        printPrimes.calculatePrimes();
        printPrimes.printPrimes();
    }
    
    private int calculateNextOddMultiple(int oddBaseNumber, int multiplicator) {
    	
    	int nextOddMultiple = 0;
    	nextOddMultiple = oddBaseNumber + 2 * multiplicator;
    	
    	return nextOddMultiple;
    }

    private void calculatePrimes() {
        /* Two is the only even prime. All other prime numbers are odd.
         * To simplify the code, we simply add 2 as a prime number, and
         * delegate the task of finding all odd prime numbers to a helper
         * function.
         * --------------- Changed to:
         * 2 is the only even prime. To simplify the code, we add 2 as a 
         * prime number in the listOfPrimes list. We incorporate the information
         * that all the rest of the prime numbers are even into their search.
         */
        listOfPrimes[1] = 2;
        
        boolean isPrime;
        int multiplesOfPrimes[] = new int[ORDMAX + 1];

        int currentOddNumber = 1;
        int indexOfPrimeToSquare = 2;
        int nearestSquare = 9;
	int i, j;
        for (i = 2; i <= numberOfPrimes; i++) {
            do {
                currentOddNumber = currentOddNumber + 2;
                if (currentOddNumber == nearestSquare) {
                    indexOfPrimeToSquare++;
                    nearestSquare = listOfPrimes[indexOfPrimeToSquare] * listOfPrimes[indexOfPrimeToSquare];
                    multiplesOfPrimes[indexOfPrimeToSquare - 1] = currentOddNumber;
                }
                j = 2;
                isPrime = true;
                while ((j < indexOfPrimeToSquare) && isPrime) {
                    while (multiplesOfPrimes[j] < currentOddNumber)
                    	multiplesOfPrimes[j] = calculateNextOddMultiple(multiplesOfPrimes[j], listOfPrimes[j]);
                    if (multiplesOfPrimes[j] == currentOddNumber)
                        isPrime = false;
                    j++;
                }
                
            } while (!isPrime);
            listOfPrimes[i] = currentOddNumber;
        }
    }

    public void printPrimes() {
        int pageNumber = 1;
        int pageOffset = 1;
        while (pageOffset <= numberOfPrimes) {
            System.out.println("The First " + numberOfPrimes +
                               " Prime Numbers --- Page " + pageNumber);
            System.out.println("");
            for (int row = pageOffset; row < pageOffset + maxRowsPerPage; row++) {
                for (int col = 0; col < maxColumnsPerPage; col++)
                    if (row + col * maxRowsPerPage <= numberOfPrimes)
                        System.out.format("%10d", listOfPrimes[row + col * maxRowsPerPage]);
                System.out.println("");
            }
            System.out.println("\f");
            pageNumber = pageNumber + 1;
            pageOffset = pageOffset + maxRowsPerPage * maxColumnsPerPage;
        }
    }
}

