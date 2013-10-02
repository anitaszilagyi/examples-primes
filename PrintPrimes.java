public class PrintPrimes {
	
    int numberOfPrimes;
    int maxRowsPerPage;
    int maxColumnsPerPage;
    int WW;
    int ORDMAX;
    int listOfPrimes[];

    public PrintPrimes(int numberOfPrimes, int maxRowsPerPage, int maxColumnsPerPage, int WW, int ORDMAX) {
        this.numberOfPrimes = numberOfPrimes;
        this.maxRowsPerPage = maxRowsPerPage;
        this.maxColumnsPerPage = maxColumnsPerPage;
        this.WW = WW;
        this.ORDMAX = ORDMAX;
        this.listOfPrimes = new int[numberOfPrimes + 1];
    }
    
    public static void main(String[] args) {
        PrintPrimes printPrimes = new PrintPrimes(300, 50, 4, 10, 30);
        printPrimes.calculatePrimes();
        printPrimes.printPrimes();
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
        int N;
        int MULT[] = new int[ORDMAX + 1];

        int currentOddNumber = 1;
        int ORD = 2;
        int nearestSquare = 9;
	int i;
        for (i = 2; i <= numberOfPrimes; i++) {
            do {
                currentOddNumber = currentOddNumber + 2;
                if (currentOddNumber == nearestSquare) {
                    ORD = ORD + 1;
                    nearestSquare = listOfPrimes[ORD] * listOfPrimes[ORD];
                    MULT[ORD - 1] = currentOddNumber;
                }
                N = 2;
                isPrime = true;
                while (N < ORD && isPrime) {
                    while (MULT[N] < currentOddNumber)
                        MULT[N] = MULT[N] + listOfPrimes[N] + listOfPrimes[N];
                    if (MULT[N] == currentOddNumber)
                        isPrime = false;
                    N = N + 1;
                }
                
            } while (!isPrime);
            listOfPrimes[i] = currentOddNumber;
        }
    }

    public void printPrimes() {
        int PAGENUMBER = 1;
        int PAGEOFFSET = 1;
        while (PAGEOFFSET <= numberOfPrimes) {
            System.out.println("The First " + numberOfPrimes +
                               " Prime Numbers --- Page " + PAGENUMBER);
            System.out.println("");
            for (int ROWOFFSET = PAGEOFFSET; ROWOFFSET < PAGEOFFSET + maxRowsPerPage; ROWOFFSET++) {
                for (int C = 0; C < CC; C++)
                    if (ROWOFFSET + C * maxRowsPerPage <= numberOfPrimes)
                        System.out.format("%10d", listOfPrimes[ROWOFFSET + C * maxRowsPerPage]);
                System.out.println("");
            }
            System.out.println("\f");
            PAGENUMBER = PAGENUMBER + 1;
            PAGEOFFSET = PAGEOFFSET + maxRowsPerPage * maxColumnsPerPage;
        }
    }
}

