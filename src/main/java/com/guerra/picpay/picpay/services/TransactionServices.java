package com.guerra.picpay.picpay.services;

import com.guerra.picpay.picpay.DTO.EmailDTO;
import com.guerra.picpay.picpay.DTO.TransactionDTO;
import com.guerra.picpay.picpay.models.Transactions;
import com.guerra.picpay.picpay.models.Users;
import com.guerra.picpay.picpay.repositories.TransactionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class TransactionServices {

    @Autowired
    private UsersService usersService;

    @Autowired
    private TransactionalRepository transactionalRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private RestTemplate restTemplate;

    public Transactions transaction( TransactionDTO transactionDTO) throws Exception {
        System.out.println(transactionDTO.senderId());
        System.out.println(transactionDTO.receiverId());
        Users sender = this.usersService.findUserById(transactionDTO.senderId());
        Users receiver = this.usersService.findUserById(transactionDTO.receiverId());

        usersService.validateTransaction(sender,transactionDTO.amount());

        System.out.println(authorizate());

        if(!authorizate()){
            throw new Exception("Transaction not authorized");
        }

        Transactions transaction = new Transactions();
        transaction.setSender(sender);
        transaction.setReceiver(receiver);
        transaction.setAmount(transactionDTO.amount());

        sender.setBalance(sender.getBalance() - transactionDTO.amount());

        receiver.setBalance(receiver.getBalance() + transactionDTO.amount());

        transactionalRepository.save(transaction);
        usersService.save(receiver);
        usersService.save(sender);

        emailService.sendEmail(new EmailDTO(usersService.findUserById(receiver.getId()).getEmail(),"Transação realizada.","Transação realiazado com sucesso! Seu saldo agora é de R$" + usersService.findUserById(receiver.getId()).getBalance()));

        return transaction;
    }

    public boolean authorizate() {
        ResponseEntity<Map> authorizateResponse = restTemplate.getForEntity("https://util.devi.tools/api/v2/authorize",Map.class);

        if(authorizateResponse.getStatusCode() == HttpStatus.OK) {
            String status = (String) authorizateResponse.getBody().get("status");
            return "success".equalsIgnoreCase(status);
        } else return false;
    }
}
