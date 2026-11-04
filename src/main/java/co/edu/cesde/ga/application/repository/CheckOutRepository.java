package co.edu.cesde.ga.application.repository;

import co.edu.cesde.ga.application.dto.request.CheckOutRequest;
import co.edu.cesde.ga.application.dto.response.FacturaResponse;

public interface CheckOutRepository {

    FacturaResponse realizarCheckOut(CheckOutRequest request);
}
