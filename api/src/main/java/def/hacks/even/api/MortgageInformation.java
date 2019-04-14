package def.hacks.even.api;

public class MortgageInformation {
    public enum propertyType {
        condo, multi_unit, rent, single_family, townhouse
    }

    public enum propertyStatus {
        own_outright, own_with_mortgage, rent
    }

    public int propertyValue;
    public int mortgageBalance;
    public String lenderName;
    public boolean hasFHALoan;
    public boolean currentWithLoan;
}
