package def.hacks.even.api;

public class LoanInformation {
    // todo fix this later?
    public enum Purpose {
        auto, boat, business, debt_consdolidation, green, home_improvement, household_expenses,
        large_purchases, medical_dental, moving_relocation, other, student_loan, taxes, vacation,
        wedding
    }

    public String purpose;
    public long loanAmount;
}


