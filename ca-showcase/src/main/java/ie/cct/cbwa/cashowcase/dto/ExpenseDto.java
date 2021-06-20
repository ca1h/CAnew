package ie.cct.cbwa.cashowcase.dto;

public class ExpenseDto {
    private Double value;
    private String description;

    public ExpenseDto(Double value, String description) {
        this.value = value;
        this.description = description;
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
}