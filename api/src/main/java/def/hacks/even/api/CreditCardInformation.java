package def.hacks.even.api;

public class CreditCardInformation {
    public boolean allowAnnualFee;

    public enum CardBenefits {
        balance_transfer,earning_rewards, improve_credit, travel_incentives, low_interest, new_to_credit,
        student
    }
    public CardBenefits[] cardBenefits;

}
