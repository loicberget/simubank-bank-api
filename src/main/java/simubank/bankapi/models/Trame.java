package simubank.bankapi.models;

import java.sql.Timestamp;

public class Trame {
    public String entete;
    public Timestamp horodatage;
    public Long emetteur;   //id TPE
    public String banque;   //code banque
    public String cardNumber;
    public double montant;
    public String reponse;
}
