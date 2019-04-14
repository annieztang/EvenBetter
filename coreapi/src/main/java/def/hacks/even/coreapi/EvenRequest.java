package def.hacks.even.coreapi;

import com.google.gson.Gson;

public class EvenRequest {
    public ProductTypes[] productTypes;
    public PersonalInformation personalInformation;
    public LoanInformation loanInformation;
    public MortgageInformation mortgageInformation;
    public CreditCardInformation creditCardInformation;
    public CreditInformation creditInformation;
    public FinancialInformation financialInformation;
    public EmploymentInformation employmentInformation;
    public LegalInformation legalInformation;

    public static final String TEMP = "{\n" +
        " \"productTypes\": [\n" +
        "   \"loan\",\n" +
        "   \"savings\"\n" +
        " ],\n" +
        " \"personalInformation\": {\n" +
        "   \"firstName\": \"John\",\n" +
        "   \"lastName\": \"Doe\",\n" +
        "   \"email\": \"john@example.com\",\n" +
        "   \"city\": \"New York\",\n" +
        "   \"state\": \"NY\",\n" +
        "   \"workPhone\": \"2125551234\",\n" +
        "   \"primaryPhone\": \"2125556789\",\n" +
        "   \"address1\": \"45 West 21st Street\",\n" +
        "   \"address2\": \"5th Floor\",\n" +
        "   \"zipcode\": \"10010\",\n" +
        "   \"monthsAtAddress\": 5,\n" +
        "   \"driversLicenseNumber\": \"111222333\",\n" +
        "   \"driversLicenseState\": \"NY\",\n" +
        "   \"ipAddress\": \"8.8.8.8\",\n" +
        "   \"activeMilitary\": false,\n" +
        "   \"militaryVeteran\": true,\n" +
        "   \"dateOfBirth\": \"1993-10-09\",\n" +
        "   \"educationLevel\": \"bachelors\",\n" +
        "   \"ssn\": \"111-22-3333\"\n" +
        " },\n" +
        " \"loanInformation\": {\n" +
        "   \"purpose\": \"home_refi\",\n" +
        "   \"loanAmount\": 10000\n" +
        " },\n" +
        " \"mortgageInformation\": {\n" +
        "   \"propertyType\": \"condo\",\n" +
        "   \"propertyStatus\": \"own_with_mortgage\",\n" +
        "   \"propertyValue\": 200000,\n" +
        "   \"mortgageBalance\": 10000,\n" +
        "   \"lenderName\": \"Bank OF NY\",\n" +
        "   \"hasFHALoan\": true,\n" +
        "   \"currentWithLoan\": true\n" +
        " },\n" +
        " \"creditCardInformation\": {\n" +
        "   \"allowAnnualFee\": true,\n" +
        "   \"cardBenefits\": [\n" +
        "     \"travel_incentives\"\n" +
        "   ]\n" +
        " },\n" +
        " \"creditInformation\": {\n" +
        "   \"providedCreditRating\": \"excellent\",\n" +
        "   \"providedNumericCreditScore\": 750\n" +
        " },\n" +
        " \"financialInformation\": {\n" +
        "   \"employmentStatus\": \"employed\",\n" +
        "   \"employmentPayFrequency\": \"weekly\",\n" +
        "   \"annualIncome\": 70000,\n" +
        "   \"monthlyNetIncome\": 10000,\n" +
        "   \"bankName\": \"Santander\",\n" +
        "   \"bankRoutingNumber\": \"231372691\",\n" +
        "   \"bankAccountType\": \"savings\",\n" +
        "   \"monthsAtBank\": 10,\n" +
        "   \"bankAccountNumber\": \"1234567890\"\n" +
        " },\n" +
        " \"employmentInformation\": {\n" +
        "   \"employerName\": \"EVEN Financial\",\n" +
        "   \"employerAddress\": \"45 W 21st St\",\n" +
        "   \"employerCity\": \"New York\",\n" +
        "   \"employerState\": \"NY\",\n" +
        "   \"employerZip\": \"10010\",\n" +
        "   \"jobTitle\": \"Software Engineer\",\n" +
        "   \"monthsEmployed\": 14,\n" +
        "   \"directDeposit\": true,\n" +
        "   \"payDate1\": \"2004-10-06\",\n" +
        "   \"payDate2\": \"2004-11-06\"\n" +
        " },\n" +
        " \"legalInformation\": {\n" +
        "   \"consentsToFcra\": true,\n" +
        "   \"consentsToTcpa\": true,\n" +
        "   \"tcpaLanguage\": \"I agree to be contacted by Even Financial and its partners at the telephone number(s) I have provided above to explore personal loan offers, including contact through automatic dialing systems, artificial or pre-recorded voice messaging, or text message. I understand my consent is not required as a condition to purchasing any goods or services from anyone.\"\n" +
        " },\n" +
        " \"clientTags\": {\n" +
        "   \"hello\": [\n" +
        "     \"world\",\n" +
        "     \"there\"\n" +
        "   ],\n" +
        "   \"something\": [\n" +
        "     \"else\"\n" +
        "   ]\n" +
        " }\n" +
        "}";

    public static EvenRequest temp(Gson gson) {
        return gson.fromJson(TEMP, EvenRequest.class);
    }
}


