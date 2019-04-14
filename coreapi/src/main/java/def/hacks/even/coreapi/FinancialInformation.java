package def.hacks.even.coreapi;

public class FinancialInformation {
    public enum EmploymentStatus {
        employed, military, not_employed, other, retired, self_employed

    }

    public String employmentStatus;

    public enum EmploymentPayFrequency {
        weekly, biweekly, twice_monthly, monthly
    }

    public String employmentPayFrequency;

    public int annualIncome;
    public int monthlyNetIncome;
    public String bankName;
    public String bankRoutingNumber;

    public enum BankAccountType {
        checking, savings, other
    }

    public String bankAccountType;

    public int monthsAtBank;
    public String bankAccountNumber;
}
