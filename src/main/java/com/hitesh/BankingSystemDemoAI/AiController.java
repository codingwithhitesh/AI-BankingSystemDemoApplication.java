package com.hitesh.BankingSystemDemoAI;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ai")
public class AiController {

    private final AiBankingAssistantService aiService;

    public AiController(AiBankingAssistantService aiService) {
        this.aiService = aiService;
    }

    // Endpoint: http://localhost:8080/api/ai/customer/1/ask?msg=Can I afford a laptop for 45000?
    @GetMapping("/customer/{id}/ask")
    public String askAboutMyAccount(@PathVariable Long id, @RequestParam String msg) {
        return aiService.chatWithCustomerRAG(id, msg);
    }


    // Endpoint URL: GET http://localhost:8080/api/ai/chat?message=YourQuestionHere
    @GetMapping("/chat")
    public String chatWithBankBot(@RequestParam String message) {
        return aiService.askGeneralAssistant(message);
    }
}


//*****************  OLD VERSION  **********************************************************************

// below code is for just paragraph type response, not json
/*
package com.hitesh.BankingSystemDemo;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ai")
public class AiController {

    private final AiBankingAssistantService aiService;

    public AiController(AiBankingAssistantService aiService) {
        this.aiService = aiService;
    }

    // Endpoint URL: GET http://localhost:8080/api/ai/chat?message=YourQuestionHere
    @GetMapping("/chat")
    public String chatWithBankBot(@RequestParam String message) {
        return aiService.askAssistant(message);
    }
}

 */


