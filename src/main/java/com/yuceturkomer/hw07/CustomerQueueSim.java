package com.yuceturkomer.hw07;

import java.io.*;
import java.util.LinkedList;

/**
 * The class which includes methods for simulating the customer queue.
 * The customerLinkedList is a list that holds whole data from file (not sorted)
 * runSim method creates a new PriorityQueue and works around it
 */
public class CustomerQueueSim {
    private LinkedList<Customer> customerLinkedList;

    /**
     * The no parameter constructor
     */
    public CustomerQueueSim() {
        customerLinkedList = new LinkedList<Customer>();
    }

    /**
     * Used for testing
     */
    public void printLinkedList() {
        for (Customer c : customerLinkedList)
            System.out.println(c);
    }

    /**
     * Reads data from file, parses it and puts in the Customer list
     * @param inputFile name as string for input file
     * @return returns false if file is empty or error occured.
     */
    public boolean readDataFromFile(String inputFile) {
        FileInputStream fStream = null;
        try {
            fStream = new FileInputStream(inputFile);
            DataInputStream in = new DataInputStream(fStream);
            BufferedReader bReader = new BufferedReader(new InputStreamReader(in));
            String delims = "[: ]+";
            String fLine;
            if (bReader.readLine() == null) {
                return false;
            }
            while ((fLine = bReader.readLine()) != null) {
                //System.out.println(fLine);
                String[] tokens = fLine.split(delims);

                customerLinkedList.add(new Customer(
                        Integer.parseInt(tokens[0]),
                        Integer.parseInt(tokens[1]),
                        Integer.parseInt(tokens[2]),
                        Integer.parseInt(tokens[4])));

            }
        } catch (FileNotFoundException e) {
            System.err.println("Error opening file to read. ->" + inputFile);
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            System.err.println("Error reading file. ->" + inputFile);
            e.printStackTrace();
            return false;
        }
        return true;

    }

    /**
     * Calls runSim(int) method.
     */
    public void runSim() {
        runSim(-1);
    }

    /**
     * Simulates the Customer queue and gives an output to the console or the file according to the rangeAsHour param.
     *
     * Creates a new priority queue. Holds an initial time, and current time as well as inital and current customers
     * If customerLinkedList is empty then immediately returns.
     *
     * Continues until both priorityQueue and customerLinkedList are empty.
     *
     * @param rangeAsHours If its a non-negative number, it takes inputs from input file
     *                     redirects the output to a file named "part1data2.txt" within the interval of initial and given
     *                     hour.
     *
     *                     If its a negative number, then  it takes inputs from input file
     *                     redirects the output to the standart output.
     */
    public void runSim(int rangeAsHours) {
        if (customerLinkedList.isEmpty())
            return;
        boolean b;
        FileWriter fw;
        BufferedWriter bw = null;
        PriorityQueue<Customer> priorityQueue = new PriorityQueue<Customer>();
        Customer custToAdd;
        Customer tempCust = customerLinkedList.peek();
        Customer currCust = customerLinkedList.poll();
        int initialTime = minForm(currCust.getHour(), currCust.getMinute());
        int currTime = initialTime;
        int nextTime = initialTime + currCust.getServiceTime();
        int flag = 0;
        if (rangeAsHours < 0) {
            System.out.println(String.format(
                    "Serving a person from time %-8s to %-8s ------- %-20s "
                    , hourMinStr(currTime)
                    , hourMinStr(currTime + currCust.getServiceTime())
                    , currCust));
        } else {
            File file = new File("part1data2.txt");
            // if file doesnt exists, then create it
            try {
                if (!file.exists()) {
                    b = file.createNewFile();
                }

                fw = new FileWriter(file);
                bw = new BufferedWriter(fw);
                bw.write(String.format(
                        "Part 1 20 hours log for data2.txt\n\nServing a person from time %-8s to %-8s ------- %-20s \n"
                        , hourMinStr(currTime)
                        , hourMinStr(currTime + currCust.getServiceTime())
                        , currCust));
            } catch (IOException e) {
                e.printStackTrace();
            }

        }


        while (customerLinkedList.peek() != null || !priorityQueue.isEmpty()) {
            tempCust = customerLinkedList.peek();

            while (customerLinkedList.peek() != null && minForm(tempCust.getHour(), tempCust.getMinute()) <= nextTime) {
                priorityQueue.offer(customerLinkedList.poll());
                tempCust = customerLinkedList.peek();
            }
            if (priorityQueue.isEmpty() && customerLinkedList.peek() != null) {
                priorityQueue.offer(customerLinkedList.poll());
                flag = 1;
            } else if (!priorityQueue.isEmpty()) {
                currCust = priorityQueue.poll();
                if (flag != 1) {
                    currTime = nextTime;
                    nextTime = currTime + currCust.getServiceTime();
                } else {
                    currTime = minForm(currCust.getHour(), currCust.getMinute());
                    nextTime = currTime + currCust.getServiceTime();
                }
                if (minForm(rangeAsHours, 0) < nextTime && rangeAsHours >= 0)
                    break;
                if (rangeAsHours < 0) {
                    System.out.println(String.format(
                            "Serving a person from time %-8s to %-8s ------- %-20s "
                            , hourMinStr(currTime)
                            , hourMinStr(currTime + currCust.getServiceTime())
                            , currCust));
                } else {
                    if (bw != null) {
                        try {
                            bw.append(String.format(
                                    "Serving a person from time %-8s to %-8s ------- %-20s \n"
                                    , hourMinStr(currTime)
                                    , hourMinStr(currTime + currCust.getServiceTime())
                                    , currCust));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                flag = 0;
            }
        }
        try {
            if (bw != null) {
                bw.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Takes hour and minute values, converts it to the minute form integer and returns it
     * @param hour Hour input
     * @param minute Minute input
     * @return minute form of given h:m
     */
    public int minForm(int hour, int minute) {
        return hour * 60 + minute;
    }

    /**
     * Takes the h:m as minute value and converts it to String form and returns it
     * @param minform Minute form of given h:m
     * @return "Hour : Minute" string format of given minute value
     */
    public String hourMinStr(int minform) {
        return String.format("%02d : %02d", minform / 60, minform % 60);
    }

}
