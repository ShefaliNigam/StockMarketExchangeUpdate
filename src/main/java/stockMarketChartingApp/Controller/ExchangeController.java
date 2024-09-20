package stockMarketChartingApp.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import stockMarketChartingApp.Entity.ExchangeEntity;
import stockMarketChartingApp.Service.ExchangeService;

@RestController
@RequestMapping("/api/exchanges")
@Validated
public class ExchangeController {
	private static final Logger logger = LoggerFactory.getLogger(ExchangeController.class);

    @Autowired
    private ExchangeService exchangeService;

    @PostMapping("/add")
    public ResponseEntity<ExchangeEntity> addExchange(@Valid @RequestBody ExchangeEntity exchangeEntity) {
    	logger.info("Received request to add new exchange: {}", exchangeEntity);
        return ResponseEntity.ok(exchangeService.addExchange(exchangeEntity));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExchangeEntity> updateExchange(@PathVariable Long id,@Valid @RequestBody ExchangeEntity exchangeEntity) {
    	logger.info("Received request to update exchange with id: {}", id);
        return ResponseEntity.ok(exchangeService.updateExchange(id, exchangeEntity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExchange(@PathVariable Long id) {
    	logger.info("Received request to delete exchange with id: {}", id);
        exchangeService.deleteExchange(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExchangeEntity> getExchange(@PathVariable Long id) {
    	logger.info("Received request to fetch exchange with id: {}", id);
        return ResponseEntity.ok(exchangeService.getExchange(id));
    }
}
