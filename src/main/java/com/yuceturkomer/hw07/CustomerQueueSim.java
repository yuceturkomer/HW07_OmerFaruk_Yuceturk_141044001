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
     *
     * 
     */
    public CustomerQueueSim() {
        customerLinkedList = new LinkedList<Customer>();
    }

    public void printLinkedList() {
        for (Customer c : customerLinkedList)
            System.out.println(c);
    }

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

    private void swap(int firstIndex, int secondIndex) {
        if (firstIndex < 0 || firstIndex >= customerLinkedList.size() || secondIndex < 0 || secondIndex >= customerLinkedList.size())
            throw new ArrayIndexOutOfBoundsException();

        Customer temp = customerLinkedList.get(firstIndex);
        customerLinkedList.set(firstIndex, customerLinkedList.get(secondIndex));
        customerLinkedList.set(secondIndex, temp);
    }

    public void runSim() {
        runSim(-1);
    }

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

    public int minForm(int hour, int minute) {
        return hour * 60 + minute;
    }

    public String hourMinStr(int minform) {
        return String.format("%02d : %02d", minform / 60, minform % 60);
    }

}
