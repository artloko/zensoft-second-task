package third_mode;

public class LevensteinMetric {

    private static final int DEFAULT_LENGTH = 255;
    private int[] currentRow;
    private int[] previousRow;

    public LevensteinMetric() {
        this(DEFAULT_LENGTH);
    }

    public LevensteinMetric(int maxLength) {
        previousRow = new int[maxLength + 1];
        currentRow = new int[maxLength + 1];
    }

    public int getDistance(String first, String second, int maxK){
        int firstLength = first.length();
        int secondLength = second.length();

        if (firstLength == 0)
            return secondLength;
        else if (secondLength == 0) return firstLength;

        if (firstLength > secondLength) {
            String tmp = first;
            first = second;
            second = tmp;
            firstLength = secondLength;
            secondLength = second.length();
        }

        if (maxK < 0) maxK = secondLength;
        if (secondLength - firstLength > maxK) return maxK + 1;

        if (firstLength > currentRow.length) {
            currentRow = new int[firstLength + 1];
            previousRow = new int[firstLength + 1];
        }

        for (int i = 0; i <= firstLength; i++)
            previousRow[i] = i;

        for (int i = 1; i <= secondLength; i++) {
            char ch = second.charAt(i - 1);
            currentRow[0] = i;

            int from = Math.max(i - maxK - 1, 1);
            int to = Math.min(i + maxK + 1, firstLength);
            for (int j = from; j <= to; j++) {
                int cost = first.charAt(j - 1) == ch ? 0 : 1;
                currentRow[j] = Math.min(Math.min(currentRow[j - 1] + 1, previousRow[j] + 1), previousRow[j - 1] + cost);
            }

            int tempRow[] = previousRow;
            previousRow = currentRow;
            currentRow = tempRow;
        }
        return previousRow[firstLength];
    }
}
