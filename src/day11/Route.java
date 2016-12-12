package day11;

import java.util.ArrayList;
import java.util.List;

public class Route {

//    private static Integer minimumLength = Integer.MAX_VALUE;
//    private static Integer minimumLength = 20;
    private Route comingFrom;
    private Step nextStep;

    public boolean isSolution() {
        return this.nextStep.isSolution();
    }

    public Route(Route comingFrom, Step nextStep) {
        this.comingFrom = comingFrom;
        this.nextStep = nextStep;
    }

    public Route(Step nextStep) {
        this.nextStep = nextStep;
    }

    public List<Step> getVisitedPlaces() {
        List<Step> visitedPlaces = new ArrayList<>();

        if (comingFrom != null) {
            visitedPlaces.addAll(comingFrom.getVisitedPlaces());
//            visitedPlaces.add(comingFrom.nextStep);
        }
        visitedPlaces.add(nextStep);
        return visitedPlaces;
    }

    public int getLength() {
        return this.getVisitedPlaces().size();
    }

    public int getLength() {
        return this.getVisitedPlaces().size()+1;
    }

    private boolean alreadyVisited() {
        return this.getVisitedPlaces().contains(this.nextStep);
    }

    private boolean alreadyVisited(Step anotherStep) {
        return this.getVisitedPlaces().contains(anotherStep);
    }

    public List<Route> expand() {
        // new steps
        List<Route> newRoutes = new ArrayList<>();

        // Populates list of things, which can be carried to other floors
        List<String> canBeTakenAway = new ArrayList<>();
        canBeTakenAway.addAll(nextStep.getCurrentFloorGenerators());
        canBeTakenAway.addAll(nextStep.getCurrentFloorMicroChips());

        List<List<String>> takeAwayCombinations = new ArrayList<List<String>>();
        // Create every possible combination of 2 what can be taken away
        for (int i = 1; i <= 2; i++) {
            takeAwayCombinations.addAll(CombinatoricUtils.getPermutationsList(canBeTakenAway, i));
        }

        for (int i = 0; i < takeAwayCombinations.size(); i++) {
            // Go up a floor, if we are below 4th -> move every possible combination up there
            if (nextStep.getCurrentFloor() < 4) {
                Step newStep = new Step(nextStep, nextStep.getCurrentFloor() + 1, takeAwayCombinations.get(i));
                if (!alreadyVisited(newStep)) {
                    newRoutes.add(new Route(this, newStep));
                }
            }

            // Go down a floor, if we are above 1st -> move every possible combination down there
            if (nextStep.getCurrentFloor() > 1) {
                Step newStep = new Step(nextStep, nextStep.getCurrentFloor() - 1, takeAwayCombinations.get(i));
                if (!alreadyVisited(newStep)) {
                    newRoutes.add(new Route(this, newStep));
                }
            }
        }
        return newRoutes;
    }
}
