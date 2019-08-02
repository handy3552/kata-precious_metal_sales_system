package com.coding.sales.controller;

import com.coding.sales.FileUtils;
import com.coding.sales.input.OrderCommand;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class OrderControllerTest {

    @Test
    public void calculateOrder() {
        String absolutePath = getResourceFilePath("sample_command.json");
        String commandString = FileUtils.readFromFile(absolutePath);

        OrderCommand command = OrderCommand.from(commandString);
        OrderController orderController = new OrderController();
        orderController.calculateOrder(command);
    }

    private String getResourceFilePath(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());
        return file.getAbsolutePath();
    }
}