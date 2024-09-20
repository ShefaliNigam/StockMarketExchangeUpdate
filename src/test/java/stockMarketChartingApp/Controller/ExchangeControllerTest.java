package stockMarketChartingApp.Controller;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
 
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
 
import com.fasterxml.jackson.databind.ObjectMapper;
 
import stockMarketChartingApp.Entity.ExchangeEntity;
import stockMarketChartingApp.Service.ExchangeService;
import stockMarketChartingApp.exception.ResourceNotFoundException;
 
@WebMvcTest(ExchangeController.class)
public class ExchangeControllerTest {
	
	@Autowired
    private MockMvc mockMvc;
 
    @MockBean
    private ExchangeService exchangeService;
 
    @Autowired
    private ObjectMapper objectMapper;
 
    private ExchangeEntity exchangeEntity;
 
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        exchangeEntity = new ExchangeEntity();
        exchangeEntity.setId(1L);
//        exchangeEntity.setName("Test Exchange");
    }
 
    @Test
    void testAddExchange() throws Exception {
        when(exchangeService.addExchange(any(ExchangeEntity.class))).thenReturn(exchangeEntity);
 
        mockMvc.perform(MockMvcRequestBuilders.post("/api/exchanges")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(exchangeEntity)));
                
    }
 
    @Test
    void testDeleteExchange_Success() throws Exception {
        doNothing().when(exchangeService).deleteExchange(anyLong());
 
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/exchanges/1"));
    }
 
    @Test
    void testDeleteExchange_NotFound() throws Exception {
        doThrow(new ResourceNotFoundException("Exchange not found with id: 1"))
            .when(exchangeService).deleteExchange(anyLong());
 
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/exchanges/1"));
    }
 
    @Test
    void testUpdateExchange_Success() throws Exception {
        ExchangeEntity updatedEntity = new ExchangeEntity();
        updatedEntity.setId(1L);
//        updatedEntity.setName("Updated Exchange");
 
        when(exchangeService.updateExchange(anyLong(), any(ExchangeEntity.class))).thenReturn(updatedEntity);
 
        mockMvc.perform(MockMvcRequestBuilders.put("/api/exchanges/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(exchangeEntity)));
    }
 
    @Test
    void testUpdateExchange_NotFound() throws Exception {
        when(exchangeService.updateExchange(anyLong(), any(ExchangeEntity.class)))
                .thenThrow(new ResourceNotFoundException("Exchange not found with id: 1"));
        mockMvc.perform(MockMvcRequestBuilders.put("/api/exchanges/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(exchangeEntity)));
    }
    
    @Test
    void testGetExchange_Success() throws Exception {
        when(exchangeService.getExchange(anyLong())).thenReturn(exchangeEntity);
 
        mockMvc.perform(MockMvcRequestBuilders.get("/api/exchanges/1")
                .contentType(MediaType.APPLICATION_JSON));
    }
 
    @Test
    void testGetExchange_NotFound() throws Exception {
        when(exchangeService.getExchange(anyLong()))
                .thenThrow(new ResourceNotFoundException("Exchange not found with id: 1"));
 
        mockMvc.perform(MockMvcRequestBuilders.get("/api/exchanges/1")
                .contentType(MediaType.APPLICATION_JSON));
    }
}
 