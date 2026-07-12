package com.hitesh.BankingSystemDemoAI;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/banking")
public class BankingController {

    private final BankingService bankingService;

    public BankingController(BankingService bankingService) {
        this.bankingService = bankingService;
    }

    // 1. Open Account
    @PostMapping("/open/{userId}")
    public ResponseEntity<String> openAccount(@PathVariable Long userId) {
        BankAccount account = bankingService.openSavingAccount(userId);
        return ResponseEntity.ok(account.getType() + " opened successfully. Acc No: " + account.getAccountNumber());
    }

    // 2. Deposit
    @PutMapping("/deposit/{accountNumber}")
    public ResponseEntity<BankAccount> deposit(@PathVariable Long accountNumber, @RequestParam double amount) {
        BankAccount account = bankingService.deposit(accountNumber, amount);
        return ResponseEntity.ok(account);
    }

    // 3. Withdraw
    @PutMapping("/withdraw/{accountNumber}")
    public ResponseEntity<BankAccount> withdraw(@PathVariable Long accountNumber, @RequestParam double amount) {
        BankAccount account = bankingService.withdraw(accountNumber, amount);
        return ResponseEntity.ok(account);
    }

    @GetMapping("/checkBalance/{accountNumber}")
    public ResponseEntity<Double> checkBalance(@PathVariable Long accountNumber) {
        double AccBal = bankingService.checkBalance(accountNumber);
        return ResponseEntity.ok(AccBal);
    }

    // 4. Close Account
    @DeleteMapping("/close/{accountNumber}")
    public ResponseEntity<String> closeAccount(@PathVariable Long accountNumber) {
        bankingService.closeAccount(accountNumber);
        return ResponseEntity.ok("Account " + accountNumber + " closed successfully.");
    }
    @PutMapping("/updateUserDetails/{userId}")
    public ResponseEntity<String> updateUserDetails(@PathVariable Long userId , @RequestParam String newCity) {
        bankingService.updateUserDetails(userId, newCity);
        return ResponseEntity.ok("User with userId : " + userId + " updated successfully.");
    }
}