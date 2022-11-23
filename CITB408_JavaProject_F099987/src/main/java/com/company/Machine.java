package com.company;

public class Machine {

    private int id;
    private int MaxNumberOfPages;
    private int NumOfCurrentPapers;
    private PrintType printType;

    public Machine(int ID, int maxNumberOfPages, PrintType printType) {
        this.id = ID;
        MaxNumberOfPages = maxNumberOfPages;
        this.printType = printType;
    }

    public int getId() {
        return id;
    }

    public int getMaxNumberOfPages() {
        return MaxNumberOfPages;
    }

    public int getNumOfCurrentPapers() {
        return NumOfCurrentPapers;
    }

    public void LoadMachine(int numOfCurrentPapers) {
        if(numOfCurrentPapers < 0) {
            NumOfCurrentPapers = 0;
        } else if(numOfCurrentPapers <= MaxNumberOfPages){
            NumOfCurrentPapers = numOfCurrentPapers;
        } else {
            NumOfCurrentPapers = MaxNumberOfPages;
        }
    }

    public void setNumOfCurrentPapers(int numOfCurrentPapers) {
        NumOfCurrentPapers = numOfCurrentPapers;
    }

    public PrintType getPrintType() {
        return printType;
    }


    @Override
    public String toString() {
        return "Machine{" +
                "MaxNumberOfPages=" + MaxNumberOfPages +
                ", NumOfCurrentPapers=" + NumOfCurrentPapers +
                ", printType=" + printType +
                '}';
    }
}
