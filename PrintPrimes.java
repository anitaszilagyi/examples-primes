public class PrintPrimes {

    public static final int NUMBER_OF_PRIMES = 300;
    public static final int MAX_ROWS = 50;
    public static final int MAX_COLUMNS = 4;
    public static final int MAX_SQUARES_OF_PRIMES = 30;
    
    public static final int FIRST_ODD_NUMBER = 1;
    public static final int FIRST_PRIME = 2;
    public static final int INDEX_OF_FIRST_PRIME = 1;
    public static final int FIRST_SQUARE = 9;
	
    int numberOfPrimes;
    int maxRowsPerPage;
    int maxColumnsPerPage;
    int maxSquares;
    int listOfPrimes[];

    public PrintPrimes(int numberOfPrimes, int maxRowsPerPage, int maxColumnsPerPage, int maxSquares) {
        this.numberOfPrimes = numberOfPrimes;
        this.maxRowsPerPage = maxRowsPerPage;
        this.maxColumnsPerPage = maxColumnsPerPage;
        this.maxSquares = maxSquares;
        this.listOfPrimes = new int[numberOfPrimes + 1];
    }
    
    public static void main(String[] args) {
        PrintPrimes printPrimes = new PrintPrimes(NUMBER_OF_PRIMES, MAX_ROWS, MAX_COLUMNS, MAX_SQUARES_OF_PRIMES);
        printPrimes.calculatePrimes();
        printPrimes.printPrimes();
    }
    
    private static int calculateNextOddNumber(int currentOddNumber) {
    	int nextOddNumber = 0;
    	nextOddNumber = currentOddNumber + 2;
    	return nextOddNumber;
    }
    
    private static int calculateSquare(int numberToSquare) {
    	int square = 0;
    	square = numberToSquare * numberToSquare;
    	return square;
    }
    
    private int calculateNextOddMultiple(int oddBaseNumber, int multiplicator) {
    	
    	int nextOddMultiple = 0;
    	nextOddMultiple = oddBaseNumber + 2 * multiplicator;
    	
    	return nextOddMultiple;
    }
    
    private int calculatePrimesPrintedSoFar(int rowsPrinted, int colsPrinted) {
    	int primesPrintedSoFar = rowsPrinted + colsPrinted * maxRowsPerPage;
    	return primesPrintedSoFar;
    }
    
    private boolean isEndReached(int primesPrintedSoFar) {
    	 boolean bool = false;
    	 bool = (primesPrintedSoFar <= numberOfPrimes);
    	 return bool;
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
        listOfPrimes[INDEX_OF_FIRST_PRIME] = FIRST_PRIME;
        
        boolean isPrime;
        int multiplesOfPrimes[] = new int[MAX_SQUARES_OF_PRIMES + 1];

        int currentOddNumber = FIRST_ODD_NUMBER;
        int indexOfPrimeToSquare = INDEX_OF_FIRST_PRIME + 1;
        int nearestSquare = FIRST_SQUARE;
	int i, j;
        for (i = 2; i <= numberOfPrimes; i++) {
            do {
            	j = 2;
                isPrime = true;
                currentOddNumber = calculateNextOddNumber(currentOddNumber);
                if (currentOddNumber == nearestSquare) {
                    multiplesOfPrimes[indexOfPrimeToSquare] = currentOddNumber;
                    indexOfPrimeToSquare++;
                    nearestSquare = calculateSquare(listOfPrimes[indexOfPrimeToSquare]);
                    isPrime = false;
                }
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
        int rowOffset = 1;
        int primesPrintedSoFar = 0;
        String pageTitle = "The First " + numberOfPrimes + " Prime Numbers --- Page ";
        while (rowOffset <= numberOfPrimes) {
            System.out.println(pageTitle + pageNumber);
            System.out.println("");
            for (int row = rowOffset; row < rowOffset + maxRowsPerPage; row++) {
                for (int col = 0; col < maxColumnsPerPage; col++) {
                    primesPrintedSoFar = calculatePrimesPrintedSoFar(row, col);	
                    if (isEndReached(primesPrintedSoFar))
                        System.out.format("%10d", listOfPrimes[primesPrintedSoFar]);
                }
                System.out.println("");
            }
            System.out.println("\f");
            pageNumber++;
            rowOffset = rowOffset + maxRowsPerPage * maxColumnsPerPage;
        }
    }
}

