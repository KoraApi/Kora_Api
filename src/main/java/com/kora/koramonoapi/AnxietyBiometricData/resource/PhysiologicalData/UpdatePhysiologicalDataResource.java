package com.kora.koramonoapi.AnxietyBiometricData.resource.PhysiologicalData;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdatePhysiologicalDataResource {
    private Integer heartRate;
    private Integer bloodOxygen;
    private Integer sleepHours;
}
