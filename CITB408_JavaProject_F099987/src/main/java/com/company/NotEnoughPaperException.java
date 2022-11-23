package com.company;

public class NotEnoughPaperException extends Exception{

    private Machine machine;

    public NotEnoughPaperException(Machine machine) {
        this.machine = machine;
    }


    @Override
    public String toString() {
        return "NotEnoughPaperException{" +
                "machine ID: " + machine.getId() + " Maximum number of pages: " + machine.getMaxNumberOfPages() +
                "} ";
    }
}
