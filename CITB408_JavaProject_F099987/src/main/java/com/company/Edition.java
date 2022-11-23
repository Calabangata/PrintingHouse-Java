package com.company;




public class Edition{

    private String name;
    private Paper paper;
    private int NumberOfPages;
    private int Copies;


    public Edition(String name, Paper paper, int numberOfPages, int copies) {
        this.name = name;
        this.paper = paper;
        NumberOfPages = numberOfPages;
        this.Copies = copies;

    }

    public Paper getPaper() {
        return paper;
    }

    public int getCopies() {
        return Copies;
    }

    public String getName() {
        return name;
    }

    public int getNumberOfPages() {
        return NumberOfPages;
    }

    @Override
    public String toString() {
        return "Edition{" +
                "name='" + name + '\'' +
                ", paper=" + paper +
                ", NumberOfPages=" + NumberOfPages +
                ", Copies=" + Copies +
                '}' + '\n';
    }
}
