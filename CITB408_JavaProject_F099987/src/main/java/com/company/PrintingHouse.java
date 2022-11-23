package com.company;


import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class PrintingHouse {

    private String CompanyName;
    private List<Employee> Employees;
    private List<Machine> Machines;
    private List<Edition>EditionsToBePrinted;
    private BigDecimal percentageForSalaryIncrease;
    private BigDecimal percentageForDiscount;
    private BigDecimal TargetIncome;
    private int TargetCopies;

    public PrintingHouse(String companyName, BigDecimal percentage, BigDecimal percentageForDiscount, BigDecimal targetIncome, int copies) {
        CompanyName = companyName;
        this.percentageForSalaryIncrease = percentage;
        this.percentageForDiscount = percentageForDiscount;
        TargetIncome = targetIncome;//target income
        this.TargetCopies = copies;
        this.Employees = new ArrayList<>();
        this.Machines = new ArrayList<>();
        this.EditionsToBePrinted = new ArrayList<>();
    }

    public void setTargetCopies(int targetCopies) {
        TargetCopies = targetCopies;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public List<Employee> getEmployees() {
        return Employees;
    }

    public List<Edition> getEditionsToBePrinted() {
        return EditionsToBePrinted;
    }

    public List<Machine> getMachines() {
        return Machines;
    }

    public BigDecimal getPercentageForSalaryIncrease() {
        return percentageForSalaryIncrease;
    }

    public BigDecimal getTargetIncome() {
        return TargetIncome;
    }

    public void HireEmployee(Employee emp1) {
            if (this.Employees.contains(emp1)) {
                System.out.println("The Employee is already hired;");
                return;
            }else{
                this.Employees.add(emp1);
            }
    }

    public void FireEmployee(Employee emp){
        this.Employees.remove(emp);
    }

    public void AddMachine(Machine machine) {
        if(this.Machines.contains(machine)){
            System.out.println("Machine is already in list!");
            return;
        } else{
            this.Machines.add(machine);
        }
    }

    public void RemoveMachine(Machine machine){
        this.Machines.remove(machine);
    }

    public void PrintListOfEmployees(){
        for(Employee employee : this.Employees){
               System.out.println(employee);
        }
    }

    public BigDecimal CalculateIncome(){
        BigDecimal PricePerEdition = BigDecimal.valueOf(0);
        BigDecimal TotalIncome = BigDecimal.valueOf(0);

        for(Edition edition : this.EditionsToBePrinted){
            PricePerEdition = BigDecimal.valueOf(edition.getPaper().getPrice().doubleValue() * edition.getNumberOfPages() * edition.getCopies());

            if(edition.getCopies() > this.TargetCopies){
                BigDecimal discount = BigDecimal.valueOf((PricePerEdition.doubleValue()/100) * this.percentageForDiscount.doubleValue());
                PricePerEdition = PricePerEdition.subtract(discount);
            }

            TotalIncome = BigDecimal.valueOf(TotalIncome.doubleValue() + PricePerEdition.doubleValue());
        }

        TotalIncome = TotalIncome.setScale(1, RoundingMode.HALF_DOWN);

        return TotalIncome;
    }

    public void SetEmployeesSalary(BigDecimal salary){
        for(Employee emp : this.Employees){
            emp.setBaseSalary(salary);

        }
    }

    public void IncreaseSalaries(){
        BigDecimal BonusToSalary;

        for(Employee emp : this.Employees){

            BonusToSalary = BigDecimal.valueOf((emp.getBaseSalary().doubleValue() / 100) * this.percentageForSalaryIncrease.doubleValue());
            if(emp.getPosition() == EmployeePosition.MANAGER && (this.CalculateIncome().compareTo(this.TargetIncome) > 0)){
                emp.setBaseSalary(emp.getBaseSalary().add(BonusToSalary));
            }

        }

    }

    public BigDecimal ExpensesForSalaries(){
        BigDecimal ExpensesForSalaries = BigDecimal.valueOf(0);
        for(Employee employee : this.Employees){
            ExpensesForSalaries = ExpensesForSalaries.add(employee.getBaseSalary());
        }

        return ExpensesForSalaries;
    }

public BigDecimal ExpensesForPaper(){

    BigDecimal ExpensesForPaper = BigDecimal.valueOf(0);

    for(Edition edition : this.EditionsToBePrinted){
        ExpensesForPaper = ExpensesForPaper.add(BigDecimal.valueOf(edition.getPaper().getPrice().doubleValue() * edition.getNumberOfPages() * edition.getCopies()));
    }

    ExpensesForPaper = ExpensesForPaper.setScale(3, RoundingMode.HALF_UP);
    return ExpensesForPaper;

}



    public void PrintEditions() throws NotEnoughPaperException {

        int cntEditionsTotalPages = 0;
        int MachinesTotalPages = 0;

        for(Machine machine : this.Machines){
            if(machine.getNumOfCurrentPapers() == 0){
                throw new NotEnoughPaperException(machine);
            }

            MachinesTotalPages += machine.getNumOfCurrentPapers();
        }

        /*Scanner scanner = new Scanner(System.in);
        Scanner scanner2 = new Scanner(System.in);
        Scanner scanner3 = new Scanner(System.in);
        Scanner scanner4 = new Scanner(System.in);
        List<Edition> EditionsToBePrinted = new ArrayList<>(NumOfEditions);

        String EditionName;
        int NumOfPages; // CODE FOR CUSTOM INPUT
        PaperSize paperSize;
        PaperType paperType;
        Paper paper;

        for (int i = 0; i < NumOfEditions; i++) {

            System.out.println("Enter Edition name, Paper Size and type and number of pages: ");

            EditionName = scanner.nextLine();
            paperSize = PaperSize.valueOf(scanner2.nextLine());
            paperType = PaperType.valueOf(scanner3.nextLine());
            paper = new Paper(paperSize, paperType);

            NumOfPages = scanner4.nextInt();

            Edition edition = new Edition(EditionName, paper, NumOfPages);

            EditionsToBePrinted.add(edition);
            cntEditionsTotalPages += edition.getNumberOfPages();
        }*/


            for (Edition edition : this.EditionsToBePrinted) {
                edition.getPaper().CalculatePaperPrice();

                System.out.println(edition);

                for (int i = 1; i <= edition.getCopies(); i++) {

                    for(int j = 1; j <= edition.getNumberOfPages(); j++) {

                        System.out.println(edition.getName() + " copy №: " + i + " Page № " + j);
                        MachinesTotalPages -= 1;

                        if (MachinesTotalPages == 0) {

                            System.out.println("The paper in the machines has finished!");
                            return;
                        }
                    }
                    System.out.println(" ");
                }
            }
    }


    public void WriteFile(String Filename){
       BigDecimal tmp = this.CalculateIncome();
        BigDecimal tmp2 = this.ExpensesForSalaries();
        BigDecimal tmp3 = this.ExpensesForPaper();
        BigDecimal tmp4 = tmp2.add(tmp3);
        try (FileWriter fout = new FileWriter(Filename, false)){
            if(this != null){

                fout.append("Company name: " + this.getCompanyName() + System.lineSeparator() + this.EditionsToBePrinted + System.lineSeparator());
                fout.append("Company Income: " + tmp + System.lineSeparator() + "Expenses for salary: " + tmp2 + System.lineSeparator());
                fout.append("Expenses for paper: " + tmp3 + System.lineSeparator() + "Total Expenses: " + tmp4 + System.lineSeparator() + '\n');
                fout.append("List of workers:\n " + this.Employees + System.lineSeparator());
                }
        } catch (IOException e) {
            e.printStackTrace();
            }
    }

    public void ReadFromFile(String FilePath){

        try (BufferedReader br = new BufferedReader(new FileReader(FilePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public String toString() {
        return "PrintingHouse{" +
                "CompanyName='" + CompanyName + '\'' +
                ", Employees=" + Employees +
                ", Machines=" + Machines +
                ", EditionsToBePrinted=" + EditionsToBePrinted +
                ", percentageForSalaryIncrease=" + percentageForSalaryIncrease +
                ", percentageForDiscount=" + percentageForDiscount +
                ", TargetIncome=" + TargetIncome +
                ", TargetCopies=" + TargetCopies +
                '}';
    }
}
