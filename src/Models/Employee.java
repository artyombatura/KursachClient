package Models;

import javafx.scene.control.TextField;

public class Employee {
    private String firstName;
    private String lastName;
    private String patronymic;
    private Integer salaryByContract;

    private TextField hoursWorkedTextField = new TextField();
    private TextField workRateTextField = new TextField();

    Integer countedSalary;

    public Employee(String firstName, String lastName, String patronymic, Integer salaryByContract) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.salaryByContract = salaryByContract;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public Integer getSalaryByContract() {
        return salaryByContract;
    }

    public Integer getCountedSalary() {
        return countedSalary;
    }

    public void setCountedSalary(Integer countedSalary) {
        this.countedSalary = countedSalary;
    }

    public TextField getHoursWorkedTextField() {
        return hoursWorkedTextField;
    }

    public void setHoursWorkedTextField(TextField hoursWorkedTextField) {
        this.hoursWorkedTextField = hoursWorkedTextField;
    }

    public TextField getWorkRateTextField() {
        return workRateTextField;
    }

    public void setWorkRateTextField(TextField workRateTextField) {
        this.workRateTextField = workRateTextField;
    }

    public Integer getHoursWorked() {
        return Integer.parseInt(this.hoursWorkedTextField.getText());
    }

    public Integer getWorkRate() {
        return Integer.parseInt(this.workRateTextField.getText());
    }

    @Override
    public String toString() {
        return "Employee{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", salaryByContract=" + salaryByContract +
                ", hoursWorkedTextField=" + getHoursWorked() +
                ", workRateTextField=" + getWorkRate() +
                ", countedSalary=" + countedSalary +
                '}';
    }
}
