package ie.cct.cbwa.cashowcase.dto;


import ie.cct.cbwa.cashowcase.model.Expense;
import ie.cct.cbwa.cashowcase.model.Trip;

import java.util.ArrayList;
import java.util.List;

public class TripDto {
    private Boolean isActive = true;
    private List<ExpenseUserDto> expenses = new ArrayList<>();

    public TripDto(Trip trip) {
        this.isActive = trip.getActive();
        for(Expense e : trip.getExpenses()) {
            expenses.add(new ExpenseUserDto(e));
        }

    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public List<ExpenseUserDto> getExpenses() {
        return expenses;
    }

    public void setExpenses(List<ExpenseUserDto> expenses) {
        this.expenses = expenses;
    }
}
