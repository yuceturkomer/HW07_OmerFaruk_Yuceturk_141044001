package com.yuceturkomer.hw07;

import org.junit.Assert;
import org.junit.Test;

/**
 * The CustomerQueueSimTest
 *
 */
public class CustomerQueueSimTest {
    CustomerQueueSim lel = new CustomerQueueSim();

    @Test
    public void testMinForm() throws Exception {
        System.out.println("Testing minForm(int hour, int minute)");
        Assert.assertEquals(lel.minForm(1,5),65);
        Assert.assertEquals(lel.minForm(2,5),125);
        Assert.assertEquals(lel.minForm(1,50),110);
    }

    @Test
    public void testHourMinStr() throws Exception {
        System.out.println("Testing hourMinStr(int minForm)");
        Assert.assertEquals(lel.hourMinStr(65),"01 : 05");
        Assert.assertEquals(lel.hourMinStr(125),"02 : 05");
        Assert.assertEquals(lel.hourMinStr(110),"01 : 50");
    }
}