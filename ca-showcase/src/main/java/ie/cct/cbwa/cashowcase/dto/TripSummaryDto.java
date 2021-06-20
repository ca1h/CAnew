package ie.cct.cbwa.cashowcase.dto;


import ie.cct.cbwa.cashowcase.model.Expense;
import ie.cct.cbwa.cashowcase.model.Trip;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TripSummaryDto {
    private Boolean isActive = true;
    private Map<String, Double> expenses = new HashMap<>();
    private Double total = 0D;

    public TripSummaryDto(Trip trip) {
        this.isActive = trip.getActive();
        for(Expense e : trip.getExpenses()) {
            String username = e.getUser().getUsername();
            Double value = e.getValue();
            total += value;

            if(expenses.get(username) == null) {
                expenses.put(username, e.getValue());
            } else {
                Double newValue = e.getValue() + expenses.get(username);
                expenses.put(username, newValue);
            }
        }

    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Map<String, Double> getExpenses() {
        return expenses;
    }

    public void setExpenses(Map<String, Double> expenses) {
        this.expenses = expenses;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
