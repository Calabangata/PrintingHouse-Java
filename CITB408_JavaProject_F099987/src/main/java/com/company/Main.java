package com.company;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // write your code here

        PrintingHouse prtHouse = new PrintingHouse("Print", BigDecimal.valueOf(10), BigDecimal.valueOf(5), BigDecimal.valueOf(2000), 5);

        Employee emp1 = new Employee("Ivan",  EmployeePosition.MANAGER);
        Employee emp2 = new Employee("Dragan",  EmployeePosition.MACHINEOPERATOR);

        Machine machine1 = new Machine(1,5000, PrintType.BLACK_WHITE);
        Machine machine2 = new Machine(2,4000, PrintType.BLACK_WHITE);

       Edition edition1 = new Edition("Izd1", new Paper(PaperSize.A5, PaperType.NORMAL), 20, 3);
       Edition edition2 = new Edition("Izd2", new Paper(PaperSize.A2, PaperType.GLOSSY), 20, 2);
       Edition edition3 = new Edition("Izd3", new Paper(PaperSize.A3, PaperType.NEWSPAPER), 15, 3);
       Edition edition4 = new Edition("Izd4", new Paper(PaperSize.A5, PaperType.NORMAL), 25, 5);

        prtHouse.AddMachine(machine1);
        prtHouse.AddMachine(machine2);

        prtHouse.HireEmployee(emp1);
        prtHouse.HireEmployee(emp2);

       prtHouse.getEditionsToBePrinted().add(edition1);
       prtHouse.getEditionsToBePrinted().add(edition2);
       prtHouse.getEditionsToBePrinted().add(edition3);
       prtHouse.getEditionsToBePrinted().add(edition4);

       for(Machine machine : prtHouse.getMachines()){
           machine.LoadMachine(800);
       }
       prtHouse.SetEmployeesSalary(BigDecimal.valueOf(1500));

       for(Edition edition : prtHouse.getEditionsToBePrinted()) {
           edition.getPaper().setPrice(BigDecimal.valueOf(10));
       }




        try {
            prtHouse.PrintEditions();
        } catch (NotEnoughPaperException e) {
            e.printStackTrace();
        }

        System.out.println(" ");
        System.out.println("Manager salary before bonus: ");
        System.out.println(emp1.getBaseSalary());

        prtHouse.IncreaseSalaries();

        System.out.println("Manager salary after bonus: ");
        System.out.println(emp1.getBaseSalary());

        String filetest = "PrintHouse2.txt";

        prtHouse.WriteFile(filetest);

        prtHouse.ReadFromFile(filetest);






    }
}