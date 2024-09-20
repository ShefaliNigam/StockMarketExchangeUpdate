package stockMarketChartingApp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import stockMarketChartingApp.Entity.ExchangeEntity;

@Repository
public interface ExchangeRepository extends JpaRepository<ExchangeEntity,Long> {

}
