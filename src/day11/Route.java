package day11;

import java.util.ArrayList;
import java.util.List;

public class Route {

    private Route comingFrom;
    private Step nextStep;

    public Route(Route comingFrom, Step nextStep) {
        this.comingFrom = comingFrom;
        this.nextStep = nextStep;
        System.out.println(nextStep.isValid());
        if (!alreadyVisited()) {
//            generateNextSteps();
        }
    }

    public Route(Step nextStep) {
        this.nextStep = nextStep;
        generateNextSteps();
        System.out.println("route from initial");
    }

    public List<Step> getVisitedPlaces() {
        List<Step> visitedPlaces = new ArrayList<>();

        if (comingFrom != null) {
            visitedPlaces.addAll(comingFrom.getVisitedPlaces());
            visitedPlaces.add(comingFrom.nextStep);
        }

        return visitedPlaces;
    }

    private boolean alreadyVisited() {
        return this.getVisitedPlaces().contains(this.nextStep);
    }

    public void generateNextSteps() {
        if (nextStep.isValid() && !nextStep.isSolution()) {
            // Populates list of things, which can be carried to other floors
            List<String> canBeTakenAway = new ArrayList<>();
            canBeTakenAway.addAll(nextStep.getCurrentFloorGenerators());
            canBeTakenAway.addAll(nextStep.getCurrentFloorMicroChips());

            List<List<String>> takeAwayCombinations = new ArrayList<List<String>>();
            // Create every possible combination of 2 what can be taken away
            for (int i = 1; i <= 2; i++) {
//                List<String> maximumThings = new ArrayList<>();
//                for (int j = 0; j < canBeTakenAway.size(); j++) {
//                    // don't put the same thing twice
//                    if (maximumThings.contains(canBeTakenAway.get(j))) {
//                        continue;
//                    } else {
//                        maximumThings.add(canBeTakenAway.get(j));
//                    }
//                }
//                takeAwayCombinations.add(maximumThings);
                takeAwayCombinations.addAll(CombinatoricUtils.getPermutationsList(canBeTakenAway, i));
            }

            for (List<String> ls : takeAwayCombinations) {
                for (String s : ls) {
                    System.out.print(s + "+");
                }
                System.out.println();
            }

            for (int i = 0; i < takeAwayCombinations.size(); i++) {
                // Go up a floor, if we are below 4th -> move every possible combination up there
                if (nextStep.getCurrentFloor() < 4) {
                    new Route(this, new Step(nextStep, nextStep.getCurrentFloor() + 1, takeAwayCombinations.get(i)));
                }

                // Go down a floor, if we are above 1st -> move every possible combination down there
                if (nextStep.getCurrentFloor() > 1) {
                    new Route(this, new Step(nextStep, nextStep.getCurrentFloor() - 1, takeAwayCombinations.get(i)));
                }
            }
        }
    }
}
