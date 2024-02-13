package simubank.bankapi.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import simubank.bankapi.models.Account;
import simubank.bankapi.models.Card;
import simubank.bankapi.models.Tpe;
import simubank.bankapi.models.Trame;
import simubank.bankapi.repositories.*;

@RestController
public class API {

    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private TpeRepository TpeRepository;

    public void verifCard(Trame trame, Card card){
        //On vérifie si le carte est encore valide
        if(card.getDate().before(trame.horodatage)){
            trame.reponse = "Carte expirée";
            return;
        }
        //On vérifie si elle est pas opposé
        if(card.isOpposed()){
            trame.reponse = "Carte en opposition";
            return;
        }
        //On vérifie si le solde du client est suffisant
        Account account = card.getAccount();
        if(account.getBalance() < trame.montant){
            trame.reponse = "solde insuffisant";
            return;
        }
        //La carte est valide
        trame.reponse = "OK";
    }

    public void debit(Trame trame, Account account){
        // Débiter le compte du montant de la transaction
        account.setBalance(account.getBalance() - trame.montant);
        accountRepository.save(account);
        trame.reponse = "DEBIT_OK";
    }

    public void credit(Trame trame){
        //On récupère le compte du commercant
        Optional<Tpe> optionalTpe = TpeRepository.findById(trame.emetteur);
        if(!optionalTpe.isPresent()){
            //Le tpe n'a pas était trouver
            trame.reponse = "TPE non identifie, compte introuvable";
            return;
        }
        Tpe tpe = optionalTpe.get();
        Account account = tpe.getAccount();
        //Si la transacation n'a pas echouer
        if(trame.reponse.equals("DEBIT_OK")){
            //On crédite le commercant
            account.setBalance(account.getBalance() + trame.montant);
            accountRepository.save(account);
            trame.reponse="CREDIT_OK";
        }
        //Sinon on aura toujours le bon message d'erreur dans le champ reponse
    }
    

    @PostMapping("/transaction")
    public Trame Transaction(@RequestBody Trame trame) {
        //Si la banque joue le role de BP -> débit
        if(trame.entete.equals("DemandePorteur")){
            // Récupérer la carte associée au numéro de carte de la trame
            Card card = cardRepository.findByCardNumber(trame.cardNumber);
            //On vérifie la carte
            verifCard(trame, card);
            if(trame.reponse.equals("OK")){
                //la carte est valide et le solde suffisant
                //On débite le porteur
                debit(trame,card.getAccount());
                //On modifie l'entete 
                trame.entete="Reponse";
            }
        }
        //Si la banque joue le role de BC -> crédit
        else if(trame.entete.equals("Reponse")){
            credit(trame);
        }
        else if(trame.entete.equals("DemandeTPE")){
            trame.entete = "DemandePorteur";
        }
        /* A rajouter  
         redirection de la trame en focntion de l'entete :
         si entete = "Demande TPE" -> envoyer au giecb 
                   = "DemandePorteur" -> envoyer au giecb
                   = "reponse" -> envoyer au tpe
        */
        return trame;
    }

}
