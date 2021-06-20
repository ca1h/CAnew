package ie.cct.cbwa.cashowcase.model;

import ie.cct.cbwa.cashowcase.dto.ExpenseDto;

public class Expense {
    private Double value;
    private String description;
    private User user;

    public Expense(Double value, String description, User user) {
        this.value = value;
        this.description = description;
        this.user = user;
    }

    public  Expense(ExpenseDto dto, User u) {
        this.value = dto.getValue();
        this.description = dto.getDescription();
        this.user = u;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "value=" + value +
                ", description='" + description + '\'' +
                ", user=" + user +
                '}';
    }
}
