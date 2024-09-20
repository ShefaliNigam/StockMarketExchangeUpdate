package stockMarketChartingApp.Entity;

import java.util.List;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
@Entity
@Table(name="exchange")
public class ExchangeEntity {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
//	@NotBlank(message = "Name cannot be blank")
//	@Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
//	private String name;
	@NotBlank(message = "Country cannot be blank")
    @Size(min = 2, max = 50, message = "Country must be between 2 and 50 characters")
	private String country;
	
	@JdbcTypeCode(SqlTypes.JSON)
	private List<StockEntity> stocks;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public List<StockEntity> getStocks() {
		return stocks;
	}

	public void setStocks(List<StockEntity> stocks) {
		this.stocks = stocks;
	}

	@Override
	public String toString() {
		return "ExchangeEntity [id=" + id + ", country=" + country + ", stocks=" + stocks + "]";
	}

	public ExchangeEntity(Long id,
			@NotBlank(message = "Country cannot be blank") @Size(min = 2, max = 50, message = "Country must be between 2 and 50 characters") String country,
			List<StockEntity> stocks) {
		super();
		this.id = id;
		this.country = country;
		this.stocks = stocks;
	}

	public ExchangeEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
}
