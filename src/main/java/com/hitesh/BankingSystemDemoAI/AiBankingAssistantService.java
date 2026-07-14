package com.hitesh.BankingSystemDemoAI;

import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.stereotype.Service;

@Service
public class AiBankingAssistantService {

    private final OllamaChatModel chatModel;
    private final AccountRepository accountRepository;

    // Constructor injection for both AI model and H2 database repository
    public AiBankingAssistantService(OllamaChatModel chatModel, AccountRepository accountRepository) {
        this.chatModel = chatModel;
        this.accountRepository = accountRepository;
    }

    /**
     * Handles customer queries using RAG (Retrieval-Augmented Generation).
     * Fetches live account data from H2 DB and augments the AI prompt.
     */
    public String chatWithCustomerRAG(Long customerId, String userMessage) {
        // 1. RETRIEVAL: Fetch live data from H2 relational database
        BankAccount account = accountRepository.findById(customerId).orElse(null);

        if (account == null) {
            return "Hello! I couldn't find an account matching that ID in our records. Please verify your details.";
        }

        // 2. AUGMENTATION: Inject live database values into system prompt
        String fullPromptText = """
            You are a polite, concise customer support bot for Hitesh's Demo Bank.
            
            OFFICIAL BRANCH INFORMATION:
            - Location: Chandpole Market, Jaipur, India.
            - MD and CEO: Shruti Rathore.
            - Savings Interest Rate: 4%%.
            
            LIVE RELEVANT CONTEXT (FROM H2 DATABASE):
            - Customer Name: %s
            - Current Balance: INR %.2f
            - Account Type: %s
            
            USER QUESTION:
            %s
            
            INSTRUCTION: Use the database context above to answer the user's question directly, accurately, and politely. 
            Keep your answer to a short, friendly paragraph.
            """.formatted(account.getUser(), account.getBalance(), account.getType(), userMessage);

        // 3. GENERATION: Pass augmented prompt to Llama 3.2 for response
        return chatModel.call(fullPromptText);
    }

    /**
     * Handles general queries (for customers and non-customers).
     */
    public String askGeneralAssistant(String userPrompt) {
        String basicPrompt = """
            You are a polite, concise customer support bot for Hitesh's Demo Bank.
            Use the following official branch information to answer questions:
            
            - Working Hours: Monday to Friday, 9:00 AM to 4:00 PM.
            - Closed on: Saturdays, Sundays, and public holidays.
            - Branch Location: Chandpole Market, Jaipur, India.
            - MD and CEO: Shruti Rathore.
            - Employees: 2000
            - Turnover: 2000 crore
            - Services: Savings, Current, and Gold Loan accounts
            - Loans: Car, Bike, Home (No loan against FD)
            - Savings Interest Rate: 4%%
            
            If a user asks about anything outside this information or core banking transactions, 
            politely decline to answer.
            """;

        return chatModel.call(basicPrompt);
    }
}


//*****************  OLD VERSION  **********************************************************************



// below code is for just paragraph type response, not json

/*package com.hitesh.BankingSystemDemo;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;



@Service
public class AiBankingAssistantService {

    private final ChatClient chatClient;

    public AiBankingAssistantService(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder
                .defaultSystem("""
                    You are a polite, concise customer support bot for Hitesh's Demo Bank.
                     Use the following official branch information to answer questions:
                    
                    - Working Hours: Monday to Friday, 9:00 AM to 4:00 PM.
                    - Closed on: Saturdays, Sundays, and public holidays.
                    - Branch Location: Chandpole Market, Jaipur, India. MD and CEO of bank is Shruti Rathore. 
                    it has 2000 employees and turnover of 2000 crore. opens saving, current and gold loan account.. does 
                    all type of loans like cars , bike and home. saving interest rate is 4 %. we dont do loan against FD
                    
                    
                    If a user asks about anything outside this information or core banking transactions, 
                    politely decline to answer.
                    """)
                .build();
    }

    public String askAssistant(String userPrompt) {
        return chatClient.prompt()
                .user(userPrompt)
                .call()
                .content();
    }
}

 */

// ollama pull llama3.2  (To start Ollama , paste this command in commamd prompt)
//Postman URL: http://localhost:8080/api/ai/chat?message=hello

