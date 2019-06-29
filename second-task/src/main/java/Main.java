import third_mode.LevensteinMetric;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner inputScanner = new Scanner(new File("src/main/resources/input.txt"));
        Scanner patternsScanner = new Scanner(new File("src/main/resources/patterns.txt"));

        ArrayList<String> input = new ArrayList<String>();
        Set<String> patterns = new LinkedHashSet<String>();

        while (inputScanner.hasNextLine())
            input.add(inputScanner.nextLine());

        while (patternsScanner.hasNextLine())
            patterns.add(patternsScanner.nextLine());

        inputScanner.close();
        patternsScanner.close();

        ArrayList<String> firstModeResult = firstMode(input, patterns);
        ArrayList<String> secondModeResult = secondMode(input, patterns);
        ArrayList<String> thirdModeResult = thirdMode(input, patterns, 1);

        System.out.println("Mode 1 outputs: ");
        for (String line :
                firstModeResult) {
            System.out.println(line);
        }

        System.out.println();

        System.out.println("Mode 2 outputs: ");
        for (String line :
                secondModeResult) {
            System.out.println(line);
        }

        System.out.println();

        System.out.println("Mode 3 outputs: ");
        for (String line :
                thirdModeResult) {
            System.out.println(line);
        }
    }

    private static ArrayList<String> firstMode(ArrayList<String> input, Set<String> patterns){
        ArrayList<String> result = new ArrayList<String>();
        for(String textLine :
                input)
            if (patterns.contains(textLine))
                result.add(textLine);
        return result;
    }

    private static ArrayList<String> secondMode(ArrayList<String> input, Set<String> patterns){
        ArrayList<String> result = new ArrayList<String>();

        for(String textLine :
                input){
            for (String patternLine :
                    patterns) {
                if (textLine.contains(patternLine))
                    result.add(textLine);
            }
        }
        return result;
    }

    private static ArrayList<String> thirdMode(ArrayList<String> input, Set<String> patterns, int maxDistance){

        ArrayList<String> result = new ArrayList<String>();

        LevensteinMetric levensteinMetric = new LevensteinMetric();

        for(String textLine :
                input){
            for (String patternLine :
                    patterns) {
                if (levensteinMetric.getDistance(textLine, patternLine, maxDistance) <= maxDistance)
                    result.add(textLine);
            }
        }
        return result;
    }
}
