package stockMarketChartingApp.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import stockMarketChartingApp.Entity.ExchangeEntity;
import stockMarketChartingApp.Repository.ExchangeRepository;
import stockMarketChartingApp.exception.ResourceNotFoundException;

@Service
public class ExchangeService {
	
	private static final Logger logger = LoggerFactory.getLogger(ExchangeService.class);

    @Autowired
    private ExchangeRepository exchangeRepository;

    public ExchangeEntity addExchange(ExchangeEntity exchangeEntity) {
    	 logger.info("Adding a new exchange: {}", exchangeEntity);
        return exchangeRepository.save(exchangeEntity);
    }

    public ExchangeEntity updateExchange(Long id, ExchangeEntity exchangeEntity) {
    	 logger.info("Updating exchange with id: {}", id);
        if (!exchangeRepository.existsById(id)) {
        	  logger.error("Exchange with id {} not found", id);
            throw new ResourceNotFoundException("Exchange not found with id: " + id);
            
        }
        exchangeEntity.setId(id);
        return exchangeRepository.save(exchangeEntity);
    }

    public void deleteExchange(Long id) {
    	logger.info("Deleting exchange with id: {}", id);
        if (!exchangeRepository.existsById(id)) {
        	logger.error("Exchange with id {} not found", id);
            throw new ResourceNotFoundException("Exchange not found with id: " + id);
        }
        exchangeRepository.deleteById(id);
    }

    public ExchangeEntity getExchange(Long id) {
    	logger.info("Fetching exchange with id: {}", id);
        return exchangeRepository.findById(id)
                .orElseThrow();
    }
}
