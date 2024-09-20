package stockMarketChartingApp.Service;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
 
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
 
import stockMarketChartingApp.Entity.ExchangeEntity;
import stockMarketChartingApp.Repository.ExchangeRepository;
import stockMarketChartingApp.exception.ResourceNotFoundException;
 
public class ExchangeServiceTest {
	
	 @Mock
	    private ExchangeRepository exchangeRepository;
 
	    @InjectMocks
	    private ExchangeService exchangeService; // Replace with the actual service class name
 
	    @BeforeEach
	    void setUp() {
	        MockitoAnnotations.openMocks(this);
	    }
	    
	    @Test
	    void testAddExchange() {
	        ExchangeEntity exchangeEntity = new ExchangeEntity();
	        exchangeEntity.setId(1L); // Set any required fields here
 
	        when(exchangeRepository.save(exchangeEntity)).thenReturn(exchangeEntity);
 
	        ExchangeEntity result = exchangeService.addExchange(exchangeEntity);
 
	        assertNotNull(result);
	        assertEquals(exchangeEntity.getId(), result.getId());
	        verify(exchangeRepository, times(1)).save(exchangeEntity);
	    }
	    
	    @Test
	    void testUpdateExchange_Success() {
	        // Arrange
	        Long id = 1L;
	        ExchangeEntity existingEntity = new ExchangeEntity();
	        existingEntity.setId(id);
	        
	        ExchangeEntity updatedEntity = new ExchangeEntity();
	        updatedEntity.setId(id);
 
	        when(exchangeRepository.existsById(id)).thenReturn(true);
	        when(exchangeRepository.save(updatedEntity)).thenReturn(updatedEntity);
 
	        // Act
	        ExchangeEntity result = exchangeService.updateExchange(id, updatedEntity);
 
	        // Assert
	        assertNotNull(result);
	        assertEquals(id, result.getId());
	        verify(exchangeRepository, times(1)).existsById(id);
	        verify(exchangeRepository, times(1)).save(updatedEntity);
	    }
 
	    @Test
	    void testUpdateExchange_ResourceNotFound() {
	        // Arrange
	        Long id = 1L;
	        ExchangeEntity updatedEntity = new ExchangeEntity();
	        updatedEntity.setId(id);
 
	        when(exchangeRepository.existsById(id)).thenReturn(false);
 
	        // Act & Assert
	        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
	            exchangeService.updateExchange(id, updatedEntity);
	        });
 
	        assertEquals("Exchange not found with id: " + id, exception.getMessage());
	        verify(exchangeRepository, times(1)).existsById(id);
	        verify(exchangeRepository, never()).save(any(ExchangeEntity.class));
	    }
	    
	    @Test
	    void testDeleteExchange_Success() {
	        // Arrange
	        Long id = 1L;
	        when(exchangeRepository.existsById(id)).thenReturn(true);
 
	        // Act
	        exchangeService.deleteExchange(id);
 
	        // Assert
	        verify(exchangeRepository, times(1)).existsById(id);
	        verify(exchangeRepository, times(1)).deleteById(id);
	    }
 
	    @Test
	    void testDeleteExchange_ResourceNotFound() {
	        // Arrange
	        Long id = 1L;
	        when(exchangeRepository.existsById(id)).thenReturn(false);
 
	        // Act & Assert
	        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
	            exchangeService.deleteExchange(id);
	        });
 
	        assertEquals("Exchange not found with id: " + id, exception.getMessage());
	        verify(exchangeRepository, times(1)).existsById(id);
	        verify(exchangeRepository, never()).deleteById(id);
	    }
}
 