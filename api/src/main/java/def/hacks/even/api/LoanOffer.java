package def.hacks.even.api;

/**
 * Created by William Zulueta on 4/13/19.
 */
public class LoanOffer {
    public String uuid;
    public Originator originator;
    public int termLength;
    public String termUnit;
    public int maxAmount;
    public int minAmount;
    public double maxTotalPayment;
    public double minTotalPayment;
    public String terms;
    public String url;
    public boolean preQualified;
    public boolean preApproved;
    public boolean secured;
    public boolean sponsored;
    public int recommendationScore;
    public String productType;
    public String productSubType;
}
