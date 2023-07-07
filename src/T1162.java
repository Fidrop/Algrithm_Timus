import java.util.*;

class ExchangePoint {
    int currencyA;
    int currencyB;
    double rateAB;
    double commissionAB;
    double rateBA;
    double commissionBA;

    public ExchangePoint(int currencyA, int currencyB, double rateAB, double commissionAB, double rateBA, double commissionBA) {
        this.currencyA = currencyA;
        this.currencyB = currencyB;
        this.rateAB = rateAB;
        this.commissionAB = commissionAB;
        this.rateBA = rateBA;
        this.commissionBA = commissionBA;
    }
}

public class T1162 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();
        int M = scanner.nextInt();
        int S = scanner.nextInt();
        double V = scanner.nextDouble();

        List<ExchangePoint> exchangePoints = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            int currencyA = scanner.nextInt();
            int currencyB = scanner.nextInt();
            double rateAB = scanner.nextDouble();
            double commissionAB = scanner.nextDouble();
            double rateBA = scanner.nextDouble();
            double commissionBA = scanner.nextDouble();
            exchangePoints.add(new ExchangePoint(currencyA, currencyB, rateAB, commissionAB, rateBA, commissionBA));
        }

        scanner.close();

        boolean result = canIncreaseWealth(N, S, V, exchangePoints);
        System.out.println(result ? "YES" : "NO");
    }

    public static boolean canIncreaseWealth(int N, int S, double V, List<ExchangePoint> exchangePoints) {
        double[] wealth = new double[N + 1];
        wealth[S] = V;


        for (int i = 0; i < N - 1; i++) {
            for (ExchangePoint point : exchangePoints) {
                int currencyA = point.currencyA;
                int currencyB = point.currencyB;
                double rateAB = point.rateAB;
                double commissionAB = point.commissionAB;
                double rateBA = point.rateBA;
                double commissionBA = point.commissionBA;


                double amountA = wealth[currencyA];
                if (amountA > 0) {
                    double convertedB = (amountA - commissionAB) * rateAB;
                    wealth[currencyB] = Math.max(wealth[currencyB], convertedB);
                }


                double amountB = wealth[currencyB];
                if (amountB > 0) {
                    double convertedA = (amountB - commissionBA) * rateBA;
                    wealth[currencyA] = Math.max(wealth[currencyA], convertedA);
                }
            }
        }


        for (ExchangePoint point : exchangePoints) {
            int currencyA = point.currencyA;
            int currencyB = point.currencyB;
            double rateAB = point.rateAB;
            double commissionAB = point.commissionAB;
            double rateBA = point.rateBA;
            double commissionBA = point.commissionBA;

            double amountA = wealth[currencyA];
            double amountB = wealth[currencyB];
            if (amountA > 0 && (amountA - commissionAB) * rateAB > amountB) {
                return true;
            }
            if (amountB > 0 && (amountB - commissionBA) * rateBA > amountA) {
                return true;
            }
        }

        return false;
    }
}

