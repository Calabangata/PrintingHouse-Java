package com.company;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Paper {

    private PaperSize Size;
    private PaperType Type;
    private BigDecimal Price;

    public Paper(PaperSize size, PaperType type) {
        Size = size;
        Type = type;

    }


    public PaperSize getSize() {
        return Size;
    }

    public PaperType getType() {
        return Type;
    }

    public BigDecimal getPrice() {
        return Price;
    }

    public void setPrice(BigDecimal price) {
        Price = price;
    }

    @Override
    public String toString() {
        return
                "Size=" + Size +
                ", Type=" + Type
                ;
    }

    public void CalculatePaperPrice(){

        BigDecimal Bonus;

        //BonusToSalary = BigDecimal.valueOf((emp.getBaseSalary().doubleValue() / 100) * this.percentage);

        switch (Size){

            case A1: Bonus = BigDecimal.valueOf((Price.doubleValue()/100) * 2);
            Price = BigDecimal.valueOf(Price.doubleValue() + Bonus.doubleValue());
                        break;


            case A2: Bonus = BigDecimal.valueOf((Price.doubleValue()/100) * 3);
                Price = BigDecimal.valueOf(Price.doubleValue() + Bonus.doubleValue());
                break;

            case A3: Bonus = BigDecimal.valueOf((Price.doubleValue()/100) * 4);
                Price = BigDecimal.valueOf(Price.doubleValue() + Bonus.doubleValue());
                break;

            case A4: Bonus = BigDecimal.valueOf((Price.doubleValue()/100) * 5);
                Price = BigDecimal.valueOf(Price.doubleValue() + Bonus.doubleValue());
                break;

            case A5: Bonus = BigDecimal.valueOf((Price.doubleValue()/100) * 6);
                Price = BigDecimal.valueOf(Price.doubleValue() + Bonus.doubleValue());
                break;

            default: break;
        }

        switch (Type){
            case NORMAL: Bonus = BigDecimal.valueOf((Price.doubleValue()/100) * 6);
                Price = BigDecimal.valueOf(Price.doubleValue() + Bonus.doubleValue());
                break;

            case GLOSSY: Bonus = BigDecimal.valueOf((Price.doubleValue()/100) * 8);
                Price = BigDecimal.valueOf(Price.doubleValue() + Bonus.doubleValue());
                break;

            case NEWSPAPER: Bonus = BigDecimal.valueOf((Price.doubleValue()/100) * 5);
                Price = BigDecimal.valueOf(Price.doubleValue() + Bonus.doubleValue());
                break;

            default: Price = BigDecimal.valueOf(0);
        }

        Price = Price.setScale(3, RoundingMode.HALF_UP);

    }
}
