package co.edu.cesde.ga.application.repository;

import co.edu.cesde.ga.application.dto.request.CheckInRequest;
import co.edu.cesde.ga.application.dto.response.CheckInResponse;

public interface CheckInRepository {

    CheckInResponse realizarCheckIn(CheckInRequest request);
}
