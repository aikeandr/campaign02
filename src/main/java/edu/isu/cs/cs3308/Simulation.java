package edu.isu.cs.cs3308;


import edu.isu.cs.cs3308.structures.DoublyLinkedList;
import edu.isu.cs.cs3308.structures.LinkedQueue;

import java.util.Random;

/**
 * Class representing a wait time simulation program.
 *
 * @author Isaac Griffith
 * @author Andrew Aikens
 */
public class Simulation {

    private int arrivalRate;
    private int maxNumQueues;
    private Random r;
    private int numIterations = 50;
    private DoublyLinkedList<LinkedQueue<Integer>> lines;

    /**
     * Constructs a new simulation with the given arrival rate and maximum number of queues. The Random
     * number generator is seeded with the current time. This defaults to using 50 iterations.
     *
     * @param arrivalRate the integer rate representing the maximum number of new people to arrive each minute
     * @param maxNumQueues the maximum number of lines that are open
     */
    public Simulation(int arrivalRate, int maxNumQueues) {
        this.arrivalRate = arrivalRate;

        this.maxNumQueues = maxNumQueues;
        r = new Random();
    }

    /**
     * Constructs a new simulation with the given arrival rate and maximum number of queues. The Random
     * number generator is seeded with the provided seed value, and the number of iterations is set to
     * the provided value.
     *
     * @param arrivalRate the integer rate representing the maximum number of new people to arrive each minute
     * @param maxNumQueues the maximum number of lines that are open
     * @param numIterations the number of iterations used to improve data
     * @param seed the initial seed value for the random number generator
     */
    public Simulation(int arrivalRate, int maxNumQueues, int numIterations, int seed) {
        this(arrivalRate, maxNumQueues);
        r = new Random(seed);
        this.numIterations = numIterations;
    }

    /**
     * Executes the Simulation
     */
    public void runSimulation() {
        System.out.println("Arrival Rate: " + arrivalRate);
        for(int i = 0; i < maxNumQueues; i++) {
            int averageWait = 0;
            for (int j = 0; j < numIterations; j++) {
                lines = new DoublyLinkedList<>();
                for (int queuesOpen = 0; queuesOpen < i + 1; queuesOpen++)
                    lines.addLast(new LinkedQueue<>());
                int totalTimeWaited = 0;
                int numOfPeoplePassed = 0;
                for (int arrivalTime = 0; arrivalTime < 720; arrivalTime++) {
                    int peopleArrivedThisMinute = getRandomNumPeople(arrivalRate);
                    for (int k = 0; k < peopleArrivedThisMinute; k++)
                        personEntersQueue(arrivalTime);
                    for (int k = 0; k < lines.size(); k++) {
                        if (lines.get(k).peek() != null) {
                            totalTimeWaited += (arrivalTime - lines.get(k).poll());
                            numOfPeoplePassed += 1;
                        }
                        if (lines.get(k).peek() != null) {
                            totalTimeWaited += (arrivalTime - lines.get(k).poll());
                            numOfPeoplePassed += 1;
                        }
                    }
                }
                averageWait = averageWait + totalTimeWaited / numOfPeoplePassed;
            }
            averageWait = averageWait/numIterations + 1;
            printSimulation(averageWait, i+1);
        }
    }

    /**
     * Adds a person into the last queue with the least amount of people
     *
     * @param arrivalTime of the new person
     */
    private void personEntersQueue(int arrivalTime) {
        int personEntersQueue = 0;
        while (personEntersQueue+1 < lines.size()){
            if (lines.get(personEntersQueue).size() >= lines.get(personEntersQueue+1).size())
                personEntersQueue++;
            else {
                lines.get(personEntersQueue).offer(arrivalTime);
                personEntersQueue = lines.size();
            }
        }
        if(lines.get(personEntersQueue) != null)
            lines.get(personEntersQueue).offer(arrivalTime);
    }

    /**
     * Prints the wait time based on the number of open lines.
     *
     * @param average wait time for a person
     * @param openQueues number of available lines
     */
    private void printSimulation(int average, int openQueues){
        System.out.println("Average wait time using " + openQueues + " queue(s): " + average);
    }

    /**
     * returns a number of people based on the provided average
     *
     * @param avg The average number of people to generate
     * @return An integer representing the number of people generated this minute
     */
    //Don't change this method.
    private static int getRandomNumPeople(double avg) {
        Random r = new Random();
        double L = Math.exp(-avg);
        int k = 0;
        double p = 1.0;
        do {
            p = p * r.nextDouble();
            k++;
        } while (p > L);
        return k - 1;
    }
}
