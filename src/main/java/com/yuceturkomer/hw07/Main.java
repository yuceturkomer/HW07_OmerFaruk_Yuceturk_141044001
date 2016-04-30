package com.yuceturkomer.hw07;

/**
 * Main method to test the methods
 * Includes:
 * "data1.txt" test for all hours. Output is printed to the standard output.
 * "data2.txt" test first 20 hours to output file named "part1data2.txt"
 *
 * @author Omer Faruk Yuceturk
 */
public class Main {
    public static void main(String[] args) {
        // Part 1 "data1.txt" test for all hours. Output is printed to the standard output.
        CustomerQueueSim simulation = new CustomerQueueSim();
        simulation.readDataFromFile("data1.txt");
        System.out.println("\n\nSimulation for data1.txt \n\n");
        //simulation.printLinkedList();
        simulation.runSim();

        //Part 1 "data2.txt" test first 20 hours to output file named "part1data2.txt"
        simulation = new CustomerQueueSim();
        simulation.readDataFromFile("data2.txt");
        simulation.runSim(20);
        System.exit(0);
    }
}
