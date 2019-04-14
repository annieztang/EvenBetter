package def.hacks.even.api;

public class FinancialInformation {
    public enum EmploymentStatus {
        employed,military, not_employed, other, retired, self_employed

    }
    public EmploymentStatus employmentStatus;

    public enum EmploymentPayFrequency {
        weekly, biweekly, twice_monthly, monthly
    }
    public EmploymentPayFrequency employmentPayFrequency;

    public long annualIncome;
    public long monthlyNetIncome;
    public String bankName;
    public long bankRoutingNumber;

    public enum BankAccountType {
        checking, savings, other
    }
    public BankAccountType bankAccountType;

    public int monthsAtBank;
    public long bankAccountNumeber;
}
