package ie.cct.cbwa.cashowcase.dto;

import ie.cct.cbwa.cashowcase.model.Expense;

public class ExpenseUserDto {
    private Double value;
    private String description;
    private String user;

    public ExpenseUserDto(Double value, String description, String user) {
        this.value = value;
        this.description = description;
        this.user = user;
    }

    public ExpenseUserDto(Expense e) {
        this.value = e.getValue();
        this.description = e.getDescription();
        this.user = e.getUser().getName();
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

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}