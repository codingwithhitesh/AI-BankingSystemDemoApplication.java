package com.hitesh.BankingSystemDemoAI;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BankingService {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

    // Constructor Injection
    public BankingService(AccountRepository accountRepository, UserRepository userRepository) {
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public BankAccount openSavingAccount(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        BankAccount newAccount = new BankAccount(user);
        newAccount.setType("Saving");
        return accountRepository.save(newAccount);
    }

    @Transactional
    public void closeAccount(Long accountNumber) {
        BankAccount account = accountRepository.findById(accountNumber)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        accountRepository.delete(account);
    }

    @Transactional
    public BankAccount deposit(Long accountNumber, double amount) {
        BankAccount account = accountRepository.findById(accountNumber)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        account.deposit(amount);
        return accountRepository.save(account);
    }

    @Transactional
    public BankAccount withdraw(Long accountNumber, double amount) {
        BankAccount account = accountRepository.findById(accountNumber)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        account.withdraw(amount);
        return accountRepository.save(account);
    }

    @Transactional(readOnly = true)
    public double checkBalance(Long accountNumber) {
        BankAccount account = accountRepository.findById(accountNumber)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        return account.getBalance();
    }

    @Transactional
    public void updateUserDetails(Long userId , String newData) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setHomeCity(newData);

       userRepository.save(user);
    }

}

/*
    // --- NEW: Check Balance API ---


    @Transactional
    public BankAccount deposit(Long accountNumber, double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be greater than zero");
        }

        BankAccount account = accountRepository.findById(accountNumber)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        account.deposit(amount); // Updates internal state
        return accountRepository.save(account); // Flushes to DB
    }

    @Transactional
    public BankAccount withdraw(Long accountNumber, double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be greater than zero");
        }

        BankAccount account = accountRepository.findById(accountNumber)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        // Critical Sync Check: Prevent account from going negative
        if (account.getBalance() < amount) {
            throw new IllegalStateException("Insufficient funds for this withdrawal");
        }

        account.withdraw(amount); // Updates internal state
        return accountRepository.save(account); // Flushes to DB
    }

    // ... keeping openSavingAccount and closeAccount the same
}

 */

//************************************************************************************************************


/*FUND transfer

@Transactional
public void transferFunds(Long sourceAccountNumber, Long destinationAccountNumber, double amount) {
    if (amount <= 0) {
        throw new IllegalArgumentException("Transfer amount must be positive");
    }

    BankAccount sourceAccount = accountRepository.findById(sourceAccountNumber)
            .orElseThrow(() -> new RuntimeException("Source account not found"));

    BankAccount destinationAccount = accountRepository.findById(destinationAccountNumber)
            .orElseThrow(() -> new RuntimeException("Destination account not found"));

    // Deduct and add
    sourceAccount.withdraw(amount);
    destinationAccount.deposit(amount);

    accountRepository.save(sourceAccount);
    accountRepository.save(destinationAccount);

    // Bonus: Save a transaction log here (see point 2)
}
 */