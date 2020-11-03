package webfusion.lawyercrm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import webfusion.lawyercrm.controllers.responses.Response;
import webfusion.lawyercrm.services.LiquidationService;
import webfusion.lawyercrm.services.exceptions.LiquidationPageNotFoundException;

@RestController
@RequestMapping("api/liquidation")
public class LiquidationController {
    @Autowired
    private LiquidationService liquidationService;

    @GetMapping
    public Object get() {
        try {
            return new Response("GOOD", "Liquidation content", liquidationService.findById(1L).getText());
        } catch (LiquidationPageNotFoundException e) {
            return new Response("FAILED", "Liquidation doesn't fill", null);
        }
    }
}
