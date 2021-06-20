package ie.cct.cbwa.cashowcase.model;

import java.util.ArrayList;
import java.util.List;

public class Trip {
    private Boolean isActive = true;
    private List<Expense> expenses = new ArrayList<>();



    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    public void setExpenses(List<Expense> expenses) {
        this.expenses = expenses;
    }

    @Override
    public String toString() {
        return "Trip{" +
                "expenses=" + expenses +
                '}';
    }
}
